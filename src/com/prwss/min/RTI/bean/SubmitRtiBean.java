package com.prwss.min.RTI.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.prwss.mis.common.util.MISConstants;


	@Entity
	@Table(name = "rti_details", schema = MISConstants.MIS_DB_SCHEMA_NAME)

	public class SubmitRtiBean implements Serializable {
		
		
		private static final long serialVersionUID = 565898371593691926L;
		
		
		@Id
		@GeneratedValue(generator = "rti_details_rti_id_seq", strategy = GenerationType.AUTO)
		@SequenceGenerator(name = "rti_details_rti_id_seq", sequenceName = "prwss_main.rti_details_rti_id_seq")
		
		
		@Column(name = "rti_id")
		private Integer rtiID;
		
		@Column(name = "rti_refno")
		private String rtiRefNo;
		
		@Column(name = "office_type")
		private Integer office;
		
		@Column(name = "zone_id")
		private Integer zone;
		
		@Column(name = "circle_id")
		private Integer circle;
		
		@Column(name = "disrict_id")
		private Integer district;
		
		@Column(name = "division_id")
		private Integer division;
		
		@Column(name = "subdivision_id")
		private Integer subdivision;
		
		@Column(name = "wing_id")
		private Integer wings;
		
		@Column(name = "pio_id")
		private Integer pio;
		
		@Column(name = "rti_type_id")
		private Integer rtiType;
		
		@Column(name = "rti_title")
		private String shortname;
		
		@Column(name = "rti_details")
		private String details;
		
		@Column(name = "applicant_name")
		private String name;
		
		@Column(name = "applicant_gender")
		private Integer gender;
		
		@Column(name = "applicant_mobile_no")
		private Long mobile;
		
		@Column(name = "applicant_landline")
		private Long landline;
		
		@Column(name = "applicant_district_id")
		private Integer districta;
		
		@Column(name = "applicant_block_id")
		private Integer block;
		
		@Column(name = "applicant_village_id")
		private Integer villageId;
		
		@Column(name = "applicant_email")
		private String email;
		
		@Column(name = "applicant_address")
		private String address;
		
		@Column(name = "applicant_educaton")
		private String literate;
		
		@Column(name = "applicant_povertyline")
		private Integer poverty;
		
		@Column(name = "rti_applicationdoc_name")
		private String applicationFileName;

		@Column(name="rti_applicationdoc")
		@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
		@Basic(fetch=FetchType.EAGER)
		private byte[] applicationFile;
				
		@Column(name = "rti_supportingdoc_name")
		private String documentsFileName;
		
		@Column(name="rti_supportingdoc")
		@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
		@Basic(fetch=FetchType.EAGER)
		private byte[] documentsFile;
		
		@Column(name = "payment_amount")
		private Integer amount;
		
		@Column(name = "payment_mode")
		private String paymentMode;
		
		@Column(name = "payment_date")
		private Date datePayment;
		
		@Column(name = "payment_remarks")
		private String remarks;
		
		@Column(name = "Payment_Via")
		private Integer paymentVia;
		
		@Column(name = "Pay_Via_number")
		private String number;
		
		@Column(name = "userid")
		private Integer userID;
		
	/*	@Column(name = "lst_updated_date")
		private Date
		
		@Column(name = "lst_updated_user")
		private Integer
		*/
		@Column(name = "crt_date")
		private Date createdDate;
		
		@Column(name = "crt_by_usr")
		private Integer createdByUser;
		
		@Column(name = "loc_id")
		private Integer locationId;
		
		@Column(name = "active_flag")
		private Integer activeFlag;
		
		@Column(name = "response_remark")
		private String responseRemarks;
		
		@Column(name = "response_name")
		private String responseName;
		
		@Column(name="response")
		@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
		@Basic(fetch=FetchType.EAGER)
		private byte[] responseFile;
		
		@Column(name = "isclosed")
		private Integer isclosed;
		
		@Column(name = "isassigned")
		private Integer isassigned;
		
		@Column(name = "designation_assign")
		private Integer designation;
		
		@Column(name = "employee_assign")
		private Integer employee;
		
		@Column(name = "duedate_assign")
		private Date dueDate;
		
		@Column(name = "assign_remarks")
		private String assignRemarks;

		public Integer getRtiID() {
			return rtiID;
		}

		public void setRtiID(Integer rtiID) {
			this.rtiID = rtiID;
		}

		public String getRtiRefNo() {
			return rtiRefNo;
		}

		public void setRtiRefNo(String rtiRefNo) {
			this.rtiRefNo = rtiRefNo;
		}

		public Integer getOffice() {
			return office;
		}

		public void setOffice(Integer office) {
			this.office = office;
		}

		public Integer getZone() {
			return zone;
		}

		public void setZone(Integer zone) {
			this.zone = zone;
		}

		public Integer getCircle() {
			return circle;
		}

		public void setCircle(Integer circle) {
			this.circle = circle;
		}

		public Integer getDistrict() {
			return district;
		}

		public void setDistrict(Integer district) {
			this.district = district;
		}

		public Integer getDivision() {
			return division;
		}

		public void setDivision(Integer division) {
			this.division = division;
		}

		public Integer getSubdivision() {
			return subdivision;
		}

		public void setSubdivision(Integer subdivision) {
			this.subdivision = subdivision;
		}

		public Integer getWings() {
			return wings;
		}

		public void setWings(Integer wings) {
			this.wings = wings;
		}

		public Integer getPio() {
			return pio;
		}

		public void setPio(Integer pio) {
			this.pio = pio;
		}

		public Integer getRtiType() {
			return rtiType;
		}

		public void setRtiType(Integer rtiType) {
			this.rtiType = rtiType;
		}

		public String getShortname() {
			return shortname;
		}

		public void setShortname(String shortname) {
			this.shortname = shortname;
		}

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getGender() {
			return gender;
		}

		public void setGender(Integer gender) {
			this.gender = gender;
		}

		public Long getMobile() {
			return mobile;
		}

		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}

		public Long getLandline() {
			return landline;
		}

		public void setLandline(Long landline) {
			this.landline = landline;
		}

		public Integer getDistricta() {
			return districta;
		}

		public void setDistricta(Integer districta) {
			this.districta = districta;
		}

		public Integer getBlock() {
			return block;
		}

		public void setBlock(Integer block) {
			this.block = block;
		}

		public Integer getVillageId() {
			return villageId;
		}

		public void setVillageId(Integer villageId) {
			this.villageId = villageId;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getLiterate() {
			return literate;
		}

		public void setLiterate(String literate) {
			this.literate = literate;
		}

		public Integer getPoverty() {
			return poverty;
		}

		public void setPoverty(Integer poverty) {
			this.poverty = poverty;
		}

		public String getApplicationFileName() {
			return applicationFileName;
		}

		public void setApplicationFileName(String applicationFileName) {
			this.applicationFileName = applicationFileName;
		}

		public byte[] getApplicationFile() {
			return applicationFile;
		}

		public void setApplicationFile(byte[] applicationFile) {
			this.applicationFile = applicationFile;
		}

		public String getDocumentsFileName() {
			return documentsFileName;
		}

		public void setDocumentsFileName(String documentsFileName) {
			this.documentsFileName = documentsFileName;
		}

		public byte[] getDocumentsFile() {
			return documentsFile;
		}

		public void setDocumentsFile(byte[] documentsFile) {
			this.documentsFile = documentsFile;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public String getPaymentMode() {
			return paymentMode;
		}

		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}

		public Date getDatePayment() {
			return datePayment;
		}

		public void setDatePayment(Date datePayment) {
			this.datePayment = datePayment;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public Integer getPaymentVia() {
			return paymentVia;
		}

		public void setPaymentVia(Integer paymentVia) {
			this.paymentVia = paymentVia;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public Integer getUserID() {
			return userID;
		}

		public void setUserID(Integer userID) {
			this.userID = userID;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Integer getCreatedByUser() {
			return createdByUser;
		}

		public void setCreatedByUser(Integer createdByUser) {
			this.createdByUser = createdByUser;
		}

		public Integer getLocationId() {
			return locationId;
		}

		public void setLocationId(Integer locationId) {
			this.locationId = locationId;
		}

		public Integer getActiveFlag() {
			return activeFlag;
		}

		public void setActiveFlag(Integer activeFlag) {
			this.activeFlag = activeFlag;
		}

		public String getResponseRemarks() {
			return responseRemarks;
		}

		public void setResponseRemarks(String responseRemarks) {
			this.responseRemarks = responseRemarks;
		}

		public String getResponseName() {
			return responseName;
		}

		public void setResponseName(String responseName) {
			this.responseName = responseName;
		}

		public byte[] getResponseFile() {
			return responseFile;
		}

		public void setResponseFile(byte[] responseFile) {
			this.responseFile = responseFile;
		}

		public Integer getIsclosed() {
			return isclosed;
		}

		public void setIsclosed(Integer isclosed) {
			this.isclosed = isclosed;
		}

		public Integer getIsassigned() {
			return isassigned;
		}

		public void setIsassigned(Integer isassigned) {
			this.isassigned = isassigned;
		}

		public Integer getDesignation() {
			return designation;
		}

		public void setDesignation(Integer designation) {
			this.designation = designation;
		}

		public Integer getEmployee() {
			return employee;
		}

		public void setEmployee(Integer employee) {
			this.employee = employee;
		}

		public Date getDueDate() {
			return dueDate;
		}

		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}

		public String getAssignRemarks() {
			return assignRemarks;
		}

		public void setAssignRemarks(String assignRemarks) {
			this.assignRemarks = assignRemarks;
		}
		
		
		
}