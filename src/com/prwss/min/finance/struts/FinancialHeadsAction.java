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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FinanceHeadBean;
import com.prwss.min.finance.bo.FinancialHeadsBo;
import com.prwss.min.finance.dao.FinancialHeadsDao;
import com.prwss.min.finance.form.FinanceHeadsForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FinancialHeadsAction extends DispatchAction {

	private Logger log = Logger.getLogger(FinancialHeadsAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private FinancialHeadsBo<FinanceDto> financialHeadsBo;
	private FinancialHeadsDao financialHeadsDao;

	public FinancialHeadsDao getFinancialHeadsDao() {
		return financialHeadsDao;
	}

	public void setFinancialHeadsDao(FinancialHeadsDao financialHeadsDao) {
		this.financialHeadsDao = financialHeadsDao;
	}

	public FinancialHeadsBo<FinanceDto> getFinancialHeadsBo() {
		return financialHeadsBo;
	}

	public void setFinancialHeadsBo(FinancialHeadsBo<FinanceDto> financialHeadsBo) {
		this.financialHeadsBo = financialHeadsBo;
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
		boolean status = false;
		FinanceHeadsForm financeHeadsForm = (FinanceHeadsForm) form;

		System.out.println("Inside financeHeads Save()->");
		log.debug("Inside CollectionCenterAction Save()->" + financeHeadsForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = financialHeadsBo.save(financeHeadsForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved");
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
		refreshFinanceForm(financeHeadsForm);

		return mapping.findForward("display");

	}

	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		FinanceHeadsForm financeHeadsForm = (FinanceHeadsForm) form;

		System.out.println("Inside financeHeads update()->");
		log.debug("Inside CollectionCenterAction update()->" + financeHeadsForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = financialHeadsBo.update(financeHeadsForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Update");
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
		refreshFinanceForm(financeHeadsForm);

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
		log.debug("Unspecified........FinancialHeadAction");
		System.out.println("Unspecified........FinancialHeadAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	private void refreshFinanceForm(FinanceHeadsForm financeHeadsForm) {
		financeHeadsForm.setDescription(null);
		financeHeadsForm.setHeadType(null);
		financeHeadsForm.setNumber(null);
		financeHeadsForm.setParent(null);
		financeHeadsForm.setFinanceHeadId(null);
	}

	public ActionForward fetchParent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FinanceHeadBean> financeHeadBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("headType"))) {

				if (request.getParameter("headType").equalsIgnoreCase(MISConstants.ONE_SEVENTY_NINE)) {
					financeHeadBeans = financialHeadsDao.getParent(MISConstants.ONE_SEVENTY_EIGHT);
				} else if (request.getParameter("headType").equalsIgnoreCase(MISConstants.ONE_EIGHTY)) {
					financeHeadBeans = financialHeadsDao.getParent(MISConstants.ONE_SEVENTY_NINE);
				} else if (request.getParameter("headType").equalsIgnoreCase(MISConstants.ONE_EIGHTY_ONE)) {
					financeHeadBeans = financialHeadsDao.getParent(MISConstants.ONE_EIGHTY);
				} else if (request.getParameter("headType").equalsIgnoreCase(MISConstants.ONE_EIGHTY_TWO)) {
					financeHeadBeans = financialHeadsDao.getParent(MISConstants.ONE_EIGHTY_ONE);
				} else if (request.getParameter("headType").equalsIgnoreCase(MISConstants.ONE_EIGHTY_THREE)) {
					financeHeadBeans = financialHeadsDao.getParent(MISConstants.ONE_EIGHTY_TWO);
				} else if (request.getParameter("headType").equalsIgnoreCase(MISConstants.ONE_EIGHTY_FOUR)) {
					financeHeadBeans = financialHeadsDao.getParent(MISConstants.ONE_EIGHTY_THREE);
				}
				System.out.println(financeHeadBeans.toString());
				buffer.append("<option value='-1' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");
				for (FinanceHeadBean financeHeadBean : financeHeadBeans) {
					buffer.append("<option value=\"").append(financeHeadBean.getHeadId()).append("\">");
					buffer.append(financeHeadBean.getHeadName());
					buffer.append("</option>");
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

	public ActionForward populateFinanceHead(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		try {
			List<FinanceDto> financeHeadBeans = null;
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			financeHeadBeans = new ArrayList<FinanceDto>();
			
			financeHeadBeans = financialHeadsDao.populateFinanceHead(searchParameter, clickedColumn, clickedColumnDir);
			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(financeHeadBeans)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(financeHeadBeans)) {
				locationJson.setiTotalDisplayRecords(financeHeadBeans.size());
				locationJson.setiTotalRecords(financeHeadBeans.size());
			}
			List<FinanceDto> financeHeadBeans2 = null;
			if (!MisUtility.ifEmpty(financeHeadBeans)) {
				financeHeadBeans2 = financialHeadsBo.getListBasedOnPageNumber(financeHeadBeans, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(financeHeadBeans2);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
