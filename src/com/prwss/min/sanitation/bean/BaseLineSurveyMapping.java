/**
 * 
 */
package com.prwss.min.sanitation.bean;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "baselinesurvey_mapping", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class BaseLineSurveyMapping implements Serializable {

	private static final long serialVersionUID = 363501238334415L;

	@Id
	@GeneratedValue(generator = "seq_beneficiary_survey_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_beneficiary_survey_id", sequenceName = "prwss_main.seq_beneficiary_survey_id")
	@Column(name = "beneficiary_survey_id")
	private int beneficiarySurveyId;
	
	
	@ManyToOne(targetEntity=BeneficiaryEntryBean.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="baslinesurveyid", nullable=false)
	private BaseLineMasterBean baslineSurveyBean;
	
	
	@Column(name="beneficiary_id")
	private Integer beneficiaryId;
	
	@Column(name="crt_by_usr")
	private Integer crtByUsr;

	public int getBeneficiarySurveyId() {
		return beneficiarySurveyId;
	}

	public void setBeneficiarySurveyId(int beneficiarySurveyId) {
		this.beneficiarySurveyId = beneficiarySurveyId;
	}
	
	public BaseLineMasterBean getBaslineSurveyBean() {
		return baslineSurveyBean;
	}

	public void setBaslineSurveyBean(BaseLineMasterBean baslineSurveyBean) {
		this.baslineSurveyBean = baslineSurveyBean;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}


	public Integer getCrtByUsr() {
		return crtByUsr;
	}

	public void setCrtByUsr(Integer crtByUsr) {
		this.crtByUsr = crtByUsr;
	}

	/*@Override
	public String toString() {
		return "BaseLineSurveyMapping [beneficiarySurveyId=" + beneficiarySurveyId + ", baslineSurveyBean="
				+ baslineSurveyBean + ", beneficiaryId=" + beneficiaryId + ", crtByUsr=" + crtByUsr + "]";
	}*/

}
