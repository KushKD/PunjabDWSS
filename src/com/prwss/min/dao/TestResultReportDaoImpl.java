/**
 * 
 */
package com.prwss.min.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.ResultEntryBean;

/**
 * @author BH390738
 *
 */
public class TestResultReportDaoImpl extends HibernateDaoSupport implements TestResultReportDao {

	private Logger log = Logger.getLogger(TestResultReportDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> fetchSample(String labId) throws DataAccessException {
		List<String> sampleDtos=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(ResultEntryBean.class,"resultEntryBean");
			criteria.createAlias("resultEntryBean.receiveSampleBean", "receiveSampleBean");
			criteria.add(Restrictions.eq("resultEntryBean.labId", Integer.parseInt(labId)));
			
			criteria.setProjection(Projections.distinct(Projections.property("receiveSampleBean.sampleNumber")));
			
			sampleDtos=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
		}
		return sampleDtos;
	}
	
	

}
