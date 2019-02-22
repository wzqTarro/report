/**
 * Zcareze Inc.
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.zcareze.report.data.service.result;

import com.zcareze.commons.result.BaseResult;
import com.zcareze.report.data.service.vo.QueryInputValueVO;

/**
 *                       
 * @Filename QueryInputValueResult.java
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
 * <li>Date: 2017年5月3日</li>
 * <li>Version: 1.0</li>
 * <li>Content: create</li>
 *
 */
public class QueryInputValueResult extends BaseResult<QueryInputValueVO>{

	/**
	 * 默认值
	 */
	private String defValue;	

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	
}
