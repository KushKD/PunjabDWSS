/**
 * 
 */
package com.prwss.min.construction.quality.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanSchemeMappingBean;
import com.prwss.min.construction.quality.bo.MonthlyPlanBo;
import com.prwss.min.construction.quality.dao.MonthlyPlanDao;
import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import jxl.common.Logger;

/**
 * @author BH390738
 *
 */
public class UpdateMonthlyPlanAction extends DispatchAction {

	private Logger log = Logger.getLogger(UpdateMonthlyPlanAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MonthlyPlanBo monthlyPlanBo;
	private MonthlyPlanDao monthlyPlanDao;

	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	public MonthlyPlanBo getMonthlyPlanBo() {
		return monthlyPlanBo;
	}

	public void setMonthlyPlanBo(MonthlyPlanBo monthlyPlanBo) {
		this.monthlyPlanBo = monthlyPlanBo;
	}

	public MonthlyPlanDao getMonthlyPlanDao() {
		return monthlyPlanDao;
	}

	public void setMonthlyPlanDao(MonthlyPlanDao monthlyPlanDao) {
		this.monthlyPlanDao = monthlyPlanDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("<------inside find-------->");
		MonthlyPlanInspectionForm monthlyPlanInspectionForm = null;
		List<MonthlyPlanInspectionBean> monthlyPlanInspectionBeans = null;
		List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans = null;
		List<Integer> yearlyPlanId = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			monthlyPlanInspectionForm = (MonthlyPlanInspectionForm) actionForm;

			if (MisUtility.ifEmpty(monthlyPlanInspectionForm)) {

				yearlyPlanId = monthlyPlanDao.findYeralyPlan(monthlyPlanInspectionForm.getYearPlan(),
						monthlyPlanInspectionForm.getComponent());
				if (!MisUtility.ifEmpty(yearlyPlanId)) {
					monthlyPlanInspectionBeans = monthlyPlanDao
							.fetchMonthlyPlanSchemeMapping(yearlyPlanId.get(0),monthlyPlanInspectionForm);

					if (!MisUtility.ifEmpty(monthlyPlanInspectionBeans)) {
						
						request.setAttribute("yearlyPlan", monthlyPlanInspectionForm.getYearPlan());
						request.setAttribute("component", monthlyPlanInspectionForm.getComponent());
						request.setAttribute("month", monthlyPlanInspectionForm.getMonth());
						request.setAttribute("team", monthlyPlanInspectionForm.getTeam());

						monthlyPlanSchemeMappingBeans = new ArrayList<MonthlyPlanSchemeMappingBean>();
						for (MonthlyPlanInspectionBean monthlyPlanInspectionBean : monthlyPlanInspectionBeans) {
							request.setAttribute("perMonthhVisit", monthlyPlanInspectionBean.getTotalNumberVillage());
							monthlyPlanInspectionForm
									.setMonthlyPlanId(String.valueOf(monthlyPlanInspectionBean.getMonthlyPlanId()));
							monthlyPlanInspectionForm.setCurrentMonthVisit(
									String.valueOf(monthlyPlanInspectionBean.getVillageToBeVisited()));

							monthlyPlanSchemeMappingBeans
									.addAll(monthlyPlanInspectionBean.getMonthlyPlanSchemeMappingBeans());
							List<MonthlyPlanDto> monthlyPlanDtos = getMonthlyPlanDto(monthlyPlanSchemeMappingBeans);
							monthlyPlanInspectionForm.setMonthlyPlanDtos(monthlyPlanDtos);
							request.setAttribute("monthlyPlanDtos", monthlyPlanInspectionForm);
						}
						request.setAttribute("finacialYear", monthlyPlanInspectionForm.getYearPlan());
						request.setAttribute("component", monthlyPlanInspectionForm.getComponent());
						request.setAttribute("month", monthlyPlanInspectionForm.getMonth());
						request.setAttribute("team", monthlyPlanInspectionForm.getTeam());
					}
				}
				System.out.println("<-------------------monthlyPlanInspectionBeans----------->"
						+ monthlyPlanInspectionBeans.toString());
			}
			request.setAttribute("level2", "true");
			if (!MisUtility.ifEmpty(monthlyPlanInspectionBeans)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.find", "Successfully find Record");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "No Record Found");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		// refreshYearlyInspectionForm(yearlyPlanInspectionForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		boolean status = false;
		MonthlyPlanInspectionForm monthlyPlanInspectionForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			monthlyPlanInspectionForm = (MonthlyPlanInspectionForm) actionForm;

			if (MisUtility.ifEmpty(monthlyPlanInspectionForm)) {
				status = monthlyPlanBo.updateMonthlyInspectionPlan(monthlyPlanInspectionForm,
						Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Update Plan for Finacial Year:" + "\t" + monthlyPlanInspectionForm.getYearPlan());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error please check logs..");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}

	private List<MonthlyPlanDto> getMonthlyPlanDto(List<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans) {
		List<MonthlyPlanDto> monthlyPlanDtos = null;
		try {

			if (!MisUtility.ifEmpty(monthlyPlanSchemeMappingBeans)) {
				monthlyPlanDtos = new ArrayList<MonthlyPlanDto>();
				for (MonthlyPlanSchemeMappingBean monthlyPlanSchemeMappingBean : monthlyPlanSchemeMappingBeans) {

					MonthlyPlanDto monthlyPlanDto = new MonthlyPlanDto();

					monthlyPlanDto.setConstituency(String.valueOf(monthlyPlanSchemeMappingBean.getConstituency()));
					monthlyPlanDto.setConstituencyId(String.valueOf(monthlyPlanSchemeMappingBean.getConstituency()));

					monthlyPlanDto.setDistrict(String.valueOf(monthlyPlanSchemeMappingBean.getDistrict()));
					monthlyPlanDto.setDistrictId(String.valueOf(monthlyPlanSchemeMappingBean.getDistrict()));

					monthlyPlanDto.setDivision(String.valueOf(monthlyPlanSchemeMappingBean.getDivision()));
					monthlyPlanDto.setDivisionId(String.valueOf(monthlyPlanSchemeMappingBean.getDivision()));

					monthlyPlanDto.setScheme(String.valueOf(monthlyPlanSchemeMappingBean.getScheme_id()));
					monthlyPlanDto.setSchemeId(String.valueOf(monthlyPlanSchemeMappingBean.getScheme_id()));

					monthlyPlanDto.setMonthly_plan_scheme_id(
							String.valueOf(monthlyPlanSchemeMappingBean.getMonthly_plan_scheme_id()));
					monthlyPlanDto
							.setMonthly_plan_id(String.valueOf(monthlyPlanSchemeMappingBean.getMonthly_plan_id()));
					monthlyPlanDtos.add(monthlyPlanDto);
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return monthlyPlanDtos;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		System.out.println("Unspecified........UpdateMonthlyPlanInspectionAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
	}

}
