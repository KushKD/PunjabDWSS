/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.SaveMonthlyReportGridBean;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;
import com.prwss.min.construction.quality.dao.SaveMonthlyReportDao;
import com.prwss.min.construction.quality.form.SaveMonthlyReportForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class SaveMonthlyReportBoImpl implements SaveMonthlyReportBo {

	private Logger log = Logger.getLogger(SaveMonthlyReportBoImpl.class);
	private SaveMonthlyReportDao saveMonthlyReportDao;
	
	
	public SaveMonthlyReportDao getSaveMonthlyReportDao() {
		return saveMonthlyReportDao;
	}

	public void setSaveMonthlyReportDao(SaveMonthlyReportDao saveMonthlyReportDao) {
		this.saveMonthlyReportDao = saveMonthlyReportDao;
	}

	@Override
	public boolean saveMonthlyReport(SaveMonthlyReportForm saveMonthlyReportForm, int entBy) throws MISException {
		boolean status=false;
		try {
			List<SaveMonthlyReportObservationBean> saveMonthlyReportObservationBeans=populateMonthlyReportBean(saveMonthlyReportForm, entBy);
			if(!MisUtility.ifEmpty(saveMonthlyReportObservationBeans)){
				status=saveMonthlyReportDao.saveMonthlyReport(saveMonthlyReportObservationBeans);
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	private List<SaveMonthlyReportObservationBean> populateMonthlyReportBean(
			SaveMonthlyReportForm saveMonthlyReportForm, int entBy) {

		List<SaveMonthlyReportObservationBean> saveMonthlyReportObservationBeans = null;
		try {
			Collection<SaveMonthlyReportGridBean> saveMonthlyReportGridBeans = saveMonthlyReportForm
					.getSaveMonthlyReportGrid().getAddedData();

			if (!MisUtility.ifEmpty(saveMonthlyReportGridBeans)) {
				saveMonthlyReportObservationBeans = new ArrayList<SaveMonthlyReportObservationBean>();
				for (SaveMonthlyReportGridBean saveMonthlyReportGridBean : saveMonthlyReportGridBeans) {

					SaveMonthlyReportObservationBean saveMonthlyReportObservationBean = new SaveMonthlyReportObservationBean();
					if (MisUtility.ifEmpty(saveMonthlyReportForm.getMonthlyPlanId())) {
						saveMonthlyReportObservationBean
								.setMonthlyPlanId(Long.parseLong(saveMonthlyReportForm.getMonthlyPlanId()));
					}if(MisUtility.ifEmpty(saveMonthlyReportGridBean.getPhaseId())){
						saveMonthlyReportObservationBean.setCommentType(Integer.parseInt(saveMonthlyReportGridBean.getPhaseId()));
					}if(MisUtility.ifEmpty(saveMonthlyReportGridBean.getSchemeId())){
						saveMonthlyReportObservationBean.setScheme(Integer.parseInt(saveMonthlyReportGridBean.getSchemeId()));
					}if(MisUtility.ifEmpty(saveMonthlyReportGridBean.getObservation())){
						saveMonthlyReportObservationBean.setObservation(saveMonthlyReportGridBean.getObservation());
					}
					saveMonthlyReportObservationBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
					saveMonthlyReportObservationBean.setCrtByUsr(Long.parseLong(String.valueOf(entBy)));
					saveMonthlyReportObservationBeans.add(saveMonthlyReportObservationBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return saveMonthlyReportObservationBeans;
	}

}
