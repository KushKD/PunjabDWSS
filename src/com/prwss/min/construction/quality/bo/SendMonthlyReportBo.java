/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.prwss.min.construction.quality.form.SendMonthlyReportForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface SendMonthlyReportBo<T> {

	public boolean forwardReport(SendMonthlyReportForm sendMonthlyReportForm,int entBy)throws MISException, FileNotFoundException, IOException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	
	public boolean approveReport(SendMonthlyReportForm sendMonthlyReportForm,int entBy)throws MISException;
	
}
