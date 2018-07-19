package com.prwss.min.sanitation.bean;

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

import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;


@Entity
@Table(name="cmn_gram_panchayat_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class GramPanchayatDetailBean implements Serializable {

	private static final long serialVersionUID = 13249635344L;
	
	@Id
	@GeneratedValue(generator = "cmn_gram_panchayat_detail_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cmn_gram_panchayat_detail_seq", sequenceName = "prwss_main.cmn_gram_panchayat_detail_seq")
	@Column(name = "gram_panchayat_village_id")
	private int gramPanchayatVillageId;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBeandistrict;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBeanBlock;
	
	@OneToOne(targetEntity=GramPanchayatMasterBean.class)
	@JoinColumn(name="gram_panchayat_id", referencedColumnName="gram_panchayat_id",insertable=false,updatable=false)
	private GramPanchayatMasterBean gramPanchayatMasterBean;
	
	
	@Column(name="gram_panchayat_id")
	private Integer gramPanchayatId;
	
	@Column(name="district_id")
	private Integer district;
	
	@Column(name="block_id")
	private Integer block;
	
	@Column(name="village_id")
	private Integer village;
	
	@Column(name="active_flag")
	private Integer status;
	
	@Column(name="crt_by_usr")
	private Integer createdByUser;

	
	public GramPanchayatMasterBean getGramPanchayatMasterBean() {
		return gramPanchayatMasterBean;
	}

	public void setGramPanchayatMasterBean(GramPanchayatMasterBean gramPanchayatMasterBean) {
		this.gramPanchayatMasterBean = gramPanchayatMasterBean;
	}

	public int getGramPanchayatVillageId() {
		return gramPanchayatVillageId;
	}

	public void setGramPanchayatVillageId(int gramPanchayatVillageId) {
		this.gramPanchayatVillageId = gramPanchayatVillageId;
	}

	public LocationDetailsBean getLocationDetailBean() {
		return locationDetailBean;
	}

	public void setLocationDetailBean(LocationDetailsBean locationDetailBean) {
		this.locationDetailBean = locationDetailBean;
	}

	public Integer getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(Integer gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getBlock() {
		return block;
	}

	public void setBlock(Integer block) {
		this.block = block;
	}

	public Integer getVillage() {
		return village;
	}

	public void setVillage(Integer village) {
		this.village = village;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}
	
	
	

	public LocationDetailsBean getLocationDetailBeandistrict() {
		return locationDetailBeandistrict;
	}

	public void setLocationDetailBeandistrict(
			LocationDetailsBean locationDetailBeandistrict) {
		this.locationDetailBeandistrict = locationDetailBeandistrict;
	}

	public LocationDetailsBean getLocationDetailBeanBlock() {
		return locationDetailBeanBlock;
	}

	public void setLocationDetailBeanBlock(
			LocationDetailsBean locationDetailBeanBlock) {
		this.locationDetailBeanBlock = locationDetailBeanBlock;
	}

	@Override
	public String toString() {
		return "GramPanchayatDetailBean [gramPanchayatVillageId="
				+ gramPanchayatVillageId + ", locationDetailBean="
				+ locationDetailBean + ", locationDetailBeandistrict="
				+ locationDetailBeandistrict + ", locationDetailBeanBlock="
				+ locationDetailBeanBlock + ", gramPanchayatMasterBean="
				+ gramPanchayatMasterBean + ", gramPanchayatId="
				+ gramPanchayatId + ", district=" + district + ", block="
				+ block + ", village=" + village + ", status=" + status
				+ ", createdByUser=" + createdByUser + "]";
	}

	
	
}
