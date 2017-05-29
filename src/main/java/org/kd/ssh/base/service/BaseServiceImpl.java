package org.kd.ssh.base.service;

import java.util.List;

import org.kd.ssh.base.dao.BaseDao;
import org.kd.ssh.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<T> implements BaseService<T>
{

	@Autowired
	BaseDao<T> baseDao;

	@Override
	public void save(T model)
	{
		baseDao.save(model);

	}

	@Override
	public void delete(Integer id)
	{
		baseDao.delete(id);

	}

	@Override
	public void update(T model)
	{
		baseDao.update(model);

	}

	@Override
	public List<T> queryAllList()
	{
		return baseDao.queryAllList();
	}

	@Override
	public T getById(Integer id)
	{
		return baseDao.getById(id);
	}

	@Override
	public PageBean<T> queryPageList(Integer pageNum, Integer pageSize) {
		List<T> list=baseDao.queryPageList(pageNum, pageSize);
		Long total=baseDao.getCount();
		return new PageBean<T>(pageSize, pageNum, total, list);
	}

}