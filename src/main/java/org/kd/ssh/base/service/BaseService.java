package org.kd.ssh.base.service;

import java.util.List;

import org.kd.ssh.page.PageBean;

public interface BaseService<T>
{

	// 增 
	public void save(T model); 
	// 删
	public void delete(Integer id); 
	// 改
	public void update(T model); 
	// 查询所有
	public List<T> queryAllList();
	// 查询单个
	public T getById(Integer id);
	
	// 分页  pageSize 页数  pageNum 页码
	
	public PageBean<T> queryPageList(Integer pageNum,Integer pageSize);
}
