/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentBean;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentDetailsBean;
import com.prwss.min.sanitation.dao.VerifyPaymentDao;
import com.prwss.min.sanitation.form.VerifyPaymentForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class VerifyPaymentBoImpl implements VerifyPaymentBo {
	private Logger log = Logger.getLogger(VerifyPaymentBoImpl.class);

	private VerifyPaymentDao verifyPaymentDao;

	public VerifyPaymentDao getVerifyPaymentDao() {
		return verifyPaymentDao;
	}

	public void setVerifyPaymentDao(VerifyPaymentDao verifyPaymentDao) {
		this.verifyPaymentDao = verifyPaymentDao;
	}

	@Override
	public boolean delete(String requestDetailId, Long enteredBy) throws MISException {

		List<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBeans = null;
		boolean flag = false;
		try {
			beneficiaryPaymentDetailsBeans = verifyPaymentDao.findPaymentDetails(requestDetailId);

			if (!MisUtility.ifEmpty(beneficiaryPaymentDetailsBeans)) {

				for (BeneficiaryPaymentDetailsBean beneficiaryPaymentDetailsBean : beneficiaryPaymentDetailsBeans) {
					beneficiaryPaymentDetailsBean.setIsDelete(Integer.parseInt(MISConstants.ONE));
					flag = verifyPaymentDao.deletePaymentDetails(beneficiaryPaymentDetailsBean);
				}
			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return flag;
	}

	@Override
	public boolean updatePaymentDetails(VerifyPaymentForm verifyPaymentForm,String flow) throws MISException {
		List<BeneficiaryPaymentBean> beneficiaryPaymentBeans = null;
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		boolean status = false;
		try {
			List<Integer> paymentRequestId = populatePaymentRequestId(verifyPaymentForm);
			beneficiaryPaymentBeans = verifyPaymentDao.validatePaymentDetails(paymentRequestId);
			if (!MisUtility.ifEmpty(beneficiaryPaymentBeans)) {
				for (BeneficiaryPaymentBean beneficiaryPaymentBean1 : beneficiaryPaymentBeans) {
					workFlowMasterBeans = verifyPaymentDao
							.getEmployeeDetails(beneficiaryPaymentBean1.getLyingWithUser());
					if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
						beneficiaryPaymentBean1
								.setUserid(Integer.parseInt(String.valueOf(verifyPaymentForm.getEnterBy())));
						if(flow.equalsIgnoreCase(MISConstants.FORWARD)){
							beneficiaryPaymentBean1.setLyingWithUser(workFlowMasterBeans.get(0).getTo_emp_id_fwd());
						}else if(flow.equalsIgnoreCase(MISConstants.BACK)){
							beneficiaryPaymentBean1.setLyingWithUser(workFlowMasterBeans.get(0).getTo_emp_id_ret());
						}
						
						status = verifyPaymentDao.updateBeneficiaryPayment(beneficiaryPaymentBean1);
					}
				}

			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}

	private List<Integer> populatePaymentRequestId(VerifyPaymentForm verifyPaymentForm) {

		List<Integer> paymentRequestId=null;
	
		try {
			if (MisUtility.ifEmpty(verifyPaymentForm)) {
				paymentRequestId=new ArrayList<Integer>();
				if(MisUtility.ifEmpty(verifyPaymentForm.getValidationRequestId())){
					String[] validationRequest=verifyPaymentForm.getValidationRequestId().split(",");
					for(String str:validationRequest){
						paymentRequestId.add(Integer.parseInt(str));
					}
				}
				
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return paymentRequestId;
	}

}
