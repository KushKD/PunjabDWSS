/**
 * 
 */
package com.prwss.min.finance.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.DdoMasterBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.dao.DdoMasterDao;
import com.prwss.min.finance.form.DdoMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class DdoMasterBoImpl extends AbstractPaginationMaster<FinanceDto> implements DdoMasterBo<FinanceDto> {

	private Logger log = Logger.getLogger(DdoMasterBoImpl.class);
	
	private DdoMasterDao ddoMasterDao;
	
	public DdoMasterDao getDdoMasterDao() {
		return ddoMasterDao;
	}

	public void setDdoMasterDao(DdoMasterDao ddoMasterDao) {
		this.ddoMasterDao = ddoMasterDao;
	}

	@Override
	public boolean saveDdoMaster(DdoMasterForm ddoMasterForm, MISSessionBean misSessionBean) throws MISException {

		DdoMasterBean ddoMasterBean = null;
		try {
			if (MisUtility.ifEmpty(ddoMasterForm)) {
				ddoMasterBean = populateDdoMasterBean(ddoMasterForm, misSessionBean);
				
				if(MisUtility.ifEmpty(ddoMasterBean)){
					ddoMasterDao.saveDdoMasterBean(ddoMasterBean);
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}

		return true;
	}

	private DdoMasterBean populateDdoMasterBean(DdoMasterForm ddoMasterForm, MISSessionBean misSessionBean) {

		DdoMasterBean ddoMasterBean = null;
		try {
			if (MisUtility.ifEmpty(ddoMasterForm)) {
				ddoMasterBean = new DdoMasterBean();

				if (MisUtility.ifEmpty(ddoMasterForm.getZone())) {
					ddoMasterBean.setZoneId(Integer.parseInt(ddoMasterForm.getZone()));
				}
				if (MisUtility.ifEmpty(ddoMasterForm.getCircle())) {
					ddoMasterBean.setCircleId(Integer.parseInt(ddoMasterForm.getCircle()));
				}
				if (MisUtility.ifEmpty(ddoMasterForm.getDivision())) {
					ddoMasterBean.setDivisionId(Integer.parseInt(ddoMasterForm.getDivision()));
				}
				ddoMasterBean.setDdoName(ddoMasterForm.getDdoName());
				ddoMasterBean.setDdoNumber(ddoMasterForm.getDdoNumber());
				if (MisUtility.ifEmpty(ddoMasterForm.getStatus())) {
					ddoMasterBean.setActiveFlag(Integer.parseInt(ddoMasterForm.getStatus()));
				}
				ddoMasterBean.setCrtByUsr(misSessionBean.getEnteredBy());
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return ddoMasterBean;
	}

}
