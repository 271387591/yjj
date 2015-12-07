package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.NewsDao;
import com.ozstrategy.model.News;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-15.
*/
@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao{

    public NewsDaoImpl() {
    super(News.class);
    }

}