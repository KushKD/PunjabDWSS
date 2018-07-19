package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.SduVillageTrainingAttachmentBean;
import com.prwss.min.SDU.bean.SduVillageTrainingDetailsBean;
import com.prwss.min.SDU.bean.SduVillageTrainingMaterialBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class TrainingDetailsEntryDaoImpl extends HibernateDaoSupport implements TrainingDetailsEntryDao{
	
	private Logger log = Logger.getLogger(DivisionActivityMpgDaoImpl.class);
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVillageMappingBean> getVillageNameAndId(Integer financialYr, Integer division) throws DataAccessException {

		List<ActivityVillageMappingBean> activityVillageMappingBeans = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingBean.class);
			
			criteria.add(Restrictions.eq("financialYear", financialYr));
			criteria.add(Restrictions.eq("divisionId", division));

			activityVillageMappingBeans = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(activityVillageMappingBeans.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return activityVillageMappingBeans;
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVillageMappingDetalBean> getActivityNameandId(Integer financialYr, Integer division,Integer village) throws DataAccessException {

		List<ActivityVillageMappingDetalBean> activityVillageMappingDetalBean = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(ActivityVillageMappingDetalBean.class);

			criteria.createAlias("activityVillageMappingBean", "activityVillageMappingBean");
			criteria.createAlias("activityAttributeName", "activityAttributeName");
			
			criteria.add(Restrictions.eq("activityVillageMappingBean.financialYear", financialYr));
			criteria.add(Restrictions.eq("activityVillageMappingBean.divisionId", division));
			criteria.add(Restrictions.eq("activityVillageMappingBean.villageId", village));

			activityVillageMappingDetalBean = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(activityVillageMappingDetalBean.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return activityVillageMappingDetalBean;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	//----------------------------------------------------------------------------------------------------------------------------------------------

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
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public Integer saveVillageTrainingDetail(SduVillageTrainingDetailsBean sduVillageTrainingDetailsBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().save(sduVillageTrainingDetailsBean);
		}
		catch(DataAccessException e){
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return sduVillageTrainingDetailsBean.getTraining_id();
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean saveVillageTrainingMaterial(List<SduVillageTrainingMaterialBean> sduVillageTrainingMaterialBeans) throws DataAccessException {
		
		try{
			getHibernateTemplate().saveOrUpdateAll(sduVillageTrainingMaterialBeans);
		}
		catch(DataAccessException e){
			log.debug(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return true;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean saveSduVillageTrainingAttachment(SduVillageTrainingAttachmentBean sduVillageTrainingAttachmentBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().save(sduVillageTrainingAttachmentBean);
		}
		catch(DataAccessException e){
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}
}
