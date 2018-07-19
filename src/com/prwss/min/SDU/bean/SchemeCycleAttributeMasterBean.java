package com.prwss.min.SDU.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "sdu_sch_cyl_att", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeCycleAttributeMasterBean implements Serializable {

	private static final long serialVersionUID = 6926724754853847L;

	@Id
	@SequenceGenerator(name = "sdu_sch_cyl_att_sch_attribute_id_seq", sequenceName = "prwss_main.sdu_sch_cyl_att_sch_attribute_id_seq")
	@GeneratedValue(generator = "sdu_sch_cyl_att_sch_attribute_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "sch_attribute_id")
	private Integer schAttributeId;

	@OneToOne(targetEntity = SchemeCycleAttributeMasterBean.class)
	@JoinColumn(name = "sch_attribute_id", referencedColumnName = "sch_attribute_id", insertable = false, updatable = false)
	private SchemeCycleAttributeMasterBean detailAttributeName;
	
	

	@Column(name = "attribute_type")
	private Integer attributeType;

	@Column(name = "active_flag")
	private Integer activeFlag;

	@Column(name = "crt_by_usr")
	private Integer createdByUser;
	
	
	
	

	public SchemeCycleAttributeMasterBean getDetailAttributeName() {
		return detailAttributeName;
	}

	public void setDetailAttributeName(
			SchemeCycleAttributeMasterBean detailAttributeName) {
		this.detailAttributeName = detailAttributeName;
	}



	public Integer getSchAttributeId() {
		return schAttributeId;
	}

	public void setSchAttributeId(Integer schAttributeId) {
		this.schAttributeId = schAttributeId;
	}

	public Integer getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(Integer attributeType) {
		this.attributeType = attributeType;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	@Override
	public String toString() {
		return "SchemeCycleAttributeMasterBean [schAttributeId="
				+ schAttributeId + ", attributeType=" + attributeType
				+ ", activeFlag=" + activeFlag + ", createdByUser="
				+ createdByUser + "]";
	}

}
