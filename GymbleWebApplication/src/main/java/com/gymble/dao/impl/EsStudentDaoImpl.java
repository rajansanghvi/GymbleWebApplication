package com.gymble.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gymble.dao.intf.EsStudentDao;
import com.gymble.entity.EsStudent;

@Repository("studentDao")
public class EsStudentDaoImpl extends AbstractDao<Integer, EsStudent> implements EsStudentDao {

	@Override
	public EsStudent findById(int id) {
		EsStudent user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getAddress());
			Hibernate.initialize(user.getStudentInfo());
			Hibernate.initialize(user.getMedical());
		}
		return user;
	}

	@Override
	public EsStudent findByCode(String code) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userCode", code));
		EsStudent user = (EsStudent)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getAddress());
			Hibernate.initialize(user.getStudentInfo());
			Hibernate.initialize(user.getMedical());		}
		return user;
	}

	@Override
	public void save(EsStudent user) {
		persist(user);
	}

	@Override
	public void deleteByUserCode(String userCode) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userCode", userCode));
		EsStudent user = (EsStudent)crit.uniqueResult();
		delete(user);
	}

	@Override
	public List<EsStudent> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<EsStudent> users = (List<EsStudent>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

}