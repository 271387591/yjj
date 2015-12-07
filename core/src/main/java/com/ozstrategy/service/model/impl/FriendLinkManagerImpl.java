package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.FriendLinkDao;
import com.ozstrategy.model.FriendLink;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.FriendLinkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("friendLinkManager")
public class FriendLinkManagerImpl extends BaseManagerImpl<FriendLink> implements FriendLinkManager {
    @Autowired
    private FriendLinkDao friendLinkDao;

    @Override
    public BaseDao<FriendLink> baseDao() {
        return friendLinkDao;
    }
}
