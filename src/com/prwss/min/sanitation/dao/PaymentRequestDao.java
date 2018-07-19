/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentBean;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentDetailsBean;
import com.prwss.min.sanitation.form.PaymentRequestForm;

/**
 * @author BH390738
 *
 */
public interface PaymentRequestDao {

	public List<BeneficiaryDto> findBeneficiary(PaymentRequestForm paymentRequestForm) throws DataAccessException;

	public List<BeneficiaryDto> getPayment(PaymentRequestForm paymentRequestForm) throws DataAccessException;

	public List<BeneficiaryDto> getPaymentDetails(PaymentRequestForm paymentRequestForm) throws DataAccessException;

	public int saveBeneficiaryPayment(BeneficiaryPaymentBean beneficiaryPaymentBean) throws DataAccessException;

	public boolean saveBeneficiaryPaymentDetails(List<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBeans)
			throws DataAccessException;

	public List<BeneficiaryDto> getProgressStageDetails(String progressStageId) throws DataAccessException;

	public List<BeneficiaryDto> getBillDetails(PaymentRequestForm paymentRequestForm) throws DataAccessException;

	public List<BeneficiaryDto> getBillAmount(PaymentRequestForm paymentRequestForm) throws DataAccessException;

	public List<BeneficiaryPaymentBean> validateBillDetails(Integer paymentRequestId) throws DataAccessException;

	public List<WorkFlowMasterBean> getEmployeeDetails(Integer lyingWithUsr) throws DataAccessException;
	
	public boolean updateBeneficiaryPayment(BeneficiaryPaymentBean beneficiaryPaymentBean)throws DataAccessException;

}
