/**
 * 
 */
package com.prwss.min.construction.quality.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="cc_team_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TeamMasterBean implements Serializable {

	private static final long serialVersionUID=1234455L;
	
	@Id
	@GeneratedValue(generator="team_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="team_id_seq",sequenceName="prwss_main.team_id_seq")
	@Column(name="team_id")
	private int teamId;
	
	@Column(name="team_name")
	private String team_name;
	
	@Column(name="financial_year")
	private Integer financial_year;
	
	@Column(name="team_details")
	private String team_details;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_user")
	private Integer crtByUsr;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public Integer getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(Integer financial_year) {
		this.financial_year = financial_year;
	}

	public String getTeam_details() {
		return team_details;
	}

	public void setTeam_details(String team_details) {
		this.team_details = team_details;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	@Override
	public String toString() {
		return "TeamMasterBean [teamId=" + teamId + ", team_name=" + team_name + ", financial_year=" + financial_year
				+ ", team_details=" + team_details + ", activeFlag=" + activeFlag + ", crtByUsr=" + crtByUsr + "]";
	}
	
}
