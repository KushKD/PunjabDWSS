/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FinanceHeadBean;
import com.prwss.min.finance.dao.FinancialHeadsDao;
import com.prwss.min.finance.form.FinanceHeadsForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FinancialHeadsBoImpl extends AbstractPaginationMaster<FinanceDto> implements FinancialHeadsBo<FinanceDto> {

	private Logger log = Logger.getLogger(FinancialHeadsBoImpl.class);

	private FinancialHeadsDao financialHeadsDao;

	public FinancialHeadsDao getFinancialHeadsDao() {
		return financialHeadsDao;
	}

	public void setFinancialHeadsDao(FinancialHeadsDao financialHeadsDao) {
		this.financialHeadsDao = financialHeadsDao;
	}

	@Override
	public boolean save(FinanceHeadsForm financeHeadsForm, MISSessionBean misSessionBean) throws MISException {
		
		FinanceHeadBean financeHeadBean = null;
		try {
			if (MisUtility.ifEmpty(financeHeadsForm)) {

				financeHeadBean = populateFinanceHeadBean(financeHeadsForm, misSessionBean);

				if (MisUtility.ifEmpty(financeHeadBean)) {
					financialHeadsDao.save(financeHeadBean);
				}
			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	@Override
	public boolean update(FinanceHeadsForm financeHeadsForm, MISSessionBean misSessionBean) throws MISException {
		
		FinanceHeadBean financeHeadBean = null;
		List<FinanceHeadBean> financeHeadBeans = null;
		try {
			if (MisUtility.ifEmpty(financeHeadsForm)) {
				financeHeadBeans = financialHeadsDao.getFinanceHead(financeHeadsForm);
				if (!MisUtility.ifEmpty(financeHeadBeans)) {
					financeHeadBean = populateFinanceHeadBean(financeHeadsForm, misSessionBean);

					if (MisUtility.ifEmpty(financeHeadBean)) {
						financialHeadsDao.update(financeHeadBean);
					}
				}
			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	private FinanceHeadBean populateFinanceHeadBean(FinanceHeadsForm financeHeadsForm, MISSessionBean misSessionBean) {

		FinanceHeadBean financeHeadBean = null;
		try {
			financeHeadBean = new FinanceHeadBean();

			if (MisUtility.ifEmpty(financeHeadsForm.getHeadType()))
				financeHeadBean.setHead_type(Integer.parseInt(financeHeadsForm.getHeadType()));

			if (MisUtility.ifEmpty(financeHeadsForm.getParent()))
				financeHeadBean.setParent_head_id(Integer.parseInt(financeHeadsForm.getParent()));

			if (MisUtility.ifEmpty(financeHeadsForm.getFinanceHeadId())) {
				financeHeadBean.setHeadId(Long.parseLong(financeHeadsForm.getFinanceHeadId()));
			}

			financeHeadBean.setHeadName(financeHeadsForm.getDescription());

			financeHeadBean.setHeadNumber(financeHeadsForm.getNumber());
			financeHeadBean.setCrtByUsr(misSessionBean.getEnteredBy());
			financeHeadBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return financeHeadBean;
	}

}
