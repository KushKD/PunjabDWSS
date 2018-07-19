/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.sanitation.bean.BaseLineMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.SurveyReviewMapping;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
public class ValidateRequestDaoImpl extends HibernateDaoSupport implements ValidateRequestDao {

	private Logger log = Logger.getLogger(ValidateRequestDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getFreezedSurvey() throws DataAccessException {
		System.out.println("inside dao----------");
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class, "baseLineMasterBean");
			criteria.createAlias("baseLineMasterBean.surveyMasterBean", "surveyMasterBean");

			criteria.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("baseLineMasterBean.surveyId")), "surveyId")
					.add(Projections.property("surveyMasterBean.survey_name"), "surveyName")
					.add(Projections.property("surveyMasterBean.purpose"), "purpose")
					
					);
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
	public List<BeneficiaryDto> findFreezedSurvey(String baselineSurveyId) throws DataAccessException {
		System.out.println("inside dao---------"+baselineSurveyId);
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class, "baseLineMasterBean");
			criteria.createAlias("baseLineMasterBean.surveyMasterBean", "surveyMasterBean");
			criteria.createAlias("baseLineMasterBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.add(Restrictions.eq("surveyId", Integer.parseInt(baselineSurveyId)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("surveyMasterBean.survey_name"), "surveyName")
					.add(Projections.property("surveyMasterBean.actl"), "actualStartDate")
					.add(Projections.property("surveyMasterBean.actl_end_date"), "actEndDate")
					.add(Projections.property("baseLineMasterBean.surveyId"), "surveyId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
					.add(Projections.property("baseLineMasterBean.baslineSurveyId"), "baselineSurveyId")
					);
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
	public List<BeneficiaryDto> getSurveyDetails(String surveyId) throws DataAccessException {
		
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class, "baseLineMasterBean");
			criteria.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("surveyId", Integer.parseInt(surveyId)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("baseLineMasterBean.surveyId"),"surveyId")
					.add(Projections.property("baseLineMasterBean.districtId"),"districtId")
					.add(Projections.property("baseLineMasterBean.blockId"),"blockId")
					.add(Projections.property("baseLineMasterBean.villageId"),"villageId")
					.add(Projections.groupProperty("baseLineMasterBean.surveyId"))
					.add(Projections.groupProperty("baseLineMasterBean.districtId"))
					.add(Projections.groupProperty("baseLineMasterBean.blockId"))
					.add(Projections.groupProperty("baseLineMasterBean.villageId"))
										);
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
	public List<BeneficiaryDto> getEmployeeDetails(BeneficiaryDto beneficiaryDto) throws DataAccessException {
		
		List<BeneficiaryDto> beneficiaryDtos=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(SurveyReviewMapping.class);
			criteria.add(Restrictions.eq("surveyId", beneficiaryDto.getSurveyId()));
			criteria.add(Restrictions.eq("districtId", beneficiaryDto.getDistrictId()));
			criteria.add(Restrictions.eq("blockId", beneficiaryDto.getBlockId()));
			criteria.add(Restrictions.eq("villageId", beneficiaryDto.getVillageId()));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("reviewAuthority"),"reviewAuthority")
					.add(Projections.property("surveyReviewId"),"surveyReviewId")
					);
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDtos=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return beneficiaryDtos;
	}

	@Override
	public boolean saveSurveyEmpDetails(List<SurveyValidationRequest> beneficiaryDtos) throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(beneficiaryDtos);
		}catch(DataAccessException e){
			log.debug(e);
			throw e;
		}
		return true;
	}

}
