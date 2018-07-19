/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentBean;
import com.prwss.min.sanitation.bean.BeneficiaryPaymentDetailsBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author BH390738
 *
 */
public class VerifyPaymentDaoImpl extends HibernateDaoSupport implements VerifyPaymentDao {

	private Logger log = Logger.getLogger(VerifyPaymentDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getBillDetails(Long enterBy) throws DataAccessException {
		
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentBean.class,
					"beneficiaryPaymentBean");
			criteria.createAlias("beneficiaryPaymentBean.beneficiaryPaymentDetailsBean",
					"beneficiaryPaymentDetailsBean");
			
			criteria.add(Restrictions.eq("beneficiaryPaymentBean.lyingWithUser",
					Integer.parseInt(String.valueOf(enterBy))));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.sum("beneficiaryPaymentDetailsBean.amount"), "amount")
					.add(Projections.count("beneficiaryPaymentDetailsBean.beneficiaryId"), "totalBeneficiary")
					.add(Projections.property("beneficiaryPaymentBean.paymetRequestId"), "paymetRequestId")
					.add(Projections.property("beneficiaryPaymentBean.billno"), "billno")
					.add(Projections.groupProperty("beneficiaryPaymentBean.billno"))
			        .add(Projections.groupProperty("beneficiaryPaymentBean.paymetRequestId"))
			        .add(Projections.groupProperty("beneficiaryPaymentDetailsBean.beneficiaryId")));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> getPaymentDetails(String paymentRequestId, Long enterBy) throws DataAccessException {
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
					Integer.parseInt(String.valueOf(enterBy))));
			criteria.add(Restrictions.eq("beneficiaryPaymentBean.paymetRequestId",Integer.parseInt(paymentRequestId)));
			criteria.add(Restrictions.ne("beneficiaryPaymentDetailsBean.isDelete",Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
					.add(Projections.property("progressStageMaster.stageName"), "stageName")
					.add(Projections.property("beneficiaryPaymentDetailsBean.amount"), "amount")
					.add(Projections.property("progressStageMappingBean.progressStageId"), "progressStageId")
					.add(Projections.property("beneficiaryPaymentBean.paymetRequestId"), "paymetRequestId")
					.add(Projections.property("beneficiaryPaymentBean.billno"), "billno")
					.add(Projections.property("beneficiaryPaymentDetailsBean.requestDetailId"), "requestDetailId"));
			
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryPaymentDetailsBean> findPaymentDetails(String requestDetailId)throws DataAccessException {
		
		List<BeneficiaryPaymentDetailsBean> beneficiaryPaymentDetailsBeans=null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentDetailsBean.class);
			criteria.add(Restrictions.eq("requestDetailId", Integer.parseInt(requestDetailId)));
			
			beneficiaryPaymentDetailsBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return beneficiaryPaymentDetailsBeans;
	}

	@Override
	public boolean deletePaymentDetails(BeneficiaryPaymentDetailsBean beneficiaryPaymentDetailsBean)
			throws DataAccessException {
		try{
			getHibernateTemplate().update(beneficiaryPaymentDetailsBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updatePaymentDetails(List<Integer> paymentRequestId) throws DataAccessException {
		
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryPaymentBean> validatePaymentDetails(List<Integer> paymentRequestId)
			throws DataAccessException {
		
		List<BeneficiaryPaymentBean> beneficiaryPaymentBeans=null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryPaymentBean.class);
			criteria.add(Restrictions.in("paymetRequestId", paymentRequestId));
			
			beneficiaryPaymentBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return beneficiaryPaymentBeans;
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
		
		try{
			getHibernateTemplate().update(beneficiaryPaymentBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}
}
