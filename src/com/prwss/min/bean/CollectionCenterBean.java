/**
 * 
 */
package com.prwss.min.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author BH390738
 *
 */

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="wq_collection_center",schema="prwss_main")
public class CollectionCenterBean implements Serializable {
	
	private static final long serialVersionUID = 13453245344L;

	@Id
	@GeneratedValue(generator = "wq_collection_center_collection_center_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "wq_collection_center_collection_center_id_seq", sequenceName = "prwss_main.wq_collection_center_collection_center_id_seq")
	@Column(name = "collection_center_id")
	private long collectionCenterId;
	

	@Column(name = "lab_id")
	private Integer labId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_number")
	private Long phoneNumber;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "crt_by_usr")
	private Long crtByUsr;
	
	@Column(name = "active_flag")
	private Integer activeFlag;

	
	public long getCollectionCenterId() {
		return collectionCenterId;
	}

	public void setCollectionCenterId(long collectionCenterId) {
		this.collectionCenterId = collectionCenterId;
	}

	public Integer getLabId() {
		return labId;
	}


	public void setLabId(Integer labId) {
		this.labId = labId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "CollectionCenterBean [collectionCenterId=" + collectionCenterId + ", labId=" + labId + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", crtByUsr=" + crtByUsr + ", activeFlag="
				+ activeFlag + "]";
	}
	
}
