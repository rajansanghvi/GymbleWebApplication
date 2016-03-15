package com.gymble.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gymble.dao.intf.EsSequenceGeneratorDao;
import com.gymble.entity.EsSequenceGenerator;
import com.gymble.enumeration.SequenceTitle;
import com.gymble.exception.EsDatabaseException;

@Repository("esSequenceGeneratorDao")
public class EsSequenceGeneratorDaoImpl extends AbstractDao<Long, EsSequenceGenerator> implements EsSequenceGeneratorDao {

    @Override
    public EsSequenceGenerator fndByTitle(SequenceTitle title) throws EsDatabaseException {
        try
        {
            Criteria criteria = getSession().createCriteria(EsSequenceGenerator.class);
            criteria.add(Restrictions.eq("title", title));
            return (EsSequenceGenerator) criteria.uniqueResult();
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new EsDatabaseException("Database Exception while fetching the Sequence generator for " + title.name());
        }
    }

    @Override
    public void edit(EsSequenceGenerator sequenceGenerator) throws EsDatabaseException {
        try
        {
            getSession().update(sequenceGenerator);
        }
        catch(Exception e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new EsDatabaseException("Database Exception while updating the Sequence generator for " + sequenceGenerator.getTitle());
        }
    }

}
