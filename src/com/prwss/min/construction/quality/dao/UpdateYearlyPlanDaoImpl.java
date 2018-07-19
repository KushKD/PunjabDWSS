/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;
import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class UpdateYearlyPlanDaoImpl extends HibernateDaoSupport implements UpdateYearlyPlanDao {

	private Logger log = Logger.getLogger(UpdateYearlyPlanDaoImpl.class);
	@Override
	public boolean updateYearPlan() throws DataAccessException {
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearlyPlanInspectionBean> getYearlyPlanDetails(YearlyPlanInspectionForm yearlyPlanInspectionForm) throws DataAccessException {
		
		List<YearlyPlanInspectionBean> yearlyPlanInspectionBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(YearlyPlanInspectionBean.class,"yearlyPlanInspectionBean");
			criteria.createAlias("yearlyPlanInspectionBean.yearlyPlanningComponentMappingBeans", "yearlyPlanningComponentMappingBeans");
			if(MisUtility.ifEmpty(yearlyPlanInspectionForm.getFinancialYear())){
				criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(yearlyPlanInspectionForm.getFinancialYear())));
			}
			if(MisUtility.ifEmpty(yearlyPlanInspectionForm.getComponent())){
				criteria.add(Restrictions.eq("yearlyPlanningComponentMappingBeans.component", Integer.parseInt(yearlyPlanInspectionForm.getComponent())));
			}
			yearlyPlanInspectionBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return yearlyPlanInspectionBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearlyPlanInspectionBean> getYearlyPlanDetails(String yearlyPlanId) throws DataAccessException {
		
		List<YearlyPlanInspectionBean> yearlyPlanInspectionBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(YearlyPlanInspectionBean.class);
			criteria.add(Restrictions.eq("yearlyPlanId", Integer.parseInt(yearlyPlanId)));
			
			yearlyPlanInspectionBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return yearlyPlanInspectionBeans;
	}

	@Override
	public boolean updateYearlyPlanDetails(YearlyPlanInspectionBean yearlyPlanInspectionBean)
			throws DataAccessException {
		try{
			getHibernateTemplate().update(yearlyPlanInspectionBean);
		}catch(DataAccessException e){
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateComponentYearlyPlanDetails(YearlyPlanningComponentMappingBean yearlyPlanningComponentMappingBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().update(yearlyPlanningComponentMappingBean);
		}catch(DataAccessException e){
			
		}
		return true;
	}

}
