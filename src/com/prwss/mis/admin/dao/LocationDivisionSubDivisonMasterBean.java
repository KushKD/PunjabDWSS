package com.prwss.mis.admin.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="location_div_subdiv_master",schema="prwss_main")
public class LocationDivisionSubDivisonMasterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3694265416283817699L;
	
	@Id
	@GeneratedValue(generator = "location_div_subdiv_master_div_subdiv_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "location_div_subdiv_master_div_subdiv_id_seq", sequenceName = "prwss_main.location_div_subdiv_master_div_subdiv_id_seq")
	@Column(name = "div_subdiv_id")
	private int DivSubDivId;
	
	@Column(name="div_subdiv_type")
	private Integer locationTypeId;
	
	@Column(name="parent_div_subdiv")
	private Integer parentLocation;
	
	@Column(name="active_flag")
	private Integer activeField;
	
	@Column(name="crt_by_usr")
	private Long createdByUSer;

	public int getDivSubDivId() {
		return DivSubDivId;
	}

	public void setDivSubDivId(int divSubDivId) {
		DivSubDivId = divSubDivId;
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
		return "LocationDivisionSubDivisonMasterBean [DivSubDivId=" + DivSubDivId + ", locationTypeId=" + locationTypeId
				+ ", parentLocation=" + parentLocation + ", activeField=" + activeField + ", createdByUSer="
				+ createdByUSer + "]";
	}
	
	
	

}
