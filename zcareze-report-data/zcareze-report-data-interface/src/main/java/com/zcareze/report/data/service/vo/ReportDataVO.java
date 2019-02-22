package com.zcareze.report.data.service.vo;

import java.io.Serializable;

/**
 *报表数据内容 
 *                       
 * @Filename ReportDataVO.java
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
 * <li>Author: 虾米</li>
 * <li>Date: 2017年4月10日</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 *
 */
public class ReportDataVO implements Serializable{

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -7382437853754358821L;
	
	private String xmlData;

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}
}
