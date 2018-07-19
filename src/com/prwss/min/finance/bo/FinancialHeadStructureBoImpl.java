/**
 * 
 */
package com.prwss.min.finance.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FinanceHeadStructureBean;
import com.prwss.min.finance.dao.FinancialHeadStructureDao;
import com.prwss.min.finance.form.FinanceHeadsStructureForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FinancialHeadStructureBoImpl extends AbstractPaginationMaster<FinanceDto>
		implements FinancialHeadStructureBo<FinanceDto> {

	private Logger log = Logger.getLogger(FinancialHeadStructureBoImpl.class);
	private FinancialHeadStructureDao financialHeadStructureDao;

	public FinancialHeadStructureDao getFinancialHeadStructureDao() {
		return financialHeadStructureDao;
	}

	public void setFinancialHeadStructureDao(FinancialHeadStructureDao financialHeadStructureDao) {
		this.financialHeadStructureDao = financialHeadStructureDao;
	}

	@Override
	public boolean save(FinanceHeadsStructureForm financeHeadsStructureForm, MISSessionBean misSessionBean)
			throws MISException {

		FinanceHeadStructureBean financeHeadStructureBean = null;
		try {
			financeHeadStructureBean = populateFinanceHeadStructureBean(financeHeadsStructureForm, misSessionBean);
			if (MisUtility.ifEmpty(financeHeadStructureBean)) {
				financialHeadStructureDao.save(financeHeadStructureBean);
			}

		} catch (DataAccessException e) {
			throw new MISException(e);
		}

		return true;
	}

	@Override
	public boolean update(FinanceHeadsStructureForm financeHeadsStructureForm, MISSessionBean misSessionBean)
			throws MISException {

		FinanceHeadStructureBean financeHeadStructureBean = null;
		List<FinanceHeadStructureBean> financeHeadStructureBeans = null;
		try {
			financeHeadStructureBeans = financialHeadStructureDao.getHeadStructureBean(financeHeadsStructureForm);

			if (!MisUtility.ifEmpty(financeHeadStructureBeans)) {
				financeHeadStructureBean = populateFinanceHeadStructureBean(financeHeadsStructureForm, misSessionBean);
				if (MisUtility.ifEmpty(financeHeadStructureBean)) {
					financialHeadStructureDao.update(financeHeadStructureBean);
				}
			}

		} catch (DataAccessException e) {
			throw new MISException(e);
		}

		return true;
	}

	private FinanceHeadStructureBean populateFinanceHeadStructureBean(
			FinanceHeadsStructureForm financeHeadsStructureForm, MISSessionBean misSessionBean) {

		FinanceHeadStructureBean financeHeadStructureBean = new FinanceHeadStructureBean();
		try {
			if (MisUtility.ifEmpty(financeHeadsStructureForm.getFinancialYear()))
				financeHeadStructureBean
						.setFinancialYear(Integer.parseInt(financeHeadsStructureForm.getFinancialYear()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getDimandNo()))
				financeHeadStructureBean.setDemandNumber(Integer.parseInt(financeHeadsStructureForm.getDimandNo()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getMajorHead()))
				financeHeadStructureBean.setMajorHead(Integer.parseInt(financeHeadsStructureForm.getMajorHead()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getSubMajorHead()))
				financeHeadStructureBean.setSubMajorHead(Integer.parseInt(financeHeadsStructureForm.getSubMajorHead()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getMinorHead()))
				financeHeadStructureBean.setMinorHead(Integer.parseInt(financeHeadsStructureForm.getMinorHead()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getSubHead()))
				financeHeadStructureBean.setSubHead(Integer.parseInt(financeHeadsStructureForm.getSubHead()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getDetailedHead()))
				financeHeadStructureBean.setDetailedHead(Integer.parseInt(financeHeadsStructureForm.getDetailedHead()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getSOE()))
				financeHeadStructureBean.setSoeNumber(Integer.parseInt(financeHeadsStructureForm.getSOE()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getPlanNonPlan()))
				financeHeadStructureBean.setPlanNonplan(Integer.parseInt(financeHeadsStructureForm.getPlanNonPlan()));

			if (MisUtility.ifEmpty(financeHeadsStructureForm.getVotedCharged()))
				financeHeadStructureBean.setVotedCharged(Integer.parseInt(financeHeadsStructureForm.getVotedCharged()));

			
			if (MisUtility.ifEmpty(financeHeadsStructureForm.getHeadStructureId())){
				financeHeadStructureBean.setHeadStructureId(Long.parseLong(financeHeadsStructureForm.getHeadStructureId()));
			}
			financeHeadStructureBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			financeHeadStructureBean.setCrtByUsr(misSessionBean.getEnteredBy());
			financeHeadStructureBean.setHeadStructureName(financeHeadsStructureForm.getDescription());

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return financeHeadStructureBean;
	}

}
