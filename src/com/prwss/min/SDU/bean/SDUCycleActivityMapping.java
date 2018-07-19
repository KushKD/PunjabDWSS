package com.prwss.min.SDU.bean;

import java.io.Serializable;

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

import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_cycle_activity_mpg",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SDUCycleActivityMapping implements Serializable{
	
	private static final long serialVersionUID = 6926724754485338475L;
	
	@Id
	@SequenceGenerator(name="sdu_cycle_activity_mpg_cycle_activity_id_seq",sequenceName="prwss_main.sdu_cycle_activity_mpg_cycle_activity_id_seq")
	@GeneratedValue(generator="sdu_cycle_activity_mpg_cycle_activity_id_seq",strategy=GenerationType.AUTO)
	@Column(name="cycle_activity_id")
	private Integer cycleActivityId;
	
	@OneToOne(targetEntity=SDUSchemeCycleMasterBean.class)
	@JoinColumn(name="scheme_cycle_id",referencedColumnName="sch_cylce_id",insertable=false,updatable=false)
	private SDUSchemeCycleMasterBean  sduSchemeCycleMasterBean;
	
	@OneToOne(targetEntity = ActivityVillageMappingDetalBean.class)
	@JoinColumn(name = "activity_id", referencedColumnName = "activity_id", insertable = false, updatable = false)
	private ActivityVillageMappingDetalBean activityVillageMappingDetalBean;
	
	@Column(name="scheme_cycle_id")
	private Integer schemeCycleId;
	
	@Column(name="activity_id")
	private Integer activityId;
	
	@Column(name="act_compaign_id")
	private Integer actCompaignId;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_usr")
	private Integer createdByUser;

	
	public Integer getCycleActivityId() {
		return cycleActivityId;
	}

	public void setCycleActivityId(Integer cycleActivityId) {
		this.cycleActivityId = cycleActivityId;
	}

	public Integer getSchemeCycleId() {
		return schemeCycleId;
	}

	public void setSchemeCycleId(Integer schemeCycleId) {
		this.schemeCycleId = schemeCycleId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getActCompaignId() {
		return actCompaignId;
	}

	public void setActCompaignId(Integer actCompaignId) {
		this.actCompaignId = actCompaignId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}


	public SDUSchemeCycleMasterBean getSduSchemeCycleMasterBean() {
		return sduSchemeCycleMasterBean;
	}

	public void setSduSchemeCycleMasterBean(SDUSchemeCycleMasterBean sduSchemeCycleMasterBean) {
		this.sduSchemeCycleMasterBean = sduSchemeCycleMasterBean;
	}

	public ActivityVillageMappingDetalBean getActivityVillageMappingDetalBean() {
		return activityVillageMappingDetalBean;
	}

	public void setActivityVillageMappingDetalBean(
			ActivityVillageMappingDetalBean activityVillageMappingDetalBean) {
		this.activityVillageMappingDetalBean = activityVillageMappingDetalBean;
	}

	@Override
	public String toString() {
		return "SDUCycleActivityMapping [cycleActivityId=" + cycleActivityId + ", getActivityId=" + sduSchemeCycleMasterBean
				+ ", schemeCycleId=" + schemeCycleId + ", activityId=" + activityId + ", actCompaignId=" + actCompaignId
				+ ", activeFlag=" + activeFlag + ", createdByUser=" + createdByUser + "]";
	}

}
