package com.prwss.mis.masters.employee.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_designation", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeeDesignationBean implements Serializable{
	
	private static final long serialVersionUID = 222L;
	
@Id
@Column(name = "desig_id", nullable = false)
private int designationId;

@Column(name = "desig_name_id")
private String designationNameId;

@Column(name = "desig_name")
private String designationName;

@Column(name = "hierarchy_level")
private Long hierarchyLevel;

public int getDesignationId() {
	return designationId;
}

public void setDesignationId(int designationId) {
	this.designationId = designationId;
}

public String getDesignationName() {
	return designationName;
}

public void setDesignationName(String designationName) {
	this.designationName = designationName;
}



/*@Override
public int compareTo(EmployeeDesignationBean o) {
	 
	return this.designationId.trim().compareTo(o.designationId.trim());
}*/
 


public String getDesignationNameId() {
	return designationNameId;
}

public void setDesignationNameId(String designationNameId) {
	this.designationNameId = designationNameId;
}

public void setHierarchyLevel(Long hierarchyLevel) {
	this.hierarchyLevel = hierarchyLevel;
}

public Long getHierarchyLevel() {
	return hierarchyLevel;
}


}
