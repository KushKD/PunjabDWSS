/**
 * 
 */
package com.prwss.min.sanitation.struts;

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
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bo.ValidateRequestBo;
import com.prwss.min.sanitation.dao.ValidateRequestDao;
import com.prwss.min.sanitation.form.ValidateRequestForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ValidateRequestAction extends DispatchAction {

	private Logger log = Logger.getLogger(ValidateRequestAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ValidateRequestBo validateRequestBo;
	private ValidateRequestDao validateRequestDao;
	
	
	
	public ValidateRequestBo getValidateRequestBo() {
		return validateRequestBo;
	}

	public void setValidateRequestBo(ValidateRequestBo validateRequestBo) {
		this.validateRequestBo = validateRequestBo;
	}

	public ValidateRequestDao getValidateRequestDao() {
		return validateRequestDao;
	}

	public void setValidateRequestDao(ValidateRequestDao validateRequestDao) {
		this.validateRequestDao = validateRequestDao;
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
	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("---------------------inside find-------------------------------");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		
		ValidateRequestForm validateBeneficiaryForm=(ValidateRequestForm)form;
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			this.setAttrib(request);
				beneficiaryDto = validateRequestBo.getFreezedSurvey();
				System.out.println("----------------after-----inside find-------------------------------");
				
				System.out.println("validateBeneficiaryForm-----------"+validateBeneficiaryForm.getBeneficiaryDto());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryDto);
				
				System.out.println("json2------------"+json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
			if (MisUtility.ifEmpty(beneficiaryDto)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "No Record Found");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("success.save", "No Record Found");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return null;
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

		System.out.println("Unspecified........ValidateBeneficiaryAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}
	public ActionForward populateSurvey(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<BeneficiaryDto>  beneficiaryDtos=null;
		try{
			String surveyId=request.getParameter("surveyId");
			if(MisUtility.ifEmpty(surveyId)){
				beneficiaryDtos=validateRequestDao.findFreezedSurvey(surveyId);
				System.out.println("beneficiaryDtos------------"+beneficiaryDtos.toString());
			}
			if(!MisUtility.ifEmpty(beneficiaryDtos)){
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryDtos);
				
				System.out.println("json2------------"+json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
			}
		}catch(Exception e){
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}
	public ActionForward saveEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		
		boolean status=false;
		try{
			PrintWriter out = MisUtility.getPrintWriter(response);
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			ValidateRequestForm validateRequestForm=(ValidateRequestForm)form;
			long entBy=misAuditBean.getEnteredBy();
			System.out.println("----------1-----------"+validateRequestForm.getSurveyId());
			if(MisUtility.ifEmpty(validateRequestForm.getSurveyId())){
				status=validateRequestBo.saveEmpSurvey(validateRequestForm.getSurveyId(),entBy);
			}
			if(status){
			
				/*ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Forwarded");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);*/
				out.print("successfully forwarded");
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Internal error please check logs");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
		}catch(Exception e){
			log.debug(e.getLocalizedMessage());
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("success.save",
					"Internal error please check logs");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return  null;
	}
	
}
