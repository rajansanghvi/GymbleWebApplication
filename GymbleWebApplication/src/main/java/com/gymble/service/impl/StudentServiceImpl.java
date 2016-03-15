package com.gymble.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymble.dao.intf.EsSequenceGeneratorDao;
import com.gymble.dao.intf.EsStudentDao;
import com.gymble.entity.EsSequenceGenerator;
import com.gymble.entity.EsStudent;
import com.gymble.enumeration.SequenceTitle;
import com.gymble.exception.EsDatabaseException;
import com.gymble.service.intf.StudentService;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private EsStudentDao dao;
	
	@Autowired
	private EsSequenceGeneratorDao esSequenceGeneratorDao;

	@Override
	public EsStudent findById(int id) {
		return dao.findById(id);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public EsStudent findByUserCode(String code) {
		EsStudent user = dao.findByCode(code);
		return user;
	}

	@Override
	public void saveStudent(EsStudent user) throws EsDatabaseException {
		EsSequenceGenerator sequenceGenerator = esSequenceGeneratorDao.fndByTitle(SequenceTitle.EsRegisteration);
        
        user.setUserCode(sequenceGenerator.getCodePreffix() + (sequenceGenerator.getValue() + 1));
        sequenceGenerator.setValue(sequenceGenerator.getValue() + 1);
        esSequenceGeneratorDao.edit(sequenceGenerator);
		user.setActive(true);
		user.setDateTimeModified(new Date());
		user.setModifiedBy("admin");
		user.setCreatedBy("admin");
		user.getStudentInfo().setActive(true);
		user.getStudentInfo().setActive(true);
		//user.getStudentInfo().setCreated(new Date());
		user.getStudentInfo().setDateTimeModified(new Date());
		user.getStudentInfo().setCreatedBy("Admin");
		user.getStudentInfo().setModifiedBy("admin");
		user.getMedical().setActive(true);
		user.getMedical().setActive(true);
		//user.getMedical().setCreated(new Date());
		user.getMedical().setDateTimeModified(new Date());
		user.getMedical().setCreatedBy("Admin");
		user.getMedical().setModifiedBy("admin");
		user.getAddress().setActive(true);
		//user.getAddress().setCreated(new Date());
		user.getAddress().setDateTimeModified(new Date());
		user.getAddress().setCreatedBy("Admin");
		user.getAddress().setModifiedBy("admin");
	//	EsStudent es = new EsStudent(true, new Date(),
		//		user.getDateTimeModified(), "admin", "admin", 1,
			//	user.getMedical(), user.getFirstName(), user.getMiddleName(),
				//user.getLastName(), user.getStudentInfo(), user.getAddress(),
				//user.getUserCode());
		//es.setContacts(user.getContacts());
		dao.save(user);

	}

	@Override
	public void updateStudent(EsStudent user) {
		EsStudent entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setUserCode(user.getUserCode());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setMiddleName(user.getMiddleName());
			entity.setStudentInfo(user.getStudentInfo());
			entity.setAddress(user.getAddress());
			entity.setMedical(user.getMedical());
		}
	}

	@Override
	public void deleteStudentByUserCode(String sso) {
		dao.deleteByUserCode(sso);

	}

	@Override
	public List<EsStudent> findAllStudents() {
		return dao.findAllUsers();
	}

	@Override
	public boolean isUserCodeUnique(Integer id, String code) {
		EsStudent user = findByUserCode(code);
		return (user == null || ((id != null) && (user.getId() == id)));
	}
}
