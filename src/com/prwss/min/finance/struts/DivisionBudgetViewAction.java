/**
 * 
 */
package com.prwss.min.finance.struts;

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
import org.springframework.dao.DataAccessException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.finance.bean.DivisionBudgetBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.DivisionBudgetBo;
import com.prwss.min.finance.dao.DivisionBudgetDao;
import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

/**
 * @author BH390738
 *
 */
public class DivisionBudgetViewAction extends DispatchAction {

	private Logger log = Logger.getLogger(DivisionBudgetAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private LocationDao locationDao;
	private DivisionBudgetDao divisionBudgetDao;
	private DivisionBudgetBo<FinanceDto> divisionBudgetBo;

	public DivisionBudgetBo<FinanceDto> getDivisionBudgetBo() {
		return divisionBudgetBo;
	}

	public void setDivisionBudgetBo(DivisionBudgetBo<FinanceDto> divisionBudgetBo) {
		this.divisionBudgetBo = divisionBudgetBo;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public DivisionBudgetDao getDivisionBudgetDao() {
		return divisionBudgetDao;
	}

	public void setDivisionBudgetDao(DivisionBudgetDao divisionBudgetDao) {
		this.divisionBudgetDao = divisionBudgetDao;
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
		DivisionBudgetForm divisionBudgetForm = null;
		List<FinanceDto> financeDtos = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionBudgetForm = (DivisionBudgetForm) actionForm;
			if (MisUtility.ifEmpty(divisionBudgetForm.getDivision())) {
				if (divisionBudgetForm.getDivision().equalsIgnoreCase("All")) {
					request.setAttribute("division", "All");
				} else {
					request.setAttribute("division", divisionBudgetForm.getDivision());
				}
			}
			request.setAttribute("financialYear", divisionBudgetForm.getFinancialYear());
			String divisionType = request.getParameter("divisionType");

			financeDtos = divisionBudgetDao.getComponentDetails(divisionBudgetForm, divisionType, misAuditBean);

			if (!MisUtility.ifEmpty(financeDtos)) {
				long granTotal = 0L;
				for (FinanceDto dto : financeDtos) {
					granTotal = granTotal + (dto.getReqNxtYear());
				}
				request.setAttribute("granTotal", granTotal);
				request.setAttribute("financeDtos", financeDtos);
			}

			if (!MisUtility.ifEmpty(financeDtos)) {
				/*
				 * ActionErrors errors = new ActionErrors(); ActionMessage
				 * message = new ActionMessage("success.find",
				 * "Successfully find Record");
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				 * saveErrors(request, errors);
				 */

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

	public ActionForward approve(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("<------inside save---11111111111111----->");
		DivisionBudgetForm divisionBudgetForm = null;
		boolean flag = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionBudgetForm = (DivisionBudgetForm) actionForm;

			flag = divisionBudgetBo.approve(divisionBudgetForm, misAuditBean);

			if (flag) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.find", "Successfully Approved");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
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
		refreshDivisionBudgetForm(divisionBudgetForm);
		return mapping.findForward("display");
	}

	public ActionForward forward(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("<------inside find-------->");
		DivisionBudgetForm divisionBudgetForm = null;
		String userName = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionBudgetForm = (DivisionBudgetForm) actionForm;

			userName = divisionBudgetBo.forward(divisionBudgetForm, misAuditBean);

			if (MisUtility.ifEmpty(userName)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.find",
						"Successfully Forwarded To" + "\t" + userName);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
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
		refreshDivisionBudgetForm(divisionBudgetForm);
		return mapping.findForward("display");
	}

	public ActionForward forwardState(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("<------inside find-------->");
		DivisionBudgetForm divisionBudgetForm = null;
		String userName = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionBudgetForm = (DivisionBudgetForm) actionForm;

			userName = divisionBudgetBo.forwardState(divisionBudgetForm, misAuditBean);

			if (MisUtility.ifEmpty(userName)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.find",
						"Successfully Forwarded To" + "\t" + userName);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
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
		refreshDivisionBudgetForm(divisionBudgetForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("<------inside find-------->");
		DivisionBudgetForm divisionBudgetForm = null;
		String userName = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionBudgetForm = (DivisionBudgetForm) actionForm;

			userName = divisionBudgetBo.forward(divisionBudgetForm, misAuditBean);

			if (MisUtility.ifEmpty(userName)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.find",
						"Successfully Forwarded To" + "\t" + userName);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
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
		refreshDivisionBudgetForm(divisionBudgetForm);
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
		this.setAttrib(request);
		DivisionBudgetForm divisionBudgetForm = (DivisionBudgetForm) form;
		refreshDivisionBudgetForm(divisionBudgetForm);
		log.debug("Unspecified........DivisionBudgetAction");
		System.out.println("Unspecified........DivisionBudgetAction");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

	private void refreshDivisionBudgetForm(DivisionBudgetForm divisionBudgetForm) {
		divisionBudgetForm.setFinancialYear(null);
		divisionBudgetForm.setBudgetRefNo(null);
	}

	public ActionForward fetchNodalDivision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<LocationMasterDto> locationBeans = null;
		List<Integer> divisionIds = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			divisionIds = divisionBudgetDao.getNodalDivisionId(misAuditBean);

			if (!MisUtility.ifEmpty(divisionIds)) {
				locationBeans = locationDao.getNodalDivision(divisionIds);
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(locationBeans)) {
					buffer.append("<option value='All'>");
					buffer.append("All");
					buffer.append("</option>");
					for (LocationMasterDto locationMasterDto : locationBeans) {
						buffer.append("<option value=\"").append(locationMasterDto.getLocationId()).append("\">");
						buffer.append(locationMasterDto.getLocationName());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}

		return null;
	}

	public ActionForward fetchDivision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<LocationMasterDto> locationBeans = null;
		List<Integer> divisionIds = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			divisionIds = divisionBudgetDao.getDivision(misAuditBean);

			if (!MisUtility.ifEmpty(divisionIds)) {
				locationBeans = locationDao.getLocationBeanOnLocationIdList(divisionIds);
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(locationBeans)) {
					for (LocationMasterDto locationMasterDto : locationBeans) {
						buffer.append("<option value=\"").append(locationMasterDto.getLocationId()).append("\">");
						buffer.append(locationMasterDto.getLocationName());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}

		return null;
	}

	public ActionForward fetchFinancialYear(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FinanceDto> financeDtos = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			buffer.append("<option value='' selected>");
			buffer.append("Please Select");
			buffer.append("</option>");

			financeDtos = divisionBudgetDao.getFinancialYear(request.getParameter("divisionIds"), misAuditBean);
			if (!MisUtility.ifEmpty(financeDtos)) {
				for (FinanceDto financeDto : financeDtos) {
					buffer.append("<option value=\"").append(financeDto.getCmb_id()).append("\">");
					buffer.append(financeDto.getCmb_name());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return null;
	}

	public ActionForward fetchReferenceNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<DivisionBudgetBean> divisionBudgetBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			buffer.append("<option value='' selected>");
			buffer.append("Please Select");
			buffer.append("</option>");
			if (MisUtility.ifEmpty(request.getParameter("divisionId"))
					&& MisUtility.ifEmpty(request.getParameter("financialYear"))) {
				divisionBudgetBeans = divisionBudgetDao.getDivisionBudget(request.getParameter("financialYear"),
						request.getParameter("divisionId"), misAuditBean);
				if (!MisUtility.ifEmpty(divisionBudgetBeans)) {
					for (DivisionBudgetBean divisionBudgetBean : divisionBudgetBeans) {
						buffer.append("<option value=\"").append(divisionBudgetBean.getDivAnnBudgId()).append("\">");
						buffer.append(divisionBudgetBean.getBudgetRefNo());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}

		return null;
	}

	public ActionForward populateDivisionBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		System.out.println("-------------------populateDivisionBudget---------------");
		List<FinanceDto> financeDtos = null;
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

			System.out.println(
					"clickedColumn---------->" + clickedColumn + "clickedColumnDir-------->" + clickedColumnDir);
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			String searchParameter = request.getParameter("sSearch");

			System.out.println("-------division--------" + request.getParameter("division"));
			System.out.println("-------finance--------" + request.getParameter("finance"));

			if (MisUtility.ifEmpty(request.getParameter("finance"))) {
				financeDtos = divisionBudgetDao.populateDivisionBudgetData(request.getParameter("division"),
						request.getParameter("finance"), request.getParameter("divisionType"), searchParameter,
						clickedColumn, clickedColumnDir, misAuditBean);
			}
			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(financeDtos)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(financeDtos)) {
				locationJson.setiTotalDisplayRecords(financeDtos.size());
				locationJson.setiTotalRecords(financeDtos.size());
				// locationJson.setAaData(beneficiaryDto);
			}
			List<FinanceDto> financeDtos2 = null;
			if (!MisUtility.ifEmpty(financeDtos)) {
				financeDtos2 = divisionBudgetBo.getListBasedOnPageNumber(financeDtos, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(financeDtos2);
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
