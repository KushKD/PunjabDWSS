/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author bhsingh
 *
 */

@Entity
@Table(name="pms_scheme_mst",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PmsSchemeMaster implements Serializable {
	
	private static final long serialVersionUID = -78589837671926L;

	@Id
	@Column(name="scheme_id")
	private Integer schemeId;
	
	
	@Column(name="scheme_no")
	private String schemeNo;
	
	@Column(name="prog_id")
	private String progId;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@OneToOne(targetEntity=PMSSchemeDetailsBean.class)
	@JoinColumn(name="scheme_id",referencedColumnName="scheme_id", insertable=false,nullable=false)
	private PMSSchemeDetailsBean pMSSchemeDetailsBean;

	
	
	
	
	public PMSSchemeDetailsBean getpMSSchemeDetailsBean() {
		return pMSSchemeDetailsBean;
	}

	public void setpMSSchemeDetailsBean(PMSSchemeDetailsBean pMSSchemeDetailsBean) {
		this.pMSSchemeDetailsBean = pMSSchemeDetailsBean;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public String getSchemeNo() {
		return schemeNo;
	}

	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}

	public String getProgId() {
		return progId;
	}

	public void setProgId(String progId) {
		this.progId = progId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "PmsSchemeMaster [schemeId=" + schemeId + ", schemeNo=" + schemeNo + ", progId=" + progId
				+ ", activeFlag=" + activeFlag + ", pMSSchemeDetailsBean=" + pMSSchemeDetailsBean + "]";
	}

}
