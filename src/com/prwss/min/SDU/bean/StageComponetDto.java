package com.prwss.min.SDU.bean;

import java.io.Serializable;

public class StageComponetDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8971669529954948705L;
	private String categoryName;
	private int villages;

	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getVillages() {
		return villages;
	}

	public void setVillages(int villages) {
		this.villages = villages;
	}

	

}
