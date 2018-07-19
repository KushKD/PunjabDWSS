package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.DivisionVillageMappingBean;
import com.prwss.min.SDU.bean.DivisionVillageMappingDetalBean;
import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.form.VillageDivisionMpgForm;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.util.MISConstants;

public class VillageDivisionMappingDaoImpl extends HibernateDaoSupport  implements VillageDivisionMappingDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionWiseSummaryBean> getCategoryNameAndId(int parseInt) throws DataAccessException {
		List<DivisionWiseSummaryBean> categoryDetail = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class, "divisionWiseSummaryBean");
			criteria.createAlias("divisionWiseSummaryBean.combodetailCatName", "combodetailCatName");
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.financialYear", parseInt));
			
		
			categoryDetail = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(categoryDetail.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return categoryDetail;
	}



@SuppressWarnings("unchecked")
	@Override
	public List<StageComponentBean> getComponentNameAndId(Integer financialYr, Integer division, Integer category,
			Integer stage) throws DataAccessException {

		List<StageComponentBean> stageComponentBean = null;
		//Integer outlinePlanId = getOutlinePlanId(financialYr, division, category);

		try {

			// Criteria criteria=
			// getSession().createCriteria(DivisionWiseSummaryBean.class,
			// "DivisionWiseSummaryBean");

			/*
			 * DetachedCriteria criteria =
			 * DetachedCriteria.forClass(DivisionWiseSummaryBean.class);
			 * criteria.add(Restrictions.eq("activeFlag",
			 * Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			 * criteria.add(Restrictions.eq("stageId", stage));
			 * criteria.add(Restrictions.eq("outlinePlanId", outlinePlanId));
			 */
			DetachedCriteria criteria = DetachedCriteria.forClass(StageComponentBean.class);

			criteria.createAlias("divisionWiseSummaryBean", "divisionWiseSummaryBean");

			criteria.add(Restrictions.eq("divisionWiseSummaryBean.activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.financialYear", financialYr));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.division", division));
			criteria.add(Restrictions.eq("divisionWiseSummaryBean.category", category));
			criteria.add(Restrictions.eq("stageId", stage));

			stageComponentBean = getHibernateTemplate().findByCriteria(criteria);

			System.out.println(stageComponentBean.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return stageComponentBean;
	}



@SuppressWarnings("unchecked")
@Override
public int getVillagesBasedOnStageAndCategory(String StageId, String ComponentId ,String fy, String div , String cat) throws DataAccessException {
	
	List<StageComponentBean> stageComponentBean = null;
	
	try {
		DetachedCriteria criteria = DetachedCriteria.forClass(StageComponentBean.class);
		
		criteria.createAlias("divisionWiseSummaryBean", "divisionWiseSummaryBean");
		
		criteria.add(Restrictions.eq("stageId", Integer.parseInt(StageId)));
		criteria.add(Restrictions.eq("componentId", Integer.parseInt(ComponentId)));
		
		criteria.add(Restrictions.eq("divisionWiseSummaryBean.financialYear", Integer.parseInt(fy))); 
		criteria.add(Restrictions.eq("divisionWiseSummaryBean.division", Integer.parseInt(div))); 
		criteria.add(Restrictions.eq("divisionWiseSummaryBean.category", Integer.parseInt(cat))); 
	
		stageComponentBean = getHibernateTemplate().findByCriteria(criteria);
		
		System.out.println(stageComponentBean.toString());

	} catch (DataAccessException e) {
		e.printStackTrace();
		Log.error(e.getLocalizedMessage(), e);
		throw e;
	}
	return stageComponentBean.get(0).getNumberVillages();
}



@SuppressWarnings("unchecked")
@Override
public List<Integer> getVillageIds(String DivisionId) throws DataAccessException {
	
	
	List<Integer> villageDetails = null;
	
	try {
		
		//Criteria criteria= getSessrrion().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
		criteria.add(Restrictions.eq("divisionalId", Integer.parseInt(DivisionId)));
		 criteria.setProjection(Projections.property("location_id"));
		
	
		villageDetails = getHibernateTemplate().findByCriteria(criteria);
		
		System.out.println(villageDetails.toString());

	} catch (DataAccessException e) {
		throw e;
	}
	return villageDetails;

}



@SuppressWarnings("unchecked")
@Override
public List<LocationDetailsBean> villageDetails(List<Integer> idLocations) throws DataAccessException {
	List<LocationDetailsBean> LocationDetails = new ArrayList<LocationDetailsBean>();
	try {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class);

		criteria.add(Restrictions.in("locationDetailsId", idLocations));
	
		LocationDetails = getHibernateTemplate().findByCriteria(criteria);

	} catch (DataAccessException e) {
		throw e;
	}
	return LocationDetails;
}



@Override
public Boolean getDivVillageId(DivisionVillageMappingBean divisionVillageMapping) throws DataAccessException {
	
	
	try{
		getHibernateTemplate().saveOrUpdate(divisionVillageMapping);
		return true;
	}catch(DataAccessException ex){
		ex.printStackTrace();
		return false;
		
	}
	
	
	
}



@SuppressWarnings("unchecked")
@Override
public List<DivisionVillageMappingBean> getDivisionVillageId(VillageDivisionMpgForm form) throws DataAccessException {
	List<DivisionVillageMappingBean> categoryDetail = null;
	try {
		
		//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DivisionVillageMappingBean.class);
		criteria.add(Restrictions.eq("categoryId", Integer.parseInt(form.getCategory())));
		criteria.add(Restrictions.eq("stageId", Integer.parseInt(form.getStage())));
		criteria.add(Restrictions.eq("componentId", Integer.parseInt(form.getComponent())));
		
	
		categoryDetail = getHibernateTemplate().findByCriteria(criteria);
		
		System.out.println(categoryDetail.toString());

	} catch (DataAccessException e) {
		throw e;
	}
	return categoryDetail;
}



@SuppressWarnings("unchecked")
@Override
public int getDivVillageIdValue(VillageDivisionMpgForm form) throws DataAccessException {
	List<DivisionVillageMappingBean> categoryDetail = null;
	try {
		
		//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DivisionVillageMappingBean.class);
		criteria.add(Restrictions.eq("categoryId", Integer.parseInt(form.getCategory())));
		criteria.add(Restrictions.eq("stageId", Integer.parseInt(form.getStage())));
		criteria.add(Restrictions.eq("componentId", Integer.parseInt(form.getComponent())));
		
	
		categoryDetail = getHibernateTemplate().findByCriteria(criteria);
		
		System.out.println(categoryDetail.toString());

	} catch (DataAccessException e) {
		throw e;
	}
	return categoryDetail.get(0).getDivVillageId();
}



@Override
public Boolean saveDetailDivisonVillage(List<DivisionVillageMappingDetalBean> bean) throws DataAccessException {
	try{
		getHibernateTemplate().saveOrUpdateAll(bean);
		return true;
	}catch(DataAccessException ex){
		ex.printStackTrace();
		return false;
		
	}
}








}
