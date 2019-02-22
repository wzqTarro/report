/**
 * zcareze Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.zcareze.report.data.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcareze.commons.cache.ApplicationCacheUtils;
import com.zcareze.commons.result.BaseResult;
import com.zcareze.commons.result.ResultEnum;
import com.zcareze.commons.utils.DateUtil;
import com.zcareze.commons.utils.StringUtils;
import com.zcareze.commons.utils.ValidatorUtil;
import com.zcareze.data.datatable.DataColumn;
import com.zcareze.data.datatable.DataRow;
import com.zcareze.data.datatable.DataTable;
import com.zcareze.domain.regional.OrgList;
import com.zcareze.kpi.service.KpiService;
import com.zcareze.kpi.service.result.KpiRptInputResult;
import com.zcareze.kpi.service.vo.KpiRptInputVO;
import com.zcareze.regional.service.OrgService;
import com.zcareze.regional.service.result.OrgListResult;
import com.zcareze.report.base.service.BaseReportService;
import com.zcareze.report.base.service.bo.QueryInputDesignBO;
import com.zcareze.report.base.service.bo.ReportDesignBO;
import com.zcareze.report.base.service.bo.ReportTableDesignBO;
import com.zcareze.report.base.service.enst.QueryInputDefType;
import com.zcareze.report.base.service.result.QueryInputDesignResult;
import com.zcareze.report.base.service.result.ReportDesignResult;
import com.zcareze.report.base.service.vo.ReportInputVO;
import com.zcareze.report.common.ReportParameterValue;
import com.zcareze.report.common.enst.ParamDataType;
import com.zcareze.report.data.dao.DataReportDao;
import com.zcareze.report.data.enst.ReportResultMessage;
import com.zcareze.report.data.enst.ReportXmlConstant;
import com.zcareze.report.data.service.DataReportService;
import com.zcareze.report.data.service.cache.DataReportCache;
import com.zcareze.report.data.service.param.KpiReportParam;
import com.zcareze.report.data.service.param.ReportViewParam;
import com.zcareze.report.data.service.param.ScreenReportParam;
import com.zcareze.report.data.service.result.QueryInputValueResult;
import com.zcareze.report.data.service.result.ReportDataResult;
import com.zcareze.report.data.service.vo.QueryInputValueVO;
import com.zcareze.report.data.service.vo.ReportDataVO;
import com.zcareze.report.data.service.vo.ReportParamValue;
import com.zcareze.report.data.util.ReportInputValueUtils;
import com.zcareze.report.data.util.SystemParameterValueUtils;

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
@Service("baseHdocService")
public class DataReportServiceImpl implements DataReportService {
	public static final Logger LOGGER = LoggerFactory.getLogger(DataReportServiceImpl.class);

	@Autowired
	private DataReportDao dataReportDao;
	@Autowired
	private BaseReportService baseReportService;
	@Autowired
	private KpiService kpiService;
	@Autowired
	private OrgService orgService;

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
	@Override
	public QueryInputValueResult getReportQueryInputValue(String reportId, String name) {
		QueryInputValueResult result = new QueryInputValueResult();
		QueryInputDesignResult reportInputResult = baseReportService.getQueryReportInput(reportId, name);
		QueryInputDesignBO queryInputDesion = reportInputResult.getOne();
		if (queryInputDesion == null) {
			result.setCode(reportInputResult.getCode());
			result.setMessage(reportInputResult.getMessage());
			result.setErrorCode(reportInputResult.getErrorCode());
			return result;
		}
		ReportInputVO reportInput = queryInputDesion.getReportInput();
		if (reportInput == null) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.QUERYINPUTDATAERROR);
			return result;
		}
		String defValue = reportInput.getDefValue();
		QueryInputDefType defType = QueryInputDefType.parse(reportInput.getDefType());
		if (defType != null) {
			ReportParamValue paramValue = ReportInputValueUtils.getValue(reportInput);
			if (paramValue != null) {
				defValue = paramValue.getValue();
			}
		}
		if (defValue == null) {
			defValue = "";
		}
		result.setDefValue(defValue);
		List<ReportParameterValue> paramValues = new ArrayList<ReportParameterValue>();
		Integer seqNO = 1;
		for (int i = 0; i < queryInputDesion.getParameters().size(); i++) {
			String inputName = queryInputDesion.getParameters().get(i);
			String value = "";
			ReportParamValue paramValue = SystemParameterValueUtils.getValue(inputName);
			if (paramValue != null) {
				value = paramValue.getValue();
			}
			ReportParameterValue parValue = new ReportParameterValue();
			parValue.setSeqNO(seqNO);
			parValue.setCode(inputName);
			parValue.setDataType(ParamDataType.TYPE_STRING);
			parValue.setValue(value);
			paramValues.add(parValue);
			seqNO = seqNO + 1;
		}
		DataTable dataTable = dataReportDao.GetReportData(reportInput.getSqlText(), paramValues);
		if (dataTable == null || !dataTable.getColumns().contains(ReportXmlConstant.QUERYINPUT_VALUE)
				|| !dataTable.getColumns().contains(ReportXmlConstant.QUERYINPUT_TITLE)) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.QUERYINPUTDATAERROR);
			return result;
		}
		List<QueryInputValueVO> queryInputValue = new LinkedList<QueryInputValueVO>();
		for (DataRow dr : dataTable.getRows()) {
			String colValue = "";
			String colTitle = "";
			Object objValue = dr.getValue(ReportXmlConstant.QUERYINPUT_VALUE);
			if (objValue != null) {
				colValue = objValue.toString();
			}
			Object objTitle = dr.getValue(ReportXmlConstant.QUERYINPUT_TITLE);
			if (objTitle != null) {
				colTitle = objTitle.toString();
			}
			queryInputValue.add(new QueryInputValueVO(colValue, colTitle));
		}
		result.success(queryInputValue);
		return result;
	}

	/**
	 * 获取指定报表的数据
	 * 
	 * @param reportParam
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月6日 下午4:46:42
	 */
	@Override
	public ReportDataResult getReportData(ReportViewParam reportParam) {
		ReportDataResult result = new ReportDataResult();
		if (reportParam == null || ValidatorUtil.isEmpty(reportParam.getReportId())) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.REPORTPARAMEMPTY);
			return result;
		}
		String reportId = reportParam.getReportId();
		// 得到报表设计信息
		ReportDesignBO reportDesign = getReportDesignInfo(result, reportId);
		if (reportDesign == null || reportDesign.getReportTables() == null
				|| reportDesign.getReportTables().size() == 0) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.REPORTDATAEMPTY);
			return result;
		}
		String xmlData = getReportData(reportParam.getParamValues(), reportDesign, null, false);
		ReportDataVO dataVO = new ReportDataVO();
		dataVO.setXmlData(xmlData);
		result.success(dataVO);
		return result;
	}

	/**
	 * 获取指定大屏的所有数据表的结构
	 * 
	 * @param reportParam
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年11月9日 下午5:54:28
	 */
	public ReportDataResult getScreenReportStruct(String reportId) {
		ReportDataResult result = new ReportDataResult();
		// 得到报表设计信息
		ReportDesignBO reportDesign = getReportDesignInfo(result, reportId);
		if (reportDesign == null || reportDesign.getReportTables() == null
				|| reportDesign.getReportTables().size() == 0) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.REPORTDATAEMPTY);
			return result;
		}
		String xmlData = getReportData(null, reportDesign, null, true);
		ReportDataVO dataVO = new ReportDataVO();
		dataVO.setXmlData(xmlData);
		result.success(dataVO);
		return result;
	}

	@Override
	public ReportDataResult getScreenReportData(ScreenReportParam reportParam) {
		ReportDataResult result = new ReportDataResult();
		if (reportParam == null || ValidatorUtil.isEmpty(reportParam.getReportId())) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.REPORTPARAMEMPTY);
			return result;
		}
		String reportId = reportParam.getReportId();
		// 得到报表设计信息
		ReportDesignBO reportDesign = getReportDesignInfo(result, reportId);
		if (reportDesign == null || reportDesign.getReportTables() == null
				|| reportDesign.getReportTables().size() == 0) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.REPORTDATAEMPTY);
			return result;
		}
		String xmlData = getReportData(reportParam.getParamValues(), reportDesign, reportParam.getTableNames(), false);
		ReportDataVO dataVO = new ReportDataVO();
		dataVO.setXmlData(xmlData);
		result.success(dataVO);
		return result;
	}

	private String getReportData(List<ReportParamValue> InputParamValue, ReportDesignBO reportDesign,
			List<String> tableNames, Boolean fetchStruct) {
		List<DataTable> dataSource = new ArrayList<DataTable>();
		List<ReportParameterValue> allInputs = new LinkedList<ReportParameterValue>();
		List<String> parNames = new ArrayList<String>();
		for (ReportTableDesignBO reportTable : reportDesign.getReportTables()) {
			if (tableNames != null && tableNames.size() > 0) {
				if (!tableNames.contains(reportTable.getName())) {
					continue;
				}
			}
			// 处理参数和参数值
			List<ReportParameterValue> paramValues = new ArrayList<ReportParameterValue>();
			int seqNO = 1;
			if (reportTable.getReportInputs() != null && reportTable.getReportInputs().size() > 0) {
				for (int i = 0; i < reportTable.getReportInputs().size(); i++) {
					ReportInputVO paramVO = reportTable.getReportInputs().get(i);
					ReportParamValue paramValue = null;
					// 从输入参数中获取
					if (InputParamValue != null && InputParamValue.size() > 0) {
						for (ReportParamValue reportParameterValue : InputParamValue) {
							if (reportParameterValue != null
									&& paramVO.getName().equals(reportParameterValue.getName())) {
								paramValue = reportParameterValue;
								break;
							}
						}
					}
					// 取默认值
					if (paramValue == null) {
						paramValue = ReportInputValueUtils.getValue(paramVO);
					}
					if (paramValue == null) {
						continue;
					}
					ReportParameterValue parValue = new ReportParameterValue();
					parValue.setSeqNO(seqNO);
					parValue.setCode(paramVO.getName());
					parValue.setName(paramVO.getCaption());
					parValue.setDataType(ParamDataType.parse(paramVO.getDataType()));
					parValue.setValue(paramValue.getValue());
					if (StringUtils.isEmpty(paramValue.getTitle())) {
						parValue.setTitle(paramValue.getValue());
					} else {
						parValue.setTitle(paramValue.getTitle());
					}
					paramValues.add(parValue);
					if (!parNames.contains(paramVO.getName())) {
						allInputs.add(parValue);
						parNames.add(paramVO.getName());
					}
					seqNO = seqNO + 1;
				}
			}
			// 获取报表数据
			DataTable dataTable = dataReportDao.GetReportData(reportTable.getSqlText(), paramValues);
			dataTable.setTableName(reportTable.getName());
			dataSource.add(dataTable);
		}
		String xmlData = "";
		// 得到数据xml并封装到VO中
		if (fetchStruct) {
			xmlData = toStructXml(allInputs, dataSource);
		} else {
			xmlData = toDataXml(allInputs, dataSource);
		}
		return xmlData;
	}

	/**
	 * 获取指定报表的数据(用于指标对应报表查看)
	 * 
	 * @param kpiReportParam
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月24日 上午11:55:23
	 */
	public ReportDataResult getKpiReportData(KpiReportParam kpiReportParam) {
		ReportDataResult result = new ReportDataResult();
		if (kpiReportParam == null || ValidatorUtil.isEmpty(kpiReportParam.getKpiId(), kpiReportParam.getReportId())) {
			result.failure(ResultEnum.PARAM_ERROR, ReportResultMessage.REPORTPARAMEMPTY);
			return result;
		}
		KpiRptInputResult kpiInputResult = kpiService.getKpiRptInputs(kpiReportParam.getKpiId(),
				kpiReportParam.getReportId());
		if (kpiInputResult == null || kpiInputResult.getCode() != ResultEnum.SUCCESS.getCode()) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.KPIREPORTERROR);
			return result;
		}
		List<KpiRptInputVO> kpiRptInpus = kpiInputResult.getLists();
		List<ReportParamValue> paramValues = new LinkedList<ReportParamValue>();
		List<String> parIds = new ArrayList<String>();
		for (KpiRptInputVO kpiRptInputVO : kpiRptInpus) {
			String parValue = "";
			String parTitle = "";
			switch (kpiRptInputVO.getValLet()) {
				case 1 :
					if (!StringUtils.isEmpty(kpiReportParam.getCycValue())) {
						parValue = kpiReportParam.getCycValue();						
						parTitle = parValue;
					}
					break;
				case 2 :
					if (!StringUtils.isEmpty(kpiReportParam.getOrgId())) {
						parValue = kpiReportParam.getOrgId();
						OrgListResult orglistResult = orgService.getOrgListById(kpiReportParam.getOrgId());
						if (orglistResult != null && orglistResult.getOne() != null) {
							OrgList orglist = orglistResult.getOne();
							parTitle = orglist.getName();
						}
					}
					break;
				default :
					continue;
			}
			if (!parIds.contains(kpiRptInputVO.getName())) {
				parIds.add(kpiRptInputVO.getName());
				ReportParamValue par = new ReportParamValue();
				par.setName(kpiRptInputVO.getName());
				par.setTitle(parTitle);
				par.setValue(parValue);
				paramValues.add(par);
			}
		}
		// 得到报表设计信息
		ReportDesignBO reportDesign = getReportDesignInfo(result, kpiReportParam.getReportId());
		if (reportDesign == null || reportDesign.getReportTables() == null
				|| reportDesign.getReportTables().size() == 0) {
			result.failure(ResultEnum.DATA_ERROR, ReportResultMessage.REPORTDATAEMPTY);
			return result;
		}
		String xmlData = getReportData(paramValues, reportDesign, null, false);
		ReportDataVO dataVO = new ReportDataVO();
		dataVO.setXmlData(xmlData);
		result.success(dataVO);
		return result;
	}

	/**
	 * 获取报表设计信息 缓存版本对比，处理缓存
	 * 
	 * @param result
	 * @param reportId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月11日 上午11:55:12
	 */
	private ReportDesignBO getReportDesignInfo(ReportDataResult result, String reportId) {
		String cloudId = ApplicationCacheUtils.getCloudId();
		ReportDesignBO reportDesign = DataReportCache.get(cloudId, reportId);
		if (reportDesign != null) {
			// 获取最后更新时间
			BaseResult<Date> updateTimeResult = baseReportService.getReportListUpdateTime(reportId);
			Date updateTime = updateTimeResult.getOne();
			// 比较本地版本和最新版本是否一致
			if (updateTime != null && DateUtil.getTimeInmilis(updateTime, reportDesign.getEditTime()) == 0) {
				return reportDesign;
			}
			DataReportCache.remove(cloudId, reportId);
		}
		ReportDesignResult reportDesignResult = baseReportService.getReportDesignById(reportId);
		if (reportDesignResult.getOne() == null) {
			return null;
		}
		reportDesign = reportDesignResult.getOne();
		DataReportCache.put(cloudId, reportId, reportDesign);
		return reportDesign;
	}

	private String toDataXml(List<ReportParameterValue> allInputs, List<DataTable> dataSource) {
		Element rootNode = DocumentHelper.createElement(ReportXmlConstant.XML_ROOTNODE);
		Document document = DocumentHelper.createDocument(rootNode);
		// 参数
		Element parameterNode = rootNode.addElement(ReportXmlConstant.XML_PARAMETER);
		for (ReportParameterValue reportInput : allInputs) {
			Element paramRecordNode = parameterNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD);
			Element paramNameNode = paramRecordNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD_NAME);
			if (StringUtils.isNotEmpty(reportInput.getCode())) {
				paramNameNode.setText(reportInput.getCode());
			}
			Element paramValueNode = paramRecordNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD_VALUE);
			if (reportInput.getValue() != null) {
				paramValueNode.setText(reportInput.getValue().toString());
			}
			Element paramTitleNode = paramRecordNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD_TITLE);
			if (StringUtils.isNotEmpty(reportInput.getTitle())) {
				paramTitleNode.setText(reportInput.getTitle());
			}
		}
		// 数据表
		Integer seqNum = 1;
		for (DataTable dt : dataSource) {
			String dataNodeName = dt.getTableName();
			Element dataNode = rootNode.addElement(ReportXmlConstant.XML_TABLE);
			dataNode.addAttribute(ReportXmlConstant.XML_TABLE_NAME, dataNodeName);
			dataNode.addAttribute(ReportXmlConstant.XML_TABLE_SEQNUM, seqNum.toString());
			seqNum = seqNum + 1;
			for (int i = 0; i < dt.getRows().size(); i++) {
				Element dataRecordNode = dataNode.addElement(ReportXmlConstant.XML_TABLE_RECORD);
				DataRow dr = dt.getRows().get(i);
				for (DataColumn col : dt.getColumns()) {
					Element colNode = dataRecordNode.addElement(col.getColumnName());
					Object val = dr.getValue(col.getColumnName());
					if (val != null) {
						colNode.setText(val.toString());
					}
				}
			}
		}
		return document.asXML();
	}

	private String toStructXml(List<ReportParameterValue> allInputs, List<DataTable> dataSource) {
		Element rootNode = DocumentHelper.createElement(ReportXmlConstant.XML_ROOTNODE);
		Document document = DocumentHelper.createDocument(rootNode);
		// 参数
		Element parameterNode = rootNode.addElement(ReportXmlConstant.XML_PARAMETER);
		for (ReportParameterValue reportInput : allInputs) {
			Element paramRecordNode = parameterNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD);
			Element paramNameNode = paramRecordNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD_NAME);
			if (StringUtils.isNotEmpty(reportInput.getCode())) {
				paramNameNode.setText(reportInput.getCode());
			}
			Element paramValueNode = paramRecordNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD_VALUE);
			if (reportInput.getValue() != null) {
				paramValueNode.setText(reportInput.getValue().toString());
			}
			Element paramTitleNode = paramRecordNode.addElement(ReportXmlConstant.XML_PARAMETER_RECORD_TITLE);
			if (StringUtils.isNotEmpty(reportInput.getTitle())) {
				paramTitleNode.setText(reportInput.getTitle());
			}
		}
		// 数据表
		for (DataTable dt : dataSource) {
			String dataNodeName = dt.getTableName();
			Element dataNode = rootNode.addElement(ReportXmlConstant.XML_TABLE);
			Element dataNameNode = dataNode.addElement(ReportXmlConstant.XML_TABLE_NAME);
			dataNameNode.setText(dataNodeName);
			Element colsNode = dataNode.addElement(ReportXmlConstant.XML_TABLE_COLUMNS);
			for (DataColumn col : dt.getColumns()) {
				Element colNode = colsNode.addElement(ReportXmlConstant.XML_TABLE_COLUMN);
				colNode.setText(col.getColumnName());
			}
		}
		return document.asXML();
	}
}