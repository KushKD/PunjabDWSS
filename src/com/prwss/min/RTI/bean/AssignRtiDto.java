package com.prwss.min.RTI.bean;

public class AssignRtiDto {
	
	
	private int rtiID;
	private String shortname;
	private String rtiRefNo;
	private String name;
	public int getRtiID() {
		return rtiID;
	}
	public void setRtiID(int rtiID) {
		this.rtiID = rtiID;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getRtiRefNo() {
		return rtiRefNo;
	}
	public void setRtiRefNo(String rtiRefNo) {
		this.rtiRefNo = rtiRefNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AssignRtiDto [rtiID=" + rtiID + ", shortname=" + shortname + ", rtiRefNo=" + rtiRefNo + ", name=" + name
				+ "]";
	}
	
	
}