/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
import java.sql.Date;

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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "san_odf_declaration", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ODFDeclarationBean implements Serializable {
	
	private static final long serialVersionUID = 36350123215L;

	@Id
	@GeneratedValue(generator = "odf_declaration_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "odf_declaration_id_seq", sequenceName = "prwss_main.odf_declaration_id_seq")
	@Column(name = "odf_declaration_id")
	private int odfDeclarationId;
	
	
	@Column(name="district_id")
	private Integer districtId;
	
	
	@Column(name="block_id")
	private Integer blockId;
	
	@Column(name="gram_panchayat_id")
	private String gramPanchayatId;
	
	@Column(name="village_id")
	private Integer villageId;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="meeting_date")
	private Date meetingDate;
	
	@Column(name="meeting_place")
	private Integer meetingPlace;
	
	@Column(name="chaired_by")
	private String chairedBy;
	
	@Column(name="declaration_name")
	private String declarationName;
	
	@Column(name="declaration")
	@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@Basic(fetch = FetchType.LAZY)
	private byte[] declaration;

	public int getOdfDeclarationId() {
		return odfDeclarationId;
	}

	public void setOdfDeclarationId(int odfDeclarationId) {
		this.odfDeclarationId = odfDeclarationId;
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

	public String getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(String gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
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

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public Integer getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(Integer meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public String getChairedBy() {
		return chairedBy;
	}

	public void setChairedBy(String chairedBy) {
		this.chairedBy = chairedBy;
	}

	public String getDeclarationName() {
		return declarationName;
	}

	public void setDeclarationName(String declarationName) {
		this.declarationName = declarationName;
	}

	public byte[] getDeclaration() {
		return declaration;
	}

	public void setDeclaration(byte[] declaration) {
		this.declaration = declaration;
	}
	
	
	
}
