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

/**
 * @author BH390738
 *
 */
public interface VerifyPaymentDao {
	public List<BeneficiaryDto> getBillDetails(Long enterBy) throws DataAccessException;
	public List<BeneficiaryDto> getPaymentDetails(String paymentRequestId,Long enterBy) throws DataAccessException;
	public List<BeneficiaryPaymentDetailsBean> findPaymentDetails(String requestDetailId)throws DataAccessException;
	public boolean deletePaymentDetails(BeneficiaryPaymentDetailsBean beneficiaryPaymentDetailsBean)throws DataAccessException;
	public boolean updatePaymentDetails(List<Integer> paymentRequestId)throws DataAccessException;
	public List<BeneficiaryPaymentBean> validatePaymentDetails(List<Integer> paymentRequestId)throws DataAccessException;
	public List<WorkFlowMasterBean> getEmployeeDetails(Integer userId) throws DataAccessException;
	
	public boolean updateBeneficiaryPayment(BeneficiaryPaymentBean beneficiaryPaymentBean) throws DataAccessException;
	
}
