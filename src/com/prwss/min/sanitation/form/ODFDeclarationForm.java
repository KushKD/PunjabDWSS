/**
 * 
 */
package com.prwss.min.sanitation.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class ODFDeclarationForm  extends ValidatorForm{

	private static final long serialVersionUID = -8813761202058L;
	
	private String district;
	private String village;
	private String block;
	private String gramPanchayat;
	private FormFile declaration;
	private String chairedBy;
	private String meetingPlace;
	private String dateOfMeeting;
	
	private Long createdBy;
	
	
	
	
	public FormFile getDeclaration() {
		return declaration;
	}
	public void setDeclaration(FormFile declaration) {
		this.declaration = declaration;
	}
	public String getChairedBy() {
		return chairedBy;
	}
	public void setChairedBy(String chairedBy) {
		this.chairedBy = chairedBy;
	}
	public String getMeetingPlace() {
		return meetingPlace;
	}
	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}
	public String getDateOfMeeting() {
		return dateOfMeeting;
	}
	public void setDateOfMeeting(String dateOfMeeting) {
		this.dateOfMeeting = dateOfMeeting;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getGramPanchayat() {
		return gramPanchayat;
	}
	public void setGramPanchayat(String gramPanchayat) {
		this.gramPanchayat = gramPanchayat;
	}
	
}
