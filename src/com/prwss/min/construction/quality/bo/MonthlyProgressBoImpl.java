/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressDto;
import com.prwss.min.construction.quality.bean.MonthlyProgressGridBean;
import com.prwss.min.construction.quality.dao.MonthlyPlanDao;
import com.prwss.min.construction.quality.dao.MonthlyProgressDao;
import com.prwss.min.construction.quality.form.MonthlyProgressForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MonthlyProgressBoImpl implements MonthlyProgressBo {

	private Logger log = Logger.getLogger(MonthlyProgressBoImpl.class);

	private MonthlyProgressDao monthlyProgressDao;
	private MonthlyPlanDao monthlyPlanDao;

	public MonthlyPlanDao getMonthlyPlanDao() {
		return monthlyPlanDao;
	}

	public void setMonthlyPlanDao(MonthlyPlanDao monthlyPlanDao) {
		this.monthlyPlanDao = monthlyPlanDao;
	}

	public MonthlyProgressDao getMonthlyProgressDao() {
		return monthlyProgressDao;
	}

	public void setMonthlyProgressDao(MonthlyProgressDao monthlyProgressDao) {
		this.monthlyProgressDao = monthlyProgressDao;
	}

	@Override
	public boolean save(MonthlyProgressForm monthlyProgressForm, int entBy) throws MISException {

		List<MonthlyProgressBean> monthlyProgressBeans = null;
		try {
			if (MisUtility.ifEmpty(monthlyProgressForm)) {
				monthlyProgressBeans = populateMonthlyProgressBean(monthlyProgressForm, entBy);

				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
					monthlyProgressDao.saveMonthlyProgress(monthlyProgressBeans);
				}
			}
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		return true;
	}

	@Override
	public boolean update(MonthlyProgressForm monthlyProgressForm, int entBy) throws MISException {

		boolean status = false;
		List<MonthlyProgressBean> monthlyProgressBeans = null;
		try {
			if (MisUtility.ifEmpty(monthlyProgressForm)) {
				monthlyProgressBeans = populateMonthlyProgressBean(monthlyProgressForm, entBy);

				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
					status = monthlyProgressDao.saveMonthlyProgress(monthlyProgressBeans);
				}
			}
		} catch (Exception e) {
			throw new MISException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	private List<MonthlyProgressBean> populateMonthlyProgressBean(MonthlyProgressForm monthlyProgressForm,
			int enterBy) {

		List<MonthlyProgressBean> monthlyProgressBeans = null;
		List<MonthlyProgressDto> monthlyProgressDtos = null;
		List<Integer> monthlyPlanId = null;
		List<Integer> yearlyPlanId = null;
		try {
			Collection<MonthlyProgressGridBean> monthlyProgressGridBeans = monthlyProgressForm.getMonthlyProgressGrid()
					.getAddedData();

			monthlyProgressBeans = new ArrayList<MonthlyProgressBean>();
			if (!MisUtility.ifEmpty(monthlyProgressGridBeans)) {
				for (MonthlyProgressGridBean monthlyProgressGridBean : monthlyProgressGridBeans) {
					MonthlyProgressBean monthlyProgressBean = new MonthlyProgressBean();

					yearlyPlanId = monthlyPlanDao.findYeralyPlan(monthlyProgressForm.getYearPlan(),
							monthlyProgressGridBean.getComponentId());
					if (!MisUtility.ifEmpty(yearlyPlanId)) {
						monthlyPlanId = monthlyProgressDao.getMonthlyPlanId(yearlyPlanId.get(0),
								monthlyProgressForm.getMonth(), monthlyProgressGridBean.getTeamId());
						if (!MisUtility.ifEmpty(monthlyPlanId)) {
							monthlyProgressBean.setMonthlyPlanId(monthlyPlanId.get(0));
							if (MisUtility.ifEmpty(monthlyProgressGridBean.getComponentId())) {
								monthlyProgressBean
										.setComponent(Integer.parseInt(monthlyProgressGridBean.getComponentId()));
							}
							if (MisUtility.ifEmpty(monthlyProgressGridBean.getVillageVisited())) {
								monthlyProgressBean.setVillageToBeVisited(
										Long.parseLong(monthlyProgressGridBean.getVillageVisited()));
							}
							if (MisUtility.ifEmpty(monthlyProgressGridBean.getTeamId())) {
								monthlyProgressBean.setTeamId(Integer.parseInt(monthlyProgressGridBean.getTeamId()));
							}
							if (MisUtility.ifEmpty(monthlyProgressGridBean.getPlanning())) {
								monthlyProgressBean.setPlanning(Long.parseLong(monthlyProgressGridBean.getPlanning()));
							}
							if (MisUtility.ifEmpty(monthlyProgressGridBean.getImplementation())) {
								monthlyProgressBean
										.setImplementaion(Long.parseLong(monthlyProgressGridBean.getImplementation()));
							}
							if (MisUtility.ifEmpty(monthlyProgressGridBean.getPostImplementation())) {
								monthlyProgressBean.setPostimplementaion(
										Long.parseLong(monthlyProgressGridBean.getPostImplementation()));
							}
							monthlyProgressBean.setCrtByUsr(Long.parseLong(String.valueOf(enterBy)));
							monthlyProgressBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

							monthlyProgressBeans.add(monthlyProgressBean);
						}
					}
				}

			}

			monthlyProgressDtos = monthlyProgressForm.getMonthlyProgressDtos();
			if (!MisUtility.ifEmpty(monthlyProgressDtos)) {
				for (MonthlyProgressDto monthlyProgressDto : monthlyProgressDtos) {
					MonthlyProgressBean monthlyProgressBean = new MonthlyProgressBean();
					monthlyProgressBean
							.setMonthlyProgressId(Integer.parseInt(monthlyProgressDto.getMonthlyProgressId()));
					/*
					 * if(MisUtility.ifEmpty(monthlyProgressForm.getYearPlan()))
					 * { monthlyProgressBean.setYearlyPlanId(Integer.parseInt(
					 * monthlyProgressForm.getYearPlan())); }
					 */
					
					if (MisUtility.ifEmpty(monthlyProgressForm.getMonth())) {
						monthlyProgressBean.setMonthlyPlanId(Integer.parseInt(monthlyProgressDto.getMonthlyPlanId()));
					}
					if (MisUtility.ifEmpty(monthlyProgressDto.getComponentId())) {
						monthlyProgressBean.setComponent(Integer.parseInt(monthlyProgressDto.getComponentName()));
					}
					if (MisUtility.ifEmpty(monthlyProgressDto.getVillageToBeVisited())) {
						monthlyProgressBean
								.setVillageToBeVisited(Long.parseLong(monthlyProgressDto.getVillageToBeVisited()));
					}
					if (MisUtility.ifEmpty(monthlyProgressDto.getTeamId())) {
						monthlyProgressBean.setTeamId(Integer.parseInt(monthlyProgressDto.getTeamName()));
					}
					if (MisUtility.ifEmpty(monthlyProgressDto.getPlanning())) {
						monthlyProgressBean.setPlanning(Long.parseLong(monthlyProgressDto.getPlanning()));
					}
					if (MisUtility.ifEmpty(monthlyProgressDto.getImplementation())) {
						monthlyProgressBean.setImplementaion(Long.parseLong(monthlyProgressDto.getImplementation()));
					}
					if (MisUtility.ifEmpty(monthlyProgressDto.getPostimplementaion())) {
						monthlyProgressBean
								.setPostimplementaion(Long.parseLong(monthlyProgressDto.getPostimplementaion()));
					}
					monthlyProgressBean.setCrtByUsr(Long.parseLong(String.valueOf(enterBy)));
					monthlyProgressBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

					monthlyProgressBeans.add(monthlyProgressBean);
				}
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return monthlyProgressBeans;
	}
}
