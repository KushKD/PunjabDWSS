package com.prwss.min.environment.form;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

public class EnvironmentDataCollectionBaseLineEnvironmentForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4619841811051920142L;
	
	private String edsId;
	private String edsBaselineId;
	private String topography ; 
	private String soil; 
	private String rainfall; 
	private String temperatureMin;
	private String temperatueMax;
	private String scopeOfLand;
	private String predominentWindDirection;
	private String waterTable;
	private String existingWaterBodyWithinVillage;
	private String existingWaterBodyWithinVillageOthers;
	private String[] ifPondCurrentUse;
	private String waterLoggingProblemsYesNo;
	private String nameOfAreas;
	private String areaUnderWaterLogging;
	private String periodOfWaterLogging;
	private String populationEffected;
	private String contamination;
	private String extra;
	private String maximumWidth;
	private String minimumWidth;
	private String existingRoads;
	private String[] solidWasteDisposable;
	private String localVegitation;
	
	
	
	
	
	
	public String getEdsBaselineId() {
		return edsBaselineId;
	}
	public void setEdsBaselineId(String edsBaselineId) {
		this.edsBaselineId = edsBaselineId;
	}
	public String getEdsId() {
		return edsId;
	}
	public void setEdsId(String edsId) {
		this.edsId = edsId;
	}
	public String getTopography() {
		return topography;
	}
	public void setTopography(String topography) {
		this.topography = topography;
	}
	public String getSoil() {
		return soil;
	}
	public void setSoil(String soil) {
		this.soil = soil;
	}
	public String getRainfall() {
		return rainfall;
	}
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
	}
	
	public String getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(String temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	public String getTemperatueMax() {
		return temperatueMax;
	}
	public void setTemperatueMax(String temperatueMax) {
		this.temperatueMax = temperatueMax;
	}
	public String getScopeOfLand() {
		return scopeOfLand;
	}
	public void setScopeOfLand(String scopeOfLand) {
		this.scopeOfLand = scopeOfLand;
	}
	public String getPredominentWindDirection() {
		return predominentWindDirection;
	}
	public void setPredominentWindDirection(String predominentWindDirection) {
		this.predominentWindDirection = predominentWindDirection;
	}
	public String getWaterTable() {
		return waterTable;
	}
	public void setWaterTable(String waterTable) {
		this.waterTable = waterTable;
	}
	public String getExistingWaterBodyWithinVillage() {
		return existingWaterBodyWithinVillage;
	}
	public void setExistingWaterBodyWithinVillage(
			String existingWaterBodyWithinVillage) {
		this.existingWaterBodyWithinVillage = existingWaterBodyWithinVillage;
	}
	
	
	public String getExistingWaterBodyWithinVillageOthers() {
		return existingWaterBodyWithinVillageOthers;
	}
	public void setExistingWaterBodyWithinVillageOthers(
			String existingWaterBodyWithinVillageOthers) {
		this.existingWaterBodyWithinVillageOthers = existingWaterBodyWithinVillageOthers;
	}
	public String[] getIfPondCurrentUse() {
		return ifPondCurrentUse;
	}
	public void setIfPondCurrentUse(String[] ifPondCurrentUse) {
		this.ifPondCurrentUse = ifPondCurrentUse;
	}
	public String getWaterLoggingProblemsYesNo() {
		return waterLoggingProblemsYesNo;
	}
	public void setWaterLoggingProblemsYesNo(String waterLoggingProblemsYesNo) {
		this.waterLoggingProblemsYesNo = waterLoggingProblemsYesNo;
	}
	public String getNameOfAreas() {
		return nameOfAreas;
	}
	public void setNameOfAreas(String nameOfAreas) {
		this.nameOfAreas = nameOfAreas;
	}
	public String getAreaUnderWaterLogging() {
		return areaUnderWaterLogging;
	}
	public void setAreaUnderWaterLogging(String areaUnderWaterLogging) {
		this.areaUnderWaterLogging = areaUnderWaterLogging;
	}
	public String getPeriodOfWaterLogging() {
		return periodOfWaterLogging;
	}
	public void setPeriodOfWaterLogging(String periodOfWaterLogging) {
		this.periodOfWaterLogging = periodOfWaterLogging;
	}
	public String getPopulationEffected() {
		return populationEffected;
	}
	public void setPopulationEffected(String populationEffected) {
		this.populationEffected = populationEffected;
	}
	public String getContamination() {
		return contamination;
	}
	public void setContamination(String contamination) {
		this.contamination = contamination;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getMaximumWidth() {
		return maximumWidth;
	}
	public void setMaximumWidth(String maximumWidth) {
		this.maximumWidth = maximumWidth;
	}
	public String getMinimumWidth() {
		return minimumWidth;
	}
	public void setMinimumWidth(String minimumWidth) {
		this.minimumWidth = minimumWidth;
	}
	public String getExistingRoads() {
		return existingRoads;
	}
	public void setExistingRoads(String existingRoads) {
		this.existingRoads = existingRoads;
	}
	public String[] getSolidWasteDisposable() {
		return solidWasteDisposable;
	}
	public void setSolidWasteDisposable(String[] solidWasteDisposable) {
		this.solidWasteDisposable = solidWasteDisposable;
	}
	public String getLocalVegitation() {
		return localVegitation;
	}
	public void setLocalVegitation(String localVegitation) {
		this.localVegitation = localVegitation;
	}
	@Override
	public String toString() {
		return "EnvironmentDataCollectionBaseLineEnvironmentForm [edsId="
				+ edsId + ", edsBaselineId=" + edsBaselineId + ", topography="
				+ topography + ", soil=" + soil + ", rainfall=" + rainfall
				+ ", temperatureMin=" + temperatureMin + ", temperatueMax="
				+ temperatueMax + ", scopeOfLand=" + scopeOfLand
				+ ", predominentWindDirection=" + predominentWindDirection
				+ ", waterTable=" + waterTable
				+ ", existingWaterBodyWithinVillage="
				+ existingWaterBodyWithinVillage
				+ ", existingWaterBodyWithinVillageOthers="
				+ existingWaterBodyWithinVillageOthers + ", ifPondCurrentUse="
				+ Arrays.toString(ifPondCurrentUse)
				+ ", waterLoggingProblemsYesNo=" + waterLoggingProblemsYesNo
				+ ", nameOfAreas=" + nameOfAreas + ", areaUnderWaterLogging="
				+ areaUnderWaterLogging + ", periodOfWaterLogging="
				+ periodOfWaterLogging + ", populationEffected="
				+ populationEffected + ", contamination=" + contamination
				+ ", extra=" + extra + ", maximumWidth=" + maximumWidth
				+ ", minimumWidth=" + minimumWidth + ", existingRoads="
				+ existingRoads + ", solidWasteDisposable="
				+ Arrays.toString(solidWasteDisposable) + ", localVegitation="
				+ localVegitation + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
