package com.prwss.min.SDU.Struts;

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
import com.prwss.min.SDU.bean.ConsolidatedPlanDivisionWiseDTO;
import com.prwss.min.SDU.dao.ConsolidatedPlanDivisionWiseDao;
import com.prwss.min.SDU.form.ConsolidatedPlanDivisionWiseForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class ConsolidatedPlanDivisionWiseAction extends DispatchAction{
	
	private Logger log = Logger.getLogger(ConsolidatedPlanDivisionWiseAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private ConsolidatedPlanDivisionWiseDao consolidatedPlanDivisionWiseDao ;
	
	
	public ConsolidatedPlanDivisionWiseDao getConsolidatedPlanDivisionWiseDao() {
		return consolidatedPlanDivisionWiseDao;
	}
	public void setConsolidatedPlanDivisionWiseDao(ConsolidatedPlanDivisionWiseDao consolidatedPlanDivisionWiseDao) {
		this.consolidatedPlanDivisionWiseDao = consolidatedPlanDivisionWiseDao;
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
	
	// -----------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("Unspecified.................");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		
		this.setAttrib(request);
		System.out.println("Unspecified........ConsolidatedPlanDivisionWiseAction.........");

		return mapping.findForward("display");
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getAbstract(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		
		ConsolidatedPlanDivisionWiseForm consolidatedPlanDivisionWiseForm = null;
		List<ConsolidatedPlanDivisionWiseDTO> dto  = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			consolidatedPlanDivisionWiseForm = (ConsolidatedPlanDivisionWiseForm) actionForm;

			
			if (MisUtility.ifEmpty(consolidatedPlanDivisionWiseForm)) {
				System.out.println("inside sa-ve----------------------------"+ consolidatedPlanDivisionWiseForm.toString());
				dto = consolidatedPlanDivisionWiseDao.getConsolidatePlanDivision(consolidatedPlanDivisionWiseForm.getFinancialYear());
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		request.setAttribute("consolidatePlanDivisionDtos", dto);
		request.setAttribute("financialYear", consolidatedPlanDivisionWiseForm.getFinancialYear());
		return mapping.findForward("display");
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getPendingDivisions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<ConsolidatedPlanDivisionWiseDTO> consolidatedPlanDivisionWiseDTOs = null;
		try {
			
			List<Integer> divisionId = null;
			String financialYear = request.getParameter("financialYear");

			if (MisUtility.ifEmpty(financialYear)) {
				
				divisionId = consolidatedPlanDivisionWiseDao.getExistingDivisionId(Integer.parseInt(financialYear));
				
				if (!MisUtility.ifEmpty(divisionId)){
				consolidatedPlanDivisionWiseDTOs = consolidatedPlanDivisionWiseDao.getPendingDivisions(divisionId);
				System.out.println("-----1-------"+consolidatedPlanDivisionWiseDTOs);
				}
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(consolidatedPlanDivisionWiseDTOs);
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
				
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
		public ActionForward getDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception, IOException {

			List<ConsolidatedPlanDivisionWiseDTO> consolidatedPlanDivisionWiseDTOs = null;
			try {
				
				String financialYear = request.getParameter("financialYear");
				String divisionId = request.getParameter("divisionId");

				if (MisUtility.ifEmpty(financialYear) && MisUtility.ifEmpty(divisionId)) {
					
					consolidatedPlanDivisionWiseDTOs = consolidatedPlanDivisionWiseDao.getDetails(Integer.parseInt(financialYear),Integer.parseInt(divisionId));
					System.out.println("-----1-------"+consolidatedPlanDivisionWiseDTOs);
					
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json2 = gson.toJson(consolidatedPlanDivisionWiseDTOs);
					System.out.println(json2);
					PrintWriter out = MisUtility.getPrintWriter(response);
					out.print(json2);
					
				}

			} catch (Exception e) {
				log.debug(e.getLocalizedMessage());
			}
			return null;
		}

}
