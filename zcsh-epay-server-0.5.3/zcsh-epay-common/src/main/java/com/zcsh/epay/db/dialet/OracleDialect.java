package com.zcsh.epay.db.dialet;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
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

