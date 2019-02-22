/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.common.enst;

/**
 * 
 * @Filename ParamDataType.java
 *
 * @Description
 *
 * @Version 1.0
 *
 * @Author 虾米
 *
 * @Email xiazongyan@zcareze.com
 * 
 * @History
 *          <li>Author: 虾米</li>
 *          <li>Date: 2017年4月12日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public enum ParamDataType {

	TYPE_STRING(11, "字符串"),

	TYPE_NUMBER(12, "数字"),

	TYPE_DATE_YEAR(21, "年度"),

	TYPE_DATE_MONTH(22, "月份"),

	TYPE_DATE_DAY(23, "日期"),

	TYPE_ORGANIZE(31, "机构"),

	TYPE_DEPARTMENT(32, "科室"),

	TYPE_DOCTOR(33, "医生团队");

	private Integer code;

	private String name;

	private ParamDataType(Integer code, String name) {
		this.code = code;
		this.setName(name);
	}

	/**
	 * 根据code得到枚举
	 * @param code
	 * @return
	 * <p>说明：</p>
	 * @author 虾米  by 2017年5月3日 上午11:41:51
	 */
	public static ParamDataType parse(String dataType) {
		switch (dataType) {
			case "11" :
				return ParamDataType.TYPE_STRING;
			case "12" :
				return ParamDataType.TYPE_NUMBER;
			case "21" :
				return ParamDataType.TYPE_DATE_YEAR;
			case "22" :
				return ParamDataType.TYPE_DATE_MONTH;
			case "23" :
				return ParamDataType.TYPE_DATE_DAY;
			case "31" :
				return ParamDataType.TYPE_ORGANIZE;
			case "32" :
				return ParamDataType.TYPE_DEPARTMENT;
			case "33" :
				return ParamDataType.TYPE_DOCTOR;
			default :
				return ParamDataType.TYPE_STRING;
		}
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
