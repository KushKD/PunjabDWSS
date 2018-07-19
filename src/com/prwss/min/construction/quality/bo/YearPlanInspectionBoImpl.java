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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;
import com.prwss.min.construction.quality.dao.YearPlanInspectionDao;
import com.prwss.min.construction.quality.form.YearlyInspectionGrid;
import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class YearPlanInspectionBoImpl implements YearPlanInspectionBo {

	private Logger log = Logger.getLogger(YearPlanInspectionBoImpl.class);

	private YearPlanInspectionDao yearPlanInspectionDao;

	public YearPlanInspectionDao getYearPlanInspectionDao() {
		return yearPlanInspectionDao;
	}

	public void setYearPlanInspectionDao(YearPlanInspectionDao yearPlanInspectionDao) {
		this.yearPlanInspectionDao = yearPlanInspectionDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean save(YearlyPlanInspectionForm yearlyPlanInspectionForm, Integer employeeId) throws MISException {

		int yearlyPlanId = 0;
		try {

			if (MisUtility.ifEmpty(yearlyPlanInspectionForm)) {
				YearlyPlanInspectionBean yearlyPlanInspectionBean = getYearlyInspectionBean(yearlyPlanInspectionForm,
						employeeId);
				if (MisUtility.ifEmpty(yearlyPlanInspectionBean)) {
					yearlyPlanId = yearPlanInspectionDao.saveYearPlanDetails(yearlyPlanInspectionBean);
				}
				if (MisUtility.ifEmpty(yearlyPlanId)) {
					List<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBeans = populateYearlyPlanningComponentBean(
							yearlyPlanInspectionForm, employeeId, yearlyPlanId);
					if (!MisUtility.ifEmpty(yearlyPlanningComponentMappingBeans)) {
						yearPlanInspectionDao.saveYearPlanComponentDetails(yearlyPlanningComponentMappingBeans);
					}

				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	private YearlyPlanInspectionBean getYearlyInspectionBean(YearlyPlanInspectionForm yearlyPlanInspectionForm,
			Integer empId) {
		YearlyPlanInspectionBean yearlyPlanInspectionBean = null;
		try {
			yearlyPlanInspectionBean = new YearlyPlanInspectionBean();
			yearlyPlanInspectionBean.setYearlyPlanName(yearlyPlanInspectionForm.getPlanName());
			if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getFinalizationDate())) {
				yearlyPlanInspectionBean.setDateFinalization(
						MisUtility.convertStringSqlDate(yearlyPlanInspectionForm.getFinalizationDate()));
			}
			if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getInspectionType())) {
				yearlyPlanInspectionBean
						.setInspectionType(Integer.parseInt(yearlyPlanInspectionForm.getInspectionType()));
			}
			if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getFinancialYear())) {
				yearlyPlanInspectionBean
						.setFinancialYear(Integer.parseInt(yearlyPlanInspectionForm.getFinancialYear()));
			}
			if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getAssignedTo())) {
				yearlyPlanInspectionBean.setAssignedTo(Integer.parseInt(yearlyPlanInspectionForm.getAssignedTo()));
			}
			yearlyPlanInspectionBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
			yearlyPlanInspectionBean.setCrtByUsr(empId);
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		return yearlyPlanInspectionBean;
	}

	@SuppressWarnings("unchecked")
	private List<YearlyPlanningComponentMappingBean> populateYearlyPlanningComponentBean(
			YearlyPlanInspectionForm yearlyPlanInspectionForm, Integer empId, Integer yearlyPlanId) {

		List<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBeans = null;
		YearlyPlanningComponentMappingBean yearlyPlanningComponentMappingBean = null;
		try {
			if (MisUtility.ifEmpty(yearlyPlanInspectionForm)) {
				Collection<YearlyInspectionGrid> yearlyInspectionGrids = yearlyPlanInspectionForm.getYearlyPlanGrid()
						.getAddedData();

				if (!MisUtility.ifEmpty(yearlyInspectionGrids)) {
					yearlyPlanningComponentMappingBeans = new ArrayList<YearlyPlanningComponentMappingBean>();
					for (YearlyInspectionGrid yearlyInspectionGrid : yearlyInspectionGrids) {
						yearlyPlanningComponentMappingBean = new YearlyPlanningComponentMappingBean();
						
						Integer componentId = getComponentId(yearlyInspectionGrid.getComponent());
						if (MisUtility.ifEmpty(componentId)) {
							yearlyPlanningComponentMappingBean.setComponent(componentId);
						}
						if (MisUtility.ifEmpty(yearlyInspectionGrid.getNumberOfVillage())) {
							yearlyPlanningComponentMappingBean
									.setTotalNumberVillage(Long.parseLong(yearlyInspectionGrid.getNumberOfVillage()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getVisitVillage())) {
							yearlyPlanningComponentMappingBean
									.setVillageToBeVisited(Long.parseLong(yearlyInspectionGrid.getVisitVillage()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getVisitVillage())) {
							yearlyPlanningComponentMappingBean.setVillageToBeVisited(Long.parseLong(yearlyInspectionGrid.getVisitVillage()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getPeriodIml())) {
							yearlyPlanningComponentMappingBean.setPeriodOfImplementaion(Long.parseLong(yearlyInspectionGrid.getPeriodIml()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getVisitPerVillage())) {
							yearlyPlanningComponentMappingBean.setVisitPerVillage(Long.parseLong(yearlyInspectionGrid.getVisitPerVillage()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getPerMonthVisit())) {
							yearlyPlanningComponentMappingBean.setVisitedPerMonth(Long.parseLong(yearlyInspectionGrid.getPerMonthVisit()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getTotalVisit())) {
							yearlyPlanningComponentMappingBean.setTotalVisit(Long.parseLong(yearlyInspectionGrid.getTotalVisit()));
						}
						if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getTotalDuration())) {
							yearlyPlanningComponentMappingBean.setTotalDuration(Long.parseLong(yearlyInspectionGrid.getTotalDuration()));
						}
						yearlyPlanningComponentMappingBean.setComments(yearlyPlanInspectionForm.getComments());
						yearlyPlanningComponentMappingBean.setYearlyPlanId(yearlyPlanId);
						yearlyPlanningComponentMappingBean.setCrtByUsr(empId);
						yearlyPlanningComponentMappingBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
						yearlyPlanningComponentMappingBeans.add(yearlyPlanningComponentMappingBean);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return yearlyPlanningComponentMappingBeans;
	}

	private Integer getComponentId(String componentName) {
		Integer comId = null;
		try {
			String componentId = componentName.substring(componentName.indexOf('(') + 1, componentName.length() - 1);
			if (MisUtility.ifEmpty(componentId)) {
				comId = Integer.parseInt(componentId);
			}
		} catch (Exception e) {

		}
		return comId;
	}
}
