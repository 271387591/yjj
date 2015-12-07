package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.Advert;

import java.util.Date;

/**
* Created by lihao1 on 2015-10-15.
*/
public class AdvertCommand {
    private String content;
    private Long id;
    private String title;
    private Integer idx;
    private Integer readNum;
    private String pubUnit;
    private Boolean publish;
    private Date createDate;
    private Date publishDate;
    private String picUrl;
    private String picPath;
    private String picName;
    private String outUrl;
    public AdvertCommand() {
    }
    public AdvertCommand(Advert model) {
        this.content=model.getContent();
        this.id=model.getId();
        this.title=model.getTitle();
        this.idx=model.getIdx();
        this.readNum=model.getReadNum();
        this.pubUnit=model.getPubUnit();
        this.publish=model.getPublish();
        this.createDate=model.getCreateDate();
        this.publishDate=model.getPublishDate();
        this.picName=model.getPicName();
        this.picUrl=model.getPicUrl();
        this.outUrl=model.getOutUrl();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getIdx() {
        return idx;
    }
    public void setIdx(Integer idx) {
        this.idx = idx;
    }
    public Integer getReadNum() {
        return readNum;
    }
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }
    public String getPubUnit() {
        return pubUnit;
    }
    public void setPubUnit(String pubUnit) {
        this.pubUnit = pubUnit;
    }
    public Boolean getPublish() {
        return publish;
    }
    public void setPublish(Boolean publish) {
        this.publish = publish;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getOutUrl() {
        return outUrl;
    }

    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }
}
