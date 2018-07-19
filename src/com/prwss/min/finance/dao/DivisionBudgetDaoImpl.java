/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.DdoMasterBean;
import com.prwss.min.finance.bean.DivisionBudgetBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.NodalDivisionBean;
import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;

/**
 * @author BH390738
 * 
 */
public class DivisionBudgetDaoImpl extends HibernateDaoSupport implements DivisionBudgetDao {

	private Logger log = Logger.getLogger(DivisionBudgetDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentDetailsBean> getComponentType(String componentType, String componentId)
			throws DataAccessException {

		List<ComponentDetailsBean> componentDetailsBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);

			if (MisUtility.ifEmpty(componentType)) {
				criteria.add(Restrictions.eq("componentBean.componentType", Integer.parseInt(componentType)));
			}
			if (MisUtility.ifEmpty(componentId)) {
				criteria.add(Restrictions.eq("componentBean.parentCompoId", Integer.parseInt(componentId)));
			}
			criteria.add(Restrictions.eq("componentBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			componentDetailsBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return componentDetailsBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentDetailsBean> getChildComponent(String componentType, String componentId)
			throws DataAccessException {

		List<ComponentDetailsBean> componentDetailsBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);

			if (MisUtility.ifEmpty(componentType)) {
				criteria.add(Restrictions.eq("componentBean.componentType", Integer.parseInt(componentType)));
			}
			if (MisUtility.ifEmpty(componentId)) {
				criteria.add(Restrictions.eq("componentBean.parentCompoId", Integer.parseInt(componentId)));
			}
			criteria.add(Restrictions.eq("componentBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			componentDetailsBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return componentDetailsBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getSubComponentLevel1(String componentType, String componentId) throws DataAccessException {

		List<Long> subComponentLevel1 = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);

			if (MisUtility.ifEmpty(componentType)) {
				criteria.add(Restrictions.eq("componentBean.componentType", Integer.parseInt(componentType)));
			}
			if (MisUtility.ifEmpty(componentId)) {
				criteria.add(Restrictions.eq("componentBean.parentCompoId", Integer.parseInt(componentId)));
			}
			criteria.add(Restrictions.eq("componentBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.property("componentDetailsId"));
			subComponentLevel1 = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return subComponentLevel1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentDetailsBean> getActivity(List<Integer> componentDetailsId) throws DataAccessException {

		List<ComponentDetailsBean> componentDetailsBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);
			criteria.add(
					Restrictions.eq("componentBean.componentType", Integer.parseInt(MISConstants.TWO_HUNDRED_NINE)));
			if (!MisUtility.ifEmpty(componentDetailsId)) {
				criteria.add(Restrictions.in("componentBean.parentCompoId", componentDetailsId));
			}
			criteria.add(Restrictions.eq("componentBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			componentDetailsBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}

		return componentDetailsBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> getDivisionBudgetBean() throws DataAccessException {

		List<FinanceDto> componentDetailsBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("locationDivisionSubDivisonDetailsBean", "locationDivisionSubDivisonDetailsBean",
					CriteriaSpecification.LEFT_JOIN);

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.projectionList().add(Projections.property("divisionId"), "divisionId")
					.add(Projections.property("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"),
							"divisonSubDivisonDetailsName"));
			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			componentDetailsBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return componentDetailsBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getSubComponentLevel2(List<Integer> subComponentLevel1) throws DataAccessException {
		List<Long> subComponentLevel12 = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentDetailsBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);

			if (!MisUtility.ifEmpty(subComponentLevel1)) {
				criteria.add(Restrictions.in("componentBean.parentCompoId", subComponentLevel1));
			}
			criteria.add(Restrictions.eq("componentBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.property("componentDetailsId"));
			subComponentLevel12 = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return subComponentLevel12;
	}

	@Override
	public boolean save(DivisionBudgetBean divisionBudgetBean) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdate(divisionBudgetBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateBudgetData(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeDtos = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("combodetailFinancialYear", "combodetailFinancialYear");
			criteria.createAlias("locationDivisionSubDivisonDetailsBean", "locationDivisionSubDivisonDetailsBean",
					CriteriaSpecification.LEFT_JOIN);

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("divAnnBudgId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("budgetRefNo", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("combodetailFinancialYear.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName",
								searchString, MatchMode.ANYWHERE)));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("budgetRefNo"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("budgetRefNo"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("combodetailFinancialYear.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("combodetailFinancialYear.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				}
			}
			criteria.setProjection(
					Projections.projectionList().add(Projections.property("budgetRefNo"), "budgetRefNo")
							.add(Projections.property("combodetailFinancialYear.cmb_name"), "financialYear").add(
									Projections.property(
											"locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"),
									"division")

							.add(Projections.property("isForward"), "isForward")

			);

			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			financeDtos = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateDivisionBudgetData(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeDtos = null;
		Session session = null;
		try {
			session = getSession();
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("combodetailFinancialYear", "combodetailFinancialYear");

			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("locationDivisionSubDivisonDetailsBean", "locationDivisionSubDivisonDetailsBean",
					CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("isForward", Integer.parseInt(MISConstants.ZERO)));

			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("divAnnBudgId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("budgetRefNo", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("combodetailFinancialYear.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName",
								searchString, MatchMode.ANYWHERE)));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("budgetRefNo"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("budgetRefNo"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("combodetailFinancialYear.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("combodetailFinancialYear.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("componentBean.componentName"));
				}
			}
			criteria.setProjection(
					Projections.projectionList().add(Projections.property("budgetRefNo"), "budgetRefNo")
							.add(Projections.property("combodetailFinancialYear.cmb_name"), "financialYear")
							.add(Projections.property(
									"locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"), "division")
							.add(Projections.property("componentBean.componentName"), "componentName")
							.add(Projections.property("finYear"), "finYear")
							.add(Projections.property("divisionId"), "divisionId")
							.add(Projections.property("schemeId"), "schemeId")
							.add(Projections.property("componentId"), "componentId")

							.add(Projections.property("subComponentId"), "subComponentId")
							.add(Projections.property("activityId"), "activityId")
							.add(Projections.property("subSubComponentId"), "subSubComponentId")

							.add(Projections.property("subComponentLevel3Id"), "subComponentLevel3Id")
							.add(Projections.property("estimatedCost"), "estimatedCost")

							.add(Projections.property("reqNxtYear"), "reqNxtYear")
							.add(Projections.property("alreadySpent"), "alreadySpent")
							.add(Projections.property("qtr1"), "qtr1").add(Projections.property("qtr2"), "qtr2")
							.add(Projections.property("qtr3"), "qtr3").add(Projections.property("qtr4"), "qtr4")
							.add(Projections.property("divAnnBudgId"), "divAnnBudgId"));

			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			criteria.getExecutableCriteria(session).setMaxResults(100);
			financeDtos = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return financeDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDivision(MISSessionBean misSessionBean) throws DataAccessException {

		List<Integer> divisionIds = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.add(Restrictions.eq("crtByUsr", misSessionBean.getEnteredBy()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("isForward", Integer.parseInt(MISConstants.ZERO)));

			criteria.setProjection(Projections.property("divisionId"));

			divisionIds = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> getFinancialYear(String divisionId, MISSessionBean misSessionBean)
			throws DataAccessException {

		List<FinanceDto> financeDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("combodetailFinancialYear", "combodetailFinancialYear");

			if (MisUtility.ifEmpty(divisionId) && (!divisionId.equalsIgnoreCase("All")))
				criteria.add(Restrictions.eq("divisionId", Integer.parseInt(divisionId)));

			// criteria.add(Restrictions.eq("crtByUsr",
			// misSessionBean.getEnteredBy()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("combodetailFinancialYear.cmb_id")), "cmb_id")
					.add(Projections.property("combodetailFinancialYear.cmb_name"), "cmb_name"));
			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			financeDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionBudgetBean> getDivisionBudget(String financialYear, String divisionId,
			MISSessionBean misSessionBean) throws DataAccessException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("finYear", Integer.parseInt(financialYear)));
			
			if(MisUtility.ifEmpty(divisionId)&&(!divisionId.equalsIgnoreCase("All")))
				criteria.add(Restrictions.eq("divisionId", Integer.parseInt(divisionId)));
			
			criteria.add(Restrictions.eq("lyingWithUsr", misSessionBean.getEnteredBy()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.ne("isForward", Integer.parseInt(MISConstants.ONE)));
			divisionBudgetBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionBudgetBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionBudgetBean> getDivisionBudgets(String financialYear, String divisionId,
			MISSessionBean misSessionBean) throws DataAccessException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("finYear", Integer.parseInt(financialYear)));
			
			if(MisUtility.ifEmpty(divisionId)&&(!divisionId.equalsIgnoreCase("All")))
				criteria.add(Restrictions.eq("divisionId", Integer.parseInt(divisionId)));
			
			criteria.add(Restrictions.eq("crtByUsr", misSessionBean.getEnteredBy()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.ne("isForward", Integer.parseInt(MISConstants.ONE)));
			divisionBudgetBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionBudgetBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionBudgetBean> getDivisionBudgetState(String financialYear, String divisionId,
			MISSessionBean misSessionBean) throws DataAccessException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("componentBean", "componentBean", CriteriaSpecification.LEFT_JOIN);
			criteria.add(Restrictions.eq("finYear", Integer.parseInt(financialYear)));

			if (MisUtility.ifEmpty(divisionId)&&(!divisionId.equalsIgnoreCase("All"))) {
				criteria.add(Restrictions.eq("divisionId", Integer.parseInt(divisionId)));
			}
			criteria.add(Restrictions.eq("lyingWithUsr",misSessionBean.getUserId()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("isForward", Integer.parseInt(MISConstants.ONE)));
			divisionBudgetBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionBudgetBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> getComponentDetails(DivisionBudgetForm divisionBudgetForm, String divisionType,
			MISSessionBean misSessionBean) throws DataAccessException {
		List<FinanceDto> financeDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("componentBean", "componentBean");

			if (MisUtility.ifEmpty(divisionBudgetForm.getDivision())
					&& (!divisionBudgetForm.getDivision().equalsIgnoreCase("All")))
				criteria.add(Restrictions.eq("divisionId", Integer.parseInt(divisionBudgetForm.getDivision())));

			criteria.add(Restrictions.eq("finYear", Integer.parseInt(divisionBudgetForm.getFinancialYear())));

			if (MisUtility.ifEmpty(divisionType)) {
				criteria.add(Restrictions.eq("isForward", Integer.parseInt(MISConstants.ONE)));
				criteria.add(Restrictions.eq("lyingWithUsr", misSessionBean.getUserId()));
			} else {
				criteria.add(Restrictions.ne("isForward", Integer.parseInt(MISConstants.ONE)));
			}

			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("componentBean.componentName"), "componentName")
					.add(Projections.sum("reqNxtYear"), "reqNxtYear")
					.add(Projections.groupProperty("componentBean.componentName")));
			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));

			financeDtos = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}

		return financeDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateDivisionBudgetData(String division, String financialYear, String divisionType,
			String searchString, int clickedColumn, String colOrder, MISSessionBean misSessionBean)
			throws DataAccessException {
		List<FinanceDto> financeDtos = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.createAlias("combodetailFinancialYear", "combodetailFinancialYear");
			criteria.createAlias("locationDivisionSubDivisonDetailsBean", "locationDivisionSubDivisonDetailsBean",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("componentBean", "componentBean");
			criteria.createAlias("subComponentBean", "subComponentBean");
			criteria.createAlias("activityComponentBean", "activityComponentBean");
			criteria.createAlias("subSubComponentBean", "subSubComponentBean");

			if (MisUtility.ifEmpty(division) && (!division.equalsIgnoreCase("All"))) {
				criteria.add(Restrictions.eq("divisionId", Integer.parseInt(division)));
			}

			criteria.add(Restrictions.eq("finYear", Integer.parseInt(financialYear)));

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			if (MisUtility.ifEmpty(divisionType)) {
				criteria.add(Restrictions.eq("isForward", Integer.parseInt(MISConstants.ONE)));
				criteria.add(Restrictions.eq("lyingWithUsr", misSessionBean.getUserId()));
			} else {
				criteria.add(Restrictions.ne("isForward", Integer.parseInt(MISConstants.ONE)));
			}
			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("divAnnBudgId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("componentBean.componentName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("subComponentBean.componentName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("activityComponentBean.componentName", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("subSubComponentBean.componentName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("estimated_cost::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("already_spent::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("req_nxt_year::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("qtr_1::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("qtr_2::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("qtr_3::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("qtr_4::text like '%" + searchString + "%'")));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("componentBean.componentName"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("componentBean.componentName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("subComponentBean.componentName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("subComponentBean.componentName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("activityComponentBean.componentName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("activityComponentBean.componentName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("estimatedCost"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("estimatedCost"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("alreadySpent"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("alreadySpent"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("reqNxtYear"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("reqNxtYear"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("qtr1"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("qtr1"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("qtr2"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("qtr2"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("qtr3"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("qtr3"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.desc("qtr4"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("qtr4"));
				}
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("componentBean.componentName"), "componentName")
					.add(Projections.property("subComponentBean.componentName"), "subComponentName")
					.add(Projections.property("activityComponentBean.componentName"), "activityComponentName")
					.add(Projections.property("subSubComponentBean.componentName"), "subSubComponentName")

					.add(Projections.property("finYear"), "finYear")
					.add(Projections.property("divisionId"), "divisionId")
					.add(Projections.property("schemeId"), "schemeId")
					.add(Projections.property("componentId"), "componentId")
					.add(Projections.property("subComponentId"), "subComponentId")
					.add(Projections.property("activityId"), "activityId")
					.add(Projections.property("subSubComponentId"), "subSubComponentId")
					.add(Projections.property("subComponentLevel3Id"), "subComponentLevel3Id")
					.add(Projections.property("estimatedCost"), "estimatedCost")
					.add(Projections.property("reqNxtYear"), "reqNxtYear")
					.add(Projections.property("alreadySpent"), "alreadySpent").add(Projections.property("qtr1"), "qtr1")
					.add(Projections.property("qtr2"), "qtr2").add(Projections.property("qtr3"), "qtr3")
					.add(Projections.property("qtr4"), "qtr4")
					.add(Projections.property("divAnnBudgId"), "divAnnBudgId"));

			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			financeDtos = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeDtos;
	}

	@Override
	public boolean updateDivisionBudget(List<DivisionBudgetBean> divisionBudgetBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(divisionBudgetBeans);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUserBean> getUserName(List<String> userId) throws DataAccessException {

		List<LoginUserBean> loginUserBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserBean.class);
			criteria.add(Restrictions.in("userId", userId));
			// criteria.add(Restrictions.eq("roleId", MISConstants.XEN));
			loginUserBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return loginUserBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUserBean> getStateUserName(String userId) throws DataAccessException {

		List<LoginUserBean> loginUserBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserBean.class);
			criteria.add(Restrictions.eq("userId", userId));
			// criteria.add(Restrictions.eq("roleId", MISConstants.XEN));
			loginUserBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return loginUserBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserId(String division) throws DataAccessException {

		List<String> loginUserLocationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(NodalDivisionBean.class);
			criteria.add(Restrictions.eq("locationId", Integer.parseInt(division)));
			criteria.setProjection(Projections.property("userId"));
			loginUserLocationBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return loginUserLocationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getNodalDivision(MISSessionBean misSessionBean) throws DataAccessException {
		List<Integer> divisionIds = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.add(Restrictions.eq("crtByUsr", misSessionBean.getEnteredBy()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.property("divisionId"));

			divisionIds = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionBudgetBean> getDivisionBudgetBean(String divAnnBudgId) throws DataAccessException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.add(Restrictions.eq("divAnnBudgId", Long.parseLong(divAnnBudgId)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			divisionBudgetBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionBudgetBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDdoName(String divisionId) throws DataAccessException {
		List<String> ddoName = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DdoMasterBean.class);
			criteria.add(Restrictions.eq("divisionId", Integer.parseInt(divisionId)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.property("ddoNumber"));
			ddoName = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return ddoName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getNodalDivisionId(MISSessionBean misSessionBean) throws DataAccessException {
		List<Integer> divisionIds = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionBudgetBean.class);
			criteria.add(Restrictions.eq("lyingWithUsr", misSessionBean.getUserId()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("isForward", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.property("divisionId"));

			divisionIds = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return divisionIds;
	}

}
