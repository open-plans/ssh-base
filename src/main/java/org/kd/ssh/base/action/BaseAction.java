package org.kd.ssh.base.action;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.kd.ssh.page.PageBean;
import org.kd.ssh.util.ReadPropertyPlaceholder;
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("all")
public class BaseAction<T> implements ModelDriven<T>, ServletRequestAware {
	protected Integer pageNum = 1;
	protected Integer pageSize = 10; 
	protected T model; // 实例对象 如 user type say
	protected Map<String, Object> jsonMap = new HashMap<String, Object>();
	protected HttpServletRequest request;
	protected PageBean<T> pageBean = new PageBean<T>();
	@Resource
	protected ReadPropertyPlaceholder readPropertyPlaceholder;

	public BaseAction() {
		try {
			// java 高级编程思想----> 反射思想 hibernate spring Struts2
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public T getModel() {
		return model;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// 初始化 基础配置
		init(request);
		this.request = request;
	}

	public PageBean<T> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<T> pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		pageBean.setPageNum(pageNum);
		this.pageNum = pageNum;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 初始化 基础配置
	public void init(HttpServletRequest request) {
		request.setAttribute("webPath", readPropertyPlaceholder.getProperty("dev.webPath"));
		request.setAttribute("imgPath", readPropertyPlaceholder.getProperty("dev.imgPath"));
	}

}
