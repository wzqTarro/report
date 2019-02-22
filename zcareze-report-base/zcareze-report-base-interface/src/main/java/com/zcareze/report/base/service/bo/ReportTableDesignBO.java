package com.zcareze.report.base.service.bo;

import java.io.Serializable;
import java.util.List;

import com.zcareze.report.base.service.vo.ReportInputVO;

/**
 * 报表数据表信息
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
public class ReportTableDesignBO implements Serializable {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -1197674781891370435L;
	
	private String name;
	private String sqlText;
	private Integer seqNum;	
	private List<ReportInputVO> reportInputs;	
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	public List<ReportInputVO> getReportInputs() {
		return reportInputs;
	}

	public void setReportInputs(List<ReportInputVO> reportInputs) {
		this.reportInputs = reportInputs;
	}
}