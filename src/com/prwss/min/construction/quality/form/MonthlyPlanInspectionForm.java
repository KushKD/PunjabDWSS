/**
 * 
 */
package com.prwss.min.construction.quality.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class MonthlyPlanInspectionForm extends ActionForm {

	private static final long serialVersionUID = -233110344776L;
	
	private String yearPlan;
	private String month;
	private String visitPerMonth;
	private String currentMonthVisit;
	private String component;
	private String team;
	private String district;
	private String division;
	private String scheme;
	private String constituency;
	private Datagrid monthlyPlanGrid;
	private String districtName;
	private String divisionName;
	private String schemeName;
	private String constituencyName;
	private String yearlyPlanName;
	private String monthlyPlanId;
	List<MonthlyPlanDto> monthlyPlanDtos;
	private Long loginUser;
	
	
	
	
	public Long getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Long loginUser) {
		this.loginUser = loginUser;
	}

	public String getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(String monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}

	public List<MonthlyPlanDto> getMonthlyPlanDtos() {
		return monthlyPlanDtos;
	}

	public void setMonthlyPlanDtos(List<MonthlyPlanDto> monthlyPlanDtos) {
		this.monthlyPlanDtos = monthlyPlanDtos;
	}

	public void setMonthlyPlanLst(int index, MonthlyPlanDto value) {
		System.out.println("---------inside MonthlyPlanSchemeMappingBean---------"+index);
		this.monthlyPlanDtos.add(index,value);
	}

	public MonthlyPlanDto getMonthlyPlanLst(int index) {
		
		System.out.println("----------index MonthlyPlanSchemeMappingBean size----------"+index);
		int size = monthlyPlanDtos.size();
		while (index >= size) {
			monthlyPlanDtos.add(new MonthlyPlanDto());
			size = monthlyPlanDtos.size();
		}
		return this.monthlyPlanDtos.get(index);
	}
	public String getYearlyPlanName() {
		return yearlyPlanName;
	}
	public void setYearlyPlanName(String yearlyPlanName) {
		this.yearlyPlanName = yearlyPlanName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Datagrid getMonthlyPlanGrid() {
		return monthlyPlanGrid;
	}
	public void setMonthlyPlanGrid(Datagrid monthlyPlanGrid) {
		this.monthlyPlanGrid = monthlyPlanGrid;
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
	public String getVisitPerMonth() {
		return visitPerMonth;
	}
	public void setVisitPerMonth(String visitPerMonth) {
		this.visitPerMonth = visitPerMonth;
	}
	public String getCurrentMonthVisit() {
		return currentMonthVisit;
	}
	public void setCurrentMonthVisit(String currentMonthVisit) {
		this.currentMonthVisit = currentMonthVisit;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	@Override
	public String toString() {
		return "MonthlyPlanInspectionForm [yearPlan=" + yearPlan + ", month=" + month + ", visitPerMonth="
				+ visitPerMonth + ", currentMonthVisit=" + currentMonthVisit + ", component=" + component + ", team="
				+ team + ", district=" + district + ", division=" + division + ", scheme=" + scheme + ", constituency="
				+ constituency + ", monthlyPlanGrid=" + monthlyPlanGrid + ", districtName=" + districtName
				+ ", divisionName=" + divisionName + ", schemeName=" + schemeName + ", constituencyName="
				+ constituencyName + ", yearlyPlanName=" + yearlyPlanName + ", monthlyPlanId=" + monthlyPlanId
				+ ", monthlyPlanDtos=" + monthlyPlanDtos + "]";
	}
	
}
