/**
 * 
 */
package com.prwss.min.finance.bo;

import com.prwss.min.finance.form.AllocationForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface AllocationBo {

	public boolean save(AllocationForm allocationForm,MISSessionBean misSessionBean)throws MISException;
}
