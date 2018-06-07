package com.zcsh.epay.db.interceptor;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.zcsh.epay.log.LogCvt;


@Intercepts(value = {
		@Signature(type = Executor.class, method = "update", args = {
				MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }) })
public class SqlStatementInterceptor implements Interceptor {
	private Properties properties;

	public Object intercept(Invocation arg0) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) arg0.getArgs()[0];
		String sqlId = mappedStatement.getId();
		Object returnValue;

		long start = System.currentTimeMillis();
		returnValue = arg0.proceed();
		long end = System.currentTimeMillis();
		long time = end - start;

		StringBuilder str = new StringBuilder(100);
		str.append(sqlId);
		str.append(": ");
		str.append("cost time ");
		str.append(time);
		str.append(" ms.");
		String sqlInfo = str.toString();
		LogCvt.debug(sqlInfo);
		return returnValue;
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties arg0) {
		this.properties = arg0;
	}
}