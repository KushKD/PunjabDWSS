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
@Table(name = "pms_scheme_dtl", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class PMSSchemeDetailsBean implements Serializable {

	private static final long serialVersionUID = -78589836671926L;

	@Id
	@Column(name = "scheme_dtl_pk")
	private Integer schemeDtlPk;

	@OneToOne(targetEntity=PmsSchemeMaster.class)
	@JoinColumn(name="scheme_id",referencedColumnName="scheme_id", insertable=false,updatable=false)
	private PmsSchemeMaster pmsSchemeMaster;

	@Column(name = "scheme_name")
	private String schemeName;

	@Column(name = "scheme_id")
	private Integer scheme_id;

	
	
	public PmsSchemeMaster getPmsSchemeMaster() {
		return pmsSchemeMaster;
	}

	public void setPmsSchemeMaster(PmsSchemeMaster pmsSchemeMaster) {
		this.pmsSchemeMaster = pmsSchemeMaster;
	}

	public Integer getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(Integer scheme_id) {
		this.scheme_id = scheme_id;
	}
//
//	public ReceiveSampleBean getReceiveSampleBean() {
//		return receiveSampleBean;
//	}
//
//	public void setReceiveSampleBean(ReceiveSampleBean receiveSampleBean) {
//		this.receiveSampleBean = receiveSampleBean;
//	}

	public Integer getSchemeDtlPk() {
		return schemeDtlPk;
	}

	public void setSchemeDtlPk(Integer schemeDtlPk) {
		this.schemeDtlPk = schemeDtlPk;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

}
