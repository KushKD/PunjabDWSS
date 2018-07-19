package com.prwss.min.SDU.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_village_activity_dtl",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ActivityVillageMappingDetalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8547007316039715592L;
	
	@Id
	@SequenceGenerator(name="sdu_village_activity_dtl_village_activity_dtl_id_seq",sequenceName="prwss_main.sdu_village_activity_dtl_village_activity_dtl_id_seq")
	@GeneratedValue(generator="sdu_village_activity_dtl_village_activity_dtl_id_seq",strategy=GenerationType.AUTO)
	@Column(name="Village_activity_dtl_id")
	private Integer activityVillageDetailId;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToOne(targetEntity=ActivityVillageMappingBean.class)
	@JoinColumn(name="Village_activity_id",referencedColumnName="village_activity_id",insertable=false,updatable=false)
	private ActivityVillageMappingBean  activityVillageMappingBean;
	
	
	/*@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(targetEntity=SchemeCycleAttributeDetailBean.class)
	@JoinColumn(name="activity_id",referencedColumnName="sch_attribute_dtl_id",insertable=false,updatable=false)
	private SchemeCycleAttributeDetailBean  schemeCycleAttributeDetailBean;
	*/
	@OneToOne(targetEntity = SchemeCycleAttributeDetailBean.class)
	@JoinColumn(name = "activity_id", referencedColumnName = "sch_attribute_id", insertable = false, updatable = false)
	private SchemeCycleAttributeDetailBean activityAttributeName;
	
	
	
	
	
	
	
	@Column(name="Village_activity_id")
	private Integer ActivityVillageId;

	
	@Column(name="activity_id")
	private Integer ActivityId;
	
	
	
	@Column(name="expt_start_date")
	private Date startDate;
	
	@Column(name="expt_end_date")
	private Date endDate;
	
	

	/*@Column(name="active_flag")
	private Integer activeFlag;*/

	/*@Column (name="crt_date")
	private Date createdDate;*/

	@Column(name="crt_by_usr")
	private Integer createdByUser;
	
	@Column(name="actual_start_date")
	private Date actualStartDate;
	
	@Column(name="actual_end_date")
	private Date actualEndDate;
	
	@Column(name="outcome_achieved")
	private Integer outComeAchieved;
	
	@Column(name="comments")
	private String comments;

	

	
	
	public SchemeCycleAttributeDetailBean getActivityAttributeName() {
		return activityAttributeName;
	}

	public void setActivityAttributeName(
			SchemeCycleAttributeDetailBean activityAttributeName) {
		this.activityAttributeName = activityAttributeName;
	}

	public Integer getActivityVillageDetailId() {
		return activityVillageDetailId;
	}

	public void setActivityVillageDetailId(Integer activityVillageDetailId) {
		this.activityVillageDetailId = activityVillageDetailId;
	}

	public Integer getActivityVillageId() {
		return ActivityVillageId;
	}

	public void setActivityVillageId(Integer activityVillageId) {
		ActivityVillageId = activityVillageId;
	}

	public Integer getActivityId() {
		return ActivityId;
	}

	public void setActivityId(Integer activityId) {
		ActivityId = activityId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/*public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}*/

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	public ActivityVillageMappingBean getActivityVillageMappingBean() {
		return activityVillageMappingBean;
	}

	public void setActivityVillageMappingBean(ActivityVillageMappingBean activityVillageMappingBean) {
		this.activityVillageMappingBean = activityVillageMappingBean;
	}
/*
	public SchemeCycleAttributeDetailBean getSchemeCycleAttributeDetailBean() {
		return schemeCycleAttributeDetailBean;
	}

	public void setSchemeCycleAttributeDetailBean(SchemeCycleAttributeDetailBean schemeCycleAttributeDetailBean) {
		this.schemeCycleAttributeDetailBean = schemeCycleAttributeDetailBean;
	}*/

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Integer getOutComeAchieved() {
		return outComeAchieved;
	}

	public void setOutComeAchieved(Integer outComeAchieved) {
		this.outComeAchieved = outComeAchieved;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "ActivityVillageMappingDetalBean [activityVillageDetailId="
				+ activityVillageDetailId + ", activityVillageMappingBean="
				+ activityVillageMappingBean + ", activityAttributeName="
				+ activityAttributeName + ", ActivityVillageId="
				+ ActivityVillageId + ", ActivityId=" + ActivityId
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", createdByUser=" + createdByUser + ", actualStartDate="
				+ actualStartDate + ", actualEndDate=" + actualEndDate
				+ ", outComeAchieved=" + outComeAchieved + ", comments="
				+ comments + "]";
	}

	/*@Override
	public String toString() {
		return "ActivityVillageMappingDetalBean [ActivityVillageDetailId=" + activityVillageDetailId
				+ ", activityVillageMappingBean=" + activityVillageMappingBean + ", schemeCycleAttributeDetailBean="
				+ schemeCycleAttributeDetailBean + ", ActivityVillageId=" + ActivityVillageId + ", ActivityId="
				+ ActivityId + ", startDate=" + startDate + ", endDate=" + endDate + ", createdByUser=" + createdByUser
				+ ", actualStartDate=" + actualStartDate + ", actualEndDate=" + actualEndDate + ", outComeAchieved="
				+ outComeAchieved + ", comments=" + comments + "]";
	}*/
	
	
	
}
