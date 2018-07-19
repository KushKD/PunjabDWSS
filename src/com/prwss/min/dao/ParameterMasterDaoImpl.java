/**
 * 
 */
package com.prwss.min.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.quality.ParameterMasterForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

/**
 * @author bhsingh
 *
 */
public class ParameterMasterDaoImpl extends HibernateDaoSupport implements ParameterMasterDao {

	private Logger log = Logger.getLogger(ParameterMasterDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getEmployee(List<String> statusList) throws DataAccessException {

		List<EmployeeBean> employeeBeans = null;
		try {
			List<Object[]> obj = getSession().createCriteria(EmployeeBean.class, "employeeBean")
					.add(Restrictions.isNotNull("employeeBean.desigId"))
					.add(Restrictions.in("misAuditBean.status", statusList))
					.setProjection(Projections.projectionList().add(Projections.property("employeeBean.employeeId"))
							.add(Projections.property("employeeBean.employeeName")))
					.list();

			if (!MisUtility.ifEmpty(obj)) {
				employeeBeans = new ArrayList<EmployeeBean>();
				for (Object[] object : obj) {
					EmployeeBean bean = new EmployeeBean();
					bean.setEmployeeId(Long.parseLong(object[0].toString()));
					bean.setEmployeeName(object[1].toString());
					employeeBeans.add(bean);
				}
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		// System.out.println("daooooooo+++++++++++"+employeeBeans);
		return employeeBeans;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParameterMasterBean> getParameterMasterByPagination(String searchString,int clickedColumn,String colOrder) throws DataAccessException {
		System.out.println("Here In DAO 111::::");
		List<ParameterMasterBean> parameterMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ParameterMasterBean.class,"parameterMasterBean");
			criteria.createCriteria("parameterMasterBean.combodetailUom",org.hibernate.sql.JoinFragment.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("parameterId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("parameterName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("uom::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("acceptable_limit::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("permissible_limit::text like '%" + searchString + "%'"))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("parameterName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("parameterName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("uom"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("uom"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("acceptableLimit"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("acceptableLimit"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("permissibleLimit"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("permissibleLimit"));
				}
			}
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			getHibernateTemplate().setCacheQueries(true);
			parameterMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return parameterMasterBeans;
	}

	@Override
	public String getEmployeeDesig(List<String> statusList, long empId) throws DataAccessException {

		String designation = null;
		Session session=null;
		try {
			session=getSession();
			Object obj = session.createCriteria(EmployeeBean.class, "employeeBean")
					.add(Restrictions.eq("employeeBean.employeeId", empId))
					.add(Restrictions.in("misAuditBean.status", statusList))
					.setProjection(Projections.projectionList().add(Projections.property("employeeBean.designationId")))
					.list().get(0);
			designation = obj.toString();

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}finally{
			if(session!=null){
				session.close();
			}
		}
		return designation;
	}

	@Override
	public boolean saveParameterData(ParameterMasterBean parameterMasterLst) throws DataAccessException {
		System.out.println("inside dao=========");
		try {

			if (MisUtility.ifEmpty(parameterMasterLst)) {
				getHibernateTemplate().saveOrUpdate(parameterMasterLst);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParameterMasterBean> findParameterData(ParameterMasterForm parameterMasterForm)
			throws DataAccessException {
		List<ParameterMasterBean> parameterMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ParameterMasterBean.class);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("parameterName",(parameterMasterForm.getParameterName())));
			parameterMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return parameterMasterBeans;
	}

	@Override
	public boolean updateParameterData(ParameterMasterBean parameterMasterLst) throws DataAccessException {
		try {

			if (MisUtility.ifEmpty(parameterMasterLst)) {
				getHibernateTemplate().update(parameterMasterLst);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;

	}
	@SuppressWarnings("unchecked")
	@Override
	public String getParameterAcceptableLimit(int param_id) throws DataAccessException {
		
		List<ParameterMasterBean> parameterMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ParameterMasterBean.class);
			criteria.add(Restrictions.eq("parameterId", param_id));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			parameterMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return parameterMasterBeans.get(0).getAcceptableLimit().toString().trim();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getParameterPermissibleLimit(int param_id) throws DataAccessException {
		List<ParameterMasterBean> parameterMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ParameterMasterBean.class);
			criteria.add(Restrictions.eq("parameterId", param_id));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			parameterMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return parameterMasterBeans.get(0).getPermissibleLimit().toString().trim();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParameterMasterBean> getAllParameters() throws DataAccessException {
		// TODO Auto-generated method stub
		List<ParameterMasterBean> parameterMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ParameterMasterBean.class);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			parameterMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return parameterMasterBeans;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public String getParameterIdName(String  param_id) throws DataAccessException {
		List<ParameterMasterBean> parameterMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ParameterMasterBean.class);
			criteria.add(Restrictions.eq("parameterId", Integer.parseInt(param_id)));
			parameterMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return parameterMasterBeans.get(0).getParameter_id_name().toString().trim();
		
	}
}
