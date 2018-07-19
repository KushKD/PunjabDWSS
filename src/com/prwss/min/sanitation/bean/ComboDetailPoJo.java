package com.prwss.min.sanitation.bean;

/**
 * @author KU854963
 *
 */
public class ComboDetailPoJo {
	
	private int cmb_id;
	private String cmb_name;
	
	
	public int getCmb_id() {
		return cmb_id;
	}
	public void setCmb_id(int cmb_id) {
		this.cmb_id = cmb_id;
	}
	public String getCmb_name() {
		return cmb_name;
	}
	public void setCmb_name(String cmb_name) {
		this.cmb_name = cmb_name;
	}
	@Override
	public String toString() {
		return "ComboDetailPoJo [cmb_id=" + cmb_id + ", cmb_name=" + cmb_name + "]";
	}
	
	
	

}
