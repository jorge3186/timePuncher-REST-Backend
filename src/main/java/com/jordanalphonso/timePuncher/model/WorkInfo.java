package com.jordanalphonso.timePuncher.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "work_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class WorkInfo implements Serializable {
	
	@JsonView(JsonViews.Public.class)
	private long user_id;
	@JsonView(JsonViews.Public.class)
	private long employeeId;
	private BigDecimal  hourly_wage;
	@JsonView(JsonViews.Public.class)
	private BigDecimal salary_wage;
	@JsonView(JsonViews.Public.class)
	private String department;
	private User user;
	
	@Id
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	@Column(name = "employeeId")
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public BigDecimal getHourly_wage() {
		return hourly_wage;
	}
	public void setHourly_wage(BigDecimal hourly_wage) {
		this.hourly_wage = hourly_wage;
	}
	
	@Column(name = "salary_wage")
	public BigDecimal getSalary_wage() {
		return salary_wage;
	}
	public void setSalary_wage(BigDecimal salary_wage) {
		this.salary_wage = salary_wage;
	}
	
	@Column(name = "department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = true, insertable = true, updatable = true)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
