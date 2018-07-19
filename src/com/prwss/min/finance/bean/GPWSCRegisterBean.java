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

import com.prwss.min.sanitation.bean.ComboBeanDetails;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "finance_gpwsc_register", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class GPWSCRegisterBean implements Serializable {

	private static final long serialVersionUID = 36355776815L;

	@Id
	@GeneratedValue(generator = "finance_gpwsc_register_finance_gpwsc_register_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_gpwsc_register_finance_gpwsc_register_seq", sequenceName = "prwss_main.finance_gpwsc_register_finance_gpwsc_register_seq")
	@Column(name = "finance_gpwsc_register")
	private Long financeGpwscRegister;

	@OneToOne(targetEntity=ComboBeanDetails.class)
	@JoinColumn(name="transaction_type",referencedColumnName="cmb_dtl_id",insertable=false,updatable=false)
	private ComboBeanDetails  comboDetailsTransactionType;
	
	@Column(name = "divsion_id")
	private Integer divsionId;

	@Column(name = "sub_div_id")
	private Integer subDivId;

	@Column(name = "village_id")
	private Integer villageId;

	@Column(name = "gpwsc_id")
	private Integer gpwscId;

	@Column(name = "scheme_id")
	private Integer schemeId;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "transaction_type")
	private Integer transactionType;

	@Column(name = "transaction_no")
	private String transactionNo;

	@Column(name = "bank_id")
	private Integer bank_id;

	@Column(name = "payee_name")
	private String payeeName;

	@Column(name = "bill_no")
	private String billNo;

	@Column(name = "payment_type")
	private Integer paymentType;

	@Column(name = "payment_amt")
	private Double paymentAmt;

	@Column(name = "payment_remark")
	private String paymentRemark;

	@Column(name = "reciept_type")
	private Integer recieptType;

	@Column(name = "reciept_amt")
	private Double recieptAmt;

	@Column(name = "reciept_remark")
	private String recieptRemark;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	

	public ComboBeanDetails getComboDetailsTransactionType() {
		return comboDetailsTransactionType;
	}

	public void setComboDetailsTransactionType(ComboBeanDetails comboDetailsTransactionType) {
		this.comboDetailsTransactionType = comboDetailsTransactionType;
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

	public Long getFinanceGpwscRegister() {
		return financeGpwscRegister;
	}

	public void setFinanceGpwscRegister(Long financeGpwscRegister) {
		this.financeGpwscRegister = financeGpwscRegister;
	}

	public Integer getDivsionId() {
		return divsionId;
	}

	public void setDivsionId(Integer divsionId) {
		this.divsionId = divsionId;
	}

	public Integer getSubDivId() {
		return subDivId;
	}

	public void setSubDivId(Integer subDivId) {
		this.subDivId = subDivId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getGpwscId() {
		return gpwscId;
	}

	public void setGpwscId(Integer gpwscId) {
		this.gpwscId = gpwscId;
	}

	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Integer getBank_id() {
		return bank_id;
	}

	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(Double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public String getPaymentRemark() {
		return paymentRemark;
	}

	public void setPaymentRemark(String paymentRemark) {
		this.paymentRemark = paymentRemark;
	}

	public Integer getRecieptType() {
		return recieptType;
	}

	public void setRecieptType(Integer recieptType) {
		this.recieptType = recieptType;
	}

	public Double getRecieptAmt() {
		return recieptAmt;
	}

	public void setRecieptAmt(Double recieptAmt) {
		this.recieptAmt = recieptAmt;
	}

	public String getRecieptRemark() {
		return recieptRemark;
	}

	public void setRecieptRemark(String recieptRemark) {
		this.recieptRemark = recieptRemark;
	}

	@Override
	public String toString() {
		return "GPWSCRegisterBean [financeGpwscRegister=" + financeGpwscRegister + ", divsionId=" + divsionId
				+ ", subDivId=" + subDivId + ", villageId=" + villageId + ", gpwscId=" + gpwscId + ", schemeId="
				+ schemeId + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType
				+ ", transactionNo=" + transactionNo + ", bank_id=" + bank_id + ", payeeName=" + payeeName + ", billNo="
				+ billNo + ", paymentType=" + paymentType + ", paymentAmt=" + paymentAmt + ", paymentRemark="
				+ paymentRemark + ", recieptType=" + recieptType + ", recieptAmt=" + recieptAmt + ", recieptRemark="
				+ recieptRemark + "]";
	}

}
