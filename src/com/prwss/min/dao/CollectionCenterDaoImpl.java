/**
 * 
 */
package com.prwss.min.dao;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.CollectionCenterBean;

/**
 * @author BH390738
 *
 */
public class CollectionCenterDaoImpl extends HibernateDaoSupport implements CollectionCenterDao{

	private Logger log = Logger.getLogger(CollectionCenterDaoImpl.class);

	@Override
	public boolean save(CollectionCenterBean collectionCenterBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(collectionCenterBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return false;
	}

	

}
