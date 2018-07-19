/**
 * 
 */
package com.prwss.min.finance.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="finance_fund_request_doc",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class FundRequestDocBean implements Serializable {

	private static final long serialVersionUID = 36353357765L;
	
	@Id
	@GeneratedValue(generator = "finance_fund_request_doc_request_doc_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "finance_fund_request_doc_request_doc_id_seq", sequenceName = "prwss_main.finance_fund_request_doc_request_doc_id_seq")
	@Column(name="request_doc_id")
	private Long requestDocId;
	
	
	@Column(name = "fund_request_id")
	private Long fund_request_id;
	
	@Column(name = "attachment_id")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] attachment;
	
	@Column(name = "attachement_name")
	private String attachment_name;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	
	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	public Long getRequestDocId() {
		return requestDocId;
	}

	public void setRequestDocId(Long requestDocId) {
		this.requestDocId = requestDocId;
	}

	public Long getFund_request_id() {
		return fund_request_id;
	}

	public void setFund_request_id(Long fund_request_id) {
		this.fund_request_id = fund_request_id;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
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
	
}
