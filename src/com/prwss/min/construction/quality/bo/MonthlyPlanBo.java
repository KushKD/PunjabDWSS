/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface MonthlyPlanBo {

	public boolean saveMonthlyInspectionPlan(MonthlyPlanInspectionForm monthlyPlanInspectionForm,int empId) throws MISException;
	public boolean updateMonthlyInspectionPlan(MonthlyPlanInspectionForm monthlyPlanInspectionForm,int empId) throws MISException;
}
