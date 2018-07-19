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
import com.prwss.min.finance.bean.FinanceHeadBeans;
import com.prwss.min.finance.bo.FinancialHeadStructureBo;
import com.prwss.min.finance.dao.FinancialHeadStructureDao;
import com.prwss.min.finance.form.FinanceHeadsStructureForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class FinancialHeadStructureAction extends DispatchAction {

	private Logger log = Logger.getLogger(FinancialHeadStructureAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private FinancialHeadStructureDao financialHeadStructureDao;
	private FinancialHeadStructureBo<FinanceDto> financialHeadStructureBo;

	public FinancialHeadStructureBo<FinanceDto> getFinancialHeadStructureBo() {
		return financialHeadStructureBo;
	}

	public void setFinancialHeadStructureBo(FinancialHeadStructureBo<FinanceDto> financialHeadStructureBo) {
		this.financialHeadStructureBo = financialHeadStructureBo;
	}

	public FinancialHeadStructureDao getFinancialHeadStructureDao() {
		return financialHeadStructureDao;
	}

	public void setFinancialHeadStructureDao(FinancialHeadStructureDao financialHeadStructureDao) {
		this.financialHeadStructureDao = financialHeadStructureDao;
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
		FinanceHeadsStructureForm financeHeadsStructureForm = (FinanceHeadsStructureForm) form;

		System.out.println("Inside financeHeads Save()->");
		log.debug("Inside CollectionCenterAction Save()->" + financeHeadsStructureForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = financialHeadStructureBo.save(financeHeadsStructureForm, misSessionBean);
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
		refreshFinanceStructureForm(financeHeadsStructureForm);

		return mapping.findForward("display");

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		FinanceHeadsStructureForm financeHeadsStructureForm = (FinanceHeadsStructureForm) form;

		System.out.println("Inside financeHeads Update()->");
		log.debug("Inside CollectionCenterAction Update()->" + financeHeadsStructureForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = financialHeadStructureBo.update(financeHeadsStructureForm, misSessionBean);
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
		refreshFinanceStructureForm(financeHeadsStructureForm);

		return mapping.findForward("display");

	}

	private void refreshFinanceStructureForm(FinanceHeadsStructureForm financeHeadsStructureForm) {

		financeHeadsStructureForm.setDescription(null);
		financeHeadsStructureForm.setDetailedHead(null);
		financeHeadsStructureForm.setDimandNo(null);
		financeHeadsStructureForm.setFinancialYear(null);
		financeHeadsStructureForm.setMajorHead(null);
		financeHeadsStructureForm.setMajorHead(null);
		financeHeadsStructureForm.setMinorHead(null);
		financeHeadsStructureForm.setPlanNonPlan(null);
		financeHeadsStructureForm.setSOE(null);
		financeHeadsStructureForm.setSubMajorHead(null);
		financeHeadsStructureForm.setVotedCharged(null);
		financeHeadsStructureForm.setHeadStructureId(null);
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
		log.debug("Unspecified........FinancialHeadStructureAction");
		System.out.println("Unspecified........FinancialHeadStructureAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	public ActionForward fetchHeadType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FinanceHeadBeans> financeHeadBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("headType"))) {
				
				
				
				System.out.println("head type===========>"+request.getParameter("headType"));

				financeHeadBeans = financialHeadStructureDao.getHeadType(request.getParameter("headType"),null);
				System.out.println(financeHeadBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");
				for (FinanceHeadBeans financeHeadBean : financeHeadBeans) {
					buffer.append("<option value=\"").append(financeHeadBean.getHeadId()).append("\">");
					buffer.append(financeHeadBean.getHeadName()+"("+financeHeadBean.getHeadNumber()+")");
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

	
	public ActionForward fetchDiamond(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FinanceHeadBeans> financeHeadBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("headType"))) {

				financeHeadBeans = financialHeadStructureDao.getHeadType(null,request.getParameter("headType"));
				System.out.println(financeHeadBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Please Select");
				buffer.append("</option>");
				for (FinanceHeadBeans financeHeadBean : financeHeadBeans) {
					buffer.append("<option value=\"").append(financeHeadBean.getHeadId()).append("\">");
					buffer.append(financeHeadBean.getHeadName()+"("+financeHeadBean.getHeadNumber()+")");
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

	public ActionForward populateFinanceStructureHead(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

		
		List<FinanceDto> financeHeadBeans = null;
		List<FinanceDto> financeHeadBeans2 = null;
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
			financeHeadBeans = new ArrayList<FinanceDto>();

			financeHeadBeans = financialHeadStructureDao.populateFinanceStructureHead(searchParameter, clickedColumn,
					clickedColumnDir);

			financeHeadBeans2 = populateHeadStructureBean(financeHeadBeans);

			LocationJsonObject<FinanceDto> locationJson = new LocationJsonObject<FinanceDto>();

			if (MisUtility.ifEmpty(financeHeadBeans2)) {
				locationJson.setAaData(new ArrayList<FinanceDto>());
			}
			if (!MisUtility.ifEmpty(financeHeadBeans2)) {
				locationJson.setiTotalDisplayRecords(financeHeadBeans2.size());
				locationJson.setiTotalRecords(financeHeadBeans2.size());
			}
			List<FinanceDto> financeHeadBeans3 = null;
			if (!MisUtility.ifEmpty(financeHeadBeans)) {
				financeHeadBeans3 = financialHeadStructureBo.getListBasedOnPageNumber(financeHeadBeans2,
						pageDisplayLength, pageNumber, iDisplayStart);
				locationJson.setAaData(financeHeadBeans3);
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

	private List<FinanceDto> populateHeadStructureBean(List<FinanceDto> financeHeadBeans) {

		List<FinanceDto> financeDtos = null;
		String plan=null;
		String vote=null;
		try {
			if (!MisUtility.ifEmpty(financeHeadBeans)) {
				financeDtos = new ArrayList<FinanceDto>();
				for (FinanceDto financeDto : financeHeadBeans) {
					FinanceDto financeDto2 = new FinanceDto();
					
					if(MisUtility.ifEmpty(financeDto.getPlanNonPlan())){
						if(financeDto.getPlanNonPlan().equalsIgnoreCase("Plan")){
							plan="P";
						}else if(financeDto.getPlanNonPlan().equalsIgnoreCase("Non Plan")){
							plan="NP";
						}else{
							plan="";
						}
					}if(MisUtility.ifEmpty(financeDto.getVotedCharged())){
						if(financeDto.getVotedCharged().equalsIgnoreCase("Voted")){
							vote="V";
						}else if(financeDto.getVotedCharged().equalsIgnoreCase("Charged")){
							vote="C";
						}else{
							vote="";
						}
					}
					financeDto2.setHeadStructureName(financeDto.getHeadStructureName());
					financeDto2.setFinancialYear(financeDto.getFinancialYear());
					financeDto2.setHoa(financeDto.getDemandHeadNumber() + "~" + financeDto.getMajorHead() + "~"
							+ financeDto.getSubMajorHead() + "~" + financeDto.getMinorHead() + "~"
							+ financeDto.getSubHead() + "~" + financeDto.getDetailHead() + "~" + financeDto.getSoeHead()
							+ "~" +plan + "~" + vote);
					financeDto2.setHeadStructureId(financeDto.getHeadStructureId());
					financeDto2.setFinancialYears(financeDto.getFinancialYears());
					financeDto2.setDemandNumber(financeDto.getDemandNumber());
					financeDto2.setMajorHeads(financeDto.getMajorHeads());
					financeDto2.setSubMajorHeads(financeDto.getSubMajorHeads());
					financeDto2.setMinorHeads(financeDto.getMinorHeads());
					financeDto2.setSubHeads(financeDto.getSubHeads());
					financeDto2.setDetailedHead(financeDto.getDetailedHead());
					financeDto2.setSoeNumber(financeDto.getSoeNumber());
					financeDto2.setPlanNonplan(financeDto.getPlanNonplan());
					financeDto2.setVotedChargeds(financeDto.getVotedChargeds());
					financeDtos.add(financeDto2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return financeDtos;
	}
}
