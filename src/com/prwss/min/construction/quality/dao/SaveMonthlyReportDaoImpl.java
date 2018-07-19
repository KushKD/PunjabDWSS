/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;

/**
 * @author BH390738
 *
 */
public class SaveMonthlyReportDaoImpl extends HibernateDaoSupport implements SaveMonthlyReportDao{

	private Logger log = Logger.getLogger(SaveMonthlyReportDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getScheme(String yearPlanId, String monthlyId)throws DataAccessException {
		
		List<Long> schemeIds=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyPlanInspectionBean.class,"monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.monthlyPlanSchemeMappingBeans", "monthlyPlanSchemeMappingBeans");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(yearPlanId)));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(monthlyId)));
			
			criteria.setProjection(Projections.property("monthlyPlanSchemeMappingBeans.scheme_id"));
			
			schemeIds=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return schemeIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyPlanDto> getSchemeName(List<Integer> schemeIds) throws DataAccessException {
		
		List<MonthlyPlanDto> monthlyPlanDtos=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyPlanInspectionBean.class,"monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.monthlyPlanSchemeMappingBeans", "monthlyPlanSchemeMappingBeans");
			criteria.createAlias("monthlyPlanSchemeMappingBeans.pmsSchemeDetailsBean", "pmsSchemeDetailsBean");
			criteria.add(Restrictions.in("pmsSchemeDetailsBean.scheme_id", schemeIds));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("pmsSchemeDetailsBean.scheme_id"),"schmId")
					.add(Projections.property("pmsSchemeDetailsBean.schemeName"),"schemeName")
					);
			criteria.setResultTransformer(Transformers.aliasToBean(MonthlyPlanDto.class));
			monthlyPlanDtos=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanDtos;
	}

	@Override
	public boolean saveMonthlyReport(List<SaveMonthlyReportObservationBean> saveMonthlyReportObservationBeans)throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(saveMonthlyReportObservationBeans);
		}catch(DataAccessException e){
			throw e;
		}
		return true;
	}

}
