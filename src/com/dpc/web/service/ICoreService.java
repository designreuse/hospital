package com.dpc.web.service;

import java.util.List;

import com.dpc.utils.PageEntity;
import com.dpc.utils.PageResult;
import com.dpc.web.VO.DoctorVO;
import com.dpc.web.VO.Pager;
import com.dpc.web.mybatis3.domain.Article;
import com.dpc.web.mybatis3.domain.Doctor;
import com.dpc.web.mybatis3.domain.FeedBack;
import com.dpc.web.mybatis3.domain.MedicalDynamic;

public interface ICoreService {

	Pager<FeedBack> findFeedBackByPaginaton(FeedBack feedBack);

	void addFeedBack(FeedBack feedBack);

	void updateFeedBack(FeedBack feedBack);

	
}
