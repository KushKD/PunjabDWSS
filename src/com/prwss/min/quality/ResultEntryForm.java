package com.prwss.min.quality;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.validator.ValidatorForm;

import com.prwss.min.bean.ParameterMasterDto;

public class ResultEntryForm extends ValidatorForm{
	
	private static final long serialVersionUID = -49982344536693884L;
	
	private Logger log = Logger.getLogger(ResultEntryForm.class);

	private String sampleNum;
	private String partno;
	private String technician;
	private String labname;
	private String aluminium;
	private String lead;
	private String selenium;
	private String chromium;
	private String mercury;
	private String arcenic;
	private String cadmium;
	private String nickel;
	private String copper;
	private String iron;
	private String uranium;
	private String floride;
	private String chloride;
	private String nitrate;
	private String sulphate;
	private String calcium;
	private String magnesium;
	private String sodium;
	private String pottasium;
	private String empId;
	private String comDate;
	private String sampleId;
	
	
	private String resultEntryId;
	
	
	
	
	
	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	private List<ParameterMasterDto> parameterMasterBeans;
	

	public List<ParameterMasterDto> getParameterMasterBeans() {
		return parameterMasterBeans;
	}

	public void setParameterMasterBeans(List<ParameterMasterDto> parameterMasterBeans) {
		this.parameterMasterBeans = parameterMasterBeans;
	}

	public void setParameterLst(int index, ParameterMasterDto value) {
		System.out.println("---------inside setParameterMasterBean---------"+index+"------value----------"+value);
		log.debug("---------inside setParameterMasterBean---------"+index+"------value----------"+value);
		this.parameterMasterBeans.add(index,value);
	}

	public ParameterMasterDto getParameterLst(int index) {
		int size = parameterMasterBeans.size();
		while (index >= size) {
			parameterMasterBeans.add(new ParameterMasterDto());
			size = parameterMasterBeans.size();
		}
		return this.parameterMasterBeans.get(index);
	}
	
	
	
	public String getResultEntryId() {
		return resultEntryId;
	}
	public void setResultEntryId(String resultEntryId) {
		this.resultEntryId = resultEntryId;
	}
	/**
	 * @return the comDate
	 */
	public String getComDate() {
		return comDate;
	}
	/**
	 * @param comDate the comDate to set
	 */
	public void setComDate(String comDate) {
		this.comDate = comDate;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getSampleNum() {
		return sampleNum;
	}
	public void setSampleNum(String sampleNum) {
		this.sampleNum = sampleNum;
	}
	public String getPartno() {
		return partno;
	}
	public void setPartno(String partno) {
		this.partno = partno;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public String getLabname() {
		return labname;
	}
	public void setLabname(String labname) {
		this.labname = labname;
	}
	public String getAluminium() {
		return aluminium;
	}
	public void setAluminium(String aluminium) {
		this.aluminium = aluminium;
	}
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	public String getSelenium() {
		return selenium;
	}
	public void setSelenium(String selenium) {
		this.selenium = selenium;
	}
	public String getChromium() {
		return chromium;
	}
	public void setChromium(String chromium) {
		this.chromium = chromium;
	}
	public String getMercury() {
		return mercury;
	}
	public void setMercury(String mercury) {
		this.mercury = mercury;
	}
	public String getArcenic() {
		return arcenic;
	}
	public void setArcenic(String arcenic) {
		this.arcenic = arcenic;
	}
	public String getCadmium() {
		return cadmium;
	}
	public void setCadmium(String cadmium) {
		this.cadmium = cadmium;
	}
	public String getNickel() {
		return nickel;
	}
	public void setNickel(String nickel) {
		this.nickel = nickel;
	}
	public String getCopper() {
		return copper;
	}
	public void setCopper(String copper) {
		this.copper = copper;
	}
	public String getIron() {
		return iron;
	}
	public void setIron(String iron) {
		this.iron = iron;
	}
	public String getUranium() {
		return uranium;
	}
	public void setUranium(String uranium) {
		this.uranium = uranium;
	}
	public String getFloride() {
		return floride;
	}
	public void setFloride(String floride) {
		this.floride = floride;
	}
	public String getChloride() {
		return chloride;
	}
	public void setChloride(String chloride) {
		this.chloride = chloride;
	}
	public String getNitrate() {
		return nitrate;
	}
	public void setNitrate(String nitrate) {
		this.nitrate = nitrate;
	}
	public String getSulphate() {
		return sulphate;
	}
	public void setSulphate(String sulphate) {
		this.sulphate = sulphate;
	}
	public String getCalcium() {
		return calcium;
	}
	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}
	public String getMagnesium() {
		return magnesium;
	}
	public void setMagnesium(String magnesium) {
		this.magnesium = magnesium;
	}
	public String getSodium() {
		return sodium;
	}
	public void setSodium(String sodium) {
		this.sodium = sodium;
	}
	public String getPottasium() {
		return pottasium;
	}
	public void setPottasium(String pottasium) {
		this.pottasium = pottasium;
	}

	@Override
	public String toString() {
		return "ResultEntryForm [log=" + log + ", sampleNum=" + sampleNum + ", partno=" + partno + ", technician="
				+ technician + ", labname=" + labname + ", aluminium=" + aluminium + ", lead=" + lead + ", selenium="
				+ selenium + ", chromium=" + chromium + ", mercury=" + mercury + ", arcenic=" + arcenic + ", cadmium="
				+ cadmium + ", nickel=" + nickel + ", copper=" + copper + ", iron=" + iron + ", uranium=" + uranium
				+ ", floride=" + floride + ", chloride=" + chloride + ", nitrate=" + nitrate + ", sulphate=" + sulphate
				+ ", calcium=" + calcium + ", magnesium=" + magnesium + ", sodium=" + sodium + ", pottasium="
				+ pottasium + ", empId=" + empId + ", comDate=" + comDate + ", resultEntryId=" + resultEntryId
				+ ", parameterMasterBeans=" + parameterMasterBeans + "]";
	}

	
	

}
