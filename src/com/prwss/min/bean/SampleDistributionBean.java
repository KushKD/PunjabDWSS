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

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_sample_distribuion",schema="prwss_main")
public class SampleDistributionBean implements Serializable {
	
	
	private static final long serialVersionUID = 134333545344L;
	
	@Id
	@GeneratedValue(generator = "wq_sample_distribuion_id_seq ", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "wq_sample_distribuion_id_seq ", sequenceName = "prwss_main.sample_id_seq")
	@Column(name = "id")
	private int sampleId;
	
	
	@OneToOne(targetEntity=LabMasterBean.class)
	@JoinColumn(name="labName", referencedColumnName="lab_id",insertable=false,updatable=false)
	private LabMasterBean labMasterBean;
	
	@OneToOne(targetEntity=SamplePartCodeLabMapping.class)
	@JoinColumn(name="tests", referencedColumnName="test_lab_samplesubcode_id",insertable=false,updatable=false)
	private SamplePartCodeLabMapping samplePartCodeLabMapping;
	
	
	
	@Column(name="sampleNum")
	private String sampleNum;
	
	@Column(name="labname")
	private Integer labName;
	
	@Column(name="locationName")
	private String locationName;
	
	@Column(name="distributionDate")
	private Date distributionDate;
	
	@Column(name="technician")
	private String technician;
	
	@Column(name="samplePartNum")
	private String samplePartNum;
	
	@Column(name="tests")
	private Integer tests;
	
	@Column(name="requiredBy")
	private String requiredBy;
	
	@Column(name="crt_by_user")
	private Long createByUsr;
	
	
	@Column(name="active_flag")
	private Integer activeFlag;

	@Column(name="sample_type")
	private String sampleType;

	@Column(name="is_entered")
	private Integer isEntered;
	
	
	
	
	public Integer getIsEntered() {
		return isEntered;
	}

	public void setIsEntered(Integer isEntered) {
		this.isEntered = isEntered;
	}

	public LabMasterBean getLabMasterBean() {
		return labMasterBean;
	}

	public void setLabMasterBean(LabMasterBean labMasterBean) {
		this.labMasterBean = labMasterBean;
	}

	public SamplePartCodeLabMapping getSamplePartCodeLabMapping() {
		return samplePartCodeLabMapping;
	}

	public void setSamplePartCodeLabMapping(SamplePartCodeLabMapping samplePartCodeLabMapping) {
		this.samplePartCodeLabMapping = samplePartCodeLabMapping;
	}

	public int getSampleId() {
		return sampleId;
	}

	public void setSampleId(int sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleNum() {
		return sampleNum;
	}

	public void setSampleNum(String sampleNum) {
		this.sampleNum = sampleNum;
	}

	public Integer getLabName() {
		return labName;
	}

	public void setLabName(Integer labName) {
		this.labName = labName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Date getDistributionDate() {
		return distributionDate;
	}

	public void setDistributionDate(Date distributionDate) {
		this.distributionDate = distributionDate;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getSamplePartNum() {
		return samplePartNum;
	}

	public void setSamplePartNum(String samplePartNum) {
		this.samplePartNum = samplePartNum;
	}

	public Integer getTests() {
		return tests;
	}

	public void setTests(Integer tests) {
		this.tests = tests;
	}

	public String getRequiredBy() {
		return requiredBy;
	}

	public void setRequiredBy(String requiredBy) {
		this.requiredBy = requiredBy;
	}

	public Long getCreateByUsr() {
		return createByUsr;
	}

	public void setCreateByUsr(Long createByUsr) {
		this.createByUsr = createByUsr;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	@Override
	public String toString() {
		return "SampleDistributionBean [sampleId=" + sampleId + ", labMasterBean=" + labMasterBean
				+ ", samplePartCodeLabMapping=" + samplePartCodeLabMapping + ", sampleNum=" + sampleNum + ", labName="
				+ labName + ", locationName=" + locationName + ", distributionDate=" + distributionDate
				+ ", technician=" + technician + ", samplePartNum=" + samplePartNum + ", tests=" + tests
				+ ", requiredBy=" + requiredBy + ", createByUsr=" + createByUsr + ", activeFlag=" + activeFlag
				+ ", sampleType=" + sampleType + "]";
	}


}
