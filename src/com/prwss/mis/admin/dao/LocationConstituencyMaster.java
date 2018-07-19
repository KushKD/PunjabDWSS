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
@Table(name="location_constituency_master",schema="prwss_main")
public class LocationConstituencyMaster implements Serializable {
	
	//location_constituency_master_constituency_id_seq

	/**
	 * 
	 */
	private static final long serialVersionUID = -6467562378505063822L;
	
	@Id
	@GeneratedValue(generator = "location_constituency_master_constituency_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "location_constituency_master_constituency_id_seq", sequenceName = "prwss_main.location_constituency_master_constituency_id_seq")
	@Column(name = "constituency_id")
	private int ConstituencyId;
	
	@Column(name="location_type")
	private Integer locationTypeId;
	
	@Column(name="parent_location")
	private Integer parentLocation;
	
	@Column(name="active_flag")
	private Integer activeField;
	
	
	
	@Column(name="crt_by_usr")
	private Long createdByUSer;



	public int getConstituencyId() {
		return ConstituencyId;
	}



	public void setConstituencyId(int constituencyId) {
		ConstituencyId = constituencyId;
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



	public Long getCreatedByUSer() {
		return createdByUSer;
	}



	public void setCreatedByUSer(Long createdByUSer) {
		this.createdByUSer = createdByUSer;
	}

 

	@Override
	public String toString() {
		return "LocationConstituencyMaster [ConstituencyId=" + ConstituencyId + ", locationTypeId=" + locationTypeId
				+ ", parentLocation=" + parentLocation + ", activeField=" + activeField + ", createdByUSer="
				+ createdByUSer + "]";
	}
	
	
	
	
	

}
