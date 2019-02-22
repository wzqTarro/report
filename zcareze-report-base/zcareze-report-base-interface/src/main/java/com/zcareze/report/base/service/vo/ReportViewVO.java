package com.zcareze.report.base.service.vo;

import java.util.Date;
import java.util.List;

import com.zcareze.commons.IdStrEntity;

/**
 * 报表信息-用于前端界面 包括报表名称,样式信息(xslt),需要输入的参数列表(参数名,类型)
 * 
 * @Filename ReportViewVO.java
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
 *          <li>Date: 2017年4月11日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportViewVO extends IdStrEntity {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -7788713664324983095L;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 报表名
	 */
	private String name;
	/**
	 * 报表分组码
	 */
	private String grpCode;
	/**
	 * xslt文件存储地址
	 */
	private String fileUrl;
	/**
	 * 图表设置
	 */
	private String chart;
	
	/**
	 * 最后修改时间(时间格式)
	 */
	private Date editTime;
	/**
	 * 最后修改时间(时间戳)
	 */
	private Long updateTime;
	/**
	 * 执行方式 1-先报表：首先按参数默认值计算报表显示，必要时用户重置参数查询； 2-先参数：首先显示参数输入界面，确定后计算查看报表。
	 */
	private Integer runway;
	/**
	 * 输入参数
	 */
	private List<ViewParameterVO> inputParams;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getRunway() {
		return runway;
	}

	public void setRunway(Integer runway) {
		this.runway = runway;
	}

	public List<ViewParameterVO> getInputParams() {
		return inputParams;
	}

	public void setInputParams(List<ViewParameterVO> inputParams) {
		this.inputParams = inputParams;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGrpCode() {
		return grpCode;
	}

	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}
}