package com.huayu.web.platform.bo;

import java.util.List;

public class DataResourceTable {
	private String tableName;
	private String tableComment;

	private List<DataResourceColumn> columns  ;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<DataResourceColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DataResourceColumn> columns) {
		this.columns = columns;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
}
