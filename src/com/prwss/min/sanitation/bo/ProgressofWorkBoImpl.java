package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.ProgressStageMappingBean;
import com.prwss.min.sanitation.bean.ProgressWorkBean;
import com.prwss.min.sanitation.dao.ProgressofWorkDao;
import com.prwss.min.sanitation.form.ProgressofWorkForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class ProgressofWorkBoImpl
		/* extends AbstractPaginationMaster<GramPanchayatDto> */ implements ProgressofWorkBo {

	private Logger log = Logger.getLogger(ProgressofWorkBoImpl.class);

	ProgressofWorkDao progressofWorkDao;

	public ProgressofWorkDao getProgressofWorkDao() {
		return progressofWorkDao;
	}

	public void setProgressofWorkDao(ProgressofWorkDao progressofWorkDao) {
		this.progressofWorkDao = progressofWorkDao;
	}

	/*---------------------------------------------------------saveProgressofWork--------------------------------------------------------------------*/

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveProgressofWork(ProgressofWorkForm progressofWorkForm) throws MISException {

		ProgressWorkBean progressWorkBean = null;
		List<ProgressWorkBean> progressWorkBeans = null;
		ProgressStageMappingBean progressStageMappingBean = null;
		List<ProgressStageMappingBean> progressStageMappingBeans = null;
		int progressWorkId = 0;
		boolean status = false;
		try {

			progressWorkBean = populateProgressWorkMaster(progressofWorkForm);
			// progressWorkBeans =
			// progressofWorkDao.getProgressWorkMaster(progressWorkBean);

			if (MisUtility.ifEmpty(progressWorkBeans)) {
				progressWorkId = progressofWorkDao.saveProgressWorkMaster(progressWorkBean);
				if (MisUtility.ifEmpty(progressWorkId)) {
					progressStageMappingBeans = populateprogressStageMapping(progressofWorkForm, progressWorkId);
					status = progressofWorkDao.saveprogressStageMapping(progressStageMappingBeans);
				}
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return status;
	}

	/*---------------------------------------------------------populateProgressWorkMaster-------------------------------------------------------*/

	private ProgressWorkBean populateProgressWorkMaster(ProgressofWorkForm progressofWorkForm) {
		ProgressWorkBean progressWorkBean = null;
		if (MisUtility.ifEmpty(progressofWorkForm)) {
			progressWorkBean = new ProgressWorkBean();
			progressWorkBean.setDistrictId(Integer.parseInt(progressofWorkForm.getDistrict()));
			progressWorkBean.setBlockId(Integer.parseInt(progressofWorkForm.getBlock()));
			progressWorkBean.setVillageId(Integer.parseInt(progressofWorkForm.getVillage()));
			progressWorkBean.setGramPanchayatId(Integer.parseInt(progressofWorkForm.getGramPanchayat()));
			progressWorkBean.setBeneficiaryId(Integer.parseInt(progressofWorkForm.getBeneficiaryId()));
			progressWorkBean.setCrtByUsr(Integer.parseInt(String.valueOf(progressofWorkForm.getCreatedByUser())));
			progressWorkBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
		}

		return progressWorkBean;

	}

	/*---------------------------------------------------populateprogressStageMapping---------------------------------------------------------------------*/

	private List<ProgressStageMappingBean> populateprogressStageMapping(ProgressofWorkForm progressofWorkForm,
			int progressWorkId) {
		ProgressStageMappingBean progressStageMappingBean = null;
		ProgressStageMappingBean progressStageMappingBean1 = null;
		ProgressStageMappingBean progressStageMappingBean2 = null;
		List<ProgressStageMappingBean> progressStageMappingBeans = null;
		try {

			if (MisUtility.ifEmpty(progressofWorkForm)) {
				progressStageMappingBeans = new ArrayList<ProgressStageMappingBean>();
				progressStageMappingBean2 = new ProgressStageMappingBean();
				progressStageMappingBean1 = new ProgressStageMappingBean();
				progressStageMappingBean = new ProgressStageMappingBean();

				progressStageMappingBean.setProgressWorkId(progressWorkId);
				progressStageMappingBean.setStageId(Integer.parseInt(MISConstants.ONE));
				if (MisUtility.ifEmpty(progressofWorkForm.getStatus1())) {
					progressStageMappingBean.setStageStatus(Integer.parseInt(progressofWorkForm.getStatus1()));
				}
				if (MisUtility.ifEmpty(progressofWorkForm.getPhotograph1().getFileSize())){
					progressStageMappingBean.setPictureStage(progressofWorkForm.getPhotograph1().getFileData());
				}
				progressStageMappingBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				progressStageMappingBean
						.setCrtByUser(Integer.parseInt(String.valueOf(progressofWorkForm.getCreatedByUser())));

				progressStageMappingBean1.setProgressWorkId(progressWorkId);
				progressStageMappingBean1.setStageId(Integer.parseInt(MISConstants.TWO));
				if (MisUtility.ifEmpty(progressofWorkForm.getStatus2())){
					progressStageMappingBean1.setStageStatus(Integer.parseInt(progressofWorkForm.getStatus2()));
				}
				if (MisUtility.ifEmpty(progressofWorkForm.getPhotograph2().getFileSize())){
					progressStageMappingBean1.setPictureStage(progressofWorkForm.getPhotograph2().getFileData());
				}
				progressStageMappingBean1.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				progressStageMappingBean1
						.setCrtByUser(Integer.parseInt(String.valueOf(progressofWorkForm.getCreatedByUser())));

				progressStageMappingBean2.setProgressWorkId(progressWorkId);
				progressStageMappingBean2.setStageId(Integer.parseInt(MISConstants.THREE));
				if (MisUtility.ifEmpty(progressofWorkForm.getStatus3())){
					progressStageMappingBean2.setStageStatus(Integer.parseInt(progressofWorkForm.getStatus3()));
				}
				if (MisUtility.ifEmpty(progressofWorkForm.getPhotograph3().getFileSize())){
					progressStageMappingBean2.setPictureStage(progressofWorkForm.getPhotograph3().getFileData());
				}
				progressStageMappingBean2.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				progressStageMappingBean2
						.setCrtByUser(Integer.parseInt(String.valueOf(progressofWorkForm.getCreatedByUser())));

				progressStageMappingBeans.add(progressStageMappingBean);
				progressStageMappingBeans.add(progressStageMappingBean1);
				progressStageMappingBeans.add(progressStageMappingBean2);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return progressStageMappingBeans;
	}

	/*-----------------------------------------getGramPanchayatMasterByPagination------------------------------------------------------------------------*/

	/*
	 * @Override public List<GramPanchayatDto>
	 * getGramPanchayatMasterByPagination() throws MISException {
	 * List<GramPanchayatDto> locationMasterFormLst = null; try {
	 * locationMasterFormLst =
	 * gramPanchayatMasterDao.getLocationMasterByPagination();
	 * 
	 * 
	 * } catch (DataAccessException e) { log.error(e.getLocalizedMessage(), e);
	 * throw new MISException(e); } return locationMasterFormLst; }
	 */

}