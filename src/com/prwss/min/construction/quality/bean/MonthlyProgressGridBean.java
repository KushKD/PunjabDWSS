/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class MonthlyProgressGridBean implements Serializable {

	private static final long serialVersionUID=-4323566666L;
	
	private String componentId;
	private String componentName;
	private String villageVisited;
	private String planning;
	private String implementation;
	private String postImplementation;
	private String teamId;
	private String teamName;
	private String progressId;
	
	public String getProgressId() {
		return progressId;
	}
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getVillageVisited() {
		return villageVisited;
	}
	public void setVillageVisited(String villageVisited) {
		this.villageVisited = villageVisited;
	}
	public String getPlanning() {
		return planning;
	}
	public void setPlanning(String planning) {
		this.planning = planning;
	}
	public String getImplementation() {
		return implementation;
	}
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	public String getPostImplementation() {
		return postImplementation;
	}
	public void setPostImplementation(String postImplementation) {
		this.postImplementation = postImplementation;
	}
	@Override
	public String toString() {
		return "MonthlyProgressGridBean [componentId=" + componentId + ", componentName=" + componentName
				+ ", villageVisited=" + villageVisited + ", planning=" + planning + ", implementation=" + implementation
				+ ", postImplementation=" + postImplementation + "]";
	}
}
