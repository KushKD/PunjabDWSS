/**
 * 
 */
package com.prwss.min.construction.quality.struts;

import java.io.IOException;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressDto;
import com.prwss.min.construction.quality.bean.MonthlyReportDto;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;
import com.prwss.min.construction.quality.bo.SendMonthlyReportBo;
import com.prwss.min.construction.quality.dao.MonthlyProgressDao;
import com.prwss.min.construction.quality.dao.SendMonthlyReportDao;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class SendMonthlyReportAction extends DispatchAction {

	private Logger log = Logger.getLogger(SendMonthlyReportAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MonthlyProgressDao monthlyProgressDao;
	private SendMonthlyReportDao sendMonthlyReportDao;
	private SendMonthlyReportBo<MonthlyReportDto> sendMonthlyReportBo;

	public SendMonthlyReportBo<MonthlyReportDto> getSendMonthlyReportBo() {
		return sendMonthlyReportBo;
	}

	public void setSendMonthlyReportBo(SendMonthlyReportBo<MonthlyReportDto> sendMonthlyReportBo) {
		this.sendMonthlyReportBo = sendMonthlyReportBo;
	}

	public SendMonthlyReportDao getSendMonthlyReportDao() {
		return sendMonthlyReportDao;
	}

	public void setSendMonthlyReportDao(SendMonthlyReportDao sendMonthlyReportDao) {
		this.sendMonthlyReportDao = sendMonthlyReportDao;
	}

	public MonthlyProgressDao getMonthlyProgressDao() {
		return monthlyProgressDao;
	}

	public void setMonthlyProgressDao(MonthlyProgressDao monthlyProgressDao) {
		this.monthlyProgressDao = monthlyProgressDao;
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
		SendMonthlyReportForm sendMonthlyReportForm = null;
		List<MonthlyProgressBean> monthlyProgressBeans = null;
		List<SaveMonthlyReportObservationBean> saveMonthlyReportObservationBeans = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			sendMonthlyReportForm = (SendMonthlyReportForm) actionForm;

			if (MisUtility.ifEmpty(sendMonthlyReportForm)) {
				monthlyProgressBeans = monthlyProgressDao.pupolateMonthlyProgressBean(sendMonthlyReportForm);
				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
					
					request.setAttribute("yearlyPlan", sendMonthlyReportForm.getYearPlan());
					request.setAttribute("month", sendMonthlyReportForm.getMonth());
					request.setAttribute("monthlyPlanId", monthlyProgressBeans.get(0).getMonthlyPlanId());
					
					
					sendMonthlyReportForm.setMonthlyProgressDtos(getMonthlyProgressDto(monthlyProgressBeans));
					
					
					request.setAttribute("monthlyProgressDtos", sendMonthlyReportForm);
					saveMonthlyReportObservationBeans = sendMonthlyReportDao.pupolateObservation(sendMonthlyReportForm);
					if (!MisUtility.ifEmpty(saveMonthlyReportObservationBeans)) {
						for (SaveMonthlyReportObservationBean saveMonthlyReportObservationBean : saveMonthlyReportObservationBeans) {
							if (saveMonthlyReportObservationBean.getCommentType() == Integer
									.parseInt(MISConstants.PRE_IMPLEMENTATION)) {
								request.setAttribute("PRE_IMPLEMENTATION",
										saveMonthlyReportObservationBean.getObservation());
							} else if (saveMonthlyReportObservationBean.getCommentType() == Integer
									.parseInt(MISConstants.IMPLEMENTATION)) {
								request.setAttribute("IMPLEMENTATION",
										saveMonthlyReportObservationBean.getObservation());
							} else if (saveMonthlyReportObservationBean.getCommentType() == Integer
									.parseInt(MISConstants.POST_IMPLEMENTATION)) {
								request.setAttribute("POST_IMPLEMENTATION",
										saveMonthlyReportObservationBean.getObservation());
							} else if (saveMonthlyReportObservationBean.getCommentType() == Integer
									.parseInt(MISConstants.IMPORTANT_OBSERVATIONS)) {
								request.setAttribute("IMPORTANT_OBSERVATIONS",
										saveMonthlyReportObservationBean.getObservation());
							}
						}
					}
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
		SendMonthlyReportForm sendMonthlyReportForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			sendMonthlyReportForm = (SendMonthlyReportForm) actionForm;

			if (MisUtility.ifEmpty(sendMonthlyReportForm)) {

				if (!(sendMonthlyReportForm.getProgressFile().getFileName().endsWith(".docx")
						||sendMonthlyReportForm.getProgressFile().getFileName().endsWith(".docm")
						|| sendMonthlyReportForm.getProgressFile().getFileName().endsWith(".dotm")||sendMonthlyReportForm.getProgressFile().getFileName().endsWith(".pdf"))) {
					throw new MISException(MISExceptionCodes.MIS003,
							"Progress File Format should be of PDF/Word");
				}
				if (sendMonthlyReportForm.getProgressFile().getFileSize() > 512000) {
					throw new MISException(MISExceptionCodes.MIS003,
							"Progress File size should not be greater than 500 KB");
				}
				status = sendMonthlyReportBo.forwardReport(sendMonthlyReportForm,
						Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved Monthly Report");
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

	private List<MonthlyProgressDto> getMonthlyProgressDto(List<MonthlyProgressBean> monthlyProgressBeans) {

		List<MonthlyProgressDto> monthlyProgressDtos = null;
		try {
			if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
				monthlyProgressDtos = new ArrayList<MonthlyProgressDto>();
				for (MonthlyProgressBean monthlyProgressBean : monthlyProgressBeans) {
					MonthlyProgressDto monthlyProgressDto = new MonthlyProgressDto();
					monthlyProgressDto.setMonthlyProgressId(String.valueOf(monthlyProgressBean.getMonthlyProgressId()));
					monthlyProgressDto.setComponentId(String.valueOf(monthlyProgressBean.getComponent()));
					monthlyProgressDto.setComponentName(monthlyProgressBean.getCombodetailComponent().getCmb_name());
					monthlyProgressDto
							.setVillageToBeVisited(String.valueOf(monthlyProgressBean.getVillageToBeVisited()));
					monthlyProgressDto.setTeamId(monthlyProgressBean.getTeamId().toString());
					monthlyProgressDto.setTeamName(monthlyProgressBean.getTeamMasterBean().getTeam_name());
					monthlyProgressDto.setPlanning(monthlyProgressBean.getPlanning().toString());
					monthlyProgressDto.setImplementation(monthlyProgressBean.getImplementaion().toString());
					monthlyProgressDto.setPostimplementaion(monthlyProgressBean.getPostimplementaion().toString());

					monthlyProgressDtos.add(monthlyProgressDto);
				}
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return monthlyProgressDtos;

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
		System.out.println("Unspecified........SendMonthlyReportAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "yearPlan@month");
	}
	
	
	public ActionForward populateMonthlyReportPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		System.out.println("-------------------populateMonthlyPlan---------------");
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
				int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
				String clickedColumnDir = request.getParameter("sSortDir_0");

				Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				Integer pageNumber = 0;
				Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
				if (null != request.getParameter("iDisplayStart"))
					pageNumber = (iDisplayStart / pageDisplayLength) + 1;
				String searchParameter = request.getParameter("sSearch");
				
				//---------------------------------------------------------------------//
				
				List<MonthlyReportDto> monthlyPlanDtos = sendMonthlyReportDao.getMonthlyProgressData(misAuditBean.getEnteredBy(),
						searchParameter, clickedColumn, clickedColumnDir);
				
				//---------------------------------------------------------------------//
				
				LocationJsonObject<MonthlyReportDto> locationJson = new LocationJsonObject<MonthlyReportDto>();

				if (MisUtility.ifEmpty(monthlyPlanDtos)) {
					locationJson.setAaData(new ArrayList<MonthlyReportDto>());
				}
				if (!MisUtility.ifEmpty(monthlyPlanDtos)) {
					locationJson.setiTotalDisplayRecords(monthlyPlanDtos.size());
					locationJson.setiTotalRecords(monthlyPlanDtos.size());
					// locationJson.setAaData(beneficiaryDto);
				}
				List<MonthlyReportDto> monthlyPlanDtos2 = null;
				if (!MisUtility.ifEmpty(monthlyPlanDtos)) {
					monthlyPlanDtos2 = sendMonthlyReportBo.getListBasedOnPageNumber(monthlyPlanDtos, pageDisplayLength,
							pageNumber, iDisplayStart);
					locationJson.setAaData(monthlyPlanDtos2);
				}

				System.out.println("locationjson------------" + locationJson.toString());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(locationJson);
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
		} catch (Exception e) {
			log.debug(e.getMessage());

		}
		return null;
	}
}
