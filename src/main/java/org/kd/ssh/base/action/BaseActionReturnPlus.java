package org.kd.ssh.base.action;

import org.kd.ssh.base.service.BaseService;
import org.kd.ssh.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("all")
public class BaseActionReturnPlus<T> extends BaseAction<T> {

	@Autowired
    BaseService<T> baService;

	protected Integer kdField;

	/**
	 * @功能: 设置id属性
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public Integer setField() {
		//return getIdField("tId");
		return getIdField("Id");
	}

	/**
	 * @功能: 获取id
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public Integer getIdField(String fieldName) {
		Integer tid = null;
		try {
			tid = (Integer) model.getClass().getDeclaredMethod("get" + fieldName, null).invoke(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tid;
	}

	/**
	 * @功能:添加页面
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String saveUI() {
		return "saveUI";
	}

	/**
	 * @功能:编辑页面
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String updateUI() {
		this.kdField = this.setField();
		request.setAttribute("model", baService.getById(kdField));// 在准备跳转的页面上放值${user.uId}
		return "updateUI";
	}

	

	/**
	 * @功能:查询所有
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String list() {
		PageBean page = baService.queryPageList(pageNum, pageSize);
		request.setAttribute("page", page);
		return "list";
	}

	public String allList() {
		request.setAttribute("list", baService.queryAllList());
		return "allList";
	}

	/**
	 * @功能:获取单个实体类数据
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String getById() {
		request.setAttribute("flag", "kd");// 在准备跳转的页面上放值${flag}
		return "list";
	}

	/**
	 * @功能:保存
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String save() {
		baService.save(model);
		return "toList";
	}

	/**
	 * @功能:删除
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String delete() {
		baService.delete(this.setField());
		return "toList";
	}

	/**
	 * @功能:修改
	 * @作者:柯栋 @代号:隐无为
	 * @时间:2017年3月29日
	 * @return
	 */
	public String update() {
		baService.update(model);
		return "toList";
	}

	public Integer gettId() {
		return kdField;
	}

	public void settId(Integer tId) {
		this.kdField = tId;
	}

}
