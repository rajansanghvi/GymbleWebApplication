package com.gymble.tos;

import java.util.Date;
import java.util.Random;

import com.gymble.entity.*;



public class EsStudentTO {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userCode;

	private String firstName;

	private String middleName;

	private String lastName;

	private EsAddress address;

	private EsStudentInfo studentInfo;

	private EsMedical medical;

	private String phone;

	private String email;

	private String emergencyContact;
	
//	private EsActivity esActivity;

//	public String getActivityName() {
//		return ActivityName;
//	}
//
//	public void setActivityName(String activityName) {
//		ActivityName = activityName;
//	}

//	public EsActivity getEsActivity() {
//		return esActivity;
//	}
//
//	public void setEsActivity(EsActivity esActivity) {
//		this.esActivity = esActivity;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserCode() {
		this.userCode = new Random().toString();
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public EsAddress getAddress() {
		return address;
	}

	public void setAddress(EsAddress address) {
		this.address = address;
	}

	public EsStudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(EsStudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public EsMedical getMedical() {
		return medical;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMedical(EsMedical medical) {
		this.medical = medical;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public EsStudentTO(Boolean active, Date created, Date modified,
			String createdBy, String modifiedBy, Integer Version,
			EsMedical medical, String firstName, String middleName,
			String lastName, EsStudentInfo studentInfo, EsAddress esAddress,
			String usercode) {

		this.address = esAddress;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.medical = medical;
		this.studentInfo = studentInfo;
		this.userCode = usercode;

	}

	public EsStudentTO() {
		studentInfo = new EsStudentInfo();
		medical = new EsMedical();
		address = new EsAddress();
	}

}
