/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.form.MonthlyProgressForm;
import com.prwss.min.construction.quality.form.SaveMonthlyReportForm;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;

/**
 * @author BH390738
 *
 */
public interface MonthlyProgressDao{

	public boolean saveMonthlyProgress(List<MonthlyProgressBean> monthlyProgressBeans)throws DataAccessException;
	public List<Integer> getPerVisitedVillages(String yerlyPlanId,String monthlyId,String componentId,String teamId)throws DataAccessException;
	public List<MonthlyProgressBean> pupolateMonthlyProgressBean(MonthlyProgressForm monthlyProgressForm)throws DataAccessException;
	public List<MonthlyProgressBean> pupolateMonthlyProgressBean(SaveMonthlyReportForm saveMonthlyReportForm)throws DataAccessException;
	public List<MonthlyProgressBean> pupolateMonthlyProgressBean(SendMonthlyReportForm sendMonthlyReportForm)throws DataAccessException;
	public List<Integer> getMonthlyPlanId(Integer yearlyPlanId,String monthId,String Team)throws DataAccessException;
	public List<MonthlyProgressBean> getMonthlyProgress(Integer monthlyPlanId)throws DataAccessException;
}
