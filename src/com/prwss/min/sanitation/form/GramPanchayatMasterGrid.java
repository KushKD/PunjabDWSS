package com.prwss.min.sanitation.form;

import java.io.Serializable;

public class GramPanchayatMasterGrid implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 934396663675134566L;
	
	private String gramPanchayatName;
	private String districtGrid;
	private String blockGrid;
	private String villageGrid;
	private String statusGrid;
	
	
	
	
	
	public String getStatusGrid() {
		return statusGrid;
	}
	public void setStatusGrid(String statusGrid) {
		this.statusGrid = statusGrid;
	}
	public String getGramPanchayatName() {
		return gramPanchayatName;
	}
	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}
	public String getDistrictGrid() {
		return districtGrid;
	}
	public void setDistrictGrid(String districtGrid) {
		this.districtGrid = districtGrid;
	}
	public String getBlockGrid() {
		return blockGrid;
	}
	public void setBlockGrid(String blockGrid) {
		this.blockGrid = blockGrid;
	}
	public String getVillageGrid() {
		return villageGrid;
	}
	public void setVillageGrid(String villageGrid) {
		this.villageGrid = villageGrid;
	}
	@Override
	public String toString() {
		return "GramPanchayatMasterGrid [gramPanchayatName="
				+ gramPanchayatName + ", districtGrid=" + districtGrid
				+ ", blockGrid=" + blockGrid + ", villageGrid=" + villageGrid
				+ ", statusGrid=" + statusGrid + "]";
	}
	
	
	
	
	
	

}
