package com.prwss.min.environment.struts;

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
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.form.VillageActivityMpgForm;
import com.prwss.min.environment.bo.EnvironmentEDSMasterBo;
import com.prwss.min.environment.dao.EnvironmentDataCollectionDao;
import com.prwss.min.environment.form.EnvironmentDataCollectionForm;
import com.prwss.min.finance.struts.AllocationAction;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentDataCollection extends DispatchAction{


	private Logger log = Logger.getLogger(EnvironmentDataCollection.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private EnvironmentDataCollectionDao environmentDataCollectionDao;
	private EnvironmentEDSMasterBo environmentDataCollectionEdsMaster;
	
	
	
	
	
	public EnvironmentEDSMasterBo getEnvironmentDataCollectionEdsMaster() {
		return environmentDataCollectionEdsMaster;
	}

	public void setEnvironmentDataCollectionEdsMaster(
			EnvironmentEDSMasterBo environmentDataCollectionEdsMaster) {
		this.environmentDataCollectionEdsMaster = environmentDataCollectionEdsMaster;
	}

	public EnvironmentDataCollectionDao getEnvironmentDataCollectionDao() {
		return environmentDataCollectionDao;
	}

	public void setEnvironmentDataCollectionDao(
			EnvironmentDataCollectionDao environmentDataCollectionDao) {
		this.environmentDataCollectionDao = environmentDataCollectionDao;
	}

	@Override
	public String toString() {
		return "EnvironmentDataCollection [environmentDataCollectionDao="
				+ environmentDataCollectionDao + "]";
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
		//AllocationForm allocationForm = (AllocationForm) form;
		//allocationForm(allocationForm);
		log.debug("Unspecified........Data Collection Form");
		System.out.println("Unspecified........Daa Collection Form");

		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward populateDataEDS (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		
		try{
			List<EnvironmentDataCollectionForm> edsLst = null;
		int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
		String clickedColumnDir = request.getParameter("sSortDir_0");
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Integer pageNumber = 0;
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (iDisplayStart / pageDisplayLength) + 1;
		
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");
		edsLst = new ArrayList<EnvironmentDataCollectionForm>();
		edsLst = environmentDataCollectionEdsMaster.getdataByPagination(searchParameter, clickedColumn, clickedColumnDir);

		LocationJsonObject<EnvironmentDataCollectionForm> edsJson = new LocationJsonObject<EnvironmentDataCollectionForm>();
		
		if (MisUtility.ifEmpty(edsLst)) {
			edsJson.setAaData(new ArrayList<EnvironmentDataCollectionForm>());
		}
		
		if(!MisUtility.ifEmpty(edsLst)){
			edsJson.setiTotalDisplayRecords(edsLst.size());
			edsJson.setiTotalRecords(edsLst.size());
		}

		List<EnvironmentDataCollectionForm> locMasterLst = null;
		if (!MisUtility.ifEmpty(edsLst)) {
			locMasterLst = environmentDataCollectionEdsMaster.getListBasedOnPageNumber(edsLst, pageDisplayLength, pageNumber,iDisplayStart);
			edsJson.setAaData(locMasterLst);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(edsJson);
		System.out.println(json2);
		PrintWriter out = MisUtility.getPrintWriter(response);
		out.print(json2);
		//}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//GetGramPanchayat
	public ActionForward getGramPanchayat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getGramPanchayat");
		String villageID = null;
		
		villageID = request.getParameter("villageId");
		

		System.out.println("financial year id is----------" + villageID);

		List<Integer> gramPanchayatId = null;
		List<GramPanchayatMasterBean> gramPanchayatNameAndId = null;
		
		if(MisUtility.ifEmpty(villageID)){
			gramPanchayatId = new ArrayList<Integer>();
			gramPanchayatId = environmentDataCollectionDao.getGramPanchayatId(villageID);
			
			if(!MisUtility.ifEmpty(gramPanchayatId)){
				gramPanchayatNameAndId = environmentDataCollectionDao.getGramPanchayatDetails(gramPanchayatId);
			}
			
		}
		
		

		StringBuffer buffer = new StringBuffer();

		try {
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Gram Panchayat");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(gramPanchayatNameAndId)) {
				for (GramPanchayatMasterBean stageComponentBean : gramPanchayatNameAndId) {
					buffer.append("<option value=\"").append(stageComponentBean.getGramPanchayatId()).append("\">");
					buffer.append(stageComponentBean.getNameofGramPanchayat());
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
	
	
	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws MISException {
		int status =0 ;
		EnvironmentDataCollectionForm environmentDataCollectionForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			environmentDataCollectionForm = (EnvironmentDataCollectionForm) actionForm;

			if (MisUtility.ifEmpty(environmentDataCollectionForm)) {
				System.out.println("inside sa-ve----------------------------" + environmentDataCollectionForm.toString());
				 status = environmentDataCollectionEdsMaster.updateMaster(environmentDataCollectionForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}
			if (status != 0) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("server.id", status);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshVillageActivityMpgForm(environmentDataCollectionForm);
		return mapping.findForward("display");
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		int status =0 ;
		EnvironmentDataCollectionForm environmentDataCollectionForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			environmentDataCollectionForm = (EnvironmentDataCollectionForm) actionForm;

			if (MisUtility.ifEmpty(environmentDataCollectionForm)) {
				System.out.println("inside sa-ve----------------------------" + environmentDataCollectionForm.toString());
				 status = environmentDataCollectionEdsMaster.saveMaster(environmentDataCollectionForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}
			if (status != 0)  {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("server.id", status);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshVillageActivityMpgForm(environmentDataCollectionForm);
		return mapping.findForward("display");
	}

	private void refreshVillageActivityMpgForm( EnvironmentDataCollectionForm environmentDataCollectionForm) {
		
		
		environmentDataCollectionForm.setBlock("");
		environmentDataCollectionForm.setZone("");  
		environmentDataCollectionForm.setDistrict("");
		environmentDataCollectionForm.setSchemeCategory("");
		environmentDataCollectionForm.setSchemeId("");
		environmentDataCollectionForm.setSchemeType("");
		environmentDataCollectionForm.setVi("");
		environmentDataCollectionForm.setVillageId("");
	}
	
	
	

}
