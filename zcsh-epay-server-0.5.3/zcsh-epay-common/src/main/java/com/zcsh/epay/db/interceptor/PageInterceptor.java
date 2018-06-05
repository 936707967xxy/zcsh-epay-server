/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ��������ҳ���
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PageInterceptor implements Interceptor {
	public final static String					SQL_SELECT_REGEX				= "(?is)^\\s*SELECT.*$";
	public final static String					SQL_COUNT_REGEX					= "(?is)^\\s*SELECT\\s+COUNT\\s*\\(\\s*(?:\\*|\\w+)\\s*\\).*$";

	private static final ObjectFactory			DEFAULT_OBJECT_FACTORY			= new DefaultObjectFactory();
	private static final ObjectWrapperFactory	DEFAULT_OBJECT_WRAPPER_FACTORY	= new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
	private static Dialect						databaseDialect					=  new OracleDialect();														// ���ݿⷽ

	public Object intercept(Invocation invocation) throws Throwable {
		
		StatementHandler delegate = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(delegate);

		// ������������
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		// �������һ����������Ŀ����
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");

			metaStatementHandler = SystemMetaObject.forObject(object);
		}

		RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) metaStatementHandler.getOriginalObject();

		// ͨ�������ȡ����ǰRoutingStatementHandler�����delegate����
		StatementHandler delegateNew = (StatementHandler) ReflectHelper.getFieldVal(routingStatementHandler, "delegate");

		// ͨ�������ȡdelegate����BaseStatementHandler��mappedStatement����
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

			// ������Ҫ��ҳ��SQL
			if (null != paging && paging.getLimit() != paging.DEFAULT.getLimit()) {

				// ��ȡ����ǰStatementHandler�� boundSql
				boundSql = delegate.getBoundSql();

				// �õ���ǰ��Sql�Ĳ������󣬾��������ڵ��ö�Ӧ��Mapperӳ�����ʱ������Ĳ�������
				// ��ҳSQL<select>��parameterType���Զ�Ӧ��ʵ���������Mapper�ӿ���ִ�з�ҳ�����Ĳ���,�ò�������Ϊ��

				// ���ص���prepare����������һ��Connection����
				Connection connection = (Connection) invocation.getArgs()[0];

				// ��ȡ��ǰҪִ�е�Sql��䣬Ҳ��������ֱ����Mapperӳ�������д��Sql���
				sql = boundSql.getSql();
				// ��¼ͳ��
				@SuppressWarnings("static-access")
				String countSql = this.getCountSql(sql);
				
				countSql = countSql.replaceAll("\t", " ");
				countSql = countSql.replaceAll("\n", " ");
				// ͨ��connection����һ��countSql��Ӧ��PreparedStatement����
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
				parameterHandler.setParameters(countStmt);
				// ֮�����ִ�л�ȡ�ܼ�¼����Sql���ͻ�ȡ�����
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

				// ��ȡ��ҳSql���
				String pageSql = this.generatePageSql(paging, sql);
				//
				pageSql = pageSql.replaceAll("\t", " ");
				pageSql = pageSql.replaceAll("\n", " ");
				//
				// ���÷������õ�ǰBoundSql��Ӧ��sql����Ϊ���ǽ����õķ�ҳSql���
				ReflectHelper.setFieldVal(boundSql, "sql", pageSql);

				// һ��Ҫ��ԭ�����޷��õ���һ������(��һ�ε����ݱ�������)���ô�һ���Ӱ�������
				FieldUtils.writeField(rowBounds, "offset", RowBounds.NO_ROW_OFFSET, true);
				FieldUtils.writeField(rowBounds, "limit", RowBounds.NO_ROW_LIMIT, true);
			}
			// }
		}
		return invocation.proceed();
	}

	private Paging getPaging(Object parameterObject) throws NoSuchFieldException {
		Paging page = null;
		if (parameterObject instanceof Paging) { // ��������Pageʵ��
			page = (Paging) parameterObject;
		} else if (parameterObject instanceof Map) {
			for (Entry entry : (Set<Entry>) ((Map) parameterObject).entrySet()) {
				if (entry.getValue() instanceof Paging) {
					page = (Paging) entry.getValue();
					break;
				}
			}
		} else {
			// ����Ϊĳ��ʵ�壬��ʵ��ӵ��Page����
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
	 * ��������Ӧ�ķ�װԭʼ����ķ���
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * ����ԭSql����ȡ��Ӧ�Ĳ�ѯ�ܼ�¼����Sql���
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
     * ƴ�ӻ�ȡ������sql���
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
        if (upperString.contains(" GROUP BY ")) {//�򵥵�group by��ҳ�Ż�
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
	 * �������ݿⷽ�ԣ������ض��ķ�ҳsql
	 * 
	 * @param page
	 * @param sql
	 * @return
	 */
	private String generatePageSql(Paging page, String sql) {

		if (page != null && databaseDialect != null) {
			// pageNumberĬ���Ǵ�1���������ݿ��Ǵ�0��ʼ����ģ�����(page.getPageNumber()-1)
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

