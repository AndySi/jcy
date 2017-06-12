package com.idou.service;

import com.idou.entity.ProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ZhangSi
 * @email 917661718@qq.com
 * @date 2017-06-06 15:21:53
 */
public interface ProductService {
	
	ProductEntity queryObject(Long id);
	
	List<ProductEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProductEntity product);
	
	void update(ProductEntity product);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
