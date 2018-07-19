package com.prwss.min.SDU.Struts;

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

import com.prwss.min.SDU.BO.TrackingBo;
import com.prwss.min.SDU.BO.TrackingDto;
import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageDetailBean;
import com.prwss.min.SDU.dao.StageComponentMappingDao;
import com.prwss.min.SDU.dao.TrakingDao;
import com.prwss.min.SDU.form.TrackingForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class TrackingAction extends DispatchAction {

	private Logger log = Logger.getLogger(TrackingAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;

	private TrakingDao trackingdao;
	private TrackingBo trackingbo;

	private StageComponentMappingDao stageComponentMpgDao;

	public StageComponentMappingDao getStageComponentMpgDao() {
		return stageComponentMpgDao;
	}

	public void setStageComponentMpgDao(
			StageComponentMappingDao stageComponentMpgDao) {
		this.stageComponentMpgDao = stageComponentMpgDao;
	}

	public TrackingBo getTrackingbo() {
		return trackingbo;
	}

	public void setTrackingbo(TrackingBo trackingbo) {
		this.trackingbo = trackingbo;
	}

	public TrakingDao getTrackingdao() {
		return trackingdao;
	}

	public void setTrackingdao(TrakingDao trackingdao) {
		this.trackingdao = trackingdao;
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
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("Unspecified.................");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		// financialYear@division@village@stage@category@component
		// DivisionActivityMpgForm divisionActivityMpgForm =
		// (DivisionActivityMpgForm) form;

		this.setAttrib(request);
		System.out
				.println("Unspecified........DivisionActivityMpgAction.........");

		return mapping.findForward("display");
	}

	/*
	 * private void refreshDivisionActivityMpgForm(DivisionActivityMpgForm
	 * divisionActivityMpgForm) {
	 * 
	 * divisionActivityMpgForm.setActivity(null);
	 * divisionActivityMpgForm.setCampaign(null);
	 * divisionActivityMpgForm.setCategory(null);
	 * divisionActivityMpgForm.setComponent(null);
	 * divisionActivityMpgForm.setStage(null);
	 * divisionActivityMpgForm.setDivActivityMpgGrid(getDivisionActDatagrid(null
	 * )); }
	 */

	private void setAttrib(HttpServletRequest request) {
		// request.setAttribute("Rpt", "ent");
		// request.setAttribute("menuId", request.getParameter("menuId"));
		// request.setAttribute("d__mode", request.getParameter("d__mode"));
		// request.setAttribute("d__ky", "locationName");
		// request.setAttribute("de_kyenable",
		// "financialYear@division@village@category@stage@component");
	}

	public ActionForward find(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		TrackingForm trackingForm = null;
		List<TrackingDto> tracking_dto = null;
		List<Integer> outcomes = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			trackingForm = (TrackingForm) actionForm;

			if (MisUtility.ifEmpty(trackingForm)) {
				System.out.println("inside sa-ve----------------------------"
						+ trackingForm.toString());
				// status = yearPlanInspectionBo.save(yearlyPlanInspectionForm,
				// Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
				tracking_dto = trackingdao.gerTrackingDTO(trackingForm);

				if (!MisUtility.ifEmpty(tracking_dto)) {
					outcomes=new ArrayList<Integer>();
					for (int i = 0; i < tracking_dto.size(); i++) {
						if (tracking_dto.get(i).getActual_start_date() != null) {
							tracking_dto.get(i).setaStartDate(
									MisUtility.convertDateString(tracking_dto
											.get(i).getActual_start_date()));

						}
						if (tracking_dto.get(i).getActual_End_Date() != null) {
							tracking_dto.get(i).setaEndDate(
									MisUtility.convertDateString(tracking_dto
											.get(i).getActual_End_Date()));

						}
						// dbAcheived
						if (tracking_dto.get(i).getAchieved() != null) {
							System.out.println(tracking_dto.get(i)
									.getAchieved());
							if (tracking_dto.get(i).getAchieved() == 1) {
								tracking_dto.get(i).setDbAcheived("on");
							} else {
								tracking_dto.get(i).setDbAcheived("off");
							}
						}
						
						outcomes.add(tracking_dto.get(i).getActivityId());

					}
					//List<String> trackingdao.getOutcome(outcomes);
					trackingForm.setTrackingDtos(tracking_dto);
				}
				request.setAttribute("trackingDtos", trackingForm);
				request.setAttribute("financialYear",
						trackingForm.getFinancialYear());
				request.setAttribute("division", trackingForm.getDivision());
				request.setAttribute("category", trackingForm.getCategory());
				request.setAttribute("stage", trackingForm.getStage());
				request.setAttribute("component", trackingForm.getComponent());

			}

			/*
			 * if (status) { ActionErrors errors = new ActionErrors();
			 * ActionMessage message = new ActionMessage("success.save",
			 * "Successfully Updated");
			 * errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			 * saveErrors(request, errors);
			 * 
			 * } else { request.setAttribute("level2", "true"); ActionErrors
			 * errors = new ActionErrors(); ActionMessage message = new
			 * ActionMessage("error.save", "Internal error Please check logs..s"
			 * ); errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			 * saveErrors(request, errors); }
			 */
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry",
					"Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		request.setAttribute("consolidatePlanActivityDtos", tracking_dto);
		return mapping.findForward("display");
	}

	// save
	public ActionForward save(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		boolean status = false;
		TrackingForm trackingForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			trackingForm = (TrackingForm) actionForm;

			if (MisUtility.ifEmpty(trackingForm)) {
				System.out.println("inside sa-ve----------------------------"
						+ trackingForm.toString());

				status = trackingbo.save(trackingForm, Integer.parseInt(String
						.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Updated Data ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry",
					"Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		// refreshVillageActivityMpgForm(villageActivityMpgForm);
		return mapping.findForward("display");
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------

	public ActionForward getDivisionsBasedOnUserId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String UserID = null;
		List<String> locationId = null;
		List<LocationDivisionSubDivisonDetailsBean> LocationNameandId = null;
		List<Integer> IdLocations = null;
		UserID = request.getParameter("username_");

		if (MisUtility.ifEmpty(UserID)) {
			locationId = trackingdao.getAllLocationIds(UserID);
		}
		IdLocations = new ArrayList<Integer>();

		for (int i = 0; i < locationId.size(); i++) {
			IdLocations.add(Integer.parseInt(locationId.get(i)));
		}
		if (!MisUtility.ifEmpty(IdLocations)) {
			LocationNameandId = trackingdao.getLocationNameandId(IdLocations);
		}
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Location ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(LocationNameandId)) {
				for (LocationDivisionSubDivisonDetailsBean bean : LocationNameandId) {
					buffer.append("<option value=\"")
							.append(bean.getDivisonSubDivisonDetailsId())
							.append("\">");
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

	// ---------------------------------------------------------------------------------------------------------------------------------------------

	public ActionForward getStages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		// /String UserID = null;
		// List<String> locationId = null;
		List<StageDetailBean> stageDetails = null;
		// List<Integer> IdLocations = null;
		// UserID = request.getParameter("username_");

		stageDetails = new ArrayList<StageDetailBean>();
		stageDetails = trackingdao.getStageNameAndId();

		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Stages ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(stageDetails)) {
				for (StageDetailBean bean : stageDetails) {
					buffer.append("<option value=\"").append(bean.getStageId())
							.append("\">");
					buffer.append(bean.getStageName());
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

	public ActionForward getComponents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {

		String FinancialYear = null;
		String Division = null;
		// List<String> locationId = null;
		List<DivisionWiseSummaryBean> stageDetails = null;
		// List<Integer> IdLocations = null;
		FinancialYear = request.getParameter("year");
		Division = request.getParameter("div");

		stageDetails = new ArrayList<DivisionWiseSummaryBean>();
		stageDetails = stageComponentMpgDao.getComponets(FinancialYear,
				Division);

		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Stages ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(stageDetails)) {
				for (DivisionWiseSummaryBean bean : stageDetails) {
					buffer.append("<option value=\"")
							.append(bean.getCategory()).append("\">");
					buffer.append(bean.getCombodetailCatName().getCmb_name());
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
