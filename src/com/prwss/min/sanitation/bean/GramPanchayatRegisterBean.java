package com.prwss.min.sanitation.bean;

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

import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "san_gramPanchayat_register", schema = MISConstants.MIS_DB_SCHEMA_NAME)

//san_gramPanchayat_register_seq
public class GramPanchayatRegisterBean implements Serializable {
	
	
	private static final long serialVersionUID = 565898374234671926L;
	
	
	@Id
	@GeneratedValue(generator = "san_gramPanchayat_register_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "san_gramPanchayat_register_seq", sequenceName = "prwss_main.san_gramPanchayat_register_seq")
	@Column(name = "gp_register_id", nullable = false)
	private int gp_register_id;
	
	@Column(name = "district_id", nullable = false)
	private Integer district_id;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="habitaion_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailHabitation;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="gender_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailGender;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="card_type_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailCardType;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="category_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailCategoryID;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="sub_category_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailSubCategoryID;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="caste_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailCasteId;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="toilet_before_survey",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailtoiletBeforeSurvey;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="toilet_constructed_from",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailtoiletConstructedFrom;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="had_functional_toilet",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailhad_functional_toilet;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="functional_toilet_used",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailfunctional_toilet_used;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="water_facility_available",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailwater_facility_available;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="is_covered",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailis_covered;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="toilet_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailtoilet_type;
	
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	
	
	@Column(name = "block_id", nullable = false)
	private Integer block_id;
	
	@Column(name = "village_id", nullable = false)
	private Integer village_id;
	
	@Column(name = "gram_panchayat_id", nullable = true)
	private Integer gram_panchayat_id;
	
	@Column(name = "habitaion_id", nullable = true)
	private Integer habitaion_id;
	
	@Column(name = "family_id", nullable = false)
	private String family_id;
	
	@Column(name = "family_head", nullable = false)
	private String family_head;
	
	@Column(name = "fat_hus_name", nullable = true)
	private String fat_hus_name;
	
	@Column(name = "gender_id", nullable = false)
	private Integer gender_id;
	
	@Column(name = "card_type_id", nullable = true)
	private Integer card_type_id;
	
	@Column(name = "category_id", nullable = true)
	private Integer category_id;
	
	@Column(name = "sub_category_id", nullable = true)
	private Integer sub_category_id;
	
	@Column(name = "aadhaar_number", nullable = true)
	private Long aadhaar_number;
	
	@Column(name = "caste_id", nullable = true)
	private Integer caste_id;
	
	@Column(name = "toilet_before_survey", nullable = false)
	private Integer toilet_before_survey;
	
	@Column(name = "toilet_constructed_from", nullable = true)
	private Integer toilet_constructed_from;
	
	@Column(name = "had_functional_toilet", nullable = true)
	private Integer had_functional_toilet;
	
	@Column(name = "functional_toilet_used", nullable = true)
	private Integer functional_toilet_used;
	
	@Column(name = "water_facility_available", nullable = true)
	private Integer water_facility_available;
	
	@Column(name = "is_covered", nullable = true)
	private Integer is_covered;
	
	@Column(name = "toilet_type", nullable = true)
	private Integer toilet_type;
	
	@Column(name = "crt_date", nullable = true)
	private Date crt_date;
	
	@Column(name = "crt_by_usr", nullable = true)
	private Integer crt_by_usr;
	
	/*@Column(name = "lst_updated_date", nullable = true)
	private Date lst_updated_date;
	
	@Column(name = "lst_updated_user", nullable = true)
	private Date lst_updated_user;*/
	
	
	@Column(name = "loc_id", nullable = true)
	private Integer loc_id;
	
	@Column(name = "active_flag", nullable = false)
	private Integer active_flag;
	
	
	
	@Column(name = "remarks", nullable = false)
	private String remarks;



	public int getGp_register_id() {
		return gp_register_id;
	}



	public void setGp_register_id(int gp_register_id) {
		this.gp_register_id = gp_register_id;
	}



	public Integer getDistrict_id() {
		return district_id;
	}



	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}




	public ComboBeanDetails getCombodetailHabitation() {
		return combodetailHabitation;
	}



	public void setCombodetailHabitation(ComboBeanDetails combodetailHabitation) {
		this.combodetailHabitation = combodetailHabitation;
	}



	public ComboBeanDetails getCombodetailGender() {
		return combodetailGender;
	}



	public void setCombodetailGender(ComboBeanDetails combodetailGender) {
		this.combodetailGender = combodetailGender;
	}



	public ComboBeanDetails getCombodetailCardType() {
		return combodetailCardType;
	}



	public void setCombodetailCardType(ComboBeanDetails combodetailCardType) {
		this.combodetailCardType = combodetailCardType;
	}



