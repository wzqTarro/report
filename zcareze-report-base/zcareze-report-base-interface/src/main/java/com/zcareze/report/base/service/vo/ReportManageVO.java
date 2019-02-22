package com.zcareze.report.base.service.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 有管理权限的报表(包括已经授予的权限)
 * 
 * @Filename ReportListVO.java
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
 *          <li>Date: 2017年4月10日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportManageVO implements Serializable {
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -8832962018313379273L;

	/**
	 * 报表Id
	 */
	private String reportId;
	/**
	 * 报表名称
	 */
	private String reportName;
	/**
	 * 报表分组名称
	 */
	private String groupName;
	/**
	 * 機構Id
	 */
	private String orgId;
	/**
	 * 機構名稱
	 */
	private String orgName;	
	/**
	 * 已授权列表
	 */
	private List<ReportGrantToVO> grantList;
	
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<ReportGrantToVO> getGrantList() {
		return grantList;
	}

	public void setGrantList(List<ReportGrantToVO> grantList) {
		this.grantList = grantList;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}	
}