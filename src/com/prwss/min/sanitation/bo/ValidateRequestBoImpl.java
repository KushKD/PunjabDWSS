/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.dao.ValidateRequestDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ValidateRequestBoImpl implements ValidateRequestBo {

	private Logger log = Logger.getLogger(ValidateRequestBoImpl.class);

	private ValidateRequestDao validateRequestDao;
	


	public ValidateRequestDao getValidateRequestDao() {
		return validateRequestDao;
	}

	public void setValidateRequestDao(ValidateRequestDao validateRequestDao) {
		this.validateRequestDao = validateRequestDao;
	}

	@Override
	public List<BeneficiaryDto> getFreezedSurvey() throws MISException{
		List<BeneficiaryDto> beneficiaryDtos=null;
		try{
			System.out.println("inside bo----------");
			beneficiaryDtos=validateRequestDao.getFreezedSurvey();
			
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return beneficiaryDtos;
	}

	@Override
	public boolean saveEmpSurvey(String surveyId,Long entBy) throws MISException {
		
		List<BeneficiaryDto> beneficiaryDtos=null;
		List<BeneficiaryDto> beneficiaryDtoss=null;
		List<BeneficiaryDto> beneficiaryDt=null;
		boolean status=false;
		try{
			beneficiaryDtos=validateRequestDao.getSurveyDetails(surveyId);
			if(!MisUtility.ifEmpty(beneficiaryDtos)){
				beneficiaryDt=new ArrayList<BeneficiaryDto>();
				for(BeneficiaryDto beneficiaryDto:beneficiaryDtos){
					beneficiaryDtoss=validateRequestDao.getEmployeeDetails(beneficiaryDto);
					if(!MisUtility.ifEmpty(beneficiaryDtoss)){
						for(BeneficiaryDto bnfDto:beneficiaryDtoss){
							beneficiaryDt.add(bnfDto);
						}
					}
					
				}
				if(!MisUtility.ifEmpty(beneficiaryDt)){
					List<SurveyValidationRequest> surveyValidationRequests=populateRequest(beneficiaryDt,entBy);
					status=validateRequestDao.saveSurveyEmpDetails(surveyValidationRequests);
				}
			}
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return status;
	}
	
	private List<SurveyValidationRequest> populateRequest(List<BeneficiaryDto> beneficiaryDt,Long entBy){
		List<SurveyValidationRequest> surveyValidationRequest=new ArrayList<SurveyValidationRequest>();
		if(!MisUtility.ifEmpty(beneficiaryDt)){
			for(BeneficiaryDto beneficiaryDto:beneficiaryDt){
				SurveyValidationRequest surveyValidationRequest2=new SurveyValidationRequest();
				surveyValidationRequest2.setLyingWithUsr(beneficiaryDto.getReviewAuthority());
				surveyValidationRequest2.setSurveyId(beneficiaryDto.getSurveyReviewId());
				surveyValidationRequest2.setUserId(Integer.parseInt(String.valueOf(entBy)));
				surveyValidationRequest2.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				surveyValidationRequest2.setCrtByUsr(Integer.parseInt(String.valueOf(entBy)));
				surveyValidationRequest.add(surveyValidationRequest2);
			}
		}
		return surveyValidationRequest;
	}

}
