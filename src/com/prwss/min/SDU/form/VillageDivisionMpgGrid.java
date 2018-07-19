package com.prwss.min.SDU.form;

import java.io.Serializable;

public class VillageDivisionMpgGrid implements Serializable{
	
	private static final long serialVersionUID = 3635076334815L;
	
	
	private String village;

	
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	@Override
	public String toString() {
		return "VillageDivisionMpgGrid [village=" + village + "]";
	}

	
}
