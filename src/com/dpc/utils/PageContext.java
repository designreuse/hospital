package com.dpc.utils;

import java.util.Map;
public class PageContext
{
	/**
	 * 起始条目数
	 */
	private static ThreadLocal<Integer> start = new ThreadLocal<Integer>();

	/**
	 * 每页显示多少条数据
	 */
	private static ThreadLocal<Integer> limit = new ThreadLocal<Integer>();
	
	/**
	 * 查询参数
	 */
	private static ThreadLocal<Map<String, String>> params = new ThreadLocal<Map<String, String>>();

	public static void setStart(int value)
	{
		PageContext.start.set(value);
	}

	public static void removeStart()
	{
		PageContext.start.remove();
	}

	public static int getStart()
	{
		return PageContext.start.get();
	}

	public static void setLimit(int value)
	{
		PageContext.limit.set(value);
	}

	public static void removeLimit()
	{
		PageContext.limit.remove();
	}

	public static int getLimit()
	{
		return PageContext.limit.get();
	}
	
	public static void setParams(Map<String, String> value)
	{
		PageContext.params.set(value);
	}

	public static void removeParams()
	{
		PageContext.params.remove();
	}

	public static Map<String, String> getParams()
	{
		return PageContext.params.get();
	}
}
