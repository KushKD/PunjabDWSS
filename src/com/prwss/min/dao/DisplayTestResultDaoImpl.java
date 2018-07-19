package com.prwss.min.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.TestResultCommentBean;
import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.quality.ResultEntryDto;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class DisplayTestResultDaoImpl extends HibernateDaoSupport implements DisplayTestResultDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultEntryDto> getResultEntry(int testResultId) {

		List<ResultEntryDto> resultEntryDto = null;
		System.out.println("---inside dao ----" + testResultId);
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ResultEntryBean.class, "resultEntryBean");
			criteria.createAlias("resultEntryBean.locDetailBeans", "locDetailBeans");
			criteria.createAlias("resultEntryBean.receiveSampleBean", "receiveSampleBean");
			criteria.createAlias("resultEntryBean.labMasterBean", "labMasterBean");
			criteria.createAlias("resultEntryBean.employeeBean", "employeeBean");
			criteria.createAlias("locDetailBeans.parameterMasterBean", "parameterMasterBean");
			
			criteria.add(Restrictions.eq("locDetailBeans.activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("receiveSampleBean.activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("labMasterBean.activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("resultEntryBean.testResultId", testResultId));
			
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("receiveSampleBean.sampleNumber"), "samplenumber")
					.add(Projections.property("resultEntryBean.testStatus"), "teststatus")
					.add(Projections.property("resultEntryBean.sampleId"), "sampleid")
					.add(Projections.property("resultEntryBean.requestLevel"), "requestlevel")
					.add(Projections.property("resultEntryBean.samplePartNo"), "samplepartno")
					.add(Projections.property("resultEntryBean.testResultId"), "testresultid")
					.add(Projections.property("locDetailBeans.paramId"), "paramid")
					.add(Projections.property("locDetailBeans.actualValue"), "actualvalue")
					.add(Projections.property("labMasterBean.labName"), "labname")
					.add(Projections.property("employeeBean.employeeName"), "testdoneby")
					.add(Projections.property("resultEntryBean.lyingWithUser"), "lyingWithUser")
			        .add(Projections.property("parameterMasterBean.parameterName"), "parameterName"));
			

			criteria.setResultTransformer(Transformers.aliasToBean(ResultEntryDto.class));
			resultEntryDto=getHibernateTemplate().findByCriteria(criteria);

			System.out.println("resultEntryDto---------------" + resultEntryDto);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return resultEntryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> fetchSampleId(String sampleNumber) throws DataAccessException {
		// TODO Auto-generated method stub
		List<ReceiveSampleBean> receiveSampleBean = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);
			criteria.add(Restrictions.eq("sampleNumber", sampleNumber));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			receiveSampleBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return receiveSampleBean;
	}

	@Override
	public boolean updateResultEntry(ResultEntryBean resultEntryBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(resultEntryBean);
		} catch (DataAccessException e) {
			Log.debug(e.getLocalizedMessage());
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultEntryBean> getResultEntry(String testId) throws DataAccessException {

		List<ResultEntryBean> resultEntryBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(ResultEntryBean.class);

			criteria.add(Restrictions.eq("testResultId", Integer.parseInt(testId)));

			resultEntryBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			Log.debug(e.getLocalizedMessage());
			throw e;
		}

		return resultEntryBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlowMasterBean> getWorkflowMaster(String testId, String level, String requestFlow, String empId)
			throws DataAccessException {

		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(WorkFlowMasterBean.class);

			criteria.add(Restrictions.eq("level", Integer.parseInt(level)));
			criteria.add(Restrictions.eq("from_emp_id", Integer.parseInt(empId)));
			criteria.add(Restrictions.eq("subject_id", 1));

			workFlowMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return workFlowMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> fetchEmployeeName(Long empId, List<String> status) throws DataAccessException {
		// TODO Auto-generated method stub
		List<EmployeeBean> employeeBean = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);

			criteria.add(Restrictions.eq("employeeId", empId));
			criteria.add(Restrictions.in("misAuditBean.status", status));

			employeeBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return employeeBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultEntryBean> getEmployeeId(int testId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<ResultEntryBean> resultEntryBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ResultEntryBean.class);

			criteria.add(Restrictions.eq("testResultId", testId));

			resultEntryBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return resultEntryBeans;
	}

	@Override
	public boolean saveComments(TestResultCommentBean testResultCommentBean) throws DataAccessException {
		try {
			getHibernateTemplate().save(testResultCommentBean);
		} catch (DataAccessException e) {
			Log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestResultCommentBean> fetchComments(int testresultid) throws DataAccessException {
		// TODO Auto-generated method stub
		List<TestResultCommentBean> testResultCommentBean = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(TestResultCommentBean.class);
			criteria.add(Restrictions.eq("testResultId", testresultid));
			testResultCommentBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return testResultCommentBean;
	}

}
