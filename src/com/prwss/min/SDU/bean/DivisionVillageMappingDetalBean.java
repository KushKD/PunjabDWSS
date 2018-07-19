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

import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;


@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_division_village_dtl",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DivisionVillageMappingDetalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2421138845261154794L;
	
	@Id
	@SequenceGenerator(name="sdu_division_village_dtl_div_village_dtl_id_seq",sequenceName="prwss_main.sdu_division_village_dtl_div_village_dtl_id_seq")
	@GeneratedValue(generator="sdu_division_village_dtl_div_village_dtl_id_seq",strategy=GenerationType.AUTO)
	@Column(name="div_village_dtl_id")
	private Integer divVillageDetailId;
	
	@OneToOne(targetEntity=DivisionVillageMappingBean.class)
	@JoinColumn(name="div_village_id",referencedColumnName="div_village_id",insertable=false,updatable=false)
	private DivisionVillageMappingBean  divisionVillageMappingBean;
	
	@OneToOne(targetEntity=LocationDetailsBean.class)
	@JoinColumn(name="village_id",referencedColumnName="location_id",insertable=false,updatable=false)
	private LocationDetailsBean  locationDetailsBean;
	
	@Column(name="div_village_id")
	private Integer divVillageId;

	
	@Column(name="village_id")
	private Integer villageId;

	@Column(name="active_flag")
	private Integer activeFlag;

	/*@Column (name="crt_date")
	private Date createdDate;*/

	@Column(name="crt_by_usr")
	private Integer createdByUser;

	public Integer getDivVillageDetailId() {
		return divVillageDetailId;
	}

	public void setDivVillageDetailId(Integer divVillageDetailId) {
		this.divVillageDetailId = divVillageDetailId;
	}

	public Integer getDivVillageId() {
		return divVillageId;
	}

	public void setDivVillageId(Integer divVillageId) {
		this.divVillageId = divVillageId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
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

	public DivisionVillageMappingBean getDivisionVillageMappingBean() {
		return divisionVillageMappingBean;
	}

	public void setDivisionVillageMappingBean(DivisionVillageMappingBean divisionVillageMappingBean) {
		this.divisionVillageMappingBean = divisionVillageMappingBean;
	}

	public LocationDetailsBean getLocationDetailsBean() {
		return locationDetailsBean;
	}

	public void setLocationDetailsBean(LocationDetailsBean locationDetailsBean) {
		this.locationDetailsBean = locationDetailsBean;
	}

	@Override
	public String toString() {
		return "DivisionVillageMappingDetalBean [divVillageDetailId=" + divVillageDetailId + ", divVillageId="
				+ divVillageId + ", villageId=" + villageId + ", activeFlag=" + activeFlag + ", createdByUser="
				+ createdByUser + "]";
	}
	
	

}
