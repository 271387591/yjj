package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.AdvertDao;
import com.ozstrategy.model.Advert;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.AdvertManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("advertManager")
public class AdvertManagerImpl extends BaseManagerImpl<Advert> implements AdvertManager {
    @Autowired
    private AdvertDao advertDao;

    @Override
    public BaseDao<Advert> baseDao() {
        return advertDao;
    }
}
