package com.prwss.min.sanitation.struts;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.min.sanitation.bo.SurveyMasterBO;
import com.prwss.min.sanitation.dao.SurveyMasterDao;
import com.prwss.min.sanitation.form.SurveyMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

public class SurveyMasterAction extends DispatchAction {

	private Logger log = Logger.getLogger(SurveyMasterAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private SurveyMasterBO surveyMasterBO;
	private SurveyMasterDao surveyMasterDao;

	public SurveyMasterDao getSurveyMasterDao() {
		return surveyMasterDao;
	}

	public void setSurveyMasterDao(SurveyMasterDao surveyMasterDao) {
		this.surveyMasterDao = surveyMasterDao;
	}

	public SurveyMasterBO getSurveyMasterBO() {
		return surveyMasterBO;
	}

	public void setSurveyMasterBO(SurveyMasterBO surveyMasterBO) {
		this.surveyMasterBO = surveyMasterBO;
	}

	

	/**
	 * @return the misAuditBean
	 */
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

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		SurveyMasterForm surveymasterform = (SurveyMasterForm) form;
		
		System.out.println("inside save SurveyMasterForm --------------------" + surveymasterform.toString());
		try{
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
						
				
			} else {
				return mapping.findForward("login");
			}
			
			List<SurveyMasterBean> surveyMasterdetails=surveyMasterDao.findSurveyCollection(surveymasterform);
			if(!MisUtility.ifEmpty(surveyMasterdetails)){
				throw new MISException(MISExceptionCodes.MIS002,"Entry Already Exist for ("+surveymasterform.getSurveyName()+")");				
			}
			surveymasterform.setCreatedByUser(misSessionBean.getEnteredBy());
			status=surveyMasterBO.saveSurveyMasterData(surveymasterform);
			System.out.println("-------- inside IF --------------------");
			
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully saved  "+"\t" + surveymasterform.getSurveyName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				/*
				 * refreshMasterLocationForm(locationMasterForm);
				 */
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Entry " +"\t"+ surveymasterform.getSurveyName()+"\t"+ "failed to save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
	} catch (MISException e) {
		
		System.out.println("e.getCode()------------->"+e.getCode());
		if (MISExceptionCodes.MIS002.equals(e.getCode())) {	
		log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	}
			
			
			return mapping.findForward("display");
		

	}
	
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		SurveyMasterForm surveyMasterForm = (SurveyMasterForm) form;
		
		
		try {
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			surveyMasterForm.setCreatedByUser(misSessionBean.getEnteredBy());
		
			if (MisUtility.ifEmpty(surveyMasterForm)) {
				status = surveyMasterBO.updateSurvey(surveyMasterForm);
			}
			if (status) {
				
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Entry" +"\t"+ surveyMasterForm.getSurveyName()+"\t"+ "successfully updated.");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				
				  refreshMasterLocationForm(surveyMasterForm);
				 
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Location ---->" + surveyMasterForm.getSurveyName());
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
			}else{
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}
			catch (Exception e) {
					log.error(e.getLocalizedMessage(), e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
		}
		refreshMasterLocationForm(surveyMasterForm);
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

		System.out.println("Unspecified........Survey");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	private void refreshMasterLocationForm(SurveyMasterForm surveyMasterForm) {

		surveyMasterForm.setSurveyName(null);
		surveyMasterForm.setSurveyStatus(null);
		surveyMasterForm.setPlannedStartDate(null);
		surveyMasterForm.setPlannedEndDate(null);
		surveyMasterForm.setActualStartDate(null);
		surveyMasterForm.setActualEndDate(null);
		surveyMasterForm.setPurpose(null);

		

	}


	
	/*=============================================================================================================
			================================================================================================================
			=================================================================================================================
			=======================================================*/
	@SuppressWarnings("unchecked")
	public ActionForward populateSurveyMasterData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		try{
			List<SurveyMasterForm> surveyMasterLst = null;
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
		
		surveyMasterLst = surveyMasterBO.getSurveyMasterByPagination();

		
		System.out.println("SurveyMaster ========================================"+ surveyMasterLst.size());
		
		//if(!MisUtility.ifEmpty(labMasterLst)){
		LocationJsonObject<SurveyMasterForm> locationJson = new LocationJsonObject<SurveyMasterForm>();
		//locationJson.setDraw(Integer.parseInt(draw));
		if(!MisUtility.ifEmpty(surveyMasterLst)){
			locationJson.setiTotalDisplayRecords(surveyMasterLst.size());
			locationJson.setiTotalRecords(surveyMasterLst.size());
		}

		List<SurveyMasterForm> locMasterLst = null;
		if (!MisUtility.ifEmpty(surveyMasterLst)) {
			locMasterLst = surveyMasterBO.getListBasedOnPageNumber(surveyMasterLst, pageDisplayLength, pageNumber,iDisplayStart);
			locationJson.setAaData(surveyMasterLst);
		}
		if ((!MisUtility.ifEmpty(surveyMasterLst)) && MisUtility.ifEmpty(searchParameter)) {
			locMasterLst = surveyMasterBO.getListBasedOnSearchParameter(searchParameter, surveyMasterLst);
			locationJson.setAaData(surveyMasterLst);
		}
		/*if (!MisUtility.ifEmpty(locationMasterList) && clickedColumn != 0) {
			locMasterLst = locationMasterBo.getListBasedOnColumnSorting(locationMasterList, clickedColumn,
					clickedColumnDir, locationMasterForm);
			locationJson.setAaData(locMasterLst);
		}*/
		if(MisUtility.ifEmpty(surveyMasterLst)){
			locationJson.setAaData(surveyMasterLst);
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
}
