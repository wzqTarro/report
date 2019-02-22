/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.data.service.param;

import java.io.Serializable;
import java.util.List;

import com.zcareze.report.data.service.vo.ReportParamValue;

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
public class ReportViewParam implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1535484945787478042L;

	private String reportId;	

	private List<ReportParamValue> paramValues;	

	public ReportViewParam() {
	}	

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ReportParamValue> getParamValues() {
		return paramValues;
	}

	public void setParamValues(List<ReportParamValue> paramValues) {
		this.paramValues = paramValues;
	}	
}
