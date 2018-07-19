/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bo.ValidateBeneficiaryBo;
import com.prwss.min.sanitation.dao.ValidateBeneficiaryDao;
import com.prwss.min.sanitation.form.ValidateBeneficiaryForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ValidateBeneficiaryAction extends DispatchAction{

	private Logger log = Logger.getLogger(ValidateBeneficiaryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ValidateBeneficiaryBo validateBeneficiaryBo;
	private ValidateBeneficiaryDao validateBeneficiaryDao;
	
	
	public ValidateBeneficiaryDao getValidateBeneficiaryDao() {
		return validateBeneficiaryDao;
	}

	public void setValidateBeneficiaryDao(ValidateBeneficiaryDao validateBeneficiaryDao) {
		this.validateBeneficiaryDao = validateBeneficiaryDao;
	}

	public ValidateBeneficiaryBo getValidateBeneficiaryBo() {
		return validateBeneficiaryBo;
	}

	public void setValidateBeneficiaryBo(ValidateBeneficiaryBo validateBeneficiaryBo) {
		this.validateBeneficiaryBo = validateBeneficiaryBo;
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
		List<BeneficiaryDto> beneficiaryDto = null;
		PrintWriter out = null;
		try {
			 out = MisUtility.getPrintWriter(response);
			this.setAttrib(request);
			ValidateBeneficiaryForm validateBeneficiaryForm = (ValidateBeneficiaryForm) form;
			
			validateBeneficiaryForm.setSurveyId(request.getParameter("surveyId"));
			if (MisUtility.ifEmpty(validateBeneficiaryForm)) {
				beneficiaryDto = validateBeneficiaryBo.getBeneficiaryDetails(validateBeneficiaryForm);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryDto);
				out.print(json2);
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {
			out.print("Internal error");
		}
		return null;
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("inside save--------");
		
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		PrintWriter out = null;
		String entBy=String.valueOf(misAuditBean.getEnteredBy());
		String[] jsonArr=request.getParameterValues("jsonArr");
		String surveyId=request.getParameter("surveyId");
		boolean flag=false;
		try {
			System.out.println("in action ------"+entBy);
			 out = MisUtility.getPrintWriter(response);
			JSONParser parser=new JSONParser();
			JSONArray jsonArray = (JSONArray)parser.parse(Arrays.toString(jsonArr));
			if(!MisUtility.ifEmpty(jsonArray)){
				flag=validateBeneficiaryBo.saveBeneficiaryDetails(jsonArray,surveyId,entBy);
			}
			if(flag){
				out.print("Successfully Saved");	
			}
		}  catch (Exception e) {
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
	
	public ActionForward getBeneficiary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		List<BeneficiaryDto> beneficiaryDto = null;
		PrintWriter out = null;
		try {
			 out = MisUtility.getPrintWriter(response);
			this.setAttrib(request);
			
			System.out.println(request.getParameter("surveyId"));
			if(MisUtility.ifEmpty(request.getParameter("surveyId"))){
				beneficiaryDto=validateBeneficiaryDao.getBeneficiary(request.getParameter("surveyId"));
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryDto);
				out.print(json2);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getAddedBeneficiary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		List<BeneficiaryDto> beneficiaryDto = null;
		PrintWriter out = null;
		try {
			 out = MisUtility.getPrintWriter(response);
			this.setAttrib(request);
			
			if(MisUtility.ifEmpty(request.getParameter("surveyId"))){
				beneficiaryDto=validateBeneficiaryDao.getAddedBeneficiary(request.getParameter("surveyId"),misAuditBean.getEnteredBy());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryDto);
				System.out.println(json2);
				out.print(json2);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}
	public ActionForward updateValidationRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		boolean status=false;
		PrintWriter out = null;
		try {
			 out = MisUtility.getPrintWriter(response);
			this.setAttrib(request);
			String entBy=String.valueOf(misAuditBean.getEnteredBy());
			if(MisUtility.ifEmpty(request.getParameter("validationRequestId"))){
				status=validateBeneficiaryBo.updateValidationRequest(request.getParameter("validationRequestId").split(","),entBy);
				if(status){
					out.print("Successfully Forwarded");
				}
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
