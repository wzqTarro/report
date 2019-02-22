/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Filename reportUsually.java
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
 *          <li>Date: 2017年5月15日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportUsually implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -2430832972372446394L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 职员Id
	 */
	private String staffId;
	/**
	 * 排列号
	 */
	private Integer seqNum;
	/**
	 * 查看时间 上次查看该报表的时间，根据报表的期间参数，可提示是否该查阅
	 */
	private Date readTime;
	/**
	 * 报表名称
	 */
	private String reportName;
	/**
	 * 报表分组名称
	 */
	private String groupName;
	/**
	 * 界面显示颜色
	 */
	private String color;
	/**
	 * 是否个人常用报表
	 */
	private Boolean usually;

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getUsually() {
		return usually;
	}

	public void setUsually(Boolean usually) {
		this.usually = usually;
	}
}
