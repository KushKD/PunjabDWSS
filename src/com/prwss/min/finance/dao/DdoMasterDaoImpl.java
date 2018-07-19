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
import com.prwss.min.finance.bean.DdoMasterBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class DdoMasterDaoImpl extends HibernateDaoSupport implements DdoMasterDao{

	private Logger log = Logger.getLogger(DdoMasterDaoImpl.class);
	
	@Override
	public boolean saveDdoMasterBean(DdoMasterBean ddoMasterBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(ddoMasterBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> getDdoMaster(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeHeadBeans = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DdoMasterBean.class);
			criteria.createAlias("locationDivisionSubDivisonDetailsBean", "locationDivisionSubDivisonDetailsBean");
			
			criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("ddoId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("ddoNumber", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("ddoName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName", searchString, MatchMode.ANYWHERE))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("ddoNumber"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("ddoNumber"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("ddoName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("ddoName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"));
				} 
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("ddoNumber"), "ddoNumber")
					.add(Projections.property("ddoName"), "ddoName")
					.add(Projections.property("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"), "division")
					);
					
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
