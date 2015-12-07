package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.NoticeDao;
import com.ozstrategy.model.Notice;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-15.
*/
@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao{

    public NoticeDaoImpl() {
    super(Notice.class);
    }

}