package com.zcareze.report.base.service.result;

import java.util.List;

import com.zcareze.commons.result.Result;

public class ReportCopyResult extends Result{

	/**
	 * 同步出错的区域Id
	 */
	public List<String> errorCloudIds;

	public List<String> getErrorCloudIds() {
		return errorCloudIds;
	}

	public void setErrorCloudIds(List<String> errorCloudIds) {
		this.errorCloudIds = errorCloudIds;
	}		
}
