/**
 * 代号:隐无为 2017：厚溥
 * 文件名：HpIsDeleteFieldModel.java
 * 日期：2017年6月10日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.annotation.model;
/**
 * 用途：注解字段实体类
 */
public class HpIsDeleteFieldModel {

	private String fieldName;
	
	private int  fieldValue;

	private int  alreadyDeleteValue;
	
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}

	public int getAlreadyDeleteValue() {
		return alreadyDeleteValue;
	}

	public void setAlreadyDeleteValue(int alreadyDeleteValue) {
		this.alreadyDeleteValue = alreadyDeleteValue;
	}

	public HpIsDeleteFieldModel(String fieldName, int fieldValue, int alreadyDeleteValue) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.alreadyDeleteValue = alreadyDeleteValue;
	}


	
	
	
}
