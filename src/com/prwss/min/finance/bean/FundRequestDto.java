/**
 * 
 */
package com.prwss.min.finance.bean;

import java.sql.Date;

/**
 * @author BH390738
 *
 */
public class FundRequestDto {

	private String addmin_app_no;
	private Date addmin_app_date;
	private Long admin_app_amount;
	private String tech_app_no;
	private Date tech_app_date;
	private Long tech_app_amount;
	private String wb_app_no;
	private Date wb_app_date;
	private String schemeNo;
	
	private String component;
	private String schemeName;
	private String divisionName;
	private Long fundRequestId;
	private String request_no;
	private String ddoNumber;
	private Double value_of_inst;
	private String installmentName;
	
	

	public Double getValue_of_inst() {
		return value_of_inst;
	}

	public void setValue_of_inst(Double value_of_inst) {
		this.value_of_inst = value_of_inst;
	}

	public String getInstallmentName() {
		return installmentName;
	}

	public void setInstallmentName(String installmentName) {
		this.installmentName = installmentName;
	}

	public String getDdoNumber() {
		return ddoNumber;
	}

	public void setDdoNumber(String ddoNumber) {
		this.ddoNumber = ddoNumber;
	}

	public Long getFundRequestId() {
		return fundRequestId;
	}

	public void setFundRequestId(Long fundRequestId) {
		this.fundRequestId = fundRequestId;
	}

	public String getRequest_no() {
		return request_no;
	}

	public void setRequest_no(String request_no) {
		this.request_no = request_no;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getAddmin_app_no() {
		return addmin_app_no;
	}

	public void setAddmin_app_no(String addmin_app_no) {
		this.addmin_app_no = addmin_app_no;
	}

	public Date getAddmin_app_date() {
		return addmin_app_date;
	}

	public void setAddmin_app_date(Date addmin_app_date) {
		this.addmin_app_date = addmin_app_date;
	}

	public Long getAdmin_app_amount() {
		return admin_app_amount;
	}

	public void setAdmin_app_amount(Long admin_app_amount) {
		this.admin_app_amount = admin_app_amount;
	}

	public String getTech_app_no() {
		return tech_app_no;
	}

	public void setTech_app_no(String tech_app_no) {
		this.tech_app_no = tech_app_no;
	}

	public Date getTech_app_date() {
		return tech_app_date;
	}

	public void setTech_app_date(Date tech_app_date) {
		this.tech_app_date = tech_app_date;
	}

	public Long getTech_app_amount() {
		return tech_app_amount;
	}

	public void setTech_app_amount(Long tech_app_amount) {
		this.tech_app_amount = tech_app_amount;
	}

	public String getWb_app_no() {
		return wb_app_no;
	}

	public void setWb_app_no(String wb_app_no) {
		this.wb_app_no = wb_app_no;
	}

	public Date getWb_app_date() {
		return wb_app_date;
	}

	public void setWb_app_date(Date wb_app_date) {
		this.wb_app_date = wb_app_date;
	}

	public String getSchemeNo() {
		return schemeNo;
	}

	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}

	@Override
	public String toString() {
		return "FundRequestDto [addmin_app_no=" + addmin_app_no + ", addmin_app_date=" + addmin_app_date
				+ ", admin_app_amount=" + admin_app_amount + ", tech_app_no=" + tech_app_no + ", tech_app_date="
				+ tech_app_date + ", tech_app_amount=" + tech_app_amount + ", wb_app_no=" + wb_app_no + ", wb_app_date="
				+ wb_app_date + ", schemeNo=" + schemeNo + "]";
	}
	
}
