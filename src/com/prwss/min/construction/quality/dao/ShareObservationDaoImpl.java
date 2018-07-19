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

import com.prwss.min.construction.quality.bean.CCBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;
import com.prwss.min.construction.quality.bean.ShareCommonObservationBean;
import com.prwss.min.construction.quality.bean.ShareObservationBean;
import com.prwss.min.construction.quality.form.ShareObservationForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;

/**
 * @author BH390738
 *
 */
public class ShareObservationDaoImpl extends HibernateDaoSupport implements ShareObservationDao {
	private Logger log = Logger.getLogger(ShareObservationDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyPlanDto> getEmployee(List<String> statusList, String designationId) {
		List<MonthlyPlanDto> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class, "employeeBean")
					.add(Restrictions.eq("employeeBean.designationId", designationId))
					.add(Restrictions.in("misAuditBean.status", statusList))
					.setProjection(Projections.projectionList()
							.add(Projections.property("employeeBean.employeeId"), "employeeId")
							.add(Projections.property("employeeBean.employeeName"), "employeeName"));
			criteria.setResultTransformer(Transformers.aliasToBean(MonthlyPlanDto.class));
			employeeBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
			throw e;
		}
		return employeeBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeDesignationBean> getEmployeeDesig() throws DataAccessException {

		List<EmployeeDesignationBean> employeeBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeDesignationBean.class);

			employeeBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		// System.out.println("daooooooo+++++++++++"+employeeBeans);
		return employeeBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getScheme(String yearPlanId, String monthlyId) throws DataAccessException {
		List<Integer> schemeIds=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(SaveMonthlyReportObservationBean.class,"saveMonthlyReportObservationBean");
			criteria.createAlias("saveMonthlyReportObservationBean.monthlyPlanInspectionBean", "monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(yearPlanId)));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(monthlyId)));
			
			criteria.setProjection(Projections.property("saveMonthlyReportObservationBean.scheme"));
			
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getComments(String scheme) throws DataAccessException {
	
		List<String> comments=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(SaveMonthlyReportObservationBean.class);
			criteria.add(Restrictions.eq("scheme", Integer.parseInt(scheme)));
			criteria.setProjection(Projections.property("observation"));
			comments=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return comments;
	}

	@Override
	public boolean saveObservation(List<ShareObservationBean> shareObservationBeans) throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(shareObservationBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveCc(List<CCBean> ccBeans) throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(ccBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@Override
	public Long saveCommonObservationData(ShareCommonObservationBean shareCommonObservationBeans)
			throws DataAccessException {
		try{
			getHibernateTemplate().save(shareCommonObservationBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		
		return shareCommonObservationBeans.getObsId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getMonthlyPlanId(ShareObservationForm shareObservationForm) throws DataAccessException {
		List<Long> monthlyPlanIds=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(SaveMonthlyReportObservationBean.class,"saveMonthlyReportObservationBean");
			criteria.createAlias("saveMonthlyReportObservationBean.monthlyPlanInspectionBean", "monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(shareObservationForm.getYearPlan())));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(shareObservationForm.getMonth())));
			
			criteria.setProjection(Projections.property("saveMonthlyReportObservationBean.monthlyPlanId"));
			
			monthlyPlanIds=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanIds;
	}
	
}
