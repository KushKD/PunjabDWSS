/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;
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
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="san_motivator_acedemic_mapping",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MotivatorAcademicMapping implements Serializable {

	private static final long serialVersionUID = -363503490815L;
	
	@Id
	@GeneratedValue(generator="motivator_acedemic_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="motivator_acedemic_id_seq",sequenceName="prwss_main.motivator_acedemic_id_seq")
	@Column(name="motivator_acedemic_id")
	private int motivatorAcademicId;
	
	@OneToOne(targetEntity=MotivatorBean.class)
	@JoinColumn(name="motivator_id", nullable=false)
	private MotivatorBean motivatorBean;
	
	@Column(name="exam_name")
	private String examName;
	
	@Column(name="exam_year")
	private String examYear;
	
	@Column(name="total_marks")
	private String totalMarks;
	
	@Column(name="marks_obtained")
	private String marksObtained;
	
	@Column(name="board_university")
	private String boardUniversity;
	
	@Column(name="crt_by_usr")
	private Integer createdById;
	
	@Column(name="active_flag")
	private Integer activeFlag;

	
	
	public MotivatorBean getMotivatorBean() {
		return motivatorBean;
	}

	public void setMotivatorBean(MotivatorBean motivatorBean) {
		this.motivatorBean = motivatorBean;
	}

	public int getMotivatorAcademicId() {
		return motivatorAcademicId;
	}

	public void setMotivatorAcademicId(int motivatorAcademicId) {
		this.motivatorAcademicId = motivatorAcademicId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamYear() {
		return examYear;
	}
	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	public String getTotalMarks() {	
		return totalMarks;
	}

	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}

	public String getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getBoardUniversity() {
		return boardUniversity;
	}

	public void setBoardUniversity(String boardUniversity) {
		this.boardUniversity = boardUniversity;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "MotivatorAcademicMapping [motivatorAcademicId=" + motivatorAcademicId + ", examName=" + examName + ", examYear=" + examYear + ", totalMarks=" + totalMarks
				+ ", marksObtained=" + marksObtained + ", boardUniversity=" + boardUniversity + ", createdById="
				+ createdById + ", activeFlag=" + activeFlag + "]";
	}
}
