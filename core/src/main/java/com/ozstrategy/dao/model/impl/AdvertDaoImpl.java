package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.AdvertDao;
import com.ozstrategy.model.Advert;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-15.
*/
@Repository("advertDao")
public class AdvertDaoImpl extends BaseDaoImpl<Advert> implements AdvertDao{

    public AdvertDaoImpl() {
    super(Advert.class);
    }

}