/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 报表授权
 * 
 * @Filename ReportGrantTo.java
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
public class ReportGrantTo implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -8250647509917283347L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 机构Id
	 */
	private String orgId;
	/**
	 * 授予角色 01-健康助理;02-基层医生;03-专科医生;04-医学专家;05-专业技师;11-业务管理
	 */
	private String roles;
	/**
	 * 人员对于角色 (只是内部使用)
	 */
	private String classes;
	/**
	 * 授权包含下级
	 */
	private Integer manage;
	/**
	 * 授权者
	 */
	private String granter;
	/**
	 * 授权时间
	 */
	private Date grantTime;

	public ReportGrantTo() {

	}

	public ReportGrantTo(String rptId, String orgId, String roles, Integer manage, String granter, Date grantTime) {
		setGranter(granter);
		setGrantTime(grantTime);
		setManage(manage);
		setOrgId(orgId);
		setRoles(roles);
		setRptId(rptId);
	}

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
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

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
}
