package com.zcareze.report.common;

import java.io.Serializable;

import com.zcareze.report.common.enst.ParamDataType;

public class ReportParameterValue implements Serializable {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 5334061540316018043L;
	private String code;
	private String name;
	private ParamDataType dataType;
	private Object value;
	private String title;
	private Integer seqNO;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSeqNO() {
		return seqNO;
	}

	public void setSeqNO(Integer seqNO) {
		this.seqNO = seqNO;
	}

	public ParamDataType getDataType() {
		return dataType;
	}

	public void setDataType(ParamDataType dataType) {
		this.dataType = dataType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
