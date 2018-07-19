/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.form.BeneficiaryForm;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface BeneficiaryBo<T> {
	
	public List<BeneficiaryEntryBean> validateBeneficiaryDetails(T beneficiaryForm) throws MISException;

	public boolean saveBeneficiaryDetails(BeneficiaryForm beneficiaryForm)throws MISException;
	
	public List<BeneficiaryForm> populateBeneficiaryDetails(ViewRegistrationsForm viewRegistrationForm,String searchString,int clickedColumn,String colOrder)throws MISException;
	
	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList)throws MISException;

	public List<T> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn, String sortingOrder, Object t)throws MISException;

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	
}
