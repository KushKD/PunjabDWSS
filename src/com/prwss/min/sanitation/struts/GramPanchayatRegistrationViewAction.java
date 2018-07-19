package com.prwss.min.sanitation.struts;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.prwss.min.quality.SampleDistributionForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bean.GramPanchayatDetailsDto;
import com.prwss.min.sanitation.dao.GramPanchayatRegisterDao;
import com.prwss.min.sanitation.form.GramPanchayatRegisterForm;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MisUtility;

public class GramPanchayatRegistrationViewAction extends DispatchAction {

	private Logger log = Logger.getLogger(GramPanchayatRegistrationViewAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	LocationMasterDao locationMasterDao;
	private GramPanchayatRegisterDao gramPanchayatRegisterDao;
	
	
	
	
	public GramPanchayatRegisterDao getGramPanchayatRegisterDao() {
		return gramPanchayatRegisterDao;
	}
	public void setGramPanchayatRegisterDao(GramPanchayatRegisterDao gramPanchayatRegisterDao) {
		this.gramPanchayatRegisterDao = gramPanchayatRegisterDao;
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
	public LocationMasterDao getLocationMasterDao() {
		return locationMasterDao;
	}
	public void setLocationMasterDao(LocationMasterDao locationMasterDao) {
		this.locationMasterDao = locationMasterDao;
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

		System.out.println("Unspecified........Ghram Panchayat");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "block@district@villageId");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	
	
	//populateLabMaster
		public ActionForward populateGramPanchayatData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception, IOException {
			
			String district = null;
			String block =  null;
			String village = null;
			
			
			if(MisUtility.ifEmpty(request.getParameter("district"))){
				district =  (String)request.getParameter("district");	
				System.out.println("Distrivt from requst " + district);
			}else{
				district = "";
			}
			if(MisUtility.ifEmpty(request.getParameter("block"))){
				 block =  (String)request.getParameter("block");	
				 System.out.println("block from requst " + block);
			}else{
				block="";
			}
			if(MisUtility.ifEmpty(request.getParameter("village"))){
				village =  (String)request.getParameter("village");			
				System.out.println("village from requst " + village);
			}else{
				village= null;
			}
			
			//Setting Values to form
			ViewRegistrationsForm viewRegistrationForm = (ViewRegistrationsForm) form;
			viewRegistrationForm.setBlock(block);
			viewRegistrationForm.setDistrict(district);
			viewRegistrationForm.setVillage(village);

			List<GramPanchayatDetailsDto> dto = null;
			try{
				
				List<GramPanchayatRegisterForm> formList = null;
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			// Fetch Page display length

			
			System.out.println("Form"+viewRegistrationForm.toString());
			dto = gramPanchayatRegisterDao.getGramPanchayatDetails(viewRegistrationForm);
			
			

			
			//System.out.println("LabMaster ========================================"+ labMasterLst.size());
			
			//if(!MisUtility.ifEmpty(labMasterLst)){
			LocationJsonObject<GramPanchayatDetailsDto> locationJson = new LocationJsonObject<GramPanchayatDetailsDto>();
			//locationJson.setDraw(Integer.parseInt(draw));
			if(!MisUtility.ifEmpty(dto)){
				locationJson.setiTotalDisplayRecords(dto.size());
				
				locationJson.setiTotalRecords(dto.size());
				locationJson.setAaData(dto);
			}

			List<SampleDistributionForm> locMasterLst = null;
			/*if (!MisUtility.ifEmpty(dto)) {
				locMasterLst = sampleDistributionBO.getListBasedOnPageNumber(labMasterLst, pageDisplayLength, pageNumber,iDisplayStart);
				locationJson.setAaData(locMasterLst);
			}*/
			if(MisUtility.ifEmpty(dto)){
				locationJson.setAaData(dto);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	

	
}
