package com.prwss.min.SDU.Struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.min.SDU.bean.SchemeCycleAttributeDetailBean;
import com.prwss.min.SDU.BO.VillageActivityMappingBO;
import com.prwss.min.SDU.dao.VillageActivityMappingDao;
import com.prwss.min.SDU.form.VillageActivityMpgForm;
import com.prwss.min.SDU.form.VillageActivityMpgGrid;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;
import jxl.common.Logger;

public class VillageActivityMpgAction extends DispatchAction {

	private Logger log = Logger.getLogger(VillageActivityMpgAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;

	private VillageActivityMappingDao villageActivityMappingdao;
	private VillageActivityMappingBO villageActivityMappingBO;
	
	public VillageActivityMappingBO getVillageActivityMappingBO() {
		return villageActivityMappingBO;
	}

	public void setVillageActivityMappingBO(VillageActivityMappingBO villageActivityMappingBO) {
		this.villageActivityMappingBO = villageActivityMappingBO;
	}

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
		VillageActivityMpgForm villageActivityMpgForm = (VillageActivityMpgForm) form;
		refreshVillageActivityMpgForm(villageActivityMpgForm);
		this.setAttrib(request);
		System.out.println("Unspecified........DivisionActivityMpgAction.........");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		boolean status = false;
		VillageActivityMpgForm villageActivityMpgForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			villageActivityMpgForm = (VillageActivityMpgForm) actionForm;

			if (MisUtility.ifEmpty(villageActivityMpgForm)) {
				System.out.println("inside sa-ve----------------------------" + villageActivityMpgForm.toString());
				 status = villageActivityMappingBO.save(villageActivityMpgForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Mapped Villages and Activities ");
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
		refreshVillageActivityMpgForm(villageActivityMpgForm);
		return mapping.findForward("display");
	}

	private void refreshVillageActivityMpgForm(VillageActivityMpgForm villageActivityMpgForm) {

		villageActivityMpgForm.setActivity(null);
		villageActivityMpgForm.setCategory(null);
		villageActivityMpgForm.setComponent(null);
		villageActivityMpgForm.setExpectedEndDate(null);
		villageActivityMpgForm.setExpectedStartDate(null);
		villageActivityMpgForm.setStage(null);
		villageActivityMpgForm.setVillage(null);
		villageActivityMpgForm.setVillageActivityMpgGrid(getVillActMpgDatagrid(null));
	}

	private Datagrid getVillActMpgDatagrid(List<VillageActivityMpgGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(VillageActivityMpgGrid.class);
		List<VillageActivityMpgGrid> villageBeans = new ArrayList<VillageActivityMpgGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}

	// Get Villages List
	public ActionForward getVillagesList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getComponentNameAndId");
		
		String category = null;
		String stage = null;
		String component = null;
		List<Integer> villageIds ;
		

		component = request.getParameter("component2");
		category = request.getParameter("category2");
		stage = request.getParameter("stage2");

		System.out.println("category id is----------" + category);
		System.out.println("stage id is----------" + stage);
		System.out.println("stage id is----------" + component);

		int stageComponentBeans;

		StringBuffer buffer = new StringBuffer();

		try {
			stageComponentBeans = villageActivityMappingdao.getDivVillageIdValue(component,category, stage);
			System.out.println(stageComponentBeans);
			
			//Get All the Village ID's on the Basis of Division Village Mapping Id
			villageIds = new ArrayList<Integer>();
			villageIds  = villageActivityMappingdao.getVillageIds(stageComponentBeans);
			
			//Getting the Vikllages Names and Ids on the Basis of Id's
			List<LocationDetailsBean> villageDetails = villageActivityMappingdao.getVillagesData(villageIds);
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Villages ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(villageDetails)) {
				for (LocationDetailsBean bean:villageDetails) {
					buffer.append("<option value=\"").append(bean.getLocationDetailsId()).append("\">");
					buffer.append(bean.getLocationName());
			buffer.append("</option>");
				}
				
				
				
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
			
		}catch(Exception e){ log.error(e); }

	return null;
}
	
	//getActivity
	public ActionForward getActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getComponentNameAndId");
		
		String category = null;
		String stage = null;
		String component = null;
		String DivisionId = null;
		List<Integer> ActivityIds ;
		

		component = request.getParameter("component2");
		category = request.getParameter("category2");
		stage = request.getParameter("stage2");
		DivisionId = request.getParameter("divId2");

		System.out.println("category id is----------" + category);
		System.out.println("stage id is----------" + stage);
		System.out.println("Component id is----------" + component);
		System.out.println("Division id is----------" + DivisionId);

		int ActivityDivisionId;

		StringBuffer buffer = new StringBuffer();

		try {
			ActivityDivisionId = villageActivityMappingdao.getDivisionActivityId(category, stage ,component, DivisionId);
			System.out.println(ActivityDivisionId);
			
			//Get All the Activity ID's on the Basis of Division Village Mapping Id
			ActivityIds = new ArrayList<Integer>();
			ActivityIds  = villageActivityMappingdao.getIdActivities(ActivityDivisionId);
			
			//Getting the Activity Names and Ids on the Basis of Id's  
			List<SchemeCycleAttributeDetailBean> ActivityDetails = villageActivityMappingdao.getGetActivityDetails(ActivityIds);
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Activities ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(ActivityDetails)) {
				for (SchemeCycleAttributeDetailBean bean:ActivityDetails) {
					buffer.append("<option value=\"").append(bean.getSchAttributeId()).append("\">");
					buffer.append(bean.getAttributeName());
			buffer.append("</option>");
				}
				
				
				
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
			
		}catch(Exception e){ log.error(e); }

	return null;
}

}
