/**
 * 
 */
package com.prwss.min.finance.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class FundSourceMasterForm   extends ValidatorForm{

	private static final long serialVersionUID = 3635577633815L;
	
	private String program;
	private String scheme;
	private String fundSrcMaster;
	private String stateShare;
	private String centerShare;
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getFundSrcMaster() {
		return fundSrcMaster;
	}
	public void setFundSrcMaster(String fundSrcMaster) {
		this.fundSrcMaster = fundSrcMaster;
	}
	public String getStateShare() {
		return stateShare;
	}
	public void setStateShare(String stateShare) {
		this.stateShare = stateShare;
	}
	public String getCenterShare() {
		return centerShare;
	}
	public void setCenterShare(String centerShare) {
		this.centerShare = centerShare;
	}
	
	
	
}
