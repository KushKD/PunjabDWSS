/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentBean;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentDetailsBean;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.dao.PaymentRequestDao;
import com.prwss.min.sanitation.form.PaymentRequestForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class PaymentRequestBoImpl implements PaymentRequestBo {
	private Logger log = Logger.getLogger(PaymentRequestBoImpl.class);

	private PaymentRequestDao paymentRequestDao;

	public PaymentRequestDao getPaymentRequestDao() {
		return paymentRequestDao;
	}

	public void setPaymentRequestDao(PaymentRequestDao paymentRequestDao) {
		this.paymentRequestDao = paymentRequestDao;
	}

	@Override
	public List<BeneficiaryDto> findBeneficiary(PaymentRequestForm paymentRequestForm) throws MISException {
		List<BeneficiaryDto> beneficiaryDtos = null;
		try {
			beneficiaryDtos = paymentRequestDao.findBeneficiary(paymentRequestForm);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return beneficiaryDtos;
	}

	@Override
	public boolean saveBeneficiary(PaymentRequestForm paymentRequestForm) throws MISException {
		boolean flag = false;
		int paymentRequestId = 0;
		BeneficiaryPaymentBean beneficiaryPaymentBean = null;
		try {
			if (MisUtility.ifEmpty(paymentRequestForm)) {
				beneficiaryPaymentBean = populateBeneficiaryPaymentBean(paymentRequestForm);
				paymentRequestId = paymentRequestDao.saveBeneficiaryPayment(beneficiaryPaymentBean);
				if (MisUtility.ifEmpty(paymentRequestId)) {
					List<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBeans = populateBeneficiaryPaymentBeanDetails(
							paymentRequestForm, paymentRequestId);

					if (!MisUtility.ifEmpty(beneficiaryPaymentDetailsBeans)) {
						flag = paymentRequestDao.saveBeneficiaryPaymentDetails(beneficiaryPaymentDetailsBeans);
					}
				}

			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return flag;
	}

	@Override
	public boolean updatePaymentDetails(PaymentRequestForm paymentRequestForm) throws MISException {
		List<BeneficiaryPaymentBean> beneficiaryPaymentBeans = null;
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		boolean status=false;
		try {
			Integer paymentRequestId=populatePaymentRequestId(paymentRequestForm);
			beneficiaryPaymentBeans = paymentRequestDao.validateBillDetails(paymentRequestId);
			if (!MisUtility.ifEmpty(beneficiaryPaymentBeans)) {
				for (BeneficiaryPaymentBean beneficiaryPaymentBean1 : beneficiaryPaymentBeans) {
					workFlowMasterBeans = paymentRequestDao.getEmployeeDetails(beneficiaryPaymentBean1.getLyingWithUser());
					if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
						beneficiaryPaymentBean1.setUserid(Integer.parseInt(String.valueOf(paymentRequestForm.getLoginUser())));
						beneficiaryPaymentBean1.setLyingWithUser(workFlowMasterBeans.get(0).getTo_emp_id_fwd());
						beneficiaryPaymentBean1.setRemarks(paymentRequestForm.getRemarks());
						status=paymentRequestDao.updateBeneficiaryPayment(beneficiaryPaymentBean1);
					}
				}

			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}

	private Integer populatePaymentRequestId(PaymentRequestForm paymentRequestForm) {
		
		Integer paymentRequestId = null;
		try {
			List<BeneficiaryDto> beneficiaryDtos = paymentRequestForm.getBeneficiaryDtos();
			if (!MisUtility.ifEmpty(beneficiaryDtos)) {
					paymentRequestId=beneficiaryDtos.get(0).getPaymetRequestId();
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return paymentRequestId;
	}

	private List<BeneficiaryPaymentDetailsBean> populateBeneficiaryPaymentBeanDetails(
			PaymentRequestForm paymentRequestForm, int paymentRequestId) {

		List<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBeans = null;
		String datetime = null;
		String approvalNo = null;
		String payOrderNumber = null;
		int requestId = 0;
		List<BeneficiaryDto> beneficiaryPaymentBeans = null;
		try {
			List<BeneficiaryDto> beneficiaryDtos = paymentRequestForm.getBeneficiaryDto();
			if (!MisUtility.ifEmpty(beneficiaryDtos)) {
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmss");
				datetime = ft.format(dNow);
				approvalNo = datetime + "_";
				payOrderNumber = paymentRequestForm.getDistrict() + "/" + paymentRequestForm.getBlock() + "/"
						+ paymentRequestForm.getVillage() + "/";

				beneficiaryPaymentBeans = paymentRequestDao.getPaymentDetails(paymentRequestForm);

				if (!MisUtility.ifEmpty(beneficiaryPaymentBeans)) {
					for (BeneficiaryDto dto : beneficiaryPaymentBeans) {
						requestId = dto.getRequestDetailId();
					}
					requestId = requestId + 1;
				} else {
					requestId = paymentRequestId + 1;
				}

				beneficiaryPaymentDetailsBeans = new ArrayList<BeneficiaryPaymentDetailsBean>();
				for (BeneficiaryDto dto : beneficiaryDtos) {
					BeneficiaryPaymentDetailsBean beneficiaryPaymentDetailsBean = new BeneficiaryPaymentDetailsBean();
					if (dto.getIsSelected().equalsIgnoreCase(MISConstants.CHECKED)) {
						beneficiaryPaymentDetailsBean.setPaymetRequestId(paymentRequestId);
						beneficiaryPaymentDetailsBean.setApprovalNo((approvalNo + requestId));
						beneficiaryPaymentDetailsBean.setPayOrderNo((payOrderNumber + requestId));
						beneficiaryPaymentDetailsBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
						beneficiaryPaymentDetailsBean.setAmount(dto.getAmount());
						beneficiaryPaymentDetailsBean.setProgressStageId(dto.getProgressStageId());
						beneficiaryPaymentDetailsBean
								.setCrtByUsr(Integer.parseInt(String.valueOf(paymentRequestForm.getLoginUser())));
						beneficiaryPaymentDetailsBean.setBeneficiaryId(dto.getBeneficiaryId());
						beneficiaryPaymentDetailsBeans.add(beneficiaryPaymentDetailsBean);
						beneficiaryPaymentDetailsBean.setIsDelete(Integer.parseInt(MISConstants.ZERO));
						requestId++;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return beneficiaryPaymentDetailsBeans;
	}

	private BeneficiaryPaymentBean populateBeneficiaryPaymentBean(PaymentRequestForm paymentRequestForm) {

		BeneficiaryPaymentBean beneficiaryPaymentBeans = null;
		try {
			beneficiaryPaymentBeans = new BeneficiaryPaymentBean();
			String billNumber = getBillNumber(paymentRequestForm);
			beneficiaryPaymentBeans.setBillno(billNumber);
			beneficiaryPaymentBeans.setCrtByUsr(Integer.parseInt(String.valueOf(paymentRequestForm.getLoginUser())));
			beneficiaryPaymentBeans.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			beneficiaryPaymentBeans.setLyingWithUser(Integer.parseInt(MISConstants.ZERO));
			beneficiaryPaymentBeans.setIsSubmit(Integer.parseInt(MISConstants.ZERO));
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return beneficiaryPaymentBeans;
	}

	private List<String> getAutoGeneratedNumberLst(PaymentRequestForm paymentRequestForm, int paymentRequestId) {
		String approvalNo = "";
		String payOrderNumber = "";
		List<String> autoNumbers = null;
		try {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmss");
			String datetime = ft.format(dNow);
			approvalNo = datetime + "_" + paymentRequestId;
			payOrderNumber = paymentRequestForm.getDistrict() + "/" + paymentRequestForm.getBlock() + "/"
					+ paymentRequestForm.getVillage() + "/" + paymentRequestId;

			autoNumbers = new ArrayList<String>();
			autoNumbers.add(approvalNo);
			autoNumbers.add(payOrderNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autoNumbers;
	}

	private String getBillNumber(PaymentRequestForm paymentRequestForm) {
		List<BeneficiaryDto> beneficiaryPaymentBeans = null;
		String paymentRequestIds = null;
		String billNumber = "";
		try {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmss");
			String datetime = ft.format(dNow);
			beneficiaryPaymentBeans = paymentRequestDao.getPayment(paymentRequestForm);
			if (!MisUtility.ifEmpty(beneficiaryPaymentBeans)) {
				for (BeneficiaryDto dto : beneficiaryPaymentBeans) {
					int paymetRequestId = dto.getPaymetRequestId();
					paymentRequestIds = String.valueOf(paymetRequestId);
				}
			} else {
				paymentRequestIds = MISConstants.ONE;
			}
			billNumber = MISConstants.SAN + datetime + "_" + (Integer.parseInt(paymentRequestIds) + 1);

		} catch (Exception e) {

		}

		return billNumber;
	}

}
