/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.finance.bean.ContractManagementMasterBean;
import com.prwss.min.finance.bean.FundRequestDocBean;
import com.prwss.min.finance.bean.FundRequestDto;
import com.prwss.min.finance.bean.FundRequestMasterBean;
import com.prwss.min.finance.bean.PMSSchemeDetailUpgradeBean;

/**
 * @author BH390738
 *
 */
public class FundRequestDaoImpl extends HibernateDaoSupport implements FundRequestDao {

	private Logger log = Logger.getLogger(FundRequestDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FundRequestDto> getSchemeDetailsUpgrade(String schemeId) throws DataAccessException {

		List<FundRequestDto> FundRequestDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PMSSchemeDetailUpgradeBean.class);
			criteria.createAlias("pmsSchemeMaster", "pmsSchemeMaster");
			criteria.add(Restrictions.eq("scheme_id", Integer.parseInt(schemeId)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("addmin_app_no"),"addmin_app_no")
					.add(Projections.property("addmin_app_date"),"addmin_app_date")
					.add(Projections.property("admin_app_amount"),"admin_app_amount")
					.add(Projections.property("pmsSchemeMaster.schemeNo"),"schemeNo")
					.add(Projections.property("tech_app_no"),"tech_app_no")
					.add(Projections.property("tech_app_date"),"tech_app_date")
					
					.add(Projections.property("tech_app_amount"),"tech_app_amount")
					.add(Projections.property("wb_app_no"),"wb_app_no")
					.add(Projections.property("wb_app_date"),"wb_app_date")
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(FundRequestDto.class));
			FundRequestDto = getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}

		return FundRequestDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContractManagementMasterBean> getContractAwardDetails(String schemeId) throws DataAccessException {
		
		List<ContractManagementMasterBean> contractManagementMasterBeans=null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(ContractManagementMasterBean.class);
			criteria.add(Restrictions.eq("scheme_id", Integer.parseInt(schemeId)));
			contractManagementMasterBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return contractManagementMasterBeans;
	}

	@Override
	public FundRequestMasterBean save(FundRequestMasterBean fundRequestMasterBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(fundRequestMasterBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return fundRequestMasterBean;
	}

	@Override
	public boolean saveFundRequestDao(FundRequestDocBean fundRequestDocBean) throws DataAccessException {
		try{
			getHibernateTemplate().save(fundRequestDocBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

}
