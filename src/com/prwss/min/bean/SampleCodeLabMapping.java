/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="wq_lab_samplecode_mpg",schema="prwss_main")
public class SampleCodeLabMapping implements Serializable {

	private static final long serialVersionUID = 13423445344L;
	
	@Id
	@Column(name = "test_lab_samplecode_id")
	private long testLabSamplecodeId;
	
	@Column(name = "lab_id")
	private Integer labId;
	
	@Column(name = "current_sequence")
	private Integer currentSequence;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	

	public long getTestLabSamplecodeId() {
		return testLabSamplecodeId;
	}

	public void setTestLabSamplecodeId(long testLabSamplecodeId) {
		this.testLabSamplecodeId = testLabSamplecodeId;
	}

	public Integer getLabId() {
		return labId;
	}

	public void setLabId(Integer labId) {
		this.labId = labId;
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
		return "SampleCodeLabMapping [testLabSamplecodeId=" + testLabSamplecodeId + ", labId=" + labId
				+ ", currentSequence=" + currentSequence + ", activeFlag=" + activeFlag + "]";
	}
}
