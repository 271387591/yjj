package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.FriendLink;

import java.util.Date;

/**
* Created by lihao1 on 2015-10-16.
*/
public class FriendLinkCommand {
    private Long id;
    private String outUrl;
    private String name;
    private Boolean publish;
    private Date createDate;
    private Date publishDate;
    public FriendLinkCommand() {
    }
    public FriendLinkCommand(FriendLink model) {
        this.id=model.getId();
        this.outUrl=model.getOutUrl();
        this.name=model.getName();
        this.publish=model.getPublish();
        this.createDate=model.getCreateDate();
        this.publishDate=model.getPublishDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOutUrl() {
        return outUrl;
    }
    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
}
