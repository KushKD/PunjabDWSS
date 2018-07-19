/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyPlanGridBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanSchemeMappingBean;
import com.prwss.min.construction.quality.dao.MonthlyPlanDao;
import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MonthlyPlanBoImpl implements MonthlyPlanBo {

	private Logger log = Logger.getLogger(MonthlyPlanBoImpl.class);
	private MonthlyPlanDao monthlyPlanDao;

	public MonthlyPlanDao getMonthlyPlanDao() {
		return monthlyPlanDao;
	}

	public void setMonthlyPlanDao(MonthlyPlanDao monthlyPlanDao) {
		this.monthlyPlanDao = monthlyPlanDao;
	}

	@Override
	public boolean saveMonthlyInspectionPlan(MonthlyPlanInspectionForm monthlyPlanInspectionForm, int empId)
			throws MISException {

		MonthlyPlanInspectionBean monthlyPlanInspectionBean = null;
		int monthlyPlanId = 0;
		try {
			if (MisUtility.ifEmpty(monthlyPlanInspectionForm)) {
				monthlyPlanInspectionBean = populateMonthlyPlanBean(monthlyPlanInspectionForm, empId);
				if (MisUtility.ifEmpty(monthlyPlanInspectionBean)) {
					monthlyPlanId = monthlyPlanDao.saveMonthlyPlan(monthlyPlanInspectionBean);
					if (MisUtility.ifEmpty(monthlyPlanId)) {
						List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans = populateMonthlyPlanSchemeMappingBean(
								monthlyPlanInspectionForm, monthlyPlanId, empId);
						if (!MisUtility.ifEmpty(monthlyPlanSchemeMappingBeans)) {
							monthlyPlanDao.saveMonthlyPlanSchemeMapping(monthlyPlanSchemeMappingBeans);
						}
					}
				}

			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}

	@Override
	public boolean updateMonthlyInspectionPlan(MonthlyPlanInspectionForm monthlyPlanInspectionForm, int empId)
			throws MISException {

		List<MonthlyPlanInspectionBean> monthlyPlanInspectionBeans = null;
		try {
			monthlyPlanInspectionBeans = monthlyPlanDao.findMonthlyPlan(monthlyPlanInspectionForm.getMonthlyPlanId());
			if (!MisUtility.ifEmpty(monthlyPlanInspectionBeans)) {
				for (MonthlyPlanInspectionBean monthlyPlanInspectionBean : monthlyPlanInspectionBeans) {
					monthlyPlanInspectionBean
							.setVillageToBeVisited(Integer.parseInt(monthlyPlanInspectionForm.getCurrentMonthVisit()));
					boolean status = monthlyPlanDao.updateMonthlyPlan(monthlyPlanInspectionBean);
					if (status) {
						List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans = populateUpdatedMonthlyPlanSchemeBean(
								monthlyPlanInspectionForm, empId);
						if (!MisUtility.ifEmpty(monthlyPlanSchemeMappingBeans)) {
							monthlyPlanDao.updateMonthlyPlanSchemeMapping(monthlyPlanSchemeMappingBeans);
						}
					}
				}

			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
		}

		return true;
	}

	private List<MonthlyPlanSchemeMappingBean> populateUpdatedMonthlyPlanSchemeBean(
			MonthlyPlanInspectionForm monthlyPlanInspectionForm, int empId) {

		List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans = null;
		try {
			List<MonthlyPlanDto> monthlyPlanDtos = monthlyPlanInspectionForm.getMonthlyPlanDtos();
			if (!MisUtility.ifEmpty(monthlyPlanDtos)) {
				monthlyPlanSchemeMappingBeans = new ArrayList<MonthlyPlanSchemeMappingBean>();
				for (MonthlyPlanDto monthlyPlanDto : monthlyPlanDtos) {
					MonthlyPlanSchemeMappingBean monthlyPlanSchemeMappingBean = new MonthlyPlanSchemeMappingBean();
					monthlyPlanSchemeMappingBean = new MonthlyPlanSchemeMappingBean();
					if (MisUtility.ifEmpty(monthlyPlanDto.getScheme())) {
						monthlyPlanSchemeMappingBean.setScheme_id(Long.parseLong(monthlyPlanDto.getScheme()));
					}
					if (MisUtility.ifEmpty(monthlyPlanDto.getConstituency())) {
						monthlyPlanSchemeMappingBean.setConstituency(Long.parseLong(monthlyPlanDto.getConstituency()));
					}
					if (MisUtility.ifEmpty(monthlyPlanDto.getDistrict())) {
						monthlyPlanSchemeMappingBean.setDistrict(Long.parseLong(monthlyPlanDto.getDistrict()));
					}
					if (MisUtility.ifEmpty(monthlyPlanDto.getDivision())) {
						monthlyPlanSchemeMappingBean.setDivision(Long.parseLong(monthlyPlanDto.getDivision()));
					}
					monthlyPlanSchemeMappingBean
							.setMonthly_plan_scheme_id(Long.parseLong(monthlyPlanDto.getMonthly_plan_scheme_id()));
					monthlyPlanSchemeMappingBean
							.setMonthly_plan_id(Integer.parseInt(monthlyPlanDto.getMonthly_plan_id()));
					monthlyPlanSchemeMappingBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
					monthlyPlanSchemeMappingBean.setCrtByUsr(empId);
					monthlyPlanSchemeMappingBeans.add(monthlyPlanSchemeMappingBean);
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return monthlyPlanSchemeMappingBeans;
	}

	private MonthlyPlanInspectionBean populateMonthlyPlanBean(MonthlyPlanInspectionForm monthlyPlanInspectionForm,
			int empId) {

		MonthlyPlanInspectionBean monthlyPlanInspectionBean = null;
		List<Integer> yearlyPlanId=null;
		try {
			
			yearlyPlanId=monthlyPlanDao.findYeralyPlan(monthlyPlanInspectionForm.getYearPlan(), monthlyPlanInspectionForm.getComponent());
			if(!MisUtility.ifEmpty(yearlyPlanId)){
				monthlyPlanInspectionBean = new MonthlyPlanInspectionBean();

				if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getYearPlan())) {
					monthlyPlanInspectionBean.setYearlyPlanId(yearlyPlanId.get(0));
				}
				if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getMonth())) {
					monthlyPlanInspectionBean.setMonthId(Integer.parseInt(monthlyPlanInspectionForm.getMonth()));
				}
				if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getComponent())) {
					monthlyPlanInspectionBean.setComponent(Integer.parseInt(monthlyPlanInspectionForm.getComponent()));
				}
				if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getVisitPerMonth())) {
					monthlyPlanInspectionBean
							.setTotalNumberVillage(Long.parseLong(monthlyPlanInspectionForm.getVisitPerMonth()));
				}
				if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getCurrentMonthVisit())) {
					monthlyPlanInspectionBean
							.setVillageToBeVisited(Integer.parseInt(monthlyPlanInspectionForm.getCurrentMonthVisit()));
				}
				if (MisUtility.ifEmpty(monthlyPlanInspectionForm.getTeam())) {
					monthlyPlanInspectionBean.setTeam(Integer.parseInt(monthlyPlanInspectionForm.getTeam()));
				}
				monthlyPlanInspectionBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				monthlyPlanInspectionBean.setCrtByUsr(empId);
			}
		

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return monthlyPlanInspectionBean;

	}

	@SuppressWarnings("unchecked")
	private List<MonthlyPlanSchemeMappingBean> populateMonthlyPlanSchemeMappingBean(
			MonthlyPlanInspectionForm monthlyPlanInspectionForm, int monthlyPlanId, int empId) {

		MonthlyPlanSchemeMappingBean monthlyPlanSchemeMappingBean = null;
		List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans = null;
		try {
			Collection<MonthlyPlanGridBean> monthlyPlanGridBeans = monthlyPlanInspectionForm.getMonthlyPlanGrid()
					.getAddedData();

			if (!MisUtility.ifEmpty(monthlyPlanGridBeans)) {
				monthlyPlanSchemeMappingBeans = new ArrayList<MonthlyPlanSchemeMappingBean>();
				for (MonthlyPlanGridBean monthlyPlanGridBean : monthlyPlanGridBeans) {
					monthlyPlanSchemeMappingBean = new MonthlyPlanSchemeMappingBean();
					if (MisUtility.ifEmpty(monthlyPlanGridBean.getScheme())) {
						monthlyPlanSchemeMappingBean.setScheme_id(Long.parseLong(monthlyPlanGridBean.getScheme()));
					}
					if (MisUtility.ifEmpty(monthlyPlanGridBean.getConstituency())) {
						monthlyPlanSchemeMappingBean
								.setConstituency(Long.parseLong(monthlyPlanGridBean.getConstituency()));
					}
					if (MisUtility.ifEmpty(monthlyPlanGridBean.getDistrict())) {
						monthlyPlanSchemeMappingBean.setDistrict(Long.parseLong(monthlyPlanGridBean.getDistrict()));
					}
					if (MisUtility.ifEmpty(monthlyPlanGridBean.getDivision())) {
						monthlyPlanSchemeMappingBean.setDivision(Long.parseLong(monthlyPlanGridBean.getDivision()));
					}
					monthlyPlanSchemeMappingBean.setMonthly_plan_id(monthlyPlanId);
					monthlyPlanSchemeMappingBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
					monthlyPlanSchemeMappingBean.setCrtByUsr(empId);

					monthlyPlanSchemeMappingBeans.add(monthlyPlanSchemeMappingBean);

				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return monthlyPlanSchemeMappingBeans;
	}

	

}
