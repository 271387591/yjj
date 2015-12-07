package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.FeatureDao;
import com.ozstrategy.model.user.Feature;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-08.
*/
@Repository("featureDao")
public class FeatureDaoImpl extends BaseDaoImpl<Feature> implements FeatureDao{

    public FeatureDaoImpl() {
    super(Feature.class);
    }

}