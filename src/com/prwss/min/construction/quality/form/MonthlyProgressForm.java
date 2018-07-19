/**
 * 
 */
package com.prwss.min.construction.quality.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.prwss.min.construction.quality.bean.MonthlyProgressDto;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class MonthlyProgressForm extends ActionForm {

	private static final long serialVersionUID = -233110344776L;
	
	private String villageToBeVisit;
	private String yearPlan;
	private String month;
	private String component;
	private String planning;
	private String implementation;
	private String postImplementation;
	private String componentName;
	private String team;
	private String teamName;
	private List<MonthlyProgressDto> monthlyProgressDtos;
	private Datagrid monthlyProgressGrid;
	
	
	
	
	
	public List<MonthlyProgressDto> getMonthlyProgressDtos() {
		return monthlyProgressDtos;
	}
	public void setMonthlyProgressDtos(List<MonthlyProgressDto> monthlyProgressDtos) {
		this.monthlyProgressDtos = monthlyProgressDtos;
	}
	public void setMonthlyPlanLst(int index, MonthlyProgressDto value) {
		System.out.println("---------inside MonthlyProgress---------"+index);
		this.monthlyProgressDtos.add(index,value);
	}
	public MonthlyProgressDto getMonthlyPlanLst(int index) {
		System.out.println("----------index MonthlyProgress size----------"+index);
		int size = monthlyProgressDtos.size();
		while (index >= size) {
			monthlyProgressDtos.add(new MonthlyProgressDto());
			size = monthlyProgressDtos.size();
		}
		return this.monthlyProgressDtos.get(index);
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
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
	public Datagrid getMonthlyProgressGrid() {
		return monthlyProgressGrid;
	}
	public void setMonthlyProgressGrid(Datagrid monthlyProgressGrid) {
		this.monthlyProgressGrid = monthlyProgressGrid;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getVillageToBeVisit() {
		return villageToBeVisit;
	}
	public void setVillageToBeVisit(String villageToBeVisit) {
		this.villageToBeVisit = villageToBeVisit;
	}
	public String getYearPlan() {
		return yearPlan;
	}
	public void setYearPlan(String yearPlan) {
		this.yearPlan = yearPlan;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "MonthlyProgressForm [villageToBeVisit=" + villageToBeVisit + ", yearPlan=" + yearPlan + ", month="
				+ month + ", component=" + component + ", planning=" + planning + ", implementation=" + implementation
				+ ", postImplementation=" + postImplementation + ", monthlyProgressGrid=" + monthlyProgressGrid + "]";
	}
	
}
