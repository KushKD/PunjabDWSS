package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanSchemeMappingBean;
import com.prwss.min.construction.quality.bean.TeamMasterBean;
import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MonthlyPlanDaoImpl extends HibernateDaoSupport implements MonthlyPlanDao {

	private Logger log = Logger.getLogger(MonthlyPlanDaoImpl.class);

	@Override
	public int saveMonthlyPlan(MonthlyPlanInspectionBean monthlyPlanInspectionBean) throws DataAccessException {

		try {
			getHibernateTemplate().save(monthlyPlanInspectionBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanInspectionBean.getMonthlyPlanId();
	}

	@Override
	public boolean saveMonthlyPlanSchemeMapping(List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(monthlyPlanSchemeMappingBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearlyPlanInspectionBean> getPerMonthVisitedVillages(String yearlyPlanId, String componentId)
			throws DataAccessException {

		List<YearlyPlanInspectionBean> yearlyPlanInspectionBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(YearlyPlanInspectionBean.class,
					"yearlyPlanInspectionBean");
			criteria.createAlias("yearlyPlanInspectionBean.yearlyPlanningComponentMappingBeans",
					"yearlyPlanningComponentMappingBeans");

			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(yearlyPlanId)));
			criteria.add(
					Restrictions.eq("yearlyPlanningComponentMappingBeans.component", Integer.parseInt(componentId)));

			yearlyPlanInspectionBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return yearlyPlanInspectionBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeamMasterBean> fetchTeam(String financialYearId) throws DataAccessException {
		List<TeamMasterBean> teamMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TeamMasterBean.class);
			criteria.add(Restrictions.eq("financial_year", Integer.parseInt(financialYearId)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			teamMasterBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return teamMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyPlanInspectionBean> fetchMonthlyPlanSchemeMapping(Integer yearlyPlanId,
			MonthlyPlanInspectionForm monthlyPlanInspectionForm) throws DataAccessException {
		
		List<MonthlyPlanInspectionBean> monthlyPlanInspectionBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyPlanInspectionBean.class,"monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanningComponentMappingBean", "yearlyPlanningComponentMappingBean");
			criteria.add(Restrictions.eq("yearlyPlanningComponentMappingBean.yearlyPlanId", yearlyPlanId));
			criteria.add(Restrictions.eq("component", Integer.parseInt(monthlyPlanInspectionForm.getComponent())));
			criteria.add(Restrictions.eq("monthId", Integer.parseInt(monthlyPlanInspectionForm.getMonth())));
			criteria.add(Restrictions.eq("team", Integer.parseInt(monthlyPlanInspectionForm.getTeam())));
			
			monthlyPlanInspectionBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanInspectionBeans;
	}

	@Override
	public boolean updateMonthlyPlanSchemeMapping(List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans)
			throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(monthlyPlanSchemeMappingBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyPlanInspectionBean> findMonthlyPlan(String monthlyPlanId) throws DataAccessException {
		
		List<MonthlyPlanInspectionBean> monthlyPlanInspectionBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(MonthlyPlanInspectionBean.class);
			criteria.add(Restrictions.eq("monthlyPlanId",Integer.parseInt(monthlyPlanId)));
			monthlyPlanInspectionBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanInspectionBeans;
	}

	@Override
	public boolean updateMonthlyPlan(MonthlyPlanInspectionBean monthlyPlanSchemeMappingBeans)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().save(monthlyPlanSchemeMappingBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyPlanDto> getMonthlyPlanData(MonthlyPlanInspectionForm monthlyPlanInspectionForm,String searchString, int clickedColumn, String colOrder) throws DataAccessException {
		
		List<MonthlyPlanDto> monthlyPlanDtos = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(MonthlyPlanInspectionBean.class, "monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.monthlyPlanSchemeMappingBeans", "monthlyPlanSchemeMappingBeans");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanningComponentMappingBean", "yearlyPlanningComponentMappingBean");
			criteria.createAlias("monthlyPlanInspectionBean.combodetailComponent", "combodetailComponent");
				
			criteria.createAlias("monthlyPlanSchemeMappingBeans.pmsSchemeDetailsBean", "pmsSchemeDetailsBean");
			criteria.createAlias("monthlyPlanSchemeMappingBeans.districtDetailBean", "districtDetailBean");
			criteria.createAlias("monthlyPlanSchemeMappingBeans.locationDivisonDetailsBean", "locationDivisonDetailsBean");
			criteria.createAlias("monthlyPlanSchemeMappingBeans.locationConstituencyDetailsBean", "locationConstituencyDetailsBean");
			criteria.createAlias("monthlyPlanInspectionBean.teamMasterBean", "teamMasterBean");
			
			if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getYearPlan()))
				criteria.add(Restrictions.eq("monthlyPlanInspectionBean.yearlyPlanId", Integer.parseInt(monthlyPlanInspectionForm.getYearPlan())));

			if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getMonth()))
				criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId", Integer.parseInt(monthlyPlanInspectionForm.getMonth())));

			if (MisUtility.ifEmpty(Integer.parseInt(String.valueOf(monthlyPlanInspectionForm.getLoginUser())))) {
				criteria.add(Restrictions.eq("monthlyPlanInspectionBean.crtByUsr",
						Integer.parseInt(String.valueOf(monthlyPlanInspectionForm.getLoginUser()))));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("combodetailComponent.cmb_name", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("pmsSchemeDetailsBean.schemeName", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("districtDetailBean.locationName", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("teamMasterBean.team_name", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("locationDivisonDetailsBean.DivisonSubDivisonDetailsName", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("locationConstituencyDetailsBean.ConstituencyDetailsName", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("villages_visited_per_month::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("villages_to_be_visited_month::text like '%" + searchString + "%'")));
						
			}
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.activeFlag", Integer.parseInt(MISConstants.ONE)));
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("combodetailComponent.cmb_name"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("combodetailComponent.cmb_name"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("pmsSchemeDetailsBean.schemeName"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("pmsSchemeDetailsBean.schemeName"));
				} else if (MISConstants.CLICKED_SIX.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("districtDetailBean.locationName"));
				} else if (MISConstants.CLICKED_SIX.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("districtDetailBean.locationName"));
				} else if (MISConstants.CLICKED_SEVEN.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("locationDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				} else if (MISConstants.CLICKED_SEVEN.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("locationDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				}else if (MISConstants.CLICKED_EIGHT.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("locationConstituencyDetailsBean.ConstituencyDetailsName"));
				}else if (MISConstants.CLICKED_EIGHT.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("locationConstituencyDetailsBean.ConstituencyDetailsName"));
				}
				
				else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("yearlyPlanningComponentMappingBean.visitedPerMonth"));
				}else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("yearlyPlanningComponentMappingBean.visitedPerMonth"));
				}
				
				else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("monthlyPlanInspectionBean.villageToBeVisited"));
				}else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("monthlyPlanInspectionBean.villageToBeVisited"));
				}else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("teamMasterBean.team_name"));
				}else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("teamMasterBean.team_name"));
				}
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("combodetailComponent.cmb_name"), "component")
					.add(Projections.property("pmsSchemeDetailsBean.schemeName"), "schemeName")
					.add(Projections.property("districtDetailBean.locationName"), "district")
					.add(Projections.property("locationDivisonDetailsBean.DivisonSubDivisonDetailsName"), "division")
					.add(Projections.property("locationConstituencyDetailsBean.ConstituencyDetailsName"), "constituency")
					.add(Projections.property("yearlyPlanningComponentMappingBean.visitedPerMonth"), "visitPerMonth")
					.add(Projections.property("monthlyPlanInspectionBean.villageToBeVisited"), "villageToBeVisited")
					.add(Projections.property("teamMasterBean.team_name"), "teamName"));
			criteria.setResultTransformer(Transformers.aliasToBean(MonthlyPlanDto.class));
			
			monthlyPlanDtos=getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return monthlyPlanDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findYeralyPlan(String yearPlan, String component) throws DataAccessException {
		List<Integer> yearlyPlanId=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(YearlyPlanInspectionBean.class,"yearlyPlanInspectionBean");
			criteria.createAlias("yearlyPlanInspectionBean.yearlyPlanningComponentMappingBeans", "yearlyPlanningComponentMappingBeans");
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear", Integer.parseInt(yearPlan)));
			criteria.add(Restrictions.eq("yearlyPlanningComponentMappingBeans.component", Integer.parseInt(component)));
			criteria.setProjection(Projections.property("yearlyPlanningComponentMappingBeans.yearlyPlanId"));
			yearlyPlanId=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return yearlyPlanId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findYeralyPlan(String yearPlan) throws DataAccessException {
		List<Integer> yearlyPlanId=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(YearlyPlanInspectionBean.class);
			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(yearPlan)));
			criteria.setProjection(Projections.property("yearlyPlanId"));
			yearlyPlanId=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return yearlyPlanId;
	}
	

}
