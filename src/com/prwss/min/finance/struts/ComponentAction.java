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
import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.ComponentBo;
import com.prwss.min.finance.dao.ComponentDao;
import com.prwss.min.finance.form.ComponentForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ComponentAction extends DispatchAction {

	private Logger log = Logger.getLogger(ComponentAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ComponentBo<FinanceDto> componentBo;
	private ComponentDao componentDao;

	public ComponentDao getComponentDao() {
		return componentDao;
	}

	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	public ComponentBo<FinanceDto> getComponentBo() {
		return componentBo;
	}

	public void setComponentBo(ComponentBo<FinanceDto> componentBo) {
		this.componentBo = componentBo;
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
		ComponentForm componentForm = (ComponentForm) form;

		System.out.println("Inside component Save()->");
		log.debug("Inside ComponentAction Save()->" + componentForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = componentBo.save(componentForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved");
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
		refreshComponentForm(componentForm);

		return mapping.findForward("display");

	}

	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		ComponentForm componentForm = (ComponentForm) form;

		System.out.println("Inside component Update()->");
		log.debug("Inside ComponentAction Update()->" + componentForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = componentBo.save(componentForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Update");
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
		refreshComponentForm(componentForm);

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
		ComponentForm componentForm = (ComponentForm) form;
		refreshComponentForm(componentForm);
		log.debug("Unspecified........FinancialComponentAction");
		System.out.println("Unspecified........FinancialComponentAction");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	private void refreshComponentForm(ComponentForm componentForm) {
		componentForm.setComponentName(null);
		componentForm.setComponentType(null);
		componentForm.setParentComponent(null);
		componentForm.setDescription(null);
	}

	public ActionForward fetchParent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		System.out.println("inside parent----------->");
		List<ComponentDetailsBean> componentDetailsBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("componentType"))) {

				if (request.getParameter("componentType").equalsIgnoreCase(MISConstants.ONE_NIGHTY_FOUR)) {
					componentDetailsBeans = componentDao.getParent(MISConstants.ONE_NIGHTY_TWO,null);
				} else if (request.getParameter("componentType").equalsIgnoreCase(MISConstants.ONE_NIGHTY_FIVE)) {
					componentDetailsBeans = componentDao.getParent(MISConstants.ONE_NIGHTY_FOUR,null);
				} else if (request.getParameter("componentType").equalsIgnoreCase(MISConstants.TWO_HUNDRED_EIGHT)) {
					componentDetailsBeans = componentDao.getParent(MISConstants.ONE_NIGHTY_FIVE,null);
				}else if (request.getParameter("componentType").equalsIgnoreCase(MISConstants.TWO_HUNDRED_NINE)) {
					componentDetailsBeans = componentDao.getParent(MISConstants.ONE_NIGHTY_FIVE,MISConstants.TWO_HUNDRED_EIGHT);
				}
				buffer.append("<option value='-1' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");
				if (!MisUtility.ifEmpty(componentDetailsBeans)) {
					for (ComponentDetailsBean componentDetailsBean : componentDetailsBeans) {
						buffer.append("<option value=\"").append(componentDetailsBean.getFinCompId()).append("\">");
						buffer.append(componentDetailsBean.getComponentName());
						buffer.append("</option>");
					}
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

	public ActionForward populateComponentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		try {
			List<FinanceDto> financeComponentsBean = null;
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			financeComponentsBean = new ArrayList<FinanceDto>();
			
			financeComponentsBean = componentDao.populateComponent(searchParameter, clickedColumn, clickedColumnDir);
			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(financeComponentsBean)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(financeComponentsBean)) {
				locationJson.setiTotalDisplayRecords(financeComponentsBean.size());
				locationJson.setiTotalRecords(financeComponentsBean.size());
			}
			List<FinanceDto> financeHeadBeans2 = null;
			if (!MisUtility.ifEmpty(financeComponentsBean)) {
				financeHeadBeans2 = componentBo.getListBasedOnPageNumber(financeComponentsBean, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(financeHeadBeans2);
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
