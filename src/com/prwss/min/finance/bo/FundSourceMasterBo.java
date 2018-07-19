/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.List;

import com.prwss.min.finance.form.FundSourceMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface FundSourceMasterBo<T> {

	public boolean saveFundSource(FundSourceMasterForm fundSourceMasterForm,MISSessionBean misSessionBean)throws MISException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
