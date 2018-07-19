/**
 * 
 */
package com.prwss.min.quality.struts;

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
import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.dao.ParameterMasterDao;
import com.prwss.min.quality.ParameterMasterBo;
import com.prwss.min.quality.ParameterMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;
/**
 * @author bhsingh
 *
 */
public class ParameterMasterAction extends DispatchAction {

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ParameterMasterDao parameterMasterDao;

	private ParameterMasterBo<ParameterMasterForm> parameterMasterBo;

	public ParameterMasterDao getParameterMasterDao() {
		return parameterMasterDao;
	}

	public void setParameterMasterDao(ParameterMasterDao parameterMasterDao) {
		this.parameterMasterDao = parameterMasterDao;
	}

	public ParameterMasterBo<ParameterMasterForm> getParameterMasterBo() {
		return parameterMasterBo;
	}

	public void setParameterMasterBo(ParameterMasterBo<ParameterMasterForm> parameterMasterBo) {
		this.parameterMasterBo = parameterMasterBo;
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
		ParameterMasterForm parameterMasterForm = (ParameterMasterForm) form;
		ParameterMasterForm locationMasterForm = (ParameterMasterForm) form;
		System.out.println("inside save ParameterMasterAction->" + locationMasterForm.toString());
		log.debug(locationMasterForm.toString());
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			List<ParameterMasterBean> parameterMasterdetails=parameterMasterDao.findParameterData(parameterMasterForm);
			if(!MisUtility.ifEmpty(parameterMasterdetails)){
				throw new MISException(MISExceptionCodes.MIS002,"Entry Already Exist for" + "\t" + locationMasterForm.getParameterName());				
			}
			locationMasterForm.setCreatedByUser(misSessionBean.getEnteredBy());
			status = parameterMasterBo.saveParameter(locationMasterForm);

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Entry Successfully Saved For" + "\t" + locationMasterForm.getParameterName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshMasterParameterForm(locationMasterForm);
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Entry Failed to Save For" + "\t" + locationMasterForm.getParameterName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			System.out.println("e.getCode()------------->"+e.getCode());
			if (MISExceptionCodes.MIS002.equals(e.getCode())) {	
			log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {
		this.setAttrib(request);
		boolean status = false;
		ParameterMasterForm locationMasterForm = (ParameterMasterForm) form;
		System.out.println("inside update ParameterMasterAction->" + locationMasterForm.toString());
		log.debug(locationMasterForm.toString());
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = parameterMasterBo.updateParameter(locationMasterForm);

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Update Parameter" + "\t" + locationMasterForm.getParameterName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshMasterParameterForm(locationMasterForm);
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Not update Parameter" + locationMasterForm.getParameterName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			request.setAttribute("level2", "true");
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

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

		System.out.println("Unspecified........locationMaster");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "parameterId");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private void refreshMasterParameterForm(ParameterMasterForm parameterMasterForm) {

		parameterMasterForm.setParameterName(null);
		parameterMasterForm.setUom(null);
		parameterMasterForm.setPermissibleLimit(null);
		parameterMasterForm.setAcceptableLimit(null);
		parameterMasterForm.setwHOPermissibleLimit(null);
		parameterMasterForm.setwHOAcceptableLimit(null);
		parameterMasterForm.setStatus(null);
		parameterMasterForm.setEndDate(null);
		parameterMasterForm.setStartDate(null);
		parameterMasterForm.setNoRelaxation(null);

	}

	public ActionForward populate11(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		System.out.println("Here 111::::");
		List<ParameterMasterForm> parameterMasterList=null;
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
			// Fetch Page display length

			System.out.println("clickedColumn-------------->" + clickedColumn
					+ "-----------clickedColumnDir-------------" + clickedColumnDir + "-iDisplayStart----------"
					+ iDisplayStart + "pageNumber------------" + pageNumber);
			 parameterMasterList = parameterMasterBo
					.getParameterMasterByPagination(searchParameter, clickedColumn, clickedColumnDir);

			LocationJsonObject<ParameterMasterForm> locationJson = new LocationJsonObject<ParameterMasterForm>();

			List<ParameterMasterForm> locMasterLst = null;

			if (MisUtility.ifEmpty(parameterMasterList)) {
				locationJson.setAaData(new ArrayList<ParameterMasterForm>());
			}
			if (!MisUtility.ifEmpty(parameterMasterList)) {
				locationJson.setiTotalDisplayRecords(parameterMasterList.size());
				locationJson.setiTotalRecords(parameterMasterList.size());
			}
			if (!MisUtility.ifEmpty(parameterMasterList)) {
				locMasterLst = parameterMasterBo.getListBasedOnPageNumber(parameterMasterList, pageDisplayLength,
						pageNumber, iDisplayStart);
				locationJson.setAaData(locMasterLst);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

		return null;
	}

}
