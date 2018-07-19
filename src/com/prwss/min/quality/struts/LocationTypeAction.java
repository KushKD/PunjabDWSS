/**
 * 
 */
package com.prwss.min.quality.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.mis.admin.LocationTypeBo;
import com.prwss.mis.admin.dao.LocationTypeBean;
import com.prwss.mis.admin.dao.LocationTypeDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;


/**
 * @author bhsingh
 *
 */
public class LocationTypeAction  extends  DispatchAction{

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	LocationTypeBo<LocationTypeForm> locationTypeBo;
	LocationTypeDao locationTypeDao;

	
	
	

	/**
	 * @return the locationTypeDao
	 */
	public LocationTypeDao getLocationTypeDao() {
		return locationTypeDao;
	}

	/**
	 * @param locationTypeDao the locationTypeDao to set
	 */
	public void setLocationTypeDao(LocationTypeDao locationTypeDao) {
		this.locationTypeDao = locationTypeDao;
	}

	/**
	 * @param locationTypeBo the locationTypeBo to set
	 */
	public void setLocationTypeBo(LocationTypeBo<LocationTypeForm> locationTypeBo) {
		this.locationTypeBo = locationTypeBo;
	}

	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}

	/**
	 * @param misAuditBean
	 *            the misAuditBean to set
	 */
	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	/**
	 * @return the misSessionBean
	 */
	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	/**
	 * @param misSessionBean
	 *            the misSessionBean to set
	 */
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	/*public LocationTypeAction() {
		mapMasterForm = new HashMap<String, LocationTypeForm>();
	}*/

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			this.setAttrib(request);
			System.out.println("find:" + request.getParameter("d__mode"));
			String mode = (String) request.getParameter("d__mode");
			System.out.println("mode-----------"+mode);
			request.setAttribute("level2", "true");
			

		} catch (Exception e) {

		}
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {
		
		
		System.out.println("inside save-----------------");

		this.setAttrib(request);
		boolean status = false;
		LocationTypeForm locationMasterForm = (LocationTypeForm) form;
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			
			
			List<LocationTypeBean> locationTypeBeans=locationTypeDao.getLocationTypeBean(locationMasterForm);
			if(!MisUtility.ifEmpty(locationTypeBeans)){
				throw new MISException(MISExceptionCodes.MIS012, "Entry Already Exist for Location("+locationMasterForm.getLocationType()+")");
			}
			String userId=String.valueOf(misSessionBean.getEnteredBy());
			locationMasterForm.setEmpId(Integer.parseInt(userId));
			if (MisUtility.ifEmpty(locationMasterForm)) {
				status = locationTypeBo.saveLocationMaster(locationMasterForm);
			}
			if (status) {
				request.setAttribute("success", "success");
				ActionErrors errors= new ActionErrors();
				ActionMessage message = new ActionMessage("success.save"," Successfully saved for Location Type(" + locationMasterForm.getLocationType()+")");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Location");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
				request.setAttribute("level2", "true");
				System.out.println(
						"e.getcode in action------------++++++++++" + e.getCode() + "messages----->" + e.getMessage());
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
		}
		refreshMasterLocationForm(locationMasterForm);
		return mapping.findForward("display");
	}

	/*public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("display");
	}
*/
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

		System.out.println("Unspecified........locationMaster");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private void refreshMasterLocationForm(LocationTypeForm locationMasterForm) {
		
		locationMasterForm.setLocationType(null);
		locationMasterForm.setStatus(null);
		locationMasterForm.setEndDate(null);
		locationMasterForm.setStartDate(null);
		locationMasterForm.setLocationTypeId(null);
		 
	}

	public ActionForward populate11(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
	
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

		System.out.println("clickedColumn-------------->" + clickedColumn + "-----------clickedColumnDir-------------"
				+ clickedColumnDir + "-iDisplayStart----------" + iDisplayStart + "pageNumber------------"
				+ pageNumber);
		/*LocationTypeForm locationMasterForm = mapMasterForm.get("form");
		System.out.println("LocationTypeForm----------->"+locationMasterForm.toString());*/
		
		
		LocationTypeForm locationMasterForm = (LocationTypeForm)form;
		List<LocationTypeForm> locationTypeList = locationTypeBo
				.getLocationMasterByPagination();

		LocationJsonObject<LocationTypeForm> locationJson = new LocationJsonObject<LocationTypeForm>();
		locationJson.setiTotalDisplayRecords(locationTypeList.size());
		locationJson.setiTotalRecords(locationTypeList.size());

		List<LocationTypeForm> locMasterLst = null;
		if (!MisUtility.ifEmpty(locationTypeList)) {
			locMasterLst = locationTypeBo.getListBasedOnPageNumber(locationTypeList, pageDisplayLength, pageNumber,
					iDisplayStart);
			locationJson.setAaData(locMasterLst);
		}
		if (!MisUtility.ifEmpty(locationTypeList) && MisUtility.ifEmpty(searchParameter)) {
			locMasterLst = locationTypeBo.getListBasedOnSearchParameter(searchParameter, locationTypeList);
			locationJson.setAaData(locMasterLst);
		}
		if (!MisUtility.ifEmpty(locationTypeList) && clickedColumn != 0) {
			locMasterLst = locationTypeBo.getListBasedOnColumnSorting(locationTypeList, clickedColumn,
					clickedColumnDir, locationMasterForm);
			locationJson.setAaData(locMasterLst);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(locationJson);
		System.out.println(json2);
		PrintWriter out = MisUtility.getPrintWriter(response);
		out.print(json2);

		return null;
	}

}
