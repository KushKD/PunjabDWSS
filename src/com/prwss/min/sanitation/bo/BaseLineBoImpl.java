package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.BaseLineMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.dao.BaseLineDao;
import com.prwss.min.sanitation.form.BaseLineForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BaseLineBoImpl implements BaseLineBo {

	private Logger log = Logger.getLogger(BaseLineBoImpl.class);

	private BaseLineDao baseLineDao;

	public BaseLineDao getBaseLineDao() {
		return baseLineDao;
	}

	public void setBaseLineDao(BaseLineDao baseLineDao) {
		this.baseLineDao = baseLineDao;
	}

	@Override
	public List<BeneficiaryDto> getBeneficiaryDetails(BaseLineForm baseLineForm) throws MISException {

		List<BeneficiaryDto> beneficiaryDtos = null;
		try {
			if (MisUtility.ifEmpty(baseLineForm)) {
				beneficiaryDtos = baseLineDao.getBeneficiaryDetail(baseLineForm);
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return beneficiaryDtos;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean saveBeneficiaryDetails(BaseLineForm baseLineForm, String beneficiaryId) throws MISException {

		List<BaseLineMasterBean> baseLineMasterBean = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(baseLineForm)) {
				baseLineMasterBean = populateBaseLineSurveyBean(baseLineForm, beneficiaryId);
				if (!MisUtility.ifEmpty(baseLineMasterBean)) {
					status = baseLineDao.saveBaseLineSurvey(baseLineMasterBean);
					
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return status;
	}

	
	
	
	private List<BaseLineMasterBean> populateBaseLineSurveyBean(BaseLineForm baseLineForm, String beneficiaryId) {
		List<BaseLineMasterBean> baseLineMasterBeans = null;
		BaseLineMasterBean baseLineMasterBean = null;
		try {
			baseLineMasterBeans = new ArrayList<BaseLineMasterBean>();
			String[] beneficiaryIdArr = beneficiaryId.split(",");

			for (String bnfcry : beneficiaryIdArr) {
				baseLineMasterBean = new BaseLineMasterBean();

				if (MisUtility.ifEmpty(baseLineForm.getSurveyId()))
					baseLineMasterBean.setSurveyId(Integer.parseInt(baseLineForm.getSurveyId()));

				if (MisUtility.ifEmpty(baseLineForm.getDistrict()))
					baseLineMasterBean.setDistrictId(Integer.parseInt(baseLineForm.getDistrict()));

				if (MisUtility.ifEmpty(baseLineForm.getBlock()))
					baseLineMasterBean.setBlockId(Integer.parseInt(baseLineForm.getBlock()));

				if (MisUtility.ifEmpty(baseLineForm.getVillage()))
					baseLineMasterBean.setVillageId(Integer.parseInt(baseLineForm.getVillage()));

				if (MisUtility.ifEmpty(baseLineForm.getVillage()))
					baseLineMasterBean.setBeneficiaryId(Integer.parseInt(bnfcry));

				baseLineMasterBean.setGramPanchayatId(baseLineForm.getGramPanchayatId());
				baseLineMasterBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
				baseLineMasterBean.setCrtByUsr(Integer.parseInt(String.valueOf(baseLineForm.getCreatedBy())));
				baseLineMasterBean.setIsSubmitted(Integer.parseInt(MISConstants.ONE));
				baseLineMasterBeans.add(baseLineMasterBean);
			}
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return baseLineMasterBeans;
	}

	@Override
	public List<BeneficiaryDto> getSurveyDetails(BaseLineForm baseLineForm) throws MISException {
		List<BeneficiaryDto> beneficiaryDtos = null;
		try {
			if (MisUtility.ifEmpty(baseLineForm)) {
				beneficiaryDtos = baseLineDao.getSurveyDetails(baseLineForm);
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return beneficiaryDtos;
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean updateSurvey(String surveyId,String baseLineId) throws MISException {
		List<BaseLineMasterBean> baseLineMasterBean = null;
		List<BaseLineMasterBean> baseLineMasterBeans = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(surveyId)) {
				Integer[] baseLine=getIntArr(baseLineId.split(","));
				baseLineMasterBean=baseLineDao.fetchSurveyDetails(baseLine);
				if(!MisUtility.ifEmpty(baseLineMasterBean)){
					baseLineMasterBeans = populateBaseLineSurveyBeans(baseLineMasterBean);
				}
				if (!MisUtility.ifEmpty(baseLineMasterBeans)) {
					status = baseLineDao.updateSurvey(baseLineMasterBean);
					
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	private List<BaseLineMasterBean> populateBaseLineSurveyBeans(List<BaseLineMasterBean> baseLineMasterBean) {
		
		List<BaseLineMasterBean> baseLineMasterBeans = null;
		try {
			baseLineMasterBeans = new ArrayList<BaseLineMasterBean>();
			baseLineMasterBeans=new ArrayList<BaseLineMasterBean>();
			for (BaseLineMasterBean baseLineMasterBeanss : baseLineMasterBean) {
				baseLineMasterBeanss.setIsFreezed(Integer.parseInt(MISConstants.ONE));
				baseLineMasterBeans.add(baseLineMasterBeanss);
			}
		}catch(Exception e){
			log.debug(e.getLocalizedMessage());
		}
		return baseLineMasterBeans;
	}
	
	private Integer[] getIntArr(String[] args){
		Integer[] result=new Integer[args.length];
		for(int i=0;i<args.length;i++){
			result[i]=Integer.parseInt(args[i]);
		}
		return result;
	}
}
