package com.prwss.min.RTI.Struts;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.RTI.bean.AssignRTIDetailsDto;
import com.prwss.min.RTI.bean.AssignRtiDto;
import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.bo.AssignRTIBo;
import com.prwss.min.RTI.dao.AssignRTIDao;
import com.prwss.min.RTI.form.AssignRtiForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.admin.dao.DesignationBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class AssignRtiOnlineAction extends DispatchAction {

	private Logger log = Logger.getLogger(AssignRtiOnlineAction.class);

	List<SubmitRtiBean> RTI_DB = null;

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;

	public AssignRTIBo assignRtiBo;

	public AssignRTIDao assignRtiDao;

	public AssignRTIDao getAssignRtiDao() {
		return assignRtiDao;
	}

	public void setAssignRtiDao(AssignRTIDao assignRtiDao) {
		this.assignRtiDao = assignRtiDao;
	}

	public AssignRTIBo getAssignRtiBo() {
		return assignRtiBo;
	}

	public void setAssignRtiBo(AssignRTIBo assignRtiBo) {
		this.assignRtiBo = assignRtiBo;
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
		// SubmitRtiForm RtiForm = (SubmitRtiForm) form;
		this.setAttrib(request);

		System.out.println("Unspecified........ Assign RTI Submit");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "locationName"); */
		/* request.setAttribute("d__auto", "locationName"); */
	}

	// getRtiData
	@SuppressWarnings("unused")
	public ActionForward getRtiData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			List<AssignRtiDto> rtiBean = null;

			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			String searchParameter = request.getParameter("sSearch");

			rtiBean = assignRtiBo.populateRTIDetail(searchParameter, clickedColumn, clickedColumnDir);
			LocationJsonObject<AssignRtiDto> locationJson = new LocationJsonObject<AssignRtiDto>();

			if (MisUtility.ifEmpty(rtiBean)) {
				locationJson.setAaData(new ArrayList<AssignRtiDto>());
			}
			if (!MisUtility.ifEmpty(rtiBean)) {
				locationJson.setiTotalDisplayRecords(rtiBean.size());
				locationJson.setiTotalRecords(rtiBean.size());
				locationJson.setAaData(rtiBean);
			}

			// Pagination not working
			/*
			 * if (!MisUtility.ifEmpty(rtiBean)) { rtiBean =
			 * assignRtiBo.getListBasedOnPageNumber(rtiBean, pageDisplayLength,
			 * pageNumber, iDisplayStart); locationJson.setAaData(rtiBean); }
			 */

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

	public ActionForward getRTIDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<AssignRTIDetailsDto> AssignRtiDetailsDto = null;
		try {
			String RtiId = request.getParameter("Id");

			if (MisUtility.ifEmpty(RtiId)) {
				AssignRtiDetailsDto = assignRtiDao.getRTIDetails(RtiId);

				// request.getSession().setAttribute("beneficiaryEntryBeans",
				// beneficiaryEntryDetailsDto);
				System.out.println("-----1-------" + AssignRtiDetailsDto.get(0));

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(AssignRtiDetailsDto.get(0));
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);

			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}

	// getDesignation
	public ActionForward getDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<DesignationBean> designations = null;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("<option value='' selected>");
			buffer.append("Select Designation");
			buffer.append("</option>");
			designations = assignRtiDao.getAllDesignation();
			for (DesignationBean DesignationBeans : designations) {
				buffer.append("<option value=\"").append(DesignationBeans.getDesignation_id()).append("\">");
				buffer.append(DesignationBeans.getDesig_name());
				buffer.append("</option>");
			}

			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(buffer);

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}

	// getEmployee
	public ActionForward getEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<EmployeeBean> designations = null;
		try {

			StringBuffer buffer = new StringBuffer();
			String RtiId = request.getParameter("designationId");
			System.out.println(RtiId);

			buffer.append("<option value='' selected>");
			buffer.append("Select Employee");
			buffer.append("</option>");
			designations = assignRtiDao.getAllEmployee(RtiId);
			for (EmployeeBean DesignationBeans : designations) {
				buffer.append("<option value=\"").append(DesignationBeans.getEmployeeId()).append("\">");
				buffer.append(DesignationBeans.getEmployeeName());
				buffer.append("</option>");
			}

			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(buffer);

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}

	// saveRTIAssignDetails

	// saveRTIAssignDetails
	public ActionForward saveRTIAssignDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		System.out.println("---------------------inside update-----------------------------");
		boolean status = false;
		AssignRtiForm RtiForm = (AssignRtiForm) form;
		FileOutputStream outputStream = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			setAttrib(request);

			String designation = request.getParameter("designation");
			String employee = request.getParameter("employee");
			String dueDate = request.getParameter("dueDate");
			String assignRemarks = request.getParameter("assignRemarks");
			String rtiID = request.getParameter("rtiID");

			System.out.println("Designation" + designation);
			System.out.println("employee" + employee);
			System.out.println("dueDate" + dueDate);
			System.out.println("assignRemarks" + assignRemarks);
			System.out.println("rtiID" + rtiID);

			AssignRtiForm assignRtiForm = new AssignRtiForm();
			assignRtiForm.setDesignation(request.getParameter("designation"));
			assignRtiForm.setEmployee(request.getParameter("employee"));
			assignRtiForm.setDueDate(request.getParameter("dueDate"));
			assignRtiForm.setAssignRemarks(request.getParameter("assignRemarks"));
			assignRtiForm.setRtiID(request.getParameter("rtiID"));
			if (MisUtility.ifEmpty(assignRtiForm)) {

				status = assignRtiBo.saveRTIUpdateDetails(assignRtiForm);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Respose Saved Successfully.");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

				refreshRtiForm(RtiForm);

			} else {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Form Failed to Save.");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				// refreshBeneficiaryForm(beneficiaryForm);
			}
		} catch (MISException e) {
			request.setAttribute("level2", "true");

			if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("invalide.file.extension", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

		} finally {
			if (outputStream != null)
				outputStream.close();
		}

		System.out.println("----------------Exit RTI  save  --------------------");
		return mapping.findForward("display");
	}

	private void refreshRtiForm(AssignRtiForm rtiForm) {

		rtiForm.setAssignRemarks(null);
		rtiForm.setDesignation(null);
		rtiForm.setDueDate(null);
		rtiForm.setEmployee(null);

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public ActionForward downloadApplicationFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<UpdateRTIDto> updateRTIDto = null;
		try {
			System.out.println("id------------------" + request.getParameter("rtiID"));
			if (MisUtility.ifEmpty(request.getParameter("rtiID"))) {
				updateRTIDto = assignRtiDao.getAttachmentData(request.getParameter("rtiID"));
				if (!MisUtility.ifEmpty(updateRTIDto)) {

					for (UpdateRTIDto dto : updateRTIDto) {
						byte[] attachment = dto.getApplicationFile();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition",
								"attachment; filename=" + dto.getApplicationFileName());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(attachment.length);

						InputStream in = new ByteArrayInputStream(attachment);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[attachment.length];
						while (in.read(outputByte, 0, attachment.length) != -1) {
							out.write(outputByte, 0, attachment.length);
						}
						in.close();
						out.flush();
						out.close();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward downloadDocumentsFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<UpdateRTIDto> updateRTIDto = null;
		try {
			System.out.println("id------------------" + request.getParameter("rtiID"));
			if (MisUtility.ifEmpty(request.getParameter("rtiID"))) {
				updateRTIDto = assignRtiDao.getAttachmentData(request.getParameter("rtiID"));
				if (!MisUtility.ifEmpty(updateRTIDto)) {

					for (UpdateRTIDto dto : updateRTIDto) {
						byte[] attachment = dto.getDocumentsFile();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + dto.getDocumentsFileName());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(attachment.length);

						InputStream in = new ByteArrayInputStream(attachment);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[attachment.length];
						while (in.read(outputByte, 0, attachment.length) != -1) {
							out.write(outputByte, 0, attachment.length);
						}
						in.close();
						out.flush();
						out.close();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
