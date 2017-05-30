/**
 * 代号:隐无为 2017：厚溥
 * 文件名：BaseActionSimple.java
 * 创建人：柯栋
 * 日期：2017年6月2日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.base.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.kd.ssh.util.ReadPropertyPlaceholder;

/**
 * 用途：业务模块名称
 */
@SuppressWarnings("all")
public class BaseActionSimple implements ServletRequestAware {
	@Resource
	protected ReadPropertyPlaceholder readPropertyPlaceholder;
	protected HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// 初始化 基础配置
		init(request);
		this.request = request;
	}

	// 初始化 基础配置
	public void init(HttpServletRequest request) {
		request.setAttribute("webPath", readPropertyPlaceholder.getProperty("dev.webPath"));
		request.setAttribute("imgPath", readPropertyPlaceholder.getProperty("dev.imgPath"));
	}
}
