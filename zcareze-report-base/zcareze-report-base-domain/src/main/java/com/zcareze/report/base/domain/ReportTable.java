/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.domain;

import java.io.Serializable;

/**
 * 报表数据表
 * 
 * @Filename ReportTable.java
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
public class ReportTable implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -1707962177387863306L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 查询语句
	 */
	private String sqlText;
	/**
	 * 顺序号
	 */
	private Integer seqNum;

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
