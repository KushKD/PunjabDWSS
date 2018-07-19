package com.prwss.mis.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;

public class MISSessionBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	
	private static final long serialVersionUID = -7244001561429910601L;

	@Column
	private long enteredBy;
	private Timestamp enteredDate;
	private List<String> locationId;
	private String employeeName;
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public MISSessionBean(long enteredBy, Timestamp enteredDate, List<String> locationId, String employeeName) {
		super();
		this.enteredBy = enteredBy;
		this.enteredDate = enteredDate;
		this.locationId = locationId;
		this.employeeName = employeeName;
	}

	/**
	 * Please do not use this in live code... This is only for testing in JUnit Test cases
	 */
	public MISSessionBean() {
	}

	public long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Timestamp getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Timestamp enteredDate) {
		this.enteredDate = enteredDate;
	}

	public List<String> getLocationId() {
		return locationId;
	}

	public void setLocationId(List<String> locationId) {
		this.locationId = locationId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public String toString() {
		return "MISSessionBean [enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", locationId=" + locationId
				+ ", employeeName=" + employeeName + "]";
	}

	
}
