/**
 * zcareze Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.zcareze.report.data.service;

import com.zcareze.report.data.service.param.KpiReportParam;
import com.zcareze.report.data.service.param.ReportViewParam;
import com.zcareze.report.data.service.param.ScreenReportParam;
import com.zcareze.report.data.service.result.QueryInputValueResult;
import com.zcareze.report.data.service.result.ReportDataResult;

/**
 * 健康文档基础服务
 * 
 * @Filename BaseHdocService.java
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
 *
 */
public interface DataReportService {
	

	/**
	 * 获取动态查询参数的参数值信息
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月3日 下午4:21:32
	 */
	public QueryInputValueResult getReportQueryInputValue(String reportId, String name);
	
	/**
	 * 获取指定报表的数据
	 * @param reportParam
	 * @return
	 * <p>说明：</p>
	 * @author 虾米  by 2017年4月10日 下午9:14:37
	 */
	public ReportDataResult getReportData(ReportViewParam reportParam);
	

	/**
	 * 获取指定大屏的所有数据表的结构
	 * @param reportParam
	 * @return
	 * <p>说明：</p>
	 * @author 虾米 by 2017年11月9日 下午5:54:28
	 */
	public ReportDataResult getScreenReportStruct(String reportId);	
	
	/**
	 * 获取指定大屏的指定数据表的数据
	 * @param reportParam
	 * @return
	 * <p>说明：</p>
	 * @author 虾米 by 2017年11月9日 下午5:54:28
	 */
	public ReportDataResult getScreenReportData(ScreenReportParam reportParam);
	
	/**
	 * 获取指定报表的数据(用于指标对应报表查看)
	 * @param kpiReportParam
	 * @return
	 * <p>说明：</p>
	 * @author 虾米  by 2017年5月24日 上午11:55:23
	 */
	public ReportDataResult getKpiReportData(KpiReportParam kpiReportParam);
}
