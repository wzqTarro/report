/**
 * Zcareze Inc.
 * Copyright (c) 2018 All Rights Reserved.
 */
package com.zcareze.report.base.domain;

import java.io.Serializable;
import java.util.Date;

import com.zcareze.commons.method.annotation.ParamentValidate;
import com.zcareze.commons.method.annotation.ValidateRules;

/**
 * 
 * @Filename ReportOpenTo.java
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
 *          <li>Date: 2018年5月8日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportOpenTo implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 2962183952204465458L;

	public String id;

	/**
	 * 报表ID
	 */
	public String rptId;
	/**
	 * 组织树ID
	 */
	private String orgTreeId;
	
	/**
	 * 组织树名称
	 */
	private String orgTreeName;
	
	/**
	 * 组织树层
	 */
	@ParamentValidate(name = "组织树层次", rules = {ValidateRules.NOT_NULL})
	public Integer orgTreeLayer;
	/**
	 * 角色
	 */
	@ParamentValidate(name = "角色", rules = {ValidateRules.NOT_NULL})
	public String roles;
	/**
	 * 授权者
	 */
	public String granter;
	/**
	 * 授权时间
	 */
	public Date grantTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public Integer getOrgTreeLayer() {
		return orgTreeLayer;
	}

	public void setOrgTreeLayer(Integer orgTreeLayer) {
		this.orgTreeLayer = orgTreeLayer;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getGranter() {
		return granter;
	}

	public void setGranter(String granter) {
		this.granter = granter;
	}

	public Date getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(Date grantTime) {
		this.grantTime = grantTime;
	}

	public String getOrgTreeId() {
		return orgTreeId;
	}

	public void setOrgTreeId(String orgTreeId) {
		this.orgTreeId = orgTreeId;
	}

	public String getOrgTreeName() {
		return orgTreeName;
	}

	public void setOrgTreeName(String orgTreeName) {
		this.orgTreeName = orgTreeName;
	}
}
