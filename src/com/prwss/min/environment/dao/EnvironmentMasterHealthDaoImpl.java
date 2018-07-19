package com.prwss.min.environment.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.environment.bean.EnvironmentHealthBean;
import com.prwss.min.environment.bean.EnvironmentVectorBourneDiseaseBean;
import com.prwss.min.environment.bean.EnvironmentWaterBourneDiseaseBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public  class EnvironmentMasterHealthDaoImpl  extends HibernateDaoSupport implements EnvironmentMasterHealthDao {

	private Logger log = Logger.getLogger(EnvironmentMasterHealthDaoImpl.class);
	@Override
	public int saveMasterDataSocial(EnvironmentHealthBean healthBean)
			throws DataAccessException, MISException {
		try {
			getHibernateTemplate().save(healthBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return healthBean.getEds_pblc_hlth_id();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnvironmentHealthBean> getDataHealth(String appId)
			throws DataAccessException, MISException {
		List<EnvironmentHealthBean>  form = null;
		try {
			if (MisUtility.ifEmpty(appId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(EnvironmentHealthBean.class, "environmentSocialBean")
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
	public int updateMasterDataBaseline(EnvironmentHealthBean healthBean)
			throws DataAccessException, MISException {
		try{
		      getHibernateTemplate().update(healthBean);
		    }catch(DataAccessException e){
		      log.debug(e.getLocalizedMessage());
		      e.printStackTrace();
		      throw e;
		    }
		    return healthBean.getEds_id();
	}


	@Override
	public void saveWaterBourneDisease(
			List<EnvironmentWaterBourneDiseaseBean> waterBourneDiseaseBean)
			throws DataAccessException, MISException {
		try {
			getHibernateTemplate().saveOrUpdateAll(waterBourneDiseaseBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		
	}


	@Override
	public void saveVectorBourneDisease(
			List<EnvironmentVectorBourneDiseaseBean> vectorBourneDiseaseBean)
			throws DataAccessException, MISException {
		try {
			getHibernateTemplate().saveOrUpdateAll(vectorBourneDiseaseBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		
	}
	

}
