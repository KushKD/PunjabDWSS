/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */


@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="san_motivator_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MotivatorBean implements Serializable {
	private static final long serialVersionUID = 363523490815L;
	
	@Id
	@GeneratedValue(generator="motivator_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="motivator_id_seq",sequenceName="prwss_main.motivator_id_seq")
	@Column(name="motivator_id")
	private int motivatorId;
	
	
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="gender_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailGender;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="category_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailCategory;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="religion_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailReligion;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="poi_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailPoiType;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="name_bank",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailBankName;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id_p", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationPerDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id_p", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtPerDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id_p", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockPerDetailBean;
	
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id_c", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationCrsDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id_c", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtCrsDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id_c", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockCrsDetailBean;
	
	@Column(name="motivator_name")
	private String motivatorName;
	
	@Column(name="fat_hus_name")
	private String fatHusName;
	
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="religion_id")
	private Integer religionId;
	
	@Column(name="gender_id")
	private Integer genderId;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="phone_no")
	private Long phoneNo;
	
	@Column(name="photo_graph_name")
	private String photographName;
	
	@Column(name="district_id_c")
	private Integer districtId;
	
	@Column(name="block_id_c")
	private Integer blockId;
	
	@Column(name="village_id_c")
	private Integer villageId;
	
	@Column(name="district_id_p")
	private Integer districtPrId;
	
	@Column(name="block_id_p")
	private Integer blockPrId;
	
	@Column(name="village_id_p")
	private Integer villagePrId;
	
	@Column(name="poi_type")
	private Integer poiType;
	
	@Column(name="poi_id")
	private String poiId;
	
	@Column(name="aadhaar_number")
	private Long aadhaarNumber;
	
	
	
	@Column(name="name_bank")
	private Integer bankName;
	
	@Column(name="name_branch")
	private String branchName;
	
	@Column(name="account_number")
	private Long accountNumber;
	
	@Column(name="ifsc_code")
	private String ifscCode;
	
	@Column(name="crt_by_usr")
	private Integer createdById;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="photo_graph")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] photograp;
	
	
	@Column(name="location_crsp")
	private String location;
	
	@Column(name="add_street_c")
	private String addStreet;
	@Column(name="add_landmark_c")
	private String landmark;
	@Column(name="add_housenumber_c")
	private String housenumber;
	@Column(name="pincode_c")
	private Integer pincode;
	
	@Column(name="location_prm")
	private String locationPrm;
	
	@Column(name="add_street_p")
	private String addStreetPrm;
	@Column(name="add_landmark_p")
	private String landmarkPrm;
	@Column(name="add_housenumber_p")
	private String housenumberPrm;
	@Column(name="pincode_p")
	private Integer pincodePrm;
	
	
	
	
	public String getLocationPrm() {
		return locationPrm;
	}
	public void setLocationPrm(String locationPrm) {
		this.locationPrm = locationPrm;
	}
	public String getAddStreetPrm() {
		return addStreetPrm;
	}
	public void setAddStreetPrm(String addStreetPrm) {
		this.addStreetPrm = addStreetPrm;
	}
	public String getLandmarkPrm() {
		return landmarkPrm;
	}
	public void setLandmarkPrm(String landmarkPrm) {
		this.landmarkPrm = landmarkPrm;
	}
	public String getHousenumberPrm() {
		return housenumberPrm;
	}
	public void setHousenumberPrm(String housenumberPrm) {
		this.housenumberPrm = housenumberPrm;
	}
	public Integer getPincodePrm() {
		return pincodePrm;
	}
	public void setPincodePrm(Integer pincodePrm) {
		this.pincodePrm = pincodePrm;
	}
	public int getMotivatorId() {
		return motivatorId;
	}
	public void setMotivatorId(int motivatorId) {
		this.motivatorId = motivatorId;
	}
	public ComboBeanDetails getCombodetailGender() {
		return combodetailGender;
	}
	public void setCombodetailGender(ComboBeanDetails combodetailGender) {
		this.combodetailGender = combodetailGender;
	}
	public ComboBeanDetails getCombodetailCategory() {
		return combodetailCategory;
	}
	public void setCombodetailCategory(ComboBeanDetails combodetailCategory) {
		this.combodetailCategory = combodetailCategory;
	}
	public ComboBeanDetails getCombodetailReligion() {
		return combodetailReligion;
	}
	public void setCombodetailReligion(ComboBeanDetails combodetailReligion) {
		this.combodetailReligion = combodetailReligion;
	}
	public ComboBeanDetails getCombodetailPoiType() {
		return combodetailPoiType;
	}
	public void setCombodetailPoiType(ComboBeanDetails combodetailPoiType) {
		this.combodetailPoiType = combodetailPoiType;
	}
	public ComboBeanDetails getCombodetailBankName() {
		return combodetailBankName;
	}
	public void setCombodetailBankName(ComboBeanDetails combodetailBankName) {
		this.combodetailBankName = combodetailBankName;
	}
	public LocationDetailsBean getLocationPerDetailBean() {
		return locationPerDetailBean;
	}
	public void setLocationPerDetailBean(LocationDetailsBean locationPerDetailBean) {
		this.locationPerDetailBean = locationPerDetailBean;
	}
	public LocationDetailsBean getDistrictPerDetailBean() {
		return districtPerDetailBean;
	}
	public void setDistrictPerDetailBean(LocationDetailsBean districtPerDetailBean) {
		this.districtPerDetailBean = districtPerDetailBean;
	}
	public LocationDetailsBean getBlockPerDetailBean() {
		return blockPerDetailBean;
	}
	public void setBlockPerDetailBean(LocationDetailsBean blockPerDetailBean) {
		this.blockPerDetailBean = blockPerDetailBean;
	}
	public LocationDetailsBean getLocationCrsDetailBean() {
		return locationCrsDetailBean;
	}
	public void setLocationCrsDetailBean(LocationDetailsBean locationCrsDetailBean) {
		this.locationCrsDetailBean = locationCrsDetailBean;
	}
	public LocationDetailsBean getDistrictCrsDetailBean() {
		return districtCrsDetailBean;
	}
	public void setDistrictCrsDetailBean(LocationDetailsBean districtCrsDetailBean) {
		this.districtCrsDetailBean = districtCrsDetailBean;
	}
	public LocationDetailsBean getBlockCrsDetailBean() {
		return blockCrsDetailBean;
	}
	public void setBlockCrsDetailBean(LocationDetailsBean blockCrsDetailBean) {
		this.blockCrsDetailBean = blockCrsDetailBean;
	}
	
	public String getMotivatorName() {
		return motivatorName;
	}
	public void setMotivatorName(String motivatorName) {
		this.motivatorName = motivatorName;
	}
	public String getFatHusName() {
		return fatHusName;
	}
	public void setFatHusName(String fatHusName) {
		this.fatHusName = fatHusName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getReligionId() {
		return religionId;
	}
	public void setReligionId(Integer religionId) {
		this.religionId = religionId;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhotographName() {
		return photographName;
	}
	public void setPhotographName(String photographName) {
		this.photographName = photographName;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getBlockId() {
		return blockId;
	}
	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	public Integer getVillageId() {
		return villageId;
	}
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	public Integer getDistrictPrId() {
		return districtPrId;
	}
	public void setDistrictPrId(Integer districtPrId) {
		this.districtPrId = districtPrId;
	}
	public Integer getBlockPrId() {
		return blockPrId;
	}
	public void setBlockPrId(Integer blockPrId) {
		this.blockPrId = blockPrId;
	}
	public Integer getVillagePrId() {
		return villagePrId;
	}
	public void setVillagePrId(Integer villagePrId) {
		this.villagePrId = villagePrId;
	}
	public Integer getPoiType() {
		return poiType;
	}
	public void setPoiType(Integer poiType) {
		this.poiType = poiType;
	}
	public String getPoiId() {
		return poiId;
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(Long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	
	public Integer getBankName() {
		return bankName;
	}
	public void setBankName(Integer bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public Integer getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public byte[] getPhotograp() {
		return photograp;
	}
	public void setPhotograp(byte[] photograp) {
		this.photograp = photograp;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddStreet() {
		return addStreet;
	}
	public void setAddStreet(String addStreet) {
		this.addStreet = addStreet;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getHousenumber() {
		return housenumber;
	}
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "MotivatorBean [motivatorId=" + motivatorId + ", combodetailGender=" + combodetailGender
				+ ", combodetailCategory=" + combodetailCategory + ", combodetailReligion=" + combodetailReligion
				+ ", combodetailPoiType=" + combodetailPoiType + ", combodetailBankName=" + combodetailBankName
				+ ", locationPerDetailBean=" + locationPerDetailBean + ", districtPerDetailBean="
				+ districtPerDetailBean + ", blockPerDetailBean=" + blockPerDetailBean + ", locationCrsDetailBean="
				+ locationCrsDetailBean + ", districtCrsDetailBean=" + districtCrsDetailBean + ", blockCrsDetailBean="
				+ blockCrsDetailBean +  ", motivatorName="
				+ motivatorName + ", fatHusName=" + fatHusName + ", categoryId=" + categoryId + ", religionId="
				+ religionId + ", genderId=" + genderId + ", emailId=" + emailId + ", phoneNo=" + phoneNo
				+ ", photographName=" + photographName + ", districtId=" + districtId + ", blockId=" + blockId
				+ ", villageId=" + villageId + ", districtPrId=" + districtPrId + ", blockPrId=" + blockPrId
				+ ", villagePrId=" + villagePrId + ", poiType=" + poiType + ", poiId=" + poiId + ", aadhaarNumber="
				+ aadhaarNumber + ", bankName=" + bankName + ", branchName=" + branchName + ", accountNumber="
				+ accountNumber + ", ifscCode=" + ifscCode + ", createdById=" + createdById + ", activeFlag="
				+ activeFlag + ", photograp=" + Arrays.toString(photograp) + ", location=" + location + ", addStreet="
				+ addStreet + ", landmark=" + landmark + ", housenumber=" + housenumber + ", pincode=" + pincode
				+ ", locationPrm=" + locationPrm + ", addStreetPrm=" + addStreetPrm + ", landmarkPrm=" + landmarkPrm
				+ ", housenumberPrm=" + housenumberPrm + ", pincodePrm=" + pincodePrm + "]";
	}
	
}
