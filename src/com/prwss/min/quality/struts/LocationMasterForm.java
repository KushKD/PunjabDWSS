/**
 * 
 */
package com.prwss.min.quality.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author bhsingh
 *
 */
public class LocationMasterForm extends ValidatorForm {

	private static final long serialVersionUID = 881376213534302058L;

	private String locationName;
	private String locationType;
	private String parentLocation;
	private String status;
	private String startDate;
	private String endDate;
	private int empId;
	private String locationId;
	
	
	

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	 * @return the parentLocation
	 */
	public String getParentLocation() {
		return parentLocation;
	}
	/**
	 * @param parentLocation the parentLocation to set
	 */
	public void setParentLocation(String parentLocation) {
		this.parentLocation = parentLocation;
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
		return "LocationMasterForm [locationName=" + locationName + ", locationType=" + locationType
				+ ", parentLocation=" + parentLocation + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", empId=" + empId + ", locationId=" + locationId + "]";
	}
	
	
	
}
