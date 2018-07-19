package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.form.DivisionWisePlanViewForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class DivisionWisePlanViewDaoImpl extends HibernateDaoSupport implements DivisionWisePlanViewDao{
	private Logger log = Logger.getLogger(DivisionWisePlanViewDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<DivisionWisePlanViewForm> getPlanByPagination(String searchString,int clickedColumn,String colOrder,String financialYear,String division)
			throws DataAccessException {

		List<DivisionWisePlanViewForm> divisionWisePlanViewForms = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingBean.class);
			
			criteria.createAlias("comboBeanDetailsCategoryId", "comboBeanDetailsCategoryId");
			criteria.createAlias("stageDetailBeanStageId", "stageDetailBeanStageId");
			criteria.createAlias("comboBeanDetailsComponentId", "comboBeanDetailsComponentId");
			criteria.createAlias("locationDetailsBeanVillageId", "locationDetailsBeanVillageId");
			
			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(financialYear)));
			criteria.add(Restrictions.eq("divisionId", Integer.parseInt(division)));

			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("activityVillageId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("comboBeanDetailsCategoryId.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("stageDetailBeanStageId.stageName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("comboBeanDetailsComponentId.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("locationDetailsBeanVillageId.locationName", searchString, MatchMode.ANYWHERE)));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboBeanDetailsCategoryId.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboBeanDetailsCategoryId.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("stageDetailBeanStageId.stageName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("stageDetailBeanStageId.stageName"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboBeanDetailsComponentId.cmb_name"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboBeanDetailsComponentId.cmb_name"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("locationDetailsBeanVillageId.locationName"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("locationDetailsBeanVillageId.locationName"));
				}

			}
	
			criteria.setProjection(Projections.projectionList().add(Projections.property("comboBeanDetailsCategoryId.cmb_name"),"categoryName")
															.add(Projections.property("stageDetailBeanStageId.stageName"),"stageName")
															.add(Projections.property("comboBeanDetailsComponentId.cmb_name"),"componentName")
															.add(Projections.property("locationDetailsBeanVillageId.locationName"),"villageName")
															.add(Projections.property("activityVillageId"),"activityVillageId"));
			
			
			criteria.setResultTransformer(Transformers.aliasToBean(DivisionWisePlanViewForm.class));
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);

			divisionWisePlanViewForms = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return divisionWisePlanViewForms;
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
	
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionWisePlanViewForm> getPlanDetails(String activityVillageId) throws DataAccessException {

		List<DivisionWisePlanViewForm> divisionWisePlanViewForms = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingDetalBean.class);
			
			criteria.createAlias("activityVillageMappingBean", "activityVillageMappingBean")
					.createAlias("activityVillageMappingBean.comboBeanDetailsFinancialYear", "comboBeanDetailsFinancialYear")
					.createAlias("activityVillageMappingBean.comboBeanDetailsComponentId", "comboBeanDetailsComponentId")
					.createAlias("activityVillageMappingBean.comboBeanDetailsCategoryId", "comboBeanDetailsCategoryId")
					.createAlias("activityVillageMappingBean.stageDetailBeanStageId", "stageDetailBeanStageId")
					.createAlias("activityVillageMappingBean.locationDetailsBeanVillageId", "locationDetailsBeanVillageId")
					.createAlias("activityVillageMappingBean.schemeCycleAttributeDetailBeanCampaignId", "schemeCycleAttributeDetailBeanCampaignId")
					.createAlias("activityAttributeName", "activityAttributeName");

			if (MisUtility.ifEmpty(activityVillageId)) {
				

				criteria.add(Restrictions.eq("ActivityVillageId", Integer.parseInt(activityVillageId)));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("ActivityVillageId"),"ActivityVillageId")
						.add(Projections.property("comboBeanDetailsCategoryId.cmb_name"),"categoryName")
						.add(Projections.property("stageDetailBeanStageId.stageName"),"stageName")
						.add(Projections.property("comboBeanDetailsComponentId.cmb_name"),"componentName")
						.add(Projections.property("locationDetailsBeanVillageId.locationName"),"villageName")
						.add(Projections.property("schemeCycleAttributeDetailBeanCampaignId.attributeName"), "campaignName")
						.add(Projections.property("activityAttributeName.attributeName"), "activityName")
						.add(Projections.property("startDate"), "startDate")
						.add(Projections.property("endDate"), "endDate"));
				
				
				criteria.setResultTransformer(Transformers.aliasToBean(DivisionWisePlanViewForm.class));
				divisionWisePlanViewForms = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		
		return divisionWisePlanViewForms;
	}
}
