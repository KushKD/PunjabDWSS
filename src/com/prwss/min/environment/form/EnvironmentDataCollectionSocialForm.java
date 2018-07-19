package com.prwss.min.environment.form;

import org.apache.struts.action.ActionForm;

public class EnvironmentDataCollectionSocialForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036230799035322810L;
	
	  private String population;
	  private String numberHouseholds;
	  private String pattrenLandUsed;
	  private String religiousImportance;
	  private String incomeSourse; 
	  private String naturalCultural;
	  private String naturalCulturalText;
	  private String rightsWater;
	  private String rightsWaterText;
	  private String extra;
	  private String edsId;
	  private String eds_social_env_id;
	
	  
	  
	  
	  
	  

	public String getEds_social_env_id() {
		return eds_social_env_id;
	}
	public void setEds_social_env_id(String eds_social_env_id) {
		this.eds_social_env_id = eds_social_env_id;
	}
	public String getPopulation() {
		return population;
	}
	public String getEdsId() {
		return edsId;
	}
	public void setEdsId(String edsId) {
		this.edsId = edsId;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getNumberHouseholds() {
		return numberHouseholds;
	}
	public void setNumberHouseholds(String numberHouseholds) {
		this.numberHouseholds = numberHouseholds;
	}
	public String getPattrenLandUsed() {
		return pattrenLandUsed;
	}
	public void setPattrenLandUsed(String pattrenLandUsed) {
		this.pattrenLandUsed = pattrenLandUsed;
	}
	public String getReligiousImportance() {
		return religiousImportance;
	}
	public void setReligiousImportance(String religiousImportance) {
		this.religiousImportance = religiousImportance;
	}
	public String getIncomeSourse() {
		return incomeSourse;
	}
	public void setIncomeSourse(String incomeSourse) {
		this.incomeSourse = incomeSourse;
	}
	public String getNaturalCultural() {
		return naturalCultural;
	}
	public void setNaturalCultural(String naturalCultural) {
		this.naturalCultural = naturalCultural;
	}
	public String getNaturalCulturalText() {
		return naturalCulturalText;
	}
	public void setNaturalCulturalText(String naturalCulturalText) {
		this.naturalCulturalText = naturalCulturalText;
	}
	public String getRightsWater() {
		return rightsWater;
	}
	public void setRightsWater(String rightsWater) {
		this.rightsWater = rightsWater;
	}
	public String getRightsWaterText() {
		return rightsWaterText;
	}
	public void setRightsWaterText(String rightsWaterText) {
		this.rightsWaterText = rightsWaterText;
	}
	
	
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	/*public String getWaterBourne() {
		return waterBourne;
	}
	public void setWaterBourne(String waterBourne) {
		this.waterBourne = waterBourne;
	}
	public String getWaterBourneName() {
		return waterBourneName;
	}
	public void setWaterBourneName(String waterBourneName) {
		this.waterBourneName = waterBourneName;
	}
	public String getVectorBourne() {
		return vectorBourne;
	}
	public void setVectorBourne(String vectorBourne) {
		this.vectorBourne = vectorBourne;
	}
	public String getVectorBourneText() {
		return vectorBourneText;
	}
	public void setVectorBourneText(String vectorBourneText) {
		this.vectorBourneText = vectorBourneText;
	}*/
	@Override
	public String toString() {
		return "EnvironmentDataCollectionSocialForm [population=" + population
				+ ", numberHouseholds=" + numberHouseholds
				+ ", pattrenLandUsed=" + pattrenLandUsed
				+ ", religiousImportance=" + religiousImportance
				+ ", incomeSourse=" + incomeSourse + ", naturalCultural="
				+ naturalCultural + ", naturalCulturalText="
				+ naturalCulturalText + ", rightsWater=" + rightsWater
				+ ", rightsWaterText=" + rightsWaterText + ", extra=" + extra
				+ ", edsId=" + edsId + ", eds_social_env_id="
				+ eds_social_env_id + "]";
	}
	
	
	
	
	
	
	
	  
	  

}
