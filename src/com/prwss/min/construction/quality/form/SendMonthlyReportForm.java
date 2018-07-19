/**
 * 
 */
package com.prwss.min.construction.quality.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.prwss.min.construction.quality.bean.MonthlyProgressDto;

/**
 * @author BH390738
 *
 */
public class SendMonthlyReportForm extends ActionForm{
	private static final long serialVersionUID = -23313476L;
	
	private String yearPlan;
	private String month;
	private String preImplementation;
	private String implementation;
	private String postImplemetation;
	private String importantObservation;
	private String comment;
	private String monthlyPlanId;
	private FormFile progressFile;
	private String yearPlanName;
	private String monthName;
	
	

	
	public String getYearPlanName() {
		return yearPlanName;
	}
	public void setYearPlanName(String yearPlanName) {
		this.yearPlanName = yearPlanName;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public FormFile getProgressFile() {
		return progressFile;
	}
	public void setProgressFile(FormFile progressFile) {
		this.progressFile = progressFile;
	}
	public String getYearPlan() {
		return yearPlan;
	}
	public void setYearPlan(String yearPlan) {
		this.yearPlan = yearPlan;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getPreImplementation() {
		return preImplementation;
	}
	public void setPreImplementation(String preImplementation) {
		this.preImplementation = preImplementation;
	}
	public String getImplementation() {
		return implementation;
	}
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	public String getPostImplemetation() {
		return postImplemetation;
	}
	public void setPostImplemetation(String postImplemetation) {
		this.postImplemetation = postImplemetation;
	}
	public String getImportantObservation() {
		return importantObservation;
	}
	public void setImportantObservation(String importantObservation) {
		this.importantObservation = importantObservation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMonthlyPlanId() {
		return monthlyPlanId;
	}
	public void setMonthlyPlanId(String monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}
	private List<MonthlyProgressDto> monthlyProgressDtos;
	
	
	public List<MonthlyProgressDto> getMonthlyProgressDtos() {
		return monthlyProgressDtos;
	}
	public void setMonthlyProgressDtos(List<MonthlyProgressDto> monthlyProgressDtos) {
		this.monthlyProgressDtos = monthlyProgressDtos;
	}
	
	public void setMonthlyPlanLst(int index, MonthlyProgressDto value) {
		this.monthlyProgressDtos.add(index,value);
	}
	public MonthlyProgressDto getMonthlyPlanLst(int index) {
		int size = monthlyProgressDtos.size();
		while (index >= size) {
			monthlyProgressDtos.add(new MonthlyProgressDto());
			size = monthlyProgressDtos.size();
		}
		return this.monthlyProgressDtos.get(index);
	}
	@Override
	public String toString() {
		return "SendMonthlyReportForm [yearPlan=" + yearPlan + ", month=" + month + ", preImplementation="
				+ preImplementation + ", implementation=" + implementation + ", postImplemetation=" + postImplemetation
				+ ", importantObservation=" + importantObservation + ", comment=" + comment + ", monthlyPlanId="
				+ monthlyPlanId + ", progressFile=" + progressFile + ", monthlyProgressDtos=" + monthlyProgressDtos
				+ "]";
	}
	
}
