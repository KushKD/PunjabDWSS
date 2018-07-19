/**
 * 
 */
package com.prwss.min.quality.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author bhsingh
 *
 */
public class LocationTypeForm extends ValidatorForm {
	
	private static final long serialVersionUID = 8813762132344302058L;
	
	private String locationType;
	private String status;
	private String startDate;
	private String endDate;
	private int empId;
	private String locationTypeId;
	
	
	
	
	
	/**
	 * @return the locationTypeId
	 */
	public String getLocationTypeId() {
		return locationTypeId;
	}
	/**
	 * @param locationTypeId the locationTypeId to set
	 */
	public void setLocationTypeId(String locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationTypeForm [locationType=" + locationType + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", empId=" + empId + ", locationTypeId=" + locationTypeId + "]";
	}

	
	
}
