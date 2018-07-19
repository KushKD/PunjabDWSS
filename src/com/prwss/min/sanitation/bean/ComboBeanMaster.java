package com.prwss.min.sanitation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="cmn_cmb_mst", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ComboBeanMaster implements Serializable {
	
	private static final long serialVersionUID = 5554343436979436932L;

	@Id
	@Column(name="cmb_id", nullable=false)
	private int cmb_id;
	
	@Column(name="cmb_parent_id")
	private int cmb_parent_id;
	
	@Column(name="active_flag")
	private int active_flag;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="cmb_id", nullable=false)
	private ComboBeanDetails comboBeanDetail;

	public int getCmb_id() {
		return cmb_id;
	}

	public ComboBeanDetails getComboBeanDetail() {
		return comboBeanDetail;
	}

	public void setComboBeanDetail(ComboBeanDetails comboBeanDetail) {
		this.comboBeanDetail = comboBeanDetail;
	}

	public void setCmb_id(int cmb_id) {
		this.cmb_id = cmb_id;
	}

	public int getCmb_parent_id() {
		return cmb_parent_id;
	}

	public void setCmb_parent_id(int cmb_parent_id) {
		this.cmb_parent_id = cmb_parent_id;
	}

	public int getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(int active_flag) {
		this.active_flag = active_flag;
	}

	@Override
	public String toString() {
		return "ComboBeanMaster [cmb_id=" + cmb_id + ", cmb_parent_id=" + cmb_parent_id + ", active_flag=" + active_flag
				+ "]";
	}
	
	
	
}
