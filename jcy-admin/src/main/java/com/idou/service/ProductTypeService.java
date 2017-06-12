package com.idou.service;

import com.idou.entity.ProductTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ZhangSi
 * @email 917661718@qq.com
 * @date 2017-06-06 15:21:53
 */
public interface ProductTypeService {
	
	ProductTypeEntity queryObject(Integer id);
	
	List<ProductTypeEntity> queryList(Map<String, Object> map);

	List<ProductTypeEntity> queryAll();

	int queryTotal(Map<String, Object> map);
	
	void save(ProductTypeEntity productType);
	
	void update(ProductTypeEntity productType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
