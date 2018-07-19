/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;
import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;

/**
 * @author BH390738
 *
 */
public interface UpdateYearlyPlanDao {
	public boolean updateYearPlan()throws DataAccessException;
	public List<YearlyPlanInspectionBean> getYearlyPlanDetails(YearlyPlanInspectionForm yearlyPlanInspectionForm)throws DataAccessException;
	public List<YearlyPlanInspectionBean> getYearlyPlanDetails(String  yearlyPlanId)throws DataAccessException;
	public boolean updateYearlyPlanDetails(YearlyPlanInspectionBean  yearlyPlanInspectionBean)throws DataAccessException;
	public boolean updateComponentYearlyPlanDetails(YearlyPlanningComponentMappingBean  yearlyPlanningComponentMappingBean)throws DataAccessException;
	
}
