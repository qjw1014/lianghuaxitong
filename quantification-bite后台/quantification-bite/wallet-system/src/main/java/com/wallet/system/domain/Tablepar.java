package com.wallet.system.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * boostrap table post 参数
 * @author fc
 *
 */
public class Tablepar {
	@ApiModelProperty(value = "页码")
	private int pageNum=1;//页码
	@ApiModelProperty(value = "数量")
	private int pageSize=10;//数量
	private String orderByColumn;//排序字段
	@ApiModelProperty(value = "排序字符 asc desc ")
	private String isAsc;//排序字符 asc desc 
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderByColumn() {
		return orderByColumn;
	}
	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}
	public String getIsAsc() {
		return isAsc;
	}
	public void setIsAsc(String isAsc) {
		this.isAsc = isAsc;
	}
	
}
