package com.prwss.min.SDU.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.prwss.min.SDU.bean.CategoryVillageDto;
import com.prwss.min.SDU.bean.StageComponetDto;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class StageComponentMpgForm extends ActionForm {
	
	private static final long serialVersionUID = -2331103476L;
	
	private String financialYear;
	private String division;
	private String stage;
	private String category;
	private String component;
	private String noOfVillage;
	private List<StageComponetDto> stageComponetDtos; 
	
	
	
	
	public List<StageComponetDto> getStageComponetDtos() {
		return stageComponetDtos;
	}
	public void setStageComponetDtos(List<StageComponetDto> stageComponetDtos) {
		this.stageComponetDtos = stageComponetDtos;
	}
	private List<CategoryVillageDto> categoryVillageDtos;
	private Datagrid stageCompMpgPlanGrid;
	
	
	
	
	public void setMonthlyPlanLst(int index, StageComponetDto value) {
		System.out.println("---------inside MonthlyProgress---------"+index);
		this.stageComponetDtos.add(index,value);
	}
	public StageComponetDto getMonthlyPlanLst(int index) {
		System.out.println("----------index MonthlyProgress size----------"+index);
		int size = stageComponetDtos.size();
		while (index >= size) {
			stageComponetDtos.add(new StageComponetDto());
			size = stageComponetDtos.size();
		}
		return this.stageComponetDtos.get(index);
	}
	
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getNoOfVillage() {
		return noOfVillage;
	}
	public void setNoOfVillage(String noOfVillage) {
		this.noOfVillage = noOfVillage;
	}
	public List<CategoryVillageDto> getCategoryVillageDtos() {
		return categoryVillageDtos;
	}
	public void setCategoryVillageDtos(List<CategoryVillageDto> categoryVillageDtos) {
		this.categoryVillageDtos = categoryVillageDtos;
	}
	public Datagrid getStageCompMpgPlanGrid() {
		return stageCompMpgPlanGrid;
	}
	public void setStageCompMpgPlanGrid(Datagrid stageCompMpgPlanGrid) {
		this.stageCompMpgPlanGrid = stageCompMpgPlanGrid;
	}
	@Override
	public String toString() {
		return "StageComponentMpgForm [financialYear=" + financialYear + ", division=" + division + ", stage=" + stage
				+ ", category=" + category + ", component=" + component + ", noOfVillage=" + noOfVillage
				+ ", categoryVillageDtos=" + categoryVillageDtos + ", stageCompMpgPlanGrid=" + stageCompMpgPlanGrid
				+ "]";
	}
	
}
