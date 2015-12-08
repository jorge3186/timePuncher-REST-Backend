package com.jordanalphonso.timePuncher.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Hours implements Serializable {
	
	@JsonView(JsonViews.Public.class)
	private long user_id;
	@JsonView(JsonViews.Public.class)
	private BigDecimal hours;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getHours() {
		return hours;
	}
	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

}
