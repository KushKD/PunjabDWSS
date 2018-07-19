package com.prwss.min.quality.struts;

public class Parameters {
	
	private String parameterID;
	private String acceptableLimit;
	private String permissibleLimit;
	private Boolean bdl;
	private Boolean nd;
	private Boolean fail;
	private Boolean pass;
	public String getParameterID() {
		return parameterID;
	}
	public void setParameterID(String parameterID) {
		this.parameterID = parameterID;
	}
	public String getAcceptableLimit() {
		return acceptableLimit;
	}
	public void setAcceptableLimit(String acceptableLimit) {
		this.acceptableLimit = acceptableLimit;
	}
	public String getPermissibleLimit() {
		return permissibleLimit;
	}
	public void setPermissibleLimit(String permissibleLimit) {
		this.permissibleLimit = permissibleLimit;
	}
	public Boolean getBdl() {
		return bdl;
	}
	public void setBdl(Boolean bdl) {
		this.bdl = bdl;
	}
	public Boolean getNd() {
		return nd;
	}
	public void setNd(Boolean nd) {
		this.nd = nd;
	}
	public Boolean getFail() {
		return fail;
	}
	public void setFail(Boolean fail) {
		this.fail = fail;
	}
	public Boolean getPass() {
		return pass;
	}
	public void setPass(Boolean pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Parameters [parameterID=" + parameterID + ", acceptableLimit=" + acceptableLimit + ", permissibleLimit="
				+ permissibleLimit + ", bdl=" + bdl + ", nd=" + nd + ", fail=" + fail + ", pass=" + pass + "]";
	}
	
	
	

}
