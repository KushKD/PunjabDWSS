/**
 * 
 */
package com.prwss.min.finance.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.DivisionBudgetBo;
import com.prwss.min.finance.dao.DivisionBudgetDao;
import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class DivisionBudgetAction extends DispatchAction {

	private Logger log = Logger.getLogger(DivisionBudgetAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private DivisionBudgetBo<FinanceDto> divisionBudgetBo;
	private DivisionBudgetDao divisionBudgetDao;

	public DivisionBudgetBo<FinanceDto> getDivisionBudgetBo() {
		return divisionBudgetBo;
	}

	public void setDivisionBudgetBo(DivisionBudgetBo<FinanceDto> divisionBudgetBo) {
		this.divisionBudgetBo = divisionBudgetBo;
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

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		String budgetReferenceNo = null;
		DivisionBudgetForm divisionBudgetForm = (DivisionBudgetForm) form;

		System.out.println("Inside DivisionBudgetAction Save()->");

		log.debug("Inside DivisionBudgetAction Save()->" + divisionBudgetForm.toString());

		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			budgetReferenceNo = divisionBudgetBo.save(divisionBudgetForm, misSessionBean);
			if (MisUtility.ifEmpty(budgetReferenceNo)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Divisional Budget Reference Id" + "\t" + budgetReferenceNo + "\t" + " Successfully Saved");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshDivisionBudgetForm(divisionBudgetForm);

		return mapping.findForward("display");

	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		String budgetReferenceNo = null;
		DivisionBudgetForm divisionBudgetForm = (DivisionBudgetForm) form;

		System.out.println("Inside DivisionBudgetAction Update()->");

		log.debug("Inside DivisionBudgetAction Update()->" + divisionBudgetForm.toString());

		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			budgetReferenceNo = divisionBudgetBo.update(divisionBudgetForm, misSessionBean);
			if (MisUtility.ifEmpty(budgetReferenceNo)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Divisional Budget Reference Id" + "\t" + budgetReferenceNo + "\t" + " Successfully Update");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
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
		divisionBudgetForm.setActivity(null);
		divisionBudgetForm.setAlreadyExpended(null);
		divisionBudgetForm.setComponent(null);
		divisionBudgetForm.setDivision(null);
		divisionBudgetForm.setEstimatedCosts(null);
		divisionBudgetForm.setFinancialYear(null);
		divisionBudgetForm.setNextYearReq(null);
		divisionBudgetForm.setQuarter1(null);
		divisionBudgetForm.setQuarter2(null);
		divisionBudgetForm.setQuarter3(null);
		divisionBudgetForm.setQuarter4(null);

	}

	public ActionForward fetchComponentType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<ComponentDetailsBean> componentDetailsBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("componentType"))) {

				componentDetailsBeans = divisionBudgetDao.getComponentType(request.getParameter("componentType"), null);
				System.out.println(componentDetailsBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(componentDetailsBeans)) {
					for (ComponentDetailsBean componentDetailsBean : componentDetailsBeans) {
						buffer.append("<option value=\"").append(componentDetailsBean.getComponentDetailsId())
								.append("\">");
						buffer.append(componentDetailsBean.getComponentName());
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

	public ActionForward fetchChildComByComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<ComponentDetailsBean> componentDetailsBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("componentId"))) {

				componentDetailsBeans = divisionBudgetDao.getChildComponent(request.getParameter("componentType"),
						request.getParameter("componentId"));
				System.out.println(componentDetailsBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(componentDetailsBeans)) {
					for (ComponentDetailsBean componentDetailsBean : componentDetailsBeans) {
						buffer.append("<option value=\"").append(componentDetailsBean.getComponentDetailsId())
								.append("\">");
						buffer.append(componentDetailsBean.getComponentName());
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
	public ActionForward fetchDivision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FinanceDto> componentDetailsBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("division"))) {

				componentDetailsBeans = divisionBudgetDao.getDivisionBudgetBean();
				System.out.println(componentDetailsBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(componentDetailsBeans)) {
					for (FinanceDto componentDetailsBean : componentDetailsBeans) {
						buffer.append("<option value=\"").append(componentDetailsBean.getDivisionId())
								.append("\">");
						buffer.append(componentDetailsBean.getDivisonSubDivisonDetailsName());
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

	public ActionForward fetchActivityByComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<Long> subComponentLevel1 = null;
		List<Long> subComponentLevel2 = null;
		List<Long> subComponentLevel3 = null;
		StringBuffer buffer = new StringBuffer();
		List<Integer> componentDetailsIds = null;
		List<Integer> componentDetailsIds2 = null;
		List<Integer> componentDetailsIds3 = null;
		List<ComponentDetailsBean> componentDetailsBeans = null;
		try {
			if (MisUtility.ifEmpty(request.getParameter("componentType"))) {

				subComponentLevel1 = divisionBudgetDao.getSubComponentLevel1(null,
						request.getParameter("componentType"));
				if (!MisUtility.ifEmpty(subComponentLevel1)) {
					componentDetailsIds = new ArrayList<Integer>();
					for (Long componentDetailsId : subComponentLevel1) {
						componentDetailsIds.add(Integer.parseInt(String.valueOf(componentDetailsId)));
					}
					subComponentLevel2 = divisionBudgetDao.getSubComponentLevel2(componentDetailsIds);
					if (!MisUtility.ifEmpty(subComponentLevel2)) {
						componentDetailsIds2 = new ArrayList<Integer>();
						for (Long componentDetailsId : subComponentLevel2) {
							componentDetailsIds2.add(Integer.parseInt(String.valueOf(componentDetailsId)));
						}

						subComponentLevel3 = divisionBudgetDao.getSubComponentLevel2(componentDetailsIds2);
						if (!MisUtility.ifEmpty(subComponentLevel3)) {
							componentDetailsIds3 = new ArrayList<Integer>();
							for (Long componentDetailsId : subComponentLevel3) {
								componentDetailsIds3.add(Integer.parseInt(String.valueOf(componentDetailsId)));
							}
						}

						Iterator<Integer> iterator = componentDetailsIds3.iterator();

						while (iterator.hasNext()) {
							Integer componentDetails = (Integer) iterator.next();
							componentDetailsIds2.add(componentDetails);
						}
						componentDetailsBeans = divisionBudgetDao.getActivity(componentDetailsIds2);
					}

				}

				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");

				if (!MisUtility.ifEmpty(componentDetailsBeans)) {
					for (ComponentDetailsBean componentDetailsBean : componentDetailsBeans) {
						buffer.append("<option value=\"").append(componentDetailsBean.getComponentDetailsId())
								.append("\">");
						buffer.append(componentDetailsBean.getComponentName());
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

	public ActionForward populateBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		System.out.println("-------------------populateBudget---------------");

		List<FinanceDto> financeDtos1 = null;
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

			List<FinanceDto> financeDtos = divisionBudgetDao.populateBudgetData(searchParameter, clickedColumn,
					clickedColumnDir);

			financeDtos1 = populateFinanceDto(financeDtos);

			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(financeDtos1)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(financeDtos1)) {
				locationJson.setiTotalDisplayRecords(financeDtos1.size());
				locationJson.setiTotalRecords(financeDtos1.size());
				// locationJson.setAaData(beneficiaryDto);
			}
			List<FinanceDto> financeDtos2 = null;
			if (!MisUtility.ifEmpty(financeDtos1)) {
				financeDtos2 = divisionBudgetBo.getListBasedOnPageNumber(financeDtos1, pageDisplayLength, pageNumber,
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

	private List<FinanceDto> populateFinanceDto(List<FinanceDto> financeDtos) {

		try {
			if (!MisUtility.ifEmpty(financeDtos)) {

				Iterator<FinanceDto> iterator = financeDtos.iterator();
				while (iterator.hasNext()) {
					FinanceDto financeDto = (FinanceDto) iterator.next();
					if (financeDto.getIsForward() == Integer.parseInt(MISConstants.ONE)) {
						financeDto.setForward("Submitted");
					} else {
						financeDto.setForward("Not Submitted");
					}
				}
			}
		} catch (Exception e){
			log.debug(e.getMessage());
		}
		return financeDtos;

	}

	// populateBudgetDivisionDetails
	public ActionForward populateBudgetDivisionDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

		System.out.println("-------------------populateBudget---------------");
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

			List<FinanceDto> financeDtos = divisionBudgetDao.populateDivisionBudgetData(searchParameter, clickedColumn,
					clickedColumnDir);
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
