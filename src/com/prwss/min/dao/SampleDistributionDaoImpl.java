package com.prwss.min.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.min.quality.SampleDistributionForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class SampleDistributionDaoImpl extends HibernateDaoSupport implements SampleDistributionDao {

	private Logger log = Logger.getLogger(SampleDistributionDaoImpl.class);

	@Override
	public boolean saveSampleDistributionData(SampleDistributionBean sampleDistributionBean)
			throws DataAccessException {

		Logger log = Logger.getLogger(SampleDistributionDaoImpl.class);
		try {
			getHibernateTemplate().save(sampleDistributionBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDistributionBean> sampleDistributionData(SampleDistributionForm sampleDistributionForm)
			throws DataAccessException {

		List<SampleDistributionBean> Master_sample_distributionbeans = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class);

			if (MisUtility.ifEmpty(sampleDistributionForm.getLabName()))
				criteria.add(Restrictions.eq("labName", Integer.parseInt(sampleDistributionForm.getLabName())));
			if (MisUtility.ifEmpty(sampleDistributionForm.getSampleNum())) {
				criteria.add(Restrictions.eq("sampleNum", sampleDistributionForm.getSampleNum()));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			}

			Master_sample_distributionbeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return Master_sample_distributionbeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> fetchEmployeeName(List<Long> empId, List<String> status) throws DataAccessException {
		// TODO Auto-generated method stub
		List<EmployeeBean> employeeBean = null;
		List<String> desigId = null;
		try {
			desigId = new ArrayList<String>();
			desigId.add(MISConstants.TECHNICIAN);
			desigId.add(MISConstants.LAB_ASSISTANT);
			desigId.add(MISConstants.CHEMIST);

			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);

			criteria.add(Restrictions.in("employeeId", empId));
			criteria.add(Restrictions.in("designationId", desigId));
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
	public List<SampleDistributionBean> getsampleMasterBysamplenub(SampleDistributionForm sampleDistributionForm)
			throws DataAccessException {
		List<SampleDistributionBean> labMasterBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class);
			if (MisUtility.ifEmpty(sampleDistributionForm.getSampleNum())) {
				// criteria.add(Restrictions.eq("sampleNum",sampleDistributionForm.getSampleNum()));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			}

			labMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return labMasterBeans;
	}

	@Override
	public boolean UpdateSchemeDistributionMaster(SampleDistributionBean sampleDistributionBean)
			throws DataAccessException {
		try {
			System.out.println("STATUS IS " + sampleDistributionBean.getActiveFlag());
			getHibernateTemplate().update(sampleDistributionBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDistributionBean> getsampleDistributionByPagination(String searchString, int clickedColumn,
			String colOrder) throws DataAccessException {
		List<SampleDistributionBean> labMaster = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class,
					"sampleDistributionBean");
			criteria.createAlias("sampleDistributionBean.labMasterBean", "labMasterBean");
			criteria.createAlias("sampleDistributionBean.samplePartCodeLabMapping", "samplePartCodeLabMapping");
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("sampleDistributionBean.sampleId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("sampleNum", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("samplePartNum", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("labname::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("tests::text like '%" + searchString + "%'")));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("sampleNum"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("sampleNum"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("samplePartNum"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("samplePartNum"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("labName"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("labName"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("tests"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("tests"));
				}
			}
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			getHibernateTemplate().setCacheQueries(true);
			labMaster = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return labMaster;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabEmployee> fetchEmployeeId(int labId) throws DataAccessException {
		List<LabEmployee> labEmployees = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LabEmployee.class);

			criteria.add(Restrictions.eq("labId", labId));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			labEmployees = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return labEmployees;
	}

	@Override
	public boolean updateSampleDistributionData(SampleDistributionBean sampleDistributionBean)
			throws DataAccessException {
		Logger log = Logger.getLogger(SampleDistributionDaoImpl.class);
		try {
			getHibernateTemplate().update(sampleDistributionBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SamplePartCodeLabMapping> fetchTestType(String labId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<SamplePartCodeLabMapping> samplePartCodeLabMappings = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SamplePartCodeLabMapping.class);
			criteria.add(Restrictions.eq("labId", Integer.parseInt(labId)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			samplePartCodeLabMappings = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return samplePartCodeLabMappings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> getSampleNo(String labId) throws DataAccessException {
		// TODO Auto-generated method stub
		List<ReceiveSampleBean> receiveSampleBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);
			if (MisUtility.ifEmpty(labId)) {
				criteria.add(Restrictions.eq("labTested", Integer.parseInt(labId)));
				criteria.add(Restrictions.ne("is_freeze", Integer.parseInt(MISConstants.ONE)));
				// criteria.add(Restrictions.eq("sampleType",
				// MISConstants.NORMAL));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			}

			receiveSampleBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return receiveSampleBeans;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SamplePartCodeLabMapping> fetchTestType1(String testType) throws DataAccessException {
		// TODO Auto-generated method stub
		List<SamplePartCodeLabMapping> samplePartCodeLabMappings = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SamplePartCodeLabMapping.class);
			criteria.add(Restrictions.eq("testLabSampleSubCodeId", Long.parseLong(testType)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			samplePartCodeLabMappings = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return samplePartCodeLabMappings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SamplePartCodeLabMapping> fetchSubSampleNumber(String testType) throws DataAccessException {

		// TODO Auto-generated method stub
		List<SamplePartCodeLabMapping> samplePartCodeLabMappings = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SamplePartCodeLabMapping.class);
			criteria.add(Restrictions.eq("testLabSampleSubCodeId", Long.valueOf(testType)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			samplePartCodeLabMappings = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return samplePartCodeLabMappings;
	}

	@Override
	public boolean updateSamplePart(SamplePartCodeLabMapping samplePartCodeLabMapping) {

		try {
			getHibernateTemplate().update(samplePartCodeLabMapping);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> fetchSampleAcceptDate(String sampleNo) throws DataAccessException {
		List<ReceiveSampleBean> receiveSampleBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);
			criteria.add(Restrictions.eq("sampleNumber", sampleNo));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			receiveSampleBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return receiveSampleBeans;
	}

	@Override
	public boolean updateSampleCollection(String sampleNo, String freeze,Date currentDate) throws DataAccessException {
		boolean flag = false;
		Session session = getSession();
		try {
			
			Query qry = null;
		if (MisUtility.ifEmpty(freeze)) {
			qry=session.createQuery(
					"update ReceiveSampleBean receiveSampleBean set receiveSampleBean.is_distributed=? , receiveSampleBean.is_freeze=?, receiveSampleBean.freeze_date=?  where receiveSampleBean.sampleNumber='"
							+ sampleNo + "'");
			qry.setParameter(0, 1);
			qry.setParameter(1, 1);
			qry.setParameter(2,currentDate);
		}else{
			qry=session.createQuery(
					"update ReceiveSampleBean receiveSampleBean set receiveSampleBean.is_distributed=? , receiveSampleBean.is_freeze=?  where receiveSampleBean.sampleNumber='"
							+ sampleNo + "'");
			qry.setParameter(0, 1);
			qry.setParameter(1, 0);
		}
			int res = qry.executeUpdate();
			if (res == 1) {
				flag = true;
			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return flag;
	}

}
