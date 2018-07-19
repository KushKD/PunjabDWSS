/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;
import java.sql.Date;

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

import com.prwss.min.sanitation.bean.ComboBeanDetails;

/**
 * @author bhsingh
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_parameter_Master",schema="prwss_main")
public class ParameterMasterBean implements Serializable{
	
private static final long serialVersionUID = 134534264844L;
	
	
	@Id
	@GeneratedValue(generator = "parameter_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "parameter_id_seq", sequenceName = "prwss_main.parameter_id_seq")
	@Column(name = "parameter_id")
	private Integer parameterId;
	
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="uom",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailUom;


	@Column(name = "parameter_name")
	private String parameterName;
	
	
	@Column(name = "parameter_id_name")
	private String parameter_id_name;
	
	@Column(name="uom")
	private Integer uom;
	
	@Column(name="permissible_limit")
	private String permissibleLimit;
	
	@Column(name="acceptable_limit")
	private String acceptableLimit;
	
	@Column(name="who_permissible_limit")
	private String wHOPermissibleLimit;
	
	@Column(name="who_acceptable_limit")
	private String wHOAcceptableLimit;
	
	@Column(name="start_date")
	private Date startDate;
	
	
	@Column(name="end_date")
	private Date endDate;

	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	
	@Column(name="crt_by_user")
	private Integer createdByUser;
	
	
	

	
	public String getParameter_id_name() {
		return parameter_id_name;
	}


	public void setParameter_id_name(String parameter_id_name) {
		this.parameter_id_name = parameter_id_name;
	}


	public ComboBeanDetails getCombodetailUom() {
		return combodetailUom;
	}


	public void setCombodetailUom(ComboBeanDetails combodetailUom) {
		this.combodetailUom = combodetailUom;
	}

	public Integer getParameterId() {
		return parameterId;
	}


	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}


	public String getParameterName() {
		return parameterName;
	}


	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}


	public Integer getUom() {
		return uom;
	}


	public void setUom(Integer uom) {
		this.uom = uom;
	}


	public String getPermissibleLimit() {
		return permissibleLimit;
	}


	public void setPermissibleLimit(String permissibleLimit) {
		this.permissibleLimit = permissibleLimit;
	}


	public String getAcceptableLimit() {
		return acceptableLimit;
	}


	public void setAcceptableLimit(String acceptableLimit) {
		this.acceptableLimit = acceptableLimit;
	}


	public String getwHOPermissibleLimit() {
		return wHOPermissibleLimit;
	}


	public void setwHOPermissibleLimit(String wHOPermissibleLimit) {
		this.wHOPermissibleLimit = wHOPermissibleLimit;
	}


	public String getwHOAcceptableLimit() {
		return wHOAcceptableLimit;
	}


	public void setwHOAcceptableLimit(String wHOAcceptableLimit) {
		this.wHOAcceptableLimit = wHOAcceptableLimit;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getActiveFlag() {
		return activeFlag;
	}


	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}


	public Integer getCreatedByUSer() {
		return createdByUser;
	}


	public void setCreatedByUSer(Integer createdByUSer) {
		this.createdByUser = createdByUSer;
	}


	@Override
	public String toString() {
		return "ParameterMasterBean [parameterId=" + parameterId + ", combodetailUom=" + combodetailUom
				+ ", parameterName=" + parameterName + ", parameter_id_name=" + parameter_id_name + ", uom=" + uom
				+ ", permissibleLimit=" + permissibleLimit + ", acceptableLimit=" + acceptableLimit
				+ ", wHOPermissibleLimit=" + wHOPermissibleLimit + ", wHOAcceptableLimit=" + wHOAcceptableLimit
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", activeFlag=" + activeFlag
				+ ", createdByUser=" + createdByUser + "]";
	}


	


	
}
