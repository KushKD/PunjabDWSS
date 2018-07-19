package com.prwss.min.sanitation.bean;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "cmn_gram_panchayat_master", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class GramPanchayatMasterBean implements Serializable {

	private static final long serialVersionUID = 1323234325344L;

	@Id
	@GeneratedValue(generator = "cmn_gram_panchayat_master_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cmn_gram_panchayat_master_seq", sequenceName = "prwss_main.cmn_gram_panchayat_master_seq")
	@Column(name = "gram_panchayat_id")
	private Integer gramPanchayatId;

	/*
	 * @OneToMany(targetEntity = GramPanchayatDetailBean.class, fetch =
	 * FetchType.EAGER, cascade = CascadeType.ALL)
	 * 
	 * @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.
	 * SUBSELECT)
	 * 
	 * @JoinColumn(name = "gram_panchayat_id", updatable = false, insertable =
	 * false) private Set<GramPanchayatDetailBean> locDetailBeans;
	 */

	@Column(name = "gram_panchayat_name")
	private String nameofGramPanchayat;

	@Column(name = "sarpanch_name")
	private String nameofSarpanch;

	/*
	 * @Column(name = "test_done_by") private int testDoneBy;
	 */

	@Column(name = "crt_by_usr")
	private Integer createdByUser;

	/*
	 * public EmployeeBean getEmployeeBean() { return employeeBean; }
	 * 
	 * public void setEmployeeBean(EmployeeBean employeeBean) {
	 * this.employeeBean = employeeBean; }
	 */

	@Column(name = "active_flag")
	private Integer status;

	public Integer getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(Integer gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public String getNameofGramPanchayat() {
		return nameofGramPanchayat;
	}

	public void setNameofGramPanchayat(String nameofGramPanchayat) {
		this.nameofGramPanchayat = nameofGramPanchayat;
	}

	public String getNameofSarpanch() {
		return nameofSarpanch;
	}

	public void setNameofSarpanch(String nameofSarpanch) {
		this.nameofSarpanch = nameofSarpanch;
	}

	public Integer getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
