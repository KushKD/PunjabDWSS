package com.prwss.min.SDU.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.bean.StageComponetDto;
import com.prwss.min.SDU.bean.StageDetailBean;
import com.prwss.min.SDU.form.StageComponentMpgForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class StageComponentMappingDaoImpl extends HibernateDaoSupport implements StageComponentMappingDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllLocationIds(String UserID) throws DataAccessException {
	    
		
		List<String> UserLocationBean = null;
		try {
			System.out.println("DAO Code" + UserID);

			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);

			criteria.add(Restrictions.eq("userId", UserID));
			
			criteria.setProjection(Projections.projectionList().add(
				     Projections.property("locationId"))); 
				   

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
	public List<StageComponetDto> getVillagesAndCategory(StageComponentMpgForm form) throws DataAccessException {
		List<StageComponetDto> villageandCategory = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class , "DivisionWiseSummaryBean");
			criteria.createAlias("DivisionWiseSummaryBean.combodetailCatName", "categoryName");

			criteria.add(Restrictions.eq("division", Integer.parseInt(form.getDivision())));
			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(form.getFinancialYear()))); 
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("categoryName.cmb_name"),"categoryName")
					.add(Projections.property("DivisionWiseSummaryBean.noOfVillages"),"villages"));
			criteria.setResultTransformer(Transformers.aliasToBean(StageComponetDto.class));
		
			villageandCategory = getHibernateTemplate().findByCriteria(criteria);
			
			

		} catch (DataAccessException e) {
			throw e;
		}
		return villageandCategory;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StageDetailBean> getStageNameAndId() throws DataAccessException {
		List<StageDetailBean> stageDetail = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(StageDetailBean.class );
			
		
			stageDetail = getHibernateTemplate().findByCriteria(criteria);
			
			

		} catch (DataAccessException e) {
			throw e;
		}
		return stageDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionWiseSummaryBean> getComponets(String year_id, String name_id) throws DataAccessException {
		List<DivisionWiseSummaryBean> componentDetails = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class );
			criteria.add(Restrictions.eq("division", Integer.parseInt(name_id)));
			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(year_id)));
		
			componentDetails = getHibernateTemplate().findByCriteria(criteria);
			
			

		} catch (DataAccessException e) {
			throw e;
		}
		return componentDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getOutlineIds(String year, String district, int category)
			throws DataAccessException {
		List<DivisionWiseSummaryBean> componentDetails = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class );
			criteria.add(Restrictions.eq("division", Integer.parseInt(district)));
			criteria.add(Restrictions.eq("financialYear", Integer.parseInt(year)));
			criteria.add(Restrictions.eq("category", category));
		
			componentDetails = getHibernateTemplate().findByCriteria(criteria);
			
			

		} catch (DataAccessException e) {
			throw e;
		}
		return componentDetails.get(0).getOutlinePlanId();
	}

	@Override
	public boolean saveData(List<StageComponentBean> stageComponentMappingBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().saveOrUpdateAll(stageComponentMappingBean);
		}catch(DataAccessException ex){
			ex.printStackTrace();
			
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getVillages(int categoryID) throws DataAccessException {
		List<DivisionWiseSummaryBean> villagesDetails = null;
		try {
			
			//Criteria criteria= getSession().createCriteria(DivisionWiseSummaryBean.class, "DivisionWiseSummaryBean");
			
			DetachedCriteria criteria = DetachedCriteria.forClass(DivisionWiseSummaryBean.class );
			criteria.add(Restrictions.eq("category", categoryID));
		
			villagesDetails = getHibernateTemplate().findByCriteria(criteria);
			
			

		} catch (DataAccessException e) {
			throw e;
		}
		return villagesDetails.get(0).getNoOfVillages();
	}

}
