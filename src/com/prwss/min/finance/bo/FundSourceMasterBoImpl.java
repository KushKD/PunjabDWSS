/**
 * 
 */
package com.prwss.min.finance.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FundSourceMasterBean;
import com.prwss.min.finance.dao.FundSourceMasterDao;
import com.prwss.min.finance.form.FundSourceMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FundSourceMasterBoImpl extends AbstractPaginationMaster<FinanceDto> implements  FundSourceMasterBo<FinanceDto> {

	private Logger log = Logger.getLogger(FundSourceMasterBoImpl.class);
	
	private FundSourceMasterDao fundSourceMasterDao;
	
	public FundSourceMasterDao getFundSourceMasterDao() {
		return fundSourceMasterDao;
	}
	public void setFundSourceMasterDao(FundSourceMasterDao fundSourceMasterDao) {
		this.fundSourceMasterDao = fundSourceMasterDao;
	}

	@Override
	public boolean saveFundSource(FundSourceMasterForm fundSourceMasterForm, MISSessionBean misSessionBean)
			throws MISException {

		FundSourceMasterBean fundSourceMasterBean = null;
		try {
			if (MisUtility.ifEmpty(fundSourceMasterForm)) {
				fundSourceMasterBean = populateFundSourceMaster(fundSourceMasterForm, misSessionBean);
				
				if(MisUtility.ifEmpty(fundSourceMasterBean)){
					fundSourceMasterDao.saveFundSourceMaster(fundSourceMasterBean);
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	private FundSourceMasterBean populateFundSourceMaster(FundSourceMasterForm fundSourceMasterForm,
			MISSessionBean misSessionBean) {

		FundSourceMasterBean fundSourceMasterBean=null;
		try {
			if (MisUtility.ifEmpty(fundSourceMasterForm)) {
				fundSourceMasterBean = new FundSourceMasterBean();

				if (MisUtility.ifEmpty(fundSourceMasterForm.getProgram())) {
					fundSourceMasterBean.setProgramId(Long.parseLong(fundSourceMasterForm.getProgram()));
				}if (MisUtility.ifEmpty(fundSourceMasterForm.getScheme())) {
					fundSourceMasterBean.setSchemeId(Long.parseLong(fundSourceMasterForm.getScheme()));
				}
				fundSourceMasterBean.setFundSourceMst(fundSourceMasterForm.getFundSrcMaster());
				fundSourceMasterBean.setCenterShare(fundSourceMasterForm.getCenterShare());
				fundSourceMasterBean.setStateShare(fundSourceMasterForm.getStateShare());
				
				fundSourceMasterBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				fundSourceMasterBean.setCrtByUsr(misSessionBean.getEnteredBy());
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return fundSourceMasterBean;
	}

}
