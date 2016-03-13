package com.gymble.dao.intf;

import com.gymble.entity.EsSequenceGenerator;
import com.gymble.enumeration.SequenceTitle;
import com.gymble.exception.EsDatabaseException;



public interface EsSequenceGeneratorDao {
    
    EsSequenceGenerator fndByTitle(SequenceTitle title) throws EsDatabaseException;
    
    void edit(EsSequenceGenerator sequenceGenerator) throws EsDatabaseException;
}
