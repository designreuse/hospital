package com.dpc.web.mybatis3.mapper;

import java.util.List;

import com.dpc.web.mybatis3.domain.DiagnoseExperience;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceImage;
import com.dpc.web.mybatis3.domain.DiagnoseExperienceRemark;

public interface DiagnoseExperienceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiagnoseExperience record);

    int insertSelective(DiagnoseExperience record);

    DiagnoseExperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiagnoseExperience record);

    int updateByPrimaryKey(DiagnoseExperience record);

	void addDiagnoseExperienceRemark(DiagnoseExperienceRemark remark);

	void addDiagnoseExperienceImage(DiagnoseExperienceImage image);

	DiagnoseExperience getDiagnoseExperienceDetail(int id);

	List<DiagnoseExperienceImage> getDiagnoseExperienceImageByDiaExpId(int id);

	List<DiagnoseExperience> getDiagnoseExperienceList();

	DiagnoseExperience getDiagnoseExperienceById(int id);
}