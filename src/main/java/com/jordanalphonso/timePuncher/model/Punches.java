package com.jordanalphonso.timePuncher.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "punches")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Punches implements Serializable {
	
	private long punch_id;
	@JsonView(JsonViews.Public.class)
	private long user_id;
	@JsonView(JsonViews.Public.class)
	private String status = "IN";
	@JsonView(JsonViews.Public.class)
	private String time;
	@JsonView(JsonViews.Public.class)
	private String notes;
	private User user;
	
	@Id
	@Column(name = "punch_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getPunch_id() {
		return punch_id;
	}
	public void setPunch_id(long punch_id) {
		this.punch_id = punch_id;
	}
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	@Column(name = "status", nullable = false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "time")
	@Type(type = "java.lang.String")
	public String getTime() {
				return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Column(name = "notes")
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", updatable = false, nullable = false, insertable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
