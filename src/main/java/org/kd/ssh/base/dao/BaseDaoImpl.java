package org.kd.ssh.base.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kd.ssh.annotation.HpField;
import org.kd.ssh.annotation.HpIsDeleteField;
import org.kd.ssh.annotation.model.BaseModel;
import org.kd.ssh.annotation.model.HpFieldModel;
import org.kd.ssh.annotation.model.HpIsDeleteFieldModel;
import org.kd.ssh.util.HpHqlUtil;
import org.kd.ssh.util.SortList;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T>

{

	private T model;

	private List<HpFieldModel> hpFieldModelList = new ArrayList<HpFieldModel>();

	private HpIsDeleteFieldModel hpIsDeleteFieldModel;

	public BaseDaoImpl() {
		try {

			// java 高级编程思想----> 反射思想 hibernate spring Struts2

			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = clazz.newInstance();
			setHpFieldModel(clazz);// 设置注解
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Resource
	SessionFactory sessionFactory;

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
			 throw new RuntimeException("id no exist--> id 不存在");
			// System.out.println("id no exist--> id 不存在");
			// log.error("id no exist--> id 不存在");
		}

	}
	
	@Override
	public void delete(String id) {
		Object obj = getById(id);
		if (obj != null) {
			getHibernateSession().delete(obj);
		} else {
			throw new RuntimeException("id no exist--> id 不存在");
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
		String whereHql = getWhereHql();
		String orderHql = getOrderByHql();
		return getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + whereHql + orderHql)
				.list();
	}

	@Override
	public T getById(Integer id) {
		return (T) getHibernateSession().get(model.getClass(), id);
	}
	
	@Override
	public T getById(String id) {
		return (T) getHibernateSession().get(model.getClass(), id);
	}

	@Override
	public Session getHibernateSession() {
		return sessionFactory.getCurrentSession();

	}

	@Override
	public List<T> queryPageList(Integer pageNum, Integer pageSize) {
		String whereHql = getWhereHql();
		String orderHql = getOrderByHql();
		return getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + whereHql + orderHql)
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public List<T> queryParamsAllList(String whereSql, Map<String, Object> map) {
		Query query = getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + " " + whereSql);
		int index = 0;
		for (Entry<String, Object> item : map.entrySet()) {
			query.setParameter(index, item.getValue());
			index++;
		}
		return query.list();
	}

	@Override
	public List<T> queryPageParamsList(Integer pageNum, Integer pageSize, String whereSql, Map<String, Object> map) {
		Query query = getHibernateSession().createQuery("FROM " + model.getClass().getSimpleName() + " " + whereSql);
		int index = 0;
		for (Entry<String, Object> item : map.entrySet()) {
			query.setParameter(index, item.getValue());
			index++;
		}
		return query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public Long getCount() {
		Long count=null;
		if (hpIsDeleteFieldModel == null) {
			count=(Long) getHibernateSession().createQuery("Select count(*) FROM " + model.getClass().getSimpleName())
			.uniqueResult();
		}else{
			String whereHql = getWhereHql();
			count=(Long) getHibernateSession().createQuery("Select count(*) FROM " + model.getClass().getSimpleName()+whereHql)
			.uniqueResult();
		}
		return count;
	}

	@Override
	public Long getWhereHqlCount(String whereHql,Map<String, Object> map) {
		Query query = getHibernateSession().createQuery("Select count(*) FROM " + model.getClass().getSimpleName()+whereHql);
		int index = 0;
		for (Entry<String, Object> item : map.entrySet()) {
			query.setParameter(index, item.getValue());
			index++;
		}
		return (Long) query.uniqueResult();
	}
	
	
	@Override
	public void updateDelete(Integer id) {
		if (hpIsDeleteFieldModel == null) {
			// hpIsDeleteField 注解不存在
			throw new RuntimeException("not hpIsDeleteField  annotation no exist");
		}
		T t = getById(id);
		try {
			if (t.getClass().getSuperclass().getSimpleName().equals("Object")) {
				Method method = t.getClass().getDeclaredMethod(
						HpHqlUtil.getFieldMethod("set", hpIsDeleteFieldModel.getFieldName()), Integer.class);
				method.invoke(t, hpIsDeleteFieldModel.getFieldValue());
			}else{
				// 继承父类model的情况
				Method method = t.getClass().getSuperclass().getDeclaredMethod(
						HpHqlUtil.getFieldMethod("set", hpIsDeleteFieldModel.getFieldName()), Integer.class);
				method.invoke(t, hpIsDeleteFieldModel.getAlreadyDeleteValue());
			}
			getHibernateSession().update(t);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void updateDelete(String id) {
		if (hpIsDeleteFieldModel == null) {
			// hpIsDeleteField 注解不存在
			throw new RuntimeException("not hpIsDeleteField  annotation no exist");
		}
		T t = getById(id);
		try {
			if (t.getClass().getSuperclass().getSimpleName().equals("Object")) {
				Method method = t.getClass().getDeclaredMethod(
						HpHqlUtil.getFieldMethod("set", hpIsDeleteFieldModel.getFieldName()), String.class);
				method.invoke(t, hpIsDeleteFieldModel.getFieldValue());
			}else{
				// 继承父类model的情况
				Method method = t.getClass().getSuperclass().getDeclaredMethod(
						HpHqlUtil.getFieldMethod("set", hpIsDeleteFieldModel.getFieldName()), String.class);
				method.invoke(t, hpIsDeleteFieldModel.getAlreadyDeleteValue());
			}
			getHibernateSession().update(t);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			// 获取字段中包含HpIsDeleteField的注解
			HpIsDeleteField isDeleteField = f.getAnnotation(HpIsDeleteField.class);
			if (isDeleteField != null) {
				if (StringUtils.isEmpty(isDeleteField.isDeleteField())) {
					hpIsDeleteFieldModel = new HpIsDeleteFieldModel(f.getName(), isDeleteField.isDeleteValue(),isDeleteField.alreadyDeleteValue());
				}else{
					hpIsDeleteFieldModel = new HpIsDeleteFieldModel(isDeleteField.isDeleteField(), isDeleteField.isDeleteValue(),isDeleteField.alreadyDeleteValue());
				}
			}
		}
		
		// 继承 baseModel 情况  model instanceof BaseModel
		if (!clazz.getSuperclass().getSimpleName().equals("Object")) {
			Field[] parentFieds = clazz.getSuperclass().getDeclaredFields();
			for (Field f : parentFieds) {
				// 获取字段中包含HpIsDeleteField的注解
				HpIsDeleteField isDeleteField = f.getAnnotation(HpIsDeleteField.class);
				if (isDeleteField != null) {
					if (StringUtils.isEmpty(isDeleteField.isDeleteField())) {
						hpIsDeleteFieldModel = new HpIsDeleteFieldModel(f.getName(),
								isDeleteField.isDeleteValue(),isDeleteField.alreadyDeleteValue());
					}else{
						hpIsDeleteFieldModel = new HpIsDeleteFieldModel(isDeleteField.isDeleteField(), isDeleteField.isDeleteValue(),isDeleteField.alreadyDeleteValue());
					}
				}
			}
		}

	}

	// 获取whereHql语句
	public String getWhereHql() {
		String whereHql = "";
		if (hpIsDeleteFieldModel != null) {
			whereHql += " where 1=1 and " + hpIsDeleteFieldModel.getFieldName() + "="
					+ hpIsDeleteFieldModel.getFieldValue();
		}
		return whereHql;
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
