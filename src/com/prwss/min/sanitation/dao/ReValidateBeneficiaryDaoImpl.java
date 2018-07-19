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

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.min.sanitation.bean.ValidateBeneficiaryBean;

/**
 * @author BH390738
 *
 */
public class ReValidateBeneficiaryDaoImpl extends HibernateDaoSupport implements ReValidateBeneficiaryDao {
	private Logger log = Logger.getLogger(ReValidateBeneficiaryDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getValidateBeneficiary(String surveyId, Long entBy) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria beneficiaryIdSubquery = DetachedCriteria.forClass(ValidateBeneficiaryBean.class,
					"validateBeneficiaryBean");
			beneficiaryIdSubquery.createAlias("validateBeneficiaryBean.surveyValidationRequest",
					"surveyValidationRequest");
			beneficiaryIdSubquery.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			beneficiaryIdSubquery.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			beneficiaryIdSubquery
					.add(Restrictions.eq("validateBeneficiaryBean.userId", Integer.parseInt(String.valueOf(entBy))));
			beneficiaryIdSubquery.setProjection(Projections.property("beneficiaryid").as("beneficiaryId"));

			DetachedCriteria criteria = DetachedCriteria.forClass(ValidateBeneficiaryBean.class,
					"validateBeneficiaryBean");
			criteria.createAlias("validateBeneficiaryBean.surveyValidationRequest", "surveyValidationRequest");
			criteria.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			criteria.createAlias("validateBeneficiaryBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			criteria.add(Property.forName("beneficiaryEntryBean.beneficieryId").notIn(beneficiaryIdSubquery));
			criteria.setProjection(
					Projections.projectionList()
							.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
							.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
							.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
							.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
							.add(Projections.property("validateBeneficiaryBean.validationRequestId"),
									"validationRequestId")
							.add(Projections.property("validateBeneficiaryBean.remarks"), "remarks")
							.add(Projections.property("validateBeneficiaryBean.action"), "action")
							.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));

			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return beneficiaryDto;
	}

	@Override
	public boolean saveBeneficiary(List<ValidateBeneficiaryBean> validateBeneficiaryBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(validateBeneficiaryBeans);
		} catch (DataAccessException e) {
			log.debug(e);
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getValidatedBeneficiary(String surveyId, Long entBy) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			/*DetachedCriteria beneficiaryIdSubquery = DetachedCriteria.forClass(ValidateBeneficiaryBean.class,
					"validateBeneficiaryBean");
			beneficiaryIdSubquery.createAlias("validateBeneficiaryBean.surveyValidationRequest",
					"surveyValidationRequest");
			beneficiaryIdSubquery.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			beneficiaryIdSubquery.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			beneficiaryIdSubquery
					.add(Restrictions.eq("validateBeneficiaryBean.userId", Integer.parseInt(String.valueOf(entBy))));
			beneficiaryIdSubquery.setProjection(Projections.property("beneficiaryid").as("beneficiaryId"));*/

			DetachedCriteria criteria = DetachedCriteria.forClass(ValidateBeneficiaryBean.class,
					"validateBeneficiaryBean");
			criteria.createAlias("validateBeneficiaryBean.surveyValidationRequest", "surveyValidationRequest");
			criteria.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			criteria.createAlias("validateBeneficiaryBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			criteria.add(Restrictions.eq("validateBeneficiaryBean.userId", Integer.parseInt(String.valueOf(entBy))));
			criteria.setProjection(
					Projections.projectionList()
							.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
							.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
							.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
							.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
							.add(Projections.property("validateBeneficiaryBean.validationRequestId"),
									"validationRequestId")
							.add(Projections.property("validateBeneficiaryBean.remarks"), "remarks")
							.add(Projections.property("validateBeneficiaryBean.action"), "action")
							.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));

			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyValidationRequest> findUser(String surveyId, Long entBy) throws DataAccessException {
		
		List<SurveyValidationRequest> surveyValidationRequests=null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SurveyValidationRequest.class,
					"surveyValidationRequest");
			criteria.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			criteria.add(Restrictions.eq("surveyReviewMapping.surveyId", Integer.parseInt(surveyId)));
			criteria.add(Restrictions.eq("surveyValidationRequest.lyingWithUsr", Integer.parseInt(String.valueOf(entBy))));
			surveyValidationRequests=getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return surveyValidationRequests;
	}

}
