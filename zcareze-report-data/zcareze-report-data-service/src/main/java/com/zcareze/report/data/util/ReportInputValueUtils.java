/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.data.util;

import java.util.Calendar;
import java.util.Date;

import com.zcareze.commons.cache.ApplicationCacheUtils;
import com.zcareze.commons.utils.DateUtil;
import com.zcareze.commons.utils.StringUtils;
import com.zcareze.report.base.service.enst.QueryInputDefType;
import com.zcareze.report.base.service.vo.ReportInputVO;
import com.zcareze.report.data.service.vo.ReportParamValue;

/**
 * 
 * @Filename ParamValueUtils.java
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
 *          <li>Date: 2017年4月12日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportInputValueUtils {

	public static ReportParamValue getValue(ReportInputVO reportParam) {
		if (reportParam.isSystem()) {
			return SystemParameterValueUtils.getValue(reportParam.getName());
		}
		String defType = reportParam.getDefType();
		QueryInputDefType querydefType = QueryInputDefType.parse(defType);
		if (querydefType != null) {
			return getValue(reportParam.getName(), reportParam.getDefValue(), querydefType);
		} else {
			ReportParamValue paramValue = new ReportParamValue();
			paramValue.setName(reportParam.getName());
			paramValue.setTitle(reportParam.getDefValue());
			paramValue.setValue(reportParam.getDefValue());
			return paramValue;
		}
	}

	/**
	 * 得到指定的默认值
	 * 
	 * @param defType
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月8日 下午12:05:33
	 */
	private static ReportParamValue getValue(String name, String defValue, QueryInputDefType defType) {
		ReportParamValue paramValue = new ReportParamValue();
		paramValue.setName(name);

		Calendar cal = Calendar.getInstance();
		switch (defType) {
			case ORG_ALL :
				paramValue.setTitle("所有机构");
				paramValue.setValue("");
				break;
			case ORG_MY :
				paramValue.setTitle(ApplicationCacheUtils.getDefaultOrgName());
				paramValue.setValue(ApplicationCacheUtils.getDefaultOrg());
				break;
			case DEPAMENT_ALL :
				paramValue.setTitle("所有科室");
				paramValue.setValue("");
				break;
			case DEPAMENT_MY :
				paramValue.setTitle(ApplicationCacheUtils.getDefaultDepartName());
				paramValue.setValue(ApplicationCacheUtils.getDefaultDepart());
				break;
			case TEAM_ALL :
				paramValue.setTitle("所有团队");
				paramValue.setValue("");
				break;
			case TEAM_MY :
				paramValue.setTitle(ApplicationCacheUtils.getDefaultTeamName());
				paramValue.setValue(ApplicationCacheUtils.getDefaultTeam());
				break;
			case YEAR_LAST :
				cal.add(Calendar.YEAR, -1);
				paramValue.setValue(DateUtil.convertDateToString("yyyy", cal.getTime()));
				paramValue.setTitle(DateUtil.convertDateToString("yyyy", cal.getTime()));
				break;
			case YEAR_NOW :
				paramValue.setTitle(DateUtil.convertDateToString("yyyy", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyy", cal.getTime()));
				break;
			case MONTH_LAST :
				cal.add(Calendar.MONTH, -1);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMM", cal.getTime()));
				break;
			case MONTH_NEXT :
				cal.add(Calendar.MONTH, 1);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMM", cal.getTime()));
				break;
			case MONTH_NOW :
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMM", cal.getTime()));
				break;
			case DATE_LAST :
				cal.add(Calendar.DAY_OF_YEAR, -1);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			case DATE_NEXT :
				cal.add(Calendar.DAY_OF_YEAR, 1);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			case DATE_LAST_7 :
				cal.add(Calendar.DAY_OF_YEAR, -7);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			case DATE_LAST_MONTH :
				cal.add(Calendar.MONTH, -1);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			case DATE_MONTH_FIRST :
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-01", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMM01", cal.getTime()));
				break;
			case DATE_MONTH_LAST :				
				cal.set(Calendar.DAY_OF_MONTH, 1);				
				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			case DATE_NEXT_7 :
				cal.add(Calendar.DAY_OF_YEAR, 7);
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			case DATE_NOW :
				paramValue.setTitle(DateUtil.convertDateToString("yyyy-MM-dd", cal.getTime()));
				paramValue.setValue(DateUtil.convertDateToString("yyyyMMdd", cal.getTime()));
				break;
			default :
				if (StringUtils.isNotEmpty(defValue)) {
					paramValue.setValue(defValue);
					paramValue.setTitle(defValue);
				} else {
					paramValue.setValue("");
					paramValue.setTitle("");
				}
				break;
		}
		return paramValue;
	}
}