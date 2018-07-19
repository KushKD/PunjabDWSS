/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.LatrineUsageBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class LatrineUsageDaoImpl extends HibernateDaoSupport implements LatrineUsageDao {
	private Logger log = Logger.getLogger(LatrineUsageDaoImpl.class);

	@Override
	public boolean saveLatrine(LatrineUsageBean latrineUsageBean) throws DataAccessException {
		try{
			getHibernateTemplate().save(latrineUsageBean);
			
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		
		return true;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<LatrineUsageBean> getLatrineDetails(String searchString,int clickedColumn,String colOrder)
			throws DataAccessException {
		
		List<LatrineUsageBean> latrineUsageBeans = null;
		Session session=null;
		try {
			session=getSession();
			DetachedCriteria criteria = DetachedCriteria.forClass(LatrineUsageBean.class,"latrineUsageBean");
		
			criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("latrineId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.sqlRestriction("beneficiary_id::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("district_id::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("block_id::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("village_id::text like '%" + searchString + "%'"))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("beneficiaryId"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("beneficiaryId"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("districtId"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("districtId"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("blockId"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("blockId"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("villageId"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("villageId"));
				}
			}
			
			criteria.getExecutableCriteria(session).setMaxResults(100);
			getHibernateTemplate().setCacheQueries(true);
			latrineUsageBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return latrineUsageBeans;
	}

}
