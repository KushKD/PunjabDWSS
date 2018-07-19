/**
 * 
 */
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
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressMovementBean;
import com.prwss.min.construction.quality.bean.MonthlyReportDto;
import com.prwss.min.construction.quality.bean.ProgressCommentMappingBean;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class SendMonthlyReportDaoImpl extends HibernateDaoSupport implements SendMonthlyReportDao {
	private Logger log = Logger.getLogger(SendMonthlyReportDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<SaveMonthlyReportObservationBean> pupolateObservation(SendMonthlyReportForm sendMonthlyReportForm)
			throws DataAccessException {

		List<SaveMonthlyReportObservationBean> saveMonthlyReportObservationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SaveMonthlyReportObservationBean.class,
					"saveMonthlyReportObservationBean");
			criteria.createAlias("saveMonthlyReportObservationBean.monthlyPlanInspectionBean",
					"monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			criteria.add(Restrictions.eq("yearlyPlanInspectionBean.financialYear",
					Integer.parseInt(sendMonthlyReportForm.getYearPlan())));
			criteria.add(Restrictions.eq("monthlyPlanInspectionBean.monthId",
					Integer.parseInt(sendMonthlyReportForm.getMonth())));
			saveMonthlyReportObservationBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return saveMonthlyReportObservationBeans;
	}

	@Override
	public Long saveSendMonthlyProgress(MonthlyProgressMovementBean monthlyProgressMovementBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(monthlyProgressMovementBean);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyProgressMovementBean.getProgressMvmntId();
	}

	@Override
	public boolean saveProgressComment(ProgressCommentMappingBean progressCommentMappingBean) {
		try {
			getHibernateTemplate().save(progressCommentMappingBean);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyProgressBean> fetchMonthlyPlanId(String monthlyPlanId) {
		List<MonthlyProgressBean> monthlyPlanIds = null;
		try {
			System.out.println("monthlyPlanId------dao---------------" + monthlyPlanId);
			DetachedCriteria criteria = DetachedCriteria.forClass(MonthlyProgressBean.class);
			criteria.add(Restrictions.eq("monthlyPlanId", Integer.parseInt(monthlyPlanId)));
			monthlyPlanIds = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanIds;
	}

	@Override
	public boolean updateMonthlyProgress(MonthlyProgressBean monthlyProgressBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(monthlyProgressBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyReportDto> getMonthlyProgressData(Long entBy, String searchString, int clickedColumn,
			String colOrder) throws DataAccessException {
		List<MonthlyReportDto> monthlyPlanDtos=null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MonthlyProgressMovementBean.class,
					"monthlyProgressMovementBean");
			criteria.createAlias("monthlyProgressMovementBean.monthlyPlanInspectionBean",
					"monthlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.yearlyPlanInspectionBean", "yearlyPlanInspectionBean");
			criteria.createAlias("monthlyPlanInspectionBean.combodetailMonth", "combodetailMonth");
			criteria.createAlias("yearlyPlanInspectionBean.comboDetailsYear", "comboDetailsYear");

			criteria.add(Restrictions.eq("monthlyProgressMovementBean.lyingWithUser", Integer.parseInt(String.valueOf(entBy))));

			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("combodetailMonth.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("comboDetailsYear.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("yearlyPlanInspectionBean.yearlyPlanName", searchString,
								MatchMode.ANYWHERE)));
			}
			criteria.add(
					Restrictions.eq("monthlyProgressMovementBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboDetailsYear.cmb_name"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboDetailsYear.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("combodetailMonth.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("combodetailMonth.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("yearlyPlanInspectionBean.yearlyPlanName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("yearlyPlanInspectionBean.yearlyPlanName"));
				}
			}
			criteria.setProjection(
					Projections.projectionList().add(Projections.property("comboDetailsYear.cmb_name"), "financialYear")
							.add(Projections.property("combodetailMonth.cmb_name"), "month")
							.add(Projections.property("yearlyPlanInspectionBean.yearlyPlanName"), "yearlyPlanName")
							.add(Projections.property("monthlyPlanInspectionBean.monthId"), "monthId")
							.add(Projections.property("yearlyPlanInspectionBean.financialYear"), "fnclYear")
							.add(Projections.property("monthlyProgressMovementBean.lyingWithUser"), "lyingWithUser")
			);

			criteria.setResultTransformer(Transformers.aliasToBean(MonthlyReportDto.class));

			monthlyPlanDtos = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyPlanDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonthlyProgressMovementBean> findMonthlyProgressMovement(
			SendMonthlyReportForm sendMonthlyReportForm) throws DataAccessException {
		List<MonthlyProgressMovementBean> monthlyProgressMovementBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MonthlyProgressMovementBean.class);
			criteria.add(Restrictions.eq("monthlyPlanId", Integer.parseInt(sendMonthlyReportForm.getMonthlyPlanId())));
			monthlyProgressMovementBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyProgressMovementBeans;
		
	}

	@Override
	public Long updateSendMonthlyProgress(MonthlyProgressMovementBean monthlyProgressMovementBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().update(monthlyProgressMovementBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return monthlyProgressMovementBean.getProgressMvmntId();
	}

}
