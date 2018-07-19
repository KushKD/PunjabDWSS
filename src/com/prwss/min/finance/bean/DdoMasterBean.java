/**
 * 
 */
package com.prwss.min.finance.bean;

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

import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_ddo_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DdoMasterBean implements Serializable{

	private static final long serialVersionUID = 36322556815L;

	@Id
	@GeneratedValue(generator = "finance_ddo_master_ddo_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_ddo_master_ddo_id_seq", sequenceName = "prwss_main.finance_ddo_master_ddo_id_seq")
	@Column(name="ddo_id")
	private Long ddoId;
	
	@OneToOne(targetEntity=LocationDivisionSubDivisonDetailsBean.class)
	@JoinColumn(name="division_id",referencedColumnName="div_subdiv_detail_id",insertable=false,updatable=false)
	private LocationDivisionSubDivisonDetailsBean  locationDivisionSubDivisonDetailsBean;
	
	
	@Column(name = "zone_id")
	private Integer zoneId;
	
	@Column(name = "circle_id")
	private Integer circleId;
	
	@Column(name = "division_id")
	private Integer divisionId;
	
	@Column(name = "ddo_number")
	private String ddoNumber;
	
	@Column(name = "ddo_name")
	private String ddoName;
	
	@Column(name = "treasury_id")
	private Integer treasuryId;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;

	
	public LocationDivisionSubDivisonDetailsBean getLocationDivisionSubDivisonDetailsBean() {
		return locationDivisionSubDivisonDetailsBean;
	}

	public void setLocationDivisionSubDivisonDetailsBean(
			LocationDivisionSubDivisonDetailsBean locationDivisionSubDivisonDetailsBean) {
		this.locationDivisionSubDivisonDetailsBean = locationDivisionSubDivisonDetailsBean;
	}

	public Long getDdoId() {
		return ddoId;
	}

	public void setDdoId(Long ddoId) {
		this.ddoId = ddoId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}


	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public String getDdoNumber() {
		return ddoNumber;
	}

	public void setDdoNumber(String ddoNumber) {
		this.ddoNumber = ddoNumber;
	}

	public String getDdoName() {
		return ddoName;
	}

	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}

	public Integer getTreasuryId() {
		return treasuryId;
	}

	public void setTreasuryId(Integer treasuryId) {
		this.treasuryId = treasuryId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Long crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	@Override
	public String toString() {
		return "DdoMasterBean [ddoId=" + ddoId + ", zoneId=" + zoneId + ", circleId=" + circleId + ", divsionId="
				+ divisionId + ", ddoNumber=" + ddoNumber + ", ddoName=" + ddoName + ", treasuryId=" + treasuryId
				+ ", activeFlag=" + activeFlag + ", crtByUsr=" + crtByUsr + "]";
	}
	
}
