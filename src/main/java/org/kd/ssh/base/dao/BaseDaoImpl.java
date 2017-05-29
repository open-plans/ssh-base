package org.kd.ssh.base.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T>

{

	private T model;

	public BaseDaoImpl() {
		try {

			// java 高级编程思想----> 反射思想 hibernate spring Struts2

			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Resource
	SessionFactory sessionFactory;

	@Resource
	HibernateTemplate hibernateTemplate;

	@Override
	public void save(T model) {
		getHibernateSession().save(model);
	}

	@Override
	public void delete(Integer id) {
		Object obj = getById(id);
		if (obj != null) {
			getHibernateSession().delete(obj);
		} else {
			// throw new RuntimeException("id no exist--> id 不存在");
			// System.out.println("id no exist--> id 不存在");
			//log.error("id no exist--> id 不存在");
		}

	}

	@Override
	public void update(T model) {
		getHibernateSession().update(model);
	}

	@Override
	public List<T> queryAllList() {
		return getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName()).list();
	}

	@Override
	public T getById(Integer id) {
		return (T) getHibernateSession().get(model.getClass(), id);
	}

	@Override
	public Session getHibernateSession() {
		return sessionFactory.getCurrentSession();

	}

	@Override
	public List<T> queryPageList(Integer pageNum, Integer pageSize) {
		return getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName())
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public Long getCount() {
		return (Long) getHibernateSession().createQuery("Select count(*) FROM " + model.getClass().getSimpleName())
				.uniqueResult();
	}

}
