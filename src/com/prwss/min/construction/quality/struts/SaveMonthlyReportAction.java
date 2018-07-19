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

import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressDto;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportGridBean;
import com.prwss.min.construction.quality.bo.SaveMonthlyReportBo;
import com.prwss.min.construction.quality.dao.MonthlyProgressDao;
import com.prwss.min.construction.quality.dao.SaveMonthlyReportDao;
import com.prwss.min.construction.quality.form.SaveMonthlyReportForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class SaveMonthlyReportAction extends DispatchAction {

	private Logger log = Logger.getLogger(SaveMonthlyReportAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MonthlyProgressDao monthlyProgressDao;
	private SaveMonthlyReportDao saveMonthlyReportDao;
	private SaveMonthlyReportBo saveMonthlyReportBo;
	
	public SaveMonthlyReportBo getSaveMonthlyReportBo() {
		return saveMonthlyReportBo;
	}
	public void setSaveMonthlyReportBo(SaveMonthlyReportBo saveMonthlyReportBo) {
		this.saveMonthlyReportBo = saveMonthlyReportBo;
	}
	public SaveMonthlyReportDao getSaveMonthlyReportDao() {
		return saveMonthlyReportDao;
	}
	public void setSaveMonthlyReportDao(SaveMonthlyReportDao saveMonthlyReportDao) {
		this.saveMonthlyReportDao = saveMonthlyReportDao;
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

	public MonthlyProgressDao getMonthlyProgressDao() {
		return monthlyProgressDao;
	}
	public void setMonthlyProgressDao(MonthlyProgressDao monthlyProgressDao) {
		this.monthlyProgressDao = monthlyProgressDao;
	}
	
	
	public ActionForward find(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("<------inside find-------->");
		
		this.setAttrib(request);
		SaveMonthlyReportForm saveMonthlyReportForm = null;
		List<MonthlyProgressBean> monthlyProgressBeans = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			saveMonthlyReportForm = (SaveMonthlyReportForm) actionForm;

			if (MisUtility.ifEmpty(saveMonthlyReportForm)) {
				monthlyProgressBeans = monthlyProgressDao.pupolateMonthlyProgressBean(saveMonthlyReportForm);

				if (!MisUtility.ifEmpty(monthlyProgressBeans)) {
					request.setAttribute("yearlyPlan", saveMonthlyReportForm.getYearPlan());
					request.setAttribute("month", saveMonthlyReportForm.getMonth());
					request.setAttribute("monthlyPlanId",monthlyProgressBeans.get(0).getMonthlyPlanId());
					saveMonthlyReportForm.setMonthlyProgressDtos(getMonthlyProgressDto(monthlyProgressBeans));
					request.setAttribute("monthlyProgressDtos", saveMonthlyReportForm);
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
		SaveMonthlyReportForm saveMonthlyReportForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			saveMonthlyReportForm = (SaveMonthlyReportForm) actionForm;

			if (MisUtility.ifEmpty(saveMonthlyReportForm)) {
				status = saveMonthlyReportBo.saveMonthlyReport(saveMonthlyReportForm,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
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
		refereshMonthlyReportForm(saveMonthlyReportForm);
		return mapping.findForward("display");
	}
	private List<MonthlyProgressDto> getMonthlyProgressDto(List<MonthlyProgressBean> monthlyProgressBeans){
		
		List<MonthlyProgressDto> monthlyProgressDtos=null;
		try{
			if(!MisUtility.ifEmpty(monthlyProgressBeans)){
				monthlyProgressDtos=new ArrayList<MonthlyProgressDto>();
				for (MonthlyProgressBean monthlyProgressBean : monthlyProgressBeans) {
					MonthlyProgressDto monthlyProgressDto = new MonthlyProgressDto();
					monthlyProgressDto.setMonthlyProgressId(String.valueOf(monthlyProgressBean.getMonthlyProgressId()));
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
		SaveMonthlyReportForm saveMonthlyReportForm = (SaveMonthlyReportForm) form;
		this.setAttrib(request);
		refereshMonthlyReportForm(saveMonthlyReportForm);
		System.out.println("Unspecified........SaveMonthlyReportAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "yearPlan@month");
	}
	
	private void refereshMonthlyReportForm(SaveMonthlyReportForm saveMonthlyReportForm){
		saveMonthlyReportForm.setCommentType(null);
		saveMonthlyReportForm.setMonth(null);
		saveMonthlyReportForm.setYearPlan(null);
		saveMonthlyReportForm.setObservation(null);
		saveMonthlyReportForm.setMonthlyProgressDtos(null);
		saveMonthlyReportForm.setSaveMonthlyReportGrid(getMonthlyReportDatagrid(null));
	}
	
	private Datagrid getMonthlyReportDatagrid(List<String> monthlyProgressBeans) {
		Datagrid monthlyDatagrid = null;
		List<SaveMonthlyReportGridBean> saveMonthlyReportGridBeans = new ArrayList<SaveMonthlyReportGridBean>();
		try {
			monthlyDatagrid = Datagrid.getInstance();
			monthlyDatagrid.setDataClass(SaveMonthlyReportGridBean.class);
			monthlyDatagrid.setData(saveMonthlyReportGridBeans);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}

		return monthlyDatagrid;
	}
	
	public ActionForward fetchScheme(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Long> schemeIds=null;
		List<Integer> schemeIdss=null;
		List<MonthlyPlanDto> monthlyPlanDtos=null;
		StringBuffer buffer=new StringBuffer();
		try{
			if(MisUtility.ifEmpty(request.getParameter("yearlyPlanId"))&&MisUtility.ifEmpty(request.getParameter("monthId"))){
				schemeIds=saveMonthlyReportDao.getScheme(request.getParameter("yearlyPlanId"), request.getParameter("monthId"));
				if(!MisUtility.ifEmpty(schemeIds)){
					schemeIdss=new ArrayList<Integer>();
					for(Long ll:schemeIds){
						schemeIdss.add(Integer.parseInt(String.valueOf(ll)));
					}
					monthlyPlanDtos=saveMonthlyReportDao.getSchemeName(schemeIdss);
					buffer.append("<option value='' selected>");
					buffer.append("Select Scheme");
					buffer.append("</option>");
					if(!MisUtility.ifEmpty(monthlyPlanDtos)){
						for(MonthlyPlanDto monthlyPlanDto:monthlyPlanDtos){
							buffer.append("<option value=\"").append(monthlyPlanDto.getSchmId()).append("\">");
							buffer.append(monthlyPlanDto.getSchemeName());
							buffer.append("</option>");
						}
					}
					PrintWriter out = response.getWriter();
					if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
						out.print(buffer.toString());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		
		return null;
	}
}
