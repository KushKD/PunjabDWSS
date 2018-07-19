/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

/**
 * @author Gaurav     Mishra
 *
 */
public class SampleDto  implements Serializable{

	private static final long serialVersionUID = 1345345344L;
	
	private Integer sampleId;
	private  String sampleNumber;
	
	public Integer getSampleId() {
		return sampleId;
	}
	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}
	
	
	public String getSampleNumber() {
		return sampleNumber;
	}
	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}
	@Override
	public String toString() {
		return "SampleDto [sampleId=" + sampleId + ", sampleNo=" + sampleNumber + "]";
	}
	
	
}
