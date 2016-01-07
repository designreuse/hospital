package com.dpc.web.mybatis3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dpc.web.mybatis3.domain.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

	Integer getArticleCountByType(@Param("article") Article article);

	List<Article> getArticlePagerByType(@Param("article") Article article, @Param("start") Integer start, @Param("limit") Integer limit );

	List<Article> getArticleByTypeAndCategory(int type, int category);

	List<Article> getArticlesByIllType(int illType);

	List<Article> getHeartVedioList();

	List<Article> getCartoonList();
}