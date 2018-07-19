/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "san_progress_stage_mpg", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgressStageMappingBean implements Serializable {

	private static final long serialVersionUID = -132323425332344L;

	@Id
	@GeneratedValue(generator="progress_stage_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="progress_stage_id_seq",sequenceName="prwss_main.progress_stage_id_seq")
	@Column(name="progress_stage_id")
	private int progressStageId;
	
	@OneToOne(targetEntity=ProgressStageMaster.class,fetch=FetchType.LAZY)
	@JoinColumn(name="stage_id",referencedColumnName="stage_id", insertable=false, updatable=false)
	private ProgressStageMaster progressStageMaster;
	
	
	
	
	public ProgressStageMaster getProgressStageMaster() {
		return progressStageMaster;
	}

	public void setProgressStageMaster(ProgressStageMaster progressStageMaster) {
		this.progressStageMaster = progressStageMaster;
	}
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="stage_status",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  combodetailStageStatus;

	@Column(name="progress_work_id")
	private Integer progressWorkId;
	
	@Column(name="stage_id")
	private Integer stageId;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="crt_by_usr")
	private Integer crtByUser;
	
	@Column(name="stage_status")
	private Integer stageStatus;
	
	@Column(name="picture_of_stage")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] pictureStage;

	public int getProgressStageId() {
		return progressStageId;
	}

	public void setProgressStageId(int progressStageId) {
		this.progressStageId = progressStageId;
	}

	public ComboBeanDetails getCombodetailStageStatus() {
		return combodetailStageStatus;
	}

	public void setCombodetailStageStatus(ComboBeanDetails combodetailStageStatus) {
		this.combodetailStageStatus = combodetailStageStatus;
	}

	public Integer getProgressWorkId() {
		return progressWorkId;
	}

	public void setProgressWorkId(Integer progressWorkId) {
		this.progressWorkId = progressWorkId;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCrtByUser() {
		return crtByUser;
	}

	public void setCrtByUser(Integer crtByUser) {
		this.crtByUser = crtByUser;
	}

	public Integer getStageStatus() {
		return stageStatus;
	}

	public void setStageStatus(Integer stageStatus) {
		this.stageStatus = stageStatus;
	}

	public byte[] getPictureStage() {
		return pictureStage;
	}

	public void setPictureStage(byte[] pictureStage) {
		this.pictureStage = pictureStage;
	}

	@Override
	public String toString() {
		return "ProgressStageMappingBean [progressStageId=" + progressStageId + ", combodetailStageStatus="
				+ combodetailStageStatus + ", progressWorkId=" + progressWorkId + ", stageId=" + stageId
				+ ", activeFlag=" + activeFlag + ", crtByUser=" + crtByUser + ", stageStatus=" + stageStatus
				+ ", pictureStage=" + Arrays.toString(pictureStage) + "]";
	}

}
