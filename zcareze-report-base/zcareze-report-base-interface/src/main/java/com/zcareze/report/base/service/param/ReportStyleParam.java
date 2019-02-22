/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.service.param;

import java.io.Serializable;

/**
 *                       
 * @Filename ReportStyleParam.java
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
 * <li>Author: 虾米</li>
 * <li>Date: 2017年5月9日</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 *
 */
public class ReportStyleParam implements Serializable {
		
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 3603055892888861160L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 场景
	 */
	private Integer scene;
	/**
	 * 显示文件XSLT的内容
	 */
	private String fileContent;
	
	private String fileUrl;
	/**
	 * 图表设置
	 */
	private String chart;
	
	/**
	 * 说明该格式文件应用场景区分识别等描述
	 */
	private String comment;
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
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
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
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getChart() {
		return chart;
	}
	public void setChart(String chart) {
		this.chart = chart;
	}	
}
