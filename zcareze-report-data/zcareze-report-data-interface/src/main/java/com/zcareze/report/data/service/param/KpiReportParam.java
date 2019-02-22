/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.data.service.param;

import java.io.Serializable;

/**
 * 
 * @Filename ReportViewParam.java
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
public class KpiReportParam implements Serializable {
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -3990924159043094589L;
	/**
	 * 指标Id
	 */
	private String kpiId;
	/**
	 * 报表Id
	 */ 
	private String reportId;
	/**
	 * 组织Id
	 */
	private String orgId;
	/**
	 * 周期值
	 */
	private String cycValue;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCycValue() {
		return cycValue;
	}

	public void setCycValue(String cycValue) {
		this.cycValue = cycValue;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
}
