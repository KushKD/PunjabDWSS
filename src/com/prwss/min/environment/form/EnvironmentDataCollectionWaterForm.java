package com.prwss.min.environment.form;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EnvironmentDataCollectionWaterForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 512489125581154270L;
	
	private String id;
	private String schemeType;
	private String proposedWaterSupplyScheme;
	private String currentDrinkingwaterSituation;
	private String sourceDrinkingWater;
	private String waterAvailabilityLpcd;
	private String availabilityLandIntakeOrWTPSite;
	private String assessedWaterQuality;
	private String[] natureQualityProblem;
	private String riskContamination;
	private String correctiveActionsTaken;
	private String provitionBoreWell;
	private String provitionBoreWellSanctioned;
	private String canalSourceTreatment;
	private String groundwaterTechnology;
	private String hasDisinfectionSystemProvided;
	private String hasDisinfectionSystemProvidedType;
	private String airWaterNoisTesting;
	private String lawnsTreesPlantation;
	private String waterAvailabilityParameter;
	
	
	
	
	private String eds_id;
	
	
	
	private Datagrid waterSupplyGrid;
	
	private String parameterEnvironment;
	private String parameterMeasures;
	private String parameterIssues;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameterEnvironment() {
		return parameterEnvironment;
	}
	public void setParameterEnvironment(String parameterEnvironment) {
		this.parameterEnvironment = parameterEnvironment;
	}
	public String getParameterMeasures() {
		return parameterMeasures;
	}
	public void setParameterMeasures(String parameterMeasures) {
		this.parameterMeasures = parameterMeasures;
	}
	public String getParameterIssues() {
		return parameterIssues;
	}
	public void setParameterIssues(String parameterIssues) {
		this.parameterIssues = parameterIssues;
	}
	public Datagrid getWaterSupplyGrid() {
		return waterSupplyGrid;
	}
	public void setWaterSupplyGrid(Datagrid waterSupplyGrid) {
		this.waterSupplyGrid = waterSupplyGrid;
	}
	
	public String getEds_id() {
		return eds_id;
	}
	public void setEds_id(String eds_id) {
		this.eds_id = eds_id;
	}
	
	
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getProposedWaterSupplyScheme() {
		return proposedWaterSupplyScheme;
	}
	public void setProposedWaterSupplyScheme(String proposedWaterSupplyScheme) {
		this.proposedWaterSupplyScheme = proposedWaterSupplyScheme;
	}
	public String getCurrentDrinkingwaterSituation() {
		return currentDrinkingwaterSituation;
	}
	public void setCurrentDrinkingwaterSituation(
			String currentDrinkingwaterSituation) {
		this.currentDrinkingwaterSituation = currentDrinkingwaterSituation;
	}
	public String getSourceDrinkingWater() {
		return sourceDrinkingWater;
	}
	public void setSourceDrinkingWater(String sourceDrinkingWater) {
		this.sourceDrinkingWater = sourceDrinkingWater;
	}
	public String getWaterAvailabilityLpcd() {
		return waterAvailabilityLpcd;
	}
	public void setWaterAvailabilityLpcd(String waterAvailabilityLpcd) {
		this.waterAvailabilityLpcd = waterAvailabilityLpcd;
	}
	public String getAvailabilityLandIntakeOrWTPSite() {
		return availabilityLandIntakeOrWTPSite;
	}
	public void setAvailabilityLandIntakeOrWTPSite(
			String availabilityLandIntakeOrWTPSite) {
		this.availabilityLandIntakeOrWTPSite = availabilityLandIntakeOrWTPSite;
	}
	public String getAssessedWaterQuality() {
		return assessedWaterQuality;
	}
	public void setAssessedWaterQuality(String assessedWaterQuality) {
		this.assessedWaterQuality = assessedWaterQuality;
	}
	
	public String[] getNatureQualityProblem() {
		return natureQualityProblem;
	}
	public void setNatureQualityProblem(String[] natureQualityProblem) {
		this.natureQualityProblem = natureQualityProblem;
	}
	public String getRiskContamination() {
		return riskContamination;
	}
	public void setRiskContamination(String riskContamination) {
		this.riskContamination = riskContamination;
	}
	public String getCorrectiveActionsTaken() {
		return correctiveActionsTaken;
	}
	public void setCorrectiveActionsTaken(String correctiveActionsTaken) {
		this.correctiveActionsTaken = correctiveActionsTaken;
	}
	public String getProvitionBoreWell() {
		return provitionBoreWell;
	}
	public void setProvitionBoreWell(String provitionBoreWell) {
		this.provitionBoreWell = provitionBoreWell;
	}
	public String getProvitionBoreWellSanctioned() {
		return provitionBoreWellSanctioned;
	}
	public void setProvitionBoreWellSanctioned(String provitionBoreWellSanctioned) {
		this.provitionBoreWellSanctioned = provitionBoreWellSanctioned;
	}
	public String getCanalSourceTreatment() {
		return canalSourceTreatment;
	}
	public void setCanalSourceTreatment(String canalSourceTreatment) {
		this.canalSourceTreatment = canalSourceTreatment;
	}
	public String getGroundwaterTechnology() {
		return groundwaterTechnology;
	}
	public void setGroundwaterTechnology(String groundwaterTechnology) {
		this.groundwaterTechnology = groundwaterTechnology;
	}
	public String getHasDisinfectionSystemProvided() {
		return hasDisinfectionSystemProvided;
	}
	public void setHasDisinfectionSystemProvided(
			String hasDisinfectionSystemProvided) {
		this.hasDisinfectionSystemProvided = hasDisinfectionSystemProvided;
	}
	public String getHasDisinfectionSystemProvidedType() {
		return hasDisinfectionSystemProvidedType;
	}
	public void setHasDisinfectionSystemProvidedType(
			String hasDisinfectionSystemProvidedType) {
		this.hasDisinfectionSystemProvidedType = hasDisinfectionSystemProvidedType;
	}
	public String getAirWaterNoisTesting() {
		return airWaterNoisTesting;
	}
	public void setAirWaterNoisTesting(String airWaterNoisTesting) {
		this.airWaterNoisTesting = airWaterNoisTesting;
	}
	public String getLawnsTreesPlantation() {
		return lawnsTreesPlantation;
	}
	public void setLawnsTreesPlantation(String lawnsTreesPlantation) {
		this.lawnsTreesPlantation = lawnsTreesPlantation;
	}
	public String getWaterAvailabilityParameter() {
		return waterAvailabilityParameter;
	}
	public void setWaterAvailabilityParameter(String waterAvailabilityParameter) {
		this.waterAvailabilityParameter = waterAvailabilityParameter;
	}
	@Override
	public String toString() {
		return "EnvironmentDataCollectionWaterForm [id=" + id + ", schemeType="
				+ schemeType + ", proposedWaterSupplyScheme="
				+ proposedWaterSupplyScheme
				+ ", currentDrinkingwaterSituation="
				+ currentDrinkingwaterSituation + ", sourceDrinkingWater="
				+ sourceDrinkingWater + ", waterAvailabilityLpcd="
				+ waterAvailabilityLpcd + ", availabilityLandIntakeOrWTPSite="
				+ availabilityLandIntakeOrWTPSite + ", assessedWaterQuality="
				+ assessedWaterQuality + ", natureQualityProblem="
				+ Arrays.toString(natureQualityProblem)
				+ ", riskContamination=" + riskContamination
				+ ", correctiveActionsTaken=" + correctiveActionsTaken
				+ ", provitionBoreWell=" + provitionBoreWell
				+ ", provitionBoreWellSanctioned="
				+ provitionBoreWellSanctioned + ", canalSourceTreatment="
				+ canalSourceTreatment + ", groundwaterTechnology="
				+ groundwaterTechnology + ", hasDisinfectionSystemProvided="
				+ hasDisinfectionSystemProvided
				+ ", hasDisinfectionSystemProvidedType="
				+ hasDisinfectionSystemProvidedType + ", airWaterNoisTesting="
				+ airWaterNoisTesting + ", lawnsTreesPlantation="
				+ lawnsTreesPlantation + ", waterAvailabilityParameter="
				+ waterAvailabilityParameter + ", eds_id=" + eds_id
				+ ", waterSupplyGrid=" + waterSupplyGrid
				+ ", parameterEnvironment=" + parameterEnvironment
				+ ", parameterMeasures=" + parameterMeasures
				+ ", parameterIssues=" + parameterIssues + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
