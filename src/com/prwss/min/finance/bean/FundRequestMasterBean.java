/**
 * 
 */
package com.prwss.min.finance.bean;

import java.io.Serializable;
import java.sql.Date;

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

import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.PmsSchemeMaster;
import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_fund_request_master",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class FundRequestMasterBean implements Serializable {

	private static final long serialVersionUID = 3635335776815L;
	
	
	@Id
	@GeneratedValue(generator = "finance_fund_request_master_fund_request_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_fund_request_master_fund_request_id_seq", sequenceName = "prwss_main.finance_fund_request_master_fund_request_id_seq")
	@Column(name="fund_request_id")
	private Long fundRequestId;

	@OneToOne(targetEntity=PmsSchemeMaster.class)
	@JoinColumn(name="scheme_id",insertable=false,updatable=false)
	private PmsSchemeMaster pmsSchemeMaster;
	
	@OneToOne(targetEntity=PMSSchemeDetailsBean.class)
	@JoinColumn(name="scheme_id",referencedColumnName="scheme_id",insertable=false,updatable=false)
	private PMSSchemeDetailsBean pmsSchemeDetailsBean;

	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="scheme_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailSchemeType;
	
	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="installment_id",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailInstallmentId;
	
	@OneToOne(targetEntity=LocationDivisionSubDivisonDetailsBean.class)
	@JoinColumn(name="division_id",referencedColumnName="div_subdiv_detail_id",insertable=false,updatable=false)
	private LocationDivisionSubDivisonDetailsBean  locationDivisionSubDivisonDetailsBean;
		
	@OneToOne(targetEntity=DdoMasterBean.class)
	@JoinColumn(name="crt_by_usr",referencedColumnName="crt_by_usr",insertable=false,updatable=false)
	private DdoMasterBean ddoMasterBean;
	
	
	
	public ComboBeanDetails getComboDetailInstallmentId() {
		return comboDetailInstallmentId;
	}

	public void setComboDetailInstallmentId(ComboBeanDetails comboDetailInstallmentId) {
		this.comboDetailInstallmentId = comboDetailInstallmentId;
	}

	public DdoMasterBean getDdoMasterBean() {
		return ddoMasterBean;
	}

	public void setDdoMasterBean(DdoMasterBean ddoMasterBean) {
		this.ddoMasterBean = ddoMasterBean;
	}

	public PMSSchemeDetailsBean getPmsSchemeDetailsBean() {
		return pmsSchemeDetailsBean;
	}

	public void setPmsSchemeDetailsBean(PMSSchemeDetailsBean pmsSchemeDetailsBean) {
		this.pmsSchemeDetailsBean = pmsSchemeDetailsBean;
	}

	@Column(name = "installment_id")
	private Integer installmentId;
	
	@Column(name = "division_id")
	private Integer divisionId;
	
	@Column(name = "district_id")
	private Integer districtId;
	
	@Column(name = "request_no")
	private String request_no;
	
	
	@Column(name = "block_id")
	private Integer blockId;
	
	@Column(name = "gpwsc_id")
	private Integer gpwscId;
	
	@Column(name = "scheme_type")
	private Integer schemeType;
	
	@Column(name = "scheme_id")
	private Integer schemeId;
	
	@Column(name = "adm_app_no")
	private String adm_app_no;
	
	@Column(name = "adm_app_val")
	private Double adm_app_val;
	
	@Column(name = "adm_app_dt")
	private Date adm_app_dt;
	
	@Column(name = "mis_code")
	private String mis_code;
	
	@Column(name = "techsac_app_no")
	private Double techsac_app_no;
	
	@Column(name = "techsac_app_val")
	private Double techsac_app_val;
	
	@Column(name = "techsac_app_dt")
	private Date techsac_app_dt;
	
	@Column(name = "wb_app_no")
	private String wb_app_no;
	
	@Column(name = "wb_app_date")
	private Date wb_app_date;
	
	@Column(name = "ben_share_due")
	private Long ben_share_due;
	
	@Column(name = "ben_share_coll")
	private Long ben_share_coll;
	
	@Column(name = "net_dsr")
	private Long net_dsr;
	
	@Column(name = "proc_pckg_dt")
	private Date proc_pckg_dt;
	
	
	@Column(name = "proc_pckg_num")
	private String proc_pckg_num;
	
	@Column(name = "award_date")
	private Date award_date;
	
	@Column(name = "award_number")
	private String award_number;
	
	
	@Column(name = "award_val")
	private Long award_val;
	
	@Column(name = "value_of_inst")
	private Double value_of_inst;
	
	@Column(name = "step_entry")
	private Long step_entry;
	
	@Column(name = "package_no")
	private Long package_no;
	
	
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	
	


	public LocationDivisionSubDivisonDetailsBean getLocationDivisionSubDivisonDetailsBean() {
		return locationDivisionSubDivisonDetailsBean;
	}

	public void setLocationDivisionSubDivisonDetailsBean(
			LocationDivisionSubDivisonDetailsBean locationDivisionSubDivisonDetailsBean) {
		this.locationDivisionSubDivisonDetailsBean = locationDivisionSubDivisonDetailsBean;
	}

	public ComboBeanDetails getComboDetailSchemeType() {
		return comboDetailSchemeType;
	}

	public void setComboDetailSchemeType(ComboBeanDetails comboDetailSchemeType) {
		this.comboDetailSchemeType = comboDetailSchemeType;
	}

	public PmsSchemeMaster getPmsSchemeMaster() {
		return pmsSchemeMaster;
	}

	public void setPmsSchemeMaster(PmsSchemeMaster pmsSchemeMaster) {
		this.pmsSchemeMaster = pmsSchemeMaster;
	}

	public String getRequest_no() {
		return request_no;
	}

	public void setRequest_no(String request_no) {
		this.request_no = request_no;
	}

	public Long getFundRequestId() {
		return fundRequestId;
	}

	public void setFundRequestId(Long fundRequestId) {
		this.fundRequestId = fundRequestId;
	}

	public Integer getInstallmentId() {
		return installmentId;
	}

	public void setInstallmentId(Integer installmentId) {
		this.installmentId = installmentId;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Integer getGpwscId() {
		return gpwscId;
	}

	public void setGpwscId(Integer gpwscId) {
		this.gpwscId = gpwscId;
	}

	public Integer getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(Integer schemeType) {
		this.schemeType = schemeType;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public String getAdm_app_no() {
		return adm_app_no;
	}

	public void setAdm_app_no(String adm_app_no) {
		this.adm_app_no = adm_app_no;
	}

	public Double getAdm_app_val() {
		return adm_app_val;
	}

	public void setAdm_app_val(Double adm_app_val) {
		this.adm_app_val = adm_app_val;
	}

	public Date getAdm_app_dt() {
		return adm_app_dt;
	}

	public void setAdm_app_dt(Date adm_app_dt) {
		this.adm_app_dt = adm_app_dt;
	}

	public String getMis_code() {
		return mis_code;
	}

	public void setMis_code(String mis_code) {
		this.mis_code = mis_code;
	}

	public Double getTechsac_app_no() {
		return techsac_app_no;
	}

	public void setTechsac_app_no(Double techsac_app_no) {
		this.techsac_app_no = techsac_app_no;
	}

	public Double getTechsac_app_val() {
		return techsac_app_val;
	}

	public void setTechsac_app_val(Double techsac_app_val) {
		this.techsac_app_val = techsac_app_val;
	}

	public Date getTechsac_app_dt() {
		return techsac_app_dt;
	}

	public void setTechsac_app_dt(Date techsac_app_dt) {
		this.techsac_app_dt = techsac_app_dt;
	}

	public String getWb_app_no() {
		return wb_app_no;
	}

	public void setWb_app_no(String wb_app_no) {
		this.wb_app_no = wb_app_no;
	}

	public Date getWb_app_date() {
		return wb_app_date;
	}

	public void setWb_app_date(Date wb_app_date) {
		this.wb_app_date = wb_app_date;
	}

	public Long getBen_share_due() {
		return ben_share_due;
	}

	public void setBen_share_due(Long ben_share_due) {
		this.ben_share_due = ben_share_due;
	}

	public Long getBen_share_coll() {
		return ben_share_coll;
	}

	public void setBen_share_coll(Long ben_share_coll) {
		this.ben_share_coll = ben_share_coll;
	}

	public Long getNet_dsr() {
		return net_dsr;
	}

	public void setNet_dsr(Long net_dsr) {
		this.net_dsr = net_dsr;
	}

	public Date getProc_pckg_dt() {
		return proc_pckg_dt;
	}

	public void setProc_pckg_dt(Date proc_pckg_dt) {
		this.proc_pckg_dt = proc_pckg_dt;
	}

	public String getProc_pckg_num() {
		return proc_pckg_num;
	}

	public void setProc_pckg_num(String proc_pckg_num) {
		this.proc_pckg_num = proc_pckg_num;
	}

	public Date getAward_date() {
		return award_date;
	}

	public void setAward_date(Date award_date) {
		this.award_date = award_date;
	}

	public String getAward_number() {
		return award_number;
	}

	public void setAward_number(String award_number) {
		this.award_number = award_number;
	}

	public Long getAward_val() {
		return award_val;
	}

	public void setAward_val(Long award_val) {
		this.award_val = award_val;
	}

	public Double getValue_of_inst() {
		return value_of_inst;
	}

	public void setValue_of_inst(Double value_of_inst) {
		this.value_of_inst = value_of_inst;
	}

	public Long getStep_entry() {
		return step_entry;
	}

	public void setStep_entry(Long step_entry) {
		this.step_entry = step_entry;
	}

	public Long getPackage_no() {
		return package_no;
	}

	public void setPackage_no(Long package_no) {
		this.package_no = package_no;
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
		return "FundSourceMasterBean [fundSourceId=" + fundRequestId + ", installmentId=" + installmentId
				+ ", divisionId=" + divisionId + ", districtId=" + districtId + ", blockId=" + blockId + ", gpwscId="
				+ gpwscId + ", schemeType=" + schemeType + ", schemeId=" + schemeId + ", adm_app_no=" + adm_app_no
				+ ", adm_app_val=" + adm_app_val + ", adm_app_dt=" + adm_app_dt + ", mis_code=" + mis_code
				+ ", techsac_app_no=" + techsac_app_no + ", techsac_app_val=" + techsac_app_val + ", techsac_app_dt="
				+ techsac_app_dt + ", wb_app_no=" + wb_app_no + ", wb_app_date=" + wb_app_date + ", ben_share_due="
				+ ben_share_due + ", ben_share_coll=" + ben_share_coll + ", net_dsr=" + net_dsr + ", proc_pckg_dt="
				+ proc_pckg_dt + ", proc_pckg_num=" + proc_pckg_num + ", award_date=" + award_date + ", award_number="
				+ award_number + ", award_val=" + award_val + ", value_of_inst=" + value_of_inst + ", step_entry="
				+ step_entry + ", package_no=" + package_no + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag
				+ "]";
	}

}
