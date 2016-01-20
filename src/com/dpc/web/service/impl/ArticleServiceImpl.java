package com.dpc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpc.utils.ConstantUtil;
import com.dpc.utils.PageContext;
import com.dpc.utils.ValidateUtil;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.ArticleRemark;
import com.dpc.web.mybatis3.mapper.ArticleMapper;
import com.dpc.web.mybatis3.mapper.ArticleRemarkMapper;
import com.dpc.web.service.IArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleRemarkMapper articleRemarkMapper;

	@Override
	public void saveArticle(Article article) {
		articleMapper.insertSelective(article);		
	}

	@Override
	public Pager<Article> findByPagination(Article article) {
		Integer start = PageContext.getStart();
		Integer limit = PageContext.getLimit();
		article.setStart(start);
		article.setLimit(limit);
		List<Article> datas =  articleMapper.getArticlePagerByType(article,start,limit);
		if(datas!=null&&datas.size()>0){
			for(Article a : datas){
				a.setCoverImageUrl(ConstantUtil.DOMAIN+a.getCoverImageUrl());
			}
		}
		Integer totalCount = articleMapper.getArticleCountByType(article);
		Pager<Article> pager = new Pager<Article>();
		pager.setPageOffset(start);
		pager.setPageSize(limit);
		pager.setTotal(totalCount);
		pager.setDatas(datas);
		
		return pager;
	}

	@Override
	public void delArticle(int id) {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public Article getArticleById(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Article> getArticleByTypeAndCategory(int type, int category) {
		return articleMapper.getArticleByTypeAndCategory(type,category);
	}

	@Override
	public List<Article> getArticlesByIllType(int illType) {
		
		return articleMapper.getArticlesByIllType(illType);
	}

	@Override
	public List<Article> getHeartVedioList(Article param) {
		return articleMapper.getHeartVedioList(param);
	}

	@Override
	public List<Article> getCartoonList(Article param) {
		return articleMapper.getCartoonList(param);
	}

	@Override
	public List<Article> getArticlesLately() {
		return articleMapper.getArticlesLately();
	}

	@Override
	public Article getArticleDetailById(int id) {
		return articleMapper.getArticleDetailById(id);
	}

	@Override
	public Article getArticleDetail(int id) {
		
		return articleMapper.getArticleDetail(id);
	}

	
	@Override
	public void addArticleRemark(ArticleRemark articleRemark) {
		articleRemarkMapper.insertSelective(articleRemark);
	}
}
