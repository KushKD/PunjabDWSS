package com.prwss.min.SDU.bean;

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


//prwss_main.sdu_stage_dtl
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_stage_dtl",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class StageDetailBean implements Serializable {

	
	private static final long serialVersionUID = -1699826061041008025L;

	
	//stage_dtl_id
	@Id
	@SequenceGenerator(name="sdu_stage_dtl_id_seq",sequenceName="prwss_main.sdu_stage_dtl_id_seq")
	@GeneratedValue(generator="sdu_stage_dtl_id_seq",strategy=GenerationType.AUTO)
	@Column(name="stage_dtl_id")
	private Integer StageDetaulId;

	@Column(name = "stage_id")
	private Integer stageId;

	@Column(name = "stage_name")
	private String stageName;

	@Override
	public String toString() {
		return "StageDetailBean [stageId=" + stageId + ", stageName=" + stageName + "]";
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

}