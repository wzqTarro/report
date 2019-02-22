package com.zcareze.report.base.service.cache;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zcareze.report.base.service.vo.ReportViewVO;

/**
 * 报表设计信息缓存
 * 
 * @Filename ReportViewCache.java
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
 *          <li>Date: 2017年4月11日</li>
 *          <li>Version: 1.0</li>
 *          <li>Content: create</li>
 *
 */
public class ReportViewCache {

	private static HashMap<String, ReportViewVO> reportViewCaches = new HashMap<String, ReportViewVO>();
	private static Lock myLock = new ReentrantLock(false);

	public static ReportViewVO get(String cloudId, String id) {
		String key = geneteKey(cloudId, id);
		if (reportViewCaches.containsKey(key)) {
			return reportViewCaches.get(key);
		}
		return null;
	}

	public static void put(String cloudId, String id, ReportViewVO reportViewVO) {
		String key = geneteKey(cloudId, id);
		myLock.lock();
		try {
			if (!reportViewCaches.containsKey(key)) {
				reportViewCaches.put(key, reportViewVO);
			}
		} finally {
			myLock.unlock();
		}
	}

	public static void remove(String cloudId, String id) {
		String key = geneteKey(cloudId, id);
		myLock.lock();
		try {
			if (reportViewCaches.containsKey(key)) {
				reportViewCaches.remove(key);
			}
		} finally {
			myLock.unlock();
		}
	}

	private static String geneteKey(String cloudId, String reportId) {
		return cloudId + "-" + reportId;
	}
}