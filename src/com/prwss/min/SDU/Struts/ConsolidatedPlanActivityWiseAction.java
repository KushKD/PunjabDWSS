package com.prwss.min.SDU.Struts;

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

import com.prwss.min.SDU.bean.ConsolidatedPlanActivityWiseDTO;
import com.prwss.min.SDU.dao.VillageActivityMappingDao;
import com.prwss.min.SDU.form.ConsolidatedPlanActivityWiseForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class ConsolidatedPlanActivityWiseAction extends DispatchAction{
	
	private Logger log = Logger.getLogger(ConsolidatedPlanActivityWiseAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private VillageActivityMappingDao villageActivityMappingdao ;
	
	
	
	public VillageActivityMappingDao getVillageActivityMappingdao() {
		return villageActivityMappingdao;
	}
	public void setVillageActivityMappingdao(VillageActivityMappingDao villageActivityMappingdao) {
		this.villageActivityMappingdao = villageActivityMappingdao;
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
		
		System.out.println("Unspecified.................");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		//DivisionActivityMpgForm divisionActivityMpgForm = (DivisionActivityMpgForm) form;
		
		this.setAttrib(request);
		System.out.println("Unspecified........DivisionActivityMpgAction.........");

		return mapping.findForward("display");
	}
	
	
	/*private void refreshDivisionActivityMpgForm(DivisionActivityMpgForm divisionActivityMpgForm) {

		divisionActivityMpgForm.setActivity(null);
		divisionActivityMpgForm.setCampaign(null);
		divisionActivityMpgForm.setCategory(null);
		divisionActivityMpgForm.setComponent(null);
		divisionActivityMpgForm.setStage(null);
		divisionActivityMpgForm.setDivActivityMpgGrid(getDivisionActDatagrid(null));
	}*/
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	
	public ActionForward getAbstract(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		
		ConsolidatedPlanActivityWiseForm consolidatedPlanActivityWiseForm = null;
		List<ConsolidatedPlanActivityWiseDTO> dto  = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			consolidatedPlanActivityWiseForm = (ConsolidatedPlanActivityWiseForm) actionForm;

			
			if (MisUtility.ifEmpty(consolidatedPlanActivityWiseForm)) {
				System.out.println("inside sa-ve----------------------------"+ consolidatedPlanActivityWiseForm.toString());
				//status = yearPlanInspectionBo.save(yearlyPlanInspectionForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
				dto = villageActivityMappingdao.getConsolidatePlanActivity(consolidatedPlanActivityWiseForm.getFinancialYear());
				
			}

			/*if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Added Plan:" + "\t" + divisionActivityMpgForm.getActivity());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}*/
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		request.setAttribute("consolidatePlanActivityDtos", dto);
		request.setAttribute("financialYear", consolidatedPlanActivityWiseForm.getFinancialYear());
		return mapping.findForward("display");
	}

}
