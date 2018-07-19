package com.prwss.min.quality.struts;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

public class WaterParameterReportForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1092745528983589710L;
	
	
	private String district;
	private String block;
	private String villageId;
	private String zone;
	private String circle;
	private String division;
	private String subdivision;
	private String constituency;
	private String schemeName;
	private String parameter;
	private String reportType;
	
	private List<Parameters> parametersList;
	
	private String fileTitle;
	private String fileName;
	
	
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return "/quality/waterQualityReports/WS001_004.jasper";
	}
	
	public String getFileTitle() {
		return "WS001_004"; 
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	
	public List<Parameters> getParametersList() {
		return parametersList;
	}
	public void setParametersList(List<Parameters> parametersList) {
		this.parametersList = parametersList;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getSubdivision() {
		return subdivision;
	}
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	@Override
	public String toString() {
		return "WaterParameterReportForm [district=" + district + ", block=" + block + ", villageId=" + villageId
				+ ", zone=" + zone + ", circle=" + circle + ", division=" + division + ", subdivision=" + subdivision
				+ ", constituency=" + constituency + ", schemeName=" + schemeName + ", parameter=" + parameter
				+ ", parametersList=" + parametersList + ", fileTitle=" + fileTitle + ", fileFile=" + fileName + "]";
	}
	
}
