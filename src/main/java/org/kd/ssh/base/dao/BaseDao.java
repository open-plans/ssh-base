package org.kd.ssh.base.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface BaseDao<T>
{
	// 增 
	public void save(T model); 
	// 真删
	public void delete(Integer id); 
	// 改
	public void update(T model); 
	// 查询所有
	public List<T> queryAllList();
	// 查询单个
	public T getById(Integer id);
	// 获取 hibernate  session
	public Session getHibernateSession();
	
	// 分页  pageSize 页数  pageNum 页码
	public List<T> queryPageList(Integer  pageNum,Integer pageSize);
	// 分页 条件查询
	public List<T> queryPageParamsList(Integer pageNum, Integer pageSize,String whereSql, Map<String, Object>map);
	// 条件查询（所有）
	public List<T> queryParamsAllList(String whereSql, Map<String, Object>map);
	// 查询总条数
	public Long getCount();
	// 带条件查询总条数
	public Long getWhereHqlCount(String whereHql,Map<String, Object> map);
	// 逻辑删除
	public void updateDelete(Integer id);
	
	// 真删(String)
	public void delete(String id); 
	// 查询单个(String)
	public T getById(String id);
	// 逻辑删除(String)
	public void updateDelete(String id);
}