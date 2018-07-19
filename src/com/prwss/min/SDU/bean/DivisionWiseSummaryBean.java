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

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_div_finyearly_plan_outline",schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class DivisionWiseSummaryBean implements Serializable{

private static final long serialVersionUID = 3635012389326519815L;
		
@Id
@GeneratedValue(generator="sdu_div_finyearly_plan_outline_outline_plan_id_seq",strategy=GenerationType.AUTO)
@SequenceGenerator(name="sdu_div_finyearly_plan_outline_outline_plan_id_seq",sequenceName="prwss_main.sdu_div_finyearly_plan_outline_outline_plan_id_seq")
@Column(name="outline_plan_id")
private Integer outlinePlanId;


@OneToOne(targetEntity=ComboBeanDetails.class)
@JoinColumn(name="category_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
private ComboBeanDetails  combodetailCatName;




@Column(name="division_id")
private Integer division;

@Column(name="financial_year")
private Integer financialYear;

@Column(name="category_id")
private Integer category;

@Column(name="village_no")
private Integer noOfVillages;

@Column(name="active_flag")
private Integer activeFlag;

@Column(name="crt_by_usr")
private Integer createdByUser;






public Integer getOutlinePlanId() {
	return outlinePlanId;
}

public void setOutlinePlanId(Integer outlinePlanId) {
	this.outlinePlanId = outlinePlanId;
}

public Integer getDivision() {
	return division;
}

public void setDivision(Integer division) {
	this.division = division;
}

public Integer getFinancialYear() {
	return financialYear;
}

public void setFinancialYear(Integer financialYear) {
	this.financialYear = financialYear;
}

public Integer getCategory() {
	return category;
}

public void setCategory(Integer category) {
	this.category = category;
}

public Integer getNoOfVillages() {
	return noOfVillages;
}

public void setNoOfVillages(Integer noOfVillages) {
	this.noOfVillages = noOfVillages;
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

public ComboBeanDetails getCombodetailCatName() {
	return combodetailCatName;
}

public void setCombodetailCatName(ComboBeanDetails combodetailCatName) {
	this.combodetailCatName = combodetailCatName;
}

@Override
public String toString() {
	return "DivisionWiseSummaryBean [outlinePlanId=" + outlinePlanId + ", combodetailCatName=" + combodetailCatName
			+ ", division=" + division + ", financialYear=" + financialYear + ", category=" + category
			+ ", noOfVillages=" + noOfVillages + ", activeFlag=" + activeFlag + ", createdByUser=" + createdByUser
			+ "]";
}

}
