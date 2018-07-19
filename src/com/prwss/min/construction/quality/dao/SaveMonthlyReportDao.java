/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;

/**
 * @author BH390738
 *
 */
public interface SaveMonthlyReportDao {

	public List<Long> getScheme(String yearPlanId,String monthlyId)throws DataAccessException;
	public List<MonthlyPlanDto> getSchemeName(List<Integer> schemeIds)throws DataAccessException;
	public boolean saveMonthlyReport(List<SaveMonthlyReportObservationBean> saveMonthlyReportObservationBeans)throws DataAccessException;
	
}
