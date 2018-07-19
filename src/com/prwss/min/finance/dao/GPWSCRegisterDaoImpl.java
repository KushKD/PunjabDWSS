/**
 * 
 */
package com.prwss.min.finance.dao;

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

import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.GPWSCRegisterBean;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class GPWSCRegisterDaoImpl extends HibernateDaoSupport implements GPWSCRegisterDao {

	private Logger log = Logger.getLogger(GPWSCRegisterDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatDetailBean> getGPWSC(String villageId, String district) throws DataAccessException {

		List<GramPanchayatDetailBean> gramPanchayatDetailBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatDetailBean.class);

			if (MisUtility.ifEmpty(villageId)) {
				criteria.add(Restrictions.eq("village", Integer.parseInt(villageId)));
			}
			
			if(MisUtility.ifEmpty(district)){
				criteria.add(Restrictions.eq("district", Integer.parseInt(district)));
			}
			criteria.add(Restrictions.eq("status", Integer.parseInt(MISConstants.ONE)));
			gramPanchayatDetailBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return gramPanchayatDetailBeans;
	}

	@Override
	public boolean save(GPWSCRegisterBean gpwscRegisterBean) throws DataAccessException {

		try {
			getHibernateTemplate().save(gpwscRegisterBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> getGPWSCRegiter(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeHeadBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(GPWSCRegisterBean.class);
			criteria.createAlias("comboDetailsTransactionType", "comboDetailsTransactionType");

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("financeGpwscRegister"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("transactionNo", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("comboDetailsTransactionType.cmb_name", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("transaction_date::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("payment_amt::text like '%" + searchString + "%'")));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("transactionNo"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("transactionNo"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboDetailsTransactionType.cmb_name"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboDetailsTransactionType.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("transactionDate"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("transactionDate"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("paymentAmt"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("paymentAmt"));
				}
			}
			criteria.setProjection(
					Projections.projectionList().add(Projections.property("transactionNo"), "transactionNo")
							.add(Projections.property("comboDetailsTransactionType.cmb_name"), "transactionType")
							.add(Projections.property("transactionDate"), "transactionDate")
							.add(Projections.property("paymentAmt"), "paymentAmt"));

			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			financeHeadBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeHeadBeans;
	}

}
