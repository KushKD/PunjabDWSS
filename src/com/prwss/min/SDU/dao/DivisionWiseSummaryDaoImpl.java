package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class DivisionWiseSummaryDaoImpl extends HibernateDaoSupport implements DivisionWiseSummaryDao {

	private Logger log = Logger.getLogger(DivisionWiseSummaryDaoImpl.class);

	public boolean saveDivisionWiseSummaryDetails(List<DivisionWiseSummaryBean> divisionWiseSummaryBean)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(divisionWiseSummaryBean);
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllLocationIds(String UserID) throws DataAccessException {

		List<String> UserLocationBean = null;
		try {
			System.out.println("DAO Code" + UserID);

			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);
			criteria.add(Restrictions.eq("userId", UserID));

			criteria.setProjection(Projections.projectionList().add(Projections.property("locationId")));

			UserLocationBean = new ArrayList<String>();
			UserLocationBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return UserLocationBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> locationId)
			throws DataAccessException {
		List<LocationDivisionSubDivisonDetailsBean> LocationDetails = new ArrayList<LocationDivisionSubDivisonDetailsBean>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDivisionSubDivisonDetailsBean.class);

			criteria.add(Restrictions.in("DivisonSubDivisonDetailsId", locationId));

			LocationDetails = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return LocationDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getStatusCategory(String financialYear, String division, String category)
			throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			List<DivisionWiseSummaryBean> divisionWiseSummaryBean = new ArrayList<DivisionWiseSummaryBean>();
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class);

			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(financialYear)));
			criteria.add(Restrictions.eq("division", Integer.parseInt(division)));
			criteria.add(Restrictions.eq("category", Integer.parseInt(category)));
			
			divisionWiseSummaryBean = getHibernateTemplate().findByCriteria(criteria);
			
			if(divisionWiseSummaryBean.size()==0){
				return true;
			}else{
				return false;
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
	}

}
