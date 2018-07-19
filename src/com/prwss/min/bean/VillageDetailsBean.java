package com.prwss.min.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "village_detail_mapping", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageDetailsBean implements Serializable {

	private static final long serialVersionUID = 785898374234671926L;

	@Id
	@GeneratedValue(generator = "village_details_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "village_details_seq", sequenceName = "prwss_main.village_details_seq")
	@Column(name = "location_id", nullable = false)
	private Integer location_id;
	
	@OneToMany(targetEntity=VillageSchemeMappingBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="village_id",updatable = false , insertable = false)
	private Set<VillageSchemeMappingBean> villageSchemeMappingBean;

	/*@Column(name = "location_id")
	private Integer location_id;*/
	
	
	
/*	@Column(name = "parent_habitation_name")
	private String parent_habitation_name;*/
	
	@Column(name = "gram_panchayat")
	private String gram_panchayat;

	@Column(name = "active_flag")
	private Integer active_flag;
	
	@Column(name = "block_id")
	private String block_id;
	@Column(name = "sub_div")
	private Integer subDiv;
	
	@Column(name = "divisional_id")
	private Integer divisionalId;
	
	@Column(name = "constituancy_id")
	private Integer constituancyId;
	
	
	
	public Integer getConstituancyId() {
		return constituancyId;
	}

	public void setConstituancyId(Integer constituancyId) {
		this.constituancyId = constituancyId;
	}

	public Integer getSubDiv() {
		return subDiv;
	}

	public void setSubDiv(Integer subDiv) {
		this.subDiv = subDiv;
	}

	public Integer getDivisionalId() {
		return divisionalId;
	}

	public void setDivisionalId(Integer divisionalId) {
		this.divisionalId = divisionalId;
	}

	public String getBlock_id() {
		return block_id;
	}

	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}

/*	public int getVillageIds() {
		return villageIds;
	}

	public void setVillageIds(int villageIds) {
		this.villageIds = villageIds;
	}*/

	public Set<VillageSchemeMappingBean> getVillageSchemeMappingBean() {
		return villageSchemeMappingBean;
	}

	public void setVillageSchemeMappingBean(Set<VillageSchemeMappingBean> villageSchemeMappingBean) {
		this.villageSchemeMappingBean = villageSchemeMappingBean;
	}

	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}
/*
	public String getParent_habitation_name() {
		return parent_habitation_name;
	}

	public void setParent_habitation_name(String parent_habitation_name) {
		this.parent_habitation_name = parent_habitation_name;
	}*/

	public String getGram_panchayat() {
		return gram_panchayat;
	}

	public void setGram_panchayat(String gram_panchayat) {
		this.gram_panchayat = gram_panchayat;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}
/*
	@Override
	public String toString() {
		return "VillageDetailsBean [villageIds=" + villageIds + ", villageSchemeMappingBean=" + villageSchemeMappingBean
				+ ", location_id=" + location_id + ", parent_habitation_name=" + parent_habitation_name
				+ ", gram_panchayat=" + gram_panchayat + ", active_flag=" + active_flag + "]";
	}*/

	@Override
	public String toString() {
		return "VillageDetailsBean [villageSchemeMappingBean=" + villageSchemeMappingBean
				+ ", location_id=" + location_id + ", gram_panchayat=" + gram_panchayat + ", active_flag=" + active_flag
				+ "]";
	}
	
}
