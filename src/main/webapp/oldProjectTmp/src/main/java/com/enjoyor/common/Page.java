package com.enjoyor.common;

import java.util.List;

import com.enjoyor.common.util.ResourcesProperties;

public class Page<T> {

	private int pageSize;
	private int totalPages; // 总页数
	private int records; // 总记录数
	private int pageNo; // 当前页
	private int currentCount; // 当前记录起始索引

	private String sortExp; // 排序列

	private String sortDir; // 排序方向
	List<T> result;

	public String getSortExp() {
		return sortExp;
	}

	public void setSortExp(String sortExp) {
		this.sortExp = sortExp;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotalPages() {
		if (pageSize == 0) {
			return 0;
		}
		if (records % pageSize == 0)
			totalPages = records / pageSize;
		else
			totalPages = records / pageSize + 1;

		return totalPages;
	}

	public void settoTalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getPageNo() {
		if (pageNo <= 0)
			pageNo = 1;
		if (pageNo > getTotalPages())
			pageNo = getTotalPages();
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCurrentResult() {
		currentCount = (getPageNo() - 1) * getPageSize();
		if (currentCount < 0)
			currentCount = 0;
		return currentCount;
	}

	public void setCurrentResult(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setDefaultPageSize() {
		String defaultPageSize = "15";
		/*
		 * String defaultPageSize = ResourcesProperties.getInstance()
		 * .getResourcesPropreties().get("page.pageSize.default");
		 */
		this.setPageSize(Integer.valueOf(defaultPageSize));
	}

}
