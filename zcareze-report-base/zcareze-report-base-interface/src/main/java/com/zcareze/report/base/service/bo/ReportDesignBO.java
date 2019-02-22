package com.zcareze.report.base.service.bo;

import java.util.Date;
import java.util.List;

import com.zcareze.commons.IdStrEntity;

/**
 * 报表信息
 * -用于报表服务 包括报表名称,报表数据表,报表数据表中包含参数列表
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
public class ReportDesignBO extends IdStrEntity {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -5488291662638677745L;

	private String code;
	private String name;
	private List<ReportTableDesignBO> reportTables;
	private Date editTime;
	/**
	 * 最后修改时间(时间戳)
	 */
	private Long updateTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ReportTableDesignBO> getReportTables() {
		return reportTables;
	}

	public void setReportTables(List<ReportTableDesignBO> reportTables) {
		this.reportTables = reportTables;
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

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}