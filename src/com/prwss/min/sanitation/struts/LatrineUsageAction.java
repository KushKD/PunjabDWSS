/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.io.IOException;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bo.LatrineUsageBo;
import com.prwss.min.sanitation.form.LatrineUsageForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class LatrineUsageAction extends DispatchAction{

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private LatrineUsageBo latrineUsageBo;
	
	
	
	public LatrineUsageBo getLatrineUsageBo() {
		return latrineUsageBo;
	}
	public void setLatrineUsageBo(LatrineUsageBo latrineUsageBo) {
		this.latrineUsageBo = latrineUsageBo;
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

		System.out.println("------------inside LatrineUsageAction--------");
		boolean status = false;
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			LatrineUsageForm latrineUsageForm = (LatrineUsageForm) form;
			latrineUsageForm.setCreatedBy(misAuditBean.getEnteredBy());
			if (MisUtility.ifEmpty(latrineUsageForm)) {
				status = latrineUsageBo.saveLatrine(latrineUsageForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Succefully saved");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} 

		} catch (MISException e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		} catch (Exception e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		}
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

		System.out.println("Unspecified........LatrineUsageAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	
	public ActionForward getLatrineUsageDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
System.out.println("insidr getLatrineUsageDetails ");
	
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			String searchParameter = request.getParameter("sSearch");

			List<LatrineUsageForm> beneficiaryDto = latrineUsageBo.getLatrineDetails(searchParameter, clickedColumn, clickedColumnDir);
			LocationJsonObject<LatrineUsageForm> locationJson = new LocationJsonObject<LatrineUsageForm>();

			if (MisUtility.ifEmpty(beneficiaryDto)) {
				locationJson.setAaData(new ArrayList<LatrineUsageForm>());
			}
			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				locationJson.setiTotalDisplayRecords(beneficiaryDto.size());
				locationJson.setiTotalRecords(beneficiaryDto.size());
				// locationJson.setAaData(beneficiaryDto);
			}

			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				beneficiaryDto = latrineUsageBo.getListBasedOnPageNumber(beneficiaryDto, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(beneficiaryDto);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());

		}
		return null;
	}
}
