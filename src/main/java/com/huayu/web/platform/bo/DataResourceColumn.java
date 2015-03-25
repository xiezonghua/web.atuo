package com.huayu.web.platform.bo;

import org.apache.commons.lang3.StringUtils;

import com.huayu.web.platform.utils.string.CamelCaseUtils;

public class DataResourceColumn {
	/**
	 * 列名
	 */
	private String columnName;

	/**
	 * 驼峰
	 */
	private String columnNameCamel;
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

	public String getColumnNameCamel() {
		if(StringUtils.isEmpty(columnNameCamel) && StringUtils.isNotEmpty(columnName)){
			columnNameCamel = CamelCaseUtils.toCamelCase(columnName);
		}
		return columnNameCamel;
	}

	public void setColumnNameCamel(String columnNameCamel) {
		this.columnNameCamel = columnNameCamel;
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
