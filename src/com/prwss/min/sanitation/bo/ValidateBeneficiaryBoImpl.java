/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.bean.ValidateBeneficiaryBean;
import com.prwss.min.sanitation.dao.ValidateBeneficiaryDao;
import com.prwss.min.sanitation.form.ForwardBeneficiaryForm;
import com.prwss.min.sanitation.form.ReValidateBeneficiary;
import com.prwss.min.sanitation.form.ValidateBeneficiaryForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ValidateBeneficiaryBoImpl implements ValidateBeneficiaryBo {

	private Logger log = Logger.getLogger(ValidateBeneficiaryBoImpl.class);

	private ValidateBeneficiaryDao validateBeneficiaryDao;

	public ValidateBeneficiaryDao getValidateBeneficiaryDao() {
		return validateBeneficiaryDao;
	}

	public void setValidateBeneficiaryDao(ValidateBeneficiaryDao validateBeneficiaryDao) {
		this.validateBeneficiaryDao = validateBeneficiaryDao;
	}

	@Override
	public List<BeneficiaryDto> getBeneficiaryDetails(ValidateBeneficiaryForm validateBeneficiaryForm)
			throws MISException {
		List<BeneficiaryDto> beneficiaryDtos = null;
		try {
			if (MisUtility.ifEmpty(validateBeneficiaryForm)) {
				beneficiaryDtos = validateBeneficiaryDao.getBeneficiaryDetails(validateBeneficiaryForm);
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return beneficiaryDtos;
	}

	@Override
	public boolean saveBeneficiaryDetails(JSONArray jsonArray, String surveyId, String entBy) throws MISException {
		List<BeneficiaryDto> beneficiaryDtos = null;
		List<ValidateBeneficiaryBean> validateBeneficiaryBeans = null;
		try {

			if (MisUtility.ifEmpty(surveyId)) {
				beneficiaryDtos = validateBeneficiaryDao.getValidationRequstId(surveyId);
				if (!MisUtility.ifEmpty(beneficiaryDtos)) {
					validateBeneficiaryBeans = populateBeneficiaryBean(jsonArray, beneficiaryDtos, entBy);
					validateBeneficiaryDao.saveBeneficiaryDetails(validateBeneficiaryBeans);
				}
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
		}
		return true;
	}
	
	@Override
	public boolean updateValidationRequest(String[] validateRequestId,String entBy) throws MISException {
		
		Set<String> surveyValidations=null;
		List<SurveyValidationRequest> surveyValidationRequests=null;
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		boolean status=false;
		try{
			surveyValidations=populateRequestId(validateRequestId);
			if(!MisUtility.ifEmpty(surveyValidations)){
				for(String validateRequest:surveyValidations){
					surveyValidationRequests=validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if(!MisUtility.ifEmpty(surveyValidationRequests)){
						for(SurveyValidationRequest request:surveyValidationRequests){
							workFlowMasterBeans=validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if(!MisUtility.ifEmpty(workFlowMasterBeans)){
								request.setUserId(Integer.parseInt(entBy));
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_fwd());
								status=validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}
						
					}
				}
				
			}
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}
	
	@Override
	public boolean updateValidationRequestToReturn(ReValidateBeneficiary forwardBeneficiaryForm) throws MISException {
		Set<String> surveyValidations=null;
		List<SurveyValidationRequest> surveyValidationRequests=null;
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		boolean status=false;
		try{
			surveyValidations=populateRequestIds(forwardBeneficiaryForm);
			if(!MisUtility.ifEmpty(surveyValidations)){
				for(String validateRequest:surveyValidations){
					surveyValidationRequests=validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if(!MisUtility.ifEmpty(surveyValidationRequests)){
						for(SurveyValidationRequest request:surveyValidationRequests){
							workFlowMasterBeans=validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if(!MisUtility.ifEmpty(workFlowMasterBeans)){
								request.setUserId(workFlowMasterBeans.get(0).getFrom_emp_id());
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_ret());
								status=validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}
						
					}
				}
				
			}
		}catch(DataAccessException e){
			throw new MISException(e);
		}
		return status;
	}

	
	@Override
	public boolean updateRequest(ReValidateBeneficiary forwardBeneficiaryForm) throws MISException {
		Set<String> surveyValidations=null;
		List<SurveyValidationRequest> surveyValidationRequests=null;
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		boolean status=false;
		try{
			surveyValidations=populateRequestIds(forwardBeneficiaryForm);
			if(!MisUtility.ifEmpty(surveyValidations)){
				for(String validateRequest:surveyValidations){
					surveyValidationRequests=validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if(!MisUtility.ifEmpty(surveyValidationRequests)){
						for(SurveyValidationRequest request:surveyValidationRequests){
							workFlowMasterBeans=validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if(!MisUtility.ifEmpty(workFlowMasterBeans)){
								request.setUserId(workFlowMasterBeans.get(0).getFrom_emp_id());
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_fwd());
								status=validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}
						
					}
				}
				
			}
		}catch(DataAccessException e){
			throw new MISException(e);
		}
		return status;
	}
	
	
	@Override
	public boolean updateValidationRequestToInitiator(ReValidateBeneficiary forwardBeneficiaryForm)
			throws MISException {
		Set<String> surveyValidations=null;
		List<SurveyValidationRequest> surveyValidationRequests=null;
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		boolean status=false;
		try{
			surveyValidations=populateRequestIds(forwardBeneficiaryForm);
			if(!MisUtility.ifEmpty(surveyValidations)){
				for(String validateRequest:surveyValidations){
					surveyValidationRequests=validateBeneficiaryDao.getSurveyValidation(validateRequest);
					if(!MisUtility.ifEmpty(surveyValidationRequests)){
						for(SurveyValidationRequest request:surveyValidationRequests){
							workFlowMasterBeans=validateBeneficiaryDao.getEmployeeDetails(request.getLyingWithUsr());
							if(!MisUtility.ifEmpty(workFlowMasterBeans)){
								request.setUserId(workFlowMasterBeans.get(0).getFrom_emp_id());
								request.setLyingWithUsr(workFlowMasterBeans.get(0).getTo_emp_id_rti());
								request.setIsSubmitted(Integer.parseInt(MISConstants.ONE));
								status=validateBeneficiaryDao.updateSurveyValidation(request);
							}
						}
						
					}
				}
				
			}
		}catch(DataAccessException e){
			throw new MISException(e);
		}
		return status;
	}

	private List<ValidateBeneficiaryBean> populateBeneficiaryBean(JSONArray jsonArray,
			List<BeneficiaryDto> beneficiaryDtos, String entBy) throws MISException {

		List<ValidateBeneficiaryBean> validateBeneficiaryBeans = null;
		try {
			validateBeneficiaryBeans = new ArrayList<ValidateBeneficiaryBean>();
			System.out.println("before------" + jsonArray);
			JSONArray jsonArray1 = (JSONArray) jsonArray.get(0);
			for (BeneficiaryDto dto : beneficiaryDtos) {
				for (int i = 0; i < jsonArray1.size(); i++) {
					ValidateBeneficiaryBean validateBeneficiaryBean = new ValidateBeneficiaryBean();
					JSONObject jsonObject = (JSONObject) jsonArray1.get(i);
					validateBeneficiaryBean.setRemarks(String.valueOf(jsonObject.get("remarks")));
					if (String.valueOf(jsonObject.get("status")).equalsIgnoreCase(MISConstants.AS_PER_BASELINE)) {
						validateBeneficiaryBean.setAction(MISConstants.AP);
					} else if (String.valueOf(jsonObject.get("status")).equalsIgnoreCase(MISConstants.REMOVE)) {
						validateBeneficiaryBean.setAction(MISConstants.RE);
					}
					validateBeneficiaryBean.setValidationRequestId(dto.getValidationRequestId());
					if (MisUtility.ifEmpty(entBy))
						validateBeneficiaryBean.setUserId(Integer.parseInt(entBy));
					if (MisUtility.ifEmpty(jsonObject.get("beneficiary"))) {
						validateBeneficiaryBean
								.setBeneficiaryid(Integer.parseInt(jsonObject.get("beneficiary").toString()));
					}
					validateBeneficiaryBeans.add(validateBeneficiaryBean);
				}
			}
		} catch (Exception e) {
			throw new MISException(e);
		}
		return validateBeneficiaryBeans;
	}

	private Set<String> populateRequestId(String[] validateRequestId)throws MISException{
		Set<String> surveyValidationRequests=null;
		try{
			surveyValidationRequests=new HashSet<String>();
			if(MisUtility.ifEmpty(validateRequestId)){
				for(String requestId:validateRequestId){
					surveyValidationRequests.add(requestId);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return surveyValidationRequests;
	}
	
	private Set<String> populateRequestIds(ReValidateBeneficiary forwardBeneficiaryForm)throws MISException{
		Set<String> surveyValidationRequests=null;
		try{
			surveyValidationRequests=new HashSet<String>();
			if(MisUtility.ifEmpty(forwardBeneficiaryForm)){
				List<BeneficiaryDto> beneficiaryDtos=forwardBeneficiaryForm.getBeneficiaryDtos();
				for(BeneficiaryDto requestId:beneficiaryDtos){
					surveyValidationRequests.add(String.valueOf(requestId.getValidationRequestId()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return surveyValidationRequests;
	}

	
	
}
