/**
 * zcareze Inc.
 * Copyright (c) 2016 All Rights Reserved.
 */
package com.zcareze.report.data.dao;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.zcareze.data.datatable.DataTable;
import com.zcareze.data.exception.DataSourceException;
import com.zcareze.data.service.DataOdsService;
import com.zcareze.report.common.ReportParameterValue;

/**
 * 报表数据访问
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
@Repository(value = "dataReportDao")
public class DataReportDao extends DataOdsService {
	public static final Logger LOGGER = LoggerFactory.getLogger(DataReportDao.class);

	/**
	 * 获取指定sql的数据
	 * 
	 * @param sql
	 *            sql语句
	 * @param paramValues
	 *            sql对于的参数和值
	 * @return 封装成xml格式的字符串数据
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年4月10日 下午9:53:43
	 */
	public DataTable GetReportData(String sql, List<ReportParameterValue> paramValues) {
		List<Object> mapObj = new LinkedList<Object>();
		if (paramValues != null && paramValues.size() > 0) {
			paramValues.sort(new Comparator<ReportParameterValue>() {
				public int compare(ReportParameterValue arg0, ReportParameterValue arg1) {
					return arg0.getSeqNO().compareTo(arg1.getSeqNO());
				}
			});
			for (ReportParameterValue reportParameterValue : paramValues) {
				try {
					Object value = convertDataType(reportParameterValue);
					mapObj.add(value);
				} catch (Exception ex) {
					throw new DataSourceException("参数转换出错", ex);
				}
			}
		}
		return excuteSql(sql, mapObj.toArray());
	}

	/**
	 * 处理参数值的类型转换
	 * 
	 * @param reportParameterValue
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年4月12日 下午4:24:31
	 * @throws Exception
	 */
	private Object convertDataType(ReportParameterValue reportParameterValue) throws Exception {
		Object value = null;
		if (reportParameterValue.getValue() == null || StringUtils.isEmpty(reportParameterValue.getValue())) {
			return "";
		}
		value = reportParameterValue.getValue();
		switch (reportParameterValue.getDataType()) {
//			case TYPE_DATE_DAY :
//				value = DateUtil.con.convertStringToDate("yyyyMMdd", value.toString());
//				break;
//			case TYPE_DATE_MONTH :
//				value = DateUtil.convertStringToDate("yyyyMM", value.toString());
//				break;
//			case TYPE_DATE_YEAR :
//				value = DateUtil.convertStringToDate("yyyy", value.toString());
//				break;
			case TYPE_NUMBER :
				value =new BigDecimal(value.toString());
				break;
			default :
				value = value.toString();
				break;
		}
		return value;
	}		
}