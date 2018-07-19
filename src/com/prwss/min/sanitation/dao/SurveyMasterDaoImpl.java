package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.sanitation.bean.ComboDetailPoJo;
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.min.sanitation.form.SurveyMasterForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;


public class SurveyMasterDaoImpl extends HibernateDaoSupport implements SurveyMasterDao {
	
	private Logger log = Logger.getLogger(SurveyMasterDaoImpl.class);
	
	@Override
	public boolean saveSurveyMasterData(SurveyMasterBean surveyMasterBean)
			throws DataAccessException {
		try {
			System.out.println("DAO" + surveyMasterBean.toString());
			getHibernateTemplate().save(surveyMasterBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;
	}

	@Override
	public List<ComboDetailPoJo> getHabitationFromCombo(String comboId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<ComboDetailPoJo> getHabitationFromCombo(String comboId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatDetailsDto> getGramPanchayatDetails(ViewRegistrationsForm form)
			throws DataAccessException {
		// TODO Auto-generated method stub
		List<GramPanchayatDetailsDto> gramPanchayatDetailsDtos=null;
		try{
			
			Criteria criteria=getSession().createCriteria(GramPanchayatRegisterBean.class, "gramPanchayatRegisterBean");
			criteria.createAlias("gramPanchayatRegisterBean.combodetail", "combodetail");
			criteria.createAlias("gramPanchayatRegisterBean.locationDetailBean", "locationDetailBean");
			
			if(MisUtility.ifEmpty(form.getDistrict()))
			criteria.add(Restrictions.eq("district_id", Integer.parseInt(form.getDistrict())));
			
			if(MisUtility.ifEmpty(form.getBlock()))
			criteria.add(Restrictions.eq("block_id", Integer.parseInt(form.getBlock())));
			
			if(MisUtility.ifEmpty(form.getVillage()))
			criteria.add(Restrictions.eq("village_id",Integer.parseInt( form.getVillage())));
			
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("locationDetailBean.locationName"),"villageName")
					.add(Projections.property("combodetail.cmb_name"),"habitationName")
					.add(Projections.property("gramPanchayatRegisterBean.family_id"),"family_id")
					.add(Projections.property("gramPanchayatRegisterBean.family_head"),"family_head")
					.add(Projections.property("gramPanchayatRegisterBean.fat_hus_name"),"fat_hus_name")
					.add(Projections.property("combodetail.cmb_name"),"gender"));
					
			gramPanchayatDetailsDtos=criteria.setResultTransformer(Transformers.aliasToBean(GramPanchayatDetailsDto.class)).list();
					
					
			
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return gramPanchayatDetailsDtos;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyMasterBean> getSurveyMasterByPagination()
			throws DataAccessException {
		// TODO Auto-generated method stub

		List<SurveyMasterBean> surveyMaster = null;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(SurveyMasterBean.class);
			criteria.add(Restrictions.eq("active_flag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			surveyMaster = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return surveyMaster;
	}
	
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<SurveyMasterBean> getSurveyMasterData(SurveyMasterForm surveymasterform)
			throws DataAccessException {
		
		List<SurveyMasterBean> surveyMasterBeans=null;
		
		try {

		DetachedCriteria criteria=DetachedCriteria.forClass(SurveyMasterBean.class);
		
		if(MisUtility.ifEmpty(surveymasterform.getSurveyName()))
			criteria.add(Restrictions.eq("survey_name",surveymasterform.getSurveyName()));
		
		
		surveyMasterBeans=getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return surveyMasterBeans;
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyMasterBean> findSurveyCollection(SurveyMasterForm surveymasterform)
			throws DataAccessException {
		List<SurveyMasterBean> surveyMasterBeans = null;
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(SurveyMasterBean.class);
		/*if(MisUtility.ifEmpty(surveymasterform.getSurveyID())){
			criteria.add(Restrictions.eq("survey_id", Integer.parseInt(surveymasterform.getSurveyID())));*/
		if(MisUtility.ifEmpty(surveymasterform.getSurveyName())){
				criteria.add(Restrictions.eq("survey_name",surveymasterform.getSurveyName()));
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		}
			
		
		surveyMasterBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return surveyMasterBeans;
	}
	
	
	@Override
	public boolean UpdateSurveyMaster(SurveyMasterBean surveyMasterBean)
			throws DataAccessException {
			try{
			
			if(MisUtility.ifEmpty(surveyMasterBean)){
				getHibernateTemplate().update(surveyMasterBean);
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
			return true;
	}

	/*@Override
	public boolean UpdateSurveyMaster(SurveyMasterBean surveyMasterBean) {
		try{
			getHibernateTemplate().update(surveyMasterBean);
		}catch(DataAccessException e){
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}*/

}
