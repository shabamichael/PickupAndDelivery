package com.pick.up.delivery.dispatcher;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Dispatcher implements Serializable {

	@Id
	@SequenceGenerator(name = "dispatcher_sequence",initialValue = 1001, allocationSize = 1,sequenceName = "dispatcher_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "dispatcher_sequence")
	private Long id ;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT")
	private String firstname;
	
	@Column(nullable = true, updatable = true, columnDefinition = "TEXT")
	private String otherName;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT")
	private String surname;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT", unique = true)
	private String identificationNo;
	
	@Column(nullable = false, updatable = true, columnDefinition = "TEXT", unique = true)
	private String telephone;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT", unique = true)
	private String username;
	
	@Column(nullable = false, updatable = true, columnDefinition = "TEXT")
	private String address;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT")
	private LocalDate dob;
	
	@Transient
	private int age;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT", unique = true)
	private String email;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false, updatable = false, columnDefinition = "TEXT")
	private String stateOfOrigin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getStateOfOrigin() {
		return stateOfOrigin;
	}

	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}

	public Dispatcher(String firstname, String otherName, String surname, String identificationNo, String telephone,
			String username, String address, LocalDate dob,  String email, Gender gender,
			String stateOfOrigin) {
		super();
		this.firstname = firstname;
		this.otherName = otherName;
		this.surname = surname;
		this.identificationNo = identificationNo;
		this.telephone = telephone;
		this.username = username;
		this.address = address;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.stateOfOrigin = stateOfOrigin;
	}

	public Dispatcher(Long id, String firstname, String otherName, String surname, String identificationNo,
			String telephone, String username, String address, LocalDate dob, String email, Gender gender,
			String stateOfOrigin) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.otherName = otherName;
		this.surname = surname;
		this.identificationNo = identificationNo;
		this.telephone = telephone;
		this.username = username;
		this.address = address;
		this.dob = dob;
		this.email = email;
		this.gender = gender;
		this.stateOfOrigin = stateOfOrigin;
	}

	public Dispatcher() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Dispatcher [id=" + id + ", firstname=" + firstname + ", otherName=" + otherName + ", surname=" + surname
				+ ", identificationNo=" + identificationNo + ", telephone=" + telephone + ", username=" + username
				+ ", address=" + address + ", dob=" + dob + ", age=" + age + ", email=" + email + ", gender=" + gender
				+ ", stateOfOrigin=" + stateOfOrigin + "]";
	}
	
	
	
	
}
