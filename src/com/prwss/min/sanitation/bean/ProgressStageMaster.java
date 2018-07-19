/**
 * 
 */
package com.prwss.min.sanitation.bean;

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
@Table(name = "progress_stage_master", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgressStageMaster implements Serializable{
	
	private static final long serialVersionUID = -132323425332344L;

	@Id
	@GeneratedValue(generator="stage_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="stage_id_seq",sequenceName="prwss_main.stage_id_seq")
	@Column(name="stage_id")
	private int stageId;
	
	@Column(name="stage_name")
	private String  stageName;
	
	@Column(name="amount")
	private long amount;
	
	@Column(name="active_flag")
	private int activeFlag;
	
	@Column(name="crt_by_user")
	private int crtByUser;

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public int getCrtByUser() {
		return crtByUser;
	}

	public void setCrtByUser(int crtByUser) {
		this.crtByUser = crtByUser;
	}

	@Override
	public String toString() {
		return "ProgressStageMaster [stageId=" + stageId + ", stageName=" + stageName + ", amount=" + amount
				+ ", activeFlag=" + activeFlag + ", crtByUser=" + crtByUser + "]";
	}
}
