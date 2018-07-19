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

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_division_activity_dtl_mpg",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DivisionActivityDetailMpgBean implements Serializable{
	
	private static final long serialVersionUID = 3635012326519815L;
	
	@Id
	@GeneratedValue(generator="sdu_division_activity_dtl_mpg_div_activity_dtl_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="sdu_division_activity_dtl_mpg_div_activity_dtl_id_seq",sequenceName="prwss_main.sdu_division_activity_dtl_mpg_div_activity_dtl_id_seq")
	@Column(name="div_activity_dtl_id")
	private Integer div_activity_dtl_id;
	
	@Column(name="div_activity_id")
	private Integer div_activity_id;
	
	@Column(name="activity_id")
	private Integer activity_id;
	
	@Column(name="crt_by_usr")
	private Integer crt_by_usr;

	
	public Integer getDiv_activity_dtl_id() {
		return div_activity_dtl_id;
	}

	public void setDiv_activity_dtl_id(Integer div_activity_dtl_id) {
		this.div_activity_dtl_id = div_activity_dtl_id;
	}

	public Integer getDiv_activity_id() {
		return div_activity_id;
	}

	public void setDiv_activity_id(Integer div_activity_id) {
		this.div_activity_id = div_activity_id;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public Integer getCrt_by_usr() {
		return crt_by_usr;
	}

	public void setCrt_by_usr(Integer crt_by_usr) {
		this.crt_by_usr = crt_by_usr;
	}

	@Override
	public String toString() {
		return "DivisionActivityDetailMpgBean [div_activity_dtl_id=" + div_activity_dtl_id + ", div_activity_id="
				+ div_activity_id + ", activity_id=" + activity_id + ", crt_by_usr=" + crt_by_usr + "]";
	}
	
}
