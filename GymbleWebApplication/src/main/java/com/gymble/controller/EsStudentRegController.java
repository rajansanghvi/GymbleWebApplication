package com.gymble.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gymble.entity.EsContact;
import com.gymble.entity.EsStudent;
import com.gymble.enumeration.ContactType;
import com.gymble.exception.EsDatabaseException;
import com.gymble.service.intf.StudentService;
import com.gymble.tos.EsStudentTO;

@RestController
public class EsStudentRegController {
	@Autowired
	StudentService studentService;
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/newstudent/", method = RequestMethod.GET)
    public ResponseEntity<List<EsStudent>> listAllUsers() {
        List<EsStudent> users = studentService.findAllStudents();
        if(users.isEmpty()){
            return new ResponseEntity<List<EsStudent>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<EsStudent>>(users, HttpStatus.OK);
        
    }
    //-------------------Retrieve Single User--------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EsStudent> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        EsStudent user = studentService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<EsStudent>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EsStudent>(user, HttpStatus.OK);
    }
//-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/newstudent/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody EsStudentTO esstudent,UriComponentsBuilder ucBuilder) throws EsDatabaseException {
        System.out.println("Creating User " + esstudent.getFirstName());
 
        if (!studentService.isUserCodeUnique(esstudent.getId(),
				esstudent.getUserCode())) {
            System.out.println("A User with name " + esstudent.getFirstName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        EsStudent esStudentEntity = new EsStudent();
		esStudentEntity.setFirstName(esstudent.getFirstName());
		esStudentEntity.setLastName(esstudent.getLastName());
		esStudentEntity.setMiddleName(esstudent.getMiddleName());
		esStudentEntity.setAddress(esstudent.getAddress());
		esStudentEntity.setMedical(esstudent.getMedical());
		esStudentEntity.setStudentInfo(esstudent.getStudentInfo());
		List<EsContact> esContacts = new ArrayList<EsContact>();
		EsContact ePhone = new EsContact();
		ePhone.setData(esstudent.getPhone());
		ePhone.setType(ContactType.MOBILE);
		ePhone.setModifiedBy("admin");
		ePhone.setActive(true);
		//ePhone.setCreated(new Date());
		ePhone.setCreatedBy("admin");
		esContacts.add(ePhone);
		EsContact esEmail = new EsContact();
		esEmail.setData(esstudent.getEmail());
		esEmail.setType(ContactType.EMAIL);
		esEmail.setModifiedBy("admin");
		esEmail.setActive(true);
		//esEmail.setCreated(new Date());
		esEmail.setCreatedBy("admin");
		esContacts.add(esEmail);
		EsContact esEmergency = new EsContact();
		esEmergency.setData(esstudent.getEmergencyContact());
		esEmergency.setType(ContactType.EMERGENCY);
		esEmergency.setModifiedBy("admin");
		esEmergency.setActive(true);
		//esEmergency.setCreated(new Date());
		esEmergency.setCreatedBy("admin");
		esContacts.add(esEmergency);
		esStudentEntity.setContacts(esContacts);
		studentService.saveStudent(esStudentEntity);

 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(esStudentEntity.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EsStudent> updateUser(@PathVariable("id") int id, @RequestBody EsStudent user) {
        System.out.println("Updating User " + id);
         
        EsStudent currentUser = studentService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<EsStudent>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setFirstName(user.getFirstName());  
        currentUser.setAddress(user.getAddress());
        //currentUser.setEmail(user.get);
         
        studentService.updateStudent(currentUser); 
        return new ResponseEntity<EsStudent>(currentUser, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EsStudent> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        EsStudent user = studentService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<EsStudent>(HttpStatus.NOT_FOUND);
        }
 
        studentService.deleteStudentByUserCode(user.getUserCode());
        return new ResponseEntity<EsStudent>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Users --------------------------------------------------------
     
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<EsStudent> deleteAllUsers() {
//        System.out.println("Deleting All Users");
// 
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
// 

}
