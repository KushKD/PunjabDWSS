package com.prwss.mis.admin.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.quality.struts.LocationTypeForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LocationTypeDaoImpl extends HibernateDaoSupport implements LocationTypeDao {
	
	private Logger log = Logger.getLogger(LocationTypeDaoImpl.class);

	@Override
	public boolean saveMasterLocation(LocationTypeBean locationMasterBean) throws DataAccessException {

		try {

			System.out.println("in dao ++++schemeVillageBean+++++++" + locationMasterBean.toString());

			getHibernateTemplate().flush();

			getHibernateTemplate().save(locationMasterBean);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();

		} catch (DataAccessException e) {
			// e.printStackTrace();
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
	public List<LocationTypeBean> getLocationMasterByPagination()
			throws DataAccessException {

		List<LocationTypeBean> locationMasterBeans = null;
		try {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

				locationMasterBeans = getHibernateTemplate().findByCriteria(criteria);
				
				
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationTypeBean> getLocationType(LocationTypeForm locationTypeBean) throws DataAccessException {
		List<LocationTypeBean> locationTypeBeans = null;
		try {
			if (MisUtility.ifEmpty(locationTypeBean)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationTypeBean.class);
				

				if (MisUtility.ifEmpty(locationTypeBean.getLocationTypeId())){
					criteria.add(Restrictions.eq("locationTypeId", Integer.parseInt(locationTypeBean.getLocationTypeId())));
				}
				if (MisUtility.ifEmpty(locationTypeBean.getStatus())){
					criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				}
				locationTypeBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return locationTypeBeans;
	}

	@Override
	public boolean updateMasterLocationType(LocationTypeBean locationTypeBean) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			getHibernateTemplate().saveOrUpdate(locationTypeBean);
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
	public List<LocationTypeBean> getLocationTypeBean(LocationTypeForm locationTypeForm)  throws DataAccessException{
		List<LocationTypeBean> locationTypeBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(LocationTypeBean.class);
			criteria.add(Restrictions.eq("locationName", locationTypeForm.getLocationType()));
			criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			locationTypeBeans=getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			 e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

			log.error(e.getLocalizedMessage(), e);
			throw e;
			// throw new MISException(e);
			// throw new MISException(e);

		}
		return locationTypeBeans;
	}

}
