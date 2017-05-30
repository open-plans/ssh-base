/**
 * 代号:隐无为 2017：厚溥
 * 文件名：PropertyPlaceholder.java
 * 创建人：柯栋
 * 日期：2017年2月27日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 用途：读取属性文件
 */
public class ReadPropertyPlaceholder extends PropertyPlaceholderConfigurer {
	private  Map<String, String> propertyMap;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		propertyMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			propertyMap.put(keyStr, value);
		}
	}

	// 通过属性名  获取属性值
	public  Object getProperty(String name) {
		return propertyMap.get(name);
	}

	
}
