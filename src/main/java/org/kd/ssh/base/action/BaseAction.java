package org.kd.ssh.base.action;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.kd.ssh.page.PageBean;

import com.opensymphony.xwork2.ModelDriven;
@SuppressWarnings("all")
public class BaseAction<T> implements ModelDriven<T>,ServletRequestAware
{	protected int pageNum=1;
	protected T model;   // 实例对象  如 user type  say
	protected Map<String, Object> jsonMap=new HashMap<String, Object>();
	protected HttpServletRequest request;
	protected PageBean<T> pageBean=new PageBean<T>();

	public BaseAction()
	{
	
		try
		{
			// java 高级编程思想----> 反射思想 hibernate spring Struts2
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			model = clazz.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	

	@Override
	public T getModel()
	{
		return model;
	}



	public Map<String, Object> getJsonMap()
	{
		return jsonMap;
	}



	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		request.setAttribute("webPath", "http://localhost:8080/ssh-maven-demo-3");
		this.request=request;
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




	
	

}
