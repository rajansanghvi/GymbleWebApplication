package com.gymble.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gymble.entity.EsContact;
import com.gymble.entity.EsStudent;
import com.gymble.enumeration.ContactType;
import com.gymble.exception.EsDatabaseException;
import com.gymble.service.intf.StudentService;
import com.gymble.tos.EsStudentTO;


@Controller
@RequestMapping("/registeration")
public class EsRegistrationController {
	@Autowired
	StudentService studentService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing users.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(false);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value = { "/", "/studentlist" }, method = RequestMethod.GET)
	public String listStudents(ModelMap model) {

		List<EsStudent> esstudents = studentService.findAllStudents();
		model.addAttribute("esstudents", esstudents);
		return "studentslist";
	}
	@RequestMapping(value = { "/newstudent" }, method = RequestMethod.GET)
	public String newStudent(ModelMap model) {

		EsStudentTO esstudent = new EsStudentTO();
		model.addAttribute("esstudent", esstudent);
		model.addAttribute("edit", false);
		return "studentregistration";
	}

	@RequestMapping(value = { "/newstudent" }, method = RequestMethod.POST)
	public String saveStudent(@Valid EsStudentTO esstudent,
			BindingResult result, ModelMap model) throws EsDatabaseException {
		System.out.println("binding" + result);
		if (result.hasErrors()) {
			model.addAttribute("esstudent", esstudent);
			return "studentregistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be
		 * implementing custom @Unique annotation and applying it on field [sso]
		 * of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 */
		if (!studentService.isUserCodeUnique(esstudent.getId(),
				esstudent.getUserCode())) {
			FieldError codeError = new FieldError("user", "user Code",
					messageSource.getMessage("non.unique.ssoId",
							new String[] { esstudent.getUserCode() },
							Locale.getDefault()));
			result.addError(codeError);
			return "studentregistration";
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

		model.addAttribute("success", "User " + esstudent.getFirstName() + " "
				+ esstudent.getLastName() + " registered successfully");
		// return "success";
		return "studentsuccess";
	}
	@RequestMapping(value = { "/edit-esstudent-{userCode}" }, method = RequestMethod.GET)
	public String editStudent(@PathVariable String userCode, ModelMap model) {
		EsStudent esstudent = studentService.findByUserCode(userCode);
		model.addAttribute("esstudent", esstudent);
		model.addAttribute("edit", true);
		return "studentregistration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-esstudent-{userCode}" }, method = RequestMethod.POST)
	public String updateStudent(@Valid EsStudent esstudent,
			BindingResult result, ModelMap model, @PathVariable String userCode) {

		if (result.hasErrors()) {
			return "studentregistration";
		}

		/*
		 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
		 * UI which is a unique key to a User.
		 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
		 * FieldError ssoError =new
		 * FieldError("user","ssoId",messageSource.getMessage
		 * ("non.unique.ssoId", new String[]{user.getSsoId()},
		 * Locale.getDefault())); result.addError(ssoError); return
		 * "registration"; }
		 */

		// userService.updateUser(user);
		studentService.updateStudent(esstudent);

		model.addAttribute("success", "User " + esstudent.getFirstName() + " "
				+ esstudent.getLastName() + " updated successfully");
		return "studentsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-esstudent-{userCode}" }, method = RequestMethod.GET)
	public String deleteStudent(@PathVariable String userCode) {
		// userService.deleteUserBySSO(ssoId);
		studentService.deleteStudentByUserCode(userCode);
		return "redirect:/studentlist";
	}


}
