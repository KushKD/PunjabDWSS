/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.construction.quality.bean.TeamMasterBean;

/**
 * @author BH390738
 *
 */
public class CreateTeamDaoImpl extends HibernateDaoSupport implements CreateTeamDao{

	private Logger log = Logger.getLogger(CreateTeamDaoImpl.class);

	@Override
	public boolean saveTeamDetails(TeamMasterBean teamMasterBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(teamMasterBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return false;
	}

}
