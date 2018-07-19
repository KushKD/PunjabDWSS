package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.common.util.MISConstants;


@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="cc_plan_component_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class YearlyPlanningComponentMappingBean implements Serializable {

	private static final long serialVersionUID = 36350123899815L;
	
	@Id
	@GeneratedValue(generator="plan_component_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="plan_component_id_seq",sequenceName="prwss_main.plan_component_id_seq")
	@Column(name="plan_component_id")
	private Integer planComponentId;
	
	@Column(name="yearly_plan_id")
	private Integer yearlyPlanId;
	
	@Column(name="component")
	private Integer component;
	
	@Column(name="total_number_village")
	private Long totalNumberVillage;
	
	@Column(name="period_of_implementaion")
	private Long periodOfImplementaion;
	
	@Column(name="villages_to_be_visited")
	private Long villageToBeVisited;
	
	@Column(name="visit_per_village")
	private Long visitPerVillage;
	
	@Column(name="total_visits")
	private Long totalVisit;
	
	@Column(name="total_duration")
	private Long totalDuration;
	
	@Column(name="villages_visited_per_month")
	private Long visitedPerMonth;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;

	
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getPlanComponentId() {
		return planComponentId;
	}

	public void setPlanComponentId(Integer planComponentId) {
		this.planComponentId = planComponentId;
	}

	public Integer getYearlyPlanId() {
		return yearlyPlanId;
	}

	public void setYearlyPlanId(Integer yearlyPlanId) {
		this.yearlyPlanId = yearlyPlanId;
	}

	public Integer getComponent() {
		return component;
	}

	public void setComponent(Integer component) {
		this.component = component;
	}

	public Long getTotalNumberVillage() {
		return totalNumberVillage;
	}

	public void setTotalNumberVillage(Long totalNumberVillage) {
		this.totalNumberVillage = totalNumberVillage;
	}

	public Long getPeriodOfImplementaion() {
		return periodOfImplementaion;
	}

	public void setPeriodOfImplementaion(Long periodOfImplementaion) {
		this.periodOfImplementaion = periodOfImplementaion;
	}

	public Long getVillageToBeVisited() {
		return villageToBeVisited;
	}

	public void setVillageToBeVisited(Long villageToBeVisited) {
		this.villageToBeVisited = villageToBeVisited;
	}

	public Long getVisitPerVillage() {
		return visitPerVillage;
	}

	public void setVisitPerVillage(Long visitPerVillage) {
		this.visitPerVillage = visitPerVillage;
	}

	public Long getTotalVisit() {
		return totalVisit;
	}

	public void setTotalVisit(Long totalVisit) {
		this.totalVisit = totalVisit;
	}

	public Long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Long getVisitedPerMonth() {
		return visitedPerMonth;
	}

	public void setVisitedPerMonth(Long visitedPerMonth) {
		this.visitedPerMonth = visitedPerMonth;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	@Override
	public String toString() {
		return "YearlyPlanningComponentMappingBean [planComponentId=" + planComponentId + ", yearlyPlanId="
				+ yearlyPlanId + ", component=" + component + ", totalNumberVillage=" + totalNumberVillage
				+ ", periodOfImplementaion=" + periodOfImplementaion + ", villageToBeVisited=" + villageToBeVisited
				+ ", visitPerVillage=" + visitPerVillage + ", totalVisit=" + totalVisit + ", totalDuration="
				+ totalDuration + ", visitedPerMonth=" + visitedPerMonth + ", activeFlag=" + activeFlag + ", crtByUsr="
				+ crtByUsr + "]";
	}
	
	
}
