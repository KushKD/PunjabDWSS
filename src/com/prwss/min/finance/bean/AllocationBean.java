/**
 * 
 */
package com.prwss.min.finance.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Table(name="fin_fund_alloc_mst",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class AllocationBean implements Serializable{

private static final long serialVersionUID = 363553776815L;
	
	@Id
	@GeneratedValue(generator = "fin_fund_alloc_mst_allocation_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "fin_fund_alloc_mst_allocation_id_seq", sequenceName = "prwss_main.fin_fund_alloc_mst_allocation_id_seq")
	@Column(name="allocation_id")
	private Long allocation_id;
	
	@Column(name = "request_no")
	private Long requestNo;
	
	@Column(name = "head")
	private Integer head;
	
	@Column(name = "allocation_date")
	private Date allocationDate;
	
	@Column(name = "allocation_no", length = 1024)
	private String allocationNo;
	
	@Column(name = "n_amnt_rel")
	private Double amntReleased;
	
	@Column(name = "n_amnt_revoked")
	private Double amntRevoked;
	
	@Column(name = "n_net_amnt")
	private Double netAmount;
	
	@Column(name = "active_flag")
	private Integer activeFlag;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;

	
	

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Long crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	public Long getAllocation_id() {
		return allocation_id;
	}

	public void setAllocation_id(Long allocation_id) {
		this.allocation_id = allocation_id;
	}

	public Long getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(Long requestNo) {
		this.requestNo = requestNo;
	}

	public Integer getHead() {
		return head;
	}

	public void setHead(Integer head) {
		this.head = head;
	}

	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	public String getAllocationNo() {
		return allocationNo;
	}

	public void setAllocationNo(String allocationNo) {
		this.allocationNo = allocationNo;
	}

	public Double getAmntReleased() {
		return amntReleased;
	}

	public void setAmntReleased(Double amntReleased) {
		this.amntReleased = amntReleased;
	}

	public Double getAmntRevoked() {
		return amntRevoked;
	}

	public void setAmntRevoked(Double amntRevoked) {
		this.amntRevoked = amntRevoked;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	
	
	
	
}
