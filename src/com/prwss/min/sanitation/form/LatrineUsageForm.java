/**
 * 
 */
package com.prwss.min.sanitation.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author BH390738
 *
 */
public class LatrineUsageForm extends ValidatorForm{

	private static final long serialVersionUID = -88137622344302058L;
	
	private String district;
	private String village;
	private String block;
	private String gramPanchayat;
	private String beneficiaryId;
	private String membersId;
	private Long createdBy;
	private String comments;
	private String beneficiaryName;
	private String districtName;
	private String blockName;
	private String villageName;
	private String gramPanchayatName;
	
	
	
	
	
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getGramPanchayatName() {
		return gramPanchayatName;
	}
	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getGramPanchayat() {
		return gramPanchayat;
	}
	public void setGramPanchayat(String gramPanchayat) {
		this.gramPanchayat = gramPanchayat;
	}
	public String getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getMembersId() {
		return membersId;
	}
	public void setMembersId(String membersId) {
		this.membersId = membersId;
	}
	@Override
	public String toString() {
		return "LatrineUsageForm [district=" + district + ", village=" + village + ", block=" + block
				+ ", gramPanchayat=" + gramPanchayat + ", beneficiaryId=" + beneficiaryId + ", membersId=" + membersId
				+ "]";
	}
	
}
