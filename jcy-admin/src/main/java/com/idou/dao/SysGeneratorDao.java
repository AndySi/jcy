package com.idou.dao;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 下午 2:48
 */
public interface SysGeneratorDao {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
