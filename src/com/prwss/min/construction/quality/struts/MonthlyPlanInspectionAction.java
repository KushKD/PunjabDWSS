/**
 * 
 */
package com.prwss.min.construction.quality.struts;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.prwss.min.construction.quality.bean.MonthlyPlanGridBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanSchemeMappingBean;
import com.prwss.min.construction.quality.bean.TeamMasterBean;
import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;
import com.prwss.min.construction.quality.bo.MonthlyPlanBo;
import com.prwss.min.construction.quality.dao.MonthlyPlanDao;
import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class MonthlyPlanInspectionAction extends DispatchAction {

	private Logger log = Logger.getLogger(MonthlyPlanInspectionAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MonthlyPlanBo monthlyPlanBo;
	private MonthlyPlanDao monthlyPlanDao;

	public MonthlyPlanDao getMonthlyPlanDao() {
		return monthlyPlanDao;
	}

	public void setMonthlyPlanDao(MonthlyPlanDao monthlyPlanDao) {
		this.monthlyPlanDao = monthlyPlanDao;
	}

	public MonthlyPlanBo getMonthlyPlanBo() {
		return monthlyPlanBo;
	}

	public void setMonthlyPlanBo(MonthlyPlanBo monthlyPlanBo) {
		this.monthlyPlanBo = monthlyPlanBo;
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

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
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
				status = monthlyPlanBo.saveMonthlyInspectionPlan(monthlyPlanInspectionForm,
						Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Added Plan for the financial year:" + "\t"+monthlyPlanInspectionForm.getYearlyPlanName());
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
		refreshYearlyInspectionForm(monthlyPlanInspectionForm);
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
		MonthlyPlanInspectionForm monthlyPlanInspectionForm = (MonthlyPlanInspectionForm) form;
		refreshYearlyInspectionForm(monthlyPlanInspectionForm);
		this.setAttrib(request);
		System.out.println("Unspecified........MonthlyPlanInspectionAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
	}

	private void refreshYearlyInspectionForm(MonthlyPlanInspectionForm monthlyPlanInspectionForm) {

		monthlyPlanInspectionForm.setComponent(null);
		monthlyPlanInspectionForm.setYearPlan(null);
		monthlyPlanInspectionForm.setConstituency(null);
		monthlyPlanInspectionForm.setScheme(null);
		monthlyPlanInspectionForm.setDistrict(null);
		monthlyPlanInspectionForm.setDivision(null);
		monthlyPlanInspectionForm.setCurrentMonthVisit(null);
		monthlyPlanInspectionForm.setMonth(null);
		monthlyPlanInspectionForm.setTeam(null);
		monthlyPlanInspectionForm.setMonthlyPlanGrid(getMonthlyPlanDatagrid(null));

	}

	private Datagrid getMonthlyPlanDatagrid(Set<MonthlyPlanSchemeMappingBean> monthlyPlanSchemeMappingBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(MonthlyPlanGridBean.class);
		List<MonthlyPlanGridBean> villageBeans = new ArrayList<MonthlyPlanGridBean>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}

	public ActionForward fetchVisitedVillagePerMonth(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		StringBuffer stringBuffer = new StringBuffer();
		List<YearlyPlanInspectionBean> yearlyPlanInspectionBeans = null;
		Set<YearlyPlanningComponentMappingBean> yearlyPlanningComponentMappingBeans = null;
		try {
			System.out.println("yearlyPlanId----------->" + request.getParameter("yearlyPlanId"));
			if (MisUtility.ifEmpty(request.getParameter("yearlyPlanId"))
					&& MisUtility.ifEmpty(request.getParameter("componentId"))) {
				yearlyPlanInspectionBeans = monthlyPlanDao.getPerMonthVisitedVillages(
						request.getParameter("yearlyPlanId"), request.getParameter("componentId"));

				stringBuffer.append("<option value='' selected>");
				stringBuffer.append("Select Villages Visited per Month");
				stringBuffer.append("</option>");
				if (!MisUtility.ifEmpty(yearlyPlanInspectionBeans)) {
					for (YearlyPlanInspectionBean yearlyPlanInspectionBean : yearlyPlanInspectionBeans) {
						yearlyPlanningComponentMappingBeans = yearlyPlanInspectionBean
								.getYearlyPlanningComponentMappingBeans();
						for (YearlyPlanningComponentMappingBean yearlyPlanningComponentMappingBean : yearlyPlanningComponentMappingBeans) {
							if (yearlyPlanningComponentMappingBean
									.getComponent() == (Integer.parseInt(request.getParameter("componentId")))) {
								stringBuffer.append("<option value=\"")
										.append(yearlyPlanningComponentMappingBean.getPlanComponentId()).append("\">");
								stringBuffer.append(yearlyPlanningComponentMappingBean.getVisitedPerMonth());
								stringBuffer.append("</option>");
							}
						}
					}
				}
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(stringBuffer.toString()) && stringBuffer.length() > 1) {
					out.print(stringBuffer.toString());
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward fetchTeam(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		StringBuffer stringBuffer = new StringBuffer();
		;
		List<TeamMasterBean> teamMasterBeans = null;
		try {
			System.out.println("yearlyPlanId----------->" + request.getParameter("financialYearlyId"));
			if (MisUtility.ifEmpty(request.getParameter("financialYearlyId"))) {
				teamMasterBeans = monthlyPlanDao.fetchTeam(request.getParameter("financialYearlyId"));

				stringBuffer.append("<option value='' selected>");
				stringBuffer.append("Select Team Name");
				stringBuffer.append("</option>");
				if (!MisUtility.ifEmpty(teamMasterBeans)) {
					for (TeamMasterBean teamMasterBean : teamMasterBeans) {
						stringBuffer.append("<option value=\"").append(teamMasterBean.getTeamId()).append("\">");
						stringBuffer.append(teamMasterBean.getTeam_name());
						stringBuffer.append("</option>");
					}
				}
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(stringBuffer.toString()) && stringBuffer.length() > 1) {
					out.print(stringBuffer.toString());
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
