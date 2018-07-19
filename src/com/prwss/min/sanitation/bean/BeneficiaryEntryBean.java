package com.prwss.min.sanitation.bean;

import java.io.Serializable;

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

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="san_beneficiery_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BeneficiaryEntryBean implements Serializable{

	private static final long serialVersionUID = 363503490815L;
	@Id
	@GeneratedValue(generator="seq_beneficiery_id",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_beneficiery_id",sequenceName="prwss_main.seq_beneficiery_id")
	@Column(name="beneficiery_id")
	private int beneficieryId;
	
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="caste_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailCasteId;
	
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
	@JoinColumn(name="village_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;
	
	@Column(name="beneficiery_name")
	private String beneficieryName;
	
	@Column(name="fat_hus_name")
	private String fatHusName;
	
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="caste_id")
	private Integer casteId;
	
	@Column(name="religion_id")
	private Integer religionId;
	
	@Column(name="gender_id")
	private Integer genderId;
	
	@Column(name="phone_no")
	private Long phoneNo;
	
	@Column(name="photograph_name")
	private String photographName;
	
	@Column(name="district_id")
	private Integer districtId;
	
	@Column(name="block_id")
	private Integer blockId;
	
	@Column(name="village_id")
	private Integer villageId;
	
	@Column(name="gram_panchayat_id")
	private String gramPanchayatId;
	
	@Column(name="poi_type")
	private Integer poiType;
	
	@Column(name="poi_id")
	private String poiId;
	
	@Column(name="aadhaar_number")
	private Long aadhaarNumber;
	
	@Column(name="elect_connection_number")
	private String electConnNumber;
	
	@Column(name="elect_bill")
	private String electBill;
	
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
	
	@Column(name="photograph")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] photograp;
	
	
	@Column(name="elec_con_data")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] elecConData;
	
	
	

	public byte[] getPhotograp() {
		return photograp;
	}

	public void setPhotograp(byte[] photograp) {
		this.photograp = photograp;
	}

	public byte[] getElecConData() {
		return elecConData;
	}

	public void setElecConData(byte[] elecConData) {
		this.elecConData = elecConData;
	}

	public int getBeneficieryId() {
		return beneficieryId;
	}

	public void setBeneficieryId(int beneficieryId) {
		this.beneficieryId = beneficieryId;
	}

	public String getBeneficieryName() {
		return beneficieryName;
	}

	public void setBeneficieryName(String beneficieryName) {
		this.beneficieryName = beneficieryName;
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

	public Integer getCasteId() {
		return casteId;
	}

	public void setCasteId(Integer casteId) {
		this.casteId = casteId;
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

	

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
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

	public String getElectConnNumber() {
		return electConnNumber;
	}

	public void setElectConnNumber(String electConnNumber) {
		this.electConnNumber = electConnNumber;
	}

	public String getElectBill() {
		return electBill;
	}

	public void setElectBill(String electBill) {
		this.electBill = electBill;
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

	@Override
	public String toString() {
		return "BeneficiaryEntryBean [beneficieryId=" + beneficieryId + ", beneficieryName=" + beneficieryName
				+ ", fatHusName=" + fatHusName + ", categoryId=" + categoryId + ", casteId=" + casteId + ", religionId="
				+ religionId + ", genderId=" + genderId + ", phoneNo=" + phoneNo + ", photographName=" + photographName
				+ ", districtId=" + districtId + ", blockId=" + blockId + ", villageId=" + villageId
				+ ", gramPanchayatId=" + gramPanchayatId + ", poiType=" + poiType + ", poiId=" + poiId
				+ ", aadhaarNumber=" + aadhaarNumber + ", electConnNumber=" + electConnNumber + ", electBill="
				+ electBill + ", bankName=" + bankName + ", branchName=" + branchName + ", accountNumber="
				+ accountNumber + ", ifscCode=" + ifscCode + ", createdById=" + createdById + ", activeFlag="
				+ activeFlag + "]";
	}
	
}
