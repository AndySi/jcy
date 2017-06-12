package com.idou.admin;

import com.idou.entity.SysUserEntity;
import com.idou.util.ShiroUtils;

/**
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-04-06 上午 11:18
 **/

public abstract class AbstractController {
    protected SysUserEntity getUser(){
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
