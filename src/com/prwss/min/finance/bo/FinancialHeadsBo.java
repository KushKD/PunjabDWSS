/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.List;

import com.prwss.min.finance.form.FinanceHeadsForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface FinancialHeadsBo<T> {

	public boolean save(FinanceHeadsForm financeHeadsForm,MISSessionBean misSessionBean)throws MISException;
	public boolean update(FinanceHeadsForm financeHeadsForm,MISSessionBean misSessionBean)throws MISException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
