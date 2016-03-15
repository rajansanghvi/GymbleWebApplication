package com.gymble.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "es_user_info")
public class EsStudentInfo extends ESEntityBase {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_info_id", unique = true, nullable = false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "dob", unique = true)
	private Date DOB;

	@NotEmpty
	@Column(name = "guardian_name", unique = true)
	private String guardianName;

	@OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
	private EsStudent student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public EsStudent getStudent() {
		return student;
	}

	public void setStudent(EsStudent student) {
		this.student = student;
	}

	public EsStudentInfo() {
		// TODO Auto-generated constructor stub
	}
}
