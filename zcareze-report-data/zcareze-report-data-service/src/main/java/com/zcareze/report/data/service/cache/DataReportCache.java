package com.zcareze.report.data.service.cache;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zcareze.report.base.service.bo.ReportDesignBO;

/**
 * 报表设计信息缓存
 * 
 * @Filename DataReportCache.java
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
public class DataReportCache {

	private static HashMap<String, ReportDesignBO> reportDesignCaches = new HashMap<String, ReportDesignBO>();
	private static Lock myLock = new ReentrantLock(false);

	public static ReportDesignBO get(String cloudId, String id) {
		String key = generateKey(cloudId, id);
		if (reportDesignCaches.containsKey(key)) {
			return reportDesignCaches.get(key);
		}
		return null;
	}

	public static void put(String cloudId, String id, ReportDesignBO reportDesignVO) {
		String key = generateKey(cloudId, id);
		myLock.lock();
		try {
			if (!reportDesignCaches.containsKey(key)) {
				reportDesignCaches.put(key, reportDesignVO);
			}
		} finally {
			myLock.unlock();
		}
	}

	public static void remove(String cloudId, String id) {
		String key = generateKey(cloudId, id);
		myLock.lock();
		try {
			if (reportDesignCaches.containsKey(key)) {
				reportDesignCaches.remove(key);
			}
		} finally {
			myLock.unlock();
		}
	}

	private static String generateKey(String cloudId, String id) {
		return cloudId + "-" + id;
	}
}