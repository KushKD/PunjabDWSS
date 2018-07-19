package com.prwss.min.SDU.form;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.min.SDU.BO.TrackingDto;

public class TrackingForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5444532805157623755L;

	private String stage;
	private String category;
	private String activity;
	private String component;
	private String village;
	private String financialYear;
	private String division;
	
	private List<TrackingDto> trackingDtos;
	
	

	
	
	public List<TrackingDto> getTrackingDtos() {
		return trackingDtos;
	}

	public void setTrackingDtos(List<TrackingDto> trackingDtos) {
		this.trackingDtos = trackingDtos;
	}

	public void setTrackingDto(int index, TrackingDto value) {
		System.out.println("---------inside MonthlyPlanSchemeMappingBean---------"+index);
		this.trackingDtos.add(index,value);
	}

	public TrackingDto getTrackingDto(int index) {
		
		System.out.println("----------index MonthlyPlanSchemeMappingBean size----------"+index);
		int size = trackingDtos.size();
		while (index >= size) {
			trackingDtos.add(new TrackingDto());
			size = trackingDtos.size();
		}
		return this.trackingDtos.get(index);
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

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
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

	@Override
	public String toString() {
		return "TrackingForm [stage=" + stage + ", category=" + category + ", activity=" + activity + ", component="
				+ component + ", village=" + village + ", financialYear=" + financialYear + ", division=" + division
				+ ", trackingDTO=" + trackingDtos + "]";
	}

	

}
