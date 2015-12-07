package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.RoleDao;
import com.ozstrategy.model.user.Role;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-08.
*/
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

    public RoleDaoImpl() {
    super(Role.class);
    }

    @Override
    public void deleteFeature(Long roleId) {
        String sql="delete from t_rolefeature where roleId=?";
        jdbcTemplate.update(sql,roleId);
    }

    @Override
    public void saveRoleFeature(Long roleId, Long featureId) {
        String sql="insert into t_rolefeature(roleId,featureId) values(?,?)";
        jdbcTemplate.update(sql,roleId,featureId);

    }
}