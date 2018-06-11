package com.zcsh.epay.db.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.zcsh.epay.db.dialet.Dialect;
import com.zcsh.epay.db.dialet.OracleDialect;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.util.Paging;
import com.zcsh.epay.util.ReflectHelper;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：分页插件
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PageInterceptor implements Interceptor {
	public final static String					SQL_SELECT_REGEX				= "(?is)^\\s*SELECT.*$";
	public final static String					SQL_COUNT_REGEX					= "(?is)^\\s*SELECT\\s+COUNT\\s*\\(\\s*(?:\\*|\\w+)\\s*\\).*$";

	private static final ObjectFactory			DEFAULT_OBJECT_FACTORY			= new DefaultObjectFactory();
	private static final ObjectWrapperFactory	DEFAULT_OBJECT_WRAPPER_FACTORY	= new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
	private static Dialect						databaseDialect					=  new OracleDialect();														// 数据库方

	public Object intercept(Invocation invocation) throws Throwable {
		
		StatementHandler delegate = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(delegate);

		// 分离代理对象链
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");

			metaStatementHandler = SystemMetaObject.forObject(object);
		}

		RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) metaStatementHandler.getOriginalObject();

		// 通过反射获取到当前RoutingStatementHandler对象的delegate属性
		StatementHandler delegateNew = (StatementHandler) ReflectHelper.getFieldVal(routingStatementHandler, "delegate");

		// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
		MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getFieldVal(delegateNew, "mappedStatement");

		BoundSql boundSql = delegate.getBoundSql();
		String sql = boundSql.getSql();
		if (sql.matches(SQL_SELECT_REGEX) && !Pattern.matches(SQL_COUNT_REGEX, sql)) {
			SqlSource sqlSource = mappedStatement.getSqlSource();
			if (DynamicSqlSource.class.isAssignableFrom(sqlSource.getClass())) {
				SqlNode sqlNode = (SqlNode) ReflectHelper.getFieldVal(sqlSource, "rootSqlNode");
				BoundSql boundSqlConvert = getBoundSql(mappedStatement.getConfiguration(), boundSql.getParameterObject(), sqlNode);
				ReflectHelper.setFieldVal(boundSql, "sql", boundSqlConvert.getSql());
			}
			StatementHandler target = (StatementHandler) invocation.getTarget();
			Object parameterObject = boundSql.getParameterObject();
			Object obj = FieldUtils.readField(target, "delegate", true);
			Paging paging = null;
			Object rowBounds = FieldUtils.readField(obj, "rowBounds", true);
			if (rowBounds instanceof Paging) {
				paging = (Paging) FieldUtils.readField(obj, "rowBounds", true);
			}

			// 拦截需要分页的SQL
			if (null != paging && paging.getLimit() != paging.DEFAULT.getLimit()) {

				// 获取到当前StatementHandler的 boundSql
				boundSql = delegate.getBoundSql();

				// 拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
				// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空

				// 拦截到的prepare方法参数是一个Connection对象
				Connection connection = (Connection) invocation.getArgs()[0];

				// 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
				sql = boundSql.getSql();
				// 记录统计
				@SuppressWarnings("static-access")
				String countSql = this.getCountSql(sql);
				
				countSql = countSql.replaceAll("\t", " ");
				countSql = countSql.replaceAll("\n", " ");
				// 通过connection建立一个countSql对应的PreparedStatement对象。
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
				parameterHandler.setParameters(countStmt);
				// 之后就是执行获取总记录数的Sql语句和获取结果了
				ResultSet rs = countStmt.executeQuery();
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				rs.close();
				countStmt.close();
				paging.setTotalCount(count);
				if(paging.getPageNumber() < paging.getPageCount()){
					paging.setHasNext(true);
				}

				// 获取分页Sql语句
				String pageSql = this.generatePageSql(paging, sql);
				//
				pageSql = pageSql.replaceAll("\t", " ");
				pageSql = pageSql.replaceAll("\n", " ");
				//
				// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
				ReflectHelper.setFieldVal(boundSql, "sql", pageSql);

				// 一定要还原否则将无法得到下一组数据(第一次的数据被缓存了)，好大一个坑啊。。。
				FieldUtils.writeField(rowBounds, "offset", RowBounds.NO_ROW_OFFSET, true);
				FieldUtils.writeField(rowBounds, "limit", RowBounds.NO_ROW_LIMIT, true);
			}
			// }
		}
		return invocation.proceed();
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private Paging getPaging(Object parameterObject) throws NoSuchFieldException {
		Paging page = null;
		if (parameterObject instanceof Paging) { // 参数就是Page实体
			page = (Paging) parameterObject;
		} else if (parameterObject instanceof Map) {
			for (Entry entry : (Set<Entry>) ((Map) parameterObject).entrySet()) {
				if (entry.getValue() instanceof Paging) {
					page = (Paging) entry.getValue();
					break;
				}
			}
		} else {
			// 参数为某个实体，该实体拥有Page属性
			Field pageField = ReflectHelper.getField(parameterObject, "paging");

			if (pageField != null) {
				page = (Paging) ReflectHelper.getFieldVal(parameterObject, "paging");
				if (page == null) {
					page = new Paging();
				}
				ReflectHelper.setFieldVal(parameterObject, "paging", page);
			}
		}
		return page;
	}

	/**
	 * 拦截器对应的封装原始对象的方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	private static String getCountSql(String sql) {
		String upperSql = sql.toUpperCase();
		if (upperSql.contains("SELECT")) {
			int order_by = upperSql.lastIndexOf(" ORDER BY ");
	        if (order_by > -1) {
	            upperSql = upperSql.substring(0, order_by);
	        }
			return "select count(1) from (" + upperSql + ") t";
		}else {
			return null;
		}
	}
	
	/**
     * 拼接获取条数的sql语句
     * <p>
     *
     * @param sqlPrimary
    */
    protected static String getCountSql2(String sqlPrimary) {
        String sqlUse = sqlPrimary.replaceAll("[\\s]+", " ");
        String upperString = sqlUse.toUpperCase();
        int order_by = upperString.lastIndexOf(" ORDER BY ");
        if (order_by > -1) {
            sqlUse = sqlUse.substring(0, order_by);
        }
        String[] paramsAndMethod = sqlUse.split("\\s");
        int count = 0;
        int index = 0;
        for (int i = 0; i < paramsAndMethod.length; i++) {
            String upper = paramsAndMethod[i].toUpperCase();
            if (upper.length() == 0) {
                continue;
            }
            if (upper.contains("SELECT")) {
                count++;
            } else if (upper.contains("FROM")) {
                count--;
            }
            if (count == 0) {
                index = i;
                break;
            }
        }
        StringBuilder return_sql = new StringBuilder("SELECT COUNT(1) AS cnt ");
        StringBuilder common_count = new StringBuilder();
        if (upperString.contains(" GROUP BY ")) {//简单的group by分页优化
        	common_count.append(" from (select count(1) ");
        }
        for (int j = index; j < paramsAndMethod.length; j++) {
            common_count.append(" ");
            common_count.append(paramsAndMethod[j]);
        }
        if (upperString.contains(" GROUP BY ")) {
        	common_count.append(" )");
        }
        return return_sql.append(common_count).toString();
    }

	/**
	 * 根据数据库方言，生成特定的分页sql
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	private String generatePageSql(Paging page, String sql) {

		if (page != null && databaseDialect != null) {
			// pageNumber默认是从1，而已数据库是从0开始计算的．所以(page.getPageNumber()-1)
			return databaseDialect.getLimitString(sql, page.getOffset(), page.getLimit());
		}
		LogCvt.info(sql);
		return sql;
	}

	public static BoundSql getBoundSql(Configuration configuration, Object parameterObject, SqlNode sqlNode) {
		DynamicContext context = new DynamicContext(configuration, parameterObject);
		sqlNode.apply(context);
		String countextSql = context.getSql();

		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
		Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
		String sql = modifyLikeSql(countextSql, parameterObject);
		SqlSource sqlSource = sqlSourceParser.parse(sql, parameterType, context.getBindings());

		BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
		for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
			boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
		}

		return boundSql;
	}

	public static String modifyLikeSql(String sql, Object parameterObject) {
		if (!sql.toLowerCase().contains("like"))
			return sql;
		String reg = null;

		reg = "\\s\\w+\\.?\\w*\\sLIKE\\s*(CONCAT\\((\\s*'%'\\s*\\,\\s*)?(\\s*(\\w*\\(*)(#|\\$)\\{(.+)\\}(\\s*\\)*)\\s*)(\\s*\\,\\s*'%'\\s*)\\))?";// "order\\s+by\\s+.+"
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sql);

		List<String> replaceEscape = new ArrayList<String>();
		List<String> replaceFiled = new ArrayList<String>();

		while (matcher.find()) {
			replaceEscape.add(matcher.group());
			int n = matcher.groupCount();
			for (int i = 0; i <= n; i++) {
				String output = matcher.group(i);
				if (3 == i && output != null) {
					replaceFiled.add(output.trim());
				}
			}
		}

		for (String s : replaceEscape) {
			sql = sql.replace(s, s + " ESCAPE '/' ");
		}

		MetaObject parameterObjectHandler = MetaObject.forObject(parameterObject, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);
		for (String s : replaceFiled) {
			String key = s.replace("#{", "").replace("}", "");
			Object val = null;
			val = parameterObjectHandler.getValue(key);
			if (val != null && val instanceof String && (val.toString().contains("%") || val.toString().contains("_") || val.toString().contains("/"))) {
				val = val.toString().replaceAll("%", "\\%").replaceAll("_", "\\_").replaceAll("/", "//");
				parameterObjectHandler.setValue(key, val);
			}
		}
		return sql;
	}

	public void setProperties(Properties properties) {

	}
}