/**
 * 
 */
package com.prwss.min.finance.bo;

import org.apache.log4j.Logger;

import com.prwss.min.finance.bean.AllocationBean;
import com.prwss.min.finance.dao.AllocationDao;
import com.prwss.min.finance.form.AllocationForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class AllocationBoImpl implements AllocationBo {

	private Logger log = Logger.getLogger(AllocationBoImpl.class);

	private AllocationDao allocationDao;
	
	
	public AllocationDao getAllocationDao() {
		return allocationDao;
	}

	public void setAllocationDao(AllocationDao allocationDao) {
		this.allocationDao = allocationDao;
	}

	@Override
	public boolean save(AllocationForm allocationForm, MISSessionBean misSessionBean) throws MISException {
		AllocationBean allocationBean = null;
		try {
			allocationBean = populateAllocationBean(allocationForm,misSessionBean);
			
			allocationDao.save(allocationBean);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	private AllocationBean populateAllocationBean(AllocationForm allocationForm, MISSessionBean misSessionBean) {
		AllocationBean allocationBean = null;
		try {
			if (MisUtility.ifEmpty(allocationForm)) {
				allocationBean = new AllocationBean();

				if (MisUtility.ifEmpty(allocationForm.getRequestNo())) {
					allocationBean.setRequestNo(Long.parseLong(allocationForm.getRequestNo()));
				}
				if (MisUtility.ifEmpty(allocationForm.getHead())) {
					allocationBean.setHead(Integer.parseInt(allocationForm.getHead()));
				}
				allocationBean.setAllocationNo(allocationForm.getAllocationNumber());
				if (MisUtility.ifEmpty(allocationForm.getDateAllocation())) {
					allocationBean
							.setAllocationDate(MisUtility.convertStringSqlDate(allocationForm.getDateAllocation()));
				}
				
				if (MisUtility.ifEmpty(allocationForm.getAmountReleased())) {
					allocationBean.setAmntReleased(Double.parseDouble(allocationForm.getAmountReleased()));
				}if (MisUtility.ifEmpty(allocationForm.getRevoked())) {
					allocationBean.setAmntRevoked(Double.parseDouble(allocationForm.getRevoked()));
				}if (MisUtility.ifEmpty(allocationForm.getNetAmount())) {
					allocationBean.setNetAmount(Double.parseDouble(allocationForm.getNetAmount()));
				}
				allocationBean.setCrtByUsr(misSessionBean.getEnteredBy());
				allocationBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return allocationBean;
	}

}
