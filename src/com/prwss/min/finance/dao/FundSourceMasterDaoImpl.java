/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FundSourceMasterBean;
import com.prwss.min.finance.bean.PMSSchemeDetailUpgradeBean;
import com.prwss.mis.admin.ProgramMasterBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FundSourceMasterDaoImpl extends HibernateDaoSupport implements FundSourceMasterDao {

	private Logger log = Logger.getLogger(FundSourceMasterDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramMasterBean> getProgramBean() throws DataAccessException {
		
		List<ProgramMasterBean> programMasterBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(ProgramMasterBean.class);
			criteria.createAlias("programDetailBean", "programDetailBean");
			criteria.addOrder(Order.asc("programDetailBean.progName"));
			programMasterBeans=getHibernateTemplate().findByCriteria(criteria);
			
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return programMasterBeans;
	}

	@Override
	public boolean saveFundSourceMaster(FundSourceMasterBean fundSourceMasterBeans) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(fundSourceMasterBeans);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceDto> populateFundSourceMaster(String searchString, int clickedColumn, String colOrder)
			throws DataAccessException {
		List<FinanceDto> financeHeadBeans = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(FundSourceMasterBean.class);
			
			
			criteria.add(Restrictions.eq("activeFlag",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
		
			if(MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))){
				criteria.addOrder(Order.desc("fundSourceId"));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("fundSourceMst", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("stateShare", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("centerShare", searchString, MatchMode.ANYWHERE))
						);

			}
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("fundSourceMst"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("fundSourceMst"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("stateShare"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("stateShare"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("centerShare"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("centerShare"));
				} 
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("schemeId"), "scheme")
					.add(Projections.property("fundSourceMst"), "fundSourceMst")
					.add(Projections.property("stateShare"), "stateShare")
					.add(Projections.property("centerShare"), "centerShare"));
					
			criteria.setResultTransformer(Transformers.aliasToBean(FinanceDto.class));
			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			financeHeadBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw e;
		}
		return financeHeadBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getSchemeByProgram(String programId)
			throws DataAccessException {
		
		List<Integer>  schemeUpgradeDetailBeans;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(PMSSchemeDetailUpgradeBean.class);
			
			criteria.add(Restrictions.eq("prog_id", Integer.parseInt(programId)));
			criteria.setProjection(Projections.property("scheme_id"));
			schemeUpgradeDetailBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw e;
		}
		return schemeUpgradeDetailBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PMSSchemeDetailsBean> getSchemeName(List<Integer> schemeId)
			throws DataAccessException {
			
			List<PMSSchemeDetailsBean> pMSSchemeDetailsBean=null;
			try {

				DetachedCriteria criteria = DetachedCriteria.forClass(PMSSchemeDetailsBean.class);
				criteria.add(Restrictions.in("scheme_id",schemeId));
				criteria.addOrder(Order.asc("schemeName"));

				pMSSchemeDetailsBean = getHibernateTemplate().findByCriteria(criteria);
				
			}catch(DataAccessException e){
				e.printStackTrace();
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}	
			return pMSSchemeDetailsBean;
	}
}
