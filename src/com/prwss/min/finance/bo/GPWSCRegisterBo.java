/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.List;

import com.prwss.min.finance.form.GPWSCRegisterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface GPWSCRegisterBo<T> {

	public boolean save(GPWSCRegisterForm gpwscRegisterForm,MISSessionBean misSessionBean)throws MISException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
