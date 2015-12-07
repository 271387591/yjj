package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.ApplicationConfigDao;
import com.ozstrategy.model.ApplicationConfig;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-16.
*/
@Repository("applicationConfigDao")
public class ApplicationConfigDaoImpl extends BaseDaoImpl<ApplicationConfig> implements ApplicationConfigDao{

    public ApplicationConfigDaoImpl() {
    super(ApplicationConfig.class);
    }

}