package com.wallet.system.domain;

import java.io.Serializable;
import java.util.List;

/**
 * bootstrap table list 返回封装model
 * 
 * @ClassName: TableSplitResult
 * @author
 * @date 2019-09-08 03:59
 */
public class TableSplitResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	// 当前页
	private Integer page;
	// 总条数
	private Long total;
	// 总页数
	private Integer Pages;
	// 数据
	private List<T> rows;

	public TableSplitResult() {
	}

	public TableSplitResult(Integer pageNum, Long total, List<T> rows) {
		this.page = pageNum;
		this.total = total;
		this.rows = rows;
	}

	public TableSplitResult(Integer pageNum, Long total, List<T> rows, int totalPages) {
		this.page = pageNum;
		this.total = total;
		this.rows = rows;
		this.Pages = totalPages;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getPages() {
		return Pages==null?0:Pages;
	}

	public void setPages(Integer pages) {
		Pages = pages;
	}

}
