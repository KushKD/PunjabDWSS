package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.ConsolidatedPlanActivityWiseDTO;
import com.prwss.min.SDU.bean.DivisionActivityDetailMpgBean;
import com.prwss.min.SDU.bean.DivisionActivityMpgBean;
import com.prwss.min.SDU.bean.DivisionVillageMappingBean;
import com.prwss.min.SDU.bean.DivisionVillageMappingDetalBean;
import com.prwss.min.SDU.bean.SchemeCycleAttributeDetailBean;
import com.prwss.min.SDU.form.VillageActivityMpgForm;
import com.prwss.mis.admin.dao.LocationDetailsBean;

public class VillageActivityMappingDaoImpl extends HibernateDaoSupport implements VillageActivityMappingDao {

	@SuppressWarnings("unchecked")
	@Override
	public int getDivVillageIdValue(String component, String category, String stage) throws DataAccessException {
		// TODO Auto-generated method stub
		List<DivisionVillageMappingBean> categoryDetail = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionVillageMappingBean.class);
			criteria.add(Restrictions.eq("categoryId", Integer.parseInt(category)));
			criteria.add(Restrictions.eq("stageId", Integer.parseInt(stage)));
			criteria.add(Restrictions.eq("componentId", Integer.parseInt(component)));
			
		
			categoryDetail = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(categoryDetail.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return categoryDetail.get(0).getDivVillageId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getVillageIds(int DivisionVillageId) throws DataAccessException {
		// TODO Auto-generated method stub
				List<Integer> villageIds = null;
				try {
					
					DetachedCriteria criteria = DetachedCriteria.forClass(DivisionVillageMappingDetalBean.class);
					criteria.add(Restrictions.eq("divVillageId", DivisionVillageId));
					criteria.setProjection(Projections.property("villageId"));
				
					villageIds = getHibernateTemplate().findByCriteria(criteria);
					
					
				} catch (DataAccessException e) {
					throw e;
				}
				return villageIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDetailsBean> getVillagesData(List<Integer> villageIds) throws DataAccessException {
		List<LocationDetailsBean> LocationDetails = new ArrayList<LocationDetailsBean>();
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class);

			criteria.add(Restrictions.in("locationDetailsId", villageIds));
		
			LocationDetails = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return LocationDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getDivisionActivityId(String category, String stage, String component, String DivisionId) throws DataAccessException {
		List<DivisionActivityMpgBean> categoryDetail = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionActivityMpgBean.class);
			criteria.add(Restrictions.eq("category_id", Integer.parseInt(category)));
			criteria.add(Restrictions.eq("stage_id", Integer.parseInt(stage)));
			criteria.add(Restrictions.eq("component_id", Integer.parseInt(component)));
			criteria.add(Restrictions.eq("division_id", Integer.parseInt(DivisionId)));
			
		
			categoryDetail = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(categoryDetail.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return categoryDetail.get(0).getDiv_activity_id();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getIdActivities(int DivisonActivityId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<Integer> ActivityIds = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionActivityDetailMpgBean.class);
			criteria.add(Restrictions.eq("div_activity_id", DivisonActivityId));
			criteria.setProjection(Projections.property("activity_id"));
		
			ActivityIds = getHibernateTemplate().findByCriteria(criteria);
			
			
		} catch (DataAccessException e) {
			throw e;
		}
		return ActivityIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeCycleAttributeDetailBean> getGetActivityDetails(List<Integer> activityIds)
			throws DataAccessException {
		List<SchemeCycleAttributeDetailBean> LocationDetails = new ArrayList<SchemeCycleAttributeDetailBean>();
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(SchemeCycleAttributeDetailBean.class);

			criteria.add(Restrictions.in("schAttributeDtlId", activityIds));
		
			LocationDetails = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return LocationDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVillageMappingDetalBean> getActivityVillageId(VillageActivityMpgForm villageActivityMpgForm) {
		List<ActivityVillageMappingDetalBean> categoryDetail = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingDetalBean.class);
			
			criteria.createAlias("activityVillageMappingBean", "activityVillageMappingBean");
			criteria.add(Restrictions.eq("activityVillageMappingBean.categoryId", Integer.parseInt(villageActivityMpgForm.getCategory())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.stageId", Integer.parseInt(villageActivityMpgForm.getStage())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.componentId", Integer.parseInt(villageActivityMpgForm.getComponent())));
			
		
			categoryDetail = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(categoryDetail.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return categoryDetail;
	}

	@Override
	public int saveVillageActivityBean(ActivityVillageMappingBean activityVillageMapping)
			throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdate(activityVillageMapping);
			
		}catch(DataAccessException ex){
			ex.printStackTrace();
				
		}
		return activityVillageMapping.getActivityVillageId();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getActivityVillageIdValue(VillageActivityMpgForm villageActivityMpgForm) throws DataAccessException {
		List<ActivityVillageMappingBean> Detail = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingBean.class);
			criteria.add(Restrictions.eq("categoryId", Integer.parseInt(villageActivityMpgForm.getCategory())));
			criteria.add(Restrictions.eq("stageId", Integer.parseInt(villageActivityMpgForm.getStage())));
			criteria.add(Restrictions.eq("componentId", Integer.parseInt(villageActivityMpgForm.getComponent())));
			
		
			Detail = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(Detail.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return Detail.get(0).getActivityVillageId();
	}

	@Override
	public boolean saveDetailActivityVillage(List<ActivityVillageMappingDetalBean> divisionVillageMappingDetailBean)
			throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(divisionVillageMappingDetailBean);
			return true;
		}catch(DataAccessException ex){
			ex.printStackTrace();
			return false;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsolidatedPlanActivityWiseDTO> getConsolidatePlanActivity(String financialYear)
			throws DataAccessException {
		
		 List<ConsolidatedPlanActivityWiseDTO> consolidatedPlanActivityWiseDTOs=null;
		try{
			 DetachedCriteria criteria=DetachedCriteria.forClass(ActivityVillageMappingDetalBean.class);
			 criteria.createAlias("activityVillageMappingBean", "activityVillageMappingBean");
			 criteria.createAlias("activityAttributeName", "activityAttributeName");
			 criteria.createAlias("activityVillageMappingBean.comboBeanDetailsComponentId", "comboDetailCompName");
			 
			criteria.add(Restrictions.eq("activityVillageMappingBean.financialYear", Integer.parseInt(financialYear)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("activityAttributeName.attributeName"),"activityName")
					.add(Projections.property("comboDetailCompName.cmb_name"),"cmb_name")
					.add(Projections.count("activityVillageMappingBean.componentId"),"totalComponent")
					.add(Projections.groupProperty("activityAttributeName.attributeName"))
					.add(Projections.groupProperty("comboDetailCompName.cmb_name"))
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(ConsolidatedPlanActivityWiseDTO.class));
			consolidatedPlanActivityWiseDTOs=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return consolidatedPlanActivityWiseDTOs;
	}

}
