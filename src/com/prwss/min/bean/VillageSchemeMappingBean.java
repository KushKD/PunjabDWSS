/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author bhsingh
 *
 */
@Entity

@Table(name="cmn_vlg_schm_mpg",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageSchemeMappingBean implements Serializable {

	private static final long serialVersionUID = -7858983657671926L;
	
	@Id
	@Column(name="vlg_schm_id")
	private Integer villageSchemeId;
	
	@Column(name="village_id")
	private Integer villageId;
	
	@Column(name="scheme_id")
	private Integer schemeId;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	
	
	
	public Integer getVillageSchemeId() {
		return villageSchemeId;
	}




	public void setVillageSchemeId(Integer villageSchemeId) {
		this.villageSchemeId = villageSchemeId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}




	@Override
	public String toString() {
		return "VillageSchemeMappingBean [villageSchemeId=" + villageSchemeId + ", villageId=" + villageId
				+ ", schemeId=" + schemeId + ", activeFlag=" + activeFlag + "]";
	}

}
