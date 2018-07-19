/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.form.PaymentRequestForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface PaymentRequestBo {

	public List<BeneficiaryDto> findBeneficiary(PaymentRequestForm paymentRequestForm)throws MISException;
	
	public boolean saveBeneficiary(PaymentRequestForm paymentRequestForm)throws MISException;
	public boolean updatePaymentDetails(PaymentRequestForm paymentRequestForm)throws MISException;

}
