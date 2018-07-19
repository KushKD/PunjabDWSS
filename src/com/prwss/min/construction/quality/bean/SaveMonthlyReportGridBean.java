/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class SaveMonthlyReportGridBean implements Serializable {
	private static final long serialVersionUID=-4323566666L;
	private String observation;
	private String phaseId;
	private String phaseName;
	private String schemeId;
	private String schemeName;
	
	
	
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	@Override
	public String toString() {
		return "SaveMonthlyReportGridBean [observation=" + observation + "]";
	}
}
