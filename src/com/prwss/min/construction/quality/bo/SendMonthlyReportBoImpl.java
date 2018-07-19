/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressMovementBean;
import com.prwss.min.construction.quality.bean.MonthlyReportDto;
import com.prwss.min.construction.quality.bean.ProgressCommentMappingBean;
import com.prwss.min.construction.quality.dao.SendMonthlyReportDao;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

/**
 * @author BH390738
 *
 */
public class SendMonthlyReportBoImpl extends AbstractPaginationMaster<MonthlyReportDto>
		implements SendMonthlyReportBo<MonthlyReportDto> {

	private Logger log = Logger.getLogger(SendMonthlyReportBoImpl.class);

	private LocationDao locationDao;
	private SendMonthlyReportDao sendMonthlyReportDao;

	public SendMonthlyReportDao getSendMonthlyReportDao() {
		return sendMonthlyReportDao;
	}

	public void setSendMonthlyReportDao(SendMonthlyReportDao sendMonthlyReportDao) {
		this.sendMonthlyReportDao = sendMonthlyReportDao;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Transactional
	@Override
	public boolean forwardReport(SendMonthlyReportForm sendMonthlyReportForm, int entBy)
			throws MISException, FileNotFoundException, IOException {

		MonthlyProgressMovementBean monthlyProgressMovementBean = null;
		ProgressCommentMappingBean progressCommentMappingBean = null;
		Long progressMvmntId = null;
		List<MonthlyProgressBean> monthlyProgressBeans = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(sendMonthlyReportForm)) {
				try {
					monthlyProgressBeans = sendMonthlyReportDao
							.fetchMonthlyPlanId(sendMonthlyReportForm.getMonthlyPlanId());
					if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
						for (MonthlyProgressBean monthlyProgressBean : monthlyProgressBeans) {
							monthlyProgressBean.setReportName(sendMonthlyReportForm.getProgressFile().getFileName());
							monthlyProgressBean.setReport(sendMonthlyReportForm.getProgressFile().getFileData());
							sendMonthlyReportDao.updateMonthlyProgress(monthlyProgressBean);
						}
					}
				} catch (DataAccessException e) {
					log.debug(e.getMessage());
					throw new MISException(e);
				}
				monthlyProgressMovementBean = populateMonthlyProgressBean(sendMonthlyReportForm, entBy);
				if (MisUtility.ifEmpty(monthlyProgressMovementBean)) {
					progressMvmntId = sendMonthlyReportDao.saveSendMonthlyProgress(monthlyProgressMovementBean);
					if (MisUtility.ifEmpty(progressMvmntId)) {
						progressCommentMappingBean = populateProgressCommentBean(sendMonthlyReportForm, entBy,
								progressMvmntId);
						if (MisUtility.ifEmpty(progressCommentMappingBean)) {
							status = sendMonthlyReportDao.saveProgressComment(progressCommentMappingBean);
						}
					}
				}

			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}

	private ProgressCommentMappingBean populateProgressCommentBean(SendMonthlyReportForm sendMonthlyReportForm,
			int entBy, Long progressMvmntId) throws MISException {
		ProgressCommentMappingBean progressCommentMappingBean = new ProgressCommentMappingBean();
		try {
			progressCommentMappingBean.setProgressMvmntId(progressMvmntId);
			progressCommentMappingBean.setCrtByUsr(Long.parseLong(String.valueOf(entBy)));
			progressCommentMappingBean.setComment(sendMonthlyReportForm.getComment());
			progressCommentMappingBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			progressCommentMappingBean.setProvidedBy(entBy);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MISException();
		}
		return progressCommentMappingBean;
	}

	private MonthlyProgressMovementBean populateMonthlyProgressBean(SendMonthlyReportForm sendMonthlyReportForm,
			int entBy) throws MISException {

		MonthlyProgressMovementBean monthlyProgressMovementBean = new MonthlyProgressMovementBean();
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		try {
			if (MisUtility.ifEmpty(sendMonthlyReportForm.getMonthlyPlanId())) {
				monthlyProgressMovementBean
						.setMonthlyPlanId(Integer.parseInt(sendMonthlyReportForm.getMonthlyPlanId()));
			}
			workFlowMasterBeans = locationDao.fetchWorkflowData(entBy, MISConstants.FOUR);
			if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
				monthlyProgressMovementBean.setLyingWithUser(workFlowMasterBeans.get(0).getTo_emp_id_fwd());
			}
			monthlyProgressMovementBean.setApprovalStatus(Integer.parseInt(MISConstants.ONE));
			monthlyProgressMovementBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			monthlyProgressMovementBean.setCrtByUsr(Long.parseLong(String.valueOf(entBy)));
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MISException(e.getMessage());
		}
		return monthlyProgressMovementBean;
	}

	@Transactional
	@Override
	public boolean approveReport(SendMonthlyReportForm sendMonthlyReportForm, int entBy) throws MISException {
		List<MonthlyProgressMovementBean> monthlyProgressMovementBeans = null;
		ProgressCommentMappingBean progressCommentMappingBean = null;
		Long progressMvmntId = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(sendMonthlyReportForm)) {

				monthlyProgressMovementBeans = populateMonthlyProgressBeans(sendMonthlyReportForm, entBy);
				if (!MisUtility.ifEmpty(monthlyProgressMovementBeans)) {
					for(MonthlyProgressMovementBean monthlyProgressMovementBean:monthlyProgressMovementBeans){
						progressMvmntId = sendMonthlyReportDao.updateSendMonthlyProgress(monthlyProgressMovementBean);
					}
					if (MisUtility.ifEmpty(progressMvmntId)) {
						progressCommentMappingBean = populateProgressCommentBean(sendMonthlyReportForm, entBy,
								progressMvmntId);
						if (MisUtility.ifEmpty(progressCommentMappingBean)) {
							status = sendMonthlyReportDao.saveProgressComment(progressCommentMappingBean);
						}
					}
				}

			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}

	private List<MonthlyProgressMovementBean> populateMonthlyProgressBeans(SendMonthlyReportForm sendMonthlyReportForm,
			int entBy) throws MISException {

		List<MonthlyProgressMovementBean> monthlyProgressMovementBeans = null;
		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		try {
			if (MisUtility.ifEmpty(sendMonthlyReportForm.getMonthlyPlanId())) {
				monthlyProgressMovementBeans = sendMonthlyReportDao.findMonthlyProgressMovement(sendMonthlyReportForm);
				if (!MisUtility.ifEmpty(monthlyProgressMovementBeans)) {
					workFlowMasterBeans = locationDao.fetchWorkflowData(entBy, MISConstants.FOUR);
					for(MonthlyProgressMovementBean MonthlyProgressMovementBean:monthlyProgressMovementBeans){
						if (!MisUtility.ifEmpty(workFlowMasterBeans)) {
							MonthlyProgressMovementBean.setLyingWithUser(workFlowMasterBeans.get(0).getTo_emp_id_fwd());
						}
						MonthlyProgressMovementBean.setApprovalStatus(Integer.parseInt(MISConstants.ONE));
					}
					
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MISException(e.getMessage());
		}
		return monthlyProgressMovementBeans;
	}

}
