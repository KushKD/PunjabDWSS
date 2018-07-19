/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface YearPlanInspectionBo {

	public boolean save(YearlyPlanInspectionForm yearlyPlanInspectionForm,Integer employeeId)throws MISException;
}
