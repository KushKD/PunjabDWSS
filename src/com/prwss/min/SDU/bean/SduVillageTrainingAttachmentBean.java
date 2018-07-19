package com.prwss.min.SDU.bean;

import java.io.Serializable;
import java.util.Arrays;

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

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="sdu_village_training_attachment",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SduVillageTrainingAttachmentBean implements Serializable{
	
	private static final long serialVersionUID = 3635019276719815L;
	
	@Id
	@GeneratedValue(generator="sdu_village_training_material_trng_material_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="sdu_village_training_material_trng_material_id_seq",sequenceName="prwss_main.sdu_village_training_material_trng_material_id_seq")
	@Column(name="trng_attach_id")
	private Integer trng_attach_id;
	
	@Column(name="training_id")
	private Long training_id;
	
	@Column(name="attachment_title")
	private String attachment_title;
	
	@Column(name="attachment")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch=FetchType.LAZY)
	private byte[] attachment;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	
	
	public Integer getTrng_attach_id() {
		return trng_attach_id;
	}

	public void setTrng_attach_id(Integer trng_attach_id) {
		this.trng_attach_id = trng_attach_id;
	}

	public Long getTraining_id() {
		return training_id;
	}

	public void setTraining_id(Long training_id) {
		this.training_id = training_id;
	}

	public String getAttachment_title() {
		return attachment_title;
	}

	public void setAttachment_title(String attachment_title) {
		this.attachment_title = attachment_title;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "SduVillageTrainingAttachmentBean [trng_attach_id=" + trng_attach_id + ", training_id=" + training_id
				+ ", attachment_title=" + attachment_title + ", attachment=" + Arrays.toString(attachment)
				+ ", crt_by_user=" + crt_by_user + "]";
	}
	
}
