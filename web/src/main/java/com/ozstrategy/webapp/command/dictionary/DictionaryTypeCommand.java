package com.ozstrategy.webapp.command.dictionary;
import com.ozstrategy.model.dictionary.DictionaryType;

import java.util.Date;

/**
* Created by lihao1 on 2015-08-10.
*/
public class DictionaryTypeCommand {
    private Long id;
    private String dtype;
    private Date createDate;
    public DictionaryTypeCommand() {
    }
    public DictionaryTypeCommand(DictionaryType model) {
        this.id=model.getId();
        this.dtype=model.getDtype();
        this.createDate=model.getCreateDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDtype() {
        return dtype;
    }
    public void setDtype(String dtype) {
        this.dtype = dtype;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
