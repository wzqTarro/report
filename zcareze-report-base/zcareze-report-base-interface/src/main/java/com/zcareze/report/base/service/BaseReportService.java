/**
 * zcareze Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.zcareze.report.base.service;

import java.util.Date;
import java.util.List;

import com.zcareze.commons.method.annotation.ParamentValidate;
import com.zcareze.commons.method.annotation.ValidateRules;
import com.zcareze.commons.result.BaseResult;
import com.zcareze.commons.result.Result;
import com.zcareze.report.base.domain.ReportOpenTo;
import com.zcareze.report.base.service.param.ReportStyleParam;
import com.zcareze.report.base.service.result.QueryInputDesignResult;
import com.zcareze.report.base.service.result.ReportCopyResult;
import com.zcareze.report.base.service.result.ReportDesignResult;
import com.zcareze.report.base.service.result.ReportGrantToResult;
import com.zcareze.report.base.service.result.ReportGroupResult;
import com.zcareze.report.base.service.result.ReportInputResult;
import com.zcareze.report.base.service.result.ReportListResult;
import com.zcareze.report.base.service.result.ReportManageResult;
import com.zcareze.report.base.service.result.ReportStyleResult;
import com.zcareze.report.base.service.result.ReportTableResult;
import com.zcareze.report.base.service.result.ReportUsuallyResult;
import com.zcareze.report.base.service.result.ReportViewResult;
import com.zcareze.report.base.service.vo.ReportGrantToVO;
import com.zcareze.report.base.service.vo.ReportGroupVO;
import com.zcareze.report.base.service.vo.ReportInputVO;
import com.zcareze.report.base.service.vo.ReportListVO;
import com.zcareze.report.base.service.vo.ReportTableVO;

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
public interface BaseReportService {

	/**
	 * 新增报表分组
	 * 
	 * @param reportGroup
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:36:34
	 */
	public Result addReportGroup(ReportGroupVO reportGroup);

	/**
	 * 修改指定的报表分组
	 * 
	 * @param reportGroup
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:36:42
	 */
	public Result editReportGroup(ReportGroupVO reportGroup);

	/**
	 * 删除指定的报表分组
	 * 
	 * @param code
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:36:58
	 */
	public Result deleteReportGroup(String code);

	/**
	 * 获取指定编码的报表分组信息
	 * 
	 * @param code
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:37:07
	 */
	public ReportGroupResult getReportGroupByCode(String code);

	/**
	 * 获取报表分组列表
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:37:20
	 */
	public ReportGroupResult getReportGroupList(Integer pageNow, Integer pageSize);

	/**
	 * 新增报表
	 * 
	 * @param reportList
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:56:18
	 */
	public Result addReportList(ReportListVO reportList);

	/**
	 * 修改指定报表
	 * 
	 * @param reportList
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:56:28
	 */
	public Result editReportList(ReportListVO reportList);

	/**
	 * 删除指定的报表
	 * 
	 * @param id
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:56:47
	 */
	public Result deleteReportList(String id);

	/**
	 * 根据Id获取指定报表信息
	 * 
	 * @param id
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月2日 上午11:17:35
	 */
	public ReportListResult getReportListById(String id);

	/**
	 * 获取报表清单
	 * 
	 * @param name
	 *            报表名称
	 * @param groupCode
	 *            报表分组编码
	 * @param pageNow
	 *            当前页
	 * @param pageSize
	 *            每页条数
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午2:58:30
	 */
	public ReportListResult getReportList(String name, String groupCode, Integer pageNow, Integer pageSize);

	/**
	 * 获取监控大屏报表清单
	 * @param name
	 * @return
	 * <p>说明：</p>
	 * @author 虾米 by 2018年6月7日 下午12:24:44
	 */
	public ReportListResult getScreenReportList(String name);	
	
	/**
	 * 获取指定报表最后修改时间
	 * 
	 * @param id
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 上午10:53:01
	 */
	public BaseResult<Date> getReportListUpdateTime(String id);

	/**
	 * 新增报表输入参数
	 * 
	 * @param reportInput
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:20:29
	 */
	public Result addReportInput(ReportInputVO reportInput);

	/**
	 * 修改指定的报表输入参数
	 * 
	 * @param reportInput
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:20:37
	 */
	public Result editReportInput(ReportInputVO reportInput);

	/**
	 * 删除指定的报表输入参数
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:20:48
	 */
	public Result deleteReportInput(String reportId, String name);

	/**
	 * 获取指定的报表输入参数信息
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:21:05
	 */
	public ReportInputResult getReportInput(String reportId, String name);

	/**
	 * 获取动态查询参数的设计信息
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月6日 下午3:33:22
	 */
	public QueryInputDesignResult getQueryReportInput(String reportId, String name);

	/**
	 * 获取指定报表的所有输入参数
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:21:21
	 */
	public ReportInputResult getPeportInputsByRptId(String reportId);

	/**
	 * 新增报表数据表
	 * 
	 * @param reportTable
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:21:40
	 */
	public Result addReportTable(ReportTableVO reportTable);

	/**
	 * 修改指定的报表数据表
	 * 
	 * @param reportTable
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:21:48
	 */
	public Result editReportTable(ReportTableVO reportTable);

	/**
	 * 删除指定的报表数据表
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:21:57
	 */
	public Result deleteReportTable(String reportId, String name);

	/**
	 * 获取指定报表的所有数据表
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:22:12
	 */
	public ReportTableResult getReportTableByRptId(String reportId);

	/**
	 * 新增报表显示样式
	 * 
	 * @param reportStyle
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:24:53
	 */
	public Result addReportStyle(ReportStyleParam reportStyle);

	/**
	 * 修改指定的报表显示样式
	 * 
	 * @param reportStyle
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:25:10
	 */
	public Result editReportStyle(ReportStyleParam reportStyle);

	/**
	 * 删除指定的报表显示样式
	 * 
	 * @param reportId
	 * @param scene
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:25:24
	 */
	public Result deleteReportStyle(String reportId, int scene);

	/**
	 * 获取指定报表的所有显示样式
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:25:36
	 */
	public ReportStyleResult getReportStyleByRptId(String reportId);

	/**
	 * 新增报表授权
	 * 
	 * @param reportgrant
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:25:52
	 */
	public Result addReportGrantTo(ReportGrantToVO reportgrant);

	/**
	 * 删除指定的报表授权
	 * 
	 * @param reportId
	 * @param orgId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月5日 下午12:26:09
	 */
	public Result deleteReportGrantTo(String reportId, String orgId);

	/**
	 * 获取报表已有授权列表
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月4日 下午4:06:52
	 */
	public ReportGrantToResult getReportGrantToByRptId(String reportId);

	/**
	 * 获取报表信息 主要用于前端获取报表基本信息使用
	 * 
	 * @param id
	 * @return 返回参数中包括报表名称,报表显示样式信息(xslt),需要输入的参数信息
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年4月11日 下午9:15:04
	 */
	public ReportViewResult getReportViewById(String id, Integer scene);

	/**
	 * 获取报表信息 主要用于报表数据服务获取报表数据使用
	 * 
	 * @param id
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年4月12日 上午11:02:33
	 */
	public ReportDesignResult getReportDesignById(String id);

	/**
	 * 增加我的常用报表
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月15日 下午12:10:50
	 */
	public Result addMyUsuallyReport(String reportId);

	/**
	 * 取消我的常用报表
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月15日 下午12:11:00
	 */
	public Result cancelMyUsuallyReport(String reportId);

	/**
	 * 阅览我的常用报表
	 * 
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月15日 下午12:12:42
	 */
	public Result readMyUsuallyReport(String reportId);

	/**
	 * 获取我的常用报表
	 * 
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 下午4:03:30
	 */
	public ReportUsuallyResult getMyUsuallyReports();

	/**
	 * 获取我有权限的报表
	 * 
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 下午4:03:39
	 */
	public ReportUsuallyResult getMyGrantToReports(Integer pageNow, Integer pageSize);

	/**
	 * 获取我有权限的监控大屏报表清单
	 * @param name
	 * @return
	 * <p>说明：</p>
	 * @author 虾米 by 2018年6月7日 下午12:24:44
	 */
	public ReportListResult getMyGrantScreenReportList();	
	
	/**
	 * 获取我有管理权限的报表
	 * 
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 下午4:03:48
	 */
	public ReportManageResult getMyManageReports(String orgId, Integer pageNow, Integer pageSize);

	/**
	 * 获取xlst文件的实际访问地址 (用于获取oss访问地址)
	 * 
	 * @param fileUrl
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年6月13日 下午12:06:28
	 */
	public BaseResult<String> getVisitFileUrl(String fileUrl);

	/**
	 * 报表同步到其他区域
	 * 
	 * @param reportId
	 * @param cloudIds
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年8月22日 下午4:08:03
	 */
	@ParamentValidate(name = "报表", rules = {ValidateRules.NOT_NULL})
	public ReportCopyResult copyReportList(String reportId, List<String> cloudIds);

	// #start 报表开放记录
	
	@ParamentValidate(name = "报表授权", rules = {ValidateRules.NOT_NULL})
	public Result saveReportOpenTo(ReportOpenTo reportOprnTo);
	
	@ParamentValidate(name = "报表授权", rules = {ValidateRules.NOT_NULL})
	public Result deleteReportOpenTo(String id);
	
	@ParamentValidate(name = "报表", rules = {ValidateRules.NOT_NULL})
	public BaseResult<ReportOpenTo> getReportOpenToList(String rptId);
	
	// #end 报表开放记录
}
