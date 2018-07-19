/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

/**
 * @author BH390738
 *
 */
public class ParameterMasterDto  implements Serializable {

	private static final long serialVersionUID = -3020543438L;
	
	private String parameterName;
	private String uom;
	private String permissibleLimit;
	private String acceptableLimit;
	private String parameterValue;
	private Integer parameterId;
	private Integer resultEntryDetailId;
	
	
	
	public Integer getResultEntryDetailId() {
		return resultEntryDetailId;
	}

	public void setResultEntryDetailId(Integer resultEntryDetailId) {
		this.resultEntryDetailId = resultEntryDetailId;
	}

	public Integer getParameterId() {
		return parameterId;
	}

	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getPermissibleLimit() {
		return permissibleLimit;
	}

	public void setPermissibleLimit(String permissibleLimit) {
		this.permissibleLimit = permissibleLimit;
	}

	public String getAcceptableLimit() {
		return acceptableLimit;
	}

	public void setAcceptableLimit(String acceptableLimit) {
		this.acceptableLimit = acceptableLimit;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	public String toString() {
		return "ParameterMasterDto [parameterName=" + parameterName + ", uom=" + uom + ", permissibleLimit="
				+ permissibleLimit + ", acceptableLimit=" + acceptableLimit + ", parameterValue=" + parameterValue
				+ "]";
	}
	
}
