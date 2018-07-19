/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.List;

import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface DivisionBudgetBo<T> {

	public String save(DivisionBudgetForm divisionBudgetForm,MISSessionBean misSessionBean)throws MISException;
	public String update(DivisionBudgetForm divisionBudgetForm,MISSessionBean misSessionBean)throws MISException;
	public boolean approve(DivisionBudgetForm divisionBudgetForm,MISSessionBean misSessionBean)throws MISException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	
	public String forward(DivisionBudgetForm divisionBudgetForm,MISSessionBean misSessionBean)throws MISException;
	public String forwardState(DivisionBudgetForm divisionBudgetForm,MISSessionBean misSessionBean)throws MISException;
}
