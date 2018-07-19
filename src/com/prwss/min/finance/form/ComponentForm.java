/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.action.ActionForm;

/**
 * @author BH390738
 *
 */
public class ComponentForm extends ActionForm {

	private static final long serialVersionUID = -3635554815L;
	
	private String componentName;
	private String componentType;
	private String parentComponent;
	private String status;
	private String description;
	private String componentId;
	private String componentDetailsId;
	
	
	
	public String getComponentDetailsId() {
		return componentDetailsId;
	}
	public void setComponentDetailsId(String componentDetailsId) {
		this.componentDetailsId = componentDetailsId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public String getParentComponent() {
		return parentComponent;
	}
	public void setParentComponent(String parentComponent) {
		this.parentComponent = parentComponent;
	}
	@Override
	public String toString() {
		return "ComponentForm [componentName=" + componentName + ", componentType=" + componentType
				+ ", parentComponent=" + parentComponent + "]";
	}
	
}
