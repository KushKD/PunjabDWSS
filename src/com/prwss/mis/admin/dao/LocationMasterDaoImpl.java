package com.prwss.mis.admin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.quality.struts.LocationMasterForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LocationMasterDaoImpl extends HibernateDaoSupport implements LocationMasterDao {

	private Logger log = Logger.getLogger(LocationMasterDaoImpl.class);

	@Override
	public boolean saveMasterLocation(LocationMasterBean locationMasterBean) throws DataAccessException {

		try {

			System.out.println("in dao ++++schemeVillageBean+++++++" + locationMasterBean.toString());

			getHibernateTemplate().flush();

			getHibernateTemplate().save(locationMasterBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

			log.error(e.getLocalizedMessage(), e);
			throw e;
			// throw new MISException(e);
			// throw new MISException(e);

		}

		return true;
	}

	@Override
	public boolean saveDetailsLocation(LocationDetailsBean locationDetailsBean) throws DataAccessException {

		try {

			System.out.println("in dao ++++schemeVillageBean+++++++" + locationDetailsBean.toString());

			getHibernateTemplate().flush();

			getHibernateTemplate().save(locationDetailsBean);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();

		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

			log.error(e.getLocalizedMessage(), e);
			throw e;
			// throw new MISException(e);
			// throw new MISException(e);

		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterForm> getLocationMasterByPagination() throws DataAccessException {

		List<LocationMasterForm> locationMasterForm = null;
		try {

			
			/*List<Object[]>   resultList= (List<Object[]>)getSession().createCriteria(LocationDetailsBean.class,"locationDetailsBean")
										.createCriteria("locationDetailsBean.locationMasterBean","locationMasterBean")
										.setProjection(Projections.projectionList().
										 add(Projections.property("locationMasterBean.parentLocation"))
										 .add(Projections.property("locationMasterBean.locationId"))
										.add(Projections.property("locationMasterBean.startDate"))
										.add(Projections.property("locationMasterBean.endDate"))
										.add(Projections.property("locationMasterBean.activeField"))
										.add(Projections.property("locationDetailsBean.locationName"))
										.add(Projections.property("locationMasterBean.locationTypeId"))).list();
			if (!MisUtility.ifEmpty(resultList)) {
				locationMasterForm = new ArrayList<LocationMasterForm>();

				for (Object[] obj : resultList) {
					LocationMasterForm masterForm = new LocationMasterForm();

					//List<LocationTypeBean> typpe = getLocationTypes(obj[6].toString());
					masterForm.setLocationName(obj[5].toString());
					if (!MisUtility.ifEmpty(typpe)) {
						for(LocationTypeBean bean:typpe){
						masterForm.setLocationType(bean.getLocationName() + "-" + bean.getLocationTypeId()+"");
						}
					}
					if(MisUtility.ifEmpty(obj[6])){
					masterForm.setLocationType(String.valueOf(obj[6]));
					}
					String parentLocation = getParentLocationName(obj[0].toString());
					if(MisUtility.ifEmpty(parentLocation)){
						masterForm.setParentLocation(parentLocation);
					}
					if(MisUtility.ifEmpty(obj[2])){
						masterForm.setStartDate(obj[2].toString());
					}
					
					if(MisUtility.ifEmpty(obj[3])){
						masterForm.setEndDate(obj[3].toString());
					}
					masterForm.setStatus(obj[4].toString());
					masterForm.setLocationId(obj[1].toString());
					locationMasterForm.add(masterForm);
				}

			}*/
			
			
			Session session=getSession();
			session.getTransaction().begin();
			
			String strQuery="SELECT aa.location_id, aa.location_name, aa.location_type_id, aa.parent_location,prwss_main.get_location_name(parent_location) as par,location,start_date,end_date,status  FROM ( SELECT ld.location_detail_id, ld.location_id, ld.location_name, lm.location_id AS dt_location_id, lm.location_type_id, lm.parent_location,lt.location_type_id AS location_type_id1, lt.name AS location,lm.start_date as start_date,lm.end_date as end_date,lm.active_flag as status   FROM prwss_main.location_details ld, prwss_main.location_master lm, prwss_main.location_type_master lt  WHERE lm.active_flag = 1 AND lt.active_flag = 1 AND ld.location_id = lm.location_id AND lm.location_type_id = lt.location_type_id) aa";
			Query query=session.createSQLQuery(strQuery);
			Iterator iterator=query.list().iterator();
			locationMasterForm = new ArrayList<LocationMasterForm>();
			while(iterator.hasNext()){
				Object[] object = (Object[])iterator.next();
				
				LocationMasterForm masterForm = new LocationMasterForm();
				if(MisUtility.ifEmpty(object[0])){
					masterForm.setLocationId(object[0].toString());
				}
				if(MisUtility.ifEmpty(object[1])){
					masterForm.setLocationName(object[1].toString());
				}
				if(MisUtility.ifEmpty(object[2])){
					masterForm.setLocationType(object[5].toString()+"-("+object[2].toString()+")");
				}
				if(MisUtility.ifEmpty(object[3])&&MisUtility.ifEmpty(object[4])){
					masterForm.setParentLocation(object[4].toString()+"-("+object[3].toString()+")");
				}
				if(MisUtility.ifEmpty(object[8])){
					masterForm.setStatus(object[8].toString());
				}
				if(MisUtility.ifEmpty(object[6])){
					if(object[6] instanceof java.util.Date){
						 java.util.Date mydate = (java.util.Date) object[6];
						  java.sql.Date sqlDate = new java.sql.Date(mydate.getTime());
						 masterForm.setStartDate(MisUtility.convertDateString(sqlDate));
					}
					
				}
				if(MisUtility.ifEmpty(object[7])){
					java.util.Date mydate = (java.util.Date) object[6];
					  java.sql.Date sqlDate = new java.sql.Date(mydate.getTime());
					masterForm.setEndDate(MisUtility.convertDateString(sqlDate));
				}
				
				locationMasterForm.add(masterForm);
				
				
				
				/*LocationDetailsBean locationDetails= (LocationDetailsBean)object;
				String ss=locationDetails.getLocationName();*/
				
				
			}
			
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterForm;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocationTypeBean> getLocationType(LocationTypeBean locationTypeBean) throws DataAccessException {
		List<LocationTypeBean> locationMasterBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);

			criteria.add(Restrictions.eq("activeFlag", locationTypeBean.getActiveFlag()));
			if (MisUtility.ifEmpty(locationTypeBean.getLocationName()))
				criteria.add(Restrictions.eq("locationName", locationTypeBean.getLocationName()));

			locationMasterBeans = getHibernateTemplate().findByCriteria(criteria);
			System.out.println("locationMasterBeans------------" + locationMasterBeans.size());

		} catch (DataAccessException e) {
			e.printStackTrace();

			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterBeans;
	}

	/*@SuppressWarnings("unchecked")
	private List<LocationTypeBean> getLocationTypes(String locationType) throws DataAccessException {
		List<LocationTypeBean> locationMasterBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			if (MisUtility.ifEmpty(locationType))
				criteria.add(Restrictions.eq("locationTypeId", locationType));

			locationMasterBeans =  getHibernateTemplate().findByCriteria(criteria);
			System.out.println("locationMasterBeans------------" + locationMasterBeans.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();

			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterBeans;
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDetailsBean> getParentLocation(int locationTypeId) throws DataAccessException {
		List<LocationDetailsBean> locationDetailsBean1=null;
		//List<LocationMasterDto> locationDetailsBean=null;
		try {
			
			
			
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class)
					.createAlias("locationMasterBean", "locationMasterBean")
					.add(Restrictions.eq("locationMasterBean.locationTypeId",locationTypeId))
					.add(Restrictions.eq("locationMasterBean.activeField",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			locationDetailsBean1=getHibernateTemplate().findByCriteria(criteria);

			/*DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class)
					.createAlias("locationMasterBean", "locationMasterBean")
					.add(Restrictions.eq("locationMasterBean.locationTypeId",locationTypeId))
					.add(Restrictions.eq("locationMasterBean.activeField",Integer.parseInt(MISConstants.ACTIVE_STATUS)))
							.setProjection(Projections.property("locationMasterBean.locationId"))
							.setProjection(Projections.property("locationName"));
			locationDetailsBean=getHibernateTemplate().findByCriteria(criteria);*/
			/*locationDetailsBean=getSession().createCriteria(LocationDetailsBean.class,"locationDetailsBean")
			.createCriteria("locationDetailsBean.locationMasterBean","locationMasterBean")	
			.add(Restrictions.eq("locationMasterBean.locationTypeId",locationTypeId))
			.add(Restrictions.eq("locationMasterBean.activeField",Integer.parseInt(MISConstants.ACTIVE_STATUS)))
					.setProjection(Projections.property("locationMasterBean.locationId"))
					.setProjection(Projections.property("locationDetailsBean.locationName"))
					.setProjection(Projections.property("locationMasterBean.locationTypeId"))

					.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class)).list();*/
			
			/*				
			locationDetailsBean=getSession().createCriteria(LocationDetailsBean.class,"locationDetailsBean")
						.createCriteria("locationDetailsBean.locationMasterBean","locationMasterBean")		
				.add( Property.forName("locationMasterBean.locationId").in(criteria))
				.add(Restrictions.eq("locationMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)))
						.setProjection( Projections.projectionList()
		                 .add(Projections.property("locationDetailsBean.locationName").as("locationName"))
		                 .add(Projections.property("locationMasterBean.locationId").as("locationId")))
						.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class)).list();*/
				
				
		}catch (DataAccessException e) {
			e.printStackTrace();

			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return locationDetailsBean1;
	}
	
	@SuppressWarnings("unchecked")
	public List<LocationDetailsBean> findMasterLocation(LocationMasterForm locationMasterForm)throws DataAccessException{
		List<LocationDetailsBean>   locationDetailsBean=null;
		try{
			if(MisUtility.ifEmpty(locationMasterForm)){
				if(MisUtility.ifEmpty(locationMasterForm.getLocationId())){
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class)
						.createAlias("locationMasterBean", "locationMasterBean");
						criteria.add(Restrictions.eq("locationMasterBean.activeField", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
						criteria.add(Restrictions.eq("locationMasterBean.locationId", Integer.parseInt(locationMasterForm.getLocationId())));
						
						
						locationDetailsBean =  getHibernateTemplate().findByCriteria(criteria);
						System.out.println("locationMasterBeans------------" + locationDetailsBean.toString());
				}
			}
		
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return locationDetailsBean;
	}


	@SuppressWarnings("unchecked")
	public String getParentLocationName(int parentLocId) throws DataAccessException {
		String locationName=null;
		
		try {

			
			//DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);

			List<Object[]> obj = getSession().createCriteria(LocationDetailsBean.class, "locationDetailsBean")
					.createCriteria("locationDetailsBean.locationMasterBean", "locationMasterBean")
					.add(Restrictions.eq("locationMasterBean.locationId",parentLocId))
					.add(Restrictions.eq("locationMasterBean.activeField",
							Integer.parseInt(MISConstants.ACTIVE_STATUS)))
					.setProjection(
							Projections.projectionList().add(Projections.property("locationDetailsBean.locationName"))
							.add(Projections.property("locationMasterBean.activeField")))
					.list();
			
				if(!MisUtility.ifEmpty(obj)){
					for(Object[] objj:obj){
						locationName=objj[0].toString();
					}
				}
			
				this.getSession().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locationName;
	}

	@Override
	public boolean updateMasterLocation(LocationDetailsBean locationMasterDetails) throws DataAccessException {
		Session session=getSession();
		boolean flag=false;
		int status=0;
		try{
			
			/*String hqlUpdate="update LocationMasterBean details set details.locationMasterBean.activeField = '"+locationMasterDetails.getLocationMasterBean().getActiveField()+"' "
					+ "where details.locationMasterBean.locationId = '"+locationMasterDetails.getLocationMasterBean().getLocationId()+"' and  details.locationMasterBean.activeField=1" ;
			*/
			session.getTransaction().begin();
			String sqlQuery="update prwss_main.location_master set active_flag=0 where location_id= '"+locationMasterDetails.getLocationMasterBean().getLocationId()+"' and active_flag=1";
			status=session.createSQLQuery(sqlQuery).executeUpdate();
			session.getTransaction().commit();
			session.close();
			if(status==1){
				flag=true;
			}
		}catch(DataAccessException e){
			session.getTransaction().rollback();
			session.close();
			
			e.printStackTrace();

			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		getSession().close();
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDetailsBean> getLocation(LocationMasterForm locationMasterForm) throws DataAccessException{
	
		List<LocationDetailsBean> locationDetailsBeans=null;
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class)
		.createAlias("locationMasterBean", "locationMasterBean");
		criteria.add(Restrictions.eq("locationMasterBean.activeField", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		if (MisUtility.ifEmpty(locationMasterForm.getLocationType()))
			criteria.add(Restrictions.eq("locationMasterBean.locationTypeId", Integer.parseInt(locationMasterForm.getLocationType())));
		
		if (MisUtility.ifEmpty(locationMasterForm.getParentLocation()))
			criteria.add(Restrictions.eq("locationMasterBean.parentLocation", Integer.parseInt(locationMasterForm.getParentLocation())));
		
		if (MisUtility.ifEmpty(locationMasterForm.getLocationName()))
			criteria.add(Restrictions.eq("locationName", locationMasterForm.getLocationName()));
		
		locationDetailsBeans =  getHibernateTemplate().findByCriteria(criteria);
		System.out.println("locationMasterBeans------------" + locationDetailsBeans.toString());
		
		
		
	/*	locationMaterDto= getSession().createCriteria(LocationDetailsBean.class,"locationDetailsBean")
				.createCriteria("locationDetailsBean.locationMasterBean","locationMasterBean")		
				.add(Restrictions.eq("locationMasterBean.locationTypeId",locationTypeId))
				.add(Restrictions.eq("locationMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)))
						.setProjection( Projections.projectionList()
		                 .add(Projections.property("locationDetailsBean.locationName").as("locationName"))
		                 .add(Projections.property("locationMasterBean.locationId").as("locationId")))
						.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class)).list();*/
	}catch(DataAccessException e){
		e.printStackTrace();

		log.error(e.getLocalizedMessage(),e);
		throw e;
	}
		return locationDetailsBeans;
	}
	
	
	
	
}
