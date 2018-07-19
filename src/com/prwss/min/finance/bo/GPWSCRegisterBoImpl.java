/**
 * 
 */
package com.prwss.min.finance.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.GPWSCRegisterBean;
import com.prwss.min.finance.dao.GPWSCRegisterDao;
import com.prwss.min.finance.form.GPWSCRegisterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.document.dao.DocumentNumberBean;
import com.prwss.mis.common.document.dao.DocumentNumberDAO;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class GPWSCRegisterBoImpl extends AbstractPaginationMaster<FinanceDto> implements GPWSCRegisterBo<FinanceDto> {

	private Logger log = Logger.getLogger(GPWSCRegisterBoImpl.class);
	private GPWSCRegisterDao gPWSCRegisterDao;
	private DocumentNumberDAO documentNumberDao;

	
	
	public DocumentNumberDAO getDocumentNumberDao() {
		return documentNumberDao;
	}

	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public GPWSCRegisterDao getgPWSCRegisterDao() {
		return gPWSCRegisterDao;
	}

	public void setgPWSCRegisterDao(GPWSCRegisterDao gPWSCRegisterDao) {
		this.gPWSCRegisterDao = gPWSCRegisterDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean save(GPWSCRegisterForm gpwscRegisterForm, MISSessionBean misSessionBean) throws MISException {

		GPWSCRegisterBean gpwscRegisterBean = null;
		boolean status=false;
		try {
			if (MisUtility.ifEmpty(gpwscRegisterForm)) {
				
				DocumentNumberBean documentNumebrBean = new DocumentNumberBean();
				documentNumebrBean.setDocumentType("GPWSC-REGISTER");
				DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
				
				if (!(MisUtility.ifEmpty(documentNumberBean))) {
					throw new MISException();
				}
				gpwscRegisterBean=populateGPWSCRegisterBean(gpwscRegisterForm, misSessionBean);
				
				if(MisUtility.ifEmpty(gpwscRegisterBean)){
					status=gPWSCRegisterDao.save(gpwscRegisterBean);
				}
				if(status){
					status=documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
				}
			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}

	private GPWSCRegisterBean populateGPWSCRegisterBean(GPWSCRegisterForm gpwscRegisterForm,
			MISSessionBean misSessionBean) {

		GPWSCRegisterBean gpwscRegisterBean = null;
		try {
			if (MisUtility.ifEmpty(gpwscRegisterForm)) {
				gpwscRegisterBean = new GPWSCRegisterBean();

				if (MisUtility.ifEmpty(gpwscRegisterForm.getDivision())) {
					gpwscRegisterBean.setDivsionId(Integer.parseInt(gpwscRegisterForm.getDivision()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getSubDivision())) {
					gpwscRegisterBean.setSubDivId(Integer.parseInt(gpwscRegisterForm.getSubDivision()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getVillage())) {
					gpwscRegisterBean.setVillageId(Integer.parseInt(gpwscRegisterForm.getVillage()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getgPWSC())) {
					gpwscRegisterBean.setGpwscId(Integer.parseInt(gpwscRegisterForm.getgPWSC()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getSchemeName())) {
					gpwscRegisterBean.setSchemeId(Integer.parseInt(gpwscRegisterForm.getSchemeName()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getTransactionDate())) {
					gpwscRegisterBean.setTransactionDate(MisUtility.convertStringSqlDate(gpwscRegisterForm.getTransactionDate()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getTransactionType())) {
					gpwscRegisterBean.setTransactionType(Integer.parseInt(gpwscRegisterForm.getTransactionType()));
				}
				gpwscRegisterBean.setTransactionNo(gpwscRegisterForm.getTransactionNo());

				if (MisUtility.ifEmpty(gpwscRegisterForm.getBank())) {
					gpwscRegisterBean.setBank_id(Integer.parseInt(gpwscRegisterForm.getBank()));
				}
				gpwscRegisterBean.setPayeeName(gpwscRegisterForm.getPayeeName());
				gpwscRegisterBean.setBillNo(gpwscRegisterForm.getBillNo());

				if (MisUtility.ifEmpty(gpwscRegisterForm.getPaymentType())) {
					gpwscRegisterBean.setPaymentType(Integer.parseInt(gpwscRegisterForm.getPaymentType()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getAmount())) {
					gpwscRegisterBean.setPaymentAmt(Double.parseDouble(gpwscRegisterForm.getAmount()));
				}
				gpwscRegisterBean.setPaymentRemark(gpwscRegisterForm.getPaymentRemark());
				
				if (MisUtility.ifEmpty(gpwscRegisterForm.getReceiptType())) {
					gpwscRegisterBean.setRecieptType(Integer.parseInt(gpwscRegisterForm.getReceiptType()));
				}
				if (MisUtility.ifEmpty(gpwscRegisterForm.getReceiptAmount())) {
					gpwscRegisterBean.setRecieptAmt(Double.parseDouble(gpwscRegisterForm.getReceiptAmount()));
				}
				gpwscRegisterBean.setRecieptRemark(gpwscRegisterForm.getReceiptRemark());
				
				gpwscRegisterBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				gpwscRegisterBean.setCrtByUsr(misSessionBean.getEnteredBy());
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return gpwscRegisterBean;
	}

}
