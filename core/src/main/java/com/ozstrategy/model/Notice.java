package com.ozstrategy.model;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 10/15/15.
 */
@Table(name = "t_notice")
public class Notice extends BaseEntity{
    @Id
    private Long id;
    private Date createDate;
    private Date publishDate;
    private String content;
    private String title;
    private String pubUnit;
    private Integer readNum;
    private Boolean publish=Boolean.FALSE;
    private Integer idx;

    public Notice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubUnit() {
        return pubUnit;
    }

    public void setPubUnit(String pubUnit) {
        this.pubUnit = pubUnit;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
