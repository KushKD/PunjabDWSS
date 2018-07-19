package com.prwss.min.bean;

import java.io.Serializable;
import java.sql.Date;

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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.prwss.mis.admin.dao.LocationDetailsBean;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_lab_master",schema="prwss_main")
public class LabMasterBean implements Serializable{
	private static final long serialVersionUID = 1345323445344L;

	@Id
	@GeneratedValue(generator = "lab_master_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "lab_master_seq", sequenceName = "prwss_main.lab_master_seq")
	@Column(name = "lab_id")
	private long labId;
	
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	

	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;

	
	@OneToOne(targetEntity = SampleCodeLabMapping.class)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "lab_id",referencedColumnName="test_lab_samplecode_id", nullable = false, insertable = false, updatable = false)
	private SampleCodeLabMapping sampleCodeLabMapping;
	
	@Column(name = "lab_name")
	private String labName;
	
	@Column(name = "lab_id_name")
	private String labIdName;
	
	
	
	@Column(name = "district_id")
	private Integer districtId;
	
	@Column(name = "village_id")
	private Integer villageId;
	
	@Column(name = "block_id")
	private Integer blockId;
	
	@Column(name = "lab_level")
	private Integer labLevel;
	
	@Column(name = "division_id")
	private Integer divisionId;
	
	@Column(name = "phone_no")
	private Long phoneNo;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
	@Column(name = "mobile_no")
	private Long mobileNo;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;

	
	public Integer getLabLevel() {
		return labLevel;
	}
	public void setLabLevel(Integer labLevel) {
		this.labLevel = labLevel;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

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

	public String getLabIdName() {
		return labIdName;
	}

	public void setLabIdName(String labIdName) {
		this.labIdName = labIdName;
	}

	public long getLabId() {
		return labId;
	}

	public void setLabId(long labId) {
		this.labId = labId;
	}

	public SampleCodeLabMapping getSampleCodeLabMapping() {
		return sampleCodeLabMapping;
	}

	public void setSampleCodeLabMapping(SampleCodeLabMapping sampleCodeLabMapping) {
		this.sampleCodeLabMapping = sampleCodeLabMapping;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Long getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Long crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "LabMasterBean [labId=" + labId + ", districtDetailBean=" + districtDetailBean + ", blockDetailBean="
				+ blockDetailBean + ", sampleCodeLabMapping=" + sampleCodeLabMapping + ", labName=" + labName
				+ ", labIdName=" + labIdName + ", districtId=" + districtId + ", villageId=" + villageId + ", blockId="
				+ blockId + ", labLevel=" + labLevel + ", divisionId=" + divisionId + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", contactPerson=" + contactPerson + ", mobileNo=" + mobileNo + ", crtByUsr="
				+ crtByUsr + ", activeFlag=" + activeFlag + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
