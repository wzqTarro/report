package com.zcareze.report.base.service.vo;

import com.zcareze.commons.IdStrEntity;

/**
 * 
 * 
 * @Filename ReportParameterVO.java
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
public class ViewParameterVO extends IdStrEntity {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -8939206995652313711L;
	/**
	 * 参数名
	 */
	private String name;
	/**
	 * 参数显示名
	 */
	private String caption;
	/**
	 * 数据类型 11-文本，12-数字；21-年度(YYYY)，22-月份(YYYYMM)，23-日期(YYYYMMDD)；31-机构，32-科室，33-医生团队
	 */
	private String dataType;
	/**
	 * 输入类型 0-依赖外部调用程序直接传入；1-标准控件输入；2-固定列表选择；3-动态查询列表
	 */
	private Integer inputType;
	/**
	 * 数据源 固定列表选择时为选项列表,用;隔开 动态查询列表时为数据字符串,其中包含col_value,col_title两列
	 */
	private String optionList;
	/**
	 * 默认值
	 */
	private String defValue;
	/**
	 * 默认值类型
	 */
	private String defType;
	/**
	 * 顺序号
	 */
	private Integer seqNum;

	public ViewParameterVO() {
	}

	public ViewParameterVO(String name, String caption, String dataType, String defValue, Integer seqNum,Integer inputType,String optionList,String defType) {
		setCaption(caption);
		setName(name);
		setDataType(dataType);
		setDefValue(defValue);
		setSeqNum(seqNum);
		setInputType(inputType);
		setOptionList(optionList);
		setDefType(defType);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getOptionList() {
		return optionList;
	}

	public void setOptionList(String optionList) {
		this.optionList = optionList;
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
}