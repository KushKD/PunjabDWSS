/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "san_payment_to_beneficiary_details", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class BeneficiaryPaymentDetailsBean implements Serializable {
	
	private static final long serialVersionUID = -36350123815L;

	@Id
	@GeneratedValue(generator = "request_detail_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "request_detail_id_seq", sequenceName = "prwss_main.request_detail_id_seq")
	@Column(name = "request_detail_id")
	private int requestDetailId;
	
	
	@OneToOne(targetEntity=BeneficiaryEntryBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="beneficiary_id",referencedColumnName="beneficiery_id", insertable=false, updatable=false)
	private BeneficiaryEntryBean beneficiaryEntryBean;
	
	@OneToOne(targetEntity=ProgressStageMappingBean.class,fetch=FetchType.LAZY)
	@JoinColumn(name="progress_stage_id",referencedColumnName="progress_stage_id", insertable=false, updatable=false)
	private ProgressStageMappingBean progressStageMappingBean;
	
	
	@Column(name="paymet_request_id")
	private Integer paymetRequestId;
	
	@Column(name="beneficiary_id")
	private Integer beneficiaryId;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="userid")
	private Integer userid;
	
	@Column(name="pay_order_no")
	private String payOrderNo;
	
	@Column(name="approval_no")
	private String approvalNo;
	
	@Column(name="progress_stage_id")
	private Integer progressStageId;
	
	@Column(name="is_approved")
	private Integer isApproved;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag;

	@Column(name="is_delete")
	private Integer isDelete;
	
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public BeneficiaryEntryBean getBeneficiaryEntryBean() {
		return beneficiaryEntryBean;
	}

	public void setBeneficiaryEntryBean(BeneficiaryEntryBean beneficiaryEntryBean) {
		this.beneficiaryEntryBean = beneficiaryEntryBean;
	}

	public ProgressStageMappingBean getProgressStageMappingBean() {
		return progressStageMappingBean;
	}

	public void setProgressStageMappingBean(ProgressStageMappingBean progressStageMappingBean) {
		this.progressStageMappingBean = progressStageMappingBean;
	}

	public int getRequestDetailId() {
		return requestDetailId;
	}

	public void setRequestDetailId(int requestDetailId) {
		this.requestDetailId = requestDetailId;
	}

	public Integer getPaymetRequestId() {
		return paymetRequestId;
	}

	public void setPaymetRequestId(Integer paymetRequestId) {
		this.paymetRequestId = paymetRequestId;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}

	public Integer getProgressStageId() {
		return progressStageId;
	}

	public void setProgressStageId(Integer progressStageId) {
		this.progressStageId = progressStageId;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
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
		return "BeneficiaryPaymentDetailsBean [requestDetailId=" + requestDetailId + ", beneficiaryEntryBean="
				+ beneficiaryEntryBean + ", progressStageMappingBean=" + progressStageMappingBean + ", paymetRequestId="
				+ paymetRequestId + ", beneficiaryId=" + beneficiaryId + ", amount=" + amount + ", userid=" + userid
				+ ", payOrderNo=" + payOrderNo + ", approvalNo=" + approvalNo + ", progressStageId=" + progressStageId
				+ ", isApproved=" + isApproved + ", remarks=" + remarks + ", crtByUsr=" + crtByUsr + ", activeFlag="
				+ activeFlag + "]";
	}

}
