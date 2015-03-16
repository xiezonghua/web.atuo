package com.huayu.web.platform.bo;

public class DataResourceColumn {
	/**
	 * 列名
	 */
	private String columnName;

	/**
	 * 驼峰
	 */
	private String columnCamcelName;
	/**
	 * 列默认值
	 */
	private String columnDefault;

	/**
	 * 列类型
	 */
	private String dataType;

	/**
	 * 列备注
	 */
	private String columnComment;

	/**
	 * 列索引类型
	 */
	private String columnKey;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnCamcelName() {
		return columnCamcelName;
	}

	public void setColumnCamcelName(String columnCamcelName) {
		this.columnCamcelName = columnCamcelName;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

}
