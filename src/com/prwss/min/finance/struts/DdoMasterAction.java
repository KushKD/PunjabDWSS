/**
 * 
 */
package com.prwss.min.finance.struts;

import java.io.IOException;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.DdoMasterBo;
import com.prwss.min.finance.dao.DdoMasterDao;
import com.prwss.min.finance.form.DdoMasterForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class DdoMasterAction extends DispatchAction{

	private Logger log = Logger.getLogger(DdoMasterAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private DdoMasterBo<FinanceDto> ddoMasterBo;
	private DdoMasterDao ddoMasterDao;
	
	
	
	
	
	public DdoMasterDao getDdoMasterDao() {
		return ddoMasterDao;
	}
	public void setDdoMasterDao(DdoMasterDao ddoMasterDao) {
		this.ddoMasterDao = ddoMasterDao;
	}
	public DdoMasterBo<FinanceDto> getDdoMasterBo() {
		return ddoMasterBo;
	}
	public void setDdoMasterBo(DdoMasterBo<FinanceDto> ddoMasterBo) {
		this.ddoMasterBo = ddoMasterBo;
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
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		DdoMasterForm ddoMasterForm = (DdoMasterForm) form;

		System.out.println("Inside component Save()->");

		log.debug("Inside ComponentAction Save()->" + ddoMasterForm.toString());

		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = ddoMasterBo.saveDdoMaster(ddoMasterForm, misSessionBean);
			if (MisUtility.ifEmpty(status)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Saved");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshDdoMasterForm(ddoMasterForm);

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
		this.setAttrib(request);
		DdoMasterForm ddoMasterForm = (DdoMasterForm) form;
		refreshDdoMasterForm(ddoMasterForm);
		log.debug("Unspecified........DivisionBudgetAction");
		System.out.println("Unspecified........DivisionBudgetAction");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}
	
	private void refreshDdoMasterForm(DdoMasterForm ddoMasterForm){
		
		ddoMasterForm.setZone(null);
		ddoMasterForm.setCircle(null);
		ddoMasterForm.setDivision(null);
		ddoMasterForm.setDdoName(null);
		ddoMasterForm.setDdoNumber(null);
		ddoMasterForm.setTeasury(null);
		ddoMasterForm.setStatus(null);
	}
	
	
	public ActionForward populateDdoMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<FinanceDto> ddoMaster = null;
		try {
			
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			ddoMaster = new ArrayList<FinanceDto>();
			
			ddoMaster = ddoMasterDao.getDdoMaster(searchParameter, clickedColumn, clickedColumnDir);
			
			
			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(ddoMaster)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(ddoMaster)) {
				locationJson.setiTotalDisplayRecords(ddoMaster.size());
				locationJson.setiTotalRecords(ddoMaster.size());
			}
			List<FinanceDto> gpwscRegister2 = null;
			if (!MisUtility.ifEmpty(ddoMaster)) {
				gpwscRegister2 = ddoMasterBo.getListBasedOnPageNumber(ddoMaster, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(gpwscRegister2);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
