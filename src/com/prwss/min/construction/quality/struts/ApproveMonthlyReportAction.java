/**
 * 
 */
package com.prwss.min.construction.quality.struts;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
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
import com.prwss.min.construction.quality.bean.MonthlyReportDto;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;
import com.prwss.min.construction.quality.bo.SendMonthlyReportBo;
import com.prwss.min.construction.quality.dao.MonthlyProgressDao;
import com.prwss.min.construction.quality.dao.SendMonthlyReportDao;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ApproveMonthlyReportAction extends DispatchAction {
	
	private Logger log = Logger.getLogger(ApproveMonthlyReportAction.class);

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
	
	public ActionForward approve(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
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

				status = sendMonthlyReportBo.approveReport(sendMonthlyReportForm,
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
	public ActionForward downloadReport(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws Exception, IOException {
		
		List<MonthlyProgressBean> monthlyProgressBeans = null;
		try {
			System.out.println("monthlyPlanId------------------" + request.getParameter("monthlyPlanId"));
			if (MisUtility.ifEmpty(request.getParameter("monthlyPlanId"))) {
				monthlyProgressBeans = monthlyProgressDao.getMonthlyProgress(Integer.parseInt(request.getParameter("monthlyPlanId")));
				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {

					for (MonthlyProgressBean monthlyProgressBean : monthlyProgressBeans) {
						System.out.println("-----shdddddddddddddddd-------------");
						byte[] report = monthlyProgressBean.getReport();
						
						System.out.println("report.length--------->"+report.length);
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + monthlyProgressBean.getReportName());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(report.length);

						InputStream in = new ByteArrayInputStream(report);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[report.length];
						while (in.read(outputByte, 0, report.length) != -1) {
							out.write(outputByte, 0, report.length);
						}
						in.close();
						out.flush();
					}
				}
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
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
	
	public ActionForward populate(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
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
				System.out.println("");
				monthlyProgressBeans = monthlyProgressDao.pupolateMonthlyProgressBean(sendMonthlyReportForm);
				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
					request.setAttribute("yearlyPlan", sendMonthlyReportForm.getYearPlan());
					request.setAttribute("month", sendMonthlyReportForm.getMonth());
					request.setAttribute("monthlyPlanId", monthlyProgressBeans.get(0).getMonthlyPlanId());
					request.setAttribute("reportName", monthlyProgressBeans.get(0).getReportName());
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

}
