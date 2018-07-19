/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.mis.admin.dao.LocationConstituencyDetails;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Table(name="cc_monthly_plan_scheme_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MonthlyPlanSchemeMappingBean implements Serializable{
	
	private static final long serialVersionUID=-128765433333L;
	
	@Id
	@GeneratedValue(generator="cc_monthly_plan_scheme_mapping_monthly_plan_scheme_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="cc_monthly_plan_scheme_mapping_monthly_plan_scheme_id_seq",sequenceName="prwss_main.cc_monthly_plan_scheme_mapping_monthly_plan_scheme_id_seq")
	@Column(name="monthly_plan_scheme_id")
	private Long monthly_plan_scheme_id;
	
	@OneToOne(targetEntity=PMSSchemeDetailsBean.class)
	@JoinColumn(name="scheme_id",referencedColumnName="scheme_id",insertable=false,updatable=false)
	private PMSSchemeDetailsBean  pmsSchemeDetailsBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="district", referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean districtDetailBean;
	
	@OneToOne(targetEntity=LocationDivisionSubDivisonDetailsBean.class)
	@JoinColumn(name="division", referencedColumnName="div_subdiv_id",insertable=false,updatable=false)
	private LocationDivisionSubDivisonDetailsBean locationDivisonDetailsBean;
	
	@OneToOne(targetEntity=LocationConstituencyDetails.class)
	@JoinColumn(name="constituency", referencedColumnName="constituency_id",insertable=false,updatable=false)
	private LocationConstituencyDetails locationConstituencyDetailsBean;
	


	@Column(name="monthly_plan_id")
	private Integer monthly_plan_id;
	
	@Column(name="scheme_id")
	private Long scheme_id;
	
	@Column(name="district")
	private Long district;
	
	@Column(name="division")
	private Long division;
	
	@Column(name="constituency")
	private Long constituency;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;
	
	

	public PMSSchemeDetailsBean getPmsSchemeDetailsBean() {
		return pmsSchemeDetailsBean;
	}

	public void setPmsSchemeDetailsBean(PMSSchemeDetailsBean pmsSchemeDetailsBean) {
		this.pmsSchemeDetailsBean = pmsSchemeDetailsBean;
	}

	public LocationDetailsBean getDistrictDetailBean() {
		return districtDetailBean;
	}

	public void setDistrictDetailBean(LocationDetailsBean districtDetailBean) {
		this.districtDetailBean = districtDetailBean;
	}

	public LocationDivisionSubDivisonDetailsBean getLocationDivisonDetailsBean() {
		return locationDivisonDetailsBean;
	}

	public void setLocationDivisonDetailsBean(LocationDivisionSubDivisonDetailsBean locationDivisonDetailsBean) {
		this.locationDivisonDetailsBean = locationDivisonDetailsBean;
	}

	

	public LocationConstituencyDetails getLocationConstituencyDetailsBean() {
		return locationConstituencyDetailsBean;
	}

	public void setLocationConstituencyDetailsBean(LocationConstituencyDetails locationConstituencyDetailsBean) {
		this.locationConstituencyDetailsBean = locationConstituencyDetailsBean;
	}

	public Long getMonthly_plan_scheme_id() {
		return monthly_plan_scheme_id;
	}

	public void setMonthly_plan_scheme_id(Long monthly_plan_scheme_id) {
		this.monthly_plan_scheme_id = monthly_plan_scheme_id;
	}

	public Integer getMonthly_plan_id() {
		return monthly_plan_id;
	}

	public void setMonthly_plan_id(Integer monthly_plan_id) {
		this.monthly_plan_id = monthly_plan_id;
	}

	public Long getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(Long scheme_id) {
		this.scheme_id = scheme_id;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getDivision() {
		return division;
	}

	public void setDivision(Long division) {
		this.division = division;
	}

	public Long getConstituency() {
		return constituency;
	}

	public void setConstituency(Long constituency) {
		this.constituency = constituency;
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
		return "MonthlyPlanSchemeMappingBean [monthly_plan_scheme_id=" + monthly_plan_scheme_id + ", monthly_plan_id="
				+ monthly_plan_id + ", scheme_id=" + scheme_id + ", district=" + district + ", division=" + division
				+ ", constituency=" + constituency + ", activeFlag=" + activeFlag + ", crtByUsr="
				+ crtByUsr + "]";
	}

}
