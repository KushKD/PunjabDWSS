package com.prwss.min.sanitation.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="cmn_cmb_dtl", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ComboBeanDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5555145896979436932L;

	@Id
	@Column(name="cmb_dtl_id", nullable=false)
	private int cmb_dtl_id;
	
	@Column(name="cmb_id")
	private int cmb_id;
	
	@Column(name="cmb_name")
	private String cmb_name;

	public int getCmb_dtl_id() {
		return cmb_dtl_id;
	}

	public void setCmb_dtl_id(int cmb_dtl_id) {
		this.cmb_dtl_id = cmb_dtl_id;
	}

	public int getCmb_id() {
		return cmb_id;
	}
	
	

	public void setCmb_id(int cmb_id) {
		this.cmb_id = cmb_id;
	}

	
	
	

	public String getCmb_name() {
		return cmb_name;
	}

	public void setCmb_name(String cmb_name) {
		this.cmb_name = cmb_name;
	}

	@Override
	public String toString() {
		return "ComboBeanDetails [cmb_dtl_id=" + cmb_dtl_id + ", cmb_id=" + cmb_id + ", cmb_name=" + cmb_name + "]";
	}

	
	
	
	
	
	
	

}
