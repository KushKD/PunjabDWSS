/**
 * 
 */
package com.prwss.min.finance.bo;

import com.prwss.min.finance.form.FundRequestForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface FundRequestBo {

	public boolean saveFundRequest(FundRequestForm fundRequestForm,MISSessionBean misSessionBean)throws MISException;
}
