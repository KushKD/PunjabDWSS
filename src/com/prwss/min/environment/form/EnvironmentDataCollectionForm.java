package com.prwss.min.environment.form;

import org.apache.struts.action.ActionForm;

public class EnvironmentDataCollectionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4631474291348729396L;
	
	private String zone ; 
	private String district ; 
	private String districtName;
	private String block ; 
	private String blockName;
	private String villageId ;
	private String villageName;
	private String vi ;
	private String schemeCategory;
	private String schemeType;
	private String schemeTypeName;
	private String schemeId;
	private String schemeName;
	private String edsId;
	
	
	
	
	
	
	
	public String getEdsId() {
		return edsId;
	}
	public void setEdsId(String edsId) {
		this.edsId = edsId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSchemeTypeName() {
		return schemeTypeName;
	}
	public void setSchemeTypeName(String schemeTypeName) {
		this.schemeTypeName = schemeTypeName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
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
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVi() {
		return vi;
	}
	public void setVi(String vi) {
		this.vi = vi;
	}
	public String getSchemeCategory() {
		return schemeCategory;
	}
	public void setSchemeCategory(String schemeCategory) {
		this.schemeCategory = schemeCategory;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	@Override
	public String toString() {
		return "EnvironmentDataCollectionForm [zone=" + zone + ", district="
				+ district + ", districtName=" + districtName + ", block="
				+ block + ", blockName=" + blockName + ", villageId="
				+ villageId + ", villageName=" + villageName + ", vi=" + vi
				+ ", schemeCategory=" + schemeCategory + ", schemeType="
				+ schemeType + ", schemeTypeName=" + schemeTypeName
				+ ", schemeId=" + schemeId + ", schemeName=" + schemeName
				+ ", edsId=" + edsId + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
