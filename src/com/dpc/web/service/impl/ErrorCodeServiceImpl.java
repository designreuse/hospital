package com.dpc.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.PageContext;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.ErrorCode;
import com.dpc.web.mybatis3.mapper.ErrorCodeMapper;
import com.dpc.web.service.IErrorCodeService;

@Service("errorCodeService")
@Transactional(propagation = Propagation.SUPPORTS)
public class ErrorCodeServiceImpl implements IErrorCodeService
{
	
	@Autowired
	private ErrorCodeMapper errorCodeMapper;

	public List<Map<String, Object>> getErrorCodes() {
		
		return errorCodeMapper.getErrorCodes();
	}


	@Override
	public void addErrorCode(ErrorCode errorCode) {
		errorCodeMapper.addErrorCode(errorCode);
		
	}

	@Override
	public ErrorCode getByID(Integer id) {
		return errorCodeMapper.getById(id);
	}


	@Override
	public void updateErrorCode(ErrorCode errorCode) {
		
		errorCodeMapper.updateErrorCode(errorCode);
	}


	/* (non-Javadoc)
	 * @see com.yiyiglobal.yuenr.web.service.IErrorCodeService#findByPagination()
	 */
	@Override
	public Pager<ErrorCode> findByPagination() {
		int start = PageContext.getStart();
		int limit = PageContext.getLimit();
		
		List<ErrorCode> datas = errorCodeMapper.getErrorCodeByPage(start, limit);
		Integer total = errorCodeMapper.getTotalCount();
		
		Pager<ErrorCode> pager = new Pager<ErrorCode>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(total);
		pager.setDatas(datas);
		
		return pager;
	}


	@Override
	public void delete(String[] ids) {

		errorCodeMapper.batchDel(ids);
	}


	@Override
	public List<ErrorCode> getAll() {
		
		return errorCodeMapper.getAll();
	}

}
