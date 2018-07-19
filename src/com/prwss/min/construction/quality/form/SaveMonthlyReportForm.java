/**
 * 
 */
package com.prwss.min.construction.quality.form;

import java.util.List;
import org.apache.struts.action.ActionForm;
import com.prwss.min.construction.quality.bean.MonthlyProgressDto;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class SaveMonthlyReportForm extends ActionForm{

	private static final long serialVersionUID = -2331103476L;
	
	private String yearPlan;
	private String month;
	private String observation;
	private String commentType;
	private String scheme;
	private String schemeName;
	private String phaseName;
	private String monthlyPlanId;
	private List<MonthlyProgressDto> monthlyProgressDtos;
	private Datagrid saveMonthlyReportGrid;
	
	
	public String getMonthlyPlanId() {
		return monthlyPlanId;
	}
	public void setMonthlyPlanId(String monthlyPlanId) {
		this.monthlyPlanId = monthlyPlanId;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
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
	
	public Datagrid getSaveMonthlyReportGrid() {
		return saveMonthlyReportGrid;
	}
	public void setSaveMonthlyReportGrid(Datagrid saveMonthlyReportGrid) {
		this.saveMonthlyReportGrid = saveMonthlyReportGrid;
	}
	@Override
	public String toString() {
		return "SaveMonthlyReportForm [yearPlan=" + yearPlan + ", month=" + month + ", observation=" + observation
				+ ", commentType=" + commentType + ", scheme=" + scheme + ", monthlyProgressDtos=" + monthlyProgressDtos
				+ ", saveMonthlyReportGrid=" + saveMonthlyReportGrid + "]";
	}
}
