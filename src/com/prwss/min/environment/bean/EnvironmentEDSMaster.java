package com.prwss.min.environment.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="env_eds_mst",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentEDSMaster  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5392357273689504475L;
	
	
	@Id
	@GeneratedValue(generator="env_eds_mst_eds_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="env_eds_mst_eds_id_seq",sequenceName="prwss_main.env_eds_mst_eds_id_seq")
	@Column(name="eds_id")
	private Integer eds_id;
	

	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	

	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id",referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean  villageDetailBean;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="scheme_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboBeanDetailsSchemeype;
	
	@OneToOne(targetEntity=PMSSchemeDetailsBean.class)
	@JoinColumn(name="scheme_id",referencedColumnName="scheme_id",insertable=false,updatable=false)
	private PMSSchemeDetailsBean  schemeDetailBean;
	
	
	
	
	
	@Column(name="scheme_type")
	private Integer scheme_type;
	
	@Column(name="scheme_category")
	private Integer scheme_category;
	
	@Column(name="scheme_id")
	private Integer scheme_id;
	
	
	@Column(name="village_id")
	private Integer village_id;
	
	@Column(name="gram_panchayat")
	private Integer gram_panchayat;
	
	@Column(name="block_id")
	private Integer block_id;
	
	@Column(name="district_id")
	private Integer district_id;
	
	@Column(name="zone_id")
	private Integer zone_id;
	
	@Column(name="active_flag")
	private Integer active_flag;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;
	
	  

	public LocationDetailsBean getDistrictDetailBean() {
		return districtDetailBean;
	}

	public void setDistrictDetailBean(LocationDetailsBean districtDetailBean) {
		this.districtDetailBean = districtDetailBean;
	}

	public LocationDetailsBean getBlockDetailBean() {
		return blockDetailBean;
	}

	public void setBlockDetailBean(LocationDetailsBean blockDetailBean) {
		this.blockDetailBean = blockDetailBean;
	}

	public int getEds_id() {
		return eds_id;
	}

	

	public Integer getScheme_type() {
		return scheme_type;
	}

	public void setScheme_type(Integer scheme_type) {
		this.scheme_type = scheme_type;
	}

	public Integer getScheme_category() {
		return scheme_category;
	}

	public void setScheme_category(Integer scheme_category) {
		this.scheme_category = scheme_category;
	}

	public Integer getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(Integer scheme_id) {
		this.scheme_id = scheme_id;
	}

	public Integer getVillage_id() {
		return village_id;
	}

	public void setVillage_id(Integer village_id) {
		this.village_id = village_id;
	}

	public Integer getGram_panchayat() {
		return gram_panchayat;
	}

	public void setGram_panchayat(Integer gram_panchayat) {
		this.gram_panchayat = gram_panchayat;
	}

	public Integer getBlock_id() {
		return block_id;
	}

	public void setBlock_id(Integer block_id) {
		this.block_id = block_id;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public Integer getZone_id() {
		return zone_id;
	}

	public void setZone_id(Integer zone_id) {
		this.zone_id = zone_id;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	public ComboBeanDetails getComboBeanDetailsSchemeype() {
		return comboBeanDetailsSchemeype;
	}

	public void setComboBeanDetailsSchemeype(
			ComboBeanDetails comboBeanDetailsSchemeype) {
		this.comboBeanDetailsSchemeype = comboBeanDetailsSchemeype;
	}

	public LocationDetailsBean getVillageDetailBean() {
		return villageDetailBean;
	}

	public void setVillageDetailBean(LocationDetailsBean villageDetailBean) {
		this.villageDetailBean = villageDetailBean;
	}

	public PMSSchemeDetailsBean getSchemeDetailBean() {
		return schemeDetailBean;
	}

	public void setSchemeDetailBean(PMSSchemeDetailsBean schemeDetailBean) {
		this.schemeDetailBean = schemeDetailBean;
	}
	

	public void setEds_id(Integer eds_id) {
		this.eds_id = eds_id;
	}

	@Override
	public String toString() {
		return "EnvironmentEDSMaster [eds_id=" + eds_id
				+ ", districtDetailBean=" + districtDetailBean
				+ ", blockDetailBean=" + blockDetailBean
				+ ", villageDetailBean=" + villageDetailBean
				+ ", comboBeanDetailsSchemeype=" + comboBeanDetailsSchemeype
				+ ", schemeDetailBean=" + schemeDetailBean + ", scheme_type="
				+ scheme_type + ", scheme_category=" + scheme_category
				+ ", scheme_id=" + scheme_id + ", village_id=" + village_id
				+ ", gram_panchayat=" + gram_panchayat + ", block_id="
				+ block_id + ", district_id=" + district_id + ", zone_id="
				+ zone_id + ", active_flag=" + active_flag + ", crt_by_user="
				+ crt_by_user + "]";
	}

	
	


	
	
	
	
	
}
