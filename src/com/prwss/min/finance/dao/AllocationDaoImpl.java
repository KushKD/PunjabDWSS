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

import com.prwss.min.finance.bean.AllocationBean;
import com.prwss.min.finance.bean.FinanceHeadStructureBean;
import com.prwss.min.finance.bean.FundRequestDto;
import com.prwss.min.finance.bean.FundRequestMasterBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class AllocationDaoImpl extends HibernateDaoSupport implements AllocationDao {

	private Logger log = Logger.getLogger(AllocationDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FundRequestDto> getFundRequestMasterBean(String requestId) throws DataAccessException {

		List<FundRequestDto> fundRequestDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(FundRequestMasterBean.class);
			criteria.createAlias("comboDetailSchemeType", "comboDetailSchemeType");
			criteria.createAlias("pmsSchemeMaster", "pmsSchemeMaster");
			criteria.createAlias("pmsSchemeDetailsBean", "pmsSchemeDetailsBean");
			criteria.createAlias("locationDivisionSubDivisonDetailsBean", "locationDivisionSubDivisonDetailsBean");
			criteria.createAlias("ddoMasterBean", "ddoMasterBean");
			
			
			if (MisUtility.ifEmpty(requestId)) {
				criteria.add(Restrictions.eq("fundRequestId", Long.parseLong(requestId)));
			}
			
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("locationDivisionSubDivisonDetailsBean.DivisonSubDivisonDetailsName"),"divisionName")
					.add(Projections.property("pmsSchemeMaster.schemeNo"),"schemeNo")
					.add(Projections.property("pmsSchemeDetailsBean.schemeName"),"schemeName")
					.add(Projections.property("comboDetailSchemeType.cmb_name"),"component")
					.add(Projections.property("fundRequestId"),"fundRequestId")
					.add(Projections.property("request_no"),"request_no")
					.add(Projections.property("ddoMasterBean.ddoNumber"),"ddoNumber")
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(FundRequestDto.class));
			
			fundRequestDtos = getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return fundRequestDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceHeadStructureBean> getHeadBean() throws DataAccessException {
		List<FinanceHeadStructureBean> financeHeadStructureBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(FinanceHeadStructureBean.class);
			
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			
			financeHeadStructureBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		
		return financeHeadStructureBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FundRequestDto> getFetchInstallmentAmountDetails(String requestNo) throws DataAccessException {
		
		List<FundRequestDto> fundRequestDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(FundRequestMasterBean.class);
			criteria.createAlias("comboDetailInstallmentId", "comboDetailInstallmentId");
		
			if (MisUtility.ifEmpty(requestNo)) {
				criteria.add(Restrictions.eq("fundRequestId", Long.parseLong(requestNo)));
			}
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("comboDetailInstallmentId.cmb_name"),"installmentName")
					.add(Projections.property("value_of_inst"),"value_of_inst")
					);
			
			criteria.setResultTransformer(Transformers.aliasToBean(FundRequestDto.class));
			
			fundRequestDtos = getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return fundRequestDtos;
	}

	@Override
	public boolean save(AllocationBean allocationBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(allocationBean);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

}
