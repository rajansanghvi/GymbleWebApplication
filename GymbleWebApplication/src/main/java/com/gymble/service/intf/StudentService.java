package com.gymble.service.intf;

import java.util.List;

import com.gymble.entity.EsStudent;
import com.gymble.exception.EsDatabaseException;


public interface StudentService {

	EsStudent findById(int id);
	
	EsStudent findByUserCode(String code);
	
	void saveStudent(EsStudent user) throws EsDatabaseException;
	
	void updateStudent(EsStudent user);
	
	void deleteStudentByUserCode(String sso);

	List<EsStudent> findAllStudents(); 
	
	boolean isUserCodeUnique(Integer id, String sso);

}
