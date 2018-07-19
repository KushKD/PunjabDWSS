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
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_sch_cyl_att_dtl",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeCycleAttributeDetailBean implements Serializable{
	
	private static final long serialVersionUID = 69267247548538475L;
	
	@Id
	@SequenceGenerator(name="sdu_sch_cyl_att_dtl_sch_attribute_dtl_id_seq",sequenceName="prwss_main.sdu_sch_cyl_att_dtl_sch_attribute_dtl_id_seq")
	@GeneratedValue(generator="sdu_sch_cyl_att_dtl_sch_attribute_dtl_id_seq",strategy=GenerationType.AUTO)
	@Column(name="sch_attribute_dtl_id")
	private Integer schAttributeDtlId;
	
	@OneToOne(targetEntity=SchemeCycleAttributeMasterBean.class)
	@JoinColumn(name="sch_attribute_id",referencedColumnName="sch_attribute_id",insertable=false,updatable=false)
	private SchemeCycleAttributeMasterBean  detailAttributeName;
	
	@OneToOne(targetEntity=SDUCycleActivityMapping.class)
	@JoinColumn(name="sch_attribute_id",referencedColumnName="activity_id",insertable=false,updatable=false)
	private SDUCycleActivityMapping  sDUCycleActivityMapping;
	
	
	
	
	@Column(name="sch_attribute_id")
	private Integer schAttributeId;
	
	@Column(name="attribute_name")
	private String attributeName;
	
	/*@Column(name="crt_date")
	private Date createdDate;*/
	
	@Column(name="crt_by_usr")
	private Integer createdByUser;

	
	public Integer getSchAttributeDtlId() {
		return schAttributeDtlId;
	}

	public void setSchAttributeDtlId(Integer schAttributeDtlId) {
		this.schAttributeDtlId = schAttributeDtlId;
	}

	public Integer getSchAttributeId() {
		return schAttributeId;
	}

	public void setSchAttributeId(Integer schAttributeId) {
		this.schAttributeId = schAttributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

/*	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}*/

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	public SchemeCycleAttributeMasterBean getDetailAttributeName() {
		return detailAttributeName;
	}

	public void setDetailAttributeName(SchemeCycleAttributeMasterBean detailAttributeName) {
		this.detailAttributeName = detailAttributeName;
	}

	public SDUCycleActivityMapping getsDUCycleActivityMapping() {
		return sDUCycleActivityMapping;
	}

	public void setsDUCycleActivityMapping(SDUCycleActivityMapping sDUCycleActivityMapping) {
		this.sDUCycleActivityMapping = sDUCycleActivityMapping;
	}

	@Override
	public String toString() {
		return "SchemeCycleAttributeDetailBean [schAttributeDtlId=" + schAttributeDtlId + ", detailAttributeName="
				+ detailAttributeName + ", sDUCycleActivityMapping=" + sDUCycleActivityMapping + ", schAttributeId="
				+ schAttributeId + ", attributeName=" + attributeName + ", createdByUser=" + createdByUser + "]";
	}

}
