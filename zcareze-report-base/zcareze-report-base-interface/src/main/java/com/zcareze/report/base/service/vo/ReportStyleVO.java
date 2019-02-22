/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.service.vo;

import java.io.Serializable;

/**
 * 报表样式
 * @Filename ReportStyle.java
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
 *          <li>Date: 2017年5月2日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportStyleVO implements Serializable {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 4736392557912868608L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 场景
	 */
	private Integer scene;
	/**
	 * 显示文件XSLT的存放路径
	 */
	private String fileUrl;
	
	/**
	 * 可访问的url地址
	 */
	private String visitUrl;
	
	/**
	 * 说明该格式文件应用场景区分识别等描述
	 */
	private String comment;
	
	/**
	 * 图表设置信息
	 */
	private String chart;

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	public String getVisitUrl() {
		return visitUrl;
	}

	public void setVisitUrl(String visitUrl) {
		this.visitUrl = visitUrl;
	}
}