	public LocationDetailsBean getLocationDetailBean() {
		return locationDetailBean;
	}



	public void setLocationDetailBean(LocationDetailsBean locationDetailBean) {
		this.locationDetailBean = locationDetailBean;
	}



	public Integer getBlock_id() {
		return block_id;
	}



	public void setBlock_id(Integer block_id) {
		this.block_id = block_id;
	}



	public Integer getVillage_id() {
		return village_id;
	}



	public void setVillage_id(Integer village_id) {
		this.village_id = village_id;
	}



	public Integer getGram_panchayat_id() {
		return gram_panchayat_id;
	}



	public void setGram_panchayat_id(Integer gram_panchayat_id) {
		this.gram_panchayat_id = gram_panchayat_id;
	}



	public Integer getHabitaion_id() {
		return habitaion_id;
	}



	public void setHabitaion_id(Integer habitaion_id) {
		this.habitaion_id = habitaion_id;
	}



	public String getFamily_id() {
		return family_id;
	}



	public void setFamily_id(String family_id) {
		this.family_id = family_id;
	}



	public String getFamily_head() {
		return family_head;
	}



	public void setFamily_head(String family_head) {
		this.family_head = family_head;
	}



	public String getFat_hus_name() {
		return fat_hus_name;
	}



	public void setFat_hus_name(String fat_hus_name) {
		this.fat_hus_name = fat_hus_name;
	}



