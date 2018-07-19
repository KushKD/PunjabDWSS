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
import com.prwss.min.construction.quality.bean.EmployeeDetailsGridBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.SchemeGridBean;
import com.prwss.min.construction.quality.bo.ShareObservationBo;
import com.prwss.min.construction.quality.dao.ShareObservationDao;
import com.prwss.min.construction.quality.form.ShareObservationForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class ShareObservationAction extends DispatchAction {

	private Logger log = Logger.getLogger(ShareObservationAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ShareObservationDao shareObservationDao;
	private ShareObservationBo shareObservationBo;
	
	public ShareObservationDao getShareObservationDao() {
		return shareObservationDao;
	}
	public void setShareObservationDao(ShareObservationDao shareObservationDao) {
		this.shareObservationDao = shareObservationDao;
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
	
	
	public ShareObservationBo getShareObservationBo() {
		return shareObservationBo;
	}
	public void setShareObservationBo(ShareObservationBo shareObservationBo) {
		this.shareObservationBo = shareObservationBo;
	}
	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		boolean status = false;
		ShareObservationForm shareObservationForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			shareObservationForm = (ShareObservationForm) actionForm;

			if (MisUtility.ifEmpty(shareObservationForm)) {
				status = shareObservationBo.saveObservation(shareObservationForm,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved Sharing Observation");
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
		refereshMonthlyReportForm(shareObservationForm);
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
		
		ShareObservationForm shareObservationForm=(ShareObservationForm)form;
		this.setAttrib(request);
		
		refereshMonthlyReportForm(shareObservationForm);
		log.debug("Unspecified........ShareObservationAction.........");
		System.out.println("Unspecified........ShareObservationAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "yearPlan@month");
	}
	
	private void refereshMonthlyReportForm(ShareObservationForm shareObservationForm){
		shareObservationForm.setCheckedFor(null);
		shareObservationForm.setDateOfInspection(null);
		shareObservationForm.setDesignation(null);
		shareObservationForm.setEmployee(null);
		shareObservationForm.setMonth(null);
		shareObservationForm.setRemarks(null);
		shareObservationForm.setScheme(null);
		shareObservationForm.setSchemeStage(null);
		shareObservationForm.setSubject(null);
		shareObservationForm.setSchemeGrid(getSchemeDatagrid(null));
		shareObservationForm.setEmployeeDetailsGrid(getEmployeeDetailsGrid(null));
	}
	
	private Datagrid getSchemeDatagrid(List<String> schemeDataGrid) {
		Datagrid schemeDataGrid1 = null;
		List<SchemeGridBean> schemeGridBeans = new ArrayList<SchemeGridBean>();
		try {
			schemeDataGrid1 = Datagrid.getInstance();
			schemeDataGrid1.setDataClass(SchemeGridBean.class);
			schemeDataGrid1.setData(schemeGridBeans);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}

		return schemeDataGrid1;
	}
	
	private Datagrid getEmployeeDetailsGrid(List<String> employeeDetailsGrid) {
		Datagrid employeeDetailsGrid1 = null;
		List<EmployeeDetailsGridBean> schemeGridBeans = new ArrayList<EmployeeDetailsGridBean>();
		try {
			employeeDetailsGrid1 = Datagrid.getInstance();
			employeeDetailsGrid1.setDataClass(EmployeeDetailsGridBean.class);
			employeeDetailsGrid1.setData(schemeGridBeans);
		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}

		return employeeDetailsGrid1;
	}
	
	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		System.out.println("inside find");
		System.out.println("request.getParameter---scheme----------" + request.getParameter("scheme"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("scheme"))) {
				List<String> comments = shareObservationDao.getComments(request.getParameter("scheme"));
				if (!MisUtility.ifEmpty(comments)) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json2 = gson.toJson(comments);
					PrintWriter out = MisUtility.getPrintWriter(response);
					out.print(json2);
				}
			}
			
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
	public ActionForward getSuperintendingEngineer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchParentLocation");

		StringBuffer buffer = new StringBuffer();
		System.out.println("request.getParameter---collectionType----------" + request.getParameter("monthId"));
		try {

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

			if (MisUtility.ifEmpty(request.getParameter("monthId"))) {
				List<MonthlyPlanDto> empList = shareObservationDao.getEmployee(statusList,
						MISConstants.CHEMIST);
				if (!MisUtility.ifEmpty(empList)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select Employee");
					buffer.append("</option>");
					for (MonthlyPlanDto bean : empList) {
						buffer.append("<option value=\"").append(bean.getEmployeeId()).append("\">");
						buffer.append(bean.getEmployeeName());
						buffer.append("</option>");
					}
				}
			}else if(MisUtility.ifEmpty(request.getParameter("designation"))){
				List<MonthlyPlanDto> empList = shareObservationDao.getEmployee(statusList,
						request.getParameter("designation"));
				if (!MisUtility.ifEmpty(empList)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select Employee");
					buffer.append("</option>");
					for (MonthlyPlanDto bean : empList) {
						buffer.append("<option value=\"").append(bean.getEmployeeId()).append("\">");
						buffer.append(bean.getEmployeeName());
						buffer.append("</option>");
					}
				}
			}

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
			/*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
	
	public ActionForward fetchEmpDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchParentLocation");

		StringBuffer buffer = new StringBuffer();
		List<EmployeeDesignationBean> employeeBean = null;
		try {
			if (MisUtility.ifEmpty(request.getParameter("to"))) {
				employeeBean = shareObservationDao.getEmployeeDesig();
				if (!MisUtility.ifEmpty(employeeBean)) {

					buffer.append("<option value='' selected>");
					buffer.append("Select Designation");

					buffer.append("</option>");
					for (EmployeeDesignationBean bean : employeeBean) {
						System.out.println(bean.getDesignationId());
						buffer.append("<option value=\"").append(bean.getDesignationId()).append("\">");
						buffer.append(bean.getDesignationName());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
	public ActionForward fetchScheme(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Integer> schemeIds=null;
		List<MonthlyPlanDto> monthlyPlanDtos=null;
		StringBuffer buffer=new StringBuffer();
		try{
			if(MisUtility.ifEmpty(request.getParameter("yearlyPlanId"))&&MisUtility.ifEmpty(request.getParameter("monthId"))){
				schemeIds=shareObservationDao.getScheme(request.getParameter("yearlyPlanId"), request.getParameter("monthId"));
				if(!MisUtility.ifEmpty(schemeIds)){
					
					monthlyPlanDtos=shareObservationDao.getSchemeName(schemeIds);
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
