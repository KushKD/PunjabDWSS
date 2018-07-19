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

@Entity
@Table(name="location_constituency_details",schema="prwss_main")
public class LocationConstituencyDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6727332269110961946L;
	
	@Id
	@GeneratedValue(generator = "location_constituency_details_constituency_detail_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "location_constituency_details_constituency_detail_id_seq", sequenceName = "prwss_main.location_constituency_details_constituency_detail_id_seq")
	@Column(name = "constituency_detail_id")
	private int ConstituencyDetailsId;
	
	
	
	@OneToOne(targetEntity=LocationConstituencyMaster.class)
	@JoinColumn(name="constituency_id", nullable=false)
	private LocationConstituencyMaster locationConstituencyBean;
	
	@Column(name = "constituency_name")
	private String ConstituencyDetailsName;

	public int getConstituencyDetailsId() {
		return ConstituencyDetailsId;
	}

	public void setConstituencyDetailsId(int constituencyDetailsId) {
		ConstituencyDetailsId = constituencyDetailsId;
	}

	public LocationConstituencyMaster getLocationConstituencyBean() {
		return locationConstituencyBean;
	}

	public void setLocationConstituencyBean(LocationConstituencyMaster locationConstituencyBean) {
		this.locationConstituencyBean = locationConstituencyBean;
	}

	public String getConstituencyDetailsName() {
		return ConstituencyDetailsName;
	}

	public void setConstituencyDetailsName(String constituencyDetailsName) {
		ConstituencyDetailsName = constituencyDetailsName;
	}

	@Override
	public String toString() {
		return "LocationConstituencyDetails [ConstituencyDetailsId=" + ConstituencyDetailsId
				+ ", locationConstituencyBean=" + locationConstituencyBean + ", ConstituencyDetailsName="
				+ ConstituencyDetailsName + "]";
	}
	
	

}
