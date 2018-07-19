/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import com.prwss.min.construction.quality.form.CreateTeam;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface CreateTeamBo {

	public boolean saveTeamDetails(CreateTeam createTeam,int entBy)throws MISException;
}
