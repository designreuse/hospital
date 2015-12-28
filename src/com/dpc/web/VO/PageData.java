/*
 * -------------------------------------------------------
 * Copyright (c) 2015, 北京易易科技有限公司
 * All rights reserved.
 * 
 * FileName：PageData.java.java
 * Description：简要描述本文件的内容
 * History：
 * Date           Author               Desc
 * -------------------------------------------------------
 */
package com.dpc.web.VO;

/**
 *
 * DiWu
 */

public class PageData
{
	private long startPageNo;
	private long endPageNo;

	public PageData(long startPageNo, long endPageNo)
	{
		this.startPageNo = startPageNo;
		this.endPageNo = endPageNo;
	}

	public long getStartPageNo()
	{
		return startPageNo;
	}

	public void setStartPageNo(long startPageNo)
	{
		this.startPageNo = startPageNo;
	}

	public long getEndPageNo()
	{
		return endPageNo;
	}

	public void setEndPageNo(long endPageNo)
	{
		this.endPageNo = endPageNo;
	}

}
