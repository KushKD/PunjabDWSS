package com.prwss.min.SDU.form;

import java.io.Serializable;

public class MaterialDistributionGrid implements Serializable{
	
	private static final long serialVersionUID = -4052398701889816025L;
	
	private String typeOfMaterialName;
	private String noOfCopiesDistName;
	
	public String getTypeOfMaterialName() {
		return typeOfMaterialName;
	}
	public void setTypeOfMaterialName(String typeOfMaterialName) {
		this.typeOfMaterialName = typeOfMaterialName;
	}
	public String getNoOfCopiesDistName() {
		return noOfCopiesDistName;
	}
	public void setNoOfCopiesDistName(String noOfCopiesDistName) {
		this.noOfCopiesDistName = noOfCopiesDistName;
	}
	@Override
	public String toString() {
		return "MaterialDistributionGrid [typeOfMaterialName=" + typeOfMaterialName + ", noOfCopiesDistName="
				+ noOfCopiesDistName + "]";
	}
	
}
