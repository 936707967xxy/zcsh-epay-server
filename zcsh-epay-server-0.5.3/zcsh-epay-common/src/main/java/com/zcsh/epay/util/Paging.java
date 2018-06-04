/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import org.apache.ibatis.session.RowBounds;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public class Paging extends RowBounds {
	 // -- ��ҳ���� --//
	 protected int pageNumber = 1;// ��ǰҳ��
	 protected int pageSize = 10;// ÿҳ��¼��
	 protected long totalCount = 0;// �ܼ�¼��
	 protected int pageCount = 0;// ��ҳ��
	 protected boolean hasNext = false;//�Ƿ�����һҳ

	 public Paging() {
	  setParame();
	 }

	 public int getPageNumber() {
	  return pageNumber;
	 }

	 public void setPageNumber(int pageNumber) {
	  this.pageNumber = pageNumber;
	  setParame();
	 }

	 public int getPageSize() {
	  return pageSize;
	 }

	 public void setPageSize(int pageSize) {
	  this.pageSize = pageSize;
	  setParame();
	 }
	 
	 private void setParame() {
	  ReflectHelper.setFieldVal(this, "offset", (pageNumber <= 0 ? 0 : pageNumber - 1) * pageSize);
	  ReflectHelper.setFieldVal(this, "limit", this.pageSize);
	 }

	 public long getTotalCount() {
	  return totalCount;
	 }

	 public void setTotalCount(long totalCount) {
	  this.totalCount = totalCount;
	  long size = (long) pageSize;
	  setPageCount((int) (totalCount / size + (totalCount % size == 0 ? 0 : 1)));
	 }

	 public int getPageCount() {
	  return pageCount;
	 }

	 public void setPageCount(int pageCount) {
	  this.pageCount = pageCount;
	 }

	 public boolean isHasNext() {
	  return hasNext;
	 }

	 public void setHasNext(boolean hasNext) {
	  this.hasNext = hasNext;
	 }

	}

