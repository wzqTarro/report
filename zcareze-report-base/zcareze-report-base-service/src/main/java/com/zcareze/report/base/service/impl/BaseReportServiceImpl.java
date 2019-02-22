/**
 * zcareze Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.zcareze.report.base.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcareze.biz.org.service.OrgBizService;
import com.zcareze.bottom.cache.service.CacheService;
import com.zcareze.commons.cache.ApplicationCacheUtils;
import com.zcareze.commons.result.BaseResult;
import com.zcareze.commons.result.Result;
import com.zcareze.commons.result.ResultEnum;
import com.zcareze.commons.thread.ThreadUtils;
import com.zcareze.commons.utils.DateUtil;
import com.zcareze.commons.utils.StringUtils;
import com.zcareze.commons.utils.ValidatorUtil;
import com.zcareze.data.annotation.CustomTransactional;
import com.zcareze.data.exception.DataSourceException;
import com.zcareze.data.transaction.TransactionManager;
import com.zcareze.domain.regional.OrgList;
import com.zcareze.file.service.FileService;
import com.zcareze.file.service.enst.FileTypeEnum;
import com.zcareze.file.service.result.FileUrlResult;
import com.zcareze.file.service.result.UploadFileResult;
import com.zcareze.regional.service.OrgService;
import com.zcareze.regional.service.result.OrgListResult;
import com.zcareze.report.base.dao.BaseReportDao;
import com.zcareze.report.base.domain.ReportGrantTo;
import com.zcareze.report.base.domain.ReportGrantToManage;
import com.zcareze.report.base.domain.ReportGroup;
import com.zcareze.report.base.domain.ReportInput;
import com.zcareze.report.base.domain.ReportList;
import com.zcareze.report.base.domain.ReportOpenTo;
import com.zcareze.report.base.domain.ReportStyle;
import com.zcareze.report.base.domain.ReportTable;
import com.zcareze.report.base.domain.ReportUsually;
import com.zcareze.report.base.enst.ReportConstants;
import com.zcareze.report.base.enst.ReportResultMessage;
import com.zcareze.report.base.service.BaseReportService;
import com.zcareze.report.base.service.bo.QueryInputDesignBO;
import com.zcareze.report.base.service.bo.ReportDesignBO;
import com.zcareze.report.base.service.bo.ReportTableDesignBO;
import com.zcareze.report.base.service.cache.ReportDesignCache;
import com.zcareze.report.base.service.cache.ReportViewCache;
import com.zcareze.report.base.service.enst.InputDataType;
import com.zcareze.report.base.service.enst.ReportSystemParameter;
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
import com.zcareze.report.base.service.vo.ReportManageVO;
import com.zcareze.report.base.service.vo.ReportStyleVO;
import com.zcareze.report.base.service.vo.ReportTableVO;
import com.zcareze.report.base.service.vo.ReportUsuallyVO;
import com.zcareze.report.base.service.vo.ReportViewVO;
import com.zcareze.report.base.service.vo.ViewParameterVO;
import com.zcareze.report.common.enst.ParamDataType;
import com.zcareze.token.service.TokenService;
import com.zcareze.token.service.data.CurrentLoginInfo;

/**
 * 
 * 
 * @Filename BaseReportServiceImpl.java
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
@Service("baseReportService")
public class BaseReportServiceImpl implements BaseReportService {
	public static final Logger LOGGER = LoggerFactory.getLogger(BaseReportServiceImpl.class);
	private static final String PARAM_PREFIX = "[";
	private static final String PARAM_SUFFIX = "]";
	private static final String SUFFIX = ".xslt";
	private static final String XSLTFOLDER = "xslt";

	@Autowired
	private BaseReportDao reportDao;
	@Autowired
	private FileService fileService;
	@Autowired
	private OrgService orgService;

	@Autowired
	private TokenService tokenService;
	@Autowired
	private OrgBizService orgBizService;
	@Autowired
	private CacheService cacheService;

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
	@Override
	public Result addReportGroup(ReportGroupVO reportGroup) {
		if (ValidatorUtil.isEmpty(reportGroup.getCode(), reportGroup.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportGroup gp = reportDao.findReportGroupByCode(reportGroup.getCode());
		if (gp != null) {
			return Result.error(ResultEnum.PARAM_ERROR, "分组已存在");
		}
		ReportGroup group = new ReportGroup();
		BeanUtils.copyProperties(reportGroup, group);
		reportDao.addReportGroup(group);
		return Result.success();
	}

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
	@Override
	public Result editReportGroup(ReportGroupVO reportGroup) {
		if (ValidatorUtil.isEmpty(reportGroup.getCode(), reportGroup.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportGroup group = new ReportGroup();
		BeanUtils.copyProperties(reportGroup, group);
		reportDao.editReportGroup(group);
		return Result.success();
	}

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
	@Override
	public Result deleteReportGroup(String code) {
		if (ValidatorUtil.isEmpty(code)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		reportDao.deleteReportGroup(code);
		return Result.success();
	}

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
	@Override
	public ReportGroupResult getReportGroupByCode(String code) {
		ReportGroupResult result = new ReportGroupResult();
		if (ValidatorUtil.isEmpty(code)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
			return result;
		}
		ReportGroup reportGroup = reportDao.findReportGroupByCode(code);
		if (reportGroup != null) {
			ReportGroupVO reportGroupVO = new ReportGroupVO();
			BeanUtils.copyProperties(reportGroup, reportGroupVO);
			result.success(reportGroupVO);
		} else {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.BASEREPORTDATAEMPTY);
		}
		return result;
	}

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
	public ReportGroupResult getReportGroupList(Integer pageNow, Integer pageSize) {
		ReportGroupResult result = new ReportGroupResult();
		Integer pageStart = pageNow * pageSize;
		if (pageStart <= 0) {
			pageStart = 0;
		}
		List<ReportGroup> reportGroups = reportDao.findReportGroupList(pageStart, pageSize);

		List<ReportGroupVO> reportGroupVOs = new ArrayList<ReportGroupVO>();
		if (reportGroups != null && !reportGroups.isEmpty()) {
			for (ReportGroup par : reportGroups) {
				ReportGroupVO reportGroup = new ReportGroupVO();
				BeanUtils.copyProperties(par, reportGroup);
				reportGroupVOs.add(reportGroup);
			}
		}
		result.success(reportGroupVOs);

		return result;
	}

	/**
	 * 新增报表
	 * 
	 * @param reportList
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#addReportList(com.zcareze.report.base.service.vo.ReportListVO)
	 * @author 虾米 by 2017年5月24日 下午5:14:07
	 */
	@Override
	public Result addReportList(ReportListVO reportList) {
		if (ValidatorUtil.isEmpty(reportList.getCode(), reportList.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		if (StringUtils.isEmpty(reportList.getId())) {
			reportList.setId(StringUtils.getUUID());
		}
		ReportList existReport = reportDao.findReportListByUnique(reportList.getName(), reportList.getCode());
		if (existReport != null) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.REPORTEXIST);
		}
		ReportList rlist = new ReportList();
		BeanUtils.copyProperties(reportList, rlist);
		rlist.setEditorId(ApplicationCacheUtils.getStaffId());
		rlist.setEditorName(ApplicationCacheUtils.getAccountName());
		rlist.setEditTime(new Date());
		reportDao.addReportList(rlist);
		return Result.success();
	}

	/**
	 * 修改报表
	 * 
	 * @param reportList
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#editReportList(com.zcareze.report.base.service.vo.ReportListVO)
	 * @author 虾米 by 2017年5月24日 下午5:14:31
	 */
	@Override
	public Result editReportList(ReportListVO reportList) {
		if (ValidatorUtil.isEmpty(reportList.getCode(), reportList.getName(), reportList.getId())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportList existReport = reportDao.findReportListByUnique(reportList.getName(), reportList.getCode());
		if (existReport != null && !existReport.getId().equals(reportList.getId())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.REPORTEXIST);
		}
		ReportList rlist = new ReportList();
		BeanUtils.copyProperties(reportList, rlist);
		rlist.setEditorId(ApplicationCacheUtils.getStaffId());
		rlist.setEditorName(ApplicationCacheUtils.getAccountName());
		rlist.setEditTime(new Date());
		reportDao.editReportList(rlist);
		removeCache(rlist.getId());
		return Result.success();
	}

	/**
	 * 删除报表
	 * 
	 * @param id
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#deleteReportList(java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:14:40
	 */
	@Override
	@CustomTransactional
	public Result deleteReportList(String id) {
		if (ValidatorUtil.isEmpty(id)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		List<ReportStyle> reportStyles = reportDao.findReportStyleByRptId(id);
		for (ReportStyle reportStyle : reportStyles) {
			reportDao.deleteReportStyle(reportStyle.getRptId(), reportStyle.getScene());
			fileService.deleteFile(FileTypeEnum.Report, reportStyle.getFileUrl());
		}
		reportDao.deleteReportList(id);
		removeCache(id);
		return Result.success();
	}

	/**
	 * 获取指定的报表信息
	 * 
	 * @param id
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#getReportListById(java.lang.String)
	 * @author 虾米 by 2017年5月5日 下午12:19:16
	 */
	@Override
	public ReportListResult getReportListById(String id) {
		ReportListResult result = new ReportListResult();
		if (ValidatorUtil.isEmpty(id)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
			return result;
		}
		ReportList reportlist = reportDao.findReportListById(id);
		if (reportlist != null) {
			ReportListVO reportListVO = new ReportListVO();
			BeanUtils.copyProperties(reportlist, reportListVO);
			result.success(reportListVO);
		} else {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		return result;
	}

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
	@Override
	public ReportListResult getReportList(String name, String groupCode, Integer pageNow, Integer pageSize) {
		ReportListResult result = new ReportListResult();
		Integer pageStart = pageNow * pageSize;
		if (pageStart <= 0) {
			pageStart = 0;
		}
		List<ReportList> reportlists = reportDao.findReportList(name, groupCode, pageNow, pageSize);
		List<ReportListVO> reportListvos = new ArrayList<ReportListVO>();
		if (reportlists != null) {
			for (ReportList reportList : reportlists) {
				ReportListVO reportListVO = new ReportListVO();
				BeanUtils.copyProperties(reportList, reportListVO);
				reportListvos.add(reportListVO);
			}
		}
		result.success(reportListvos);
		return result;
	}

	/**
	 * 获取监控大屏报表清单
	 * 
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2018年6月7日 下午12:24:44
	 */
	public ReportListResult getScreenReportList(String name) {
		ReportListResult result = new ReportListResult();
		List<ReportList> reportlists = reportDao.findScreenReportList(name);
		List<ReportListVO> reportListvos = new ArrayList<ReportListVO>();
		if (reportlists != null) {
			for (ReportList reportList : reportlists) {
				ReportListVO reportListVO = new ReportListVO();
				BeanUtils.copyProperties(reportList, reportListVO);
				reportListvos.add(reportListVO);
			}
		}
		result.success(reportListvos);
		return result;
	}

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
	@Override
	public BaseResult<Date> getReportListUpdateTime(String id) {
		BaseResult<Date> result = new BaseResult<Date>();
		if (ValidatorUtil.isEmpty(id)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
			return result;
		}
		Date updateTime = reportDao.findReportListUpdateTime(id);
		result.success(updateTime);
		return result;
	}

	/**
	 * 新增报表参数
	 * 
	 * @param reportInput
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#addReportInput(com.zcareze.report.base.service.vo.ReportInputVO)
	 * @author 虾米 by 2017年5月24日 下午5:14:48
	 */
	@Override
	public Result addReportInput(ReportInputVO reportInput) {
		if (ValidatorUtil.isEmpty(reportInput.getCaption(), reportInput.getDataType(), reportInput.getRptId(),
				reportInput.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		if (judgeIsSystem(reportInput.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.INPUTISYSTEM);
		}
		ReportInput rlist = new ReportInput();
		BeanUtils.copyProperties(reportInput, rlist);
		reportDao.addReportInput(rlist);
		reportDao.updateReportListEditInfo(rlist.getRptId(), new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(rlist.getRptId());
		return Result.success();
	}

	/**
	 * 修改报表参数
	 * 
	 * @param reportInput
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#editReportInput(com.zcareze.report.base.service.vo.ReportInputVO)
	 * @author 虾米 by 2017年5月24日 下午5:14:55
	 */
	@Override
	public Result editReportInput(ReportInputVO reportInput) {
		if (ValidatorUtil.isEmpty(reportInput.getCaption(), reportInput.getDataType(), reportInput.getRptId(),
				reportInput.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		if (judgeIsSystem(reportInput.getName())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.INPUTISYSTEM);
		}
		ReportInput rlist = new ReportInput();
		BeanUtils.copyProperties(reportInput, rlist);
		reportDao.editReportInput(rlist);
		reportDao.updateReportListEditInfo(rlist.getRptId(), new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(rlist.getRptId());
		return Result.success();
	}

	/**
	 * 删除报表参数
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#deleteReportInput(java.lang.String,
	 *      java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:15:02
	 */
	@Override
	public Result deleteReportInput(String reportId, String name) {
		if (ValidatorUtil.isEmpty(reportId, name)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		reportDao.deleteReportInput(reportId, name);
		reportDao.updateReportListEditInfo(reportId, new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(reportId);
		return Result.success();
	}

	/**
	 * 新增报表数据表
	 * 
	 * @param reportTable
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#addReportTable(com.zcareze.report.base.service.vo.ReportTableVO)
	 * @author 虾米 by 2017年5月24日 下午5:15:28
	 */
	@Override
	public Result addReportTable(ReportTableVO reportTable) {
		if (ValidatorUtil.isEmpty(reportTable.getName(), reportTable.getRptId(), reportTable.getSqlText())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportTable rlist = new ReportTable();
		BeanUtils.copyProperties(reportTable, rlist);
		reportDao.addReportTable(rlist);;
		reportDao.updateReportListEditInfo(rlist.getRptId(), new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(rlist.getRptId());
		return Result.success();
	}

	/**
	 * 修改報表數據表
	 * 
	 * @param reportTable
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#editReportTable(com.zcareze.report.base.service.vo.ReportTableVO)
	 * @author 虾米 by 2017年5月24日 下午5:15:37
	 */
	@Override
	public Result editReportTable(ReportTableVO reportTable) {
		if (ValidatorUtil.isEmpty(reportTable.getName(), reportTable.getRptId(), reportTable.getSqlText())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportTable rlist = new ReportTable();
		BeanUtils.copyProperties(reportTable, rlist);
		reportDao.editReportTable(rlist);
		reportDao.updateReportListEditInfo(rlist.getRptId(), new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(rlist.getRptId());
		return Result.success();
	}

	/**
	 * 删除指定的报表数据表
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#deleteReportTable(java.lang.String,
	 *      java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:15:53
	 */
	@Override
	public Result deleteReportTable(String reportId, String name) {
		if (ValidatorUtil.isEmpty(reportId, name)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		reportDao.deleteReportTable(reportId, name);
		reportDao.updateReportListEditInfo(reportId, new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(reportId);
		return Result.success();
	}

	/**
	 * 获取指定报表的数据表
	 * 
	 * @param reportId
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#getReportTableByRptId(java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:16:08
	 */
	@Override
	public ReportTableResult getReportTableByRptId(String reportId) {
		ReportTableResult result = new ReportTableResult();
		List<ReportTable> ReportTables = reportDao.findReportTableByRptId(reportId);
		List<ReportTableVO> reportTablevos = new ArrayList<ReportTableVO>();
		if (ReportTables != null) {
			for (ReportTable reporttable : ReportTables) {
				ReportTableVO reportTableVO = new ReportTableVO();
				BeanUtils.copyProperties(reporttable, reportTableVO);
				reportTablevos.add(reportTableVO);
			}
		}
		result.success(reportTablevos);
		return result;
	}

	/**
	 * 新增报表样式
	 * 
	 * @param reportStyle
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#addReportStyle(com.zcareze.report.base.service.param.ReportStyleParam)
	 * @author 虾米 by 2017年5月24日 下午5:16:34
	 */
	@Override
	public Result addReportStyle(ReportStyleParam reportStyle) {
		if (ValidatorUtil.isEmpty(reportStyle.getRptId())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		if (StringUtils.isEmpty(reportStyle.getFileContent()) && StringUtils.isEmpty(reportStyle.getFileUrl())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		String cloudId = ApplicationCacheUtils.getCloudId();
		String reportId = reportStyle.getRptId();
		// 上传文件
		UploadFileResult uploadResult = fileService.uploadTextFile(FileTypeEnum.Report, "", SUFFIX,
				reportStyle.getFileContent(), cloudId, XSLTFOLDER, reportId);
		if (!uploadResult.isSuccess()) {
			return Result.error(ResultEnum.SERVER_ERROR, ReportResultMessage.FILEUPLOADERROR);
		}
		String fileUrl = uploadResult.getOne();
		ReportStyle rlist = new ReportStyle();
		BeanUtils.copyProperties(reportStyle, rlist);
		rlist.setFileUrl(fileUrl);
		try {
			reportDao.addReportStyle(rlist);
			reportDao.updateReportListEditInfo(rlist.getRptId(), new Date(), ApplicationCacheUtils.getStaffId(),
					ApplicationCacheUtils.getAccountName());
			removeCache(rlist.getRptId());
			return Result.success();
		} catch (DataSourceException ex) {
			// 回滚文件存储
			fileService.deleteFile(FileTypeEnum.Report, fileUrl);
			LOGGER.error(ReportResultMessage.REPORTOPERATESQLERROR, ex);
			return Result.error(ResultEnum.SERVER_ERROR, ReportResultMessage.REPORTOPERATESQLERROR);
		}
	}

	/**
	 * 修改报表样式
	 * 
	 * @param reportStyle
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#editReportStyle(com.zcareze.report.base.service.param.ReportStyleParam)
	 * @author 虾米 by 2017年5月24日 下午5:16:44
	 */
	@Override
	public Result editReportStyle(ReportStyleParam reportStyle) {
		if (ValidatorUtil.isEmpty(reportStyle.getRptId())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportStyle oldStyle = null;
		List<ReportStyle> reportStyles = reportDao.findReportStyleByRptId(reportStyle.getRptId());
		for (ReportStyle reportStyle2 : reportStyles) {
			if (reportStyle2.getScene().equals(reportStyle.getScene())) {
				oldStyle = reportStyle2;
				break;
			}
		}
		if (oldStyle == null) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.STYLENOTEXIST);
		}
		String fileUrl = oldStyle.getFileUrl();
		// 文件内容不为空,需要更新文件
		if (!StringUtils.isEmpty(reportStyle.getFileContent())) {
			UploadFileResult uploadResult = fileService.uploadTextFile(FileTypeEnum.Report, fileUrl, "",
					reportStyle.getFileContent());
			if (!uploadResult.isSuccess()) {
				return Result.error(ResultEnum.SERVER_ERROR, ReportResultMessage.FILEUPLOADERROR);
			}
			reportStyle.setFileUrl(uploadResult.getOne());
		}
		// ReportStyleUrlCache.remove(ApplicationCacheUtils.getCloudId(), reportStyle.getRptId(),
		// reportStyle.getScene());
		ReportStyle rlist = new ReportStyle();
		BeanUtils.copyProperties(reportStyle, rlist);
		rlist.setFileUrl(fileUrl);
		reportDao.editReportStyle(rlist);
		reportDao.updateReportListEditInfo(rlist.getRptId(), new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		removeCache(rlist.getRptId());
		return Result.success();
	}

	/**
	 * 删除指定的报表样式
	 * 
	 * @param reportId
	 * @param scene
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#deleteReportStyle(java.lang.String,
	 *      int)
	 * @author 虾米 by 2017年5月24日 下午5:16:55
	 */
	@Override
	public Result deleteReportStyle(String reportId, int scene) {
		if (ValidatorUtil.isEmpty(reportId)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		ReportStyle oldStyle = null;
		List<ReportStyle> reportStyles = reportDao.findReportStyleByRptId(reportId);
		for (ReportStyle reportStyle2 : reportStyles) {
			if (reportStyle2.getScene().equals(scene)) {
				oldStyle = reportStyle2;
				break;
			}
		}
		reportDao.deleteReportStyle(reportId, scene);
		reportDao.updateReportListEditInfo(reportId, new Date(), ApplicationCacheUtils.getStaffId(),
				ApplicationCacheUtils.getAccountName());
		if (oldStyle != null && StringUtils.isNotEmpty(oldStyle.getFileUrl())) {
			fileService.deleteFile(FileTypeEnum.Report, oldStyle.getFileUrl());
		}
		removeCache(reportId);
		// ReportStyleUrlCache.remove(ApplicationCacheUtils.getCloudId(), reportId, scene);
		return Result.success();
	}

	/**
	 * 获取指定报表的样式列表
	 * 
	 * @param reportId
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#getReportStyleByRptId(java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:17:08
	 */
	@Override
	public ReportStyleResult getReportStyleByRptId(String reportId) {
		ReportStyleResult result = new ReportStyleResult();
		List<ReportStyle> ReportStyles = reportDao.findReportStyleByRptId(reportId);
		List<ReportStyleVO> reportStylevos = new ArrayList<ReportStyleVO>();
		if (ReportStyles != null) {
			for (ReportStyle reportstyle : ReportStyles) {
				ReportStyleVO reportStyleVO = new ReportStyleVO();
				BeanUtils.copyProperties(reportstyle, reportStyleVO);
				// 授权
				if (StringUtils.isNotEmpty(reportstyle.getFileUrl())) {
					FileUrlResult urlResult = fileService.getVisitFileUrl(FileTypeEnum.Report,
							reportstyle.getFileUrl());
					if (urlResult.isSuccess()) {
						reportStyleVO.setVisitUrl(urlResult.getOne());
					}
				}
				reportStylevos.add(reportStyleVO);
			}
		}
		result.success(reportStylevos);
		return result;
	}

	/**
	 * 新增报表授权
	 * 
	 * @param reportgrant
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#addReportGrantTo(com.zcareze.report.base.service.vo.ReportGrantToVO)
	 * @author 虾米 by 2017年5月24日 下午5:17:20
	 */
	@Override
	public Result addReportGrantTo(ReportGrantToVO reportgrant) {
		if (ValidatorUtil.isEmpty(reportgrant.getRptId(), reportgrant.getOrgId(), reportgrant.getRoles(),
				reportgrant.getRptId())) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		String rptId = reportgrant.getRptId();
		String[] ids = rptId.split(";");
		if (ids != null && ids.length > 0) {
			for (String string : ids) {
				if (StringUtils.isEmpty(string)) {
					continue;
				}
				ReportGrantTo rlist = new ReportGrantTo();
				BeanUtils.copyProperties(reportgrant, rlist);
				rlist.setRptId(string);
				rlist.setGranter(ApplicationCacheUtils.getAccountName());
				rlist.setGrantTime(new Date());
				if (rlist.getManage() == null) {
					rlist.setManage(0);
				}
				reportDao.addReportGrantTo(rlist);
			}
		}
		return Result.success();
	}

	/**
	 * 删除报表授权
	 * 
	 * @param reportId
	 * @param orgId
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#deleteReportGrantTo(java.lang.String,
	 *      java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:17:30
	 */
	@Override
	public Result deleteReportGrantTo(String reportId, String orgId) {
		if (ValidatorUtil.isEmpty(reportId, orgId)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		reportDao.deleteReportGrantTo(reportId, orgId);
		return Result.success();
	}

	/**
	 * 获取指定报表的参数列表
	 * 
	 * @param reportId
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#getPeportInputsByRptId(java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:17:48
	 */
	@Override
	public ReportInputResult getPeportInputsByRptId(String reportId) {
		ReportInputResult result = new ReportInputResult();
		try {
			List<ReportInput> parameters = reportDao.findReportInputByRptId(reportId);
			List<ReportInputVO> reportInputs = new ArrayList<ReportInputVO>();
			if (parameters != null && !parameters.isEmpty()) {
				for (ReportInput par : parameters) {
					ReportInputVO reportparameter = new ReportInputVO();
					BeanUtils.copyProperties(par, reportparameter);
					reportInputs.add(reportparameter);
				}
			}
			result.success(reportInputs);
		} catch (DataSourceException ex) {
			LOGGER.error(ReportResultMessage.REPORTOPERATESQLERROR, ex);
			result.failure(ResultEnum.SERVER_ERROR, ReportResultMessage.REPORTOPERATESQLERROR);
		}
		return result;
	}

	/**
	 * 获取指定的报表参数
	 * 
	 * @param reportId
	 * @param name
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#getReportInput(java.lang.String,
	 *      java.lang.String)
	 * @author 虾米 by 2017年5月24日 下午5:18:03
	 */
	@Override
	public ReportInputResult getReportInput(String reportId, String name) {
		ReportInputResult result = new ReportInputResult();
		if (ValidatorUtil.isEmpty(name, reportId)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
			return result;
		}
		ReportInput parameter = reportDao.findReportInput(reportId, name);
		if (parameter != null) {
			ReportInputVO reportparameterVO = new ReportInputVO();
			BeanUtils.copyProperties(parameter, reportparameterVO);
			result.success(reportparameterVO);
		} else {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.BASEREPORTDATAEMPTY);
		}
		return result;
	}

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
	@Override
	public ReportGrantToResult getReportGrantToByRptId(String reportId) {
		ReportGrantToResult result = new ReportGrantToResult();
		List<ReportGrantTo> parameters = reportDao.findReportGrantToByRptId(reportId);
		List<ReportGrantToVO> reportGrantToVOs = new ArrayList<ReportGrantToVO>();
		if (parameters != null && !parameters.isEmpty()) {
			for (ReportGrantTo par : parameters) {
				ReportGrantToVO reportGrantToVO = new ReportGrantToVO();
				BeanUtils.copyProperties(par, reportGrantToVO);
				String orgName = getOrgName(reportGrantToVO.getOrgId(), true);
				reportGrantToVO.setOrgName(orgName);
				reportGrantToVOs.add(reportGrantToVO);
			}
		}
		result.success(reportGrantToVOs);
		return result;
	}

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
	@Override
	public Result addMyUsuallyReport(String reportId) {
		if (ValidatorUtil.isEmpty(reportId)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		String staffId = ApplicationCacheUtils.getStaffId();
		// // 需要判断是否有权限,针对开放授权的报表，加入常用报表时,需要同步增加权限
		// if (!reportDao.checkReportGrantToByStaffId(reportId, staffId)) {
		// // 添加权限
		// ReportGrantTo reportGrantTo = null;
		// List<ReportOpenTo> rptOpenToList = reportDao.findReportOpenToList(reportId);
		// if (rptOpenToList == null || rptOpenToList.size() <= 0) {
		// return Result.serviceError(ReportResultMessage.REPORTNOGRANT);
		// }
		// OrgListOrgMemberWrapper MemberWrapper =
		// orgBizService.getOrgMemberOrgListByStaffId(staffId);
		// List<OrgListOrgMember> orgMembers = null;
		// if (MemberWrapper.isSuccess()) {
		// orgMembers = MemberWrapper.getOrgLists();
		// }
		// if (orgMembers != null && orgMembers.size() > 0) {
		// // 找到开放授权中符合登录人的授权，加入授权表
		// for (ReportOpenTo rptOpenTo : rptOpenToList) {
		// String orgTreeId= rptOpenTo.getOrgTreeId();
		// Integer orgTreeLayer= rptOpenTo.getOrgTreeLayer();
		// for (OrgListOrgMember orgListOrgMember : orgMembers) {
		// if(StringUtils.isEmpty(orgTreeId)){
		//
		// }
		// else{
		// OrgSingleWrapper orgListWrapper= orgBizService.getOrgListById(orgTreeId);
		// if(!orgListWrapper.isSuccess()||orgListWrapper.getOrgList()==null){
		// continue;
		// }
		//
		// }
		// }
		// }
		// }
		// }
		reportDao.addReportUsually(reportId, staffId);
		return Result.success();
	}

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
	@Override
	public Result cancelMyUsuallyReport(String reportId) {
		if (ValidatorUtil.isEmpty(reportId)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		String staffId = ApplicationCacheUtils.getStaffId();
		reportDao.deleteReportUsually(reportId, staffId);
		return Result.success();
	}

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
	@Override
	public Result readMyUsuallyReport(String reportId) {
		if (ValidatorUtil.isEmpty(reportId)) {
			return Result.error(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
		}
		String staffId = ApplicationCacheUtils.getStaffId();
		Date now = new Date();
		reportDao.readReportUsually(reportId, staffId, now);
		return Result.success();
	}

	/**
	 * 获取我的常用报表
	 * 
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 下午4:03:30
	 */
	public ReportUsuallyResult getMyUsuallyReports() {
		ReportUsuallyResult result = new ReportUsuallyResult();
		String staffId = ApplicationCacheUtils.getStaffId();
		// 云管管理人员
		boolean isAdmin = ApplicationCacheUtils.getLoginManager();
		List<ReportUsuallyVO> reportUsuallyVOs = new ArrayList<ReportUsuallyVO>();
		List<ReportUsually> reportUsually = reportDao.findReportUsuallyByStaffId(staffId);
		for (ReportUsually report : reportUsually) {
			if (!isAdmin) {
				Boolean hasGrantto = reportDao.checkReportGrantToByStaffId(report.getRptId(), staffId);
				if (!hasGrantto) {
					continue;
				}
			}
			ReportUsuallyVO reportvo = getReportUsuallyForMe(report.getRptId(), true, report.getReadTime());
			if (reportvo != null) {
				reportvo.setSeqNum(report.getSeqNum());
				reportvo.setUsually(true);
				reportUsuallyVOs.add(reportvo);
			}
		}
		result.success(reportUsuallyVOs);
		return result;
	}

	/**
	 * 获取我有权限的报表
	 * 
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 下午4:03:39
	 */
	public ReportUsuallyResult getMyGrantToReports(Integer pageNow, Integer pageSize) {
		ReportUsuallyResult result = new ReportUsuallyResult();
		String staffId = ApplicationCacheUtils.getStaffId();
		List<ReportUsuallyVO> reportUsuallyVOs = new ArrayList<ReportUsuallyVO>();
		List<ReportUsually> reportUsually = reportDao.findReportUsuallyByStaffId(staffId);
		// 云管管理人员
		boolean isAdmin = ApplicationCacheUtils.getLoginManager();
		Integer pageStart = pageNow * pageSize;
		if (pageStart <= 0) {
			pageStart = 0;
		}
		if (isAdmin) {
			// 取所有报表
			List<ReportList> reportLists = reportDao.findReportList("", "", pageStart, pageSize);
			int seqNum = 1;
			for (ReportList report : reportLists) {
				ReportUsuallyVO reportvo = new ReportUsuallyVO();
				reportvo.setSeqNum(seqNum);
				if (!StringUtils.isEmpty(report.getGrpCode())) {
					ReportGroup group = getReportGroup(report.getGrpCode());
					if (group != null) {
						reportvo.setColor(group.getColor());
						reportvo.setGroupName(group.getName());
						reportvo.setGroupCode(group.getCode());
					}
				}
				reportvo.setReportId(report.getId());
				reportvo.setReportName(report.getName());
				Long timeLong = 0l;
				if (report.getEditTime() != null) {
					timeLong = report.getEditTime().getTime();
				}
				reportvo.setUpdateTime(timeLong);
				reportvo.setUsually(false);
				for (ReportUsually report2 : reportUsually) {
					if (report2.getRptId().equals(report.getId())) {
						reportvo.setUsually(true);
						break;
					}
				}
				reportUsuallyVOs.add(reportvo);
				seqNum = seqNum + 1;
			}
		} else {
			int seqNum = 1;
			List<ReportGrantTo> reportGrants = reportDao.findReportGrantToByStaffId(staffId, pageStart, pageSize);
			List<String> existIds = new ArrayList<String>();
			for (ReportGrantTo reportGrantTo : reportGrants) {
				String rolsIn = StringUtils.intersectStr(reportGrantTo.getClasses(), reportGrantTo.getRoles(), ";");
				if (StringUtils.isEmpty(rolsIn)) {
					continue;
				}
				if (existIds.contains(reportGrantTo.getRptId())) {
					continue;
				}
				ReportUsuallyVO reportvo = getReportUsuallyForMe(reportGrantTo.getRptId(), false, null);
				if (reportvo != null) {
					reportvo.setSeqNum(seqNum);
					reportvo.setUsually(false);
					for (ReportUsually report : reportUsually) {
						if (report.getRptId().equals(reportGrantTo.getRptId())) {
							reportvo.setUsually(true);
							break;
						}
					}
					reportUsuallyVOs.add(reportvo);
					existIds.add(reportGrantTo.getRptId());
					seqNum = seqNum + 1;
				}
			}
		}
		result.success(reportUsuallyVOs);
		return result;
	}

	/**
	 * 获取我有权限的监控大屏报表清单
	 * 
	 * @param name
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2018年6月7日 下午12:24:44
	 */
	public ReportListResult getMyGrantScreenReportList() {
		ReportListResult result = new ReportListResult();
		String staffId = ApplicationCacheUtils.getStaffId();
		// 云管管理人员
		boolean isAdmin = ApplicationCacheUtils.getLoginManager();
		List<ReportListVO> reportList = new ArrayList<>();
		List<ReportList> rtpList = new ArrayList<>();
		if (isAdmin) {
			rtpList = reportDao.findScreenReportList("");
		} else {
			rtpList = reportDao.findScreenReportByStaffId(staffId);
		}
		if (rtpList != null && rtpList.size() > 0) {
			for (ReportList reportList2 : rtpList) {
				ReportListVO vo = new ReportListVO();
				BeanUtils.copyProperties(reportList2, vo);
				reportList.add(vo);
			}
		}
		result.success(reportList);
		return result;
	}

	/**
	 * 获取我的报表信息 (我的常用报表;我有权限的报表)
	 * 
	 * @param reportId
	 * @param calRead
	 * @param readTime
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月15日 下午3:55:38
	 */
	private ReportUsuallyVO getReportUsuallyForMe(String reportId, Boolean calRead, Date readTime) {
		ReportUsuallyVO reportVo = new ReportUsuallyVO();
		// 从缓存中读取
		ReportViewVO reportViewVO = ReportViewCache.get(ApplicationCacheUtils.getCloudId(), reportId);
		// TODO 临时处理,主要是在后端编辑还未调整的时候临时更新缓存
		if (reportViewVO != null) {
			Date updateTime = reportDao.findReportListUpdateTime(reportId);
			if (DateUtil.getTimeInmilis(updateTime, reportViewVO.getEditTime()) != 0) {
				removeCache(reportId);
				reportViewVO = null;
			}
		}
		// 缓存中不存在,从数据库中读取并进行sql的解析
		if (reportViewVO == null) {
			dealReportCache(reportId, 0);
			reportViewVO = ReportViewCache.get(ApplicationCacheUtils.getCloudId(), reportId);
			if (reportViewVO == null) {
				return null;
			}
		}
		if (!StringUtils.isEmpty(reportViewVO.getGrpCode())) {
			ReportGroup group = getReportGroup(reportViewVO.getGrpCode());
			if (group != null) {
				reportVo.setColor(group.getColor());
				reportVo.setGroupName(group.getName());
				reportVo.setGroupCode(group.getCode());
			}
		}
		reportVo.setReportId(reportId);
		reportVo.setReportName(reportViewVO.getName());
		reportVo.setUpdateTime(reportViewVO.getUpdateTime());
		// 判断是否需要提示需要查阅
		if (calRead) {
			ViewParameterVO dateInput = null;
			for (ViewParameterVO input : reportViewVO.getInputParams()) {
				InputDataType dataType = InputDataType.parse(input.getDataType());
				if (dataType == InputDataType.TYPE_DAY) {
					dateInput = input;
					break;
				}
				if (dataType == InputDataType.TYPE_MONTH) {
					dateInput = input;
					continue;
				}
				if (dataType == InputDataType.TYPE_YEAR) {
					if (dateInput == null || dateInput.getDataType() != InputDataType.TYPE_MONTH.getName()) {
						dateInput = input;
						continue;
					}
				}
			}
			Date cycDate = null;
			if (dateInput != null) {
				Calendar cal = Calendar.getInstance();
				cycDate = cal.getTime();
				InputDataType dataType = InputDataType.parse(dateInput.getDataType());
				switch (dataType) {
					case TYPE_MONTH :
						cycDate = DateUtil.getMonthFirstDay();
						break;
					case TYPE_YEAR :
						cal.add(Calendar.MONTH, -cal.get(Calendar.MONTH));
						cycDate = cal.getTime();
						break;
					case TYPE_DAY :
						cycDate = DateUtil.convertDateToDate(cal.getTime());
						break;
					default :
						break;
				}
			}
			if (readTime == null || (cycDate != null && DateUtil.getTimeInmilis(cycDate, readTime) < 0)) {
				reportVo.setNoRead(true);
			} else {
				reportVo.setNoRead(false);
			}
		} else {
			reportVo.setNoRead(false);
		}
		return reportVo;
	}

	private ReportGroup getReportGroup(String reportCode) {
		ReportGroup group = reportDao.findReportGroupByCode(reportCode);
		return group;
	}

	/**
	 * 获取我有管理权限的报表
	 * 
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 下午4:03:48
	 */
	public ReportManageResult getMyManageReports(String orgId, Integer pageNow, Integer pageSize) {
		ReportManageResult result = new ReportManageResult();
		Integer pageStart = pageNow * pageSize;
		if (pageStart <= 0) {
			pageStart = 0;
		}
		List<ReportManageVO> reportManages = new ArrayList<ReportManageVO>();
		List<ReportGrantToManage> allReportGrant = reportDao.findReportGrantManageByOrgId(orgId);
		reportManages = dealReportGrantTo(allReportGrant);
		result.success(reportManages);
		return result;
	}

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
	public BaseResult<String> getVisitFileUrl(String fileUrl) {
		BaseResult<String> fileUrls = new BaseResult<String>();
		FileUrlResult fileResult = fileService.getVisitFileUrl(FileTypeEnum.Report, fileUrl);
		if (fileResult != null && fileResult.getOne() != null) {
			fileUrls.success(fileResult.getOne());
		} else {
			fileUrls.setCode(fileResult.getCode());
			fileUrls.setMessage(fileResult.getMessage());
			fileUrls.setErrorCode(fileResult.getErrorCode());
		}
		return fileUrls;
	}

	private List<ReportManageVO> dealReportGrantTo(List<ReportGrantToManage> allreportGrants) {
		List<String> existKeys = new ArrayList<String>();
		List<ReportManageVO> reportManages = new ArrayList<ReportManageVO>();
		for (ReportGrantToManage reportGrantToManage : allreportGrants) {
			String orgId = reportGrantToManage.getOrgId();
			if (StringUtils.isEmpty(orgId)) // 为空表示还未授权
			{
				ReportManageVO noGrantReport = new ReportManageVO();
				ReportGroup group = getReportGroup(reportGrantToManage.getGroupCode());
				if (group != null) {
					noGrantReport.setGroupName(group.getName());
				}
				noGrantReport.setReportId(reportGrantToManage.getRptId());
				noGrantReport.setReportName(reportGrantToManage.getRptName());
				noGrantReport.setGrantList(new ArrayList<ReportGrantToVO>());
				noGrantReport.setOrgId(orgId);
				noGrantReport.setOrgName(ReportConstants.ORG_NOGRANT);
				reportManages.add(noGrantReport);
				existKeys.add(reportGrantToManage.getRptId());
				continue;
			}
			String rptId = reportGrantToManage.getRptId();
			if (existKeys.contains(rptId)) {
				continue;
			}

			ReportManageVO reportManage = new ReportManageVO();
			ReportGroup group = getReportGroup(reportGrantToManage.getGroupCode());
			if (group != null) {
				reportManage.setGroupName(group.getName());
			}
			reportManage.setReportId(reportGrantToManage.getRptId());
			reportManage.setReportName(reportGrantToManage.getRptName());
			List<ReportGrantToVO> grantList = new ArrayList<ReportGrantToVO>();
			for (ReportGrantToManage reportGrantTo : allreportGrants) {
				if (StringUtils.isEmpty(reportGrantTo.getOrgId())) // 为空表示还未授权
				{
					continue;
				}
				String rptId2 = reportGrantTo.getRptId();
				if (!rptId2.equals(rptId)) {
					continue;
				}
				ReportGrantToVO reportGrantToVO = new ReportGrantToVO();
				BeanUtils.copyProperties(reportGrantTo, reportGrantToVO);
				String orgName = getOrgName(reportGrantTo.getOrgId(), true);
				reportGrantToVO.setOrgName(orgName);
				grantList.add(reportGrantToVO);
			}
			reportManage.setGrantList(grantList);
			reportManage.setOrgId("");
			reportManage.setOrgName("");
			reportManages.add(reportManage);
			existKeys.add(rptId);
		}
		return reportManages;
	}

	/**
	 * 得到組織名稱
	 * 
	 * @param orgId
	 * @param orgLayer
	 *            0-全域;1-机构;2-科室;3-医生团队
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月19日 上午10:05:25
	 */
	private String getOrgName(String orgId, Boolean isFullName) {
		if (!StringUtils.isEmpty(orgId)) {
			if (isFullName) {
				BaseResult<OrgList> parOrgList = orgService.getParentOrgTreeByOrgId(orgId);
				String orgName = "";
				if (parOrgList.getLists().size() > 0) {
					orgName = parOrgList.getLists().get(0).getName();
					for (int i = 1; i < parOrgList.getLists().size(); i++) {
						OrgList orgList = parOrgList.getLists().get(i);
						orgName = orgName + ReportConstants.ORG_JOIN + orgList.getName();
					}
				}
				return orgName;
			} else {
				OrgListResult orglistResult = orgService.getOrgListById(orgId);
				if (orglistResult != null && orglistResult.getOne() != null) {
					OrgList orglist = orglistResult.getOne();
					return orglist.getName();
				}
			}
		}
		return "";
	}

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
	public QueryInputDesignResult getQueryReportInput(String reportId, String name) {
		QueryInputDesignResult result = new QueryInputDesignResult();
		ReportInput reportInput = reportDao.findReportInput(reportId, name);
		String sql = "";
		if (reportInput != null) {
			sql = reportInput.getSqlText();
		}
		if (StringUtils.isEmpty(sql)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.INPUTERROR);
			return result;
		}
		LinkedList<String> paramStrs = analysiSql(sql);
		List<String> systemParam = new LinkedList<String>();
		for (String parStr : paramStrs) {
			// sql替换参数,替换成可以执行的参数
			sql = sql.replace(parStr, "?");
			String inputName = parStr.replace(PARAM_PREFIX, "").replace(PARAM_SUFFIX, "").trim();
			// 系统参数
			if (!judgeIsSystem(inputName)) {
				result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.INPUTSQLERROR);
				return result;
			}
			systemParam.add(inputName);
		}
		ReportInputVO reportInputvo = new ReportInputVO();
		BeanUtils.copyProperties(reportInput, reportInputvo);
		reportInputvo.setSqlText(sql);
		QueryInputDesignBO queryInput = new QueryInputDesignBO();
		queryInput.setReportInput(reportInputvo);
		queryInput.setParameters(systemParam);
		result.success(queryInput);
		return result;
	}

	/**
	 * 获取报表信息 主要用于前端获取报表基本信息使用
	 * 
	 * @param id
	 * @return
	 * @see com.zcareze.report.base.service.BaseReportService#getReportViewById(java.lang.String)
	 * @author 虾米 by 2017年4月11日 下午9:14:38
	 */
	@Override
	public ReportViewResult getReportViewById(String id, Integer scene) {
		ReportViewResult result = new ReportViewResult();
		if (ValidatorUtil.isEmpty(id)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
			return result;
		}
		// 从缓存中读取
		ReportViewVO reportViewVO = ReportViewCache.get(ApplicationCacheUtils.getCloudId(), id);
		// TODO 临时处理,主要是在后端编辑还未调整的时候临时更新缓存
		if (reportViewVO != null) {
			Date updateTime = reportDao.findReportListUpdateTime(id);
			if (DateUtil.getTimeInmilis(updateTime, reportViewVO.getEditTime()) != 0) {
				removeCache(id);
				reportViewVO = null;
			}
		}
		// 缓存中不存在,从数据库中读取并进行sql的解析
		if (reportViewVO == null) {
			String errorMsg = dealReportCache(id, scene);
			reportViewVO = ReportViewCache.get(ApplicationCacheUtils.getCloudId(), id);
			if (reportViewVO == null) {
				result.failure(ResultEnum.SERVER_ERROR, errorMsg);
				return result;
			}
		}
		reportViewVO.setFileUrl(getReportStyleUrl(id, scene));
		result.success(reportViewVO);
		return result;
	}

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
	@Override
	public ReportDesignResult getReportDesignById(String id) {
		ReportDesignResult result = new ReportDesignResult();
		if (ValidatorUtil.isEmpty(id)) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.BASEREPORTPARAMEMPTY);
			return result;
		}
		// 从缓存中读取
		ReportDesignBO reportDesignVO = ReportDesignCache.get(ApplicationCacheUtils.getCloudId(), id);
		// TODO 临时处理,主要是在后端编辑还未调整的时候临时更新缓存
		if (reportDesignVO != null) {
			Date updateTime = reportDao.findReportListUpdateTime(id);
			if (DateUtil.getTimeInmilis(updateTime, reportDesignVO.getEditTime()) != 0) {
				removeCache(id);
				reportDesignVO = null;
			}
		}
		// 缓存中不存在,从数据库中读取并进行sql的解析
		if (reportDesignVO == null) {
			String errorMsg = dealReportCache(id, 0);
			reportDesignVO = ReportDesignCache.get(ApplicationCacheUtils.getCloudId(), id);
			if (reportDesignVO == null) {
				result.failure(ResultEnum.SERVER_ERROR, errorMsg);
				return result;
			}
		}
		result.success(reportDesignVO);
		return result;
	}

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
	public ReportCopyResult copyReportList(String reportId, List<String> cloudIds) {
		ReportCopyResult result = new ReportCopyResult();
		if (cloudIds == null || cloudIds.size() <= 0) {
			result.setCode(ResultEnum.PARAM_ERROR.getCode());
			result.setErrorCode(ReportResultMessage.COPYCLOUDEMPTY);
			return result;
		}
		ReportList reportList = reportDao.findReportListById(reportId);
		ReportGroup reportGroup = reportDao.findReportGroupByCode(reportList.getGrpCode());
		List<ReportTable> reportTables = reportDao.findReportTableByRptId(reportId);
		List<ReportInput> reportInputs = reportDao.findReportInputByRptId(reportId);
		List<ReportStyle> reportStyles = reportDao.findReportStyleByRptId(reportId);
		List<String> errorCloudIds = new ArrayList<String>();
		for (String cloudId : cloudIds) {
			ThreadUtils.setCloudId(cloudId);
			try {
				// 报表是否已经存在
				ReportList existReport = reportDao.findReportListById(reportId);
				TransactionManager.beginTransaction();
				reportList.setEditTime(new Date());
				// 分组不存在则添加
				if (reportGroup != null) {
					ReportGroup existGroup = reportDao.findReportGroupByCode(reportGroup.getCode());
					if (existGroup == null) {
						reportDao.addReportGroup(reportGroup);
					}
				}
				if (existReport == null) {
					reportDao.addReportList(reportList);
				} else {
					reportDao.editReportList(reportList);
				}
				for (ReportTable reportTable : reportTables) {
					ReportTable existTable = reportDao.findReportTableByName(reportId, reportTable.getName());
					if (existTable == null) {
						reportDao.addReportTable(reportTable);
					} else {
						reportDao.editReportTable(reportTable);
					}
				}
				for (ReportInput reportInput : reportInputs) {
					ReportInput existinput = reportDao.findReportInput(reportId, reportInput.getName());
					if (existinput == null) {
						reportDao.addReportInput(reportInput);
					} else {
						reportDao.editReportInput(reportInput);
					}
				}
				List<ReportStyle> existReportStyles = reportDao.findReportStyleByRptId(reportId);
				for (ReportStyle reportStyle : reportStyles) {
					ReportStyle existStyle = null;
					if (existReportStyles != null && existReportStyles.size() > 0) {
						for (ReportStyle reportStyle2 : existReportStyles) {
							if (reportStyle2.getScene().equals(reportStyle.getScene())) {
								existStyle = reportStyle2;
								break;
							}
						}
					}
					String fileUrl = "";
					if (existStyle != null) {
						fileUrl = existStyle.getFileUrl();
					}
					if (StringUtils.isEmpty(fileUrl)) {
						FileUrlResult urlResult = fileService.geneteFileUrl(FileTypeEnum.Report, "", SUFFIX, cloudId,
								XSLTFOLDER, reportId);
						if (urlResult.isSuccess()) {
							fileUrl = urlResult.getOne();
						}
					}
					if (StringUtils.isNotEmpty(reportStyle.getFileUrl()) && StringUtils.isNotEmpty(fileUrl)
							&& !reportStyle.getFileUrl().equals(fileUrl)) {
						fileService.copyFile(FileTypeEnum.Report, reportStyle.getFileUrl(), fileUrl);
					}
					if (existStyle == null) {
						existStyle = new ReportStyle();
						BeanUtils.copyProperties(reportStyle, existStyle);
						existStyle.setFileUrl(fileUrl);
						reportDao.addReportStyle(existStyle);
					} else {
						BeanUtils.copyProperties(reportStyle, existStyle);
						existStyle.setFileUrl(fileUrl);
						reportDao.editReportStyle(existStyle);
					}
				}
				TransactionManager.commit();
			} catch (DataSourceException ex) {
				LOGGER.error("同步报表出错:" + ex.getMessage(), ex);
				TransactionManager.rollBack();
				errorCloudIds.add(cloudId);
			}
		}
		if (errorCloudIds != null && errorCloudIds.size() > 0) {
			result.setErrorCloudIds(errorCloudIds);
		}
		return result;
	}

	/**
	 * 处理报表信息并加入到缓存
	 * 
	 * @param id
	 * @param scene
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月3日 下午9:45:21
	 */
	private String dealReportCache(String id, Integer scene) {
		ReportViewVO reportViewVO = new ReportViewVO();
		ReportDesignBO reportDesignVO = new ReportDesignBO();
		List<ReportTableDesignBO> reportTables = new ArrayList<ReportTableDesignBO>();
		ReportList reportlist = reportDao.findReportListById(id);
		List<ReportTable> reportTableList = reportDao.findReportTableByRptId(id);
		if (reportlist == null) {
			return ReportResultMessage.REPORTNOTEXIST;
		}
		reportViewVO.setId(reportlist.getId());
		reportViewVO.setCode(reportlist.getCode());
		reportViewVO.setName(reportlist.getName());
		reportViewVO.setRunway(reportlist.getRunway());
		reportViewVO.setEditTime(reportlist.getEditTime());
		reportViewVO.setGrpCode(reportlist.getGrpCode());
		reportDesignVO.setId(reportlist.getId());
		reportDesignVO.setCode(reportlist.getCode());
		reportDesignVO.setName(reportlist.getName());
		reportDesignVO.setEditTime(reportlist.getEditTime());
		Long timeLong = 0l;
		if (reportlist.getEditTime() != null) {
			timeLong = reportlist.getEditTime().getTime();
		}
		reportDesignVO.setUpdateTime(timeLong);
		reportViewVO.setUpdateTime(timeLong);
		if (reportTableList == null || reportTableList.size() <= 0) {
			return ReportResultMessage.BASEREPORTDATAEMPTY;
		}
		// 参数解析
		HashMap<String, ViewParameterVO> inputParam = new HashMap<String, ViewParameterVO>();
		for (ReportTable reportTable : reportTableList) {
			List<ReportInputVO> tableParam = new ArrayList<ReportInputVO>();
			String sql = reportTable.getSqlText();
			// 存在参数
			List<String> paramStrs = analysiSql(sql);
			int index = 0;
			for (String parStr : paramStrs) {
				// sql替换参数,替换成可以执行的参数
				sql = sql.replace(parStr, "?");
				String inputName = parStr.replace(PARAM_PREFIX, "").replace(PARAM_SUFFIX, "").trim();
				ReportInputVO paramVO = null;
				// 系统参数
				if (judgeIsSystem(inputName)) {
					paramVO = new ReportInputVO();
					ReportSystemParameter systemPar = ReportSystemParameter.parse(inputName);
					paramVO.setCaption(systemPar.getTitle());
					paramVO.setName(systemPar.getName());
					paramVO.setDataType(ParamDataType.TYPE_STRING.getCode().toString());
					paramVO.setSystem(true);
				} else {
					ReportInput parameter = reportDao.findReportInput(id, inputName);
					if (parameter == null) {
						return ReportResultMessage.INPUTISNOTEXIST;
					}
					paramVO = new ReportInputVO();
					paramVO.setSystem(false);
					BeanUtils.copyProperties(parameter, paramVO);
					if (!inputParam.containsKey(paramVO.getName())) {
						String defType = "";
						InputDataType inputType = InputDataType.parse(parameter.getDataType());
						if (inputType == InputDataType.TYPE_MONTH || inputType == InputDataType.TYPE_DAY
								|| inputType == InputDataType.TYPE_YEAR) {
							defType = parameter.getDefType();
						}
						String defValue = parameter.getDefValue();
						if (defValue == null) {
							defValue = "";
						}
						inputParam.put(paramVO.getName(),
								new ViewParameterVO(parameter.getName(), parameter.getCaption(),
										parameter.getDataType(), defValue, parameter.getSeqNum(),
										parameter.getInputType(), parameter.getOptionList(), defType));
					}
				}
				paramVO.setSeqNum(index);
				tableParam.add(paramVO);
				index = index + 1;
			}
			ReportTableDesignBO table = new ReportTableDesignBO();
			table.setName(reportTable.getName());
			table.setSeqNum(reportTable.getSeqNum());
			table.setSqlText(sql);
			table.setReportInputs(tableParam);
			reportTables.add(table);
		}
		List<ViewParameterVO> inputs = new ArrayList<ViewParameterVO>();
		inputs.addAll(inputParam.values());
		if (inputs.size() > 0) {
			inputs.sort(new Comparator<ViewParameterVO>() {
				public int compare(ViewParameterVO arg0, ViewParameterVO arg1) {
					return arg0.getSeqNum().compareTo(arg1.getSeqNum());
				}
			});
		}
		reportViewVO.setInputParams(inputs);
		ReportStyle style = reportDao.findReportStyle(id, scene);
		if (style != null && !StringUtils.isEmpty(style.getFileUrl())) {
			reportViewVO.setChart(style.getChart());
			reportViewVO.setFileUrl(getReportStyleUrl(id, scene));
		}
		reportDesignVO.setReportTables(reportTables);
		ReportViewCache.put(ApplicationCacheUtils.getCloudId(), id, reportViewVO);
		ReportDesignCache.put(ApplicationCacheUtils.getCloudId(), id, reportDesignVO);
		return "";
	}

	/**
	 * 得到报表样式文件访问URL
	 * 
	 * @param reportId
	 * @param scene
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年6月13日 下午12:50:15
	 */
	private String getReportStyleUrl(String reportId, Integer scene) {
		// String fileUrl = ReportStyleUrlCache.get(ApplicationCacheUtils.getCloudId(), reportId,
		// scene);
		// if (StringUtils.isEmpty(fileUrl)) {
		String fileUrl = "";
		ReportStyle style = reportDao.findReportStyle(reportId, scene);
		if (style != null && !StringUtils.isEmpty(style.getFileUrl())) {
			FileUrlResult fileResult = fileService.getVisitFileUrl(FileTypeEnum.Report, style.getFileUrl());
			if (fileResult != null && fileResult.getOne() != null) {
				fileUrl = fileResult.getOne();
				// ReportStyleUrlCache.put(ApplicationCacheUtils.getCloudId(), reportId, scene,
				// fileUrl);
			}
		}
		// }
		// }
		return fileUrl;
	}

	/**
	 * 解析sql,主要是参数解析
	 * 
	 * @param sql
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月6日 下午4:00:08
	 */
	private LinkedList<String> analysiSql(String sql) {
		LinkedList<String> paramStrs = new LinkedList<String>();
		if (sql.contains(PARAM_PREFIX) && sql.contains(PARAM_SUFFIX)) {
			String[] sqlSplits = sql.split("\\" + PARAM_PREFIX);
			for (int i = 0; i < sqlSplits.length; i++) {
				String sqlStr = sqlSplits[i];
				if (sqlStr.contains(PARAM_SUFFIX)) {
					Integer index = sqlStr.indexOf(PARAM_SUFFIX);
					String parStr = sqlStr.substring(0, index + PARAM_SUFFIX.length());
					parStr = PARAM_PREFIX + parStr;
					paramStrs.add(parStr);
				}
			}
		}
		return paramStrs;
	}

	/**
	 * 判断是否为系统内置参数
	 * 
	 * @param inputName
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月3日 下午8:32:15
	 */
	private Boolean judgeIsSystem(String inputName) {
		for (ReportSystemParameter s : ReportSystemParameter.values()) {
			if (s.getName().equals(inputName))
				return true;
		}
		return false;
	}

	private void removeCache(String reportId) {
		ReportDesignCache.remove(ApplicationCacheUtils.getCloudId(), reportId);
		ReportViewCache.remove(ApplicationCacheUtils.getCloudId(), reportId);
	}

	// #start 报表开放记录

	public Result saveReportOpenTo(ReportOpenTo reportOprnTo) {
		if (StringUtils.isEmpty(reportOprnTo.getId())) {
			reportOprnTo.setId(StringUtils.getUUID());
		}
		if (StringUtils.isEmpty(reportOprnTo.getOrgTreeId())) {
			reportOprnTo.setOrgTreeId(null);
		}
		CurrentLoginInfo loginInfo = tokenService.getCurrentLoginInfo();
		if (loginInfo != null) {
			reportOprnTo.setGranter(loginInfo.getStaffName());
		}
		reportOprnTo.setGrantTime(new Date());
		reportDao.saveReportOpenTo(reportOprnTo);
		return Result.success();
	}

	public Result deleteReportOpenTo(String id) {
		reportDao.deleteReportOpenTo(id);
		return Result.success();
	}

	public BaseResult<ReportOpenTo> getReportOpenToList(String rptId) {
		BaseResult<ReportOpenTo> result = new BaseResult<>();
		List<ReportOpenTo> reportOpenToList = reportDao.findReportOpenToList(rptId);
		for (ReportOpenTo reportOpenTo : reportOpenToList) {
			reportOpenTo.setOrgTreeName(getOrgName(reportOpenTo.getOrgTreeId(), true));
		}
		result.success(reportOpenToList);
		return result;
	}

	// #end 报表开放记录
}