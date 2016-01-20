package com.dpc.web.mybatis3.mapper;

import com.dpc.web.mybatis3.domain.ArticleRemark;

public interface ArticleRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleRemark record);

    int insertSelective(ArticleRemark record);

    ArticleRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleRemark record);

    int updateByPrimaryKeyWithBLOBs(ArticleRemark record);

    int updateByPrimaryKey(ArticleRemark record);
}