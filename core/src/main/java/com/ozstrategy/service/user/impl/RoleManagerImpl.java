package com.ozstrategy.service.user.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.user.RoleDao;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("roleManager")
public class RoleManagerImpl extends BaseManagerImpl<Role> implements RoleManager {
    @Autowired
    private RoleDao roleDao;

    @Override
    public BaseDao<Role> baseDao() {
        return roleDao;
    }

    @Override
    public void saveRole(Role role, List<Long> fids) {
        Long id=role.getId();
        saveOrUpdate(role);
        roleDao.deleteFeature(id);
        for(Long f:fids){
            roleDao.saveRoleFeature(id,f);
        }
    }
}
