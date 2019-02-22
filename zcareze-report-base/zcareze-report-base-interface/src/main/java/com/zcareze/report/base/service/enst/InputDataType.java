package com.zcareze.report.base.service.enst;

/**
 * 
 * 
 * @Filename QueryInputDefType.java
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
 *          <li>Date: 2017年5月8日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public enum InputDataType {

	TYPE_TXT("11", "文本"),

	TYPE_NUM("12", "数字"),

	TYPE_YEAR("21", "年度"),

	TYPE_MONTH("22", "月份"),

	TYPE_DAY("23", "日期"),

	TYPE_ORG("31", "机构"),

	TYPE_DEPAMENT("32", "科室"),

	TYPE_TEAM("33", "医生团队");

	private String name;

	private String title;

	private InputDataType(String name, String title) {
		this.name = name;
		this.setTitle(title);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static InputDataType parse(String name) {
		switch (name) {
			case "11" :
				return InputDataType.TYPE_TXT;
			case "12" :
				return InputDataType.TYPE_NUM;
			case "21" :
				return InputDataType.TYPE_YEAR;
			case "22" :
				return InputDataType.TYPE_MONTH;
			case "23" :
				return InputDataType.TYPE_DAY;
			case "31" :
				return InputDataType.TYPE_ORG;
			case "32" :
				return InputDataType.TYPE_DEPAMENT;
			case "33" :
				return InputDataType.TYPE_TEAM;
			default :
				return null;
		}
	}
}
