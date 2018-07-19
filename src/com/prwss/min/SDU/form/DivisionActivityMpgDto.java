package com.prwss.min.SDU.form;

import org.apache.struts.validator.ValidatorForm;

public class DivisionActivityMpgDto extends ValidatorForm{
	
	private static final long serialVersionUID = -423018650328184032L;
	
	private Integer stageId;
	private String stageName;
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	@Override
	public String toString() {
		return "DivisionActivityMpgDto [stageId=" + stageId + ", stageName=" + stageName + "]";
	}
	
}
