package com.idou.dao;

import com.idou.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 下午 2:53
 **/
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);
}
