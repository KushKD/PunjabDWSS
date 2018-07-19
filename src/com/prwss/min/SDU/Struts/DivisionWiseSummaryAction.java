
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

import com.prwss.min.SDU.BO.DivisionWiseSummaryBo;
import com.prwss.min.SDU.dao.DivisionWiseSummaryDao;
import com.prwss.min.SDU.form.DivisionWiseSummaryForm;
import com.prwss.min.SDU.form.DivisionWiseSummaryGrid;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;


public class DivisionWiseSummaryAction extends DispatchAction {

	private Logger log = Logger.getLogger(DivisionWiseSummaryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private DivisionWiseSummaryBo divisionWiseSummaryBo;
	private DivisionWiseSummaryDao divisionWiseSummaryDao;
	
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
	public DivisionWiseSummaryBo getDivisionWiseSummaryBo() {
		return divisionWiseSummaryBo;
	}

	public void setDivisionWiseSummaryBo(DivisionWiseSummaryBo divisionWiseSummaryBo) {
		this.divisionWiseSummaryBo = divisionWiseSummaryBo;
	}

	public DivisionWiseSummaryDao getDivisionWiseSummaryDao() {
		return divisionWiseSummaryDao;
	}

	public void setDivisionWiseSummaryDao(DivisionWiseSummaryDao divisionWiseSummaryDao) {
		this.divisionWiseSummaryDao = divisionWiseSummaryDao;
	}

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		boolean status = false;
		DivisionWiseSummaryForm divisionWiseSummaryForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			divisionWiseSummaryForm = (DivisionWiseSummaryForm) actionForm;

			if (MisUtility.ifEmpty(divisionWiseSummaryForm)) {
				System.out.println("inside sa-ve----------------------------"+divisionWiseSummaryForm.toString());
				status = divisionWiseSummaryBo.save(divisionWiseSummaryForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Record Saved Successfully." );  
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
		refreshDivisionWiseSummaryForm(divisionWiseSummaryForm);
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("Unspecified........divisionnnnnnnnnnnnnnnn.........");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		DivisionWiseSummaryForm divisionWiseSummaryForm = (DivisionWiseSummaryForm) form;
		refreshDivisionWiseSummaryForm(divisionWiseSummaryForm);
		this.setAttrib(request);
		System.out.println("Unspecified........YearlyPlanInspectionAction.........");

		return mapping.findForward("display");
	}

	private void refreshDivisionWiseSummaryForm(DivisionWiseSummaryForm divisionWiseSummaryForm) {

		divisionWiseSummaryForm.setCategory(null);
		divisionWiseSummaryForm.setCategoryName(null);
		divisionWiseSummaryForm.setDivision(null);
		divisionWiseSummaryForm.setDivWiseSumPlanGrid(getDivisionDatagrid(null));
		divisionWiseSummaryForm.setFinancialYear(null);
		divisionWiseSummaryForm.setNoOfVillages(null);
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private Datagrid getDivisionDatagrid(List<DivisionWiseSummaryGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(DivisionWiseSummaryGrid.class);
		List<DivisionWiseSummaryGrid> villageBeans = new ArrayList<DivisionWiseSummaryGrid>();
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
		
		if(MisUtility.ifEmpty(UserID)){
			 locationId = divisionWiseSummaryDao.getAllLocationIds(UserID);
		}
		IdLocations = new ArrayList<Integer>();

		for (int i = 0; i < locationId.size(); i++) {
			IdLocations.add(Integer.parseInt(locationId.get(i)));
		}
				if(!MisUtility.ifEmpty(IdLocations)){
						LocationNameandId = divisionWiseSummaryDao.getLocationNameandId(IdLocations);
		}
				StringBuffer buffer = new StringBuffer();
		try {
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Location ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(LocationNameandId)) {
				for (LocationDivisionSubDivisonDetailsBean bean:LocationNameandId) {
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
	
	//checkCategory
	public ActionForward checkCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		
		Boolean statusCategory = false;
		
		String financialYear = null;
		String division = null;
		String category = null;
		
		
		financialYear = request.getParameter("financialYear");
		division = request.getParameter("Division");
		category = request.getParameter("CategoryId");
		
				StringBuffer buffer = new StringBuffer();
		try {
			
			//TO DO Return True and False
			if (MisUtility.ifEmpty(financialYear) && 
					MisUtility.ifEmpty(division) &&
					MisUtility.ifEmpty(category) ){
				
				statusCategory = divisionWiseSummaryDao.getStatusCategory(financialYear,division,category);
				
				buffer.append(statusCategory)	;
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
