/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */

@Entity
@Table(name="cc_monthly_plan",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MonthlyPlanInspectionBean implements Serializable {

	private static final long serialVersionUID=-12364432L;
	
	@Id
	@GeneratedValue(generator="monthly_plan_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="monthly_plan_id_seq",sequenceName="prwss_main.monthly_plan_id_seq")
	@Column(name="monthly_plan_id")
	private Integer monthlyPlanId;
	
	
	@OneToMany(targetEntity=MonthlyPlanSchemeMappingBean.class,fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="monthly_plan_id", updatable = false, insertable = false)
	Set<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans;
	
	@OneToOne(targetEntity=TeamMasterBean.class)
	@JoinColumn(name="team",referencedColumnName="team_id",insertable=false,updatable=false)
	private TeamMasterBean  teamMasterBean;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="component",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailComponent;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="month_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailMonth;
	
	@OneToOne(targetEntity=YearlyPlanningComponentMappingBean.class)
	@JoinColumn(name="total_number_village",referencedColumnName="plan_component_id",insertable=false,updatable=false)
	private YearlyPlanningComponentMappingBean  yearlyPlanningComponentMappingBean;
	
	@OneToOne(targetEntity=YearlyPlanInspectionBean.class)
	@JoinColumn(name="yearly_plan_id",referencedColumnName="yearly_plan_id",insertable=false,updatable=false)
	private YearlyPlanInspectionBean  yearlyPlanInspectionBean;
	
	@Column(name="yearly_plan_id")
	private Integer yearlyPlanId;
	
	@Column(name="component")
	private Integer component;
	
	@Column(name="month_id")
	private Integer monthId;
	
	@Column(name="total_number_village")
	private Long totalNumberVillage;
	
	@Column(name="villages_to_be_visited_month")
	private Integer villageToBeVisited;
	
	@Column(name="team")
	private Integer team;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;

	
	
	public ComboBeanDetails getCombodetailMonth() {
		return combodetailMonth;
	}

	public void setCombodetailMonth(ComboBeanDetails combodetailMonth) {
		this.combodetailMonth = combodetailMonth;
	}

	public YearlyPlanInspectionBean getYearlyPlanInspectionBean() {
		return yearlyPlanInspectionBean;
	}

	public void setYearlyPlanInspectionBean(YearlyPlanInspectionBean yearlyPlanInspectionBean) {
		this.yearlyPlanInspectionBean = yearlyPlanInspectionBean;
	}

	public TeamMasterBean getTeamMasterBean() {
		return teamMasterBean;
	}

	public void setTeamMasterBean(TeamMasterBean teamMasterBean) {
		this.teamMasterBean = teamMasterBean;
	}

	public YearlyPlanningComponentMappingBean getYearlyPlanningComponentMappingBean() {
		return yearlyPlanningComponentMappingBean;
	}

	public void setYearlyPlanningComponentMappingBean(YearlyPlanningComponentMappingBean yearlyPlanningComponentMappingBean) {
		this.yearlyPlanningComponentMappingBean = yearlyPlanningComponentMappingBean;
	}

	public ComboBeanDetails getCombodetailComponent() {
		return combodetailComponent;
	}

	public void setCombodetailComponent(ComboBeanDetails combodetailComponent) {
		this.combodetailComponent = combodetailComponent;
	}


	public Set<MonthlyPlanSchemeMappingBean> getMonthlyPlanSchemeMappingBeans() {
		return monthlyPlanSchemeMappingBeans;
	}

	public void setMonthlyPlanSchemeMappingBeans(Set<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans) {
		this.monthlyPlanSchemeMappingBeans = monthlyPlanSchemeMappingBeans;
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

	public Integer getMonthlyPlanId() {
		return monthlyPlanId;
	}

	public void setMonthlyPlanId(Integer monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
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

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public Long getTotalNumberVillage() {
		return totalNumberVillage;
	}

	public void setTotalNumberVillage(Long totalNumberVillage) {
		this.totalNumberVillage = totalNumberVillage;
	}

	public Integer getVillageToBeVisited() {
		return villageToBeVisited;
	}

	public void setVillageToBeVisited(Integer villageToBeVisited) {
		this.villageToBeVisited = villageToBeVisited;
	}

	public Integer getTeam() {
		return team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "MonthlyPlanInspectionBean [monthlyPlanId=" + monthlyPlanId + ", monthlyPlanSchemeMappingBeans="
				+ monthlyPlanSchemeMappingBeans + ", yearlyPlanId=" + yearlyPlanId + ", component=" + component
				+ ", monthId=" + monthId + ", totalNumberVillage=" + totalNumberVillage + ", villageToBeVisited="
				+ villageToBeVisited + ", team=" + team + ", activeFlag=" + activeFlag + ", crtByUsr=" + crtByUsr + "]";
	}
	
}
