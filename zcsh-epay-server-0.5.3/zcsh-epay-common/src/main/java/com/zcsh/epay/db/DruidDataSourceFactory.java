/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.db;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
	public DruidDataSourceFactory() {
		this.dataSource = new DruidDataSource();
	}
}
