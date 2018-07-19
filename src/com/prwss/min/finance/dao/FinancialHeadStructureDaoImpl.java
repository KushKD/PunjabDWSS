/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FinanceHeadBeans;
import com.prwss.min.finance.bean.FinanceHeadStructureBean;
import com.prwss.min.finance.form.FinanceHeadsStructureForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 * 
 */
public class FinancialHeadStructureDaoImpl extends HibernateDaoSupport implements FinancialHeadStructureDao {

	private Logger log = Logger.getLogger(FinancialHeadStructureDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceHeadBeans> getHeadType(String headType, String head) throws DataAccessException {

		List<FinanceHeadBeans> financeHeadBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(FinanceHeadBeans.class);

			if (MisUtility.ifEmpty(headType)) {
				criteria.add(Restrictions.eq("parent_head_id", Integer.parseInt(headType)));
			}
			if (MisUtility.ifEmpty(head)) {
				criteria.add(Restrictions.eq("head_type", Integer.parseInt(head)));
			}

			financeHeadBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;

		}

		return financeHeadBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceHeadStructureBean> getHeadStructureBean(FinanceHeadsStructureForm financeHeadsStructureForm)
			throws DataAccessException {

		List<FinanceHeadStructureBean> financeHeadStructureBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(FinanceHeadStructureBean.class);

			if (MisUtility.ifEmpty(financeHeadsStructureForm)) {
				criteria.add(Restrictions.eq("headStructureId",
						Long.parseLong(financeHeadsStructureForm.getHeadStructureId())));
			}

			financeHeadStructureBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;

		}

		return financeHeadStructureBeans;
	}

	@Override
	public boolean save(FinanceHeadStructureBean financeHeadStructureBean) throws DataAccessException {

		try {
			getHibernateTemplate().save(financeHeadStructureBean);
		} catch (DataAccessException e) {
			log.debug(e);
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateFinanceStructureHead(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeHeadBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(FinanceHeadStructureBean.class,
					"financeHeadStructureBean");
			criteria.createAlias("financeHeadStructureBean.comboDetailsFinancialYear", "comboDetailsFinancialYear",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadStructureBean.financeHeadBeanDemand", "financeHeadBeanDemand",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadBeanMajorHead", "financeHeadBeanMajorHead",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadBeanSubMajorHead", "financeHeadBeanSubMajorHead",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadBeanMinorHead", "financeHeadBeanMinorHead",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadBeanSubHead", "financeHeadBeanSubHead", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadBeanDetailHead", "financeHeadBeanDetailHead",
					CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("financeHeadBeanSoeHead", "financeHeadBeanSoeHead", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("comboDetailsPlanNonPlan", "comboDetailsPlanNonPlan", CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("comboDetailsVotedCharged", "comboDetailsVotedCharged",
					CriteriaSpecification.LEFT_JOIN);

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("headStructureId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("headStructureName", searchString, MatchMode.ANYWHERE)).add(Restrictions
								.ilike("comboDetailsFinancialYear.cmb_name", searchString, MatchMode.ANYWHERE)));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("headStructureName"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("headStructureName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboDetailsFinancialYear.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboDetailsFinancialYear.cmb_name"));
				}
			}
			criteria.setProjection(
					Projections.projectionList().add(Projections.property("headStructureName"), "headStructureName")
							.add(Projections.property("comboDetailsFinancialYear.cmb_name"), "financialYear")
							.add(Projections.property("financeHeadBeanDemand.headNumber"), "demandHeadNumber")
							.add(Projections.property("financeHeadBeanMajorHead.headNumber"), "majorHead")
							.add(Projections.property("financeHeadBeanSubMajorHead.headNumber"), "subMajorHead")
							.add(Projections.property("financeHeadBeanMinorHead.headNumber"), "minorHead")
							.add(Projections.property("financeHeadBeanSubHead.headNumber"), "subHead")
							.add(Projections.property("financeHeadBeanDetailHead.headNumber"), "detailHead")
							.add(Projections.property("financeHeadBeanSoeHead.headNumber"), "soeHead")
							.add(Projections.property("comboDetailsPlanNonPlan.cmb_name"), "planNonPlan")
							.add(Projections.property("comboDetailsVotedCharged.cmb_name"), "votedCharged")
							.add(Projections.property("headStructureId"), "headStructureId")
							.add(Projections.property("financialYear"), "financialYears")
							.add(Projections.property("demandNumber"), "demandNumber")
							.add(Projections.property("majorHead"), "majorHeads")
							.add(Projections.property("subMajorHead"), "subMajorHeads")
							.add(Projections.property("minorHead"), "minorHeads")
							.add(Projections.property("subHead"), "subHeads")
							.add(Projections.property("detailedHead"), "detailedHead")
							.add(Projections.property("soeNumber"), "soeNumber")
							.add(Projections.property("planNonplan"), "planNonplan")
							.add(Projections.property("votedCharged"), "votedChargeds"));

			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));

			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			// getHibernateTemplate().setCacheQueries(true);
			financeHeadBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeHeadBeans;

	}

	@Override
	public boolean update(FinanceHeadStructureBean financeHeadStructureBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(financeHeadStructureBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

}
