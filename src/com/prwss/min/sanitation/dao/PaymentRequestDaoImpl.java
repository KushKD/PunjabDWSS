/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentBean;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentDetailsBean;
import com.prwss.min.sanitation.bean.ProgressWorkBean;
import com.prwss.min.sanitation.form.PaymentRequestForm;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
public class PaymentRequestDaoImpl extends HibernateDaoSupport implements PaymentRequestDao {

	private Logger log = Logger.getLogger(PaymentRequestDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> findBeneficiary(PaymentRequestForm paymentRequestForm) throws DataAccessException {

		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria subQuery = DetachedCriteria.forClass(BeneficiaryPaymentBean.class,
					"beneficiaryPaymentBean");
			subQuery.createAlias("beneficiaryPaymentBean.beneficiaryPaymentDetailsBean",
					"beneficiaryPaymentDetailsBean");
			subQuery.add(Restrictions.eq("beneficiaryPaymentBean.crtByUsr",
					Integer.parseInt(String.valueOf(paymentRequestForm.getLoginUser()))));
			subQuery.setProjection(
					Projections.property("beneficiaryPaymentDetailsBean.progressStageId").as("progressStageId"));

			DetachedCriteria criteria = DetachedCriteria.forClass(ProgressWorkBean.class, "progressWorkBean");
			criteria.createAlias("progressWorkBean.progressStageMappingBean", "progressStageMappingBean");
			criteria.createAlias("progressStageMappingBean.progressStageMaster", "progressStageMaster");
			criteria.createAlias("progressWorkBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.add(
					Restrictions.eq("progressWorkBean.districtId", Integer.parseInt(paymentRequestForm.getDistrict())));
			criteria.add(Restrictions.eq("progressWorkBean.blockId", Integer.parseInt(paymentRequestForm.getBlock())));
			criteria.add(Restrictions.eq("progressWorkBean.gramPanchayatId",
					Integer.parseInt(paymentRequestForm.getGramPanchayatId())));
			criteria.add(
					Restrictions.eq("progressWorkBean.villageId", Integer.parseInt(paymentRequestForm.getVillage())));
			criteria.add(Restrictions.eq("progressStageMappingBean.stageStatus",86));
			criteria.add(Property.forName("progressStageMappingBean.progressStageId").notIn(subQuery));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
					.add(Projections.property("progressStageMaster.stageName"), "stageName")
					.add(Projections.property("progressStageMaster.amount"), "amount")
					.add(Projections.property("progressStageMappingBean.progressStageId"), "progressStageId"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}

		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getPayment(PaymentRequestForm paymentRequestForm) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentBean.class, "progressWorkBean");
			criteria.createAlias("progressWorkBean.beneficiaryPaymentDetailsBean", "beneficiaryPaymentDetailsBean");
			criteria.add(Restrictions.eq("progressWorkBean.activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.max("progressWorkBean.paymetRequestId").as("paymetRequestId"))
					.add(Projections.groupProperty("beneficiaryPaymentDetailsBean.activeFlag"))
					.add(Projections.property("beneficiaryPaymentDetailsBean.activeFlag"), "activeFlag"));

			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return beneficiaryDto;

	}

	@Override
	public int saveBeneficiaryPayment(BeneficiaryPaymentBean beneficiaryPaymentBean) throws DataAccessException {

		try {
			getHibernateTemplate().save(beneficiaryPaymentBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return beneficiaryPaymentBean.getPaymetRequestId();
	}

	@Override
	public boolean saveBeneficiaryPaymentDetails(List<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(beneficiaryPaymentDetailsBeans);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getPaymentDetails(PaymentRequestForm paymentRequestForm) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentDetailsBean.class,
					"beneficiaryPaymentDetailsBean");
			criteria.add(
					Restrictions.eq("beneficiaryPaymentDetailsBean.activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.max("beneficiaryPaymentDetailsBean.requestDetailId").as("requestDetailId"))
					.add(Projections.groupProperty("beneficiaryPaymentDetailsBean.activeFlag"))
					.add(Projections.property("beneficiaryPaymentDetailsBean.activeFlag"), "activeFlag"));

			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return beneficiaryDto;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getProgressStageDetails(String progressStageId) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProgressWorkBean.class, "progressWorkBean");
			criteria.createAlias("progressWorkBean.progressStageMappingBean", "progressStageMappingBean");
			criteria.createAlias("progressStageMappingBean.progressStageMaster", "progressStageMaster");
			criteria.createAlias("progressWorkBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.add(
					Restrictions.eq("progressStageMappingBean.progressStageId", Integer.parseInt(progressStageId)));
			criteria.setProjection(
					Projections.projectionList().add(Projections.property("progressStageMaster.stageName"), "stageName")
							.add(Projections.property("progressStageMaster.amount"), "amount")
							.add(Projections.property("progressStageMappingBean.pictureStage"), "pictureStage")
							.add(Projections.property("progressWorkBean.crtDate"), "crt_date")
							
					);
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getBillDetails(PaymentRequestForm paymentRequestForm) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentBean.class,
					"beneficiaryPaymentBean");
			criteria.createAlias("beneficiaryPaymentBean.beneficiaryPaymentDetailsBean",
					"beneficiaryPaymentDetailsBean");
			criteria.createAlias("beneficiaryPaymentDetailsBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.createAlias("beneficiaryPaymentDetailsBean.progressStageMappingBean", "progressStageMappingBean");
			criteria.createAlias("progressStageMappingBean.progressStageMaster", "progressStageMaster");
			
			criteria.add(Restrictions.eq("beneficiaryPaymentBean.lyingWithUser",
					Integer.parseInt(String.valueOf(paymentRequestForm.getLoginUser()))));
			criteria.add(Restrictions.ne("beneficiaryPaymentBean.isSubmit",
					Integer.parseInt(MISConstants.ONE)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
					.add(Projections.property("progressStageMaster.stageName"), "stageName")
					.add(Projections.property("beneficiaryPaymentDetailsBean.amount"), "amount")
					.add(Projections.property("progressStageMappingBean.progressStageId"), "progressStageId")
					.add(Projections.property("beneficiaryPaymentBean.paymetRequestId"), "paymetRequestId")
					.add(Projections.property("beneficiaryPaymentBean.billno"), "billno"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryPaymentBean> validateBillDetails(Integer paymentRequestId) throws DataAccessException {
		List<BeneficiaryPaymentBean> beneficiaryPaymentBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentBean.class,
					"beneficiaryPaymentBean");
			criteria.add(Restrictions.eq("beneficiaryPaymentBean.paymetRequestId", paymentRequestId));
			beneficiaryPaymentBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return beneficiaryPaymentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getBillAmount(PaymentRequestForm paymentRequestForm) throws DataAccessException {
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentBean.class,
					"beneficiaryPaymentBean");
			criteria.createAlias("beneficiaryPaymentBean.beneficiaryPaymentDetailsBean",
					"beneficiaryPaymentDetailsBean");
			criteria.createAlias("beneficiaryPaymentDetailsBean.beneficiaryEntryBean", "beneficiaryEntryBean");
			criteria.createAlias("beneficiaryPaymentDetailsBean.progressStageMappingBean", "progressStageMappingBean");
			criteria.createAlias("progressStageMappingBean.progressStageMaster", "progressStageMaster");

			criteria.add(Restrictions.eq("beneficiaryPaymentBean.lyingWithUser",
					Integer.parseInt(String.valueOf(paymentRequestForm.getLoginUser()))));

			criteria.setProjection(
					Projections.projectionList().add(Projections.sum("beneficiaryPaymentDetailsBean.amount"), "amount")

			);
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlowMasterBean> getEmployeeDetails(Integer userId) throws DataAccessException {
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(WorkFlowMasterBean.class);
			criteria.add(Restrictions.eq("from_emp_id", userId));
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("subject_id", Integer.parseInt(MISConstants.THREE)));
			workFlowMasterBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return workFlowMasterBeans;
	}

	@Override
	public boolean updateBeneficiaryPayment(BeneficiaryPaymentBean beneficiaryPaymentBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(beneficiaryPaymentBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
