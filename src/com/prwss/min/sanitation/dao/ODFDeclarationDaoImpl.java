/**
 * 
 */
package com.prwss.min.sanitation.dao;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.sanitation.bean.ODFDeclarationBean;

/**
 * @author BH390738
 *
 */
public class ODFDeclarationDaoImpl extends HibernateDaoSupport implements ODFDeclarationDao{
	private Logger log = Logger.getLogger(ODFDeclarationDaoImpl.class);

	@Override
	public boolean save(ODFDeclarationBean odfDeclarationBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(odfDeclarationBean);
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

}
