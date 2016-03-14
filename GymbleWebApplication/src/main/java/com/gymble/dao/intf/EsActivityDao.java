package com.gymble.dao.intf;

import java.util.List;

import com.gymble.entity.EsActivity;
import com.gymble.enumeration.Status;
import com.gymble.exception.EsDatabaseException;

public interface EsActivityDao {

    EsActivity findByName(String name) throws EsDatabaseException;
    
    Long save(EsActivity activity) throws EsDatabaseException;
    
    EsActivity findByCode(String code) throws EsDatabaseException;    
   
    EsActivity findById(Long id) throws EsDatabaseException;
    
    List<EsActivity> findAllByStatus(Status status) throws EsDatabaseException;
    
    void Update(EsActivity activity) throws EsDatabaseException;
}
