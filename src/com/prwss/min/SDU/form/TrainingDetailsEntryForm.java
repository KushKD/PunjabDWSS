package com.prwss.min.SDU.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TrainingDetailsEntryForm extends ValidatorForm {
	
	private static final long serialVersionUID = -4230186032818032L;
	
	private String financialYear;
	private String division;
	private String village;
	private String activity;
	private String trainingRefNo;
	private String tainingDate;
	private String lvlOfTrng;
	private String trainer;
	private String nameOfTrng;
	private String expenditure;
	private String total;
	private String male;
	private String scMale;
	private String female;
	private String scFemale;
	private String scTotal;
	private String panchayatMembers;
	private String typeOfMaterial;
	private String noOfCopiesDist;
	private String photoTitle;
	private FormFile upldPhoto;
	private Datagrid materialDistGrid;
	
	
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getTrainingRefNo() {
		return trainingRefNo;
	}
	public void setTrainingRefNo(String trainingRefNo) {
		this.trainingRefNo = trainingRefNo;
	}
	public String getTainingDate() {
		return tainingDate;
	}
	public void setTainingDate(String tainingDate) {
		this.tainingDate = tainingDate;
	}
	public String getLvlOfTrng() {
		return lvlOfTrng;
	}
	public void setLvlOfTrng(String lvlOfTrng) {
		this.lvlOfTrng = lvlOfTrng;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getNameOfTrng() {
		return nameOfTrng;
	}
	public void setNameOfTrng(String nameOfTrng) {
		this.nameOfTrng = nameOfTrng;
	}
	public String getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getMale() {
		return male;
	}
	public void setMale(String male) {
		this.male = male;
	}
	public String getScMale() {
		return scMale;
	}
	public void setScMale(String scMale) {
		this.scMale = scMale;
	}
	public String getFemale() {
		return female;
	}
	public void setFemale(String female) {
		this.female = female;
	}
	public String getScFemale() {
		return scFemale;
	}
	public void setScFemale(String scFemale) {
		this.scFemale = scFemale;
	}
	public String getScTotal() {
		return scTotal;
	}
	public void setScTotal(String scTotal) {
		this.scTotal = scTotal;
	}
	public String getPanchayatMembers() {
		return panchayatMembers;
	}
	public void setPanchayatMembers(String panchayatMembers) {
		this.panchayatMembers = panchayatMembers;
	}
	public String getTypeOfMaterial() {
		return typeOfMaterial;
	}
	public void setTypeOfMaterial(String typeOfMaterial) {
		this.typeOfMaterial = typeOfMaterial;
	}
	public String getNoOfCopiesDist() {
		return noOfCopiesDist;
	}
	public void setNoOfCopiesDist(String noOfCopiesDist) {
		this.noOfCopiesDist = noOfCopiesDist;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	public FormFile getUpldPhoto() {
		return upldPhoto;
	}
	public void setUpldPhoto(FormFile upldPhoto) {
		this.upldPhoto = upldPhoto;
	}
	public Datagrid getMaterialDistGrid() {
		return materialDistGrid;
	}
	public void setMaterialDistGrid(Datagrid materialDistGrid) {
		this.materialDistGrid = materialDistGrid;
	}
	@Override
	public String toString() {
		return "TrainingDetailsEntryForm [financialYear=" + financialYear + ", division=" + division + ", village="
				+ village + ", activity=" + activity + ", trainingRefNo=" + trainingRefNo + ", tainingDate="
				+ tainingDate + ", lvlOfTrng=" + lvlOfTrng + ", trainer=" + trainer + ", nameOfTrng=" + nameOfTrng
				+ ", expenditure=" + expenditure + ", total=" + total + ", male=" + male + ", scMale=" + scMale
				+ ", female=" + female + ", scFemale=" + scFemale + ", scTotal=" + scTotal + ", panchayatMembers="
				+ panchayatMembers + ", typeOfMaterial=" + typeOfMaterial + ", noOfCopiesDist=" + noOfCopiesDist
				+ ", photoTitle=" + photoTitle + ", upldPhoto=" + upldPhoto + ", materialDistGrid=" + materialDistGrid
				+ "]";
	}
}
