/**
 * 
 */
package com.prwss.min.sanitation.bo;

import com.prwss.min.sanitation.form.ApprovePaymentForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface ApprovePaymentBo {

	public boolean delete(String requestDetailId,Long enteredBy)throws MISException;
	public boolean updatePaymentDetails(ApprovePaymentForm approvePaymentForm,String back) throws MISException;
}
