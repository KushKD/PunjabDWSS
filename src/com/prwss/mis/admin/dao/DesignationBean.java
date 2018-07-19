package com.prwss.mis.admin.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="mst_designation",schema="prwss_main")
public class DesignationBean implements Serializable{

	/**
	 * kush Dhawan
	 */
	private static final long serialVersionUID = 7694863033047035106L;
	
	@Id
	@GeneratedValue(generator = "mst_designation_desig_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "mst_designation_desig_id_seq", sequenceName = "prwss_main.mst_designation_desig_id_seq")
	@Column(name = "designation_id")
	private int designation_id;
	
	
	@Column(name = "desig_name_id")
	private String desig_name_id;
	
	@Column(name = "desig_name")
	private String desig_name;
	
	@Column(name = "hierarchy_level")
	private int hierarchy_level;

	public int getDesignation_id() {
		return designation_id;
	}

	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}

	public String getDesig_name_id() {
		return desig_name_id;
	}

	public void setDesig_name_id(String desig_name_id) {
		this.desig_name_id = desig_name_id;
	}

	public String getDesig_name() {
		return desig_name;
	}

	public void setDesig_name(String desig_name) {
		this.desig_name = desig_name;
	}

	public int getHierarchy_level() {
		return hierarchy_level;
	}

	public void setHierarchy_level(int hierarchy_level) {
		this.hierarchy_level = hierarchy_level;
	}

	@Override
	public String toString() {
		return "DesignationBean [designation_id=" + designation_id + ", desig_name_id=" + desig_name_id
				+ ", desig_name=" + desig_name + ", hierarchy_level=" + hierarchy_level + "]";
	}
	
	
	
	

}
