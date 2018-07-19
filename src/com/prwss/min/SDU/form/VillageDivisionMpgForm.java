package com.prwss.min.SDU.form;

import org.apache.struts.validator.ValidatorForm;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class VillageDivisionMpgForm extends ValidatorForm {
	
	private static final long serialVersionUID = -423018650328188032L;
	
	private String stage;
	private String category;
	private String villages;
	private String component;
	private Datagrid villageDivisionMpgGrid;
	private String village;
	private String financialYear;
	private String division;
	private String component1;
	private Datagrid stage1;
	private String category1;
	private String financialYear1;
	private String division1;
	
	
	
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
	public String getVillages() {
		return villages;
	}
	public void setVillages(String villages) {
		this.villages = villages;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public Datagrid getVillageDivisionMpgGrid() {
		return villageDivisionMpgGrid;
	}
	public void setVillageDivisionMpgGrid(Datagrid villageDivisionMpgGrid) {
		this.villageDivisionMpgGrid = villageDivisionMpgGrid;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getComponent1() {
		return component1;
	}
	public void setComponent1(String component1) {
		this.component1 = component1;
	}
	public Datagrid getStage1() {
		return stage1;
	}
	public void setStage1(Datagrid stage1) {
		this.stage1 = stage1;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getFinancialYear1() {
		return financialYear1;
	}
	public void setFinancialYear1(String financialYear1) {
		this.financialYear1 = financialYear1;
	}
	public String getDivision1() {
		return division1;
	}
	public void setDivision1(String division1) {
		this.division1 = division1;
	}
	@Override
	public String toString() {
		return "VillageDivisionMpgForm [stage=" + stage + ", category=" + category + ", villages=" + villages
				+ ", component=" + component + ", villageDivisionMpgGrid=" + villageDivisionMpgGrid + ", village="
				+ village + ", financialYear=" + financialYear + ", division=" + division + ", component1=" + component1
				+ ", stage1=" + stage1 + ", category1=" + category1 + ", financialYear1=" + financialYear1
				+ ", division1=" + division1 + "]";
	}
	
}
