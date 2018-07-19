package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.min.sanitation.dao.SurveyMasterDao;
import com.prwss.min.sanitation.form.SurveyMasterForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public  class SurveyMasterBoImpl extends AbstractPaginationMaster<SurveyMasterForm> implements SurveyMasterBO<SurveyMasterForm> {

	private Logger log = Logger.getLogger(SurveyMasterBoImpl.class);
	
	private SurveyMasterDao surveyMasterDao;
	
	

	public SurveyMasterDao getSurveyMasterDao() {
		return surveyMasterDao;
	}

	public void setSurveyMasterDao(SurveyMasterDao surveyMasterDao) {
		this.surveyMasterDao = surveyMasterDao;
	}

	@Override
	public boolean saveSurveyMasterData(SurveyMasterForm surveymasterform) throws MISException {
		
		SurveyMasterBean surveyMasterBean = null;
		boolean status=false;
		try {
				if (MisUtility.ifEmpty(surveymasterform)) {
					surveyMasterBean = populateSurveyMaster(surveymasterform);
				
				if(MisUtility.ifEmpty(surveyMasterBean)){
					status=surveyMasterDao.saveSurveyMasterData(surveyMasterBean);
				}
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}
	
	
	@Override
	public boolean updateSurvey(SurveyMasterForm surveyMasterForm) throws MISException{

		SurveyMasterBean surveyMasterLst = null;
		boolean status=false;
		try {
			
			if (MisUtility.ifEmpty(surveyMasterForm)) {
				
				if(MisUtility.ifEmpty(surveyMasterForm.getSurveyName())){
				List<SurveyMasterBean> masterBean=surveyMasterDao.findSurveyCollection(surveyMasterForm);
				if(!MisUtility.ifEmpty(masterBean)){
					for(SurveyMasterBean bean:masterBean){
						bean.setActive_flag(Integer.parseInt(MISConstants.INACTIVE_STATUS));
						status=surveyMasterDao.UpdateSurveyMaster(bean);
						}
					}	
				}
				surveyMasterLst = populateSurveyMaster(surveyMasterForm);
				if(MisUtility.ifEmpty(surveyMasterLst)){
					status=surveyMasterDao.saveSurveyMasterData(surveyMasterLst);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}
	
	

	private SurveyMasterBean populateSurveyMaster(SurveyMasterForm surveymasterform) {
		
		SurveyMasterBean bean = new SurveyMasterBean();
		
		if(MisUtility.ifEmpty(surveymasterform.getSurveyName())){
			bean.setSurvey_name(surveymasterform.getSurveyName());
		}
		
		if(MisUtility.ifEmpty(surveymasterform.getSurveyStatus())){
			bean.setSur_status(Integer.parseInt(surveymasterform.getSurveyStatus()));
		}
		
		if(MisUtility.ifEmpty(surveymasterform.getPlannedStartDate())){
			bean.setPlnd_strt_date(MisUtility.convertStringSqlDate(surveymasterform.getPlannedStartDate()));
		}
		
		if(MisUtility.ifEmpty(surveymasterform.getPlannedEndDate())){
			bean.setPlnd_end_date(MisUtility.convertStringSqlDate(surveymasterform.getPlannedEndDate()));
		}		

		if(MisUtility.ifEmpty(surveymasterform.getActualStartDate())){
			bean.setActl(MisUtility.convertStringSqlDate(surveymasterform.getActualStartDate()));
		}
		
		if(MisUtility.ifEmpty(surveymasterform.getActualEndDate())){
			bean.setActl_end_date(MisUtility.convertStringSqlDate(surveymasterform.getActualEndDate()));
		}
		
		if(MisUtility.ifEmpty(surveymasterform.getPurpose())){
			bean.setPurpose(surveymasterform.getPurpose());
		}
		
		if(MisUtility.ifEmpty(surveymasterform.getCreatedByUser())){
			bean.setCrt_by_usr(Integer.parseInt(String.valueOf(surveymasterform.getCreatedByUser())));
		}
		
		/*if(MisUtility.ifEmpty(surveymasterform.getLastUpdatedUser())){
			bean.setLst_updated_user(Integer.parseInt(surveymasterform.getLastUpdatedUser()));
		}*/
	
		bean.setActive_flag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
		
		
		return bean;
		
	}
	
	
	
	@Override
	public List<SurveyMasterForm> getSurveyMasterByPagination() {
		List<SurveyMasterBean> surveyMasterBean=null;
		List<SurveyMasterForm> surveyMasterForm=null;

		try{
			
			surveyMasterBean=surveyMasterDao.getSurveyMasterByPagination();
			
			if(!MisUtility.ifEmpty(surveyMasterBean)){
				surveyMasterForm=new ArrayList<SurveyMasterForm>();
				for(SurveyMasterBean bean:surveyMasterBean){
					
					SurveyMasterForm form=new SurveyMasterForm();
					
					form.setSurveyID(String.valueOf(bean.getSurvey_id()));
					
					form.setSurveyName(bean.getSurvey_name());
					
					form.setSurveyStatus(String.valueOf(bean.getSur_status()));
					
					form.setStatus(String.valueOf(bean.getActive_flag()));
					
					form.setPurpose(bean.getPurpose());
					
					if(MisUtility.ifEmpty(bean.getPlnd_strt_date())){
						form.setPlannedStartDate(MisUtility.convertDateString(bean.getPlnd_strt_date()));
					}
					if(MisUtility.ifEmpty(bean.getPlnd_end_date())){
						form.setPlannedEndDate(MisUtility.convertDateString(bean.getPlnd_end_date()));
					}
					if(MisUtility.ifEmpty(bean.getActl())){
						form.setActualStartDate(MisUtility.convertDateString(bean.getActl()));
					}
					if(MisUtility.ifEmpty(bean.getActl_end_date())){
						form.setActualEndDate(MisUtility.convertDateString(bean.getActl_end_date()));
					}
					
					surveyMasterForm.add(form);	
					}
				
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return surveyMasterForm;
	}

	@Override
	public List<SurveyMasterForm> getListBasedOnSearchParameter(String searchParameter,
			List<SurveyMasterForm> formList) {
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				List<SurveyMasterForm> locationMasterList = new ArrayList<SurveyMasterForm>();
				searchParameter = searchParameter.toUpperCase();
				for (SurveyMasterForm masterForm : formList) {
					/*if (masterForm.getBlock().toUpperCase().indexOf(searchParameter) != -1){
							locationMasterList.add(masterForm);
					}*/
				if(masterForm.getSurveyStatus().toUpperCase().indexOf(searchParameter) != -1){
					locationMasterList.add(masterForm);
					}
				if( masterForm.getSurveyName().toUpperCase().indexOf(searchParameter) != -1){
						locationMasterList.add(masterForm);
	
					}

				}
				formList = locationMasterList;
				locationMasterList = null;
			}
		} catch (Exception e) {

		}
		return formList;
	}
	
	

}
