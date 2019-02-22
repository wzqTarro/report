package com.zcareze.report.base.service.enst;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * @Filename QueryInputDefType.java
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
 *          <li>Date: 2017年5月8日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public enum QueryInputDefType {

	/**
	 * 所有机构
	 */
	ORG_ALL("所有机构", "31"),
	/**
	 * 我的机构
	 */
	ORG_MY("我的机构", "31"),
	/**
	 * 所有科室
	 */
	DEPAMENT_ALL("所有科室", "32"),
	/**
	 * 我的科室
	 */
	DEPAMENT_MY("我的科室", "32"),
	/**
	 * 所有团队
	 */
	TEAM_ALL("所有团队", "33"),
	/**
	 * 我的团队
	 */
	TEAM_MY("我的团队", "33"),
	/**
	 * 本年
	 */
	YEAR_NOW("本年", "21"),
	/**
	 * 上年
	 */
	YEAR_LAST("上年", "21"),
	/**
	 * 上月
	 */
	MONTH_LAST("上月", "22"),
	/**
	 * 本月
	 */
	MONTH_NOW("本月", "22"),
	/**
	 * 下月
	 */
	MONTH_NEXT("下月", "22"),
	/**
	 * 今天
	 */
	DATE_NOW("今天", "23"),
	/**
	 * 昨天
	 */
	DATE_LAST("昨天", "23"),
	/**
	 * 明天
	 */
	DATE_NEXT("明天", "23"),
	/**
	 * 倒七天
	 */
	DATE_LAST_7("倒七天", "23"),
	/**
	 * 进七天
	 */
	DATE_NEXT_7("进七天", "23"),
	/**
	 * 月初
	 */
	DATE_MONTH_FIRST("月初", "23"),
	/**
	 * 月末
	 */
	DATE_MONTH_LAST("月末", "23"),
	/**
	 * 上月今天
	 */
	DATE_LAST_MONTH("上月今天", "23");

	private String name;

	private String dataType;

	private QueryInputDefType(String name, String dataType) {
		this.name = name;
		this.dataType = dataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public static QueryInputDefType parse(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		switch (name) {
			case "所有机构" :
				return ORG_ALL;
			case "我的机构" :
				return ORG_MY;
			case "所有科室" :
				return DEPAMENT_ALL;
			case "我的科室" :
				return DEPAMENT_MY;
			case "所有团队" :
				return TEAM_ALL;
			case "我的团队" :
				return TEAM_MY;
			case "本年" :
				return YEAR_NOW;
			case "上年" :
				return YEAR_LAST;
			case "上月" :
				return MONTH_LAST;
			case "本月" :
				return MONTH_NOW;
			case "下月" :
				return MONTH_NEXT;
			case "今天" :
				return DATE_NOW;
			case "昨天" :
				return DATE_LAST;
			case "明天" :
				return DATE_NEXT;
			case "倒七天" :
				return DATE_LAST_7;
			case "进七天" :
				return DATE_NEXT_7;
			case "月初" :
				return DATE_MONTH_FIRST;
			case "月末" :
				return DATE_MONTH_LAST;
			case "上月今天" :
				return DATE_LAST_MONTH;
		}

		return null;
	}

	/**
	 * 得到指定数据类型的默认值类型列表
	 * 
	 * @param dataType
	 * @return
	 *         <p>
	 *         说明：
	 *         </p>
	 * @author 虾米 by 2017年5月8日 上午11:28:14
	 */
	public static List<String> getNamesByDataType(String dataType) {
		List<String> names = new ArrayList<String>();
		for (QueryInputDefType input : QueryInputDefType.values()) {
			if (input.getDataType().equals(dataType)) {
				names.add(input.getName());
			}
		}
		return names;
	}	
}
