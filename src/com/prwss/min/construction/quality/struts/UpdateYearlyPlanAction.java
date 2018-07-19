/**
 * 
 */
package com.prwss.min.construction.quality.struts;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;
import com.prwss.min.construction.quality.bo.UpdateYearPlanBo;
import com.prwss.min.construction.quality.dao.UpdateYearlyPlanDao;
import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class UpdateYearlyPlanAction extends DispatchAction {

	private Logger log = Logger.getLogger(UpdateYearlyPlanAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private UpdateYearPlanBo updateYearPlanBo;
	private UpdateYearlyPlanDao updateYearlyPlanDao;

	public UpdateYearPlanBo getUpdateYearPlanBo() {
		return updateYearPlanBo;
	}

	public void setUpdateYearPlanBo(UpdateYearPlanBo updateYearPlanBo) {
		this.updateYearPlanBo = updateYearPlanBo;
	}

	public UpdateYearlyPlanDao getUpdateYearlyPlanDao() {
		return updateYearlyPlanDao;
	}

	public void setUpdateYearlyPlanDao(UpdateYearlyPlanDao updateYearlyPlanDao) {
		this.updateYearlyPlanDao = updateYearlyPlanDao;
	}

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

	public ActionForward find(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("inside find-------->");
		YearlyPlanInspectionForm yearlyPlanInspectionForm = null;
		List<YearlyPlanInspectionBean> yearlyPlanInspectionBeans = null;
		Set<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBeans = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			yearlyPlanInspectionForm = (YearlyPlanInspectionForm) actionForm;

			if (MisUtility.ifEmpty(yearlyPlanInspectionForm)) {
				yearlyPlanInspectionBeans = updateYearlyPlanDao.getYearlyPlanDetails(yearlyPlanInspectionForm);

				System.out.println("yearlyPlanInspectionBeans----------->" + yearlyPlanInspectionBeans.toString());

				if (!MisUtility.ifEmpty(yearlyPlanInspectionBeans)) {
					for (YearlyPlanInspectionBean yearlyPlanInspectionBean : yearlyPlanInspectionBeans) {
						request.setAttribute("financialYear", yearlyPlanInspectionBean.getFinancialYear());
						request.setAttribute("externalAgency", yearlyPlanInspectionBean.getAssignedTo());
						request.setAttribute("inspectionType", yearlyPlanInspectionBean.getInspectionType());
						yearlyPlanInspectionForm
								.setYearPlanId(String.valueOf(yearlyPlanInspectionBean.getYearlyPlanId()));
						yearlyPlanningComponentMappingBeans = yearlyPlanInspectionBean
								.getYearlyPlanningComponentMappingBeans();
					}

					for (YearlyPlanningComponentMappingBean yearlyPlanningComponentMappingBean : yearlyPlanningComponentMappingBeans) {
						if (String.valueOf(yearlyPlanningComponentMappingBean.getComponent())
								.equalsIgnoreCase(yearlyPlanInspectionForm.getComponent())) {
							yearlyPlanInspectionForm.setPeriodIml(
									String.valueOf(yearlyPlanningComponentMappingBean.getPeriodOfImplementaion()));
							yearlyPlanInspectionForm.setNumberOfVillage(
									String.valueOf(yearlyPlanningComponentMappingBean.getTotalNumberVillage()));
							yearlyPlanInspectionForm.setVisitVillage(
									String.valueOf(yearlyPlanningComponentMappingBean.getVillageToBeVisited()));
							yearlyPlanInspectionForm.setVisitPerVillage(
									String.valueOf(yearlyPlanningComponentMappingBean.getVisitPerVillage()));
							yearlyPlanInspectionForm
									.setTotalVisit(String.valueOf(yearlyPlanningComponentMappingBean.getTotalVisit()));
							yearlyPlanInspectionForm.setTotalDuration(
									String.valueOf(yearlyPlanningComponentMappingBean.getTotalDuration()));
							yearlyPlanInspectionForm.setPerMonthVisit(
									String.valueOf(yearlyPlanningComponentMappingBean.getVisitedPerMonth()));
							request.setAttribute("component", yearlyPlanningComponentMappingBean.getComponent());
							yearlyPlanInspectionForm.setComponentPlanId(
									String.valueOf(yearlyPlanningComponentMappingBean.getPlanComponentId()));
							yearlyPlanInspectionForm.setComments(yearlyPlanningComponentMappingBean.getComments());
						}
					}
					// yearlyPlanInspectionForm=populateYearlyInspectionPlan(yearlyPlanInspectionBeans);
				}
			}
			request.setAttribute("level2", "true");
			if (!MisUtility.ifEmpty(yearlyPlanInspectionBeans)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.find", "Successfully find Record");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				refreshYearlyInspectionForm(yearlyPlanInspectionForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "No Record Found");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			refreshYearlyInspectionForm(yearlyPlanInspectionForm);
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
		YearlyPlanInspectionForm yearlyPlanInspectionForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			yearlyPlanInspectionForm = (YearlyPlanInspectionForm) actionForm;

			if (MisUtility.ifEmpty(yearlyPlanInspectionForm)) {
				status = updateYearPlanBo.updateYearPlan(yearlyPlanInspectionForm);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Update Plan for Finacial Year:" + "\t" + yearlyPlanInspectionForm.getFinancialYearName());
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
		refreshYearlyInspectionForm(yearlyPlanInspectionForm);
		return mapping.findForward("display");
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
		YearlyPlanInspectionForm yearlyPlanInspectionForm = (YearlyPlanInspectionForm) form;
		refreshYearlyInspectionForm(yearlyPlanInspectionForm);
		this.setAttrib(request);
		System.out.println("Unspecified........YearlyPlanInspectionAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
	}

	private void refreshYearlyInspectionForm(YearlyPlanInspectionForm yearlyPlanInspectionForm) {

		yearlyPlanInspectionForm.setAssignedTo(null);
		yearlyPlanInspectionForm.setComments(null);
		yearlyPlanInspectionForm.setComponent(null);
		yearlyPlanInspectionForm.setComponentName(null);
		yearlyPlanInspectionForm.setFinalizationDate(null);
		yearlyPlanInspectionForm.setFinancialYear(null);
		yearlyPlanInspectionForm.setFinancialYearName(null);
		yearlyPlanInspectionForm.setInspectionType(null);
		yearlyPlanInspectionForm.setNumberOfVillage(null);
		yearlyPlanInspectionForm.setPerMonthVisit(null);
		yearlyPlanInspectionForm.setTotalDuration(null);
		yearlyPlanInspectionForm.setTotalVisit(null);
		yearlyPlanInspectionForm.setVisitPerVillage(null);
		yearlyPlanInspectionForm.setVisitVillage(null);
		yearlyPlanInspectionForm.setPlanName(null);
		yearlyPlanInspectionForm.setPeriodIml(null);
	}

}
