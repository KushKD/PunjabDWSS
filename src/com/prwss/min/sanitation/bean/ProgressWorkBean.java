/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "san_progress_work", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgressWorkBean implements Serializable {

	private static final long serialVersionUID = -132323425344L;
	
	@Id
	@GeneratedValue(generator="progress_work_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="progress_work_id_seq",sequenceName="prwss_main.progress_work_id_seq")
	@Column(name="progress_work_id")
	private int progressWorkId;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;
	

	
	@OneToOne(targetEntity=BeneficiaryEntryBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="beneficiary_id",referencedColumnName="beneficiery_id", insertable=false, updatable=false)
	private BeneficiaryEntryBean beneficiaryEntryBean;
	
	@OneToMany(targetEntity = ProgressStageMappingBean.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name = "progress_work_id", updatable = false, insertable = false)
	private Set<ProgressStageMappingBean> progressStageMappingBean;

	
	
	
	
	public BeneficiaryEntryBean getBeneficiaryEntryBean() {
		return beneficiaryEntryBean;
	}

	public void setBeneficiaryEntryBean(BeneficiaryEntryBean beneficiaryEntryBean) {
		this.beneficiaryEntryBean = beneficiaryEntryBean;
	}

	public Set<ProgressStageMappingBean> getProgressStageMappingBean() {
		return progressStageMappingBean;
	}

	public void setProgressStageMappingBean(Set<ProgressStageMappingBean> progressStageMappingBean) {
		this.progressStageMappingBean = progressStageMappingBean;
	}



	@Column(name="district_id")
	private Integer districtId;
	
	@Column(name="beneficiary_id")
	private Integer beneficiaryId;
	
	@Column(name="block_id")
	private Integer blockId;
	
	@Column(name="gram_panchayat_id")
	private Integer gramPanchayatId;
	
	
	@Column(name="village_id")
	private Integer villageId;
	
	@Column(name="crt_date")
	private Date crtDate;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	
	

	public int getProgressWorkId() {
		return progressWorkId;
	}

	public void setProgressWorkId(int progressWorkId) {
		this.progressWorkId = progressWorkId;
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

	

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Integer getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(Integer gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
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

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "ProgressWorkBean [progressWorkId=" + progressWorkId + ", locationDetailBean=" + locationDetailBean
				+ ", districtDetailBean=" + districtDetailBean + ", blockDetailBean=" + blockDetailBean
				+ ", progressStageMappingBean=" + progressStageMappingBean + ", districtId=" + districtId
				+ ", beneficiaryId=" + beneficiaryId + ", blockId=" + blockId + ", gramPanchayatId=" + gramPanchayatId
				+ ", villageId=" + villageId + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + "]";
	}

}
