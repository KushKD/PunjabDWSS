/**
 * 
 */
package com.prwss.min.quality;

import org.apache.struts.action.ActionForm;

/**
 * @author BH390738
 *
 */
public class TestResultReportForm extends ActionForm{
	
	private static final long serialVersionUID = -8358500539L;
	
	private String lab;
	private String choose_fields;
	private String select_fields[];
	private String selectReport="WC0001_008";
	private String jasperFile;
	private String fileTitle="WC0001_008";
	
	
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getChoose_fields() {
		return choose_fields;
	}
	public void setChoose_fields(String choose_fields) {
		this.choose_fields = choose_fields;
	}
	public String[] getSelect_fields() {
		return select_fields;
	}
	public void setSelect_fields(String[] select_fields) {
		this.select_fields = select_fields;
	}
	public String getSelectReport() {
		return selectReport;
	}
	public void setSelectReport(String selectReport) {
		this.selectReport = selectReport;
	}
	public String getJasperFile() {
		if(selectReport.equals("WC0001_008"))
			return "/quality/waterQualityReports/WC0001_008.jasper";
		return jasperFile;
	}
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}
	@Override
	public String toString() {
		return "TestResultReportForm [lab=" + lab + ", choose_fields=" + choose_fields + ", select_fields="
				+ select_fields + "]";
	}
}
