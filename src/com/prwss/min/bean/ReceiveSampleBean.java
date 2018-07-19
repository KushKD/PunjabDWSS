/**
 * 
 */
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
 * @author KD
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_sample_collection",schema="prwss_main")
public class ReceiveSampleBean implements Serializable{
	
	private static final long serialVersionUID = 134534545344L;
	
	
	@Id
	@GeneratedValue(generator = "sample_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "sample_id_seq", sequenceName = "prwss_main.sample_id_seq")
	@Column(name = "sample_id")
	private int sampleId;
	
	@OneToOne(targetEntity=LabMasterBean.class)
	@JoinColumn(name="lab_to_be_tested", referencedColumnName="lab_id",insertable=false,updatable=false)
	private LabMasterBean labMasterBean;
	
	@OneToOne(targetEntity=SchemeMapping.class)
	@JoinColumn(name="water_source", referencedColumnName="scheme_ws_mpg_id",insertable=false,updatable=false)
	private SchemeMapping schemeMapping;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id", referencedColumnName="location_id", nullable=true,insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;
	
	
	@OneToOne(targetEntity=PMSSchemeDetailsBean.class)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="scheme_id", referencedColumnName="scheme_id", nullable=true,insertable=false,updatable=false)
	private PMSSchemeDetailsBean pmsSchemeDetailsBean;
	
	
	
	@Column(name = "sample_number")
	private String sampleNumber;
	
	@Column(name="test_type")
	private Integer testType;
	
	@Column(name="collection_type")
	private Integer collectionType;
	
	@Column(name="poi_type")
	private Integer poiType;
	
	@Column(name="mobile_no")
	private Long mobileNo;
	
	@Column(name="office_email_id")
	private String office_email_id;
	
	@Column(name="collected_by")
	private String collected_by;
	
	@Column(name="poi_id")
	private String poi_id;
	@Column(name="collection_date")
	private Date collection_date;
	
	@Column(name="scheme_id")
	private Integer schemeId;
	
	
	@Column(name="water_source")
	private Integer waterSource;
	
	
	@Column(name="scheme_status")
	private String schemeStatus;
	
	
	@Column(name="district_id")
	private Integer districtId;
	
	@Column(name="tehsil_id")
	private Integer tehsilId;
	
	@Column(name="village_id")
	private Integer villageId;
	
	
	
	@Column(name="block_id")
	private Integer blockId;
	
	@Column(name="gram_panchayat_id")
	private String gramPanchayatId;
	
	@Column(name="habitation_type")
	private String habitationType;
	
	@Column(name="lab_to_be_tested")
	private Integer labTested;
	
	@Column(name="testing_status")
	private Integer testingStatus;
	
