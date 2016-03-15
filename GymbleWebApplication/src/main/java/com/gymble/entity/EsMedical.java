package com.gymble.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "es_user_medical")
public class EsMedical extends ESEntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_medical_id", unique = true, nullable = false)
	private Integer id;

	@NotEmpty
	@Column(name = "blood_group")
	private String bloodGroup;

	@NotEmpty
	@Column(name = "medicines_req")
	private String medicinesRequired;

	@NotEmpty
	@Column(name = "other_spec")
	private String otherSpec;

	@OneToOne(mappedBy = "medical", cascade = CascadeType.ALL)
	private EsStudent student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMedicinesRequired() {
		return medicinesRequired;
	}

	public void setMedicinesRequired(String medicinesRequired) {
		this.medicinesRequired = medicinesRequired;
	}

	public String getOtherSpec() {
		return otherSpec;
	}

	public void setOtherSpec(String otherSpec) {
		this.otherSpec = otherSpec;
	}

	public EsStudent getStudent() {
		return student;
	}

	public void setStudent(EsStudent student) {
		this.student = student;
	}

	public EsMedical() {
		// TODO Auto-generated constructor stub
	}
}
