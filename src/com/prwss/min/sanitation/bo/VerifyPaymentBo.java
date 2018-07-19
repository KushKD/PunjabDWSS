/**
 * 
 */
package com.prwss.min.sanitation.bo;

import com.prwss.min.sanitation.form.VerifyPaymentForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface VerifyPaymentBo {

	public boolean delete(String requestDetailId,Long enteredBy)throws MISException;
	public boolean updatePaymentDetails(VerifyPaymentForm verifyPaymentForm,String back) throws MISException;
}
