/**
 * 
 */
package com.prwss.min.sanitation.bean;

/**
 * @author BH390738
 *
 */
public class HallofFameDetailsDto {
	private int activityId;
	private String nameofActivity;
	private int type;
	private String leadBy;
	private String description;
	private String attachmentName;
	private byte[] attachment;
	
	
	
	
	
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getNameofActivity() {
		return nameofActivity;
	}
	public void setNameofActivity(String nameofActivity) {
		this.nameofActivity = nameofActivity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
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
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	
	
	

	
}
