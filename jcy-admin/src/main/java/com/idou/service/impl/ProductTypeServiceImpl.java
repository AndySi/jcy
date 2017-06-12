package com.idou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.idou.dao.ProductTypeDao;
import com.idou.entity.ProductTypeEntity;
import com.idou.service.ProductTypeService;



@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeDao productTypeDao;
	
	@Override
	public ProductTypeEntity queryObject(Integer id){
		return productTypeDao.queryObject(id);
	}
	
	@Override
	public List<ProductTypeEntity> queryList(Map<String, Object> map){
		return productTypeDao.queryList(map);
	}

	@Override
	public List<ProductTypeEntity> queryAll() {
		return productTypeDao.queryAll();
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return productTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(ProductTypeEntity productType){
		productTypeDao.save(productType);
	}
	
	@Override
	public void update(ProductTypeEntity productType){
		productTypeDao.update(productType);
	}
	
	@Override
	public void delete(Integer id){
		productTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		productTypeDao.deleteBatch(ids);
	}
	
}
