package com.jordanalphonso.timePuncher.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "basic_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@XmlRootElement
public class BasicInfo implements Serializable {

	@JsonView(JsonViews.Public.class)
	private long user_id;
	@JsonView(JsonViews.Public.class)
	private String firstname;
	@JsonView(JsonViews.Public.class)
	private String lastname;
	private String alt_email;
	@JsonView(JsonViews.Public.class)
	private String address;
	@JsonView(JsonViews.Public.class)
	private String city;
	@JsonView(JsonViews.Public.class)
	private String state;
	@JsonView(JsonViews.Public.class)
	private String zipcode;
	@JsonView(JsonViews.Public.class)
	private String phone;
	private User user;
	
	@Id
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	@Column(name = "firstname", nullable = false)
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name = "lastname", nullable = false)
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name = "alt_email")
	public String getAlt_email() {
		return alt_email;
	}
	public void setAlt_email(String alt_email) {
		this.alt_email = alt_email;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "zipcode")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", updatable = true, insertable = true, nullable = true)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
