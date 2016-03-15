package com.gymble.service.intf;

import java.util.List;

import com.gymble.entity.EsActivity;
import com.gymble.enumeration.Status;
import com.gymble.exception.EsBadDataException;
import com.gymble.exception.EsBusinessException;
import com.gymble.exception.EsDataValidationException;

public interface EsActivityService {
    /**
     * It is used to create a new activity by the Client. Client can add a new activity in the system.
     * @param activity EsActivity Object with all the Required and valid data.
     * @return The newly created activity with the Database Generated id and System Generated Code
     * @throws EsDataValidationException 
     * @throws EsBusinessException 
     */
    Long create(EsActivity activity, String username) throws EsDataValidationException, EsBusinessException;
    
    /**
     * It is used to fetch the details of an activity by its code.
     * @param Code Unique code of the activity assigned by the system during activity creation.
     * @return EsActivity Object with all the required data
     * @throws EsBadDataException 
     * @throws EsBusinessException 
     */
    EsActivity findByCode(String Code) throws EsBadDataException, EsBusinessException;
    
    /**
     * It is used to fetch the details of an activity using the database generated Identifier.
     * @param id Database generated Unique Identifier of the system
     * @return EsActivity object will all the required data
     * @throws EsBadDataException 
     * @throws EsBusinessException 
     */
    EsActivity findById (Long id) throws EsBadDataException, EsBusinessException;
    
    /**
     * This is used to fetch all the Activities from the system using its Status i.eeither Active, Inactive or All
     * @return List of EsActivity Object with all the required data of each activity
     * @throws EsBusinessException 
     */
    List<EsActivity> findAllByStatus(Status status) throws EsBusinessException;
    
    /**
     * Used to update the Activity by the client. Client can only update the active activity details using this method 
     * @param activity Updated EsActivity Object with all the required details
     * @return The updated activity with the Database Generated id and System Generated Code
     * @throws EsBusinessException 
     * @throws EsDataValidationException 
     * @throws EsBadDataException 
     */
    EsActivity edit(EsActivity activity, String username) throws EsBusinessException, EsDataValidationException, EsBadDataException;
    
    /**
     * This method is used to in-activate the activity on a temporary basis. Client can again make the activity as active if required.
     * @param code Unique system generated code assign to each activity while its creation.
     * @return The updated activity with the Database Generated id and System Generated Code
     * @throws EsBadDataException 
     * @throws EsBusinessException 
     */
    EsActivity InactivateByCode(String code, String username) throws EsBadDataException, EsBusinessException;
    
    /**
     * this method is used to activate an inactive activity.
     * @param code code Unique system generated code assign to each activity while its creation.
     * @return The updated activity with the Database Generated id and System Generated Code
     * @throws EsBadDataException 
     * @throws EsBusinessException 
     */
    EsActivity AcitvateByCode(String code, String username) throws EsBadDataException, EsBusinessException;
    
    /**
     * This is used to permanently delete the activity from the system.A user can not get the activity back once deleted.
     * @param code code Unique system generated code assign to each activity while its creation.
     * @return The updated activity with the Database Generated id and System Generated Code
     */
    void DeleteByCode(String code);
    
    EsActivity GetActivityByName(String name) throws EsBadDataException, EsBusinessException;
}
