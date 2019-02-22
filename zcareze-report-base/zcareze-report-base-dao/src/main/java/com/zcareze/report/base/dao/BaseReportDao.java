/**
 * zcareze Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.zcareze.report.base.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcareze.commons.utils.DateUtil;
import com.zcareze.commons.utils.StringUtils;
import com.zcareze.data.dao.ZcarezeBaseDao;
import com.zcareze.report.base.domain.ReportGrantTo;
import com.zcareze.report.base.domain.ReportGrantToManage;
import com.zcareze.report.base.domain.ReportGroup;
import com.zcareze.report.base.domain.ReportInput;
import com.zcareze.report.base.domain.ReportList;
import com.zcareze.report.base.domain.ReportOpenTo;
import com.zcareze.report.base.domain.ReportStyle;
import com.zcareze.report.base.domain.ReportTable;
import com.zcareze.report.base.domain.ReportUsually;

/**
 * 报表基础信息数据访问
 * 
 * @Filename ReportDao.java
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
@Repository(value = "baseReportDao")
public class BaseReportDao extends ZcarezeBaseDao {

	/**
	 * 新增报表分组
	 * 
	 * @param reportGroup
	 *            <p>
	 *            说明：
	 *            </p>
	 * @author 虾米 by 2017年5月2日 下午5:21:25
	 */
	public void addReportGroup(ReportGroup reportGroup) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pcode", reportGroup.getCode());
		mapObj.put("pname", reportGroup.getName());
		mapObj.put("pcomment", reportGroup.getComment());
		mapObj.put("pcolor", reportGroup.getColor());
		excuteStore("addReportGroup", mapObj);
	}

	/**
	 * 修改指定的报表分组
	 * 
	 * @param reportGroup
	 *            <p>
	 *            说明：
	 *            </p>
	 * @author 虾米 by 2017年5月2日 下午5:21:35
	 */
	public void editReportGroup(ReportGroup reportGroup) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pcode", reportGroup.getCode());
		mapObj.put("pname", reportGroup.getName());
		mapObj.put("pcomment", reportGroup.getComment());
		mapObj.put("pcolor", reportGroup.getColor());
		excuteStore("editReportGroup", mapObj);
	}

	/**
	 * 删除指定的报表分组
	 * 
	 * @param code
	 *            <p>
	 *            说明：
	 *            </p>
	 * @author 虾米 by 2017年5月2日 下午5:21:46
	 */
	public void deleteReportGroup(String code) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pcode", code);
		excuteStore("deleteReportGroup", mapObj);
	}

	public List<ReportGroup> findReportGroupList(Integer pageStart, Integer pageSize) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("ppageStart", pageStart);
		mapObj.put("ppageSize", pageSize);
		return excuteStoreListQuery(ReportGroup.class, "findReportGroupList", mapObj);
	}

	/**
	 * 查找指定的报表分组信息
	 * 
	 * @param code
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月2日 下午5:22:00
	 */
	public ReportGroup findReportGroupByCode(String code) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pcode", code);
		return excuteStoreQuery(ReportGroup.class, "findReportGroupByCode", mapObj);
	}

	public void addReportList(ReportList reportList) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", reportList.getId());
		mapObj.put("pcode", reportList.getCode());
		mapObj.put("pname", reportList.getName());
		mapObj.put("pgrpCode", reportList.getGrpCode());
		mapObj.put("prunway", reportList.getRunway());
		mapObj.put("peditTime", reportList.getEditTime());
		mapObj.put("peditorId", reportList.getEditorId());
		mapObj.put("peditorName", reportList.getEditorName());
		mapObj.put("pcomment", reportList.getComment());
		excuteStore("addReportList", mapObj);
	}

	public void editReportList(ReportList reportList) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", reportList.getId());
		mapObj.put("pcode", reportList.getCode());
		mapObj.put("pname", reportList.getName());
		mapObj.put("pgrpCode", reportList.getGrpCode());
		mapObj.put("prunway", reportList.getRunway());
		mapObj.put("peditTime", reportList.getEditTime());
		mapObj.put("peditorId", reportList.getEditorId());
		mapObj.put("peditorName", reportList.getEditorName());
		mapObj.put("pcomment", reportList.getComment());
		excuteStore("editReportList", mapObj);
	}

	public void deleteReportList(String id) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", id);
		excuteStore("deleteReportList", mapObj);
	}

	public void updateReportListEditInfo(String id, Date eidtTime, String editorId, String editorName) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", id);
		mapObj.put("peditTime", eidtTime);
		mapObj.put("peditorId", editorId);
		mapObj.put("peditorName", editorName);
		excuteStore("updateReportListEditInfo", mapObj);
	}

	public void addReportInput(ReportInput reportInput) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportInput.getRptId());
		mapObj.put("pname", reportInput.getName());
		mapObj.put("pcaption", reportInput.getCaption());
		mapObj.put("pseqNum", reportInput.getSeqNum());
		mapObj.put("pdataType", reportInput.getDataType());
		mapObj.put("pinputType", reportInput.getInputType());
		mapObj.put("poptionList", reportInput.getOptionList());
		mapObj.put("psqlText", reportInput.getSqlText());
		mapObj.put("pdefValue", reportInput.getDefValue());
		mapObj.put("pdefType", reportInput.getDefType());
		excuteStore("addReportInput", mapObj);
	}

	public void editReportInput(ReportInput reportInput) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportInput.getRptId());
		mapObj.put("pname", reportInput.getName());
		mapObj.put("pcaption", reportInput.getCaption());
		mapObj.put("pseqNum", reportInput.getSeqNum());
		mapObj.put("pdataType", reportInput.getDataType());
		mapObj.put("pinputType", reportInput.getInputType());
		mapObj.put("poptionList", reportInput.getOptionList());
		mapObj.put("psqlText", reportInput.getSqlText());
		mapObj.put("pdefValue", reportInput.getDefValue());
		mapObj.put("pdefType", reportInput.getDefType());
		excuteStore("editReportInput", mapObj);
	}

	public void deleteReportInput(String reportId, String name) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pname", name);
		excuteStore("deleteReportInput", mapObj);
	}

	public void addReportTable(ReportTable reportTable) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportTable.getRptId());
		mapObj.put("pname", reportTable.getName());
		mapObj.put("psqlText", reportTable.getSqlText());
		mapObj.put("pseqNum", reportTable.getSeqNum());
		excuteStore("addReportTable", mapObj);
	}

	public void editReportTable(ReportTable reportTable) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportTable.getRptId());
		mapObj.put("pname", reportTable.getName());
		mapObj.put("psqlText", reportTable.getSqlText());
		mapObj.put("pseqNum", reportTable.getSeqNum());
		excuteStore("editReportTable", mapObj);
	}

	public void deleteReportTable(String reportId, String name) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pname", name);
		excuteStore("deleteReportTable", mapObj);
	}

	public void addReportStyle(ReportStyle reportStyle) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportStyle.getRptId());
		mapObj.put("pscene", reportStyle.getScene());
		mapObj.put("pfileUrl", reportStyle.getFileUrl());
		mapObj.put("pcomment", reportStyle.getComment());
		mapObj.put("pchart", reportStyle.getChart());
		excuteStore("addReportStyle", mapObj);
	}

	public void editReportStyle(ReportStyle reportStyle) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportStyle.getRptId());
		mapObj.put("pscene", reportStyle.getScene());
		mapObj.put("pfileUrl", reportStyle.getFileUrl());
		mapObj.put("pcomment", reportStyle.getComment());
		mapObj.put("pchart", reportStyle.getChart());
		excuteStore("editReportStyle", mapObj);
	}

	public void deleteReportStyle(String reportId, Integer scene) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pscene", scene);
		excuteStore("deleteReportStyle", mapObj);
	}

	public void addReportGrantTo(ReportGrantTo reportGrant) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportGrant.getRptId());
		mapObj.put("porgId", reportGrant.getOrgId());
		mapObj.put("proles", reportGrant.getRoles());
		mapObj.put("pmanage", reportGrant.getManage());
		mapObj.put("pgranter", reportGrant.getGranter());
		mapObj.put("pgrantTime", reportGrant.getGrantTime());
		excuteStore("addReportGrantTo", mapObj);
	}

	public void deleteReportGrantTo(String reportId, String orgId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("porgId", orgId);
		excuteStore("deleteReportGrantTo", mapObj);
	}

	public List<ReportGrantTo> findReportGrantToByRptId(String reportId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		return excuteStoreListQuery(ReportGrantTo.class, "findReportGrantToByRptId", mapObj);
	}

	public List<ReportGrantTo> findReportGrantToByOrgId(String orgId, String roles) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("porgId", orgId);
		mapObj.put("proles", roles);
		return excuteStoreListQuery(ReportGrantTo.class, "findReportGrantToByOrgId", mapObj);
	}

	public List<ReportGrantTo> findReportGrantToByStaffId(String staffId, Integer pageStart, Integer pageSize) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pstaffId", staffId);
		mapObj.put("ppageStart", pageStart);
		mapObj.put("ppageSize", pageSize);
		return excuteStoreListQuery(ReportGrantTo.class, "findReportGrantToByStaffId", mapObj);
	}

	public List<ReportGrantTo> findReportGrantToByOrgList(String reportId, String orgIds) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("porgIds", orgIds);
		return excuteStoreListQuery(ReportGrantTo.class, "findReportGrantToByOrgList", mapObj);
	}

	public void addReportUsually(String reportId, String staffId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pstaffId", staffId);
		excuteStore("addReportUsually", mapObj);
	}

	public void deleteReportUsually(String reportId, String staffId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pstaffId", staffId);
		excuteStore("deleteReportUsually", mapObj);
	}

	public void readReportUsually(String reportId, String staffId, Date readTime) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pstaffId", staffId);
		mapObj.put("preadTime", readTime);
		excuteStore("readReportUsually", mapObj);
	}

	public List<ReportUsually> findReportUsuallyByStaffId(String staffId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pstaffId", staffId);
		return excuteStoreListQuery(ReportUsually.class, "findReportUsuallyByStaffId", mapObj);
	}

	public List<ReportInput> findReportInputByRptId(String reportId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		return excuteStoreListQuery(ReportInput.class, "findReportInputByRptId", mapObj);
	}

	public List<ReportStyle> findReportStyleByRptId(String reportId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		return excuteStoreListQuery(ReportStyle.class, "findReportStyleByRptId", mapObj);
	}

	public ReportStyle findReportStyle(String reportId, Integer scene) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pscene", scene);
		return excuteStoreQuery(ReportStyle.class, "findReportStyle", mapObj);
	}

	public ReportInput findReportInput(String reportId, String name) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pname", name);
		return excuteStoreQuery(ReportInput.class, "findReportInput", mapObj);
	}

	public ReportTable findReportTableByName(String reportId, String name) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		mapObj.put("pname", name);
		return excuteStoreQuery(ReportTable.class, "findReportTableByName", mapObj);
	}

	public ReportList findReportListById(String id) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", id);
		return excuteStoreQuery(ReportList.class, "findReportListById", mapObj);
	}

	public ReportList findReportListByUnique(String name, String code) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pname", name);
		mapObj.put("pcode", code);
		return excuteStoreQuery(ReportList.class, "findReportListByUnique", mapObj);
	}

	public List<ReportList> findReportList(String name, String groupCode, Integer pageStart, Integer pageSize) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pname", name);
		mapObj.put("pgroupCode", groupCode);
		mapObj.put("ppageStart", pageStart);
		mapObj.put("ppageSize", pageSize);
		return excuteStoreListQuery(ReportList.class, "findReportList", mapObj);
	}

	public List<ReportList> findScreenReportList(String name) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pname", name);
		return excuteStoreListQuery(ReportList.class, "findScreenReportList", mapObj);
	}

	public List<ReportList> findScreenReportByStaffId(String staffId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pstaffId", staffId);
		return excuteStoreListQuery(ReportList.class, "findScreenReportByStaffId", mapObj);
	}
	
	public List<ReportTable> findReportTableByRptId(String reportId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", reportId);
		return excuteStoreListQuery(ReportTable.class, "findReportTableByRptId", mapObj);
	}

	public List<ReportGrantToManage> findReportGrantManageByOrgId(String orgId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("porgId", orgId);
		return excuteStoreListQuery(ReportGrantToManage.class, "findReportGrantManageByOrgId", mapObj);
	}

	public List<ReportGrantToManage> findReportGrantManageByOrg(String orgId, String roles) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("porgId", orgId);
		mapObj.put("proles", roles);
		return excuteStoreListQuery(ReportGrantToManage.class, "findReportGrantManageByOrg", mapObj);
	}

	public Date findReportListUpdateTime(String id) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", id);
		String updateTime = excuteStoreString("findReportListUpdateTime", mapObj);
		if (StringUtils.isNotEmpty(updateTime)) {
			return DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", updateTime);
		} else {
			return null;
		}
	}

	/**
	 * 检查职员是否对指定报表有权限
	 * 
	 * @param kpiId
	 * @param staffId
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年6月2日 下午2:42:14
	 */
	public Boolean checkReportGrantToByStaffId(String rptId, String staffId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", rptId);
		mapObj.put("pstaffId", staffId);
		return excuteStoreCount("checkReportGrantToByStaffId", mapObj) > 0;
	}

	// #start 报表开放记录

	public void saveReportOpenTo(ReportOpenTo reportOprnTo) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", reportOprnTo.getId());
		mapObj.put("prptId", reportOprnTo.getRptId());
		mapObj.put("porgTreeId", reportOprnTo.getOrgTreeId());
		mapObj.put("porgTreeLayer", reportOprnTo.getOrgTreeLayer());
		mapObj.put("proles", reportOprnTo.getRoles());
		mapObj.put("pgranter", reportOprnTo.getGranter());
		mapObj.put("pgrantTime", reportOprnTo.getGrantTime());
		excuteStore("saveReportOpenTo", mapObj);
	}

	public void deleteReportOpenTo(String id) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("pid", id);
		excuteStore("deleteReportOpenTo", mapObj);
	}

	public List<ReportOpenTo> findReportOpenToList(String rptId) {
		Map<String, Object> mapObj = new HashMap<String, Object>();
		mapObj.put("prptId", rptId);
		return excuteStoreListQuery(ReportOpenTo.class, "findReportOpenToList", mapObj);
	}

	// #end 报表开放记录
}