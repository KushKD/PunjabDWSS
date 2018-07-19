package com.prwss.min.quality.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.prwss.mis.admin.LocationMasterBo;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.admin.dao.LocationTypeBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LocationMasterAction extends DispatchAction {

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	Map<String, LocationMasterForm> mapMasterForm;
	LocationMasterBo<LocationMasterForm> locationMasterBo;
	LocationMasterDao locationMasterDao;

	/**
	 * @return the locationMasterDao
	 */
	public LocationMasterDao getLocationMasterDao() {
		return locationMasterDao;
	}

	/**
	 * @param locationMasterDao
	 *            the locationMasterDao to set
	 */
	public void setLocationMasterDao(LocationMasterDao locationMasterDao) {
		this.locationMasterDao = locationMasterDao;
	}

	/**
	 * @return the locationMasterBo
	 */
	public LocationMasterBo<LocationMasterForm> getLocationMasterBo() {
		return locationMasterBo;
	}

	/**
	 * @param locationMasterBo
	 *            the locationMasterBo to set
	 */
	public void setLocationMasterBo(LocationMasterBo<LocationMasterForm> locationMasterBo) {
		this.locationMasterBo = locationMasterBo;
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

	public LocationMasterAction() {
		mapMasterForm = new HashMap<String, LocationMasterForm>();
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		LocationMasterForm locationMaster = (LocationMasterForm) form;
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
		
			 refreshMasterLocationForm(locationMaster);
			
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
		LocationMasterForm locationMasterForm = (LocationMasterForm) form;

		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			String empId=String.valueOf(misSessionBean.getEnteredBy());
			if (MisUtility.ifEmpty(locationMasterForm)) {
				locationMasterForm.setEmpId(Integer.parseInt(empId));

				String[] type=locationMasterForm.getLocationType().split("-");
				locationMasterForm.setLocationType(type[1]);
				status = locationMasterBo.saveLocationMaster(locationMasterForm);
			}
			if (status) {
				request.setAttribute("success", "success");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully saved for  Location(" + locationMasterForm.getLocationName()+")");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				 
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Location ---->" + locationMasterForm.getLocationName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
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

	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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
	

	private void refreshMasterLocationForm(LocationMasterForm locationMasterForm) {

		locationMasterForm.setLocationName(null);
		locationMasterForm.setLocationType(null);
		locationMasterForm.setParentLocation(null);
		locationMasterForm.setStatus(null);

	}

	public ActionForward populate11(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<LocationMasterForm> locationMasterList=null;
		try{
		
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
				+ pageNumber+"searchParameter-----------"+searchParameter+"pageDisplayLength-------------"+pageDisplayLength);
		 locationMasterList = locationMasterBo.getLocationMasterByPagination();

		
/*		System.out.println("locationMasterList========================================"+locationMasterList.toString());
*/		
		
		LocationJsonObject<LocationMasterForm> locationJson = new LocationJsonObject<LocationMasterForm>();
		//locationJson.setDraw(Integer.parseInt(draw));
		if(!MisUtility.ifEmpty(locationMasterList)){
			locationJson.setiTotalDisplayRecords(locationMasterList.size());
			locationJson.setiTotalRecords(locationMasterList.size());
		}

		List<LocationMasterForm> locMasterLst = null;
		if (!MisUtility.ifEmpty(locationMasterList)) {
			locMasterLst = locationMasterBo.getListBasedOnPageNumber(locationMasterList, pageDisplayLength, pageNumber,
					iDisplayStart);
			locationJson.setAaData(locMasterLst);
		}
		if ((!MisUtility.ifEmpty(locationMasterList)) && MisUtility.ifEmpty(searchParameter)) {
			locMasterLst = locationMasterBo.getListBasedOnSearchParameter(searchParameter, locationMasterList);
			locationJson.setAaData(locMasterLst);
		}
		/*if (!MisUtility.ifEmpty(locationMasterList) && clickedColumn != 0) {
			locMasterLst = locationMasterBo.getListBasedOnColumnSorting(locationMasterList, clickedColumn,
					clickedColumnDir, locationMasterForm);
			locationJson.setAaData(locMasterLst);
		}*/
		if(MisUtility.ifEmpty(locationMasterList)){
			locationJson.setAaData(locationMasterList);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(locationJson);
		System.out.println("json2-----------------"+json2);
		PrintWriter out = MisUtility.getPrintWriter(response);
		out.print(json2);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward fetchSublocationType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSublocationType");

		List<LocationTypeBean> locationBeans = null;
		LocationTypeBean typeBean = new LocationTypeBean();
		typeBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

		StringBuffer buffer = new StringBuffer();
		System.out.println("request.getParameter-------------" + request.getParameter("locationName"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("locationName"))) {
				locationBeans = locationMasterDao.getLocationType(typeBean);
				System.out.println(locationBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select Location Type");
				buffer.append("</option>");
				for (LocationTypeBean schemeHeaderBean2 : locationBeans) {
					buffer.append("<option value=\"").append(schemeHeaderBean2.getLocationName() + "-(" + schemeHeaderBean2.getLocationTypeId()+")" ).append("\">");
					buffer.append(
							schemeHeaderBean2.getLocationName() + "-(" + schemeHeaderBean2.getLocationTypeId() + ")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString().trim());
			}
		} /*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	public ActionForward fetchParentLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchParentLocation");

		List<LocationTypeBean> locationBeans = null;
		LocationTypeBean typeBean = null;
		List<LocationDetailsBean> locationDetailsBean=null;
		StringBuffer buffer = new StringBuffer();
		System.out.println("request.getParameter-------------" + request.getParameter("locationType"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("locationType"))) {
				//String[] locType = request.getParameter("locationType").split("-");
				typeBean = new LocationTypeBean();
				
				String[] type=request.getParameter("locationType").split("-");
				typeBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
				String locTypeName=getlocationTypeName(type[0]);
				
				if(MisUtility.ifEmpty(locTypeName)){
				
				typeBean.setLocationName(locTypeName);
				
					locationBeans = locationMasterDao.getLocationType(typeBean);
				
				if (!MisUtility.ifEmpty(locationBeans)) {
					for(LocationTypeBean loc:locationBeans){
						locationDetailsBean = locationMasterDao.getParentLocation(loc.getLocationTypeId());
					
					if (!MisUtility.ifEmpty(locationDetailsBean)) {
						buffer.append("<option value='' selected>");
						buffer.append("Select Location Type");
						buffer.append("</option>");
						for (LocationDetailsBean locationDetails : locationDetailsBean) {
							buffer.append("<option value=\"").append(locationDetails.getLocationName()+"-("+locationDetails.getLocationMasterBean().getLocationId()+")")
									.append("\">");
							buffer.append(locationDetails.getLocationName()+"-("+locationDetails.getLocationMasterBean().getLocationId()+")");
							buffer.append("</option>");
						}
				}
				}

			}

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString().trim());
			}
			}
			}
			/*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return null;
	}

	
	
	private String getlocationTypeName(String locName){
		String locationName = null;
		if(locName.trim().equalsIgnoreCase("Circle")){
			locationName="Zone";
		}
		if(locName.trim().equalsIgnoreCase("District")){
			locationName="Circle";
		}
		/*if(locName.trim().equalsIgnoreCase("Division")){
			locationName="District";
		}*/
		if(locName.trim().equalsIgnoreCase("Block")){
			locationName="District";
		}
		if(locName.trim().equalsIgnoreCase("Village")){
			locationName="Block";
		}
		return locationName;
	}
}