	@Column(name="crt_by_usr")
	private Long createByUsr;
	

	
	@Column(name="designation")
	private String designation;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="water_works_sample")
	private String water_works_sample;
	
	@Column(name="city")
	private String city;
	
	@Column(name="sampleotherdetails")
	private String sampleotherdetails;
	
	@Column(name="depth")
	private Double depth;
	
	@Column(name="bottletype")
	private Integer bottleType;
	
	@Column(name="letterrefnum")
	private String letterRefNum;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name="rural_urban")
	private Integer rural_urban;

	@Column(name="is_distributed")
	private Integer is_distributed;
	
	@Column(name="is_freeze")
	private Integer is_freeze;
	
	@Column(name="freeze_date")
	private Date freeze_date;
	
	
	
	public Date getFreeze_date() {
		return freeze_date;
	}

	public void setFreeze_date(Date freeze_date) {
		this.freeze_date = freeze_date;
	}

	public Integer getIs_freeze() {
		return is_freeze;
	}

	public void setIs_freeze(Integer is_freeze) {
		this.is_freeze = is_freeze;
	}

	public Integer getIs_distributed() {
		return is_distributed;
	}

	public void setIs_distributed(Integer is_distributed) {
		this.is_distributed = is_distributed;
	}

	public int getSampleId() {
		return sampleId;
	}

	public void setSampleId(int sampleId) {
		this.sampleId = sampleId;
	}

	public LabMasterBean getLabMasterBean() {
		return labMasterBean;
	}

	public void setLabMasterBean(LabMasterBean labMasterBean) {
		this.labMasterBean = labMasterBean;
	}

	public SchemeMapping getSchemeMapping() {
		return schemeMapping;
	}

	public void setSchemeMapping(SchemeMapping schemeMapping) {
		this.schemeMapping = schemeMapping;
	}

	public LocationDetailsBean getLocationDetailBean() {
		return locationDetailBean;
	}

	public void setLocationDetailBean(LocationDetailsBean locationDetailBean) {
		this.locationDetailBean = locationDetailBean;
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

	public PMSSchemeDetailsBean getPmsSchemeDetailsBean() {
		return pmsSchemeDetailsBean;
	}

	public void setPmsSchemeDetailsBean(PMSSchemeDetailsBean pmsSchemeDetailsBean) {
		this.pmsSchemeDetailsBean = pmsSchemeDetailsBean;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public Integer getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(Integer collectionType) {
		this.collectionType = collectionType;
	}

	public Integer getPoiType() {
		return poiType;
	}

	public void setPoiType(Integer poiType) {
		this.poiType = poiType;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOffice_email_id() {
		return office_email_id;
	}

	public void setOffice_email_id(String office_email_id) {
		this.office_email_id = office_email_id;
	}

	public String getCollected_by() {
		return collected_by;
	}

	public void setCollected_by(String collected_by) {
		this.collected_by = collected_by;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public Date getCollection_date() {
		return collection_date;
	}

	public void setCollection_date(Date collection_date) {
		this.collection_date = collection_date;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public Integer getWaterSource() {
		return waterSource;
	}

	public void setWaterSource(Integer waterSource) {
		this.waterSource = waterSource;
	}

	public String getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Integer tehsilId) {
		this.tehsilId = tehsilId;
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

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public String getHabitationType() {
		return habitationType;
	}

	public void setHabitationType(String habitationType) {
		this.habitationType = habitationType;
	}

	public Integer getLabTested() {
		return labTested;
	}

	public void setLabTested(Integer labTested) {
		this.labTested = labTested;
	}

	public Integer getTestingStatus() {
		return testingStatus;
	}

	public void setTestingStatus(Integer testingStatus) {
		this.testingStatus = testingStatus;
	}

	public Long getCreateByUsr() {
		return createByUsr;
	}

	public void setCreateByUsr(Long createByUsr) {
		this.createByUsr = createByUsr;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getWater_works_sample() {
		return water_works_sample;
	}

	public void setWater_works_sample(String water_works_sample) {
		this.water_works_sample = water_works_sample;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSampleotherdetails() {
		return sampleotherdetails;
	}

	public void setSampleotherdetails(String sampleotherdetails) {
		this.sampleotherdetails = sampleotherdetails;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public Integer getBottleType() {
		return bottleType;
	}

	public void setBottleType(Integer bottleType) {
		this.bottleType = bottleType;
	}

	public String getLetterRefNum() {
		return letterRefNum;
	}

	public void setLetterRefNum(String letterRefNum) {
		this.letterRefNum = letterRefNum;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getRural_urban() {
		return rural_urban;
	}

	public void setRural_urban(Integer rural_urban) {
		this.rural_urban = rural_urban;
	}

	@Override
	public String toString() {
		return "ReceiveSampleBean [sampleId=" + sampleId + ", labMasterBean=" + labMasterBean + ", schemeMapping="
				+ schemeMapping + ", locationDetailBean=" + locationDetailBean + ", districtDetailBean="
				+ districtDetailBean + ", blockDetailBean=" + blockDetailBean + ", pmsSchemeDetailsBean="
				+ pmsSchemeDetailsBean + ", sampleNumber=" + sampleNumber + ", testType=" + testType
				+ ", collectionType=" + collectionType + ", poiType=" + poiType + ", mobileNo=" + mobileNo
				+ ", office_email_id=" + office_email_id + ", collected_by=" + collected_by + ", poi_id=" + poi_id
				+ ", collection_date=" + collection_date + ", schemeId=" + schemeId + ", waterSource=" + waterSource
				+ ", schemeStatus=" + schemeStatus + ", districtId=" + districtId + ", tehsilId=" + tehsilId
				+ ", villageId=" + villageId + ", blockId=" + blockId + ", gramPanchayatId=" + gramPanchayatId
				+ ", habitationType=" + habitationType + ", labTested=" + labTested + ", testingStatus=" + testingStatus
				+ ", createByUsr=" + createByUsr + ", designation=" + designation + ", activeFlag=" + activeFlag
				+ ", landmark=" + landmark + ", water_works_sample=" + water_works_sample + ", city=" + city
				+ ", sampleotherdetails=" + sampleotherdetails + ", depth=" + depth + ", bottleType=" + bottleType
				+ ", letterRefNum=" + letterRefNum + ", quantity=" + quantity + ", rural_urban=" + rural_urban + "]";
	}
	
}
