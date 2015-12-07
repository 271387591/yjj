package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.NewsDao;
import com.ozstrategy.model.News;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.NewsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("newsManager")
public class NewsManagerImpl extends BaseManagerImpl<News> implements NewsManager {
    @Autowired
    private NewsDao newsDao;

    @Override
    public BaseDao<News> baseDao() {
        return newsDao;
    }
}
