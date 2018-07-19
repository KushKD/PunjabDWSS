/**
 * 
 */
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
import com.prwss.min.sanitation.bean.BaseLineMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bo.BaseLineBo;
import com.prwss.min.sanitation.dao.BaseLineDao;
import com.prwss.min.sanitation.form.BaseLineForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BaselineSurveyAction extends DispatchAction {

	private Logger log = Logger.getLogger(BaselineSurveyAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private BaseLineBo baseLineBo;
	private BaseLineDao baseLineDao;
	
	public BaseLineDao getBaseLineDao() {
		return baseLineDao;
	}

	public void setBaseLineDao(BaseLineDao baseLineDao) {
		this.baseLineDao = baseLineDao;
	}

	public BaseLineBo getBaseLineBo() {
		return baseLineBo;
	}

	public void setBaseLineBo(BaseLineBo baseLineBo) {
		this.baseLineBo = baseLineBo;
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
		String json2=null;
		try {
			this.setAttrib(request);
			BaseLineForm baseLineForm = (BaseLineForm) form;
			baseLineForm.setSurveyId(request.getParameter("surveyId"));
			baseLineForm.setDistrict(request.getParameter("district"));
			baseLineForm.setBlock(request.getParameter("block"));
			baseLineForm.setVillage(request.getParameter("village"));
			baseLineForm.setGramPanchayatId(request.getParameter("gramPanchayatId"));
			if (MisUtility.ifEmpty(baseLineForm)) {
				beneficiaryDto = baseLineBo.getBeneficiaryDetails(baseLineForm);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				
				if(!MisUtility.ifEmpty(beneficiaryDto)){
					json2 = gson.toJson(beneficiaryDto);
				}else{
					json2 = gson.toJson("No Record Found");
				}
				
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
			}
			if (MisUtility.ifEmpty(beneficiaryDto)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "No Record Found");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {

		}
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		System.out.println("------------inside baseline save--------");
		boolean status = false;
		PrintWriter out = MisUtility.getPrintWriter(response);
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			
			String surveyName=request.getParameter("surveyName");
			BaseLineForm baseLineForm = (BaseLineForm) form;
			baseLineForm.setCreatedBy(misAuditBean.getEnteredBy());
			baseLineForm.setSurveyId(request.getParameter("surveyId"));
			baseLineForm.setDistrict(request.getParameter("district"));
			baseLineForm.setBlock(request.getParameter("block"));
			baseLineForm.setVillage(request.getParameter("village"));
			baseLineForm.setGramPanchayatId(request.getParameter("gramPanchayatId"));

			if (MisUtility.ifEmpty(baseLineForm)) {
				if (!MisUtility.ifEmpty(request.getParameter("beneficiary"))) {
					throw new MISException(MISExceptionCodes.MIS003, "Please Check Atleast one checkbox");
				}
				status = baseLineBo.saveBeneficiaryDetails(baseLineForm, request.getParameter("beneficiary"));
			}
			if (status) {
				out.print("Succefully saved for Survey " + "\t" + surveyName);
			} else {
				out.print("Internal error");
			}

		} catch (MISException e) {
			request.setAttribute("level2", "true");
			if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				out.print(e.getMessage());
			}
		} catch (Exception e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			out.print(e.getLocalizedMessage());
		}
		return null;
	}
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		System.out.println("------------inside baseline update--------"+request.getParameter("baseLineId"));
		boolean status = false;
		PrintWriter out = MisUtility.getPrintWriter(response);
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			if (MisUtility.ifEmpty(request.getParameter("baseLineId"))) {
				
				status = baseLineBo.updateSurvey(request.getParameter("surveyId"),request.getParameter("baseLineId"));
			}
			if (status) {
				out.print("Record successfully update");
			} else {
				out.print("Internal error");
			}

		} catch (MISException e) {
			request.setAttribute("level2", "true");
			if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				out.print(e.getMessage());
			}
		} catch (Exception e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			out.print(e.getLocalizedMessage());
		}
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		System.out.println("------------inside baseline delete--------");
		boolean status = false;
		PrintWriter out = MisUtility.getPrintWriter(response);
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			System.out.println("--1-----"+request.getParameter("beneficiary"));
			if (MisUtility.ifEmpty(request.getParameter("beneficiary"))) {
				status = baseLineDao.deleteSurveyDetails(request.getParameter("beneficiary"));
			}
			if (status) {
				out.print("Succefully deleted for Beneficiary Id " + "\t" + request.getParameter("beneficiary"));
			} 

		}  catch (Exception e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			out.print(e.getLocalizedMessage());
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

		System.out.println("Unspecified........BaselineSurveyAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}
	
	public ActionForward getAddedSurvey(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("---------------------inside getAddedSurvey-------------------------------");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		List<BeneficiaryDto> beneficiaryDto = null;
		 List<BaseLineMasterBean> baseLineMasterBeans=null;
		try {
			this.setAttrib(request);
			BaseLineForm baseLineForm = (BaseLineForm) form;
			baseLineForm.setSurveyId(request.getParameter("surveyId"));
			baseLineForm.setDistrict(request.getParameter("district"));
			baseLineForm.setBlock(request.getParameter("block"));
			baseLineForm.setVillage(request.getParameter("village"));
			baseLineForm.setGramPanchayatId(request.getParameter("gramPanchayatId"));
			if (MisUtility.ifEmpty(baseLineForm)) {
				baseLineMasterBeans= baseLineDao.verifySurveyStatus(baseLineForm);
				if(MisUtility.ifEmpty(baseLineMasterBeans)){
					beneficiaryDto = baseLineBo.getSurveyDetails(baseLineForm);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json2 = gson.toJson(beneficiaryDto);
					PrintWriter out = MisUtility.getPrintWriter(response);
					out.print(json2);
				}
			}
			if (MisUtility.ifEmpty(beneficiaryDto)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "No Record Found");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {

		}
		return null;
	}
}
