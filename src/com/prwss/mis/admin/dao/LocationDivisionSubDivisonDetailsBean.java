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

@Table(name="location_div_subdiv_details",schema="prwss_main")
public class LocationDivisionSubDivisonDetailsBean implements Serializable {
	
		private static final long serialVersionUID = -3635211115L;

		@Id
		@GeneratedValue(generator = "location_div_subdiv_details_div_subdiv_detail_id_seq", strategy = GenerationType.AUTO)
		@SequenceGenerator(name = "location_div_subdiv_details_div_subdiv_detail_id_seq", sequenceName = "prwss_main.location_div_subdiv_details_div_subdiv_detail_id_seq")
		@Column(name = "div_subdiv_detail_id")
		private int DivisonSubDivisonDetailsId;
		
		
		
		@OneToOne(targetEntity=LocationDivisionSubDivisonMasterBean.class)
		@JoinColumn(name="div_subdiv_id", nullable=false)
		private LocationDivisionSubDivisonMasterBean locationDivSubDivMasterBean;
		
		@Column(name = "div_subdiv_name")
		private String DivisonSubDivisonDetailsName;

		public int getDivisonSubDivisonDetailsId() {
			return DivisonSubDivisonDetailsId;
		}

		public void setDivisonSubDivisonDetailsId(int divisonSubDivisonDetailsId) {
			DivisonSubDivisonDetailsId = divisonSubDivisonDetailsId;
		}

		public LocationDivisionSubDivisonMasterBean getLocationDivSubDivMasterBean() {
			return locationDivSubDivMasterBean;
		}

		public void setLocationDivSubDivMasterBean(LocationDivisionSubDivisonMasterBean locationDivSubDivMasterBean) {
			this.locationDivSubDivMasterBean = locationDivSubDivMasterBean;
		}

		public String getDivisonSubDivisonDetailsName() {
			return DivisonSubDivisonDetailsName;
		}

		public void setDivisonSubDivisonDetailsName(String divisonSubDivisonDetailsName) {
			DivisonSubDivisonDetailsName = divisonSubDivisonDetailsName;
		}

		@Override
		public String toString() {
			return "LocationDivisionSubDivisonDetailsBean [DivisonSubDivisonDetailsId=" + DivisonSubDivisonDetailsId
					+ ", locationDivSubDivMasterBean=" + locationDivSubDivMasterBean + ", DivisonSubDivisonDetailsName="
					+ DivisonSubDivisonDetailsName + "]";
		}
		
		

		

}
