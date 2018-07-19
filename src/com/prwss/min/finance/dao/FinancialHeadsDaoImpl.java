package com.prwss.min.finance.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
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
import com.prwss.min.finance.bean.FinanceHeadBean;
import com.prwss.min.finance.form.FinanceHeadsForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FinancialHeadsDaoImpl extends HibernateDaoSupport implements FinancialHeadsDao {

	private Logger log = Logger.getLogger(FinancialHeadsDaoImpl.class);
	
	@Override
	public boolean save(FinanceHeadBean financeHeadBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(financeHeadBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceHeadBean> getParent(String headType) throws DataAccessException {
		
		List<FinanceHeadBean>  financeHeadBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(FinanceHeadBean.class);
			criteria.add(Restrictions.eq("head_type", Integer.parseInt(headType)));
			
			financeHeadBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return financeHeadBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateFinanceHead(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeHeadBeans = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(FinanceHeadBean.class,"financeHeadBeans");
			criteria.createAlias("financeHeadBeans.comboDetailsHeadType", "comboDetailsHeadType");
			criteria.createAlias("financeHeadBeans.financeHeadBean", "financeHeadBean",CriteriaSpecification.LEFT_JOIN);
			
			criteria.setFetchMode("financeHeadBean",  FetchMode.JOIN);
			
			criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("headId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("headNumber", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("comboDetailsHeadType.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("financeHeadBean.headName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("headName", searchString, MatchMode.ANYWHERE))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("comboDetailsHeadType.cmb_name"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("comboDetailsHeadType.cmb_name"));
				}else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("headName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("headName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("headNumber"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("headNumber"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("financeHeadBean.headName"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("financeHeadBean.headName"));
				} 
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("comboDetailsHeadType.cmb_name"), "head_type")
					.add(Projections.property("headName"), "headName")
					.add(Projections.property("headId"), "headId")
					.add(Projections.property("head_type"), "headType")
					.add(Projections.property("parent_head_id"), "parent_head_id")
					.add(Projections.property("headNumber"), "headNumber")
					.add(Projections.property("financeHeadBean.headName"), "parent_head_name"));
			
			
					
			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			//getHibernateTemplate().setCacheQueries(true);
			financeHeadBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeHeadBeans;
	}

	@Override
	public boolean update(FinanceHeadBean financeHeadBean) throws DataAccessException {
		try{
			getHibernateTemplate().update(financeHeadBean);
		}catch(DataAccessException e){
			log.debug(e);
			throw e;
		}
		return true;
	}

	@Override
	public List<FinanceHeadBean> getFinanceHead(FinanceHeadsForm financeHeadsForm) throws DataAccessException {
		List<FinanceHeadBean>  financeHeadBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(FinanceHeadBean.class);
			criteria.add(Restrictions.eq("headId", Long.parseLong(financeHeadsForm.getFinanceHeadId())));
			
			financeHeadBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return financeHeadBeans;
	}

}
