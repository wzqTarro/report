/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.service.enst;

/**
 * 报表系统参数
 * 
 * @Filename ReportSystemParameter.java
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
 *          <li>Date: 2017年5月3日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public enum ReportSystemParameter {

	CURRENT_USER("my_staff_id", "我的员工Id"),

	CURRENT_ORG("my_org_id", "我的机构Id"),

	CURRENT_DEPART("my_dept_id", "我的科室Id"),

	CURRENT_TEAM("my_team_id", "我的团队Id");

	private String name;

	private String title;

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

	private ReportSystemParameter(String name, String title) {
		this.name = name;
		this.title = title;
	}

	public static ReportSystemParameter parse(String name) {
		switch (name) {
			case "my_staff_id" :
				return ReportSystemParameter.CURRENT_USER;
			case "my_org_id" :
				return ReportSystemParameter.CURRENT_ORG;
			case "my_dept_id" :
				return ReportSystemParameter.CURRENT_DEPART;
			case "my_team_id" :
				return ReportSystemParameter.CURRENT_TEAM;
			default :
				return null;
		}
	}
}
