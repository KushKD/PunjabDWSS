package com.prwss.min.SDU.form;

import java.io.Serializable;

public class StageCompMpgPlanGrid implements Serializable {

	private static final long serialVersionUID = 3635765334815L;
	
	private String category;
	private String stage;
	private String component;
	private String noOfVillage;
	private String categoryName;
	private String stageName;
	private String componentName;
	
	
	
	
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
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
	@Override
	public String toString() {
		return "StageCompMpgPlanGrid [category=" + category + ", stage=" + stage + ", component=" + component
				+ ", noOfVillage=" + noOfVillage + ", categoryName=" + categoryName + ", stageName=" + stageName
				+ ", componentName=" + componentName + "]";
	}
	
	
	
	
}
