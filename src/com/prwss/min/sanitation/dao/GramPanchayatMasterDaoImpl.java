package com.prwss.min.sanitation.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.min.sanitation.bean.GramPanchayatDto;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class GramPanchayatMasterDaoImpl extends HibernateDaoSupport implements GramPanchayatMasterDao {

	@Override
	public int savegramPanchayatMaster(GramPanchayatMasterBean gramPanchayatMasterBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().save(gramPanchayatMasterBean);
		}
		catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return gramPanchayatMasterBean.getGramPanchayatId();
	}
	
	@Override
	public boolean savegramPanchayatDetail(List<GramPanchayatDetailBean> gramPanchayatDetailBean) throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(gramPanchayatDetailBean);
		}
		catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatMasterBean> getgramPanchayatMaster(GramPanchayatMasterBean gramPanchayatMasterBean)
			throws DataAccessException {
		List<GramPanchayatMasterBean> gramPanchayatMasterBean2=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(GramPanchayatMasterBean.class);
			criteria.add(Restrictions.eq("nameofGramPanchayat", gramPanchayatMasterBean.getNameofGramPanchayat()));
			
			gramPanchayatMasterBean2=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return gramPanchayatMasterBean2;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatDto> getLocationMasterByPagination() throws DataAccessException {
		// TODO Auto-generated method stub
		List<GramPanchayatDto> gramPanchayatDto=null;
		 try{
			 
			 DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatDetailBean.class, "gramPanchayatDetailBean");
				criteria.createAlias("gramPanchayatDetailBean.gramPanchayatMasterBean", "gramPanchayatMasterBean");
				criteria.createAlias("gramPanchayatDetailBean.locationDetailBean", "locationDetailBean");
				criteria.createAlias("gramPanchayatDetailBean.locationDetailBeandistrict", "locationDetailBeandistrict");
				criteria.createAlias("gramPanchayatDetailBean.locationDetailBeanBlock", "locationDetailBeanBlock");
				
				criteria.add(Restrictions.eq("gramPanchayatMasterBean.status", Integer.parseInt(MISConstants.ONE)));
				criteria.add(Restrictions.eq("gramPanchayatDetailBean.status", Integer.parseInt(MISConstants.ONE)));
				//criteria.add(Restrictions.eq("resultEntryBean.lyingWithUser", empId));
				criteria.setProjection(Projections.projectionList()

				//		.add(Projections.property("gramPanchayatMasterBean.gramPanchayatId"), "gramPanchayatId")
						.add(Projections.property("gramPanchayatMasterBean.nameofGramPanchayat"), "nameofGramPanchayat")
						.add(Projections.property("locationDetailBeandistrict.locationName"), "district")
						.add(Projections.property("locationDetailBeanBlock.locationName"), "block")
						.add(Projections.property("locationDetailBean.locationName"), "village"));

				criteria.setResultTransformer(Transformers.aliasToBean(GramPanchayatDto.class));
				gramPanchayatDto=getHibernateTemplate().findByCriteria(criteria);

			
			
	}
		catch(DataAccessException e){
			throw e;
		}
		return gramPanchayatDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getStatusCategory(String status, String village)
			throws DataAccessException {
		try {
			List<GramPanchayatDetailBean> divisionWiseSummaryBean = new ArrayList<GramPanchayatDetailBean>();
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatDetailBean.class);

			criteria.add(Restrictions.eq("village", Integer.parseInt(village)));
			criteria.add(Restrictions.eq("status", Integer.parseInt(status)));
			
			divisionWiseSummaryBean = getHibernateTemplate().findByCriteria(criteria);
			
			if(divisionWiseSummaryBean.size()==0){
				return true;
			}else{
				return false;
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	

	
	
}