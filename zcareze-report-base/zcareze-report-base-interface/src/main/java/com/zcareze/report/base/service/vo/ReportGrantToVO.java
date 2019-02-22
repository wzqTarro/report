/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.service.vo;

import java.io.Serializable;

/**
 * 报表授权
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
public class ReportGrantToVO implements Serializable {
		
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -4967189296824339646L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 机构Id
	 */
	private String orgId;
	
	/**
	 * 机构名称(包括上级机构名称)
	 */
	private String orgName;
	
	/**
	 * 授予角色 01-健康助理;02-基层医生;03-专科医生;04-医学专家;05-专业技师;11-业务管理
	 */
	private String roles;
	/**
	 * 授权包含下级
	 */
	private Integer manage;	

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
