/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import com.prwss.min.construction.quality.form.MonthlyProgressForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface MonthlyProgressBo {

	public boolean save(MonthlyProgressForm monthlyProgressForm,int enterBy)throws MISException;
	public boolean update(MonthlyProgressForm monthlyProgressForm,int enterBy)throws MISException;
	
}
