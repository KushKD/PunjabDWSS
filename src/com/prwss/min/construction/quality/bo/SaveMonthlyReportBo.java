/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import com.prwss.min.construction.quality.form.SaveMonthlyReportForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface SaveMonthlyReportBo {

	public boolean saveMonthlyReport(SaveMonthlyReportForm saveMonthlyReportForm,int entBy)throws MISException;
}
