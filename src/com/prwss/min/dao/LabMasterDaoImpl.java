package com.prwss.min.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LabMasterDaoImpl extends HibernateDaoSupport implements
		LabMasterDao {

	private Logger log = Logger.getLogger(LabMasterDaoImpl.class);

	@Override
	public boolean saveLabData(LabMasterBean labMasterBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(labMasterBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabMasterBean> getLabMasterByPagination(String searchString,int clickedColumn,String colOrder)
			throws DataAccessException {

		List<LabMasterBean> labMaster = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(LabMasterBean.class,"labMasterBean");
			criteria.createAlias("labMasterBean.districtDetailBean", "districtDetailBean");
			criteria.createCriteria("labMasterBean.blockDetailBean",org.hibernate.sql.JoinFragment.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("labId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("labName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("district_id::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("block_id::text like '%" + searchString + "%'"))
						.add(Restrictions.sqlRestriction("village_id::text like '%" + searchString + "%'"))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("labName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("labName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("districtId"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("districtId"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("blockId"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("blockId"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("villageId"));
				} else if (MISConstants.CLICKED_FIVE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("villageId"));
				}
			}
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			getHibernateTemplate().setCacheQueries(true);
			labMaster = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return labMaster;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getvillageLocation(String parentLocationId)
			throws DataAccessException {

		System.out.println("inside dao-------" + parentLocationId);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(LocationDetailsBean.class,
								"locationDetailsBean")
						.createCriteria(
								"locationDetailsBean.locationMasterBean",
								"locationMasterBean")
						.add(Restrictions.eq(
								"locationMasterBean.locationTypeId",
								parentLocationId))
						.add(Restrictions.eq("locationMasterBean.activeField",
								Integer.parseInt(MISConstants.ACTIVE_STATUS)))
						.setProjection(
								Projections
										.projectionList()
										.add(Projections
												.property(
														"locationDetailsBean.locationName")
												.as("locationName"))
										.add(Projections
												.property(
														"locationMasterBean.locationId")
												.as("locationId")));
				criteria.setResultTransformer(
								new AliasToBeanResultTransformer(
										LocationMasterDto.class));
				locationMasterDto=getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabMasterBean> getLabMasterData(LabMasterForm labMasterForm)
			throws DataAccessException {
		
		List<LabMasterBean> labMasterBeans=null;
		
		try {

		DetachedCriteria criteria=DetachedCriteria.forClass(LabMasterBean.class);
		
		if(MisUtility.ifEmpty(labMasterForm.getLabName()))
			criteria.add(Restrictions.eq("labName",labMasterForm.getLabName()));
		if(MisUtility.ifEmpty(labMasterForm.getDistrict()))
			criteria.add(Restrictions.eq("districtId",Integer.parseInt(labMasterForm.getDistrict())));
		if(MisUtility.ifEmpty(labMasterForm.getVillageId()))
			criteria.add(Restrictions.eq("villageId",Integer.parseInt(labMasterForm.getVillageId())));
		if(MisUtility.ifEmpty(labMasterForm.getBlock()))
			criteria.add(Restrictions.eq("blockId",Integer.parseInt(labMasterForm.getBlock())));
		if(MisUtility.ifEmpty(labMasterForm.getStatus()))
			criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(labMasterForm.getStatus())));
		
		labMasterBeans=getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return labMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabMasterBean> getLabMasterByLabId(LabMasterForm labMasterForm)throws DataAccessException {
		List<LabMasterBean> labMasterBeans=null;
		
		try {

		DetachedCriteria criteria=DetachedCriteria.forClass(LabMasterBean.class);
		
			if(MisUtility.ifEmpty(labMasterForm.getLabId())){
				criteria.add(Restrictions.eq("labId",Long.parseLong(labMasterForm.getLabId())));
				criteria.add(Restrictions.eq("activeFlag",Integer.parseInt(MISConstants.ONE)));
			

				labMasterBeans=getHibernateTemplate().findByCriteria(criteria);
			}

	} catch (DataAccessException e) {
		log.debug(e.getLocalizedMessage());
		throw e;
	}
	return labMasterBeans;
	}

	@Override
	public boolean UpdateLabMaster(LabMasterBean labMasterBean) {
		try{
			getHibernateTemplate().update(labMasterBean);
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return true;
	}

}
