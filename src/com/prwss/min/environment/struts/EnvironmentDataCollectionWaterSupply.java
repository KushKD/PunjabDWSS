package com.prwss.min.environment.struts;

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
import com.prwss.min.SDU.form.DivisionActivityMpgForm;
import com.prwss.min.SDU.form.DivisionActivityMpgGrid;
import com.prwss.min.environment.bo.EnvironmentWaterSupplyBo;
import com.prwss.min.environment.form.EnvironmentDataCollectionForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionHealthForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionWaterForm;
import com.prwss.min.environment.form.WaterSupplyGrid;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EnvironmentDataCollectionWaterSupply extends DispatchAction{


	private Logger log = Logger.getLogger(EnvironmentDataCollectionWaterSupply.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private EnvironmentWaterSupplyBo environmentWaterSupplyBo;
	
	
	
	
	
	public EnvironmentWaterSupplyBo getEnvironmentWaterSupplyBo() {
		return environmentWaterSupplyBo;
	}

	public void setEnvironmentWaterSupplyBo(
			EnvironmentWaterSupplyBo environmentWaterSupplyBo) {
		this.environmentWaterSupplyBo = environmentWaterSupplyBo;
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
		
		
		EnvironmentDataCollectionWaterForm divisionActivityMpgForm = (EnvironmentDataCollectionWaterForm) form;
		refreshForm(divisionActivityMpgForm);
		this.setAttrib(request);
		log.debug("Unspecified........Data Collection Form Water Supply");
		System.out.println("Unspecified........Daa Collection Form Water Supply");

		return mapping.findForward("display");
		
		


	}
	
	
	private void refreshForm(EnvironmentDataCollectionWaterForm waterSupplyForm) {

		/*divisionActivityMpgForm.setActivity(null);
		divisionActivityMpgForm.setCampaign(null);
		divisionActivityMpgForm.setCategory(null);
		divisionActivityMpgForm.setComponent(null);
		divisionActivityMpgForm.setStage(null);*/
		waterSupplyForm.setWaterSupplyGrid(getDivisionActDatagrid(null));
	}
	
	private Datagrid getDivisionActDatagrid(List<WaterSupplyGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(WaterSupplyGrid.class);
		List<WaterSupplyGrid> villageBeans = new ArrayList<WaterSupplyGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}
	
	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		int status =0 ;
		EnvironmentDataCollectionWaterForm environmentDataCollectionForm = null;
		
		try {
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			String edsId =  null;
			edsId =  request.getParameter("edsId");
			environmentDataCollectionForm = (EnvironmentDataCollectionWaterForm) actionForm;

			if (MisUtility.ifEmpty(environmentDataCollectionForm)) {
				System.out.println("inside sa-ve----------------------------" + environmentDataCollectionForm.toString());
				 status = environmentWaterSupplyBo.saveWaterSchemeMaster(environmentDataCollectionForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}
			if (status != 0)  {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("server.idFinal");
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
		refreshForm(environmentDataCollectionForm);
		return mapping.findForward("display");
	}
	
	
	
	
	
	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		int status =0 ;
		String edsId = null;
		EnvironmentDataCollectionWaterForm  environmentDataCollectionWaterForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			environmentDataCollectionWaterForm = (EnvironmentDataCollectionWaterForm) actionForm;

			if (MisUtility.ifEmpty(environmentDataCollectionWaterForm)) {
				edsId =  request.getParameter("edsId");
				//edsID
				System.out.println("inside sa-ve----------------------------" + environmentDataCollectionWaterForm.toString());
				 status = environmentWaterSupplyBo.updateMaster(environmentDataCollectionWaterForm ,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}
			if (status != 0)  {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("server.idFinal", status);
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
		//refreshVillageActivityMpgForm(environmentDataCollectionForm);
		return mapping.findForward("display");
	}  
	
	 

	  // Check and Get Data
	  public ActionForward checkData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	      HttpServletResponse response) throws MISException {

	    
	    String appId = null;
	    

	    appId = request.getParameter("appId");

	    System.out.println("appId id is----------" + appId);

	    EnvironmentDataCollectionWaterForm formData;

	    StringBuffer buffer = new StringBuffer();

	    try {
	    	formData = environmentWaterSupplyBo.getEnvironmentDataSheetData(appId);
	      System.out.println(formData);
	      
	      Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(formData);
			System.out.println(json2);
	
			 buffer.append(json2);
	      PrintWriter out = response.getWriter();
	      if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
	        out.print(buffer.toString());
	      }
	      
	    }catch(Exception e){ log.error(e); }

	  return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
