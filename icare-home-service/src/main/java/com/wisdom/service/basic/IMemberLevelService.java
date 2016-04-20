package com.wisdom.service.basic;


import com.wisdom.dao.entity.MemberLevel;
import com.wisdom.entity.PageInfo;

public interface IMemberLevelService {
	//列表数据
	PageInfo list(MemberLevel memberLevel, PageInfo pageInfo);

	//新增等级
	int add(MemberLevel memberLevel);

	//根据主键获取对象
	MemberLevel get(Integer id);

	//保存修改
	void editMemberLevel(MemberLevel memberLevel);

	//根据主键删除
	void deleteMemberLevel(Integer id);
}
