/**
 * 
 */
package com.prwss.min.dao;

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

import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SampleDto;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FreezeUnFreezeDaoImpl extends HibernateDaoSupport implements FreezeUnFreezeDao {

	private Logger log = Logger.getLogger(FreezeUnFreezeDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDto> getSampleDetails(MISSessionBean misSessionBean,String lab, String fromDate, String toDate, String searchString,
			int clickedColumn, String colOrder) throws DataAccessException {
		List<SampleDto> sampleDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);

			if (MisUtility.ifEmpty(lab)){
				criteria.add(Restrictions.eq("labTested", Integer.parseInt(lab)));
			}
			if (MisUtility.ifEmpty(fromDate))
				criteria.add(Restrictions.ge("freeze_date",MisUtility.convertStringSqlDate(fromDate)));
			
			if (MisUtility.ifEmpty(toDate))
				criteria.add(Restrictions.le("freeze_date",MisUtility.convertStringSqlDate(toDate)));
			
			criteria.add(Restrictions.eq("createByUsr", misSessionBean.getEnteredBy()));

			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("is_freeze", Integer.parseInt(MISConstants.ONE)));

			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("sampleNumber", searchString, MatchMode.ANYWHERE)));
			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("sampleNumber"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("sampleNumber"));
				}
			}
			criteria.setProjection(Projections.projectionList().add(Projections.property("sampleNumber"),"sampleNumber"));
			criteria.setResultTransformer(Transformers.aliasToBean(SampleDto.class));
			sampleDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return sampleDto;
	}

}
