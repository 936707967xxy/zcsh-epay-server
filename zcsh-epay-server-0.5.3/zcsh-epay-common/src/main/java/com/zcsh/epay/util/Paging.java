package com.zcsh.epay.util;

import org.apache.ibatis.session.RowBounds;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public class Paging extends RowBounds {
	 // -- 分页参数 --//
	 protected int pageNumber = 1;// 当前页码
	 protected int pageSize = 10;// 每页记录数
	 protected long totalCount = 0;// 总记录数
	 protected int pageCount = 0;// 总页数
	 protected boolean hasNext = false;//是否有下一页

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
