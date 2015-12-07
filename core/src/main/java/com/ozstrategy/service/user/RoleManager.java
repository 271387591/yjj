package com.ozstrategy.service.user;

import com.ozstrategy.model.user.Role;
import com.ozstrategy.service.BaseManager;

import java.util.List;

/**
* Created by lihao1 on 2015-06-08.
*/
public interface RoleManager extends BaseManager<Role> {
    void saveRole(Role role,List<Long> fids);
}
