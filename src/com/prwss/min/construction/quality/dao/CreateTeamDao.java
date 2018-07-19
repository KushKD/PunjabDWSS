/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.TeamMasterBean;

/**
 * @author BH390738
 *
 */
public interface CreateTeamDao {

	public boolean saveTeamDetails(TeamMasterBean teamMasterBean) throws DataAccessException;
}
