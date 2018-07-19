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

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.BO.VillageDivisionMpgBO;
import com.prwss.min.SDU.dao.VillageDivisionMappingDao;
import com.prwss.min.SDU.form.VillageDivisionMpgForm;
import com.prwss.min.SDU.form.VillageDivisionMpgGrid;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;
import jxl.common.Logger;

public class VillageDivisionMpgAction extends DispatchAction {

	private Logger log = Logger.getLogger(VillageDivisionMpgAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private VillageDivisionMpgBO villageDivisionMpgBo;
	
	 

	public VillageDivisionMpgBO getVillageDivisionMpgBo() {
		return villageDivisionMpgBo;
	}

	public void setVillageDivisionMpgBo(VillageDivisionMpgBO villageDivisionMpgBo) {
		this.villageDivisionMpgBo = villageDivisionMpgBo;
	}

	private VillageDivisionMappingDao villageDivisionMpgDao;

	public VillageDivisionMappingDao getVillageDivisionMpgDao() {
		return villageDivisionMpgDao;
	}

	public void setVillageDivisionMpgDao(VillageDivisionMappingDao villageDivisionMpgDao) {
		this.villageDivisionMpgDao = villageDivisionMpgDao;
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
		VillageDivisionMpgForm villageDivisionMpgForm = (VillageDivisionMpgForm) form;
		refreshVillageDivisionMpgForm(villageDivisionMpgForm);
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
		VillageDivisionMpgForm villageDivisionMpgForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			villageDivisionMpgForm = (VillageDivisionMpgForm) actionForm;

			if (MisUtility.ifEmpty(villageDivisionMpgForm)) {
				System.out.println("inside sa-ve----------------------------" + villageDivisionMpgForm.toString());
				 status = villageDivisionMpgBo.saveData(villageDivisionMpgForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully mapped Villages and Divisions" );
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
		refreshVillageDivisionMpgForm(villageDivisionMpgForm);
		return mapping.findForward("display");
	}

	private void refreshVillageDivisionMpgForm(VillageDivisionMpgForm villageDivisionMpgForm) {

		villageDivisionMpgForm.setCategory(null);
		villageDivisionMpgForm.setComponent(null);
		villageDivisionMpgForm.setStage(null);
		villageDivisionMpgForm.setVillage(null);
		villageDivisionMpgForm.setVillages(null);
		villageDivisionMpgForm.setVillageDivisionMpgGrid(getVillDivMpgDatagrid(null));
	}

	private Datagrid getVillDivMpgDatagrid(List<VillageDivisionMpgGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(VillageDivisionMpgGrid.class);
		List<VillageDivisionMpgGrid> villageBeans = new ArrayList<VillageDivisionMpgGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}

	public ActionForward getCategoryNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getCategoryNameAndId");
		String financialYr = null;
		financialYr = request.getParameter("financialYear_");
		System.out.println("financial year id is----------" + financialYr);
		List<DivisionWiseSummaryBean> divisionWiseSummaryBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			divisionWiseSummaryBeans = villageDivisionMpgDao.getCategoryNameAndId(Integer.parseInt(financialYr));
			System.out.println(divisionWiseSummaryBeans.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Category");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(divisionWiseSummaryBeans)) {
				for (DivisionWiseSummaryBean divisionWiseSummaryBean : divisionWiseSummaryBeans) {
					buffer.append("<option value=\"").append(divisionWiseSummaryBean.getCategory()).append("\">");
					buffer.append(divisionWiseSummaryBean.getCombodetailCatName().getCmb_name());
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

	public ActionForward getComponentNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getComponentNameAndId");
		String financialYr = null;
		String division = null;
		String category = null;
		String stage = null;

		financialYr = request.getParameter("financialYear2");
		division = request.getParameter("division2");
		category = request.getParameter("category2");
		stage = request.getParameter("stage2");

		System.out.println("financial year id is----------" + financialYr);
		System.out.println("division id is----------" + division);
		System.out.println("category id is----------" + category);
		System.out.println("stage id is----------" + stage);

		List<StageComponentBean> stageComponentBeans = null;

		StringBuffer buffer = new StringBuffer();

		try {
			stageComponentBeans = villageDivisionMpgDao.getComponentNameAndId(Integer.parseInt(financialYr),
					Integer.parseInt(division), Integer.parseInt(category), Integer.parseInt(stage));
			System.out.println(stageComponentBeans.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Category");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(stageComponentBeans)) {
				for (StageComponentBean stageComponentBean : stageComponentBeans) {
					buffer.append("<option value=\"").append(stageComponentBean.getComponentId()).append("\">");
					buffer.append(stageComponentBean.getCombodetailCompName().getCmb_name());
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

	// getVillaesByStageAndComponent
	public ActionForward getVillaesByStageAndComponent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getVillaesByStageAndComponent");
		String component = null;
		component = request.getParameter("component_");
		System.out.println("componentid is----------" + component);

		String stage = null;
		stage = request.getParameter("stage_");
		System.out.println("Stage is----------" + stage);
		
		String fy = null;
		fy = request.getParameter("financialYear_");
		System.out.println("Stage is----------" + fy);
		
		String div = null;
		div = request.getParameter("division_");
		System.out.println("Stage is----------" + div);
		
		String cat = null;
		cat = request.getParameter("category_");
		System.out.println("Stage is----------" + cat);

		int villageNumbers;
		StringBuffer buffer = new StringBuffer();

		try {
			villageNumbers = villageDivisionMpgDao.getVillagesBasedOnStageAndCategory(stage, component , fy , div ,cat );
			

			buffer.append(Integer.toString(villageNumbers));

			PrintWriter out = response.getWriter();
			//if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				System.out.println("Number f Villages is:- "+Integer.toString(villageNumbers));
				out.print(buffer.toString());
			//}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	/// getVillages //divisionId
	public ActionForward getVillages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getVillaesByStageAndComponent");
		String DivIsonId = null;
		DivIsonId = request.getParameter("divId");
		System.out.println("Division Id----------" + DivIsonId);
		
		List<Integer>villageNumbers = null;
		List<LocationDetailsBean> villageDetails = null;

	
		StringBuffer buffer = new StringBuffer();

		try {
			
			//Getting the List of Village Ids
			villageNumbers = new ArrayList<Integer>();
			villageNumbers = villageDivisionMpgDao.getVillageIds(DivIsonId);
			System.out.println(villageNumbers.size());
			
			//Getting the name of the villages from the Location Detail Id List<Integer>villageNumbers = null;
			villageDetails = new ArrayList<LocationDetailsBean>();
			villageDetails = villageDivisionMpgDao.villageDetails(villageNumbers);
			System.out.println(villageDetails.toString());
			

			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(villageDetails)) {
				for (LocationDetailsBean stageComponentBean : villageDetails) {
					buffer.append("<option value=\"").append(stageComponentBean.getLocationDetailsId()).append("\">");
					buffer.append(stageComponentBean.getLocationName());
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

}
