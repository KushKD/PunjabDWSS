/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.TeamMasterBean;
import com.prwss.min.construction.quality.dao.CreateTeamDao;
import com.prwss.min.construction.quality.form.CreateTeam;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class CreateTeamBoImpl implements CreateTeamBo {
	private Logger log = Logger.getLogger(CreateTeamBoImpl.class);
	private CreateTeamDao createTeamDao;
	
	public CreateTeamDao getCreateTeamDao() {
		return createTeamDao;
	}
	public void setCreateTeamDao(CreateTeamDao createTeamDao) {
		this.createTeamDao = createTeamDao;
	}

	@Override
	public boolean saveTeamDetails(CreateTeam createTeam, int entBy) throws MISException {

		try {
			TeamMasterBean teamMasterBean=populateTeamBean(createTeam,entBy);
			if(MisUtility.ifEmpty(teamMasterBean)){
				createTeamDao.saveTeamDetails(teamMasterBean);
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return true;
	}

	private TeamMasterBean populateTeamBean(CreateTeam createTeam, int entBy) {
		
		TeamMasterBean teamMasterBean = null;
		try {
			teamMasterBean = new TeamMasterBean();
			teamMasterBean.setTeam_name(createTeam.getTeamName());
			if (MisUtility.ifEmpty(createTeam.getFinancialYear())) {
				teamMasterBean.setFinancial_year(Integer.parseInt(createTeam.getFinancialYear()));
			}
			teamMasterBean.setTeam_details(createTeam.getDetails());
			teamMasterBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
			teamMasterBean.setCrtByUsr(entBy);
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return teamMasterBean;
	}
}
