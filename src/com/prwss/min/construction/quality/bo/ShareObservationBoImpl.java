/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.construction.quality.bean.CCBean;
import com.prwss.min.construction.quality.bean.EmployeeDetailsGridBean;
import com.prwss.min.construction.quality.bean.SchemeGridBean;
import com.prwss.min.construction.quality.bean.ShareCommonObservationBean;
import com.prwss.min.construction.quality.bean.ShareObservationBean;
import com.prwss.min.construction.quality.dao.ShareObservationDao;
import com.prwss.min.construction.quality.form.ShareObservationForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ShareObservationBoImpl implements ShareObservationBo {

	private Logger log = Logger.getLogger(ShareObservationBoImpl.class);

	private ShareObservationDao shareObservationDao;

	public ShareObservationDao getShareObservationDao() {
		return shareObservationDao;
	}

	public void setShareObservationDao(ShareObservationDao shareObservationDao) {
		this.shareObservationDao = shareObservationDao;
	}

	@Transactional
	@Override
	public boolean saveObservation(ShareObservationForm shareObservationForm, int entBy) throws MISException {

		List<ShareObservationBean> shareObservationBeans = null;
		List<CCBean> ccBeans = null;
		long obsId = 0;
		ShareCommonObservationBean shareCommonObservationBean = null;
		boolean flag=false;
		try {
			if (MisUtility.ifEmpty(shareObservationForm)) {
				shareCommonObservationBean=populateCommonObservationData(shareObservationForm,entBy);
				if(MisUtility.ifEmpty(shareCommonObservationBean)){
					obsId=shareObservationDao.saveCommonObservationData(shareCommonObservationBean);
				}
				if(MisUtility.ifEmpty(obsId)){
					shareObservationBeans = populateObservation(shareObservationForm, entBy,obsId);
					if (!MisUtility.ifEmpty(shareObservationBeans)) {
						flag = shareObservationDao.saveObservation(shareObservationBeans);
						if (flag) {
							ccBeans = populateCC(shareObservationForm, entBy, obsId);
							if (!MisUtility.ifEmpty(ccBeans)) {
								flag=shareObservationDao.saveCc(ccBeans);
							}
						}
					}
				}
				
			}
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw new MISException(e);
		}
		return flag;
	}

	private ShareCommonObservationBean populateCommonObservationData(ShareObservationForm shareObservationForm,
			int entBy) {

		ShareCommonObservationBean shareCommonObservationBean = null;
		try {
			shareCommonObservationBean = new ShareCommonObservationBean();
			
			List<Long> monthlyPlanIds = shareObservationDao.getMonthlyPlanId(shareObservationForm);

			if (!MisUtility.ifEmpty(monthlyPlanIds)) {
				shareCommonObservationBean.setMonthlyPlanId(Integer.parseInt(String.valueOf(monthlyPlanIds.get(0))));
			}
			shareCommonObservationBean.setSubject(shareObservationForm.getSubject());

			if (MisUtility.ifEmpty(shareObservationForm.getTo()))
				shareCommonObservationBean.setToEmp(Integer.parseInt(shareObservationForm.getTo()));

			shareCommonObservationBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			shareCommonObservationBean.setCrtByUsr(Long.parseLong(String.valueOf(entBy)));
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return shareCommonObservationBean;
	}

	@SuppressWarnings("unchecked")
	private List<ShareObservationBean> populateObservation(ShareObservationForm shareObservationForm, int entBy, Long obsId) {
		List<ShareObservationBean> shareObservationBeans = null;
		try {
			Collection<SchemeGridBean> schemeGridBeans = shareObservationForm.getSchemeGrid().getAddedData();

			if (!MisUtility.ifEmpty(schemeGridBeans)) {
				shareObservationBeans = new ArrayList<ShareObservationBean>();
				for (SchemeGridBean schemeGridBean : schemeGridBeans) {
					ShareObservationBean shareObservationBean2 = new ShareObservationBean();
					if (MisUtility.ifEmpty(schemeGridBean.getSchemeId())) {
						shareObservationBean2.setSchemeId(Integer.parseInt(schemeGridBean.getSchemeId()));
					}
					if (MisUtility.ifEmpty(schemeGridBean.getStageId())) {
						shareObservationBean2.setSchemeStage(Integer.parseInt(schemeGridBean.getStageId()));
					}
					if (MisUtility.ifEmpty(schemeGridBean.getCheckedId())) {
						shareObservationBean2.setCheckedFor(Integer.parseInt(schemeGridBean.getCheckedId()));
					}
					shareObservationBean2
							.setInspectionDate(MisUtility.convertStringSqlDate(schemeGridBean.getInspectionDate()));
					shareObservationBean2.setRemarks(schemeGridBean.getRemarks());
					shareObservationBean2.setActiveFlag(Integer.parseInt(MISConstants.ONE));
					shareObservationBean2.setCrtByUsr(Long.parseLong(String.valueOf(entBy)));
					shareObservationBean2.setObsId(obsId);
					shareObservationBeans.add(shareObservationBean2);
				}
			}
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		return shareObservationBeans;
	}

	@SuppressWarnings("unchecked")
	private List<CCBean> populateCC(ShareObservationForm shareObservationForm, int entBy, Long obsId) {
		List<CCBean> ccBeans = null;
		try {
			Collection<EmployeeDetailsGridBean> employeeDetailsGridBeans = shareObservationForm.getEmployeeDetailsGrid()
					.getAddedData();
			if (!MisUtility.ifEmpty(employeeDetailsGridBeans)) {
				ccBeans = new ArrayList<CCBean>();
				for (EmployeeDetailsGridBean employeeDetailsGridBean : employeeDetailsGridBeans) {
					CCBean ccBean = new CCBean();

					if (MisUtility.ifEmpty(employeeDetailsGridBean.getEmployeeId())) {
						ccBean.setEmpName(Long.parseLong(employeeDetailsGridBean.getEmployeeId()));
					}
					if (MisUtility.ifEmpty(employeeDetailsGridBean.getDesignationId())) {
						ccBean.setEmpDesignation(Integer.parseInt(employeeDetailsGridBean.getDesignationId()));
					}
					ccBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
					ccBean.setCrtByUsr(Long.parseLong(String.valueOf(entBy)));
					ccBean.setObsId(obsId);
					ccBeans.add(ccBean);
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return ccBeans;

	}

}
