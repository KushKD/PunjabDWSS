package com.prwss.min.bean;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "wq_test_result", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ResultEntryBeans implements Serializable {

	private static final long serialVersionUID = 1323234325344L;

	@Id
	@GeneratedValue(generator = "wq_test_result_test_result_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "wq_test_result_test_result_id_seq", sequenceName = "prwss_main.wq_test_result_test_result_id_seq")
	@Column(name = "test_result_id")
	private int testResultId;

	@OneToMany(targetEntity = ResultEntryDetailBean.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name = "test_result_id", updatable = false, insertable = false)
	private Set<ResultEntryDetailBean> locDetailBeans;


	@Column(name = "sample_part_no")
	private String samplePartNo;

	@Column(name = "sample_id")
	private Integer sampleId;

	@Column(name = "lab_id")
	private Integer labId;

	@Column(name = "test_done_by")
	private Integer testDoneBy;

	@Column(name = "test_completed_date")
	private Date testCompletedDate;

	@Column(name = "lying_with_user")
	private Integer lyingWithUser;

	@Column(name = "requestlevel")
	private Integer requestLevel;

	@Column(name = "test_status")
	private Integer testStatus;

	@Column(name = "crt_by_user")
	private Integer crtByUser;

	public int getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}

	public Set<ResultEntryDetailBean> getLocDetailBeans() {
		return locDetailBeans;
	}

	public void setLocDetailBeans(Set<ResultEntryDetailBean> locDetailBeans) {
		this.locDetailBeans = locDetailBeans;
	}

	public String getSamplePartNo() {
		return samplePartNo;
	}

	public void setSamplePartNo(String samplePartNo) {
		this.samplePartNo = samplePartNo;
	}

	public Integer getSampleId() {
		return sampleId;
	}

	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	public Integer getTestDoneBy() {
		return testDoneBy;
	}

	public void setTestDoneBy(Integer testDoneBy) {
		this.testDoneBy = testDoneBy;
	}

	public Date getTestCompletedDate() {
		return testCompletedDate;
	}

	public void setTestCompletedDate(Date testCompletedDate) {
		this.testCompletedDate = testCompletedDate;
	}

	public Integer getLyingWithUser() {
		return lyingWithUser;
	}

	public void setLyingWithUser(Integer lyingWithUser) {
		this.lyingWithUser = lyingWithUser;
	}

	public Integer getRequestLevel() {
		return requestLevel;
	}

	public void setRequestLevel(Integer requestLevel) {
		this.requestLevel = requestLevel;
	}

	public Integer getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(Integer testStatus) {
		this.testStatus = testStatus;
	}

	public Integer getCrtByUser() {
		return crtByUser;
	}

	public void setCrtByUser(Integer crtByUser) {
		this.crtByUser = crtByUser;
	}

	@Override
	public String toString() {
		return "ResultEntryBeans [testResultId=" + testResultId + ", locDetailBeans=" + locDetailBeans
				+ ", samplePartNo=" + samplePartNo + ", sampleId=" + sampleId + ", labId=" + labId + ", testDoneBy="
				+ testDoneBy + ", testCompletedDate=" + testCompletedDate + ", lyingWithUser=" + lyingWithUser
				+ ", requestLevel=" + requestLevel + ", testStatus=" + testStatus + ", crtByUser=" + crtByUser + "]";
	}

}
