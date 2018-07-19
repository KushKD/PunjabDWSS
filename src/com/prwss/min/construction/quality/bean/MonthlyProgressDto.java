/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class MonthlyProgressDto implements Serializable {

	private static final long serialVersionUID=-1334545L;
	
	private String monthlyProgressId;
	private String componentId;
	private String componentName;
	private String teamId;
	private String teamName;
	private String villageToBeVisited;
	private String planning;
	private String implementation;
	private String postimplementaion;
	private String monthlyPlanId;
	
	
	public String getMonthlyPlanId() {
		return monthlyPlanId;
	}
	public void setMonthlyPlanId(String monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}
	public String getMonthlyProgressId() {
		return monthlyProgressId;
	}
	public void setMonthlyProgressId(String monthlyProgressId) {
		this.monthlyProgressId = monthlyProgressId;
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
	public String getVillageToBeVisited() {
		return villageToBeVisited;
	}
	public void setVillageToBeVisited(String villageToBeVisited) {
		this.villageToBeVisited = villageToBeVisited;
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
	public void setImplementation(String implementaion) {
		this.implementation = implementaion;
	}
	public String getPostimplementaion() {
		return postimplementaion;
	}
	public void setPostimplementaion(String postimplementaion) {
		this.postimplementaion = postimplementaion;
	}
	@Override
	public String toString() {
		return "MonthlyProgressDto [monthlyProgressId=" + monthlyProgressId + ", componentId=" + componentId
				+ ", componentName=" + componentName + ", teamId=" + teamId + ", teamName=" + teamName
				+ ", villageToBeVisited=" + villageToBeVisited + ", planning=" + planning + ", implementaion="
				+ implementation + ", postimplementaion=" + postimplementaion + "]";
	}
	
	
}
