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
import javax.persistence.SequenceGenerator;
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
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="fin_nodal_div_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class NodalDivisionBean implements Serializable {

	private static final long serialVersionUID = 36355336815L;
	
	@Id
	@GeneratedValue(generator = "fin_nodal_div_mapping_nodal_div_mpg_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "fin_nodal_div_mapping_nodal_div_mpg_id_seq", sequenceName = "prwss_main.fin_nodal_div_mapping_nodal_div_mpg_id_seq")
	@Column(name="nodal_div_mpg_id")
	private Long nodalDivisionId;
	
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "old_division")
	private Integer oldVersion;
	
	@Column(name = "location_id")
	private Integer locationId;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	public Long getNodalDivisionId() {
		return nodalDivisionId;
	}

	public void setNodalDivisionId(Long nodalDivisionId) {
		this.nodalDivisionId = nodalDivisionId;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getOldVersion() {
		return oldVersion;
	}

	public void setOldVersion(Integer oldVersion) {
		this.oldVersion = oldVersion;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
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

	@Override
	public String toString() {
		return "NodalDivisionBean [nodalDivisionId=" + nodalDivisionId + ", userId=" + userId + ", oldVersion="
				+ oldVersion + ", locationId=" + locationId + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag
				+ "]";
	}
	
}
