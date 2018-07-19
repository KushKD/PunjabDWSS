/**
 * 
 */
package com.prwss.min.construction.quality.struts;

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

import com.prwss.min.construction.quality.bean.ExternalAgencyMasterBean;
import com.prwss.min.construction.quality.bo.YearPlanInspectionBo;
import com.prwss.min.construction.quality.dao.YearPlanInspectionDao;
import com.prwss.min.construction.quality.form.YearlyInspectionGrid;
import com.prwss.min.construction.quality.form.YearlyPlanInspectionForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class YearlyPlanInspectionAction extends DispatchAction {

	private Logger log = Logger.getLogger(YearlyPlanInspectionAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private YearPlanInspectionBo yearPlanInspectionBo;
	private YearPlanInspectionDao yearPlanInspectionDao;

	public YearPlanInspectionDao getYearPlanInspectionDao() {
		return yearPlanInspectionDao;
	}

	public void setYearPlanInspectionDao(YearPlanInspectionDao yearPlanInspectionDao) {
		this.yearPlanInspectionDao = yearPlanInspectionDao;
	}

	public YearPlanInspectionBo getYearPlanInspectionBo() {
		return yearPlanInspectionBo;
	}

	public void setYearPlanInspectionBo(YearPlanInspectionBo yearPlanInspectionBo) {
		this.yearPlanInspectionBo = yearPlanInspectionBo;
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

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		boolean status = false;
		YearlyPlanInspectionForm yearlyPlanInspectionForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			yearlyPlanInspectionForm = (YearlyPlanInspectionForm) actionForm;

			if (MisUtility.ifEmpty(yearlyPlanInspectionForm)) {
				status = yearPlanInspectionBo.save(yearlyPlanInspectionForm,
						Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Added Plan:" + "\t" + yearlyPlanInspectionForm.getPlanName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error please check logs..");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshYearlyInspectionForm(yearlyPlanInspectionForm);
		return mapping.findForward("display");
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
		YearlyPlanInspectionForm yearlyPlanInspectionForm = (YearlyPlanInspectionForm) form;
		refreshYearlyInspectionForm(yearlyPlanInspectionForm);
		this.setAttrib(request);
		System.out.println("Unspecified........YearlyPlanInspectionAction.........");

		return mapping.findForward("display");
	}

	private void refreshYearlyInspectionForm(YearlyPlanInspectionForm yearlyPlanInspectionForm) {

		yearlyPlanInspectionForm.setAssignedTo(null);
		yearlyPlanInspectionForm.setComments(null);
		yearlyPlanInspectionForm.setComponent(null);
		yearlyPlanInspectionForm.setComponentName(null);
		yearlyPlanInspectionForm.setFinalizationDate(null);
		yearlyPlanInspectionForm.setFinancialYear(null);
		yearlyPlanInspectionForm.setFinancialYearName(null);
		yearlyPlanInspectionForm.setInspectionType(null);
		yearlyPlanInspectionForm.setNumberOfVillage(null);
		yearlyPlanInspectionForm.setPerMonthVisit(null);
		yearlyPlanInspectionForm.setTotalDuration(null);
		yearlyPlanInspectionForm.setTotalVisit(null);
		yearlyPlanInspectionForm.setVisitPerVillage(null);
		yearlyPlanInspectionForm.setVisitVillage(null);
		yearlyPlanInspectionForm.setPlanName(null);
		yearlyPlanInspectionForm.setYearlyPlanGrid(getYearlyPlanDatagrid(null));

	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private Datagrid getYearlyPlanDatagrid(List<YearlyInspectionGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(YearlyInspectionGrid.class);
		List<YearlyInspectionGrid> villageBeans = new ArrayList<YearlyInspectionGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}

	public ActionForward fetchExternalAgency(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchExternalAgency");
		List<ExternalAgencyMasterBean> externalAgencyMasterBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			externalAgencyMasterBeans = yearPlanInspectionDao.fetchExternalAgency();
			System.out.println(externalAgencyMasterBeans.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select External Agency");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(externalAgencyMasterBeans)) {
				for (ExternalAgencyMasterBean externalAgencyMasterBean : externalAgencyMasterBeans) {
					buffer.append("<option value=\"").append(externalAgencyMasterBean.getExternal_agency_id())
							.append("\">");
					buffer.append(externalAgencyMasterBean.getAgencyName());
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
