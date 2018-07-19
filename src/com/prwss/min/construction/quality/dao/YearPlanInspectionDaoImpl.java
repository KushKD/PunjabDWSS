/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.construction.quality.bean.ExternalAgencyMasterBean;
import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;

/**
 * @author BH390738
 *
 */
public class YearPlanInspectionDaoImpl extends HibernateDaoSupport implements YearPlanInspectionDao {
	private Logger log = Logger.getLogger(YearPlanInspectionDaoImpl.class);

	@Override
	public int saveYearPlanDetails(YearlyPlanInspectionBean yearlyPlanInspectionBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(yearlyPlanInspectionBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return yearlyPlanInspectionBean.getYearlyPlanId();
	}

	@Override
	public boolean saveYearPlanComponentDetails(List<YearlyPlanningComponentMappingBean> yearlyPlanInspectionBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().saveOrUpdateAll(yearlyPlanInspectionBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExternalAgencyMasterBean> fetchExternalAgency() throws DataAccessException {
		
		List<ExternalAgencyMasterBean> externalAgencyMasterBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(ExternalAgencyMasterBean.class);
			externalAgencyMasterBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			
		}
		return externalAgencyMasterBeans;
	}

	
	
	
}
