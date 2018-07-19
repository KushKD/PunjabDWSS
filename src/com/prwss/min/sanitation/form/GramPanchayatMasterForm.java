package com.prwss.min.sanitation.form;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class GramPanchayatMasterForm extends ValidatorForm{
	private static final long serialVersionUID = -8813762114725832058L;
	
	private String nameofGramPanchayat;
	private String nameofSarpanch;
	private String district;
	private String block;
	private String village;
	private String gramPanchayatId;
	private Long createdByUser;
	private String locationId;
	private String status;
	private String gramPanchayatVillageId;
	private Datagrid gramPanchayaMasterGrid;
	
	
	
	
	
	
	
	
	
	
	

	public Datagrid getGramPanchayaMasterGrid() {
		return gramPanchayaMasterGrid;
	}
	public void setGramPanchayaMasterGrid(Datagrid gramPanchayaMasterGrid) {
		this.gramPanchayaMasterGrid = gramPanchayaMasterGrid;
	}
	public String getGramPanchayatVillageId() {
		return gramPanchayatVillageId;
	}
	public void setGramPanchayatVillageId(String gramPanchayatVillageId) {
		this.gramPanchayatVillageId = gramPanchayatVillageId;
	}
	public Long getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNameofGramPanchayat() {
		return nameofGramPanchayat;
	}
	public void setNameofGramPanchayat(String nameofGramPanchayat) {
		this.nameofGramPanchayat = nameofGramPanchayat;
	}
	public String getNameofSarpanch() {
		return nameofSarpanch;
	}
	public void setNameofSarpanch(String nameofSarpanch) {
		this.nameofSarpanch = nameofSarpanch;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getGramPanchayatId() {
		return gramPanchayatId;
	}
	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}
	@Override
	public String toString() {
		return "GramPanchayatMasterForm [nameofGramPanchayat="
				+ nameofGramPanchayat + ", nameofSarpanch=" + nameofSarpanch
				+ ", district=" + district + ", block=" + block + ", village="
				+ village + ", gramPanchayatId=" + gramPanchayatId
				+ ", createdByUser=" + createdByUser + ", locationId="
				+ locationId + ", status=" + status
				+ ", gramPanchayatVillageId=" + gramPanchayatVillageId
				+ ", gramPanchayaMasterGrid=" + gramPanchayaMasterGrid + "]";
	}
	
	
	
	
}