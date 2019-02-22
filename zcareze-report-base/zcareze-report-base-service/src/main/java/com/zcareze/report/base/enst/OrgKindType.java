/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.enst;

/**
 * 
 * @Filename CycKindType.java
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
 *          <li>Date: 2017年5月15日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public enum OrgKindType {

	ORG_OGR("H","机构"),
	
	ORG_OTHER("O","其他机构"),
	
	ORG_DEPART("D","科室或部门"),
	
	ORG_TEAM("T","医生团队");

	private String code;
	
	private String name;

	private OrgKindType(String code,String name) {
		this.name = name;
		this.setCode(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static OrgKindType parse(String code) {
		switch (code) {
			case "H" :
				return OrgKindType.ORG_OGR;
			case "D" :
				return OrgKindType.ORG_DEPART;
			case "T" :
				return OrgKindType.ORG_TEAM;
			case "O":
				return OrgKindType.ORG_OTHER;
			default :
				return null;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}