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

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_component_detail",schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class ComponentDetailsBean implements Serializable {

	private static final long serialVersionUID = 3635576815L;
	
	@Id
	@GeneratedValue(generator = "finance_component_detail_fin_comp_dtl_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_component_detail_fin_comp_dtl_id_seq", sequenceName = "prwss_main.finance_component_detail_fin_comp_dtl_id_seq")
	@Column(name="fin_comp_dtl_id")
	private Long componentDetailsId;
	
	@OneToOne(targetEntity=ComponentBean.class)
	@JoinColumn(name="fin_comp_id",referencedColumnName="fin_comp_id",insertable=false,updatable=false)
	private ComponentBean  componentBean;
	
	@Column(name = "fin_comp_id")
	private Long finCompId;
	
	@Column(name = "component_name")
	private String componentName;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	@Column(name="com_description")
	private String description;
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ComponentBean getComponentBean() {
		return componentBean;
	}

	public void setComponentBean(ComponentBean componentBean) {
		this.componentBean = componentBean;
	}

	public Long getComponentDetailsId() {
		return componentDetailsId;
	}

	public void setComponentDetailsId(Long componentDetailsId) {
		this.componentDetailsId = componentDetailsId;
	}

	
	public Long getFinCompId() {
		return finCompId;
	}

	public void setFinCompId(Long finCompId) {
		this.finCompId = finCompId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
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
		return "ComponentDetailsBean [componentDetailsId=" + componentDetailsId + ", componentBean=" + componentBean
				+ ", finCompId=" + finCompId + ", componentName=" + componentName + ", crtByUsr=" + crtByUsr
				+ ", activeFlag=" + activeFlag + "]";
	}

}
