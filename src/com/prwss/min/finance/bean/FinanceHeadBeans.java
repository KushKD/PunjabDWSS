/**
 * 
 */
package com.prwss.min.finance.bean;

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

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Table(name="finance_head_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class FinanceHeadBeans implements Serializable{

	private static final long serialVersionUID = 36355776815L;
	
	@Id
	@GeneratedValue(generator = "finance_head_master_head_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_head_master_head_id_seq", sequenceName = "prwss_main.finance_head_master_head_id_seq")
	@Column(name="head_id")
	private Long headId;
	
//	@OneToOne(targetEntity=ComboBeanDetails.class)
//	@JoinColumn(name="head_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
//	private ComboBeanDetails  comboDetailsHeadType;
	
	/*@OneToOne(targetEntity=FinanceHeadBean.class)
	@JoinColumn(name="parent_head_id",referencedColumnName="head_id",insertable=false,updatable=false)
	private FinanceHeadBean  financeHeadBean;*/
	
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="head_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsHeadType;
	
	@Column(name = "head_type")
	private Integer head_type;
	
	@Column(name = "head_name")
	private String headName;
	
	@Column(name = "head_number")
	private String headNumber;
	
	
	
	@Column(name = "parent_head_id")
	private Integer parent_head_id;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	
	
	
//	public ComboBeanDetails getComboDetailsHeadType() {
//		return comboDetailsHeadType;
//	}
//
//	public void setComboDetailsHeadType(ComboBeanDetails comboDetailsHeadType) {
//		this.comboDetailsHeadType = comboDetailsHeadType;
//	}

//	public FinanceHeadBean getFinanceHeadBean() {
//		return financeHeadBean;
//	}
//
//	public void setFinanceHeadBean(FinanceHeadBean financeHeadBean) {
//		this.financeHeadBean = financeHeadBean;
//	}

	public ComboBeanDetails getComboDetailsHeadType() {
		return comboDetailsHeadType;
	}

	public void setComboDetailsHeadType(ComboBeanDetails comboDetailsHeadType) {
		this.comboDetailsHeadType = comboDetailsHeadType;
	}

	public Long getHeadId() {
		return headId;
	}

	public void setHeadId(Long headId) {
		this.headId = headId;
	}



	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadNumber() {
		return headNumber;
	}

	public void setHeadNumber(String headNumber) {
		this.headNumber = headNumber;
	}

	public Integer getHead_type() {
		return head_type;
	}

	public void setHead_type(Integer head_type) {
		this.head_type = head_type;
	}

	public Integer getParent_head_id() {
		return parent_head_id;
	}

	public void setParent_head_id(Integer parent_head_id) {
		this.parent_head_id = parent_head_id;
	}

	public Long getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Long crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "FinanceHeadBean [headId=" + headId + ", headName=" + headName + ", headNumber=" + headNumber
				+ ", head_type=" + head_type + ", parent_head_id=" + parent_head_id + ", crtByUsr=" + crtByUsr
				+ ", activeFlag=" + activeFlag + "]";
	}
	
}
