/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.service.bo;

import java.io.Serializable;
import java.util.List;

import com.zcareze.report.base.service.vo.ReportInputVO;

/**
 * 动态查询参数设计信息
 * 
 * @Filename QueryInputValueVO.java
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
public class QueryInputDesignBO implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -7414005858597353957L;

	private ReportInputVO reportInput;

	private List<String> parameters;

	public ReportInputVO getReportInput() {
		return reportInput;
	}

	public void setReportInput(ReportInputVO reportInput) {
		this.reportInput = reportInput;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
