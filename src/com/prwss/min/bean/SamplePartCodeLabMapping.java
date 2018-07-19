/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_lab_samplesubcode_mpg",schema="prwss_main")
public class SamplePartCodeLabMapping implements Serializable {

	private static final long serialVersionUID = -13423445344L;
	
	@Id
	@Column(name = "test_lab_samplesubcode_id")
	private long testLabSampleSubCodeId;
	
	
	@OneToOne(targetEntity=LabMasterBean.class)
	@JoinColumn(name="lab_id", referencedColumnName="lab_id",insertable=false,updatable=false)
	private LabMasterBean labMaster;
	
	@Column(name = "lab_id")
	private Integer labId;
	
	@Column(name = "category_type")
	private String category_type;
	
	@Column(name = "category_type1")
	private String category_type1;
	
	@Column(name = "current_sequence")
	private Integer currentSequence;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	
	
	public String getCategory_type1() {
		return category_type1;
	}

	public void setCategory_type1(String category_type1) {
		this.category_type1 = category_type1;
	}

	public LabMasterBean getLabMaster() {
		return labMaster;
	}

	public void setLabMaster(LabMasterBean labMaster) {
		this.labMaster = labMaster;
	}

	public long getTestLabSampleSubCodeId() {
		return testLabSampleSubCodeId;
	}

	public void setTestLabSampleSubCodeId(long testLabSampleSubCodeId) {
		this.testLabSampleSubCodeId = testLabSampleSubCodeId;
	}

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	public String getCategory_type() {
		return category_type;
	}

	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}

	public Integer getCurrentSequence() {
		return currentSequence;
	}

	public void setCurrentSequence(Integer currentSequence) {
		this.currentSequence = currentSequence;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "SamplePartCodeLabMapping [testLabSampleSubCodeId=" + testLabSampleSubCodeId + ", labMaster=" + labMaster
				+ ", labId=" + labId + ", category_type=" + category_type + ", category_type1=" + category_type1
				+ ", currentSequence=" + currentSequence + ", activeFlag=" + activeFlag + "]";
	}

	
}
