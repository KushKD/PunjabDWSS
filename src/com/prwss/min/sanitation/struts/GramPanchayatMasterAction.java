package com.prwss.min.sanitation.struts;

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
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bean.GramPanchayatDto;
import com.prwss.min.sanitation.bo.GramPanchayatMasterBo;
import com.prwss.min.sanitation.dao.GramPanchayatMasterDao;
import com.prwss.min.sanitation.form.GramPanchayatMasterForm;
import com.prwss.min.sanitation.form.GramPanchayatMasterGrid;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class GramPanchayatMasterAction extends DispatchAction {
	
	private Logger log = Logger.getLogger(GramPanchayatMasterAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private GramPanchayatMasterBo<GramPanchayatDto> gramPanchayatMasterBo;
	public GramPanchayatMasterBo<GramPanchayatDto> getGramPanchayatMasterBo() {
		return gramPanchayatMasterBo;
	}

	public void setGramPanchayatMasterBo(GramPanchayatMasterBo<GramPanchayatDto> gramPanchayatMasterBo) {
		this.gramPanchayatMasterBo = gramPanchayatMasterBo;
	}

	public GramPanchayatMasterDao getGramPanchayatMasterDao() {
		return gramPanchayatMasterDao;
	}

	public void setGramPanchayatMasterDao(GramPanchayatMasterDao gramPanchayatMasterDao) {
		this.gramPanchayatMasterDao = gramPanchayatMasterDao;
	}





	private GramPanchayatMasterDao gramPanchayatMasterDao;
	
	LocationDao locationDao;
	
	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	



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
	 * @return the misAuditBean
	 */
	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}
	/**
	 * @param misAuditBean the misAuditBean to set
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
	 * @param misSessionBean the misSessionBean to set
	 */
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}
	
	
	
	public ActionForward populateGramPanchayatMasterData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		try{
			List<GramPanchayatDto> gramPanchayatMasterLst = null;
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
		
		gramPanchayatMasterLst = gramPanchayatMasterBo.getGramPanchayatMasterByPagination();

		
		System.out.println("gramPanchayatMaster ========================================"+ gramPanchayatMasterLst.size());
		
		//if(!MisUtility.ifEmpty(labMasterLst)){
		LocationJsonObject<GramPanchayatDto> locationJson = new LocationJsonObject<GramPanchayatDto>();
		//locationJson.setDraw(Integer.parseInt(draw));
		if(!MisUtility.ifEmpty(gramPanchayatMasterLst)){
			locationJson.setiTotalDisplayRecords(gramPanchayatMasterLst.size());
			locationJson.setiTotalRecords(gramPanchayatMasterLst.size());
		}

		List<GramPanchayatDto> locMasterLst = null;
		if (!MisUtility.ifEmpty(gramPanchayatMasterLst)) {
			locMasterLst = gramPanchayatMasterBo.getListBasedOnPageNumber(gramPanchayatMasterLst, pageDisplayLength, pageNumber,iDisplayStart);
			locationJson.setAaData(locMasterLst);
		}
		/*if ((!MisUtility.ifEmpty(hallofFameLst)) && MisUtility.ifEmpty(searchParameter)) {
			locMasterLst = hallofFameBO.getListBasedOnSearchParameter(searchParameter, hallofFameLst);
			locationJson.setAaData(hallofFameLst);
		}*/
		/*if (!MisUtility.ifEmpty(locationMasterList) && clickedColumn != 0) {
			locMasterLst = locationMasterBo.getListBasedOnColumnSorting(locationMasterList, clickedColumn,
					clickedColumnDir, locationMasterForm);
			locationJson.setAaData(locMasterLst);
		}*/
		if(MisUtility.ifEmpty(gramPanchayatMasterLst)){
			locationJson.setAaData(gramPanchayatMasterLst);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(locationJson);
		System.out.println(json2);
		PrintWriter out = MisUtility.getPrintWriter(response);
		out.print(json2);
		//}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		GramPanchayatMasterForm gramPanchayatMasterForm = (GramPanchayatMasterForm) form;
		
		System.out.println("inside save gram panchayat master --------------------" + gramPanchayatMasterForm.toString());
		
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			gramPanchayatMasterForm.setCreatedByUser(misSessionBean.getEnteredBy());
			try{
			if (MisUtility.ifEmpty(gramPanchayatMasterForm)) {
				status = gramPanchayatMasterBo.saveGramPanchayat(gramPanchayatMasterForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save","Gram Panchayat Name " + gramPanchayatMasterForm.getNameofGramPanchayat());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				 
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Gram Panchayat Name ---->" + gramPanchayatMasterForm.getNameofGramPanchayat());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			}
		 catch (MISException e) {
			
			System.out.println("e.getCode()------------->"+e.getCode());
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "Entry Alreaady exists for " + gramPanchayatMasterForm.getVillage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		}
			refreshgramPanchayatMasterForm(gramPanchayatMasterForm);

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
		GramPanchayatMasterForm gramPanchayatMasterForm= ( GramPanchayatMasterForm) form;
		refreshgramPanchayatMasterForm(gramPanchayatMasterForm);
		System.out.println("Unspecified........GramPanchayatMasterAction.........");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

	
	private void refreshgramPanchayatMasterForm(GramPanchayatMasterForm gramPanchayatMasterForm) {

		gramPanchayatMasterForm.setDistrict(null);
		gramPanchayatMasterForm.setBlock(null);
		gramPanchayatMasterForm.setVillage(null);
		gramPanchayatMasterForm.setGramPanchayaMasterGrid(getgramPanchayatDatagrid(null));
		

	}
	
	private Datagrid getgramPanchayatDatagrid(List<GramPanchayatMasterGrid> gramPanchayatDatagrid) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(GramPanchayatMasterGrid.class);
		List<GramPanchayatMasterGrid> villageBeans = new ArrayList<GramPanchayatMasterGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}
	
	
	
	//checkVillagenStatus
		public ActionForward checkVillagenStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws MISException {
			
			Boolean statusCategory = false;
			
			String status = null;
			String village = null;
			
			
			status = request.getParameter("status");
			village = request.getParameter("village");
			
					StringBuffer buffer = new StringBuffer();
			try {
				
				//TO DO Return True and False
				if (MisUtility.ifEmpty(village) && 
						MisUtility.ifEmpty(status)  ){
					
					statusCategory = gramPanchayatMasterDao.getStatusCategory(status,village);
					
					buffer.append(statusCategory);
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
	

}
