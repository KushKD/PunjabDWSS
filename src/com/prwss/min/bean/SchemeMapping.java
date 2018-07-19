/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author bhsingh
 *
 */
@Entity
@Table(name="scheme_ws_mapping",schema="prwss_main")
public class SchemeMapping implements Serializable {

	private static final long serialVersionUID = 1345341239344L;


	@Id
	@GeneratedValue(generator = "scheme_ws_mpg_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "scheme_ws_mpg_id_seq", sequenceName = "prwss_main.scheme_ws_mpg_id_seq")
	@Column(name = "scheme_ws_mpg_id")
	private int schemeWSId;
	
	@Column(name = "scheme_id")
	private Integer schemeId;
	
	@Column(name = "ws_id")
	private Integer waterSourceId;
	
	@Column(name = "ws_name")
	private String wsName;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	public int getSchemeWSId() {
		return schemeWSId;
	}

	public void setSchemeWSId(int schemeWSId) {
		this.schemeWSId = schemeWSId;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public Integer getWaterSourceId() {
		return waterSourceId;
	}

	public void setWaterSourceId(Integer waterSourceId) {
		this.waterSourceId = waterSourceId;
	}

	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "SchemeMapping [schemeWSId=" + schemeWSId + ", schemeId=" + schemeId + ", waterSourceId=" + waterSourceId
				+ ", wsName=" + wsName + ", activeFlag=" + activeFlag + "]";
	}
	
}
