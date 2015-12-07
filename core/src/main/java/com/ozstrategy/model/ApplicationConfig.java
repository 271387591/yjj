package com.ozstrategy.model;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;

/**
 * Created by lihao1 on 10/16/15.
 */
@Table(name = "t_applicationconfig")
public class ApplicationConfig {
    @Id
    private Long id;
    private String keyword;
    private String systemValue;

    public ApplicationConfig() {
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
