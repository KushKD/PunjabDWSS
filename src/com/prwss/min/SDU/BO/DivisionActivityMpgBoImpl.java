package com.prwss.min.SDU.BO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.SDU.bean.DivisionActivityDetailMpgBean;
import com.prwss.min.SDU.bean.DivisionActivityMpgBean;
import com.prwss.min.SDU.dao.DivisionActivityMpgDao;
import com.prwss.min.SDU.form.DivisionActivityMpgForm;
import com.prwss.min.SDU.form.DivisionActivityMpgGrid;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class DivisionActivityMpgBoImpl implements DivisionActivityMpgBo {

	private Logger log = Logger.getLogger(DivisionActivityMpgBoImpl.class);

	private DivisionActivityMpgDao divisionActivityMpgDao;

	public DivisionActivityMpgDao getDivisionActivityMpgDao() {
		return divisionActivityMpgDao;
	}

	public void setDivisionActivityMpgDao(DivisionActivityMpgDao divisionActivityMpgDao) {
		this.divisionActivityMpgDao = divisionActivityMpgDao;
	}

	/*
	 * ----------------------------------------SAVEStart--------------------------------------------------------------------
	 * ---
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean save(DivisionActivityMpgForm divisionActivityMpgForm, Integer enteredBy) throws MISException {

		boolean status = false;
		Integer divisionActivityId = 0;
		try {

			if (MisUtility.ifEmpty(divisionActivityMpgForm)) {
				List<Integer> divActivityId = divisionActivityMpgDao.fetchdivActivityId(
						Integer.parseInt(divisionActivityMpgForm.getFinancialYear()),
						Integer.parseInt(divisionActivityMpgForm.getDivision()),
						Integer.parseInt(divisionActivityMpgForm.getCategory()),
						Integer.parseInt(divisionActivityMpgForm.getComponent()),
						Integer.parseInt(divisionActivityMpgForm.getStage()),
						Integer.parseInt(divisionActivityMpgForm.getCampaign()));

				if (MisUtility.ifEmpty(divActivityId)) {
					DivisionActivityMpgBean divisionActivityMpgBeans = populateDivisionActivityMpgBean(
							divisionActivityMpgForm, enteredBy);
					if (MisUtility.ifEmpty(divisionActivityMpgBeans)) {
						divisionActivityId = divisionActivityMpgDao
								.saveDivisionActivityDetails(divisionActivityMpgBeans);

						if (MisUtility.ifEmpty(divisionActivityId)) {
							List<DivisionActivityDetailMpgBean> divisionActivityDetailMpgBeans = populateDivisionActivityDetailMpgBean(divisionActivityMpgForm,divisionActivityId,enteredBy);
							if(!MisUtility.ifEmpty(divisionActivityDetailMpgBeans)){
								status = divisionActivityMpgDao.saveDivisionActivityMpgDetails(divisionActivityDetailMpgBeans);
							}
						}
					}
				}else {
					List<DivisionActivityDetailMpgBean> divisionActivityDetailMpgBeans = populateDivisionActivityDetail2MpgBean(divisionActivityMpgForm,divActivityId,enteredBy);
					if(!MisUtility.ifEmpty(divisionActivityDetailMpgBeans)){
						status = divisionActivityMpgDao.saveDivisionActivityMpgDetails(divisionActivityDetailMpgBeans);
					}
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}

	/*
	 * ------------------------------------------------------------------------- ----------------------------------------------------------------
	 */

	/*
	 * -----------------------------------------------------populateFormStart--------------------------------------------------------------------
	 * ---
	 */

	private DivisionActivityMpgBean populateDivisionActivityMpgBean(
			DivisionActivityMpgForm divisionActivityMpgForm, Integer enteredBy) {

		DivisionActivityMpgBean divisionActivityMpgBean = null;
		try {
			if (MisUtility.ifEmpty(divisionActivityMpgForm)) {
				divisionActivityMpgBean = new DivisionActivityMpgBean();

				if (MisUtility.ifEmpty(divisionActivityMpgForm.getCampaign())) {
					divisionActivityMpgBean.setCampaign_id(Integer.parseInt(divisionActivityMpgForm.getCampaign()));
				}
				if (MisUtility.ifEmpty(divisionActivityMpgForm.getCategory())) {
					divisionActivityMpgBean.setCategory_id(Integer.parseInt(divisionActivityMpgForm.getCategory()));
				}
				if (MisUtility.ifEmpty(divisionActivityMpgForm.getComponent())) {
					divisionActivityMpgBean.setComponent_id(Integer.parseInt(divisionActivityMpgForm.getComponent()));
				}
				if (MisUtility.ifEmpty(divisionActivityMpgForm.getDivision())) {
					divisionActivityMpgBean.setDivision_id(Integer.parseInt(divisionActivityMpgForm.getDivision()));
				}
				if (MisUtility.ifEmpty(divisionActivityMpgForm.getFinancialYear())) {
					divisionActivityMpgBean
							.setFinancial_year(Integer.parseInt(divisionActivityMpgForm.getFinancialYear()));
				}
				if (MisUtility.ifEmpty(divisionActivityMpgForm.getStage())) {
					divisionActivityMpgBean.setStage_id(Integer.parseInt(divisionActivityMpgForm.getStage()));
				}

				divisionActivityMpgBean.setCreatedByUser(enteredBy);
				divisionActivityMpgBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return divisionActivityMpgBean;
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------------
	 */
	
	/*
	 * -----------------------------------------------------populateGridStart--------------------------------------------------------------------
	 * ---
	 */

	@SuppressWarnings("unchecked")
	private List<DivisionActivityDetailMpgBean> populateDivisionActivityDetailMpgBean(
			DivisionActivityMpgForm divisionActivityMpgForm, Integer divisionActivityId, Integer enteredBy) {

		List<DivisionActivityDetailMpgBean> divisionActivityDetailMpgBeans = null;
		DivisionActivityDetailMpgBean divisionActivityDetailMpgBean = null;
		try {
				  Collection<DivisionActivityMpgGrid> divisionActivityMpgGrids = divisionActivityMpgForm.getDivActivityMpgGrid().getAddedData();

				if (!MisUtility.ifEmpty(divisionActivityMpgGrids)) {
				divisionActivityDetailMpgBeans = new ArrayList<DivisionActivityDetailMpgBean>();
				
				for (DivisionActivityMpgGrid divisionActivityMpgGrid : divisionActivityMpgGrids) {
				
					divisionActivityDetailMpgBean = new DivisionActivityDetailMpgBean();
					Integer parameterId = null;
					
				 if (MisUtility.ifEmpty(divisionActivityMpgGrid.getActivity())){ 
					parameterId = getParameterId(divisionActivityMpgGrid.getActivity()); 
				 }
				if (MisUtility.ifEmpty(divisionActivityMpgGrid.getActivity())) {
					divisionActivityDetailMpgBean.setActivity_id(parameterId);
				}
				if (MisUtility.ifEmpty(divisionActivityId)) {
					divisionActivityDetailMpgBean.setDiv_activity_id(divisionActivityId);
				}

				divisionActivityDetailMpgBean.setCrt_by_usr(enteredBy);

				divisionActivityDetailMpgBeans.add(divisionActivityDetailMpgBean);
			}
				}	
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return divisionActivityDetailMpgBeans;
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------------
	 */
	
	
	/*
	 * -----------------------------------------------------populateGridStart--------------------------------------------------------------------
	 * ---
	 */

	@SuppressWarnings("unchecked")
	private List<DivisionActivityDetailMpgBean> populateDivisionActivityDetail2MpgBean(
			DivisionActivityMpgForm divisionActivityMpgForm, List<Integer> divActivityId, Integer enteredBy) {

		List<DivisionActivityDetailMpgBean> divisionActivityDetailMpgBeans = null;
		DivisionActivityDetailMpgBean divisionActivityDetailMpgBean = null;
		try {
				  Collection<DivisionActivityMpgGrid> divisionActivityMpgGrids = divisionActivityMpgForm.getDivActivityMpgGrid().getAddedData();

				if (!MisUtility.ifEmpty(divisionActivityMpgGrids)) {
				divisionActivityDetailMpgBeans = new ArrayList<DivisionActivityDetailMpgBean>();
				
				for (DivisionActivityMpgGrid divisionActivityMpgGrid : divisionActivityMpgGrids) {
				
					divisionActivityDetailMpgBean = new DivisionActivityDetailMpgBean();
					Integer parameterId = null;
					
				 if (MisUtility.ifEmpty(divisionActivityMpgGrid.getActivity())){ 
					parameterId = getParameterId(divisionActivityMpgGrid.getActivity()); 
				 }
				if (MisUtility.ifEmpty(divisionActivityMpgGrid.getActivity())) {
					divisionActivityDetailMpgBean.setActivity_id(parameterId);
				}
					divisionActivityDetailMpgBean.setDiv_activity_id(divActivityId.get(0));
				divisionActivityDetailMpgBean.setCrt_by_usr(enteredBy);

				divisionActivityDetailMpgBeans.add(divisionActivityDetailMpgBean);
			}
				}	
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return divisionActivityDetailMpgBeans;
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------------
	 */

	/*
	 * -----------------------------------------------------getCategoryId
	 * Start--------------------------------------------------------------------
	 * ---
	 */

	private Integer getParameterId(String data) {
		Integer catIds = null;

		try {
				catIds = Integer.parseInt(data.substring(data.indexOf('(') + 1, data.length() - 1));
				System.out.println("-----------------------------------"+catIds);
			System.out.println(catIds);
		} catch (Exception e) {

		}
		return catIds;
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------------
	 */

}
