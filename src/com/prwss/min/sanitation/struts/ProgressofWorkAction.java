package com.prwss.min.sanitation.struts;

import java.io.IOException;

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

import com.prwss.min.sanitation.bo.ProgressofWorkBo;
import com.prwss.min.sanitation.dao.ProgressofWorkDao;
import com.prwss.min.sanitation.form.ProgressofWorkForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

public class ProgressofWorkAction extends DispatchAction {

	private Logger log = Logger.getLogger(ProgressofWorkAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ProgressofWorkBo progressofWorkBo;
	private ProgressofWorkDao progressofWorkDao;

	

	public ProgressofWorkBo getProgressofWorkBo() {
		return progressofWorkBo;
	}

	public void setProgressofWorkBo(ProgressofWorkBo progressofWorkBo) {
		this.progressofWorkBo = progressofWorkBo;
	}

	public ProgressofWorkDao getProgressofWorkDao() {
		return progressofWorkDao;
	}

	public void setProgressofWorkDao(ProgressofWorkDao progressofWorkDao) {
		this.progressofWorkDao = progressofWorkDao;
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
		ProgressofWorkForm progressofWorkForm = (ProgressofWorkForm) form;
		
		System.out.println("inside save SurveyMasterForm --------------------" + progressofWorkForm.toString());
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
						
				
			} else {
				return mapping.findForward("login");
			}
			
			/*List<ProgressofWorkBean> progressofWorkdetails=progressofWorkDao.findSurveyCollection(progressofWorkForm);
			if(!MisUtility.ifEmpty(surveyMasterdetails)){
				throw new MISException(MISExceptionCodes.MIS002,"Entry Already Exist for ("+progressofWorkForm.getSurveyName()+")");				
			}*/
			if (MisUtility.ifEmpty(progressofWorkForm)) {
							
							if (MisUtility.ifEmpty(progressofWorkForm.getPhotograph1().getFileSize())){
								System.out.println(" --------------------" + progressofWorkForm.getPhotograph1().getFileName());
							if(!(progressofWorkForm.getPhotograph1().getFileName().endsWith(".jpeg")|| progressofWorkForm.getPhotograph1().getFileName().endsWith(".png")||progressofWorkForm.getPhotograph1().getFileName().endsWith(".jpg") || progressofWorkForm.getPhotograph1().getFileName().endsWith(".JPEG")|| progressofWorkForm.getPhotograph1().getFileName().endsWith(".PNG")||progressofWorkForm.getPhotograph1().getFileName().endsWith(".JPG"))){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File Format should be of jpeg/jpg/png");
							}
							if(progressofWorkForm.getPhotograph1().getFileSize()>512000){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File size should not be greater than 500 KB");
							}
							}
							
							if (MisUtility.ifEmpty(progressofWorkForm.getPhotograph2().getFileSize())){
								System.out.println(" --------------------" + progressofWorkForm.getPhotograph2().getFileName());
							if(!(progressofWorkForm.getPhotograph2().getFileName().endsWith(".jpeg")|| progressofWorkForm.getPhotograph2().getFileName().endsWith(".png")||progressofWorkForm.getPhotograph2().getFileName().endsWith(".jpg") || progressofWorkForm.getPhotograph2().getFileName().endsWith(".JPEG")|| progressofWorkForm.getPhotograph2().getFileName().endsWith(".png")||progressofWorkForm.getPhotograph2().getFileName().endsWith(".jpg"))){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File Format should be of jpeg/jpg/png");
							}
							if(progressofWorkForm.getPhotograph2().getFileSize()>512000){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File size should not be greater than 500 KB");
							}
							}
							
							if (MisUtility.ifEmpty(progressofWorkForm.getPhotograph3().getFileSize())){
								System.out.println(" --------------------" + progressofWorkForm.getPhotograph3().getFileName());
							if(!(progressofWorkForm.getPhotograph3().getFileName().endsWith(".jpeg")|| progressofWorkForm.getPhotograph3().getFileName().endsWith(".png")||progressofWorkForm.getPhotograph3().getFileName().endsWith(".jpg"))){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File Format should be of jpeg/jpg/png");
							}
							if(progressofWorkForm.getPhotograph3().getFileSize()>512000){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File size should not be greater than 500 KB");
							}
							}
			}
							
			progressofWorkForm.setCreatedByUser(misSessionBean.getEnteredBy());
			try{
				if (MisUtility.ifEmpty(progressofWorkForm)) {
					status = progressofWorkBo.saveProgressofWork(progressofWorkForm);
				}
			System.out.println("-------- inside IF --------------------");
			
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Entry Saved Sucessesfully");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				 refreshprogressofWorkForm(progressofWorkForm);
				 
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Entry failed to save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
	} catch (MISException e) {
		
		System.out.println("e.getCode()------------->"+e.getCode());
		if (MISExceptionCodes.MIS003.equals(e.getCode())) {	
		log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	}
	return mapping.findForward("display");
		
			
	}
	
	
/*	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
	
	private void refreshprogressofWorkForm(ProgressofWorkForm progressofWorkForm) {

		progressofWorkForm.setBeneficiaryId(null);
		progressofWorkForm.setDistrict(null);
		progressofWorkForm.setVillage(null);
		progressofWorkForm.setBlock(null);
		progressofWorkForm.setGramPanchayat(null);
		progressofWorkForm.setStatus1(null);
		progressofWorkForm.setStatus2(null);
		progressofWorkForm.setStatus3(null);
		progressofWorkForm.setPhotograph1(null);
		progressofWorkForm.setPhotograph2(null);
		progressofWorkForm.setPhotograph3(null);

		

	}


	
	/*=============================================================================================================
			================================================================================================================
			=================================================================================================================
			=======================================================*/
/*	@SuppressWarnings("unchecked")
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
		if (!MisUtility.ifEmpty(locationMasterList) && clickedColumn != 0) {
			locMasterLst = locationMasterBo.getListBasedOnColumnSorting(locationMasterList, clickedColumn,
					clickedColumnDir, locationMasterForm);
			locationJson.setAaData(locMasterLst);
		}
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
	}*/
}
