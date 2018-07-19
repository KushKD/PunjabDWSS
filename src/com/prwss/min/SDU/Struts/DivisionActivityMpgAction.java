package com.prwss.min.SDU.Struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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

import com.prwss.min.SDU.BO.DivisionActivityMpgBo;
import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.SchemeCycleAttributeDetailBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.dao.DivisionActivityMpgDao;
import com.prwss.min.SDU.form.DivisionActivityMpgDto;
import com.prwss.min.SDU.form.DivisionActivityMpgForm;
import com.prwss.min.SDU.form.DivisionActivityMpgGrid;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DivisionActivityMpgAction extends DispatchAction {

	private Logger log = Logger.getLogger(DivisionActivityMpgAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private DivisionActivityMpgDao divisionActivityMpgDao;
	private DivisionActivityMpgBo divisionActivityMpgBo;

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

	public DivisionActivityMpgDao getDivisionActivityMpgDao() {
		return divisionActivityMpgDao;
	}

	public void setDivisionActivityMpgDao(DivisionActivityMpgDao divisionActivityMpgDao) {
		this.divisionActivityMpgDao = divisionActivityMpgDao;
	}

	public DivisionActivityMpgBo getDivisionActivityMpgBo() {
		return divisionActivityMpgBo;
	}

	public void setDivisionActivityMpgBo(DivisionActivityMpgBo divisionActivityMpgBo) {
		this.divisionActivityMpgBo = divisionActivityMpgBo;
	}

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		boolean status = false;
		DivisionActivityMpgForm divisionActivityMpgForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionActivityMpgForm = (DivisionActivityMpgForm) actionForm;

			if (MisUtility.ifEmpty(divisionActivityMpgForm)) {
				System.out.println("inside sa-ve----------------------------" + divisionActivityMpgForm.toString());
				 status = divisionActivityMpgBo.save(divisionActivityMpgForm,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Record Saved.");
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
		refreshDivisionActivityMpgForm(divisionActivityMpgForm);
		return mapping.findForward("display");
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
		DivisionActivityMpgForm divisionActivityMpgForm = (DivisionActivityMpgForm) form;
		refreshDivisionActivityMpgForm(divisionActivityMpgForm);
		this.setAttrib(request);
		System.out.println("Unspecified........DivisionActivityMpgAction.........");

		return mapping.findForward("display");
	}

	private void refreshDivisionActivityMpgForm(DivisionActivityMpgForm divisionActivityMpgForm) {

		divisionActivityMpgForm.setActivity(null);
		divisionActivityMpgForm.setCampaign(null);
		divisionActivityMpgForm.setCategory(null);
		divisionActivityMpgForm.setComponent(null);
		divisionActivityMpgForm.setStage(null);
		divisionActivityMpgForm.setDivActivityMpgGrid(getDivisionActDatagrid(null));
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private Datagrid getDivisionActDatagrid(List<DivisionActivityMpgGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(DivisionActivityMpgGrid.class);
		List<DivisionActivityMpgGrid> villageBeans = new ArrayList<DivisionActivityMpgGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}

	public ActionForward getDivisionsBasedOnUserId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String UserID = null;
		List<String> locationId = null;
		List<LocationDivisionSubDivisonDetailsBean> LocationNameandId = null;
		List<Integer> IdLocations = null;
		UserID = request.getParameter("username_");

		if (MisUtility.ifEmpty(UserID)) {
			locationId = divisionActivityMpgDao.getAllLocationIds(UserID);
		}
		IdLocations = new ArrayList<Integer>();

		for (int i = 0; i < locationId.size(); i++) {
			IdLocations.add(Integer.parseInt(locationId.get(i)));
		}
		if (!MisUtility.ifEmpty(IdLocations)) {
			LocationNameandId = divisionActivityMpgDao.getLocationNameandId(IdLocations);
		}
		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Location ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(LocationNameandId)) {
				for (LocationDivisionSubDivisonDetailsBean bean : LocationNameandId) {
					buffer.append("<option value=\"").append(bean.getDivisonSubDivisonDetailsId()).append("\">");
					buffer.append(bean.getDivisonSubDivisonDetailsName());
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

	public ActionForward getStageNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getStageNameAndId");
		
		String financialYr = null;
		String division = null;
		String category = null;
		
		financialYr = request.getParameter("financialYear1");
		division = request.getParameter("division1");
		category = request.getParameter("category1");

		System.out.println("financial year id is----------" + financialYr);
		System.out.println("division id is----------" + division);
		System.out.println("category id is----------" + category);
		
		List<DivisionActivityMpgDto> divisionActivityMpgDtos = null;
		StringBuffer buffer = new StringBuffer();

		try {
			divisionActivityMpgDtos = divisionActivityMpgDao.getStageNameAndId(Integer.parseInt(financialYr), Integer.parseInt(division), Integer.parseInt(category));
			System.out.println(divisionActivityMpgDtos.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Stage");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(divisionActivityMpgDtos)) {
				for (DivisionActivityMpgDto divisionActivityMpgDto : divisionActivityMpgDtos) {
					buffer.append("<option value=\"").append(divisionActivityMpgDto.getStageId()).append("\">");
					buffer.append(divisionActivityMpgDto.getStageName());
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

	public ActionForward getCategoryNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getCategoryNameAndId");
		
		String financialYr = null;
		String division = null;
		
		financialYr = request.getParameter("financialYear_");
		division = request.getParameter("division_");
		
		System.out.println("financial year id is----------" + financialYr);
		System.out.println("division id is----------" + division);
		
		List<DivisionWiseSummaryBean> divisionWiseSummaryBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			divisionWiseSummaryBeans = divisionActivityMpgDao.getCategoryNameAndId(Integer.parseInt(financialYr),Integer.parseInt(division));
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

		financialYr = request.getParameter("financialYear1");
		division = request.getParameter("division1");
		category = request.getParameter("category1");
		stage = request.getParameter("stage1");

		System.out.println("financial year id is----------" + financialYr);
		System.out.println("division id is----------" + division);
		System.out.println("category id is----------" + category);
		System.out.println("stage id is----------" + stage);

		List<StageComponentBean> stageComponentBeans = null;

		StringBuffer buffer = new StringBuffer();

		try {
			stageComponentBeans = divisionActivityMpgDao.getComponentNameAndId(Integer.parseInt(financialYr),
					Integer.parseInt(division), Integer.parseInt(category), Integer.parseInt(stage));
			System.out.println(stageComponentBeans.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Component");
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

	public ActionForward getCampaignNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getCampaignNameAndId");

		Collection<SchemeCycleAttributeDetailBean> schemeCycleAttributeDetailBeans2 = null;

		StringBuffer buffer = new StringBuffer();

		try {

			// attributeTypeId = divisionActivityMpgDao.getAttributeId();

			if (MisUtility.ifEmpty(request.getParameter("attributeId"))) {

				schemeCycleAttributeDetailBeans2 = divisionActivityMpgDao
						.getSchAttributeId(Integer.parseInt(request.getParameter("attributeId")));
			}
			buffer.append("<option value='' selected>");
			buffer.append("Select Campaign");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(schemeCycleAttributeDetailBeans2)) {
				for (SchemeCycleAttributeDetailBean schemeCycleAttributeDetailBean : schemeCycleAttributeDetailBeans2) {
					buffer.append("<option value=\"").append(schemeCycleAttributeDetailBean.getSchAttributeDtlId())
							.append("\">");
					buffer.append(schemeCycleAttributeDetailBean.getAttributeName());
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

	public ActionForward getActivityNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getActivityNameAndId");

		String financialYr = null;
		String campaign = null;
		String category = null;
		String stage = null;
		String parentId = null;

		List<SchemeCycleAttributeDetailBean> activityNameandId = null;
		StringBuffer buffer = new StringBuffer();
		try {

			financialYr = request.getParameter("financialYear1");
			campaign = request.getParameter("campaign1");
			category = request.getParameter("category1");
			stage = request.getParameter("stage1");
			parentId = request.getParameter("parentId");

			System.out.println("financial year id is----------" + financialYr);
			System.out.println("campaign id is----------" + campaign);
			System.out.println("category id is----------" + category);
			System.out.println("stage id is----------" + stage);
			System.out.println("parent Id is----------" +parentId);

			if (MisUtility.ifEmpty(financialYr) && MisUtility.ifEmpty(campaign) && MisUtility.ifEmpty(category)
					&& MisUtility.ifEmpty(stage) && MisUtility.ifEmpty(parentId)) {
				activityNameandId = divisionActivityMpgDao.getActivityNameandId(Integer.parseInt(financialYr),
						Integer.parseInt(campaign), Integer.parseInt(category), Integer.parseInt(stage), Integer.parseInt(parentId));
			}
			buffer.append("<option value='' selected>");
			buffer.append("Select Activity");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(activityNameandId)) {
				for (SchemeCycleAttributeDetailBean bean : activityNameandId) {
					buffer.append("<option value=\"").append(bean.getSchAttributeDtlId()).append("\">");
					buffer.append(bean.getAttributeName());
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
