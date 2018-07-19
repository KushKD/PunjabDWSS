/**
 * 
 */
package com.prwss.min.bean;

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
 * @author bhsingh
 *
 */

@Entity

@Table(name="wq_test_result_Comments",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TestResultCommentBean implements Serializable{

	private static final long serialVersionUID = 70589836671926L;

	//wq_test_result_comments_test_result_cmnt_id_seq
	
	@Id
	@GeneratedValue(generator = "wq_test_result_comments_test_result_cmnt_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "wq_test_result_comments_test_result_cmnt_id_seq", sequenceName = "prwss_main.wq_test_result_comments_test_result_cmnt_id_seq")
	@Column(name = "test_result_cmnt_id")
	private int testResultCommentId;
	
	
	@Column(name="test_result_id")
	private Integer testResultId;
	
	@Column(name="comment_text")
	private String commentText;
	
	@Column(name="comment_by")
	private Integer commentBy;
	
	@Column(name="comment_date")
	private Date commentDate;
	
	@Column(name="crt_date")
	private Date createdDate;
	
	@Column(name="crt_by_user")
	private Integer crt_by_user;

	public int getTestResultCommentId() {
		return testResultCommentId;
	}

	public void setTestResultCommentId(int testResultCommentId) {
		this.testResultCommentId = testResultCommentId;
	}

	public Integer getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Integer getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(Integer commentBy) {
		this.commentBy = commentBy;
	}

	
	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCrt_by_user() {
		return crt_by_user;
	}

	public void setCrt_by_user(Integer crt_by_user) {
		this.crt_by_user = crt_by_user;
	}

	@Override
	public String toString() {
		return "TestResultCommentBean [testResultCommentId=" + testResultCommentId + ", testResultId=" + testResultId
				+ ", commentText=" + commentText + ", commentBy=" + commentBy + ", commentDate=" + commentDate
				+ ", createdDate=" + createdDate + ", crt_by_user=" + crt_by_user + "]";
	}
	
}
