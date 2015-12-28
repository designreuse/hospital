package com.dpc.web.mybatis3.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DiagnoseExperienceImage {
    private Integer id;

    private Integer diaExpId;

    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiaExpId() {
        return diaExpId;
    }

    public void setDiaExpId(Integer diaExpId) {
        this.diaExpId = diaExpId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }
}