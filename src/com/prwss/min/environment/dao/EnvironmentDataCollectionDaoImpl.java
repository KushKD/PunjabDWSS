package com.prwss.min.environment.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.dao.LabMasterDaoImpl;
import com.prwss.min.environment.bean.EnvironmentEDSMaster;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentDataCollectionDaoImpl extends HibernateDaoSupport implements EnvironmentDataCollectionDao {

	private Logger log = Logger.getLogger(EnvironmentDataCollectionDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getGramPanchayatId(String villageID)
			throws DataAccessException {
		
		List<Integer> gramPanchayatId = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatDetailBean.class);

			criteria.add(Restrictions.eq("village", Integer.parseInt(villageID)));
			//criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			 criteria.setProjection(Projections.property("gramPanchayatId"));
			gramPanchayatId = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(gramPanchayatId.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			//log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return gramPanchayatId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatMasterBean> getGramPanchayatDetails(List<Integer> gramPanchayatId) throws DataAccessException {
		List<GramPanchayatMasterBean> gramPanchayatDetails = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatMasterBean.class);

			criteria.add(Restrictions.in("gramPanchayatId", gramPanchayatId));
			//criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

			gramPanchayatDetails = getHibernateTemplate().findByCriteria(criteria);

			//System.out.println(gramPanchayatId.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			//log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return gramPanchayatDetails;
	}

	@Override
	public Integer saveMasterData(EnvironmentEDSMaster environmentEDSMasterBean)
			throws MISException, DataAccessException {
		try {
			getHibernateTemplate().save(environmentEDSMasterBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return environmentEDSMasterBean.getEds_id();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnvironmentEDSMaster> getLabMasterByPagination(
			String searchParameter, int clickedColumn, String clickedColumnDir)
			throws DataAccessException {
		List<EnvironmentEDSMaster> edsMaster = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(EnvironmentEDSMaster.class,"edsMasterBean");
			criteria.createAlias("edsMasterBean.districtDetailBean", "districtDetailBean");
			 criteria.createAlias("edsMasterBean.comboBeanDetailsSchemeype", "comboBeanDetailsSchemeype");
			 criteria.createAlias("edsMasterBean.schemeDetailBean", "schemeDetailBean");
			 
			criteria.createCriteria("edsMasterBean.blockDetailBean",org.hibernate.sql.JoinFragment.LEFT_OUTER_JOIN);
			criteria.createCriteria("edsMasterBean.villageDetailBean",org.hibernate.sql.JoinFragment.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("eds_id"));
			}
			if (MisUtility.ifEmpty(searchParameter)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("scheme_id", searchParameter, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("district_id::text like '%" + searchParameter + "%'"))
						.add(Restrictions.sqlRestriction("block_id::text like '%" + searchParameter + "%'"))
						.add(Restrictions.sqlRestriction("village_id::text like '%" + searchParameter + "%'")) 
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(clickedColumnDir)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("eds_id"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("eds_id"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("districtId"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("districtId"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("blockId"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("blockId"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("villageId"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& clickedColumnDir.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("villageId"));
				}
			}
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			getHibernateTemplate().setCacheQueries(true);
			edsMaster = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return edsMaster;
	}

	@Override
	public Integer updateMasterData(
			EnvironmentEDSMaster environmentEDSMasterBean)
			throws DataAccessException, MISException {
		try{
			getHibernateTemplate().saveOrUpdate(environmentEDSMasterBean);
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return environmentEDSMasterBean.getEds_id();
	}

}
