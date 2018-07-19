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
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_component_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class ComponentBean implements Serializable{
	
	private static final long serialVersionUID = 363553776815L;
	
	@Id
	@GeneratedValue(generator = "finance_component_master_fin_comp_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_component_master_fin_comp_id_seq", sequenceName = "prwss_main.finance_component_master_fin_comp_id_seq")
	@Column(name="fin_comp_id")
	private Long componentId;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="component_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsComponentType;
	
	@OneToOne(targetEntity=ComponentDetailsBean.class)
	@JoinColumn(name="parent_compo_id",referencedColumnName="fin_comp_id",insertable=false,updatable=false)
	private ComponentDetailsBean  componentDetailsBean;
	
	
	@Column(name = "component_type")
	private Integer componentType;
	
	@Column(name = "parent_compo_id")
	private Integer parentCompoId;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	
	
	
	public ComponentDetailsBean getComponentDetailsBean() {
		return componentDetailsBean;
	}

	public void setComponentDetailsBean(ComponentDetailsBean componentDetailsBean) {
		this.componentDetailsBean = componentDetailsBean;
	}

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	

	public ComboBeanDetails getComboDetailsComponentType() {
		return comboDetailsComponentType;
	}

	public void setComboDetailsComponentType(ComboBeanDetails comboDetailsComponentType) {
		this.comboDetailsComponentType = comboDetailsComponentType;
	}

	public Integer getComponentType() {
		return componentType;
	}

	public void setComponentType(Integer componentType) {
		this.componentType = componentType;
	}

	public Integer getParentCompoId() {
		return parentCompoId;
	}

	public void setParentCompoId(Integer parentCompoId) {
		this.parentCompoId = parentCompoId;
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
		return "ComponentBean [componentId=" + componentId + ", comboDetailsHeadType=" + comboDetailsComponentType
				+ ", componentType=" + componentType + ", parentCompoId=" + parentCompoId + ", crtByUsr=" + crtByUsr
				+ ", activeFlag=" + activeFlag + "]";
	}
}
