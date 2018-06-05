/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.db.dialet;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public abstract class Dialect {

	 public boolean supportsLimit() {
	  return false;
	 }

	 public boolean supportsLimitOffset() {
	  return supportsLimit();
	 }

	 /**
	  * ��sql��ɷ�ҳsql���,ֱ��ʹ��offset,limit��ֵ��Ϊռλ��.</br> Դ����Ϊ:
	  * getLimitString(sql,offset
	  * ,String.valueOf(offset),limit,String.valueOf(limit))
	  */
	 public String getLimitString(String sql, int offset, int limit) {
	  return getLimitString(sql, offset, Integer.toString(offset), limit,
	    Integer.toString(limit));
	 }

	 /**
	  * ��sql��ɷ�ҳsql���,�ṩ��offset��limitʹ��ռλ��(placeholder)�滻.
	  * 
	  * <pre>
	  * ��mysql
	  * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") ������
	  * select * from user limit :offset,:limit
	  * </pre>
	  * 
	  * @return ����ռλ���ķ�ҳsql
	  */
	 public String getLimitString(String sql, int offset,
	   String offsetPlaceholder, int limit, String limitPlaceholder) {
	  throw new UnsupportedOperationException("paged queries not supported");
	 }
	}

