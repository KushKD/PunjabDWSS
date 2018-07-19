package com.prwss.min.SDU.bean;

import java.io.Serializable;

public class CategoryVillageDto implements Serializable {
	
	private static final long serialVersionUID=-1335754545L;
	
	private String category;
	private String noOfVillages;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNoOfVillages() {
		return noOfVillages;
	}
	public void setNoOfVillages(String noOfVillages) {
		this.noOfVillages = noOfVillages;
	}
	@Override
	public String toString() {
		return "CategoryVillageDto [category=" + category + ", noOfVillages=" + noOfVillages + "]";
	}

}
