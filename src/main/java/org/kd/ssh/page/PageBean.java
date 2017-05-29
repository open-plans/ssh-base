/**
 * 代号:隐无为 2017：厚溥
 * 文件名：BaseDao.java
 * 创建人：柯栋
 * 日期：2017年3月29日
 * 修改人：
 * 描述：
 */
package org.kd.ssh.page;

import java.util.List;

/**
 * 用途：分页实体类
 */
public class PageBean<T> {

	private Integer pageSize = 10; // 页数 10 一页 10条
	private Integer pageNum=1; // 当前页码
	private Integer pages; // 总共多少页
	private Long total; // 总共多少条数据
	private List<T> list; // 数据集合

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPages() {
	
		return pages;
	}

	public void setPages(Integer pages) {
		
		this.pages = pages;
	}



	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageBean(Integer pageSize, Integer pageNum,  Long total, List<T> list) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.total = total;
		this.list = list;
		Integer pageCount=(int) (total/pageSize);
		if(total%pageSize>0){
			pageCount+=1;
		}
		this.pages=pageCount;
	}

	public PageBean() {
	}

	
	

}
