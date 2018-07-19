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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "sdu_activity_outcome_mpg", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class OutcomeMappingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4169285390086105700L;

	@Id
	@SequenceGenerator(name = "sdu_activity_outcome_mpg_activity_outcome_id_seq", sequenceName = "prwss_main.sdu_activity_outcome_mpg_activity_outcome_id_seq")
	@GeneratedValue(generator = "sdu_activity_outcome_mpg_activity_outcome_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "activity_outcome_id")
	private Integer activityOutcomeId;

	@OneToOne(targetEntity = SDUCycleActivityMapping.class)
	@JoinColumn(name = "cycle_activity_id", referencedColumnName = "cycle_activity_id", insertable = false, updatable = false)
	private SDUCycleActivityMapping sduCycleActivityMapping;
	
	@OneToOne(targetEntity = SchemeCycleAttributeDetailBean.class)
	@JoinColumn(name = "outcome_id", referencedColumnName = "sch_attribute_id", insertable = false, updatable = false)
	private SchemeCycleAttributeDetailBean sduAttributeDetailBean;
	

	@Column(name = "cycle_activity_id")
	private Integer cycle_ActivityID;

	@Column(name = "outcome_id")
	private Integer outcomeId;

	@Column(name = "active_flag")
	private Integer activeFlag;
	
	
	
	

	

	public Integer getActivityOutcomeId() {
		return activityOutcomeId;
	}

	public void setActivityOutcomeId(Integer activityOutcomeId) {
		this.activityOutcomeId = activityOutcomeId;
	}

	public Integer getCycle_ActivityID() {
		return cycle_ActivityID;
	}

	public void setCycle_ActivityID(Integer cycle_ActivityID) {
		this.cycle_ActivityID = cycle_ActivityID;
	}

	public Integer getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public SDUCycleActivityMapping getSduCycleActivityMapping() {
		return sduCycleActivityMapping;
	}

	public void setSduCycleActivityMapping(
			SDUCycleActivityMapping sduCycleActivityMapping) {
		this.sduCycleActivityMapping = sduCycleActivityMapping;
	}

	public SchemeCycleAttributeDetailBean getSduAttributeDetailBean() {
		return sduAttributeDetailBean;
	}

	public void setSduAttributeDetailBean(
			SchemeCycleAttributeDetailBean sduAttributeDetailBean) {
		this.sduAttributeDetailBean = sduAttributeDetailBean;
	}

	@Override
	public String toString() {
		return "OutcomeMappingBean [activityOutcomeId=" + activityOutcomeId
				+ ", cycle_ActivityID=" + cycle_ActivityID + ", outcomeId="
				+ outcomeId + ", activeFlag=" + activeFlag + "]";
	}

}
