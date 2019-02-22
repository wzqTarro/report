/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.domain;

import java.io.Serializable;

/**
 * 有管理权限的指标
 * 
 * @Filename KpiList.java
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
 *          <li>Date: 2017年5月9日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportGrantToManage implements Serializable {
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -6455761244929136226L;

	/**
	 * 报表Id
	 */
	private String rptId;

	/**
	 * 报表Id
	 */
	private String rptName;
	/**
	 * 分组Code
	 */
	private String groupCode;

	/**
	 * 机构Id
	 */
	private String orgId;
	/**
	 * 授予角色 01-健康助理;02-基层医生;03-专科医生;04-医学专家;05-专业技师;11-业务管理
	 */
	private String roles;
	/**
	 * 授权包含下级
	 */
	private Integer manage;

	/**
	 * 授权组织Id上级Id
	 */
	private String ParentOrgId;
	/**
	 * 组织码
	 */
	private String orgCode;

	/**
	 * 查詢用的orgId
	 */
	private String SearchOrgId;
	/**
	 * 授权组织名称
	 */
	private String orgName;	

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getRptName() {
		return rptName;
	}

	public void setRptName(String rptName) {
		this.rptName = rptName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Integer getManage() {
		return manage;
	}

	public void setManage(Integer manage) {
		this.manage = manage;
	}

	public String getParentOrgId() {
		return ParentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		ParentOrgId = parentOrgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSearchOrgId() {
		return SearchOrgId;
	}

	public void setSearchOrgId(String searchOrgId) {
		SearchOrgId = searchOrgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
