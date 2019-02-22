/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.base.domain;

import java.io.Serializable;

/**
 * 报表输入参数
 * 
 * @Filename ReportInput.java
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
public class ReportInput implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 638136766420663428L;
	/**
	 * 报表Id
	 */
	private String rptId;
	/**
	 * 参数名称
	 */
	private String name;
	/**
	 * 参数标签
	 */
	private String caption;
	/**
	 * 顺序号
	 */
	private Integer seqNum;
	/**
	 * 数据类型
	 * 11-文本，12-数字；21-年度(YYYY)，22-月份(YYYYMM)，23-日期(YYYYMMDD)；31-机构，32-科室，33-医生团队，该类输入类型只能是动态查询列表
	 */
	private String dataType;
	/**
	 * 输入类型 0-依赖外部调用程序直接传入；1-标准控件输入；2-固定列表选择；3-动态查询列表
	 */
	private Integer inputType;
	/**
	 * 选项列表,固定列表选择时，以分号分隔的选项列表
	 */
	private String optionList;
	/**
	 * 查询语句
	 */
	private String sqlText;
	/**
	 * 默认值文本
	 */
	private String defValue;
	/**
	 * 默认值类型
	 */
	private String defType;

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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getInputType() {
		return inputType;
	}

	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}

	public String getOptionList() {
		return optionList;
	}

	public void setOptionList(String optionList) {
		this.optionList = optionList;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getDefType() {
		return defType;
	}

	public void setDefType(String defType) {
		this.defType = defType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
