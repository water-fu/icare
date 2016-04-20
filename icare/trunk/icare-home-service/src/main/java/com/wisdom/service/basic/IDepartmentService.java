package com.wisdom.service.basic;

import com.wisdom.dao.entity.Department;
import com.wisdom.entity.PageInfo;

public interface IDepartmentService {
	  /**
     * 列表数据
     * @param
     * @param pageInfo
     * @return
     */
    PageInfo list(Department department, PageInfo pageInfo);

    /**
     * 新增科室
     * @param
     * @return
     */
    int addDepartment(Department department);

    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    Department get(Integer id);

    /**
     * 保存修改
     * @param
     */
    void editDepartment(Department department);

    /**
     * 删除
     * @param id
     */
    void deleteDepartment(Integer id);
}
