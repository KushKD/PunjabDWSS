/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.dao.ValidateBeneficiaryDao;
import com.prwss.min.sanitation.form.BeneficiaryApprovalForm;
import com.prwss.min.sanitation.form.ReValidateBeneficiary;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BeneficiaryApprovalBoImpl implements BeneficiaryApprovalBo {
	private Logger log = Logger.getLogger(ValidateBeneficiaryBoImpl.class);

	private ValidateBeneficiaryDao validateBeneficiaryDao;

	public ValidateBeneficiaryDao getValidateBeneficiaryDao() {
		return validateBeneficiaryDao;
	}

	public void setValidateBeneficiaryDao(ValidateBeneficiaryDao validateBeneficiaryDao) {
		this.validateBeneficiaryDao = validateBeneficiaryDao;
	}

	@Override
	public boolean updateValidationRequestToReturn(BeneficiaryApprovalForm forwardBeneficiaryForm) throws MISException {
		Set<String> surveyValidations = null;
		List<SurveyValidationRequest> surveyValidationRequests = null;
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		boolean status = false;
		try {
			surveyValidations = populateRequestIds(forwardBeneficiaryForm);
			if (!MisUtility.ifEmpty(surveyValidations)) {
				for (String validateRequest : surveyValidations) {
					surveyValidationRequests = validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if (!MisUtility.ifEmpty(surveyValidationRequests)) {
						for (SurveyValidationRequest request : surveyValidationRequests) {
							workFlowMasterBeans = validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
								request.setUserId(workFlowMasterBeans.get(0).getFrom_emp_id());
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_ret());
								status = validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}

					}
				}

			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean updateRequest(BeneficiaryApprovalForm forwardBeneficiaryForm) throws MISException {
		Set<String> surveyValidations = null;
		List<SurveyValidationRequest> surveyValidationRequests = null;
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		boolean status = false;
		try {
			surveyValidations = populateRequestIds(forwardBeneficiaryForm);
			if (!MisUtility.ifEmpty(surveyValidations)) {
				for (String validateRequest : surveyValidations) {
					surveyValidationRequests = validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if (!MisUtility.ifEmpty(surveyValidationRequests)) {
						for (SurveyValidationRequest request : surveyValidationRequests) {
							workFlowMasterBeans = validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
								request.setUserId(workFlowMasterBeans.get(0).getFrom_emp_id());
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_rti());
								status = validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}

					}
				}

			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean updateValidationRequestToInitiator(BeneficiaryApprovalForm forwardBeneficiaryForm)
			throws MISException {
		Set<String> surveyValidations = null;
		List<SurveyValidationRequest> surveyValidationRequests = null;
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		boolean status = false;
		try {
			surveyValidations = populateRequestIds(forwardBeneficiaryForm);
			if (!MisUtility.ifEmpty(surveyValidations)) {
				for (String validateRequest : surveyValidations) {
					surveyValidationRequests = validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if (!MisUtility.ifEmpty(surveyValidationRequests)) {
						for (SurveyValidationRequest request : surveyValidationRequests) {
							workFlowMasterBeans = validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
								request.setUserId(workFlowMasterBeans.get(0).getFrom_emp_id());
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_rti());
								request.setIsSubmitted(Integer.parseInt(MISConstants.ONE));
								status = validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}

					}
				}

			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}

	private Set<String> populateRequestIds(BeneficiaryApprovalForm forwardBeneficiaryForm) throws MISException {
		Set<String> surveyValidationRequests = null;
		try {
			surveyValidationRequests = new HashSet<String>();
			if (MisUtility.ifEmpty(forwardBeneficiaryForm)) {
				List<BeneficiaryDto> beneficiaryDtos = forwardBeneficiaryForm.getBeneficiaryDto();
				for (BeneficiaryDto requestId : beneficiaryDtos) {
					surveyValidationRequests.add(String.valueOf(requestId.getValidationRequestId()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return surveyValidationRequests;
	}

}
