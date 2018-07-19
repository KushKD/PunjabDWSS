package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.sanitation.bean.ProgressStageMappingBean;
import com.prwss.min.sanitation.bean.ProgressWorkBean;

public class ProgressofWorkDaoImpl extends HibernateDaoSupport implements ProgressofWorkDao {
	private Logger log = Logger.getLogger(ProgressofWorkDaoImpl.class);

/*---------------------------------------------------------savegramPanchayatMaster--------------------------------------------------------------------*/	
	
	@Override
	public int saveProgressWorkMaster(ProgressWorkBean progressWorkBean)
			throws DataAccessException {
		
		try{
			getHibernateTemplate().save(progressWorkBean);
		}
		catch(DataAccessException e){
			e.printStackTrace();
			log.debug(e.getMessage());
			throw e;
		}
		return progressWorkBean.getProgressWorkId();
	}

/*---------------------------------------------------------savegramPanchayatDetail--------------------------------------------------------------------*/	
	
	@Override
	public boolean saveprogressStageMapping(List<ProgressStageMappingBean> progressStageMappingBeans) throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(progressStageMappingBeans);
		}
		catch(DataAccessException e){
			log.debug(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return true;
	}

/*---------------------------------------------------------getgramPanchayatMaster--------------------------------------------------------------------*/	
	
/*	@SuppressWarnings("unchecked")
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
	}*/
	
/*---------------------------------------------------------getLocationMasterByPagination--------------------------------------------------------------------*/	
	
/*	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatDto> getLocationMasterByPagination() throws DataAccessException {
		// TODO Auto-generated method stub
		List<GramPanchayatDto> gramPanchayatDto=null;
		 try{
			 
			 DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatMasterBean.class, "gramPanchayatMasterBean");
				criteria.createAlias("gramPanchayatMasterBean.locDetailBeans", "gramPanchayatDetailBean");
				
				criteria.add(Restrictions.eq("gramPanchayatMasterBean.status", Integer.parseInt(MISConstants.ONE)));
				criteria.add(Restrictions.eq("gramPanchayatDetailBean.status", Integer.parseInt(MISConstants.ONE)));
				//criteria.add(Restrictions.eq("resultEntryBean.lyingWithUser", empId));
				criteria.setProjection(Projections.projectionList()

				//		.add(Projections.property("gramPanchayatMasterBean.gramPanchayatId"), "gramPanchayatId")
						.add(Projections.property("gramPanchayatMasterBean.nameofGramPanchayat"), "nameofGramPanchayat")
						.add(Projections.property("gramPanchayatDetailBean.district"), "district")
						.add(Projections.property("gramPanchayatDetailBean.block"), "block")
						.add(Projections.property("gramPanchayatDetailBean.village"), "village"));

				criteria.setResultTransformer(Transformers.aliasToBean(GramPanchayatDto.class));
				gramPanchayatDto=getHibernateTemplate().findByCriteria(criteria);

			
			
	}
		catch(DataAccessException e){
			throw e;
		}
		return gramPanchayatDto;
	}*/
	
	
}