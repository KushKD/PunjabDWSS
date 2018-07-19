/**
 * 
 */
package com.prwss.min.finance.bo;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.finance.bean.FundRequestDocBean;
import com.prwss.min.finance.bean.FundRequestMasterBean;
import com.prwss.min.finance.dao.FundRequestDao;
import com.prwss.min.finance.form.FundRequestForm;
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
public class FundRequestBoImpl implements FundRequestBo {

	private Logger log = Logger.getLogger(FundRequestBoImpl.class);
	private DocumentNumberDAO documentNumberDao;
	private FundRequestDao fundRequestDao;

	
	
	public DocumentNumberDAO getDocumentNumberDao() {
		return documentNumberDao;
	}

	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public FundRequestDao getFundRequestDao() {
		return fundRequestDao;
	}

	public void setFundRequestDao(FundRequestDao fundRequestDao) {
		this.fundRequestDao = fundRequestDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveFundRequest(FundRequestForm fundRequestForm, MISSessionBean misSessionBean) throws MISException {

		FundRequestMasterBean fundRequestMasterBean = null;
		FundRequestMasterBean fundRequestMasterBean1 = null;
		FundRequestDocBean fundRequestDocBean = null;

		boolean status = false;
		try {
			if (MisUtility.ifEmpty(fundRequestForm)) {
				DocumentNumberBean documentNumebrBean = new DocumentNumberBean();
				documentNumebrBean.setDocumentType("FUND-REQUEST");
				DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
				if (!(MisUtility.ifEmpty(documentNumberBean))) {
					throw new MISException();
				}
				fundRequestMasterBean = populateFundRequestBean(fundRequestForm, misSessionBean);
				fundRequestMasterBean1 = fundRequestDao.save(fundRequestMasterBean);
				if (MisUtility.ifEmpty(fundRequestMasterBean1)) {
					fundRequestDocBean = populateFundRequestDocBean(fundRequestForm, misSessionBean,
							fundRequestMasterBean1);
					status = fundRequestDao.saveFundRequestDao(fundRequestDocBean);
					if (status) {
						documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
					}
				}
			}

			// populateFundRequestBean();
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	private FundRequestDocBean populateFundRequestDocBean(FundRequestForm fundRequestForm,
			MISSessionBean misSessionBean, FundRequestMasterBean fundRequestMasterBean) {

		FundRequestDocBean fundRequestDocBean = null;
		try {
			if (MisUtility.ifEmpty(fundRequestForm)) {
				fundRequestDocBean = new FundRequestDocBean();
				if (MisUtility.ifEmpty(fundRequestForm.getAttachment().getFileName())) {
					fundRequestDocBean.setAttachment(fundRequestForm.getAttachment().getFileData());
				}
				fundRequestDocBean.setFund_request_id(fundRequestMasterBean.getFundRequestId());
				fundRequestDocBean.setAttachment_name(fundRequestForm.getAttachment().getFileName());
				fundRequestDocBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				fundRequestDocBean.setCrtByUsr(misSessionBean.getEnteredBy());
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return fundRequestDocBean;

	}

	private FundRequestMasterBean populateFundRequestBean(FundRequestForm fundRequestForm,
			MISSessionBean misSessionBean) {
		FundRequestMasterBean fundRequestMasterBean = null;
		try {
			if (MisUtility.ifEmpty(fundRequestForm)) {
				fundRequestMasterBean = new FundRequestMasterBean();

				if (MisUtility.ifEmpty(fundRequestForm.getInstallment())) {
					fundRequestMasterBean.setInstallmentId(Integer.parseInt(fundRequestForm.getInstallment()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getDivision())) {
					fundRequestMasterBean.setDivisionId(Integer.parseInt(fundRequestForm.getDivision()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getDistrict())) {
					fundRequestMasterBean.setDistrictId(Integer.parseInt(fundRequestForm.getDistrict()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getBlock())) {
					fundRequestMasterBean.setBlockId(Integer.parseInt(fundRequestForm.getBlock()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getGpwsc())) {
					fundRequestMasterBean.setGpwscId(Integer.parseInt(fundRequestForm.getGpwsc()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getSchemeType())) {
					fundRequestMasterBean.setSchemeType(Integer.parseInt(fundRequestForm.getSchemeType()));
				}
				fundRequestMasterBean.setRequest_no(fundRequestForm.getRequestNo());
				if (MisUtility.ifEmpty(fundRequestForm.getSchemeName())) {
					fundRequestMasterBean.setSchemeId(Integer.parseInt(fundRequestForm.getSchemeName()));
				}
				fundRequestMasterBean.setAdm_app_no(fundRequestForm.getApprovalNo());

				if (MisUtility.ifEmpty(fundRequestForm.getValue())) {
					fundRequestMasterBean.setAdm_app_val(Double.valueOf(fundRequestForm.getValue()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getDate())) {
					fundRequestMasterBean.setAdm_app_dt(MisUtility.convertStringToDate(fundRequestForm.getDate()));
				}
				fundRequestMasterBean.setMis_code(fundRequestForm.getMisCode());
				if (MisUtility.ifEmpty(fundRequestForm.getDate())) {
					fundRequestMasterBean
							.setTechsac_app_no(Double.parseDouble(fundRequestForm.getSanctionApprovalNo()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getSanctionDate())) {
					fundRequestMasterBean
							.setTechsac_app_dt(MisUtility.convertStringToDate(fundRequestForm.getSanctionDate()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getSanctionValue())) {
					fundRequestMasterBean.setTechsac_app_val(Double.valueOf(fundRequestForm.getSanctionValue()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getWorldBankDate())) {
					fundRequestMasterBean
							.setWb_app_date(MisUtility.convertStringToDate(fundRequestForm.getWorldBankDate()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getWorldBankApprovalNo())) {
					fundRequestMasterBean.setWb_app_no(fundRequestForm.getWorldBankApprovalNo());
				}
				if (MisUtility.ifEmpty(fundRequestForm.getDue())) {
					fundRequestMasterBean.setBen_share_due(Long.parseLong(fundRequestForm.getDue()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getActuallyCollected())) {
					fundRequestMasterBean.setBen_share_coll(Long.parseLong(fundRequestForm.getActuallyCollected()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getNetDSR())) {
					fundRequestMasterBean.setNet_dsr(Long.parseLong(fundRequestForm.getNetDSR()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getProcDate())) {
					fundRequestMasterBean
							.setProc_pckg_dt(MisUtility.convertStringSqlDate(fundRequestForm.getProcDate()));
				}
				fundRequestMasterBean.setProc_pckg_num(fundRequestForm.getProcNumber());
				if (MisUtility.ifEmpty(fundRequestForm.getAwardDate())) {
					fundRequestMasterBean.setAward_date(MisUtility.convertStringToDate(fundRequestForm.getAwardDate()));
				}
				fundRequestMasterBean.setAward_number(fundRequestForm.getAwardNumber());

				if (MisUtility.ifEmpty(fundRequestForm.getAwardValue())) {
					fundRequestMasterBean.setAward_val(Long.parseLong(fundRequestForm.getAwardValue()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getValueInstallment())) {
					fundRequestMasterBean.setValue_of_inst(Double.parseDouble(fundRequestForm.getValueInstallment()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getSchemePackage())) {
					fundRequestMasterBean.setStep_entry(Long.parseLong(fundRequestForm.getSchemePackage()));
				}
				if (MisUtility.ifEmpty(fundRequestForm.getPackageNo())) {
					fundRequestMasterBean.setPackage_no(Long.parseLong(fundRequestForm.getPackageNo()));
				}

				fundRequestMasterBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				fundRequestMasterBean.setCrtByUsr(misSessionBean.getEnteredBy());

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return fundRequestMasterBean;
	}

}
