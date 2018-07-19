package com.prwss.min.bean;

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

import com.prwss.mis.common.util.MISConstants;


@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_test_result_dtl", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ResultEntryDetailBean implements Serializable {

	private static final long serialVersionUID = 13243225344L;
	
	@Id
	@GeneratedValue(generator = "wq_test_result_detail_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "wq_test_result_detail_seq", sequenceName = "prwss_main.wq_test_result_detail_seq")
	@Column(name = "test_result_detail_id")
	private int testDetailId;
	
	
	@OneToOne(targetEntity = ParameterMasterBean.class)
	@JoinColumn(name = "param_id", referencedColumnName = "parameter_id", nullable = false, insertable = false, updatable = false)
	private ParameterMasterBean parameterMasterBean;

	@Column(name="test_date")
	private Date testDate;
	
	@Column(name="test_result_id")
	private Integer testResultId;
	
	@Column(name="param_id")
	private Integer paramId;
	
	@Column(name="actual_value")
	private String actualValue;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUser;

	
	public ParameterMasterBean getParameterMasterBean() {
		return parameterMasterBean;
	}

	public void setParameterMasterBean(ParameterMasterBean parameterMasterBean) {
		this.parameterMasterBean = parameterMasterBean;
	}

	public int getTestDetailId() {
		return testDetailId;
	}

	public void setTestDetailId(int testDetailId) {
		this.testDetailId = testDetailId;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Integer getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}

	public Integer getParamId() {
		return paramId;
	}

	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCrtByUser() {
		return crtByUser;
	}

	public void setCrtByUser(Integer crtByUser) {
		this.crtByUser = crtByUser;
	}

	@Override
	public String toString() {
		return "ResultEntryDetailBean [testDetailId=" + testDetailId + ", testDate=" + testDate + ", testResultId="
				+ testResultId + ", paramId=" + paramId + ", actualValue=" + actualValue + ", activeFlag=" + activeFlag
				+ ", crtByUser=" + crtByUser + "]";
	}

}
