package com.prwss.min.environment.form;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

public class EnvironmentDataCollectionHealthForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 693082623374047014L;

	private String edsId;
    private String eds_pblc_hlth_id;
    private String waterBourne;
	private String[] waterBourneName;
	private String vectorBourne;
	private String[] vectorBourneText;
	private String extra;
	public String getEdsId() {
		return edsId;
	}
	public void setEdsId(String edsId) {
		this.edsId = edsId;
	}
	public String getEds_pblc_hlth_id() {
		return eds_pblc_hlth_id;
	}
	public void setEds_pblc_hlth_id(String eds_pblc_hlth_id) {
		this.eds_pblc_hlth_id = eds_pblc_hlth_id;
	}
	public String getWaterBourne() {
		return waterBourne;
	}
	public void setWaterBourne(String waterBourne) {
		this.waterBourne = waterBourne;
	}
	public String[] getWaterBourneName() {
		return waterBourneName;
	}
	public void setWaterBourneName(String[] waterBourneName) {
		this.waterBourneName = waterBourneName;
	}
	public String getVectorBourne() {
		return vectorBourne;
	}
	public void setVectorBourne(String vectorBourne) {
		this.vectorBourne = vectorBourne;
	}
	public String[] getVectorBourneText() {
		return vectorBourneText;
	}
	public void setVectorBourneText(String[] vectorBourneText) {
		this.vectorBourneText = vectorBourneText;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	@Override
	public String toString() {
		return "EnvironmentDataCollectionHealthForm [edsId=" + edsId
				+ ", eds_pblc_hlth_id=" + eds_pblc_hlth_id + ", waterBourne="
				+ waterBourne + ", waterBourneName="
				+ Arrays.toString(waterBourneName) + ", vectorBourne="
				+ vectorBourne + ", vectorBourneText="
				+ Arrays.toString(vectorBourneText) + ", extra=" + extra + "]";
	}
	
	
	
	
	

	
	
	
	

}
