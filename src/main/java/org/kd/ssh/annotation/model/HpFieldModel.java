/**
 * 代号:隐无为 2017：厚溥
 * 文件名：HpFiledModel.java
 * 日期：2017年6月8日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.annotation.model;

/**
 * 用途：注解字段实体类
 */
public class HpFieldModel {

	private String fieldName;

	private String order;

	private int orderIndex;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public HpFieldModel(String fieldName, String order, int orderIndex) {
		this.fieldName = fieldName;
		this.order = order;
		this.orderIndex = orderIndex;
	}
	
	

	
	
}
