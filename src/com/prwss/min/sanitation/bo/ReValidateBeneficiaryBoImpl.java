package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.bean.ValidateBeneficiaryBean;
import com.prwss.min.sanitation.dao.ReValidateBeneficiaryDao;
import com.prwss.min.sanitation.form.ReValidateBeneficiary;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ReValidateBeneficiaryBoImpl implements ReValidateBeneficiaryBo {

	private Logger log = Logger.getLogger(ReValidateBeneficiaryBoImpl.class);

	private ReValidateBeneficiaryDao reValidateBeneficiaryDao;

	public ReValidateBeneficiaryDao getReValidateBeneficiaryDao() {
		return reValidateBeneficiaryDao;
	}

	public void setReValidateBeneficiaryDao(ReValidateBeneficiaryDao reValidateBeneficiaryDao) {
		this.reValidateBeneficiaryDao = reValidateBeneficiaryDao;
	}

	@Override
	public List<BeneficiaryDto> getValidateBeneficiary(String surveyId,Long entBy) throws MISException {

		List<BeneficiaryDto> beneficiaryDtos = null;
		List<BeneficiaryDto> beneficiaryDtos2 = null;
		try {
			beneficiaryDtos = reValidateBeneficiaryDao.getValidateBeneficiary(surveyId,entBy);
			if (!MisUtility.ifEmpty(beneficiaryDtos)) {
				beneficiaryDtos2 = getBeneficiaryDto(beneficiaryDtos);
			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException();
		}
		return beneficiaryDtos2;
	}

	@Override
	public boolean saveBeneficiary(ReValidateBeneficiary validateBeneficiary) throws MISException {
		boolean flag = false;
		List<ValidateBeneficiaryBean> validateBeneficiaryBeans = null;
		try {
			validateBeneficiaryBeans = populateValidateBeneficiaryBeans(validateBeneficiary);
			flag = reValidateBeneficiaryDao.saveBeneficiary(validateBeneficiaryBeans);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return flag;
	}

	private List<ValidateBeneficiaryBean> populateValidateBeneficiaryBeans(ReValidateBeneficiary validateBeneficiary) {

		List<ValidateBeneficiaryBean> validateBeneficiaryBeans = null;
		try {
			List<BeneficiaryDto> beneficiaryDto = validateBeneficiary.getBeneficiaryDto();
			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				validateBeneficiaryBeans = new ArrayList<ValidateBeneficiaryBean>();
				for (BeneficiaryDto beneficiaryDtos : beneficiaryDto) {
					if (beneficiaryDtos.getIsSelected().equalsIgnoreCase(MISConstants.CHECKED)) {
						ValidateBeneficiaryBean validateBeneficiaryBean = new ValidateBeneficiaryBean();
						if (beneficiaryDtos.getUpdateStatus().equalsIgnoreCase(MISConstants.REMOVE)) {
							validateBeneficiaryBean.setAction(MISConstants.RE);
						} else {
							validateBeneficiaryBean.setAction(beneficiaryDtos.getUpdateStatus());
						}
						validateBeneficiaryBean.setBeneficiaryid(beneficiaryDtos.getBeneficiaryId());
						validateBeneficiaryBean.setRemarks(beneficiaryDtos.getRemarks());
						validateBeneficiaryBean.setValidationRequestId(beneficiaryDtos.getValidationRequestId());
						validateBeneficiaryBean.setUserId(validateBeneficiary.getEntBy());
						validateBeneficiaryBeans.add(validateBeneficiaryBean);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validateBeneficiaryBeans;
	}

	private List<BeneficiaryDto> getBeneficiaryDto(List<BeneficiaryDto> beneficiaryDtos) {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			beneficiaryDto = new ArrayList<BeneficiaryDto>();
			for (BeneficiaryDto dto : beneficiaryDtos) {
				BeneficiaryDto beneficiaryDto2 = new BeneficiaryDto();
				beneficiaryDto2.setName(dto.getName());
				beneficiaryDto2.setFatherSpouseName(dto.getFatherSpouseName());
				beneficiaryDto2.setElectConnNumber(dto.getElectConnNumber());
				beneficiaryDto2.setAadharNo(dto.getAadharNo());
				if (dto.getAction().equalsIgnoreCase(MISConstants.AP)) {
					beneficiaryDto2.setAction(MISConstants.AS_PER_BASELINE);
				} else if (dto.getAction().equalsIgnoreCase(MISConstants.RE)) {
					beneficiaryDto2.setAction(MISConstants.REMOVE);
				}
				else{
					beneficiaryDto2.setAction(dto.getAction());
				}
				beneficiaryDto2.setRemarks(dto.getRemarks());
				beneficiaryDto2.setBeneficiaryId(dto.getBeneficiaryId());
				beneficiaryDto2.setValidationRequestId(dto.getValidationRequestId());
				beneficiaryDto.add(beneficiaryDto2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beneficiaryDto;

	}

	@Override
	public List<BeneficiaryDto> getValidatedBeneficiary(String surveyId, Long entBy) throws MISException {
		List<BeneficiaryDto> beneficiaryDtos = null;
		List<BeneficiaryDto> beneficiaryDtos2 = null;
		List<SurveyValidationRequest> surveyValidationRequests=null;
		try {
			surveyValidationRequests=reValidateBeneficiaryDao.findUser(surveyId, entBy);
			if(!MisUtility.ifEmpty(surveyValidationRequests)){
				for(SurveyValidationRequest request:surveyValidationRequests){
					beneficiaryDtos = reValidateBeneficiaryDao.getValidatedBeneficiary(surveyId,Long.parseLong(String.valueOf(request.getLyingWithUsr())));
				}
				if (!MisUtility.ifEmpty(beneficiaryDtos)) {
					beneficiaryDtos2 = getBeneficiaryDto(beneficiaryDtos);
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException();
		}
		return beneficiaryDtos2;
	}

	@Override
	public List<BeneficiaryDto> getReValidatedBeneficiary(String surveyId, Long entBy) throws MISException {
		List<BeneficiaryDto> beneficiaryDtos = null;
		List<BeneficiaryDto> beneficiaryDtos2 = null;
		List<SurveyValidationRequest> surveyValidationRequests=null;
		try {
			surveyValidationRequests=reValidateBeneficiaryDao.findUser(surveyId, entBy);
			if(!MisUtility.ifEmpty(surveyValidationRequests)){
				for(SurveyValidationRequest request:surveyValidationRequests){
					beneficiaryDtos = reValidateBeneficiaryDao.getValidatedBeneficiary(surveyId,Long.parseLong(String.valueOf(request.getUserId())));
				}
				if (!MisUtility.ifEmpty(beneficiaryDtos)) {
					beneficiaryDtos2 = getBeneficiaryDto(beneficiaryDtos);
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException();
		}
		return beneficiaryDtos2;
	}
	

}
