package com.prwss.min.environment.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentSocialBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentSocialMasterDaoImpl extends HibernateDaoSupport implements EnvironmentSocialMasterDao{

	private Logger log = Logger.getLogger(EnvironmentSocialMasterDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnvironmentSocialBean> getDataBaseline(String appId)
			throws MISException, DataAccessException {
		List<EnvironmentSocialBean>  form = null;
		try {
			if (MisUtility.ifEmpty(appId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(EnvironmentSocialBean.class, "environmentSocialBean")
						//.createCriteria( "locationDetailsBean.locationMasterBean", "locationMasterBean")
						
						//.add(Restrictions.eq( "locationMasterBean.locationTypeId", parentLocationId))
						.add(Restrictions.eq("environmentSocialBean.eds_id", Integer.parseInt(appId.trim())));
						/*.setProjection( Projections .projectionList() .add(Projections .property( "locationDetailsBean.locationName") .as("locationName"))
										.add(Projections .property( "locationMasterBean.locationId") .as("locationId")));*/
				//criteria.setResultTransformer( new AliasToBeanResultTransformer( LocationMasterDto.class));
				
				form = getHibernateTemplate().findByCriteria(criteria);
				
				

			}
		} catch (DataAccessException e) {
			throw e;
		}

		return form;
	}

	@Override
	public int saveMasterDataSocial(EnvironmentSocialBean socialBean)
			throws MISException, DataAccessException {
		try {
			getHibernateTemplate().save(socialBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return socialBean.getEds_id();
	}

	@Override
	public int updateMasterDataBaseline(EnvironmentSocialBean socailBean)
			throws MISException, DataAccessException {
		
		try{
		      getHibernateTemplate().update(socailBean);
		    }catch(DataAccessException e){
		      log.debug(e.getLocalizedMessage());
		      e.printStackTrace();
		      throw e;
		    }
		    return socailBean.getEds_id();
	}

}
