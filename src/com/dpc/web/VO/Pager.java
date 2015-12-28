/*
 * -------------------------------------------------------
 * Copyright (c) 2015, 北京易易科技有限公司
 * All rights reserved.
 * 
 * FileName：Pager.java.java
 * Description：简要描述本文件的内容
 * History：
 * Date           Author               Desc
 * -------------------------------------------------------
 */
package com.dpc.web.VO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * DiWu
 */

public class Pager<T> implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * 数据
	 */
	private List<T> datas;

	/**
	 * 共有多少条数据
	 */
	private long total;

	/**
	 * 每页显示多少条
	 */
	private int pageSize;

	/**
	 * 数据的offset
	 */
	private int pageOffset;

	/**
	 * 当前第几页
	 */
	private int pageNo;

	/**
	 * 共多少页
	 */
	private int totalPageNo;

	public List<T> getDatas()
	{
		return datas;
	}

	public void setDatas(List<T> datas)
	{
		this.datas = datas;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getPageNo()
	{
		return pageOffset / pageSize + 1;
	}

	public int getPageOffset()
	{
		return pageOffset;
	}

	public void setPageOffset(int pageOffset)
	{
		this.pageOffset = pageOffset;
	}

	public long getTotalPageNo()
	{
		if (total % pageSize == 0)
		{
			return (total / pageSize);
		} else
		{
			return (total / pageSize) + 1;
		}
	}

	public int getNextPageStart()
	{
		return ((pageOffset >= total) ? pageOffset : (pageOffset + pageSize));
	}

	public int getPrePageStart()
	{
		return ((pageNo == 1) ? 0 : (pageOffset - pageSize));
	}

	public int getStartPageNo()
	{
		return 0;
	}

	public int getEndPageNo()
	{
		return 0;
	}

	/**
	 * 页码数据
	 */
	private PageData pageData;

	public PageData getPageData()
	{
		if (pageData == null)
		{
			int pageNo = getPageNo();
			long totalPageNo = getTotalPageNo();
			long startPageNo = 0;
			long endPageNo = 0;
			if (pageNo == 1)
			{//
				if (totalPageNo == 1)
				{
					startPageNo = 1;
					endPageNo = 1;
				} else if (pageNo + 8 > totalPageNo)
				{
					startPageNo = pageNo;
					endPageNo = totalPageNo;
				} else
				{
					startPageNo = pageNo;
					endPageNo = pageNo + 8;
				}
			} else if (pageNo > 1 && pageNo < 8)
			{
				startPageNo = 1;
				if (totalPageNo <= 8)
				{
					endPageNo = totalPageNo;
				} else if (totalPageNo > 8)
				{
					endPageNo = 8;
				}
			} else if (pageNo > 1 && pageNo >= 8)
			{
				if ((totalPageNo - pageNo) > 4)
				{
					startPageNo = pageNo - 4;
					endPageNo = pageNo + 4;
				} else if ((totalPageNo - pageNo) <= 4)
				{
					startPageNo = totalPageNo - 8;
					endPageNo = totalPageNo;
				}
			}
			pageData = new PageData(startPageNo, endPageNo);
		}
		return pageData;
	}

}
