package com.gymble.dao.intf;

import java.util.List;

import com.gymble.entity.EsStudent;

public interface EsStudentDao  {
	
	EsStudent findById(int id);

	EsStudent findByCode(String code);

	void save(EsStudent user);

	void deleteByUserCode(String sso);

	List<EsStudent> findAllUsers();

}
