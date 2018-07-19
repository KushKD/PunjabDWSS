package com.prwss.mis.admin.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="location_type_master",schema="prwss_main")
public class LocationTypeBean  implements Serializable{
	
	private static final long serialVersionUID = 134534545344L;
	
	
	@Id
	@GeneratedValue(generator = "location_type_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "location_type_seq", sequenceName = "prwss_main.location_type_seq")
	@Column(name = "location_type_id")
	private int locationTypeId;
	
	@Column(name = "name")
	private String locationName;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	/*@Column(name="crt_date")
	private Date currentDate;*/
	
	@Column(name="crt_by_user")
	private Integer createdByUSer;

	public int getLocationTypeId() {
		return locationTypeId;
	}

	public void setLocationTypeId(int locationTypeId) {
		this.locationTypeId = locationTypeId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getCreatedByUSer() {
		return createdByUSer;
	}

	public void setCreatedByUSer(Integer createdByUSer) {
		this.createdByUSer = createdByUSer;
	}

	@Override
	public String toString() {
		return "LocationTypeBean [locationTypeId=" + locationTypeId + ", locationName=" + locationName + ", activeFlag="
				+ activeFlag + ", startDate=" + startDate + ", endDate=" + endDate + ", createdByUSer=" + createdByUSer
				+ "]";
	}
	
	/*@Column(name="lst_updated_date")
	private Date lastUpdateDate;
	
	@Column(name="lst_updated_user")
	private int lastUpdateUsr;
	*/


}
