package com.zcsh.epay.db.dialet;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public class OracleDialect extends Dialect{
	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		StringBuilder pageSql = new StringBuilder();

		if (offset > 0) {
			pageSql.append("select * from ( select temp.*,ROWNUMBER() OVER() as row_id from ( ");
			pageSql.append(sql);
			pageSql.append(" ) temp ");
			pageSql.append(") where row_id <= ").append(offset + limit).append(" and row_id > ")
					.append(offsetPlaceholder);
			return pageSql.toString();

			// return sql + " limit "+offsetPlaceholder+","+limitPlaceholder;
		} else {
			pageSql.append("select * from ( select temp.*, ROWNUMBER() OVER() as row_id from ( ");
			pageSql.append(sql);
			pageSql.append(" ) temp ) temp where row_id <= ").append(limitPlaceholder);
			return pageSql.toString();
			// // return sql + " limit "+limitPlaceholder;
		}
	}
}

