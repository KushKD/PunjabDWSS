package com.prwss.min.environment.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentWaterSchemeBean;
import com.prwss.min.environment.bean.WaterSupplyGridBean;
import com.prwss.min.environment.bean.WaterSupplyNatureQualityBean;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentWaterSupplyDaoImpl extends  HibernateDaoSupport implements EnvironmentWaterSupplyDao {
	
	private Logger log = Logger.getLogger(EnvironmentWaterSupplyDaoImpl.class);

	@Override
	public int saveMasterDataWaterSupply(
			EnvironmentWaterSchemeBean waterSupplyBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(waterSupplyBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return waterSupplyBean.getWater_supp_sch_id();
	}

	@Override
	public void saveWaterSupplyQualityNature(
			List<WaterSupplyNatureQualityBean> waterSupplyNatureQualityBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(waterSupplyNatureQualityBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		
	}

	@Override
	public int saveGridData(List<WaterSupplyGridBean> waterSupplyGridBean)
			throws DataException {
		try{
			getHibernateTemplate().saveOrUpdateAll(waterSupplyGridBean);
			return 1;
		}catch(DataAccessException ex){
			ex.printStackTrace();
			return 0;
			
		}
	}

	@Override
	public int updateMasterDataBaseline(EnvironmentWaterSchemeBean bean)
			throws DataAccessException {
		try{
		      getHibernateTemplate().update(bean);
		    }catch(DataAccessException e){
		      log.debug(e.getLocalizedMessage());
		      throw e;
		    }
		    return bean.getEds_id();
	}

	@Override
	@Transactional
	public List<EnvironmentWaterSchemeBean> getDataBaseline(String appId)
			throws DataAccessException {

		
		System.out.println("inside dao-------" + appId);
		List<EnvironmentWaterSchemeBean>  form = null;
		try {
			if (MisUtility.ifEmpty(appId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(EnvironmentWaterSchemeBean.class, "environmentBaselineBean")
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

}
