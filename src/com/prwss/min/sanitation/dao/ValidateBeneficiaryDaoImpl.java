/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BaseLineMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.bean.ValidateBeneficiaryBean;
import com.prwss.min.sanitation.form.ValidateBeneficiaryForm;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
public class ValidateBeneficiaryDaoImpl extends HibernateDaoSupport implements ValidateBeneficiaryDao {

	private Logger log = Logger.getLogger(ValidateBeneficiaryDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getBeneficiaryDetails(ValidateBeneficiaryForm validateBeneficiaryForm)
			throws DataAccessException {

		List<BeneficiaryDto> beneficiaryDto = null;
		try {

			DetachedCriteria subquery1 = DetachedCriteria.forClass(ValidateBeneficiaryBean.class,
					"validateBeneficiaryBean");
			subquery1.createAlias("validateBeneficiaryBean.surveyValidationRequest", "surveyValidationRequest");
			subquery1.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			subquery1.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(validateBeneficiaryForm.getSurveyId())));

			subquery1.setProjection(Projections.property("validateBeneficiaryBean.beneficiaryid").as("beneficiaryId"));

			DetachedCriteria subquery = DetachedCriteria.forClass(BaseLineMasterBean.class);
			subquery.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			subquery.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			subquery.add(Restrictions.eq("surveyId", Integer.parseInt(validateBeneficiaryForm.getSurveyId())));
			subquery.add(Property.forName("beneficiaryEntryBean.beneficieryId").notIn(subquery1));
			subquery.setProjection(Projections.property("beneficiaryId").as("beneficiaryId"));

			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			criteria.createAlias("beneficiaryEntryBean.combodetailReligion", "combodetailReligion");
			criteria.createAlias("beneficiaryEntryBean.locationDetailBean", "locationDetailBean");

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Property.forName("beneficiaryEntryBean.beneficieryId").in(subquery));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));

			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getValidationRequstId(String surveyId) throws DataAccessException {

		List<BeneficiaryDto> beneficiaryDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SurveyValidationRequest.class,
					"surveyValidationRequest");

			criteria.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			criteria.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.max("surveyValidationRequest.validationRequestId"), "validationRequestId")
					.add(Projections.groupProperty("surveyValidationRequest.surveyId")));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDtos = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}

		return beneficiaryDtos;
	}

	@Override
	public boolean saveBeneficiaryDetails(List<ValidateBeneficiaryBean> validateBeneficiaryBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(validateBeneficiaryBeans);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getBeneficiary(String surveyId) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria subquery1 = DetachedCriteria.forClass(ValidateBeneficiaryBean.class,
					"validateBeneficiaryBean");
			subquery1.createAlias("validateBeneficiaryBean.surveyValidationRequest", "surveyValidationRequest");
			subquery1.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			subquery1.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			subquery1.setProjection(Projections.property("validateBeneficiaryBean.beneficiaryid").as("beneficiaryId"));

			DetachedCriteria beneficiaryIdSubquery = DetachedCriteria.forClass(BaseLineMasterBean.class);
			beneficiaryIdSubquery.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			beneficiaryIdSubquery.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			beneficiaryIdSubquery.add(Restrictions.eq("surveyId", Integer.parseInt(surveyId)));
			beneficiaryIdSubquery.setProjection(Projections.property("beneficiaryId").as("beneficiaryId"));
			DetachedCriteria districtIdSubquery = DetachedCriteria.forClass(BaseLineMasterBean.class);
			districtIdSubquery.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			districtIdSubquery.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			districtIdSubquery.add(Restrictions.eq("surveyId", Integer.parseInt(surveyId)));
			districtIdSubquery.setProjection(Projections.distinct(Projections.property("districtId").as("districtId")));
			DetachedCriteria blockIdSubquery = DetachedCriteria.forClass(BaseLineMasterBean.class);
			blockIdSubquery.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			blockIdSubquery.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			blockIdSubquery.add(Restrictions.eq("surveyId", Integer.parseInt(surveyId)));
			blockIdSubquery.setProjection(Projections.distinct(Projections.property("blockId").as("blockId")));
			DetachedCriteria villageIdSubquery = DetachedCriteria.forClass(BaseLineMasterBean.class);
			villageIdSubquery.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			villageIdSubquery.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			villageIdSubquery.add(Restrictions.eq("surveyId", Integer.parseInt(surveyId)));
			villageIdSubquery.setProjection(Projections.distinct(Projections.property("villageId").as("villageId")));
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Property.forName("beneficiaryEntryBean.beneficieryId").notIn(beneficiaryIdSubquery));
			criteria.add(Property.forName("beneficiaryEntryBean.beneficieryId").notIn(subquery1));
			criteria.add(Property.forName("beneficiaryEntryBean.districtId").in(districtIdSubquery));
			criteria.add(Property.forName("beneficiaryEntryBean.blockId").in(blockIdSubquery));
			criteria.add(Property.forName("beneficiaryEntryBean.villageId").in(villageIdSubquery));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getAddedBeneficiary(String surveyId,Long enteredBy) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("enteredBy--------------"+enteredBy);
		List<BeneficiaryDto> beneficiaryDto = null;
		System.out.println("jhsdch");
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(ValidateBeneficiaryBean.class, "validateBeneficiaryBean");
		criteria.createAlias("validateBeneficiaryBean.surveyValidationRequest", "surveyValidationRequest");
		criteria.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
		criteria.createAlias("validateBeneficiaryBean.beneficiaryEntryBean", "beneficiaryEntryBean");
		criteria.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
		criteria.add(Restrictions.eq("validateBeneficiaryBean.userId", Integer.parseInt(String.valueOf(enteredBy))));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
				.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
				.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
				.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
				.add(Projections.property("validateBeneficiaryBean.validationRequestId"), "validationRequestId")
				.add(Projections.property("validateBeneficiaryBean.remarks"), "remarks")
				.add(Projections.property("validateBeneficiaryBean.action"), "action")
				.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
				);
		criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
		
		beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyValidationRequest> getSurveyValidation(String validationRequest) throws DataAccessException {
		
		List<SurveyValidationRequest> surveyValidationRequests=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(SurveyValidationRequest.class);
			criteria.add(Restrictions.eq("validationRequestId", Integer.parseInt(validationRequest)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			//criteria.add(Restrictions.eq("lyingWithUsr", Integer.parseInt(MISConstants.ONE)));
			surveyValidationRequests=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return surveyValidationRequests;
	}

	@Override
	public boolean updateSurveyValidation(SurveyValidationRequest surveyValidationRequests)
			throws DataAccessException {
		try{
			getHibernateTemplate().update(surveyValidationRequests);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlowMasterBean> getEmployeeDetails(Integer userId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(WorkFlowMasterBean.class);
			criteria.add(Restrictions.eq("from_emp_id", userId));
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("subject_id", Integer.parseInt(MISConstants.TWO)));
			workFlowMasterBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return workFlowMasterBeans;
	}

}
