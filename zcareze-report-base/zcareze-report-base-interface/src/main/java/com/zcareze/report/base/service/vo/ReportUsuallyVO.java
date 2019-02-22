package com.zcareze.report.base.service.vo;

import java.io.Serializable;

/**
 * 
 * 报表基本信息 (用于界面分析首页和报表中心)
 * 
 * @Filename ReportListVO.java
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
public class ReportUsuallyVO implements Serializable {
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -8832962018313379273L;

	/**
	 * 报表Id
	 */
	private String reportId;
	/**
	 * 报表名称
	 */
	private String reportName;
	/**
	 * 报表分组编码
	 */
	private String groupCode;
	/**
	 * 报表分组名称
	 */
	private String groupName;
	/**
	 * 界面显示颜色
	 */
	private String color;
	/**
	 * 顺序号
	 */
	private Integer seqNum;
	/**
	 * 是否个人常用报表
	 */
	private Boolean usually;
	/**
	 * 是否需要查阅
	 */
	private Boolean noRead;	
	/**
	 * 最后修改时间(时间戳)
	 */
	private Long updateTime;
	/**
	 * 授权状态
	 * 0:有授权;1:无授权（授权被取消）
	 */
	private Integer grantTo;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
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

	public Boolean getNoRead() {
		return noRead;
	}

	public void setNoRead(Boolean noRead) {
		this.noRead = noRead;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getGrantTo() {
		return grantTo;
	}

	public void setGrantTo(Integer grantTo) {
		this.grantTo = grantTo;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}