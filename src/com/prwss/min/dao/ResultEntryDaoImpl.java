package com.prwss.min.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.LabToParameterMapping;
import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.ResultEntryDetailBean;
import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class ResultEntryDaoImpl extends HibernateDaoSupport implements ResultEntryDao {

	@Override
	public int saveResultEntryData(ResultEntryBean resultEntryBean) throws DataAccessException {

		try {
			getHibernateTemplate().save(resultEntryBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return resultEntryBean.getTestResultId();
	}

	@Override
	public boolean saveResultEntryDetails(Collection<ResultEntryDetailBean> resultEntryBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(resultEntryBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDistributionBean> getSamplePartNo(String sampleNo,String mode) throws DataAccessException {
		// TODO Auto-generated method stub
		List<SampleDistributionBean> sampleDistributionBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class);
			if (MisUtility.ifEmpty(sampleNo)) {
				criteria.add(Restrictions.eq("labName", Integer.parseInt(sampleNo)));
				if(!mode.equalsIgnoreCase("modify")){
					criteria.add(Restrictions.ne("isEntered", Integer.parseInt(MISConstants.ONE)));
				}
				criteria.add(Restrictions.eq("sampleType", MISConstants.NORMAL));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			}

			sampleDistributionBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return sampleDistributionBeans;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDistributionBean> getSamplePartNos(String samplePartNum) throws DataAccessException {
		// TODO Auto-generated method stub
		List<SampleDistributionBean> sampleDistributionBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class);
			if (MisUtility.ifEmpty(samplePartNum)) {
				criteria.add(Restrictions.eq("samplePartNum", samplePartNum));
				criteria.add(Restrictions.eq("sampleType", MISConstants.NORMAL));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			}

			sampleDistributionBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return sampleDistributionBeans;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDistributionBean> fetchEmployeeId(String samplePartNo) throws DataAccessException {

		List<SampleDistributionBean> sampleDistributionBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class);

			criteria.add(Restrictions.eq("samplePartNum", samplePartNo));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			sampleDistributionBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return sampleDistributionBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> fetchEmployeeName(List<Long> empId, List<String> status) throws DataAccessException {
		// TODO Auto-generated method stub
		List<EmployeeBean> employeeBean = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);

			criteria.add(Restrictions.in("employeeId", empId));
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
	public List<SampleDistributionBean> fetchSampleNumber(String samplePartNumber) throws DataAccessException {
		// TODO Auto-generated method stub
		List<SampleDistributionBean> receiveSampleBean = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(SampleDistributionBean.class);
			criteria.add(Restrictions.ilike("samplePartNum",samplePartNumber,MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			receiveSampleBean = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return receiveSampleBean;
	}

	@Override
	public ReceiveSampleBean getSampleName(int sampleId) throws DataAccessException {
		// TODO Auto-generated method stub
		ReceiveSampleBean receiveSampleBean = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(ReceiveSampleBean.class);
			criteria.add(Restrictions.eq("sampleId", sampleId));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			receiveSampleBean = (ReceiveSampleBean) getHibernateTemplate().findByCriteria(criteria).get(0);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return receiveSampleBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ResultEntryBean> getLocationMasterByPagination(int empId, String searchString, int clickedColumn,
			String colOrder) throws DataAccessException {
		Set<ResultEntryBean> resultEntryDtos = null;
		Session session=null;
		try {
			session=getSession();
			DetachedCriteria criteria = DetachedCriteria.forClass(ResultEntryBean.class, "resultEntryBean");
			criteria.createAlias("resultEntryBean.locDetailBeans", "locDetailBeans");
			criteria.createAlias("resultEntryBean.receiveSampleBean", "receiveSampleBean");
			criteria.add(Restrictions.eq("resultEntryBean.activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("resultEntryBean.lyingWithUser", empId));
			criteria.add(Restrictions.eq("receiveSampleBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

			if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))) {
				criteria.addOrder(Order.desc("resultEntryBean.testResultId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("receiveSampleBean.sampleNumber", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("{alias}.test_result_id::text like '%" + searchString + "%'")));

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("receiveSampleBean.sampleNumber"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("receiveSampleBean.sampleNumber"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("resultEntryBean.testResultId"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("resultEntryBean.testResultId"));
				}
			}
			criteria.getExecutableCriteria(session).setMaxResults(100);
			getHibernateTemplate().setCacheQueries(true);
			resultEntryDtos = new HashSet<ResultEntryBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return resultEntryDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultEntryBean> getResultEntry(int testResultId) throws DataAccessException {
		List<ResultEntryBean> resultEntryDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ResultEntryBean.class, "resultEntryBean");
			criteria.createAlias("resultEntryBean.locDetailBeans", "locDetailBeans");
			criteria.add(Restrictions.eq("resultEntryBean.testResultId", testResultId));
			resultEntryDtos = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return resultEntryDtos;
	}

	@Override
	public boolean updateResultEntryData(ResultEntryBean resultEntryBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(resultEntryBean);
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateResultEntryDetails(Collection<ResultEntryDetailBean> resultEntryBean)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(resultEntryBean);
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabToParameterMapping> getLabToParameter(SamplePartCodeLabMapping samplePartCodeLabMapping) throws DataAccessException {
		
		List<LabToParameterMapping> labToParameterMappings=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(LabToParameterMapping.class);
			criteria.add(Restrictions.eq("lab_id",samplePartCodeLabMapping.getLabId()));
			criteria.add(Restrictions.eq("test_type",samplePartCodeLabMapping.getCategory_type1()));
			
			labToParameterMappings=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		
		return labToParameterMappings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParameterMasterBean> findParameterList(List<Integer> pararmeterId) throws DataAccessException {
		List<ParameterMasterBean> parameterMasterBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(ParameterMasterBean.class);
			criteria.add(Restrictions.in("parameterId", pararmeterId));
			parameterMasterBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			
		}
		return parameterMasterBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SamplePartCodeLabMapping> fetchTestType(SampleDistributionBean sampleDistributionBean) throws DataAccessException {
		List<SamplePartCodeLabMapping> samplePartCodeLabMappings = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SamplePartCodeLabMapping.class);
			criteria.add(Restrictions.eq("labId", sampleDistributionBean.getLabName()));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.add(Restrictions.eq("testLabSampleSubCodeId", Long.parseLong(String.valueOf(sampleDistributionBean.getTests()))));
			samplePartCodeLabMappings = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return samplePartCodeLabMappings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceiveSampleBean> fetchSampleCollectionDetails(SampleDistributionBean sampleDistributionBean)
			throws DataAccessException {
		List<ReceiveSampleBean> receiveSampleBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(ReceiveSampleBean.class);
			criteria.add(Restrictions.eq("sampleNumber", sampleDistributionBean.getSampleNum()));
			receiveSampleBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			Log.debug(e.getMessage());
			throw e;
		}
		return receiveSampleBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultEntryBean> findResultEntryList(String resultEntryId)
			throws DataAccessException {
		List<ResultEntryBean> resultEntryBeans=null;
		try{
			if(MisUtility.ifEmpty(resultEntryId)){
				DetachedCriteria criteria=DetachedCriteria.forClass(ResultEntryBean.class,"resultEntryBean");
				criteria.createAlias("resultEntryBean.locDetailBeans", "locDetailBeans");
				criteria.createAlias("locDetailBeans.parameterMasterBean", "parameterMasterBean");
				criteria.add(Restrictions.eq("testResultId", Integer.parseInt(resultEntryId)));
				resultEntryBeans=getHibernateTemplate().findByCriteria(criteria);
			}
			
		}catch(DataAccessException e){
			Log.debug(e.getLocalizedMessage());
			throw e;
		}
		return resultEntryBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleDistributionBean> fetchSamplePartDistributionDate(String samplePartNo)
			throws DataAccessException {
		List<SampleDistributionBean> sampleDistributionBeans=null;
		try{
			if(MisUtility.ifEmpty(sampleDistributionBeans)){
				DetachedCriteria criteria=DetachedCriteria.forClass(SampleDistributionBean.class);
				criteria.add(Restrictions.eq("samplePartNum",samplePartNo));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
				sampleDistributionBeans=getHibernateTemplate().findByCriteria(criteria);
			}
			
		}catch(DataAccessException e){
			Log.debug(e.getLocalizedMessage());
			throw e;
		}
		return sampleDistributionBeans;
	}


}
