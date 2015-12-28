package com.dpc.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.dpc.utils.PageContext;


/**
 * 
 * 分页过滤器
 *
 */
public class PaginationFilter implements Filter
{

	public void init(FilterConfig config) throws ServletException
	{

	}

	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain chain) throws IOException, ServletException
	{
		Map<String, String> params = getParameterMap(sreq);
		int limit = getLimit(sreq);
		int start = getStart(sreq, limit);

		PageContext.setStart(start);
		PageContext.setLimit(limit);
		PageContext.setParams(params);

		try
		{
			chain.doFilter(sreq, sresp);
		} finally
		{
			PageContext.removeStart();
			PageContext.removeLimit();
			PageContext.removeParams();
		}

	}

	/**
	 * 构造parameter map
	 * @param sreq
	 * @return
	 */
	private Map<String, String> getParameterMap(ServletRequest sreq)
	{
		Map<String, String> result = null;
		HttpServletRequest req = (HttpServletRequest) sreq;
		Map<String, String[]> params = req.getParameterMap();
		if (params != null && params.size() > 0)
		{
			result = new HashMap<String, String>();
			Iterator<String> iter = params.keySet().iterator();
			while (iter.hasNext())
			{
				String key = iter.next();
				String value = params.get(key)[0];
				result.put(key, value);
			}
		}
		return result;
	}

	private int getStart(ServletRequest sreq, int limit)
	{
		HttpServletRequest req = (HttpServletRequest) sreq;
		int pageNo = 1;
		try
		{
			pageNo = Integer.valueOf(req.getParameter("pageNo"));
		} catch (Exception e)
		{
			pageNo = 1;
		}
		return (pageNo - 1) * limit;
	}

	private int getLimit(ServletRequest sreq)
	{
		HttpServletRequest req = (HttpServletRequest) sreq;
		int limit = 10;
		try
		{
			limit = Integer.valueOf(req.getParameter("limit"));
		} catch (Exception e)
		{
			limit = 10;
		}
		return limit;
	}

	public void destroy()
	{

	}

}
