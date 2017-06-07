package org.kd.ssh.base.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kd.ssh.annotation.HpField;
import org.kd.ssh.annotation.model.HpFieldModel;
import org.kd.ssh.util.SortList;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T>

{

	private T model;

	private List<HpFieldModel> hpFieldModelList = new ArrayList<HpFieldModel>();

	public BaseDaoImpl() {
		try {

			// java 高级编程思想----> 反射思想 hibernate spring Struts2

			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			setHpFieldModel(clazz);// 设置注解
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
			// log.error("id no exist--> id 不存在");
		}

	}

	@Override
	public void update(T model) {
		getHibernateSession().update(model);
	}

	@Override
	public List<T> queryAllList() {
		String orderHql = getOrderByHql();
		return getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + orderHql).list();
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
		String orderHql = getOrderByHql();
		return getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + orderHql)
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List<T> queryParamsAllList(String whereSql, Map<String, Object> map) {
		Query query = getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + " " + whereSql);
        int index=0;
		for (Entry<String, Object> item : map.entrySet()) {
			query.setParameter(index, item.getValue());
			index++;
		}
		return query.list();
	}
	
	@Override
	public List<T> queryPageParamsList(Integer pageNum, Integer pageSize, String whereSql, Map<String, Object> map) {
		Query query = getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + " " + whereSql);
        int index=0;
		for (Entry<String, Object> item : map.entrySet()) {
			query.setParameter(index, item.getValue());
			index++;
		}
		return query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public Long getCount() {
		return (Long) getHibernateSession().createQuery("Select count(*) FROM " + model.getClass().getSimpleName())
				.uniqueResult();
	}

	// 设置 注解字段实体类
	public void setHpFieldModel(Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			// 获取字段中包含HpField的注解
			HpField hpField = f.getAnnotation(HpField.class);
			if (hpField != null) {
				hpFieldModelList.add(new HpFieldModel(f.getName(), hpField.order(), hpField.orderIndex()));
			}
		}
	}

	// 获取排序hql语句
	public String getOrderByHql() {
		String orderHql = "";
		if (!CollectionUtils.isEmpty(hpFieldModelList)) {
			orderHql = " order by ";
			// 先排序
			SortList.sort(hpFieldModelList, "orderIndex", true);
			// 再拼接
			StringBuilder sbd = new StringBuilder();
			for (HpFieldModel hpFieldModel : hpFieldModelList) {
				sbd.append(hpFieldModel.getFieldName());
				sbd.append(" ");
				sbd.append(hpFieldModel.getOrder());
				sbd.append(",");
			}

			orderHql = orderHql + sbd.substring(0, sbd.length() - 1);
		}
		return orderHql;
	}



}
