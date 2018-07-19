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

import com.prwss.min.sanitation.bean.BaseLineMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.form.BaseLineForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BaseLineDaoImpl extends HibernateDaoSupport implements BaseLineDao {

	private Logger log = Logger.getLogger(BaseLineDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getBeneficiaryDetail(BaseLineForm baseLineForm) throws DataAccessException {

		List<BeneficiaryDto> beneficiaryDto = null;
		try {

			DetachedCriteria subquery = DetachedCriteria.forClass(BaseLineMasterBean.class);
			// subquery.createAlias("baseLineSurveyMapping.baslineSurveyBean",
			// "baslineSurveyBean");
			subquery.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			subquery.add(Restrictions.eq("surveyId", Integer.parseInt(baseLineForm.getSurveyId())));
			subquery.setProjection(Projections.property("beneficiaryId").as("beneficiaryId"));

			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			criteria.createAlias("beneficiaryEntryBean.combodetailReligion", "combodetailReligion");
			criteria.createAlias("beneficiaryEntryBean.locationDetailBean", "locationDetailBean");

			if (MisUtility.ifEmpty(baseLineForm.getVillage()))
				criteria.add(Restrictions.eq("villageId", Integer.parseInt(baseLineForm.getVillage())));

			if (MisUtility.ifEmpty(baseLineForm.getBlock()))
				criteria.add(Restrictions.eq("blockId", Integer.parseInt(baseLineForm.getBlock())));

			if (MisUtility.ifEmpty(baseLineForm.getDistrict()))
				criteria.add(Restrictions.eq("districtId", Integer.parseInt(baseLineForm.getDistrict())));

			if (MisUtility.ifEmpty(Integer.parseInt(String.valueOf(baseLineForm.getLoginUser())))) {
				criteria.add(
						Restrictions.eq("createdById", Integer.parseInt(String.valueOf(baseLineForm.getLoginUser()))));
			}

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Property.forName("beneficiaryEntryBean.beneficieryId").notIn(subquery));

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

	@Override
	public boolean saveBaseLineSurvey(List<BaseLineMasterBean> baseLineMasterBean) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(baseLineMasterBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@Override
	public boolean deleteSurveyDetails(String beneficiaryId) throws DataAccessException {

		List<BaseLineMasterBean> baseLineSurveyMappings = null;
		try {
			baseLineSurveyMappings = getSurveyDetails(beneficiaryId);

			if (!MisUtility.ifEmpty(baseLineSurveyMappings)) {
				getHibernateTemplate().delete(baseLineSurveyMappings.get(0));
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private List<BaseLineMasterBean> getSurveyDetails(String beneficiaryId) {
		List<BaseLineMasterBean> baseLineSurveyMapping = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class);
			criteria.add(Restrictions.eq("beneficiaryId", Integer.parseInt(beneficiaryId)));
			baseLineSurveyMapping = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return baseLineSurveyMapping;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getSurveyDetails(BaseLineForm baseLineForm) throws DataAccessException {

		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			/*
			 * DetachedCriteria subquery =
			 * DetachedCriteria.forClass(BaseLineMasterBean.class); //
			 * subquery.createAlias("baseLineSurveyMapping.baslineSurveyBean",
			 * // "baslineSurveyBean"); //
			 * subquery.add(Restrictions.eq("baslineSurveyBean.activeFlag", //
			 * Integer.parseInt(MISConstants.ONE)));
			 * subquery.setProjection(Projections.property("beneficiaryId").as(
			 * "beneficiaryId"));
			 */

			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class, "baseLineMasterBean");
			criteria.createAlias("baseLineMasterBean.beneficiaryEntryBean", "beneficiaryEntryBean");

			if (MisUtility.ifEmpty(baseLineForm.getSurveyId()))
				criteria.add(Restrictions.eq("surveyId", Integer.parseInt(baseLineForm.getSurveyId())));

			if (MisUtility.ifEmpty(baseLineForm.getVillage()))
				criteria.add(Restrictions.eq("villageId", Integer.parseInt(baseLineForm.getVillage())));

			if (MisUtility.ifEmpty(baseLineForm.getBlock()))
				criteria.add(Restrictions.eq("blockId", Integer.parseInt(baseLineForm.getBlock())));

			if (MisUtility.ifEmpty(baseLineForm.getDistrict()))
				criteria.add(Restrictions.eq("districtId", Integer.parseInt(baseLineForm.getDistrict())));
			
			if (MisUtility.ifEmpty(baseLineForm.getGramPanchayatId()))
				criteria.add(Restrictions.eq("gramPanchayatId", baseLineForm.getGramPanchayatId()));

			if (MisUtility.ifEmpty(Integer.parseInt(String.valueOf(baseLineForm.getLoginUser())))) {
				criteria.add(
						Restrictions.eq("createdById", Integer.parseInt(String.valueOf(baseLineForm.getLoginUser()))));

			}

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			// criteria.add(Property.forName("beneficiaryEntryBean.beneficieryId").in(subquery));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
					.add(Projections.property("baseLineMasterBean.baslineSurveyId"), "baselineSurveyId")
					.add(Projections.property("baseLineMasterBean.surveyId"), "surveyId"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));

			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseLineMasterBean> verifySurveyStatus(BaseLineForm baseLineForm) throws DataAccessException {
		// TODO Auto-generated method stub

		List<BaseLineMasterBean> baseLineMasterBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class);

			if (MisUtility.ifEmpty(baseLineForm.getSurveyId()))
				criteria.add(Restrictions.eq("surveyId", Integer.parseInt(baseLineForm.getSurveyId())));

			if (MisUtility.ifEmpty(baseLineForm.getVillage()))
				criteria.add(Restrictions.eq("villageId", Integer.parseInt(baseLineForm.getVillage())));

			if (MisUtility.ifEmpty(baseLineForm.getBlock()))
				criteria.add(Restrictions.eq("blockId", Integer.parseInt(baseLineForm.getBlock())));

			if (MisUtility.ifEmpty(baseLineForm.getDistrict()))
				criteria.add(Restrictions.eq("districtId", Integer.parseInt(baseLineForm.getDistrict())));

			if (MisUtility.ifEmpty(Integer.parseInt(String.valueOf(baseLineForm.getLoginUser())))) {
				criteria.add(
						Restrictions.eq("crtByUsr", Integer.parseInt(String.valueOf(baseLineForm.getLoginUser()))));
			}
			if (MisUtility.ifEmpty(baseLineForm.getGramPanchayatId()))
				criteria.add(Restrictions.eq("gramPanchayatId", baseLineForm.getGramPanchayatId()));

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("isFreezed", Integer.parseInt(MISConstants.ONE)));

			baseLineMasterBean = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return baseLineMasterBean;
	}

	@Override
	public boolean updateSurvey(List<BaseLineMasterBean> baseLineMasterBean) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(baseLineMasterBean);

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseLineMasterBean> fetchSurveyDetails(Integer[] surveyId) throws DataAccessException {
		List<BaseLineMasterBean> baseLineMasterBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BaseLineMasterBean.class);
			criteria.add(Restrictions.in("baslineSurveyId", surveyId));
			baseLineMasterBean = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return baseLineMasterBean;
	}
}
