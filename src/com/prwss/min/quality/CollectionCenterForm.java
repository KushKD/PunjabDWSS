/**
 * 
 */
package com.prwss.min.quality;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class CollectionCenterForm extends ValidatorForm {

	private static final long serialVersionUID = -23311068618776L;
	
	private String name;
	private String address;
	private String phoneNo;
	private String lab;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	@Override
	public String toString() {
		return "CollectionCenterForm [name=" + name + ", address=" + address + ", phoneNo=" + phoneNo + ", lab=" + lab
				+ "]";
	}
}
