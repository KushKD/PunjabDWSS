package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.BO.TrackingDto;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.OutcomeMappingBean;
import com.prwss.min.SDU.bean.StageDetailBean;
import com.prwss.min.SDU.form.TrackingForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class TrackingDaoImpl extends HibernateDaoSupport implements TrakingDao {
	
	private Logger Log = Logger.getLogger(TrackingDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<TrackingDto> gerTrackingDTO(TrackingForm form) throws DataAccessException {
		// TODO
		List<TrackingDto> trackingDTO = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(OutcomeMappingBean.class);
			criteria.createAlias("sduCycleActivityMapping", "sduCycleActivityMapping");
			criteria.createAlias("sduAttributeDetailBean", "sduAttributeDetailBean");
			criteria.createAlias("sduCycleActivityMapping.activityVillageMappingDetalBean", "activityVillageMappingDetalBean");
			criteria.createAlias("activityVillageMappingDetalBean.activityVillageMappingBean", "activityVillageMappingBean");
			criteria.createAlias("activityVillageMappingDetalBean.activityAttributeName", "activityAttributeName");
			// criteria.createAlias("comboDetailCompName",
			// "comboDetailCompName");

			criteria.add(Restrictions.eq("activityVillageMappingBean.financialYear", Integer.parseInt(form.getFinancialYear())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.divisionId", Integer.parseInt(form.getDivision())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.stageId", Integer.parseInt(form.getStage())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.componentId", Integer.parseInt(form.getComponent())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.categoryId", Integer.parseInt(form.getCategory())));
			criteria.add(Restrictions.eq("activityVillageMappingBean.villageId", Integer.parseInt(form.getVillage())));

			criteria.setProjection(
					Projections.projectionList()
							.add(Projections.property("activityAttributeName.attributeName"), "activity_Name")
							.add(Projections.property("activityVillageMappingDetalBean.startDate"), "expected_Start_Date")
							.add(Projections.property("activityVillageMappingDetalBean.endDate"), "expected_End_Date")

							.add(Projections.property("activityVillageMappingDetalBean.actualStartDate"), "actual_start_date")
							.add(Projections.property("activityVillageMappingDetalBean.actualEndDate"), "actual_End_Date")
							.add(Projections.property("activityVillageMappingDetalBean.outComeAchieved"), "achieved")
							.add(Projections.property("outcomeId"), "outcomeId")
							.add(Projections.property("sduAttributeDetailBean.attributeName"), "outcome_Name")
							.add(Projections.property("activityVillageMappingDetalBean.comments"), "coments")
							.add(Projections.property("activityVillageMappingDetalBean.ActivityVillageId"), "villageactivityId")
							.add(Projections.property("activityVillageMappingDetalBean.ActivityId"), "activityId")
							.add(Projections.property("activityVillageMappingDetalBean.activityVillageDetailId"),"villageActivityDetailID")

			// .add(Projections.count("componentId"),"totalComponent")
			// .add(Projections.groupProperty("schemeCycleAttributeDetailBean.attributeName"))
			// .add(Projections.groupProperty("comboDetailCompName.cmb_name"))
			);

			criteria.setResultTransformer(Transformers.aliasToBean(TrackingDto.class));
			trackingDTO = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(trackingDTO.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return trackingDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVillageMappingDetalBean> getBeansActivityDeailMapping(List<Integer> villageActivityID)
			throws DataAccessException {

		List<ActivityVillageMappingDetalBean> mappingDetailBean = new ArrayList<ActivityVillageMappingDetalBean>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingDetalBean.class);
			criteria.createAlias("activityVillageMappingBean", "activityVillageMappingBean",CriteriaSpecification.LEFT_JOIN);
			

			criteria.add(Restrictions.in("activityVillageDetailId", villageActivityID));

			mappingDetailBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return mappingDetailBean;

	}

	@Override
	public Boolean saveUpdateData(List<ActivityVillageMappingDetalBean> saveBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(saveBeans);
			
		} catch (DataAccessException e) {
		    Log.debug(e.getMessage());
			throw e;
		}
return true;
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------
	
	
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
			Log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return UserLocationBean;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------
	
	
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
			Log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return LocationDetails;
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StageDetailBean> getStageNameAndId() throws DataAccessException {
		List<StageDetailBean> stageDetail = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(StageDetailBean.class );
		
			stageDetail = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			Log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return stageDetail;
	}

}
