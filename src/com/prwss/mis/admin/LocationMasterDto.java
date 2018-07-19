/**
 * 
 */
package com.prwss.mis.admin;

/**
 * @author bhsingh
 *
 */
public class LocationMasterDto {

	private String locationName;
	private int locationTypeId;
	private int locationId;
	private long employeeId;
	private String employeeName;
	private Integer designationId;
	private String designationName;
	
	
	public Integer getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the locationTypeId
	 */
	public int getLocationTypeId() {
		return locationTypeId;
	}
	/**
	 * @param locationTypeId the locationTypeId to set
	 */
	public void setLocationTypeId(int locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationMasterDto [locationName=" + locationName + ", locationTypeId=" + locationTypeId
				+ ", locationId=" + locationId + "]";
	}
	
	
}
