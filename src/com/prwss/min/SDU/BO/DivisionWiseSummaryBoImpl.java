package com.prwss.min.SDU.BO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.dao.DivisionWiseSummaryDao;
import com.prwss.min.SDU.form.DivisionWiseSummaryForm;
import com.prwss.min.SDU.form.DivisionWiseSummaryGrid;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class DivisionWiseSummaryBoImpl implements DivisionWiseSummaryBo{
	
	private Logger log = Logger.getLogger(DivisionWiseSummaryBoImpl.class);
	
	private DivisionWiseSummaryDao divisionWiseSummaryDao;

	public DivisionWiseSummaryDao getDivisionWiseSummaryDao() {
		return divisionWiseSummaryDao;
	}
	public void setDivisionWiseSummaryDao(DivisionWiseSummaryDao divisionWiseSummaryDao) {
		this.divisionWiseSummaryDao = divisionWiseSummaryDao;
	}
/* ----------------------------------------SAVE Start-----------------------------------------------------------------------*/	
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean save(DivisionWiseSummaryForm divisionWiseSummaryForm, Integer employeeId) throws MISException {

		boolean status=false;
		try {

			if (MisUtility.ifEmpty(divisionWiseSummaryForm)) {
				/*YearlyPlanInspectionBean yearlyPlanInspectionBean = getYearlyInspectionBean(yearlyPlanInspectionForm,
						employeeId);
				if (MisUtility.ifEmpty(yearlyPlanInspectionBean)) {
					yearlyPlanId = yearPlanInspectionDao.saveYearPlanDetails(yearlyPlanInspectionBean);
				}
				if (MisUtility.ifEmpty(yearlyPlanId)) {*/
					List<DivisionWiseSummaryBean> divisionWiseSummaryBeans = populateDivisionWiseSummaryBean(
							divisionWiseSummaryForm, employeeId);
					if (!MisUtility.ifEmpty(divisionWiseSummaryBeans)) {
						status = divisionWiseSummaryDao.saveDivisionWiseSummaryDetails(divisionWiseSummaryBeans);
					}

				/*}*/
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}
	
	/* -----------------------------------------------------------------------------------------------------------------------------------------*/

	
	
	/* -----------------------------------------------------populate Start-----------------------------------------------------------------------*/

	
	@SuppressWarnings("unchecked")
	private List<DivisionWiseSummaryBean> populateDivisionWiseSummaryBean(
			DivisionWiseSummaryForm divisionWiseSummaryForm, Integer employeeId) {

		List<DivisionWiseSummaryBean> divisionWiseSummaryBeans = null;
		DivisionWiseSummaryBean divisionWiseSummaryBean = null;
		try {
			if (MisUtility.ifEmpty(divisionWiseSummaryForm)) {
				Collection<DivisionWiseSummaryGrid> divisionWiseSummaryGrids = divisionWiseSummaryForm.getDivWiseSumPlanGrid()
						.getAddedData();

				if (!MisUtility.ifEmpty(divisionWiseSummaryGrids)) {
					divisionWiseSummaryBeans = new ArrayList<DivisionWiseSummaryBean>();
					for (DivisionWiseSummaryGrid divisionWiseSummaryGrid : divisionWiseSummaryGrids) {
						divisionWiseSummaryBean = new DivisionWiseSummaryBean();
						System.out.println("category name-------->"+divisionWiseSummaryGrid.getCategory());
						Integer categoryId = getCategoryId(divisionWiseSummaryGrid.getCategory());
						System.out.println("category name-------->"+categoryId);
						
						if (MisUtility.ifEmpty(categoryId)) {
							divisionWiseSummaryBean.setCategory(categoryId);
						}
						if (MisUtility.ifEmpty(divisionWiseSummaryGrid.getNoOfVillages())) {
							divisionWiseSummaryBean
									.setNoOfVillages(Integer.parseInt(divisionWiseSummaryGrid.getNoOfVillages()));
						}
						if (MisUtility.ifEmpty(divisionWiseSummaryForm.getFinancialYear())) {
							divisionWiseSummaryBean
									.setFinancialYear(Integer.parseInt(divisionWiseSummaryForm.getFinancialYear()));
						}
						if (MisUtility.ifEmpty(divisionWiseSummaryForm.getDivision())) {
							divisionWiseSummaryBean.setDivision(Integer.parseInt(divisionWiseSummaryForm.getDivision()));
						}
						
						divisionWiseSummaryBean.setCreatedByUser(employeeId);
						divisionWiseSummaryBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
						
						divisionWiseSummaryBeans.add(divisionWiseSummaryBean);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return divisionWiseSummaryBeans;
	}

	/* ---------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	/* -----------------------------------------------------getCategoryId Start-----------------------------------------------------------------------*/
	
	
	
	private Integer getCategoryId(String categoryName) {
		Integer catId = null;
		try {
			String categoryId = categoryName.substring(categoryName.indexOf('(') + 1, categoryName.length() - 1);
			System.out.println("--------------1---------------------"+categoryId);
			if (MisUtility.ifEmpty(categoryId)) {
				catId = Integer.parseInt(categoryId);
			}
		} catch (Exception e) {

		}
		return catId;
	}

	/* ---------------------------------------------------------------------------------------------------------------------------------------------*/

}
