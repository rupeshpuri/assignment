package com.assigment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	protected Date createdDate;

	@Column(name = "updated_date")
	@Temporal(TemporalType.DATE)
	@JsonIgnore
	protected Date updatedDate;

	@Column(name = "created_time")
	@Temporal(TemporalType.TIME)
	@JsonFormat(shape=Shape.STRING,pattern="HH:mm:ss")
	protected Date createdTime;

	@Column(name = "updated_time")
	@Temporal(TemporalType.TIME)
	@JsonFormat(shape=Shape.STRING,pattern="HH:mm:ss")
	@JsonIgnore
	protected Date updatedTime;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "Audit [createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}

	
}
