/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanSchemeMappingBean;
import com.prwss.min.construction.quality.bean.TeamMasterBean;
import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;

/**
 * @author BH390738
 *
 */
public interface MonthlyPlanDao {

	public int saveMonthlyPlan(MonthlyPlanInspectionBean monthlyPlanInspectionBean) throws DataAccessException;

	public boolean saveMonthlyPlanSchemeMapping(List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans)
			throws DataAccessException;

	public boolean updateMonthlyPlanSchemeMapping(List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans)
			throws DataAccessException;

	public boolean updateMonthlyPlan(MonthlyPlanInspectionBean monthlyPlanSchemeMappingBeans)
			throws DataAccessException;

	public List<YearlyPlanInspectionBean> getPerMonthVisitedVillages(String planId, String componentId)
			throws DataAccessException;

	public List<TeamMasterBean> fetchTeam(String financialYearId) throws DataAccessException;

	public List<MonthlyPlanInspectionBean> findMonthlyPlan(String monthlyPlanId) throws DataAccessException;

	public List<MonthlyPlanInspectionBean> fetchMonthlyPlanSchemeMapping(Integer yearlyPlanId,
			MonthlyPlanInspectionForm monthlyPlanInspectionForm) throws DataAccessException;
	
	public List<MonthlyPlanDto> getMonthlyPlanData(MonthlyPlanInspectionForm monthlyPlanInspectionForm,String searchString, int clickedColumn, String colOrder)throws DataAccessException;
	
	public List<Integer> findYeralyPlan(String yearPlan,String component) throws DataAccessException;
	
	public List<Integer> findYeralyPlan(String yearPlan) throws DataAccessException;


}
