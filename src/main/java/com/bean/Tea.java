package com.bean;

/**
 * 对应tea 表的模型
 */
public class Tea {
    private Integer teaId;

    private String teaName;

    private Double teaPrice;

    private String teaPhoto;

    private Integer teaState;

    private String teaIntro;

    private Integer teaNum;

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public Double getTeaPrice() {
        return teaPrice;
    }

    public void setTeaPrice(Double teaPrice) {
        this.teaPrice = teaPrice;
    }

    public String getTeaPhoto() {
        return teaPhoto;
    }

    public void setTeaPhoto(String teaPhoto) {
        this.teaPhoto = teaPhoto == null ? null : teaPhoto.trim();
    }

    public Integer getTeaState() {
        return teaState;
    }

    public void setTeaState(Integer teaState) {
        this.teaState = teaState;
    }

    public String getTeaIntro() {
        return teaIntro;
    }

    public void setTeaIntro(String teaIntro) {
        this.teaIntro = teaIntro == null ? null : teaIntro.trim();
    }

    public Integer getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(Integer teaNum) {
        this.teaNum = teaNum;
    }

    public Tea() {
    }

    public Tea(String teaName, Double teaPrice, String teaPhoto, String teaIntro, Integer teaNum) {
        this.teaName = teaName;
        this.teaPrice = teaPrice;
        this.teaPhoto = teaPhoto;
        this.teaIntro = teaIntro;
        this.teaNum = teaNum;
    }
}