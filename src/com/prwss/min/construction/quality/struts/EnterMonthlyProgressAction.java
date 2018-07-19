/**
 * 
 */
package com.prwss.min.construction.quality.struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressDto;
import com.prwss.min.construction.quality.bean.MonthlyProgressGridBean;
import com.prwss.min.construction.quality.bo.MonthlyProgressBo;
import com.prwss.min.construction.quality.dao.MonthlyProgressDao;
import com.prwss.min.construction.quality.form.MonthlyProgressForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class EnterMonthlyProgressAction extends DispatchAction {

	private Logger log = Logger.getLogger(EnterMonthlyProgressAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MonthlyProgressBo monthlyProgressBo;
	private MonthlyProgressDao monthlyProgressDao;

	
	public MonthlyProgressDao getMonthlyProgressDao() {
		return monthlyProgressDao;
	}

	public void setMonthlyProgressDao(MonthlyProgressDao monthlyProgressDao) {
		this.monthlyProgressDao = monthlyProgressDao;
	}

	public MonthlyProgressBo getMonthlyProgressBo() {
		return monthlyProgressBo;
	}

	public void setMonthlyProgressBo(MonthlyProgressBo monthlyProgressBo) {
		this.monthlyProgressBo = monthlyProgressBo;
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

		System.out.println("<------inside find-------->");
		this.setAttrib(request);
		MonthlyProgressForm monthlyProgressForm = null;
		List<MonthlyProgressBean> monthlyProgressBeans = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			monthlyProgressForm = (MonthlyProgressForm) actionForm;

			if (MisUtility.ifEmpty(monthlyProgressForm)) {
				monthlyProgressBeans = monthlyProgressDao.pupolateMonthlyProgressBean(monthlyProgressForm);

				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
					request.setAttribute("yearlyPlan", monthlyProgressForm.getYearPlan());
					request.setAttribute("month", monthlyProgressForm.getMonth());
					request.setAttribute("monthlyProgressBeans", monthlyProgressForm);
					monthlyProgressForm.setMonthlyProgressDtos(getMonthlyProgressDto(monthlyProgressBeans));
					//monthlyProgressForm.setMonthlyProgressGrid(getMonthlyProgressDatagrid(monthlyProgressBeans));
					request.setAttribute("monthlyProgressDtos", monthlyProgressForm);
				}
			}
			request.setAttribute("level2", "true");
			if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
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

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		boolean status = false;
		MonthlyProgressForm monthlyProgressForm = null;
		StringBuffer stringBuffer = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			monthlyProgressForm = (MonthlyProgressForm) actionForm;

			if (MisUtility.ifEmpty(monthlyProgressForm)) {
				if (!MisUtility.ifEmpty(monthlyProgressForm.getMonthlyProgressGrid().getAddedData().size())) {
					stringBuffer = new StringBuffer();
					throw new MISException(MISExceptionCodes.MIS012,
							stringBuffer.append("Please add at least one monthly progress").toString());
				}
				status = monthlyProgressBo.save(monthlyProgressForm,
						Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Added Plan:" + "\t" + monthlyProgressForm.getYearPlan());
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
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
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
		refreshMonthlyProgressForm(monthlyProgressForm);
		return mapping.findForward("display");
	}

	
	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		boolean status = false;
		MonthlyProgressForm monthlyProgressForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			monthlyProgressForm = (MonthlyProgressForm) actionForm;

			if (MisUtility.ifEmpty(monthlyProgressForm)) {
				status = monthlyProgressBo.update(monthlyProgressForm,
						Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Added Plan:" + "\t" + monthlyProgressForm.getYearPlan());
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
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
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
		refreshMonthlyProgressForm(monthlyProgressForm);
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
		MonthlyProgressForm monthlyProgressForm = (MonthlyProgressForm) form;
		this.setAttrib(request);
		refreshMonthlyProgressForm(monthlyProgressForm);
		System.out.println("Unspecified........MonthlyPlanInspectionAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "yearPlan@month");
	}

	private void refreshMonthlyProgressForm(MonthlyProgressForm monthlyProgressForm) {
		monthlyProgressForm.setComponent(null);
		monthlyProgressForm.setYearPlan(null);
		monthlyProgressForm.setMonth(null);
		monthlyProgressForm.setPlanning(null);
		monthlyProgressForm.setImplementation(null);
		monthlyProgressForm.setVillageToBeVisit(null);
		monthlyProgressForm.setPostImplementation(null);
		monthlyProgressForm.setMonthlyProgressGrid(getMonthlyProgressDatagrid(null));
	}

	private Datagrid getMonthlyProgressDatagrid(List<MonthlyProgressBean> monthlyProgressBeans) {
		Datagrid monthlyDatagrid = null;
		List<MonthlyProgressGridBean> monthlyProgressGridBeans = new ArrayList<MonthlyProgressGridBean>();
		try {
			monthlyDatagrid = Datagrid.getInstance();
			monthlyDatagrid.setDataClass(MonthlyProgressGridBean.class);
			monthlyDatagrid.setData(monthlyProgressGridBeans);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}

		return monthlyDatagrid;
	}
	private List<MonthlyProgressDto> getMonthlyProgressDto(List<MonthlyProgressBean> monthlyProgressBeans){
		
		List<MonthlyProgressDto> monthlyProgressDtos=null;
		try{
			if(!MisUtility.ifEmpty(monthlyProgressBeans)){
				monthlyProgressDtos=new ArrayList<MonthlyProgressDto>();
				for (MonthlyProgressBean monthlyProgressBean : monthlyProgressBeans) {
					MonthlyProgressDto monthlyProgressDto = new MonthlyProgressDto();
					monthlyProgressDto.setMonthlyProgressId(String.valueOf(monthlyProgressBean.getMonthlyProgressId()));
					monthlyProgressDto.setMonthlyPlanId(String.valueOf(monthlyProgressBean.getMonthlyPlanId()));
					monthlyProgressDto.setComponentId(String.valueOf(monthlyProgressBean.getComponent()));
					monthlyProgressDto
							.setComponentName(monthlyProgressBean.getCombodetailComponent().getCmb_name());
					monthlyProgressDto
							.setVillageToBeVisited(String.valueOf(monthlyProgressBean.getVillageToBeVisited()));
					monthlyProgressDto.setTeamId(monthlyProgressBean.getTeamId().toString());
					monthlyProgressDto.setTeamName(monthlyProgressBean.getTeamMasterBean().getTeam_name());
					monthlyProgressDto.setPlanning(monthlyProgressBean.getPlanning().toString());
					monthlyProgressDto.setImplementation(monthlyProgressBean.getImplementaion().toString());
					monthlyProgressDto
							.setPostimplementaion(monthlyProgressBean.getPostimplementaion().toString());
					monthlyProgressDtos.add(monthlyProgressDto);
				}
			}
		}catch(Exception e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return monthlyProgressDtos;
		
	}

	public ActionForward fetchVisitedVillage(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		StringBuffer stringBuffer = new StringBuffer();
		List<Integer> monthlyPlanInspectionBeans = null;
		try {
			System.out.println("yearlyPlanId----------->" + request.getParameter("yearlyPlanId"));
			if (MisUtility.ifEmpty(request.getParameter("yearlyPlanId"))
					&& MisUtility.ifEmpty(request.getParameter("componentId"))
					&& MisUtility.ifEmpty(request.getParameter("monthId"))) {
				monthlyPlanInspectionBeans = monthlyProgressDao.getPerVisitedVillages(
						request.getParameter("yearlyPlanId"), request.getParameter("componentId"),
						request.getParameter("monthId"), request.getParameter("teamId"));

				if (!MisUtility.ifEmpty(monthlyPlanInspectionBeans)) {
					for (Integer monthlyPlanInspectionBean : monthlyPlanInspectionBeans) {
						System.out.println("monthlyPlanInspectionBean.getVillageToBeVisited()-------->"
								+ monthlyPlanInspectionBean);
						stringBuffer.append(monthlyPlanInspectionBean);
						break;
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
