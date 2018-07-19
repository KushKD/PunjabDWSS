/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class SchemeGridBean implements Serializable {

	private static final long serialVersionUID=-4565334542L; 
	
	private String schemeId;
	private String schemeName;
	private String stageId;
	private String stageName;
	private String inspectionDate;
	private String checkedId;
	private String checkedName;
	private String remarks;
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public String getCheckedId() {
		return checkedId;
	}
	public void setCheckedId(String checkedId) {
		this.checkedId = checkedId;
	}
	public String getCheckedName() {
		return checkedName;
	}
	public void setCheckedName(String checkedName) {
		this.checkedName = checkedName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "SchemeGridBean [schemeId=" + schemeId + ", schemeName=" + schemeName + ", stageId=" + stageId
				+ ", stageName=" + stageName + ", inspectionDate=" + inspectionDate + ", checkedId=" + checkedId
				+ ", checkedName=" + checkedName + ", remarks=" + remarks + "]";
	}
	
}
