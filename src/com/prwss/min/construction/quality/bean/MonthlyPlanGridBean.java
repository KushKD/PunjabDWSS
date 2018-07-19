/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class MonthlyPlanGridBean implements Serializable{

	private static final long serialVersionUID=-12345876543L;
	
	private String scheme;
	private String district;
	private String division;
	private String constituency;
	private String districtName;
	private String divisionName;
	private String schemeName;
	private String constituencyName;
	
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	@Override
	public String toString() {
		return "MonthlyPlanGridBean [schemeId=" + scheme + ", district=" + district + ", division=" + division
				+ ", constituency=" + constituency + ", districtName=" + districtName + ", divisionName=" + divisionName
				+ ", schemeName=" + schemeName + ", constituencyName=" + constituencyName + "]";
	}
	
}
