package com.zcareze.report.base.domain;

import java.util.Date;

import com.zcareze.commons.IdStrEntity;

public class ReportList extends IdStrEntity {
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -2785000302336746350L;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 分组编码
	 */
	private String grpCode;
	/**
	 * 执行方式 1-先报表：首先按参数默认值计算报表显示，必要时用户重置参数查询；2-先参数：首先显示参数输入界面，确定后计算查看报表
	 */
	private Integer runway;
	/**
	 * 修改时间
	 */
	private Date editTime;
	/**
	 * 修改人
	 */
	private String editorId;
	/**
	 * 修改人名称
	 */
	private String editorName;
	/**
	 * 备注
	 */
	private String comment;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrpCode() {
		return grpCode;
	}

	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}

	public Integer getRunway() {
		return runway;
	}

	public void setRunway(Integer runway) {
		this.runway = runway;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getEditorId() {
		return editorId;
	}

	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
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
}