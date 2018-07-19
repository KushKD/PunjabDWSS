/**
 * 
 */
package com.prwss.min.quality.struts;

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
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.dao.LabMasterDao;
import com.prwss.min.quality.LabMasterBo;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author bhsingh
 *
 */
public class LabMasterAction extends DispatchAction  {

	
	private Logger log = Logger.getLogger(LabMasterAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private LabMasterBo<LabMasterForm> labMasterBo;
	private LabMasterDao labMasterDao;
	
	
	
	
	
	/**
	 * @return the labMasterDao
	 */
	public LabMasterDao getLabMasterDao() {
		return labMasterDao;
	}
	/**
	 * @param labMasterDao the labMasterDao to set
	 */
	public void setLabMasterDao(LabMasterDao labMasterDao) {
		this.labMasterDao = labMasterDao;
	}
	
	/**
	 * @param labMasterBo the labMasterBo to set
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setLabMasterBo(LabMasterBo labMasterBo) {
		this.labMasterBo = labMasterBo;
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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		LabMasterForm labMasterForm = (LabMasterForm) form;
		
		System.out.println("--------------------inside save --------------------");
		log.debug("Inside save labMaster->"+labMasterForm.toString());
		try {
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			labMasterForm.setEnteredBy(misSessionBean.getEnteredBy());
			
			List<LabMasterBean> labMasterdetails=labMasterDao.getLabMasterData(labMasterForm);
			if(!MisUtility.ifEmpty(labMasterdetails)){
				throw new MISException(MISExceptionCodes.MIS002,"Entry Already Exist for Lab("+labMasterForm.getLabName()+")");				
			}
			if (MisUtility.ifEmpty(labMasterForm)) {
				status = labMasterBo.saveLabMaster(labMasterForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Added New Lab:" + "\t" +  labMasterForm.getLabName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				/*
				 * refreshMasterLocationForm(locationMasterForm);
				 */
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Internal error" + "\t" +  labMasterForm.getLabName());
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
		catch(Exception e){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshMasterLocationForm(labMasterForm);
		
		return mapping.findForward("display");

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		LabMasterForm labMasterForm = (LabMasterForm) form;
		
		System.out.println("inside update --------------------");
		try {
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			labMasterForm.setEnteredBy(misSessionBean.getEnteredBy());
			
			/*List<LabMasterBean> labMasterdetails=labMasterDao.getLabMasterData(labMasterForm);
			if(!MisUtility.ifEmpty(labMasterdetails)){
				throw new MISException(MISExceptionCodes.MIS002,"Entry Already Exist for Lab("+labMasterForm.getLabName()+")");				
			}*/
			if (MisUtility.ifEmpty(labMasterForm)) {
				status = labMasterBo.updateLab(labMasterForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"SuccessFully update record for " + "\t" +labMasterForm.getLabName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				/*
				 * refreshMasterLocationForm(locationMasterForm);
				 */
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Successfully Saved" + "\t" +  labMasterForm.getLabName());
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
		refreshMasterLocationForm(labMasterForm);
		
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

		System.out.println("Unspecified........LabMasterAction.........");
		return mapping.findForward("display");
	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	

	private void refreshMasterLocationForm(LabMasterForm labMasterForm) {

		labMasterForm.setBlock(null);
		labMasterForm.setDistrict(null);
		labMasterForm.setLabName(null);
		labMasterForm.setLocationId(null);
		labMasterForm.setEndDate(null);
		labMasterForm.setAddress(null);
		labMasterForm.setContactPerson(null);
		labMasterForm.setLabLevel(null);
		labMasterForm.setPhoneNo(null);
		labMasterForm.setMobileNo(null);
		labMasterForm.setDivision(null);
	}

	public ActionForward populateLabMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		try{
			List<LabMasterForm> labMasterLst = null;
		int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
		String clickedColumnDir = request.getParameter("sSortDir_0");
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Integer pageNumber = 0;
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (iDisplayStart / pageDisplayLength) + 1;
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");
		labMasterLst = new ArrayList<LabMasterForm>();
		labMasterLst = labMasterBo.getLabMasterByPagination(searchParameter, clickedColumn, clickedColumnDir);

		LocationJsonObject<LabMasterForm> locationJson = new LocationJsonObject<LabMasterForm>();
		
		if (MisUtility.ifEmpty(labMasterLst)) {
			locationJson.setAaData(new ArrayList<LabMasterForm>());
		}
		
		if(!MisUtility.ifEmpty(labMasterLst)){
			locationJson.setiTotalDisplayRecords(labMasterLst.size());
			locationJson.setiTotalRecords(labMasterLst.size());
		}

		List<LabMasterForm> locMasterLst = null;
		if (!MisUtility.ifEmpty(labMasterLst)) {
			locMasterLst = labMasterBo.getListBasedOnPageNumber(labMasterLst, pageDisplayLength, pageNumber,iDisplayStart);
			locationJson.setAaData(locMasterLst);
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
