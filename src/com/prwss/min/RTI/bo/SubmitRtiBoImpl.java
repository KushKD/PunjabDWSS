package com.prwss.min.RTI.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.dao.SubmitRtiDao;
import com.prwss.min.RTI.form.SubmitRtiForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class SubmitRtiBoImpl implements SubmitRtiBo {

	private Logger log = Logger.getLogger(SubmitRtiBoImpl.class);

	private SubmitRtiDao submitRtiDao;

	public SubmitRtiDao getSubmitRtiDao() {
		return submitRtiDao;
	}

	public void setSubmitRtiDao(SubmitRtiDao submitRtiDao) {
		this.submitRtiDao = submitRtiDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveRtiDetails(SubmitRtiForm RtiForm) throws MISException {

		SubmitRtiBean submitRtiBean = null;
		Boolean status = false;
		try {

			submitRtiBean = populateRtiDetails(RtiForm);
			if (MisUtility.ifEmpty(submitRtiBean)) {
				status = submitRtiDao.saveRtiDetails(submitRtiBean);
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return status;
	}

	private SubmitRtiBean populateRtiDetails(SubmitRtiForm rtiForm) {

		SubmitRtiBean submitRtiBean = new SubmitRtiBean();

		try {

			if (MisUtility.ifEmpty(rtiForm.getRtiID())) {
				submitRtiBean.setRtiID(Integer.parseInt(rtiForm.getRtiID()));
			}
			submitRtiBean.setRtiRefNo(rtiForm.getRtiRefNo());

			if (MisUtility.ifEmpty(rtiForm.getOffice())) {
				submitRtiBean.setOffice(Integer.parseInt(rtiForm.getOffice()));
			}
			if (MisUtility.ifEmpty(rtiForm.getZone())) {
				submitRtiBean.setZone(Integer.parseInt(rtiForm.getZone()));
			}
			if (MisUtility.ifEmpty(rtiForm.getCircle())) {
				submitRtiBean.setCircle(Integer.parseInt(rtiForm.getCircle()));
			}
			if (MisUtility.ifEmpty(rtiForm.getDistrict())) {
				submitRtiBean.setDistrict(Integer.parseInt(rtiForm.getDistrict()));
			}
			if (MisUtility.ifEmpty(rtiForm.getDivision())) {
				submitRtiBean.setDivision(Integer.parseInt(rtiForm.getDivision()));
			}
			if (MisUtility.ifEmpty(rtiForm.getSubdivision())) {
				submitRtiBean.setSubdivision(Integer.parseInt(rtiForm.getSubdivision()));
			}
			if (MisUtility.ifEmpty(rtiForm.getWings())) {
				submitRtiBean.setWings(Integer.parseInt(rtiForm.getWings()));
			}
			if (MisUtility.ifEmpty(rtiForm.getPio())) {
				submitRtiBean.setPio(Integer.parseInt(rtiForm.getPio()));
			}
			if (MisUtility.ifEmpty(rtiForm.getRtiType())) {
				submitRtiBean.setRtiType(Integer.parseInt(rtiForm.getRtiType()));
			}
			submitRtiBean.setShortname(rtiForm.getShortname());

			submitRtiBean.setDetails(rtiForm.getDetails());

			submitRtiBean.setName(rtiForm.getName());

			if (MisUtility.ifEmpty(rtiForm.getGender())) {
				submitRtiBean.setGender(Integer.parseInt(rtiForm.getGender()));
			}
			if (MisUtility.ifEmpty(rtiForm.getMobile())) {
				submitRtiBean.setMobile(Long.valueOf(rtiForm.getMobile()));
			}
			if (MisUtility.ifEmpty(rtiForm.getLandline())) {
				submitRtiBean.setLandline(Long.valueOf(rtiForm.getLandline()));
			}
			if (MisUtility.ifEmpty(rtiForm.getDistricta())) {
				submitRtiBean.setDistricta(Integer.parseInt(rtiForm.getDistricta()));
			}
			if (MisUtility.ifEmpty(rtiForm.getBlock())) {
				submitRtiBean.setBlock(Integer.parseInt(rtiForm.getBlock()));
			}
			if (MisUtility.ifEmpty(rtiForm.getVillageId())) {
				submitRtiBean.setVillageId(Integer.parseInt(rtiForm.getVillageId()));
			}
			submitRtiBean.setEmail(rtiForm.getEmail());

			submitRtiBean.setAddress(rtiForm.getAddress());

			submitRtiBean.setLiterate(rtiForm.getLiterate());

			if (MisUtility.ifEmpty(rtiForm.getPoverty())) {
				submitRtiBean.setPoverty(Integer.parseInt(rtiForm.getPoverty()));
			}
			if (MisUtility.ifEmpty(rtiForm.getApplicationFile().getFileSize())) {
				submitRtiBean.setApplicationFile(rtiForm.getApplicationFile().getFileData());
				submitRtiBean.setApplicationFileName(rtiForm.getApplicationFile().getFileName());
			}
			if (MisUtility.ifEmpty(rtiForm.getDocumentsFile().getFileSize())) {
				submitRtiBean.setDocumentsFile(rtiForm.getDocumentsFile().getFileData());
				submitRtiBean.setDocumentsFileName(rtiForm.getDocumentsFile().getFileName());
			}
			if (MisUtility.ifEmpty(rtiForm.getAmount())) {
				submitRtiBean.setAmount(Integer.parseInt(rtiForm.getAmount()));
			}
			submitRtiBean.setPaymentMode(rtiForm.getPaymentMode());

			submitRtiBean.setDatePayment(MisUtility.convertStringSqlDate(rtiForm.getDatePayment()));

			submitRtiBean.setRemarks(rtiForm.getRemarks());

			if (MisUtility.ifEmpty(rtiForm.getPaymentVia())) {
				submitRtiBean.setPaymentVia(Integer.parseInt(rtiForm.getPaymentVia()));
			}
			submitRtiBean.setNumber(rtiForm.getNumber());

			submitRtiBean.setUserID(Integer.parseInt(String.valueOf(rtiForm.getUserID())));

			submitRtiBean.setCreatedByUser(Integer.parseInt(String.valueOf(rtiForm.getCreatedByUser())));

			submitRtiBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return submitRtiBean;

	}

	/*
	 * @Override public List<UpdateRTIDto> getRtiByPagination() {
	 * List<SubmitRtiBean> submitRtiBean=null; List<UpdateRTIDto>
	 * rtiFormLst=null;
	 * 
	 * try{
	 * 
	 * submitRtiBean=submitRtiDao.getRtiByPagination();
	 * 
	 * if(!MisUtility.ifEmpty(submitRtiBean)){ rtiFormLst=new
	 * ArrayList<UpdateRTIDto>(); for(SubmitRtiBean bean:submitRtiBean){
	 * 
	 * UpdateRTIDto form=new UpdateRTIDto();
	 * 
	 * form.setRtiID(bean.getRtiID()); form.setRtiRefNo(bean.getRtiRefNo());
	 * form.setName(bean.getName()); form.setShortname(bean.getShortname());
	 * form.setDetails(bean.getDetails()); form.setUserID(bean.getUserID());
	 * form.setViewDetails("View Details");
	 * 
	 * rtiFormLst.add(form); }
	 * 
	 * } }catch(Exception e){ e.printStackTrace(); } return rtiFormLst; }
	 */

	public List<UpdateRTIDto> getRtiByPagination(String searchString,int clickedColumn,String colOrder) throws MISException {

		List<UpdateRTIDto> dataRTIPagination = null;

		dataRTIPagination = submitRtiDao.getRtiByPagination(searchString, clickedColumn, colOrder);

		return dataRTIPagination;
	}

	public boolean saveRTIUpdateDetails(SubmitRtiForm rtiForm) throws MISException {

		Boolean status = false;
		try {
			if (MisUtility.ifEmpty(rtiForm)) {
				if (MisUtility.ifEmpty(rtiForm.getRtiID())) {
					List<SubmitRtiBean> submitRtiBeans = submitRtiDao.findRtiCollection(rtiForm);
					if (!MisUtility.ifEmpty(submitRtiBeans)) {
						for (SubmitRtiBean submitRtiBean2 : submitRtiBeans) {
							submitRtiBean2.setResponseRemarks(rtiForm.getResponseRemarks());
							submitRtiBean2.setResponseName(rtiForm.getResponseFile().getFileName());
							submitRtiBean2.setResponseFile(rtiForm.getResponseFile().getFileData());
							submitRtiBean2.setIsclosed(Integer.parseInt(MISConstants.ONE));
							status = submitRtiDao.updateRtiResponse(submitRtiBean2);
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

}
