package com.ozstrategy.service.user.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.user.FeatureDao;
import com.ozstrategy.model.user.Feature;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.FeatureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("featureManager")
public class FeatureManagerImpl extends BaseManagerImpl<Feature> implements FeatureManager {
    @Autowired
    private FeatureDao featureDao;

    @Override
    public BaseDao<Feature> baseDao() {
        return featureDao;
    }
}
