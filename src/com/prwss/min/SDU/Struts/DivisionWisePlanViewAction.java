package com.prwss.min.SDU.Struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.prwss.min.SDU.BO.DivisionWisePlanViewBo;
import com.prwss.min.SDU.dao.DivisionWisePlanViewDao;
import com.prwss.min.SDU.form.DivisionWisePlanViewForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class DivisionWisePlanViewAction extends DispatchAction {

	private Logger log = Logger.getLogger(DivisionActivityMpgAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private DivisionWisePlanViewDao divisionWisePlanViewDao;
	private DivisionWisePlanViewBo<DivisionWisePlanViewForm> divisionWisePlanViewBo;

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

	public DivisionWisePlanViewDao getDivisionWisePlanViewDao() {
		return divisionWisePlanViewDao;
	}

	public void setDivisionWisePlanViewDao(DivisionWisePlanViewDao divisionWisePlanViewDao) {
		this.divisionWisePlanViewDao = divisionWisePlanViewDao;
	}

	public DivisionWisePlanViewBo<DivisionWisePlanViewForm> getDivisionWisePlanViewBo() {
		return divisionWisePlanViewBo;
	}

	public void setDivisionWisePlanViewBo(DivisionWisePlanViewBo<DivisionWisePlanViewForm> divisionWisePlanViewBo) {
		this.divisionWisePlanViewBo = divisionWisePlanViewBo;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------

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

		System.out.println("Unspecified........DivisionWisePlanViewAction");
		return mapping.findForward("display");
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "locationName"); */
		/* request.setAttribute("d__auto", "locationName"); */
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------

	public ActionForward populatePlanData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		System.out.println("------------inside populatePlanData-----123456666544444chacha----------");

		String financialYear = null;
		String division = null;

		financialYear = request.getParameter("financialYear");
		division = request.getParameter("division");
		List<DivisionWisePlanViewForm> divisionWisePlanViewForms = null;

		try {

			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			// Fetch Page display length

			System.out.println("clickedColumn-------------->" + clickedColumn
					+ "-----------clickedColumnDir-------------" + clickedColumnDir + "-iDisplayStart----------"
					+ iDisplayStart + "pageNumber------------" + pageNumber + "searchParameter-----------"
					+ searchParameter + "pageDisplayLength-------------" + pageDisplayLength);

			divisionWisePlanViewForms = divisionWisePlanViewBo.getPlanViewByPagination(searchParameter, clickedColumn,
					clickedColumnDir, financialYear, division);

			System.out.println("DivisionWisePlanView ========================================"
					+ divisionWisePlanViewForms.size());

			LocationJsonObject<DivisionWisePlanViewForm> locationJson = new LocationJsonObject<DivisionWisePlanViewForm>();
			if (MisUtility.ifEmpty(divisionWisePlanViewForms)) {
				locationJson.setAaData(new ArrayList<DivisionWisePlanViewForm>());
			}
			if (!MisUtility.ifEmpty(divisionWisePlanViewForms)) {
				locationJson.setiTotalDisplayRecords(divisionWisePlanViewForms.size());
				locationJson.setiTotalRecords(divisionWisePlanViewForms.size());
				// locationJson.setAaData(activityVillageMappingBeans);
			}

			// Pagination not working
			List<DivisionWisePlanViewForm> divisionWisePlanViewForms2 = null;
			if (!MisUtility.ifEmpty(divisionWisePlanViewForms)) {

				divisionWisePlanViewForms2 = divisionWisePlanViewBo.getListBasedOnPageNumber(
						divisionWisePlanViewForms, pageDisplayLength, pageNumber, iDisplayStart);
								locationJson.setAaData(divisionWisePlanViewForms2);

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

	// -----------------------------------------------------------------------------------------------------------------------------------------

	
	public ActionForward getPlanDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<DivisionWisePlanViewForm> divisionWisePlanViewForms = null;
		List<DivisionWisePlanViewForm> divisionWisePlanViewForms2 = null;
		try {
			String activityVillageId = request.getParameter("activityVillageId");
System.out.println(activityVillageId);
			if (MisUtility.ifEmpty(activityVillageId)) {
				divisionWisePlanViewForms = divisionWisePlanViewDao.getPlanDetails(activityVillageId);
				
				if(!MisUtility.ifEmpty(divisionWisePlanViewForms)){
					divisionWisePlanViewForms2=getDivisionWisePlanViewLst(divisionWisePlanViewForms);
				}
				System.out.println("-----1-------"+divisionWisePlanViewForms2.get(0));
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(divisionWisePlanViewForms2);
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
				
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------	
	
	private List<DivisionWisePlanViewForm> getDivisionWisePlanViewLst(List<DivisionWisePlanViewForm> divisionWisePlanViewForms) {
		
		try{
		Iterator<DivisionWisePlanViewForm> iterator=divisionWisePlanViewForms.iterator();
		
		while(iterator.hasNext()){
			DivisionWisePlanViewForm divisionWisePlanViewForm=(DivisionWisePlanViewForm)iterator.next();
			divisionWisePlanViewForm.setStartDate1(MisUtility.convertDateString(divisionWisePlanViewForm.getStartDate()));
			divisionWisePlanViewForm.setEndDate1(MisUtility.convertDateString(divisionWisePlanViewForm.getStartDate()));
		}
		}catch(Exception e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return divisionWisePlanViewForms;
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------
	
	
	public ActionForward getDivisionsBasedOnUserId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String UserID = null;
		List<String> locationId = null;
		List<LocationDivisionSubDivisonDetailsBean> LocationNameandId = null;
		List<Integer> IdLocations = null;
		UserID = request.getParameter("username_");

		if (MisUtility.ifEmpty(UserID)) {
			locationId = divisionWisePlanViewDao.getAllLocationIds(UserID);
		}
		IdLocations = new ArrayList<Integer>();

		for (int i = 0; i < locationId.size(); i++) {
			IdLocations.add(Integer.parseInt(locationId.get(i)));
		}
		if (!MisUtility.ifEmpty(IdLocations)) {
			LocationNameandId = divisionWisePlanViewDao.getLocationNameandId(IdLocations);
		}
		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Location ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(LocationNameandId)) {
				for (LocationDivisionSubDivisonDetailsBean bean : LocationNameandId) {
					buffer.append("<option value=\"").append(bean.getDivisonSubDivisonDetailsId()).append("\">");
					buffer.append(bean.getDivisonSubDivisonDetailsName());
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
	
	// -----------------------------------------------------------------------------------------------------------------------------------------
}
