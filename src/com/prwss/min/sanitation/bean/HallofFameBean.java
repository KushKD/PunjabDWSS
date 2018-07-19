package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "san_hall_of_fame", schema = MISConstants.MIS_DB_SCHEMA_NAME)

//san_hall_of_fame_seq
public class HallofFameBean implements Serializable {
	
	
	private static final long serialVersionUID = 565898371593691926L;
	
	
	@Id
	@GeneratedValue(generator = "san_hall_of_fame_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "san_hall_of_fame_seq", sequenceName = "prwss_main.san_hall_of_fame_seq")

	
	@Column(name = "activity_id")
	private Integer activityId;
	
	
	@Column(name = "activity_name")
	private String nameofActivity;
	
	@Column(name = "activity_type")
	private Integer type;
	
	@Column(name = "lead_by")
	private String leadBy;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="attachment_name")
	private String attachmentName;
	
	@Column(name="attachment")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] attachment;
	
	@Column(name = "crt_date")
	private Date crt_date;
	
	@Column(name = "crt_by_usr")
	private Integer createdByUser;
	
	@Column(name = "lst_updated_date")
	private Date lst_updated_date;
	
	@Column(name = "lst_updated_user")
	private Integer lst_updated_user;
	
	
	/*@Column(name = "loc_id")
	private Integer locationId;*/
	
	@Column(name = "active_flag")
	private Integer status;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getNameofActivity() {
		return nameofActivity;
	}

	public void setNameofActivity(String nameofActivity) {
		this.nameofActivity = nameofActivity;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
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

	public Date getCrt_date() {
		return crt_date;
	}

	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Date getLst_updated_date() {
		return lst_updated_date;
	}

	public void setLst_updated_date(Date lst_updated_date) {
		this.lst_updated_date = lst_updated_date;
	}

	public Integer getLst_updated_user() {
		return lst_updated_user;
	}

	public void setLst_updated_user(Integer lst_updated_user) {
		this.lst_updated_user = lst_updated_user;
	}

	/*public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}*/

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

	

	
		
	
}
