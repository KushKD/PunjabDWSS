/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="san_latrine_usage",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LatrineUsageBean implements Serializable {

	private static final long serialVersionUID = 36350123823215L;

	@Id
	@GeneratedValue(generator="latrine_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="latrine_id_seq",sequenceName="prwss_main.latrine_id_seq")
	@Column(name="latrine_id")
	private int latrineId;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean locationDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="block_id", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean blockDetailBean;
	
	@ManyToOne(targetEntity=BeneficiaryEntryBean.class)
	@JoinColumn(name="beneficiary_id",referencedColumnName="beneficiery_id", insertable=false, updatable=false)
	private BeneficiaryEntryBean beneficiaryEntryBean;
	
	
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

	@Column(name="member_id")
	private Integer memberId;

	@Column(name="comments")
	private String comments;

	
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getLatrineId() {
		return latrineId;
	}

	public void setLatrineId(int latrineId) {
		this.latrineId = latrineId;
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

	public BeneficiaryEntryBean getBeneficiaryEntryBean() {
		return beneficiaryEntryBean;
	}

	public void setBeneficiaryEntryBean(BeneficiaryEntryBean beneficiaryEntryBean) {
		this.beneficiaryEntryBean = beneficiaryEntryBean;
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

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "LatrineUsageBean [latrineId=" + latrineId + ", locationDetailBean=" + locationDetailBean
				+ ", districtDetailBean=" + districtDetailBean + ", blockDetailBean=" + blockDetailBean
				+ ", beneficiaryEntryBean=" + beneficiaryEntryBean + ", districtId=" + districtId + ", beneficiaryId="
				+ beneficiaryId + ", blockId=" + blockId + ", gramPanchayatId=" + gramPanchayatId + ", villageId="
				+ villageId + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag + ", memberId=" + memberId + "]";
	}
	
}
