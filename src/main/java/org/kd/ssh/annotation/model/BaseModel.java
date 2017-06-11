/**
 * 代号:隐无为 2017：厚溥
 * 文件名：BaseModel.java
 * 日期：2017年6月10日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.annotation.model;

import java.util.Date;

import org.kd.ssh.annotation.HpField;
import org.kd.ssh.annotation.HpIsDeleteField;

/**
 * 用途：基础model
 */
public class BaseModel {
	@HpField(order="desc",orderIndex=0)
    private Date createTime;// 创建时间
    @HpField(order="desc",orderIndex=1)
    private Date updateTime;// 更新时间
    @HpIsDeleteField
    private Integer isDelete=1;// 删除标志  0 删  1 未删
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
    
    
}
