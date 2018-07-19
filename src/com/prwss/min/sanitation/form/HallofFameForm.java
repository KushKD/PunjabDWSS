package com.prwss.min.sanitation.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class HallofFameForm extends ValidatorForm{
	private static final long serialVersionUID = -8813762147258302058L;
	
	private String nameofActivity;
	private String type;
	private String leadBy;
	private String description;
	private FormFile attachment;
	private String activityId;
	private Long createdByUser;
	private String locationId;
	private String status;
	
	
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public Long getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNameofActivity() {
		return nameofActivity;
	}
	public void setNameofActivity(String nameofActivity) {
		this.nameofActivity = nameofActivity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLeadBy() {
		return leadBy;
	}
	public void setLeadBy(String leadBy) {
		this.leadBy = leadBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FormFile getAttachment() {
		return attachment;
	}
	public void setAttachment(FormFile attachment) {
		this.attachment = attachment;
	}
	
	
}