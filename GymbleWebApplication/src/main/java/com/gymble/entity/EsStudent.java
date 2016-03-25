package com.gymble.entity;


import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.gymble.tos.EsStudentTO;

@Entity
@Table(name = "es_user")
public class EsStudent extends ESEntityBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "user_code")
	private String userCode;

	@NotEmpty
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@NotEmpty
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "address_id")
	private EsAddress address;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_info_id")
	private EsStudentInfo studentInfo;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "user_medical_id")
	private EsMedical medical;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "es_user_contact_rel", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id"))
	private List<EsContact> contacts;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "es_user_batch_rel", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "batch_id", referencedColumnName = "batch_id"))
	private List<EsBatch> esBatches ;

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

	// public ESContact getMobileContact() {
	// return mobileContact;
	// }
	//
	// public void setMobileContact(ESContact contact) {
	// this.mobileContact = contact;
	// this.mobileContact.setType(ContactType.MOBILE);
	// }
	//
	// public ESContact getEmailContact() {
	// return emailContact;
	// }
	//
	// public void setEmailContact(ESContact contact) {
	// this.emailContact = contact;
	// this.emailContact.setType(ContactType.MOBILE);
	// }

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

	public void setMedical(EsMedical medical) {
		this.medical = medical;
	}

	public List<EsContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<EsContact> contacts) {
		this.contacts = contacts;
	}

	public List<EsBatch> getEsBatches() {
		return esBatches;
	}

	public void setEsBatches(List<EsBatch> esBatches) {
		this.esBatches = esBatches;
	}

	public EsStudent(Boolean active, Date modified,
			String createdBy, String modifiedBy, Integer Version,
			EsMedical medical, String firstName, String middleName,
			String lastName, EsStudentInfo studentInfo, EsAddress esAddress,
			String usercode) {
		//super(active, modified, createdBy, modifiedBy, Version);
		this.address = esAddress;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.medical = medical;
		this.studentInfo = studentInfo;
		this.userCode = usercode;

	}

	public EsStudent() {
		studentInfo = new EsStudentInfo();
		medical = new EsMedical();
		address = new EsAddress();
	}

	// public Set<UserProfile> getUserProfiles() {
	// return userProfiles;
	// }
	//
	// public void setUserProfiles(Set<UserProfile> userProfiles) {
	// this.userProfiles = userProfiles;
	// }
	EsStudentTO convertToTO()
	{
		EsStudentTO esStudent = new EsStudentTO();
		esStudent.setFirstName(this.firstName);
		esStudent.setMiddleName(this.middleName);
		esStudent.setLastName(this.lastName);
		if (this.address != null)
		esStudent.setAddress(this.address);
		esStudent.setMedical(this.medical);
		if (this.userCode != null)
		esStudent.setUserCode(this.userCode);
		esStudent.setStudentInfo(this.studentInfo);
		//contacts
		
		return esStudent;
	}

}
