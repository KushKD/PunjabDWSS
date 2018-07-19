/**
 * 
 */
package com.prwss.mis.admin.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * @author bhsingh
 *
 */

@Entity

@Table(name="location_details",schema="prwss_main")
public class LocationDetailsBean implements Serializable{

	private static final long serialVersionUID = -3635290815L;

	@Id
	@GeneratedValue(generator = "location_details_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "location_details_seq", sequenceName = "prwss_main.location_details_seq")
	@Column(name = "location_detail_id")
	private int locationDetailsId;
	
	
	
	@OneToOne(targetEntity=LocationMasterBean.class)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="location_id")
	private LocationMasterBean locationMasterBean;
	
	@Column(name = "location_name")
	private String locationName;
	
	

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
	 * @return the locationDetailsId
	 */
	public int getLocationDetailsId() {
		return locationDetailsId;
	}

	/**
	 * @param locationDetailsId the locationDetailsId to set
	 */
	public void setLocationDetailsId(int locationDetailsId) {
		this.locationDetailsId = locationDetailsId;
	}
	
	/**
	 * @return the locationMasterBean
	 */
	public LocationMasterBean getLocationMasterBean() {
		return locationMasterBean;
	}
	/**
	 * @param locationMasterBean the locationMasterBean to set
	 */
	public void setLocationMasterBean(LocationMasterBean locationMasterBean) {
		this.locationMasterBean = locationMasterBean;
	}
	
	@Override
	public String toString() {
		return "LocationDetailsBean [locationDetailsId=" + locationDetailsId + ", locationMasterBean="
				+ locationMasterBean + ", locationName=" + locationName + "]";
	}


}
