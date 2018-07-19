/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;
import com.prwss.min.construction.quality.dao.UpdateYearlyPlanDao;
import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class UpdateYearPlanBoImpl implements UpdateYearPlanBo {

	private UpdateYearlyPlanDao updateYearlyPlanDao;

	public UpdateYearlyPlanDao getUpdateYearlyPlanDao() {
		return updateYearlyPlanDao;
	}

	public void setUpdateYearlyPlanDao(UpdateYearlyPlanDao updateYearlyPlanDao) {
		this.updateYearlyPlanDao = updateYearlyPlanDao;
	}

	@Override
	public boolean updateYearPlan(YearlyPlanInspectionForm yearlyPlanInspectionForm) throws MISException {

		List<YearlyPlanInspectionBean> yearlyPlanInspectionBeans = null;
		boolean status = false;
		try {
			// populateYearlyPlanBean();
			if (MisUtility.ifEmpty(yearlyPlanInspectionForm.getYearPlanId())) {
				yearlyPlanInspectionBeans = updateYearlyPlanDao
						.getYearlyPlanDetails(yearlyPlanInspectionForm.getYearPlanId());

				if (!MisUtility.ifEmpty(yearlyPlanInspectionBeans)) {
					for (YearlyPlanInspectionBean yearlyPlanInspectionBean : yearlyPlanInspectionBeans) {
						yearlyPlanInspectionBean
								.setFinancialYear(Integer.parseInt(yearlyPlanInspectionForm.getFinancialYear()));
						yearlyPlanInspectionBean
								.setAssignedTo(Integer.parseInt(yearlyPlanInspectionForm.getAssignedTo()));
						yearlyPlanInspectionBean
								.setInspectionType(Integer.parseInt(yearlyPlanInspectionForm.getInspectionType()));
						status = updateYearlyPlanDao.updateYearlyPlanDetails(yearlyPlanInspectionBean);
						if (status) {
							status=populateComponentYearlyPlan(
									yearlyPlanInspectionBean.getYearlyPlanningComponentMappingBeans(),
									yearlyPlanInspectionForm);
						}
					}
				}
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return status;
	}

	private boolean populateComponentYearlyPlan(
			Set<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBean,
			YearlyPlanInspectionForm yearlyPlanInspectionForm) {
		
		boolean status=false;
		try {
			for (YearlyPlanningComponentMappingBean yearlyPlanningComponentMappingBean2 : yearlyPlanningComponentMappingBean) {

				yearlyPlanningComponentMappingBean2
						.setTotalNumberVillage(Long.parseLong(yearlyPlanInspectionForm.getNumberOfVillage()));
				yearlyPlanningComponentMappingBean2
						.setPeriodOfImplementaion(Long.parseLong(yearlyPlanInspectionForm.getPeriodIml()));
				yearlyPlanningComponentMappingBean2
						.setVillageToBeVisited(Long.parseLong(yearlyPlanInspectionForm.getVisitVillage()));
				yearlyPlanningComponentMappingBean2
						.setVisitPerVillage(Long.parseLong(yearlyPlanInspectionForm.getVisitPerVillage()));
				yearlyPlanningComponentMappingBean2
						.setTotalVisit(Long.parseLong(yearlyPlanInspectionForm.getTotalVisit()));
				yearlyPlanningComponentMappingBean2
						.setTotalDuration(Long.parseLong(yearlyPlanInspectionForm.getTotalDuration()));
				yearlyPlanningComponentMappingBean2
						.setVisitedPerMonth(Long.parseLong(yearlyPlanInspectionForm.getPerMonthVisit()));
				yearlyPlanningComponentMappingBean2.setComments(yearlyPlanInspectionForm.getComments());
				status=updateYearlyPlanDao.updateComponentYearlyPlanDetails(yearlyPlanningComponentMappingBean2);
				// yearlyPlanInspectionBean.set
			}

			// yearlyPlanInspectionBean
		} catch (Exception e) {

		}
		return status;
	}
}
