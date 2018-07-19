package com.prwss.min.SDU.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.ConsolidatedPlanDivisionWiseDTO;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public class ConsolidatedPlanDivisionWiseDaoImpl extends HibernateDaoSupport implements ConsolidatedPlanDivisionWiseDao{
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsolidatedPlanDivisionWiseDTO> getConsolidatePlanDivision(String financialYear)
			throws DataAccessException {
		
		 List<ConsolidatedPlanDivisionWiseDTO> consolidatedPlanDivisionWiseDTOs=null;
		try{
			 DetachedCriteria criteria=DetachedCriteria.forClass(ActivityVillageMappingBean.class);
			 criteria.createAlias("locationDivisionSubDivisonDetailsBeanDivisionId", "locationDivisionSubDivisonDetailsBeanDivisionId");
			 criteria.createAlias("comboBeanDetailsComponentId", "comboBeanDetailsComponentId");
			 
			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(financialYear)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("divisionId"),"divisionId")
					.add(Projections.property("locationDivisionSubDivisonDetailsBeanDivisionId.DivisonSubDivisonDetailsName"),"divisionName")
					.add(Projections.property("comboBeanDetailsComponentId.cmb_name"),"componentName")
					.add(Projections.count("villageId"),"totalVillage")
					.add(Projections.groupProperty("locationDivisionSubDivisonDetailsBeanDivisionId.DivisonSubDivisonDetailsName"))
					.add(Projections.groupProperty("comboBeanDetailsComponentId.cmb_name"))
					.add(Projections.groupProperty("divisionId"))
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(ConsolidatedPlanDivisionWiseDTO.class));
			consolidatedPlanDivisionWiseDTOs=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return consolidatedPlanDivisionWiseDTOs;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsolidatedPlanDivisionWiseDTO> getPendingDivisions(List<Integer> divisionId)
			throws DataAccessException {
		
		 List<ConsolidatedPlanDivisionWiseDTO> consolidatedPlanDivisionWiseDTOs=null;
		try{
			 DetachedCriteria criteria=DetachedCriteria.forClass(LocationDivisionSubDivisonDetailsBean.class);
			 criteria.createAlias("locationDivSubDivMasterBean", "locationDivSubDivMasterBean");
			 
			criteria.add(Restrictions.not(Restrictions.in("locationDivSubDivMasterBean.DivSubDivId", divisionId)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("DivisonSubDivisonDetailsName"),"divisionName")
					.add(Projections.groupProperty("DivisonSubDivisonDetailsName"))
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(ConsolidatedPlanDivisionWiseDTO.class));
			consolidatedPlanDivisionWiseDTOs=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return consolidatedPlanDivisionWiseDTOs;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getExistingDivisionId(Integer financialYear)
			throws DataAccessException {
		
		 List<Integer> divisionId = null;
		try{
			 DetachedCriteria criteria=DetachedCriteria.forClass(ActivityVillageMappingBean.class);
			 
			criteria.add(Restrictions.eq("financialYear", financialYear));
			
			criteria.setProjection(Projections.property("divisionId"));
			
			divisionId = getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return divisionId;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsolidatedPlanDivisionWiseDTO> getDetails(Integer financialYear, Integer divisionId)
			throws DataAccessException {
		
		 List<ConsolidatedPlanDivisionWiseDTO> consolidatedPlanDivisionWiseDTOs=null;
		try{
			 DetachedCriteria criteria=DetachedCriteria.forClass(ActivityVillageMappingDetalBean.class);
			 criteria.createAlias("activityAttributeName", "activityAttributeName");
			 criteria.createAlias("activityVillageMappingBean", "activityVillageMappingBean");
			 criteria.createAlias("activityVillageMappingBean.locationDetailsBeanVillageId", "locationDetailsBeanVillageId");
			 
			criteria.add(Restrictions.eq("activityVillageMappingBean.financialYear", financialYear));
			criteria.add(Restrictions.eq("activityVillageMappingBean.divisionId", divisionId));
		
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("locationDetailsBeanVillageId.locationName"),"villageName")
					.add(Projections.property("activityAttributeName.attributeName"),"activityName")
					.add(Projections.property("startDate"),"expectedStartDate")
					.add(Projections.property("endDate"),"expectedEndDate")
					.add(Projections.groupProperty("locationDetailsBeanVillageId.locationName"))
					.add(Projections.groupProperty("activityAttributeName.attributeName"))
					.add(Projections.groupProperty("startDate"))
					.add(Projections.groupProperty("endDate"))
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(ConsolidatedPlanDivisionWiseDTO.class));
			consolidatedPlanDivisionWiseDTOs=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return consolidatedPlanDivisionWiseDTOs;
	}

}