	public Integer getGender_id() {
		return gender_id;
	}



	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}



	public Integer getCard_type_id() {
		return card_type_id;
	}



	public void setCard_type_id(Integer card_type_id) {
		this.card_type_id = card_type_id;
	}



	public Integer getCategory_id() {
		return category_id;
	}



	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}



	public Integer getSub_category_id() {
		return sub_category_id;
	}



	public void setSub_category_id(Integer sub_category_id) {
		this.sub_category_id = sub_category_id;
	}



	public Long getAadhaar_number() {
		return aadhaar_number;
	}



	public void setAadhaar_number(Long aadhaar_number) {
		this.aadhaar_number = aadhaar_number;
	}



	public Integer getCaste_id() {
		return caste_id;
	}



	public void setCaste_id(Integer caste_id) {
		this.caste_id = caste_id;
	}



	public Integer getToilet_before_survey() {
		return toilet_before_survey;
	}



	public void setToilet_before_survey(Integer toilet_before_survey) {
		this.toilet_before_survey = toilet_before_survey;
	}



	public Integer getToilet_constructed_from() {
		return toilet_constructed_from;
	}



	public void setToilet_constructed_from(Integer toilet_constructed_from) {
		this.toilet_constructed_from = toilet_constructed_from;
	}



	public Integer getHad_functional_toilet() {
		return had_functional_toilet;
	}



	public void setHad_functional_toilet(Integer had_functional_toilet) {
		this.had_functional_toilet = had_functional_toilet;
	}



	public Integer getFunctional_toilet_used() {
		return functional_toilet_used;
	}



	public void setFunctional_toilet_used(Integer functional_toilet_used) {
		this.functional_toilet_used = functional_toilet_used;
	}



	public Integer getWater_facility_available() {
		return water_facility_available;
	}



	public void setWater_facility_available(Integer water_facility_available) {
		this.water_facility_available = water_facility_available;
	}



	public Integer getIs_covered() {
		return is_covered;
	}



	public void setIs_covered(Integer is_covered) {
		this.is_covered = is_covered;
	}



	public Integer getToilet_type() {
		return toilet_type;
	}



	public void setToilet_type(Integer toilet_type) {
		this.toilet_type = toilet_type;
	}



	public Date getCrt_date() {
		return crt_date;
	}



	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}



	public Integer getCrt_by_usr() {
		return crt_by_usr;
	}



	public void setCrt_by_usr(Integer crt_by_usr) {
		this.crt_by_usr = crt_by_usr;
	}



	public Integer getLoc_id() {
		return loc_id;
	}



	public void setLoc_id(Integer loc_id) {
		this.loc_id = loc_id;
	}



	public Integer getActive_flag() {
		return active_flag;
	}



	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}



	public ComboBeanDetails getCombodetailCategoryID() {
		return combodetailCategoryID;
	}



	public void setCombodetailCategoryID(ComboBeanDetails combodetailCategoryID) {
		this.combodetailCategoryID = combodetailCategoryID;
	}



	public ComboBeanDetails getCombodetailSubCategoryID() {
		return combodetailSubCategoryID;
	}



	public void setCombodetailSubCategoryID(ComboBeanDetails combodetailSubCategoryID) {
		this.combodetailSubCategoryID = combodetailSubCategoryID;
	}



	public ComboBeanDetails getCombodetailCasteId() {
		return combodetailCasteId;
	}



	public void setCombodetailCasteId(ComboBeanDetails combodetailCasteId) {
		this.combodetailCasteId = combodetailCasteId;
	}



	public ComboBeanDetails getCombodetailtoiletBeforeSurvey() {
		return combodetailtoiletBeforeSurvey;
	}



	public void setCombodetailtoiletBeforeSurvey(ComboBeanDetails combodetailtoiletBeforeSurvey) {
		this.combodetailtoiletBeforeSurvey = combodetailtoiletBeforeSurvey;
	}



	public ComboBeanDetails getCombodetailtoiletConstructedFrom() {
		return combodetailtoiletConstructedFrom;
	}



	public void setCombodetailtoiletConstructedFrom(ComboBeanDetails combodetailtoiletConstructedFrom) {
		this.combodetailtoiletConstructedFrom = combodetailtoiletConstructedFrom;
	}



	public ComboBeanDetails getCombodetailhad_functional_toilet() {
		return combodetailhad_functional_toilet;
	}



	public void setCombodetailhad_functional_toilet(ComboBeanDetails combodetailhad_functional_toilet) {
		this.combodetailhad_functional_toilet = combodetailhad_functional_toilet;
	}



	public ComboBeanDetails getCombodetailfunctional_toilet_used() {
		return combodetailfunctional_toilet_used;
	}



	public void setCombodetailfunctional_toilet_used(ComboBeanDetails combodetailfunctional_toilet_used) {
		this.combodetailfunctional_toilet_used = combodetailfunctional_toilet_used;
	}



	public ComboBeanDetails getCombodetailwater_facility_available() {
		return combodetailwater_facility_available;
	}



	public void setCombodetailwater_facility_available(ComboBeanDetails combodetailwater_facility_available) {
		this.combodetailwater_facility_available = combodetailwater_facility_available;
	}



	public ComboBeanDetails getCombodetailis_covered() {
		return combodetailis_covered;
	}



	public void setCombodetailis_covered(ComboBeanDetails combodetailis_covered) {
		this.combodetailis_covered = combodetailis_covered;
	}



	public ComboBeanDetails getCombodetailtoilet_type() {
		return combodetailtoilet_type;
	}



	public void setCombodetailtoilet_type(ComboBeanDetails combodetailtoilet_type) {
		this.combodetailtoilet_type = combodetailtoilet_type;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	@Override
	public String toString() {
		return "GramPanchayatRegisterBean [gp_register_id=" + gp_register_id + ", district_id=" + district_id
				+ ", combodetailHabitation=" + combodetailHabitation + ", combodetailGender=" + combodetailGender
				+ ", combodetailCardType=" + combodetailCardType + ", combodetailCategoryID=" + combodetailCategoryID
				+ ", combodetailSubCategoryID=" + combodetailSubCategoryID + ", combodetailCasteId="
				+ combodetailCasteId + ", combodetailtoiletBeforeSurvey=" + combodetailtoiletBeforeSurvey
				+ ", combodetailtoiletConstructedFrom=" + combodetailtoiletConstructedFrom
				+ ", combodetailhad_functional_toilet=" + combodetailhad_functional_toilet
				+ ", combodetailfunctional_toilet_used=" + combodetailfunctional_toilet_used
				+ ", combodetailwater_facility_available=" + combodetailwater_facility_available
				+ ", combodetailis_covered=" + combodetailis_covered + ", combodetailtoilet_type="
				+ combodetailtoilet_type + ", locationDetailBean=" + locationDetailBean + ", block_id=" + block_id
				+ ", village_id=" + village_id + ", gram_panchayat_id=" + gram_panchayat_id + ", habitaion_id="
				+ habitaion_id + ", family_id=" + family_id + ", family_head=" + family_head + ", fat_hus_name="
				+ fat_hus_name + ", gender_id=" + gender_id + ", card_type_id=" + card_type_id + ", category_id="
				+ category_id + ", sub_category_id=" + sub_category_id + ", aadhaar_number=" + aadhaar_number
				+ ", caste_id=" + caste_id + ", toilet_before_survey=" + toilet_before_survey
				+ ", toilet_constructed_from=" + toilet_constructed_from + ", had_functional_toilet="
				+ had_functional_toilet + ", functional_toilet_used=" + functional_toilet_used
				+ ", water_facility_available=" + water_facility_available + ", is_covered=" + is_covered
				+ ", toilet_type=" + toilet_type + ", crt_date=" + crt_date + ", crt_by_usr=" + crt_by_usr + ", loc_id="
				+ loc_id + ", active_flag=" + active_flag + ", remarks=" + remarks + "]";
	}





	
}
