/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.form.MonthlyProgressForm;
import com.prwss.min.construction.quality.form.SaveMonthlyReportForm;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;

/**
 * @author BH390738
 *
 */
public class MonthlyProgressDaoImpl extends HibernateDaoSupport implements MonthlyProgressDao {

	private Logger log = Logger.getLogger(MonthlyProgressDaoImpl.class);

	@Override
	public boolean saveMonthlyProgress(List<MonthlyProgressBean> monthlyProgressBeans) throws DataAccessException {
		
		try{
			getHibernateTemplate().saveOrUpdateAll(monthlyProgressBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getPerVisitedVillages(String yerlyPlanId, String componentId, String monthlyId, String teamId)
			throws DataAccessException {
		
		List<Integer> monthlyPlanInspectionBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyPlanInspectionBean.class);
			criteria.add(Restrictions.eq("yearlyPlanId", Integer.parseInt(yerlyPlanId)));
			criteria.add(Restrictions.eq("component", Integer.parseInt(componentId)));
			criteria.add(Restrictions.eq("monthId", Integer.parseInt(monthlyId)));
			criteria.add(Restrictions.eq("team", Integer.parseInt(teamId)));
			criteria.setProjection(Projections.property("villageToBeVisited"));
			monthlyPlanInspectionBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			
			throw e;
		}
		return monthlyPlanInspectionBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyProgressBean> pupolateMonthlyProgressBean(MonthlyProgressForm monthlyProgressForm)
			throws DataAccessException {
		
		List<MonthlyProgressBean> monthlyProgressBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyProgressBean.class,"monthlyProgressBean");
			criteria.createAlias("monthlyProgressBean.monthlyPlanInspectionBean", "monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(monthlyProgressForm.getYearPlan())));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(monthlyProgressForm.getMonth())));
			criteria.addOrder(Order.asc("monthlyProgressId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			monthlyProgressBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return monthlyProgressBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyProgressBean> pupolateMonthlyProgressBean(SaveMonthlyReportForm saveMonthlyReportForm)
			throws DataAccessException {
		List<MonthlyProgressBean> monthlyProgressBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyProgressBean.class,"monthlyProgressBean");
			criteria.createAlias("monthlyProgressBean.monthlyPlanInspectionBean", "monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(saveMonthlyReportForm.getYearPlan())));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(saveMonthlyReportForm.getMonth())));
			criteria.addOrder(Order.asc("monthlyProgressId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			monthlyProgressBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return monthlyProgressBeans;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyProgressBean> pupolateMonthlyProgressBean(SendMonthlyReportForm sendMonthlyReportForm)
			throws DataAccessException {
		List<MonthlyProgressBean> monthlyProgressBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyProgressBean.class,"monthlyProgressBean");
			criteria.createAlias("monthlyProgressBean.monthlyPlanInspectionBean", "monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(sendMonthlyReportForm.getYearPlan())));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(sendMonthlyReportForm.getMonth())));
			criteria.addOrder(Order.asc("monthlyProgressId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			monthlyProgressBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return monthlyProgressBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getMonthlyPlanId(Integer yearPlanId, String monthId,String teamId) throws DataAccessException {
		List<Integer> monthlyPlanIds;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyPlanInspectionBean.class,"monthlyPlanInspectionBean");
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.yearlyPlanId", yearPlanId));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(monthId)));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.team", Integer.parseInt(teamId)));
			criteria.setProjection(Projections.property("monthlyPlanInspectionBean.monthlyPlanId"));
			monthlyPlanIds=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanIds;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyProgressBean> getMonthlyProgress(Integer monthlyPlanId) throws DataAccessException {
		List<MonthlyProgressBean> monthlyProgressBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyProgressBean.class);
			criteria.add(Restrictions.eq("monthlyPlanId", monthlyPlanId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			monthlyProgressBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		
		return monthlyProgressBeans;
	}


}
