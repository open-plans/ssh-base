/**
 * 代号:隐无为 2017：厚溥
 * 文件名：HpHqlUtil.java
 * 日期：2017年6月8日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.util;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 用途：hql 帮助类
 */
public class HpHqlUtil {
	
	/**
	 *  @功能:获取WhereHql
	 *  @作者:柯栋 @代号:隐无为
	 *  @时间:2017年2月24日
	 *  @param map
	 *  @return  
	 */
	public static String getWhereHql(Map<String, Object>map){
		String whereHql="";
		for (Entry<String, Object> item : map.entrySet()) {
			whereHql+=" and "+item.getKey()+"=?";
		}
		return whereHql;
	}
	
	
	/**
	 *  @功能:获取get set 方法
	 *  @作者:柯栋 @代号:隐无为
	 *  @时间:2017年2月24日
	 *  @return  
	 */
	public static String getFieldMethod(String prefix,String field){
		String str=prefix+field.substring(0, 1).toUpperCase() + field.substring(1);
		return str;
	}
	

}
