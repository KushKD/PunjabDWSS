/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.MotivatorAcademicMapping;
import com.prwss.min.sanitation.bean.MotivatorBean;
import com.prwss.min.sanitation.form.MotivatorEntryForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MotivatorDaoImpl extends HibernateDaoSupport implements MotivatorDao{

	private Logger log = Logger.getLogger(MotivatorDaoImpl.class);

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<MotivatorBean> validateMotivatorDetails(MotivatorEntryForm motivatorEntryForm)
			throws DataAccessException {
		List<MotivatorBean> beneficiaryBean = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MotivatorBean.class);

			if (MisUtility.ifEmpty(motivatorEntryForm)) {
				criteria.add(Restrictions.eq("aadhaarNumber", Long.parseLong(motivatorEntryForm.getAdharNumber().trim())));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			}
			beneficiaryBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return beneficiaryBean;
	}

	@Override
	public boolean saveMotivatorDetails(MotivatorBean motivatorEntryBean) throws DataAccessException {
		
		try{
			getHibernateTemplate().save(motivatorEntryBean);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@Override
	public boolean saveMotivatorAcademicDetails(List<MotivatorAcademicMapping> motivatorAcademicMappings)
			throws DataAccessException {
		try{
			getHibernateTemplate().saveOrUpdateAll(motivatorAcademicMappings);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return true;
	}

}
