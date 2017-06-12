package com.idou.dao;

import com.idou.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 下午 2:50
 **/
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysMenuEntity> queryUserList(Long userId);
}
