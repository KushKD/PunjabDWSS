package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.ComboDetailPoJo;
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.min.sanitation.form.SurveyMasterForm;


public interface SurveyMasterDao {

	public boolean saveSurveyMasterData(SurveyMasterBean surveyMasterBean) 	throws DataAccessException ;
	public List<ComboDetailPoJo> getHabitationFromCombo(String  comboId) 	throws DataAccessException ;
	//public List<SurveyMasterBean> getSurveyMasterData(SurveyMasterForm surveymasterform)throws DataAccessException;
	/*public List<GramPanchayatDetailsDto> getGramPanchayatDetails(ViewRegistrationsForm form) 	throws DataAccessException ;
	*/
	public List<SurveyMasterBean> getSurveyMasterByPagination() throws DataAccessException;
	public  boolean UpdateSurveyMaster(SurveyMasterBean surveyMasterBean)throws DataAccessException;
	public List<SurveyMasterBean>   findSurveyCollection(SurveyMasterForm surveymasterform)throws DataAccessException;

}
