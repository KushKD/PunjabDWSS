/**
 * 
 */
package com.prwss.min.finance.struts;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.FinanceHeadStructureBean;
import com.prwss.min.finance.bean.FundRequestDto;
import com.prwss.min.finance.bo.AllocationBo;
import com.prwss.min.finance.dao.AllocationDao;
import com.prwss.min.finance.form.AllocationForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class AllocationAction extends DispatchAction{


	private Logger log = Logger.getLogger(AllocationAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private AllocationDao allocationDao;
	private AllocationBo allocationBo;
	
	
	
	public AllocationBo getAllocationBo() {
		return allocationBo;
	}
	public void setAllocationBo(AllocationBo allocationBo) {
		this.allocationBo = allocationBo;
	}
	public AllocationDao getAllocationDao() {
		return allocationDao;
	}
	public void setAllocationDao(AllocationDao allocationDao) {
		this.allocationDao = allocationDao;
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
		AllocationForm allocationForm = (AllocationForm) form;

		System.out.println("Inside allocation Save()->");
		log.debug("Inside AllocationAction Save()->" + allocationForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = allocationBo.save(allocationForm, misSessionBean);
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
		allocationForm(allocationForm);

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
		//AllocationForm allocationForm = (AllocationForm) form;
		//allocationForm(allocationForm);
		log.debug("Unspecified........allocationForm");
		System.out.println("Unspecified........allocationForm");

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}
	
	private void allocationForm(AllocationForm allocationForm){
		allocationForm.setRequestNo(null);
		allocationForm.setHead(null);
		allocationForm.setAllocationNumber(null);
		allocationForm.setDateAllocation(null);
		allocationForm.setAmountReleased(null);
		allocationForm.setRevoke(null);
		allocationForm.setRevoked(null);
		allocationForm.setNetAmount(null);
	/*	allocationForm.setActivity(null);
		allocationForm.setValue(null);
		allocationForm.setTotal(null);
		allocationForm.setNrdwpNormal(null);
		allocationForm.setNrdwpOAndM(null);
		allocationForm.setNrdwpSupport(null);
		allocationForm.setNrdwpSustain(null);*/
		allocationForm.setAllocationNumber(null);
		
	}
	
	public ActionForward fetchRequestNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		StringBuffer buffer = new StringBuffer();
		List<FundRequestDto> fundRequestMasterBeans=null;
		try {
			
			fundRequestMasterBeans=allocationDao.getFundRequestMasterBean(null);
			buffer.append("<option value='' selected>");
			buffer.append("Please Select");
			buffer.append("</option>");
			if(!MisUtility.ifEmpty(fundRequestMasterBeans)){
				for (FundRequestDto fundRequestMasterBean : fundRequestMasterBeans) {
					buffer.append("<option value=\"")
							.append(fundRequestMasterBean.getFundRequestId())
							.append("\">");
					buffer.append(fundRequestMasterBean.getRequest_no());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward fetchInstallmentAmountDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		StringBuffer buffer = new StringBuffer();
		List<FundRequestDto> fundRequestMasterBeans=null;
		try {
			if(MisUtility.ifEmpty(request.getParameter("requestNo"))){
			fundRequestMasterBeans=allocationDao.getFetchInstallmentAmountDetails(request.getParameter("requestNo"));
			
			if(!MisUtility.ifEmpty(fundRequestMasterBeans)){
				for (FundRequestDto fundRequestDto : fundRequestMasterBeans) {
					buffer.append(fundRequestDto.getValue_of_inst());
					buffer.append("~");
					buffer.append(fundRequestDto.getInstallmentName());
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward fetchHeads(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		StringBuffer buffer = new StringBuffer();
		List<FinanceHeadStructureBean> financeHeadStructureBeans=null;
		try {
			
			financeHeadStructureBeans=allocationDao.getHeadBean();
			buffer.append("<option value='' selected>");
			buffer.append("Please Select");
			buffer.append("</option>");
			if(!MisUtility.ifEmpty(financeHeadStructureBeans)){
				for (FinanceHeadStructureBean  financeHeadStructureBean : financeHeadStructureBeans) {
					buffer.append("<option value=\"")
							.append(financeHeadStructureBean.getHeadStructureId())
							.append("\">");
					buffer.append(financeHeadStructureBean.getHeadStructureName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward fetchFundRequestDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		List<FundRequestDto> fundRequestDtos = null;
		StringBuffer buffer = new StringBuffer();

		try {
			
			System.out.println();
			if (MisUtility.ifEmpty(request.getParameter("request"))) {

				fundRequestDtos=allocationDao.getFundRequestMasterBean(request.getParameter("request"));
				System.out.println("---------------1----------------"+fundRequestDtos.toString());
				if (!MisUtility.ifEmpty(fundRequestDtos)) {
					for (FundRequestDto fundRequestDto : fundRequestDtos) {
						buffer.append(fundRequestDto.getDivisionName());
						buffer.append("~");
						buffer.append(fundRequestDto.getSchemeNo());
						buffer.append("~");
						buffer.append(fundRequestDto.getSchemeName());
						buffer.append("~");
						buffer.append(fundRequestDto.getComponent());
						buffer.append("~");
						buffer.append(fundRequestDto.getDdoNumber());
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
	
	
	
}
