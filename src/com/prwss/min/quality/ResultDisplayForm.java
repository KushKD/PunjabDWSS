package com.prwss.min.quality;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.validator.ValidatorForm;

public class ResultDisplayForm extends ValidatorForm {
	private Logger log = Logger.getLogger(ResultEntryForm.class);
	private static final long serialVersionUID = -8358534500539L;
	
	private String comments;
	private String sampleNo;
	
	private String sampleNumbers;
	private String previousComments;
	private List<ResultEntryDto> resultEntryDtos;
	

	public List<ResultEntryDto> getResultEntryDtos() {
		return resultEntryDtos;
	}

	public void setResultEntryDtos(List<ResultEntryDto> resultEntryDtos) {
		this.resultEntryDtos = resultEntryDtos;
	}

	public void setResultEntryLst(int index, ResultEntryDto value) {
		System.out.println("---------inside setParameterMasterBean---------"+index+"------value----------"+value);
		log.debug("---------inside setParameterMasterBean---------"+index+"------value----------"+value);
		this.resultEntryDtos.add(index,value);
	}

	public ResultEntryDto getResultEntryLst(int index) {
		int size = resultEntryDtos.size();
		while (index >= size) {
			resultEntryDtos.add(new ResultEntryDto());
			size = resultEntryDtos.size();
		}
		return this.resultEntryDtos.get(index);
	}
	
	public String getPreviousComments() {
		return previousComments;
	}


	public void setPreviousComments(String previousComments) {
		this.previousComments = previousComments;
	}


	/**
	 * @return the sampleNumbers
	 */
	public String getSampleNumbers() {
		return sampleNumbers;
	}


	/**
	 * @param sampleNumbers the sampleNumbers to set
	 */
	public void setSampleNumbers(String sampleNumbers) {
		this.sampleNumbers = sampleNumbers;
	}


	/**
	 * @return the sampleNo
	 */
	public String getSampleNo() {
		return sampleNo;
	}


	/**
	 * @param sampleNo the sampleNo to set
	 */
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}


	public String getComments() {
		return comments;
	}
	

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
