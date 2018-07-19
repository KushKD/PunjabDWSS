/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Table(name="cc_external_agency_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ExternalAgencyMasterBean implements Serializable {

	private static final long serialVersionUID = 36350765889915L;
	
	@Id
	@Column(name="external_agency_id")
	private Integer external_agency_id;
	
	@Column(name="agency_name")
	private String agencyName;
	
	@Column(name="agency_description")
	private String agencyDescription;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;

	public Integer getExternal_agency_id() {
		return external_agency_id;
	}

	public void setExternal_agency_id(Integer external_agency_id) {
		this.external_agency_id = external_agency_id;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyDescription() {
		return agencyDescription;
	}

	public void setAgencyDescription(String agencyDescription) {
		this.agencyDescription = agencyDescription;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	@Override
	public String toString() {
		return "ExternalAgencyMasterBean [external_agency_id=" + external_agency_id + ", agencyName=" + agencyName
				+ ", agencyDescription=" + agencyDescription + ", activeFlag=" + activeFlag + ", crtByUsr=" + crtByUsr
				+ "]";
	}
	
}
