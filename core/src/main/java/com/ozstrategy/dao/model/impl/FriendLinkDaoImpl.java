package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.FriendLinkDao;
import com.ozstrategy.model.FriendLink;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-16.
*/
@Repository("friendLinkDao")
public class FriendLinkDaoImpl extends BaseDaoImpl<FriendLink> implements FriendLinkDao{

    public FriendLinkDaoImpl() {
    super(FriendLink.class);
    }

}