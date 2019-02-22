/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.data.service.vo;

import java.io.Serializable;

/**
 * 动态查询参数查询得到的参数值
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
public class QueryInputValueVO implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -6185342557345119206L;
	/**
	 * 参数值
	 */
	private String colValue;
	/**
	 * 显示值
	 */
	private String colTitle;

	public QueryInputValueVO() {
	}

	public QueryInputValueVO(String colValue, String colTitle) {
		this.colTitle = colTitle;
		this.colValue = colValue;
	}

	public String getColValue() {
		return colValue;
	}

	public void setColValue(String colValue) {
		this.colValue = colValue;
	}

	public String getColTitle() {
		return colTitle;
	}

	public void setColTitle(String colTitle) {
		this.colTitle = colTitle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
