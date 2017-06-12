package com.idou.dao;

import com.idou.entity.ProductTypeEntity;

import java.util.List;

/**
 * 
 * 
 * @author ZhangSi
 * @email 917661718@qq.com
 * @date 2017-06-06 15:21:53
 */
public interface ProductTypeDao extends BaseDao<ProductTypeEntity> {
	List<ProductTypeEntity> queryAll();
}
