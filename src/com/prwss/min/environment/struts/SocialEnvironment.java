package com.prwss.min.environment.struts;

import java.io.PrintWriter;

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
import com.prwss.min.environment.bean.EnvironmentSocialBean;
import com.prwss.min.environment.bo.EnvironmentMasterSocialBo;
import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionSocialForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class SocialEnvironment extends DispatchAction {

	
private Logger log = Logger.getLogger(SocialEnvironment.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private EnvironmentMasterSocialBo environmentSocialBo;
	
	
	
	
	
	public EnvironmentMasterSocialBo getEnvironmentSocialBo() {
		return environmentSocialBo;
	}

	public void setEnvironmentSocialBo(EnvironmentMasterSocialBo environmentSocialBo) {
		this.environmentSocialBo = environmentSocialBo;
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

		String edsId = null;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		
		System.out.println("Unspecified........Daa Collection Form HealthAndSocialEnvironment" );

		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		int status =0 ;
		String edsId = null;
		EnvironmentDataCollectionSocialForm  socailEnvironmentForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			socailEnvironmentForm = (EnvironmentDataCollectionSocialForm) actionForm;

			if (MisUtility.ifEmpty(socailEnvironmentForm)) {
				edsId =  request.getParameter("edsId");
				//edsID
				System.out.println("inside sa-ve----------------------------" + socailEnvironmentForm.toString());
				 status = environmentSocialBo.saveMaster(socailEnvironmentForm ,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
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
		//refreshVillageActivityMpgForm(environmentDataCollectionForm);
		return mapping.findForward("display");
	}
	
	
	
	
	
	
	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		int status =0 ;
		String edsId = null;
		EnvironmentDataCollectionSocialForm  socialEnvironmentForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			socialEnvironmentForm = (EnvironmentDataCollectionSocialForm) actionForm;

			if (MisUtility.ifEmpty(socialEnvironmentForm)) {
				edsId =  request.getParameter("edsId");
				//edsID
				System.out.println("inside sa-ve----------------------------" + socialEnvironmentForm.toString());
				 status = environmentSocialBo.updateMaster(socialEnvironmentForm ,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
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
		//refreshVillageActivityMpgForm(environmentDataCollectionForm);
		return mapping.findForward("display");
	}
	
	 

	  // Check and Get Data
	  public ActionForward checkData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	      HttpServletResponse response) throws MISException {

	    
	    String appId = null;
	    

	    appId = request.getParameter("appId");

	    System.out.println("appId id is----------" + appId);

	    EnvironmentDataCollectionSocialForm formData;

	    StringBuffer buffer = new StringBuffer();

	    try {
	    	formData = environmentSocialBo.getEnvironmentDataSheetData(appId);
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
