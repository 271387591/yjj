package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.ApplicationConfig;
/**
* Created by lihao1 on 2015-10-16.
*/
public class ApplicationConfigCommand {
    private Long id;
    private String keyword;
    private String systemValue;
    public ApplicationConfigCommand() {
    }
    public ApplicationConfigCommand(ApplicationConfig model) {
        this.id=model.getId();
        this.keyword=model.getKeyword();
        this.systemValue=model.getSystemValue();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getSystemValue() {
        return systemValue;
    }
    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }
}
