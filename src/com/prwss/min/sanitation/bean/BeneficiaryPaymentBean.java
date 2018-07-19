/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
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
@Table(name="san_payment_to_beneficiary",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BeneficiaryPaymentBean implements Serializable{

private static final long serialVersionUID = -3635012323815L;
	
	@Id
	@GeneratedValue(generator="paymet_request_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="paymet_request_id_seq",sequenceName="prwss_main.paymet_request_id_seq")
	@Column(name="paymet_request_id")
	private int paymetRequestId;
	
	@OneToMany(targetEntity = BeneficiaryPaymentDetailsBean.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name = "paymet_request_id", updatable = false, insertable = false)
	private Set<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBean;
	
	@Column(name="billno")
	private String billno;
	
	@Column(name="is_submit")
	private Integer isSubmit;
	
	@Column(name="lying_with_user")
	private Integer lyingWithUser;
	
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="userid")
	private Integer userid;
	
	
	

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public int getPaymetRequestId() {
		return paymetRequestId;
	}

	public void setPaymetRequestId(int paymetRequestId) {
		this.paymetRequestId = paymetRequestId;
	}

	public Set<BeneficiaryPaymentDetailsBean> getBeneficiaryPaymentDetailsBean() {
		return beneficiaryPaymentDetailsBean;
	}

	public void setBeneficiaryPaymentDetailsBean(Set<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBean) {
		this.beneficiaryPaymentDetailsBean = beneficiaryPaymentDetailsBean;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public Integer getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}

	public Integer getLyingWithUser() {
		return lyingWithUser;
	}

	public void setLyingWithUser(Integer lyingWithUser) {
		this.lyingWithUser = lyingWithUser;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
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
		return "BeneficiaryPaymentBean [paymetRequestId=" + paymetRequestId + ", beneficiaryPaymentDetailsBean="
				+ beneficiaryPaymentDetailsBean + ", billno=" + billno + ", isSubmit=" + isSubmit + ", lyingWithUser="
				+ lyingWithUser + ", remarks=" + remarks + ", crtByUsr=" + crtByUsr + ", activeFlag=" + activeFlag
				+ "]";
	}
	
}
