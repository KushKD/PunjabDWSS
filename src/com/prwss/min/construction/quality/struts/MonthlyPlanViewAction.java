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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bo.MonthlyPlanBo;
import com.prwss.min.construction.quality.bo.MonthlyPlanViewBo;
import com.prwss.min.construction.quality.dao.MonthlyPlanDao;
import com.prwss.min.construction.quality.form.MonthlyPlanInspectionForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MonthlyPlanViewAction extends DispatchAction {

	private Logger log = Logger.getLogger(MonthlyPlanViewAction.class);
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MonthlyPlanBo monthlyPlanBo;
	private MonthlyPlanDao monthlyPlanDao;
	private MonthlyPlanViewBo<MonthlyPlanDto> monthlyPlanViewBo;

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

	public MonthlyPlanBo getMonthlyPlanBo() {
		return monthlyPlanBo;
	}

	public void setMonthlyPlanBo(MonthlyPlanBo monthlyPlanBo) {
		this.monthlyPlanBo = monthlyPlanBo;
	}

	public MonthlyPlanDao getMonthlyPlanDao() {
		return monthlyPlanDao;
	}

	public void setMonthlyPlanDao(MonthlyPlanDao monthlyPlanDao) {
		this.monthlyPlanDao = monthlyPlanDao;
	}

	public MonthlyPlanViewBo<MonthlyPlanDto> getMonthlyPlanViewBo() {
		return monthlyPlanViewBo;
	}

	public void setMonthlyPlanViewBo(MonthlyPlanViewBo<MonthlyPlanDto> monthlyPlanViewBo) {
		this.monthlyPlanViewBo = monthlyPlanViewBo;
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
		System.out.println("Unspecified........MonthlyPlanViewAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}

	public ActionForward populateMonthlyPlan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		System.out.println("-------------------populateMonthlyPlan---------------");
		String yearlyPlan = null;
		String month = null;
		List<Integer> yearlyPlanId = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			if (MisUtility.ifEmpty(request.getParameter("yearlyPlan"))) {
				yearlyPlan = request.getParameter("yearlyPlan");
			}
			if (MisUtility.ifEmpty(request.getParameter("month"))) {
				month = request.getParameter("month");
			}

			MonthlyPlanInspectionForm monthlyPlanInspectionForm = (MonthlyPlanInspectionForm) form;

			yearlyPlanId = monthlyPlanDao.findYeralyPlan(yearlyPlan);
			if (!MisUtility.ifEmpty(yearlyPlanId)) {

				monthlyPlanInspectionForm.setYearPlan(String.valueOf(yearlyPlanId.get(0)));
				monthlyPlanInspectionForm.setMonth(month);
				monthlyPlanInspectionForm.setLoginUser(misAuditBean.getEnteredBy());

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
				
				List<MonthlyPlanDto> monthlyPlanDtos = monthlyPlanDao.getMonthlyPlanData(monthlyPlanInspectionForm,
						searchParameter, clickedColumn, clickedColumnDir);
				LocationJsonObject<MonthlyPlanDto> locationJson = new LocationJsonObject<MonthlyPlanDto>();

				if (MisUtility.ifEmpty(monthlyPlanDtos)) {
					locationJson.setAaData(new ArrayList<MonthlyPlanDto>());
				}
				if (!MisUtility.ifEmpty(monthlyPlanDtos)) {
					locationJson.setiTotalDisplayRecords(monthlyPlanDtos.size());
					locationJson.setiTotalRecords(monthlyPlanDtos.size());
					// locationJson.setAaData(beneficiaryDto);
				}
				List<MonthlyPlanDto> monthlyPlanDtos2 = null;
				if (!MisUtility.ifEmpty(monthlyPlanDtos)) {
					monthlyPlanDtos2 = monthlyPlanViewBo.getListBasedOnPageNumber(monthlyPlanDtos, pageDisplayLength,
							pageNumber, iDisplayStart);
					locationJson.setAaData(monthlyPlanDtos2);
				}

				System.out.println("locationjson------------" + locationJson.toString());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(locationJson);
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
			}
		} catch (Exception e) {
			log.debug(e.getMessage());

		}
		return null;
	}

}
