/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.data.util;

import com.zcareze.commons.cache.ApplicationCacheUtils;
import com.zcareze.report.base.service.enst.ReportSystemParameter;
import com.zcareze.report.data.service.vo.ReportParamValue;

/**
 * 报表系统参数
 * 
 * @Filename ReportSystemParameter.java
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
public class SystemParameterValueUtils {

	public static ReportParamValue getValue(String paramName) {
		ReportParamValue paramValue = new ReportParamValue();
		paramValue.setName(paramName);
		ReportSystemParameter systemParam = ReportSystemParameter.parse(paramName);
		if (systemParam == null) {
			return null;
		}
		switch (systemParam) {
			case CURRENT_DEPART :
				paramValue.setTitle(ApplicationCacheUtils.getDefaultDepartName());
				paramValue.setValue(ApplicationCacheUtils.getDefaultDepart());
				break;
			case CURRENT_ORG :
				paramValue.setTitle(ApplicationCacheUtils.getDefaultOrgName());
				paramValue.setValue(ApplicationCacheUtils.getDefaultOrg());
				break;
			case CURRENT_TEAM :
				paramValue.setTitle(ApplicationCacheUtils.getDefaultTeamName());
				paramValue.setValue(ApplicationCacheUtils.getDefaultTeam());
				break;
			case CURRENT_USER :
				paramValue.setTitle(ApplicationCacheUtils.getAccountName());
				paramValue.setValue(ApplicationCacheUtils.getStaffId());
				break;
		}
		return paramValue;
	}
}
