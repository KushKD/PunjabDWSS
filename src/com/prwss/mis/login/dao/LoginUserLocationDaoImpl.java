package com.prwss.mis.login.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.admin.dao.LocationTypeBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;

public class LoginUserLocationDaoImpl extends HibernateDaoSupport implements LoginUserLocationDao {
	
	
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getLoginUserLocations(String userId) throws DataAccessException {
		List<String> loginUserLocationBeans = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);
			criteria.add(Restrictions.eq("userId", userId).ignoreCase());
			//criteria.add(Restrictions.eq("rowActive", MISConstants.MIS_USER_LOCATION_ACTIVE));
			criteria.setProjection(Projections.property("locationId"));
			loginUserLocationBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}

		return loginUserLocationBeans;
	}

	@Override
	public boolean saveOrUpdateLoginUserLocations(Collection<LoginUserLocationBean> loginUserLocationBeans)
			throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(loginUserLocationBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<LoginUserLocationBean> findUserLocation(
			LoginUserLocationBean loginUserLocationBean,List<String> statusList) throws DataAccessException {
		List<LoginUserLocationBean> userLocationBeans = new ArrayList<LoginUserLocationBean>()  ;
		try {
			if(MisUtility.ifEmpty(loginUserLocationBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);
				if(MisUtility.ifEmpty(loginUserLocationBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", loginUserLocationBean.getLocationId()));
				if(MisUtility.ifEmpty(loginUserLocationBean.getUserId()))
					criteria.add(Restrictions.eq("userId", loginUserLocationBean.getUserId()));
//				criteria.add(Restrictions.eq("rowActive", MISConstants.MIS_USER_LOCATION_ACTIVE));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				userLocationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return userLocationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getLoginUserId(String userName) throws DataAccessException {
		List<Long> employeId = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserBean.class);
			criteria.add(Restrictions.eq("userId", userName).ignoreCase());
			//criteria.add(Restrictions.eq("rowActive", MISConstants.MIS_USER_LOCATION_ACTIVE));
			criteria.setProjection(Projections.property("employeeId"));
			employeId = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}

		return employeId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationTypeBean> getLocationTypeIds() throws DataAccessException {
		// TODO Auto-generated method stub
		List<String> locationTypes=new ArrayList<String>();
		
		List<LocationTypeBean> locationTypeBean=null;
		try{
		locationTypes.add("Division");
		locationTypes.add("DPMC");
		locationTypes.add("SPMC");
		locationTypes.add("Circle");
		locationTypes.add("Zone");
		locationTypes.add("SPONSER");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);
		criteria.add(Restrictions.in("locationName",locationTypes));
		criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		locationTypeBean = getHibernateTemplate().findByCriteria(criteria);
		
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return locationTypeBean;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocationTypeBean> getLocationTypeDistrictIds()throws DataAccessException{
		List<LocationTypeBean> locationTypeBean=null;
		
		try{
			
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);
			criteria.add(Restrictions.eq("locationName","DISTRICT"));
			criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			locationTypeBean = getHibernateTemplate().findByCriteria(criteria);
			
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return locationTypeBean;
	}
	
}
