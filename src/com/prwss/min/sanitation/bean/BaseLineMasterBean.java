/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="baseline_survey_mst",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BaseLineMasterBean implements Serializable {

	private static final long serialVersionUID = 36350123815L;
	
	@Id
	@GeneratedValue(generator="seq_baslinesurveyid",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_baslinesurveyid",sequenceName="prwss_main.seq_baslinesurveyid")
	@Column(name="baslinesurveyid")
	private int baslineSurveyId;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;
	
	@ManyToOne(targetEntity=BeneficiaryEntryBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="beneficiary_id",referencedColumnName="beneficiery_id", insertable=false, updatable=false)
	private BeneficiaryEntryBean beneficiaryEntryBean;
	
	@ManyToOne(targetEntity=SurveyMasterBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="surveyid",referencedColumnName="survey_id", insertable=false, updatable=false)
	private SurveyMasterBean surveyMasterBean;
	
	
	
	 
	public SurveyMasterBean getSurveyMasterBean() {
		return surveyMasterBean;
	}

	public void setSurveyMasterBean(SurveyMasterBean surveyMasterBean) {
		this.surveyMasterBean = surveyMasterBean;
	}

	@Column(name="surveyid")
	private Integer surveyId;
	
	@Column(name="district_id")
	private Integer districtId;
	
	@Column(name="beneficiary_id")
	private Integer beneficiaryId;
	
	@Column(name="block_id")
	private Integer blockId;
	
	@Column(name="gram_panchayat_id")
	private String gramPanchayatId;
	
	@Column(name="village_id")
	private Integer villageId;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag;

	@Column(name="isfreezed")
	private Integer isFreezed;
	
	@Column(name="issubmitted")
	private Integer isSubmitted;
	
	
	public Integer getIsSubmitted() {
		return isSubmitted;
	}

	public void setIsSubmitted(Integer isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public BeneficiaryEntryBean getBeneficiaryEntryBean() {
		return beneficiaryEntryBean;
	}

	public void setBeneficiaryEntryBean(BeneficiaryEntryBean beneficiaryEntryBean) {
		this.beneficiaryEntryBean = beneficiaryEntryBean;
	}

	public Integer getIsFreezed() {
		return isFreezed;
	}

	public void setIsFreezed(Integer isFreezed) {
		this.isFreezed = isFreezed;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public int getBaslineSurveyId() {
		return baslineSurveyId;
	}

	public void setBaslineSurveyId(int baslineSurveyId) {
		this.baslineSurveyId = baslineSurveyId;
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



	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
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

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	@Override
	public String toString() {
		return "BaseLineMasterBean [baslineSurveyId=" + baslineSurveyId + ", locationDetailBean=" + locationDetailBean
				+ ", districtDetailBean=" + districtDetailBean + ", blockDetailBean=" + blockDetailBean + ", surveyId="
				+ surveyId + ", districtId=" + districtId + ", beneficiaryId=" + beneficiaryId + ", blockId=" + blockId
				+ ", gramPanchayatId=" + gramPanchayatId + ", villageId=" + villageId + ", crtByUsr=" + crtByUsr
				+ ", activeFlag=" + activeFlag + "]";
	}


}
