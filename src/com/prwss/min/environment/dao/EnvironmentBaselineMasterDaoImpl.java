package com.prwss.min.environment.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvPondBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvWaterDisposableBean;
import com.prwss.min.environment.bean.EnvironmentBaselineWaterLoggingBean;
import com.prwss.min.environment.bo.EnvironmentBaselineMasterBoImpl;
import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentBaselineMasterDaoImpl extends HibernateDaoSupport implements EnvironmentBaselineMasterDao{

	private Logger log = Logger.getLogger(EnvironmentBaselineMasterDaoImpl.class);
	
	@Override
	public int saveMasterDataBaseline(EnvironmentBaselineBean baselineBean)
			throws DataAccessException, MISException {
		try {
			getHibernateTemplate().save(baselineBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return baselineBean.getEds_bsln_env_id();
	}

	@Override
	public void saveMasterDataBaselinePond( List<EnvironmentBaselineEnvPondBean> baselineEnvPondBean)
			throws DataAccessException, MISException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(baselineEnvPondBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void saveMasterDataBaselineWD(
			List<EnvironmentBaselineEnvWaterDisposableBean> baselineEnvWDBean)
			throws DataAccessException, MISException {
		try {
			getHibernateTemplate().saveOrUpdateAll(baselineEnvWDBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		
	}

	@Override
	public void saveMasterDataBaselineWaterLogged(
			EnvironmentBaselineWaterLoggingBean environmentWaterLoggedbean)
			throws DataAccessException, MISException {
		try {
			getHibernateTemplate().save(environmentWaterLoggedbean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		
	}

	@Override
	public List<EnvironmentBaselineBean> getDataBaseline(
			String appId) throws DataAccessException, MISException {
		
		
		System.out.println("inside dao-------" + appId);
		List<EnvironmentBaselineBean>  form = null;
		try {
			if (MisUtility.ifEmpty(appId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(EnvironmentBaselineBean.class, "environmentBaselineBean")
						//.createCriteria( "locationDetailsBean.locationMasterBean", "locationMasterBean")
						
						//.add(Restrictions.eq( "locationMasterBean.locationTypeId", parentLocationId))
						.add(Restrictions.eq("environmentBaselineBean.eds_id", Integer.parseInt(appId.trim())));
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
	public int updateMasterDataBaseline(EnvironmentBaselineBean baselineBean)
			throws DataAccessException, MISException {
		try{
		      getHibernateTemplate().update(baselineBean);
		    }catch(DataAccessException e){
		      log.debug(e.getLocalizedMessage());
		      throw e;
		    }
		    return baselineBean.getEds_id();
	}
	
	
	

	
}
