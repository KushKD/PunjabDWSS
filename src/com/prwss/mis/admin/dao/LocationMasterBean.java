package com.prwss.mis.admin.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author bhsingh
 *
 */
@Entity
@Table(name="location_master",schema="prwss_main")

public class LocationMasterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134534545344L;
	
	@Id
	@GeneratedValue(generator = "location_master_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "location_master_seq", sequenceName = "prwss_main.location_master_seq")
	@Column(name = "location_id")
	private int locationId;
	
	@Column(name="location_type_id")
	private Integer locationTypeId;
	
	@Column(name="parent_location")
	private Integer parentLocation;
	
	@Column(name="active_flag")
	private Integer activeField;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="crt_by_user")
	private Long createdByUSer;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Integer getLocationTypeId() {
		return locationTypeId;
	}

	public void setLocationTypeId(Integer locationTypeId) {
		this.locationTypeId = locationTypeId;
	}

	public Integer getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(Integer parentLocation) {
		this.parentLocation = parentLocation;
	}

	public Integer getActiveField() {
		return activeField;
	}

	public void setActiveField(Integer activeField) {
		this.activeField = activeField;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getCreatedByUSer() {
		return createdByUSer;
	}

	public void setCreatedByUSer(Long createdByUSer) {
		this.createdByUSer = createdByUSer;
	}

	@Override
	public String toString() {
		return "LocationMasterBean [locationId=" + locationId + ", locationTypeId=" + locationTypeId
				+ ", parentLocation=" + parentLocation + ", activeField=" + activeField + ", endDate=" + endDate
				+ ", startDate=" + startDate + ", createdByUSer=" + createdByUSer + "]";
	}


	
}
