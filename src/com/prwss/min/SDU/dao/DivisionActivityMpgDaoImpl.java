package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.DivisionActivityDetailMpgBean;
import com.prwss.min.SDU.bean.DivisionActivityMpgBean;
import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.SchemeCycleAttributeDetailBean;
import com.prwss.min.SDU.bean.SchemeCycleAttributeMasterBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.form.DivisionActivityMpgDto;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class DivisionActivityMpgDaoImpl extends HibernateDaoSupport implements DivisionActivityMpgDao {

	private Logger log = Logger.getLogger(DivisionActivityMpgDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllLocationIds(String UserID) throws DataAccessException {

		List<String> UserLocationBean = null;
		try {
			System.out.println("DAO Code" + UserID);

			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);
			criteria.add(Restrictions.eq("userId", UserID));

			criteria.setProjection(Projections.projectionList().add(Projections.property("locationId")));

			UserLocationBean = new ArrayList<String>();
			UserLocationBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return UserLocationBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> locationId)
			throws DataAccessException {
		List<LocationDivisionSubDivisonDetailsBean> LocationDetails = new ArrayList<LocationDivisionSubDivisonDetailsBean>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDivisionSubDivisonDetailsBean.class);

			criteria.add(Restrictions.in("DivisonSubDivisonDetailsId", locationId));

			LocationDetails = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return LocationDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionActivityMpgDto> getStageNameAndId(Integer financialYr, Integer division, Integer category) throws DataAccessException {
		List<DivisionActivityMpgDto> divisionActivityMpgDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(StageComponentBean.class);
			criteria.createAlias("divisionWiseSummaryBean", "divisionWiseSummaryBean");
			criteria.createAlias("stageDetailBean", "stageDetailBean");
			
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.activeFlag",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.financialYear", financialYr));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.division", division));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.category", category));
			
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("stageId"), "stageId")
																					.add(Projections.property("stageDetailBean.stageName"), "stageName")));
			
			criteria.setResultTransformer(Transformers.aliasToBean(DivisionActivityMpgDto.class));
			divisionActivityMpgDtos = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println("------------------1-----------------------"+divisionActivityMpgDtos.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return divisionActivityMpgDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionWiseSummaryBean> getCategoryNameAndId(Integer financialYear,Integer division) throws DataAccessException {
		List<DivisionWiseSummaryBean> categoryDetail = null;
		try {

			// Criteria criteria=
			// getSession().createCriteria(DivisionWiseSummaryBean.class,
			// "DivisionWiseSummaryBean");

			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class);
			criteria.createAlias("combodetailCatName", "combodetailCatName");
			
			criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("financialYear", financialYear));
			criteria.add(Restrictions.eq("division", division));

			categoryDetail = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(categoryDetail.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return categoryDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StageComponentBean> getComponentNameAndId(Integer financialYr, Integer division, Integer category,
			Integer stage) throws DataAccessException {

		List<StageComponentBean> stageComponentBean = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(StageComponentBean.class);

			criteria.createAlias("divisionWiseSummaryBean", "divisionWiseSummaryBean");

			criteria.add(Restrictions.eq("divisionWiseSummaryBean.activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.financialYear", financialYr));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.division", division));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.category", category));
			criteria.add(Restrictions.eq("stageId", stage));

			stageComponentBean = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(stageComponentBean.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return stageComponentBean;
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public int getAttributeId() throws DataAccessException {
	 * 
	 * List<ComboBeanDetails> comboBeanDetails = null;
	 * 
	 * try {
	 * 
	 * DetachedCriteria criteria =
	 * DetachedCriteria.forClass(ComboBeanDetails.class);
	 * 
	 * String campaign = "Campaign";
	 * 
	 * criteria.add(Restrictions.eq("cmb_name", campaign))
	 * .setProjection(Projections.projectionList().add(Projections.property(
	 * "cmb_id")));
	 * 
	 * comboBeanDetails = getHibernateTemplate().findByCriteria(criteria);
	 * 
	 * System.out.println(comboBeanDetails.toString());
	 * 
	 * } catch (DataAccessException e) { e.printStackTrace();
	 * log.error(e.getLocalizedMessage(), e); throw e; } return
	 * comboBeanDetails.get(0).getCmb_id(); }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeCycleAttributeDetailBean> getSchAttributeId(Integer attributeTypeId) throws DataAccessException {

		List<SchemeCycleAttributeDetailBean> schemeCycleAttributeDetailBeans = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SchemeCycleAttributeDetailBean.class);
			criteria.createCriteria("detailAttributeName", "detailAttributeName");
			criteria.add(Restrictions.eq("detailAttributeName.attributeType", attributeTypeId));

			schemeCycleAttributeDetailBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return schemeCycleAttributeDetailBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeCycleAttributeDetailBean> getCampaignNameAndId(
			Collection<SchemeCycleAttributeMasterBean> schemeCycleAttributeMasterBean) throws DataAccessException {

		List<SchemeCycleAttributeDetailBean> schemeCycleAttributeDetailBean = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SchemeCycleAttributeDetailBean.class);

			criteria.add(Restrictions.in("schAttributeId", schemeCycleAttributeMasterBean));

			criteria.setProjection(Projections.projectionList().add(Projections.property("schAttributeDtlId"))
					.add(Projections.property("attributeName")));

			schemeCycleAttributeDetailBean = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(schemeCycleAttributeDetailBean.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return schemeCycleAttributeDetailBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeCycleAttributeDetailBean> getActivityNameandId(Integer financialYr, Integer campaign,
			Integer category, Integer stage, Integer parentId) throws DataAccessException {
		List<SchemeCycleAttributeDetailBean> activityDetails = new ArrayList<SchemeCycleAttributeDetailBean>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SchemeCycleAttributeDetailBean.class);
			criteria.createAlias("sDUCycleActivityMapping", "sDUCycleActivityMapping")
					.createAlias("sDUCycleActivityMapping.sduSchemeCycleMasterBean", "sduSchemeCycleMasterBean");
			if (MisUtility.ifEmpty(financialYr))
				criteria.add(Restrictions.eq("sduSchemeCycleMasterBean.financialYear", financialYr));

			criteria.add(Restrictions.eq("sduSchemeCycleMasterBean.categoryId", category));
			criteria.add(Restrictions.eq("sduSchemeCycleMasterBean.compaignId", campaign));
			criteria.add(Restrictions.eq("sduSchemeCycleMasterBean.stageId", stage));

			activityDetails = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return activityDetails;
	}

	public Integer saveDivisionActivityDetails(DivisionActivityMpgBean divisionActivityMpgBeans)
			throws DataAccessException {

		try {
			getHibernateTemplate().save(divisionActivityMpgBeans);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}
		return divisionActivityMpgBeans.getDiv_activity_id();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> fetchdivActivityId(Integer financialYr, Integer division, Integer catagory, Integer component,
			Integer stage, Integer campaign) throws DataAccessException {
		
		List<Integer> activityId = null;
		
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionActivityMpgBean.class);
					criteria.add(Restrictions.eq("financial_year", financialYr))
							.add(Restrictions.eq("division_id", division))
							.add(Restrictions.eq("stage_id", stage))
							.add(Restrictions.eq("component_id", component))
							.add(Restrictions.eq("campaign_id", campaign))
							.add(Restrictions.eq("category_id", catagory));
					criteria.setProjection(Projections.property("div_activity_id"));
					
					activityId = getHibernateTemplate().findByCriteria(criteria);
					
		}catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return activityId;
	}
	
	
	
	public boolean saveDivisionActivityMpgDetails(List<DivisionActivityDetailMpgBean> divisionActivityDetailMpgBeans)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(divisionActivityDetailMpgBeans);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}
		return true;
	}
}