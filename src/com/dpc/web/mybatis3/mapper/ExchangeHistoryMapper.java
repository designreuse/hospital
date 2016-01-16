package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.ExchangeHistory;

public interface ExchangeHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeHistory record);

    int insertSelective(ExchangeHistory record);

    ExchangeHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeHistory record);

    int updateByPrimaryKey(ExchangeHistory record);

	List<ExchangeHistory> getMyExchangeHistoryList(int userID);
}