/**
 * 
 */
package com.prwss.min.sanitation.struts;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bo.ApprovePaymentBo;
import com.prwss.min.sanitation.dao.VerifyPaymentDao;
import com.prwss.min.sanitation.form.ApprovePaymentForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ApprovePaymentAction extends DispatchAction {

	private Logger log = Logger.getLogger(ApprovePaymentAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private VerifyPaymentDao verifyPaymentDao;
	private ApprovePaymentBo approvePaymentBo;

	public ApprovePaymentBo getApprovePaymentBo() {
		return approvePaymentBo;
	}

	public void setApprovePaymentBo(ApprovePaymentBo approvePaymentBo) {
		this.approvePaymentBo = approvePaymentBo;
	}

	public VerifyPaymentDao getVerifyPaymentDao() {
		return verifyPaymentDao;
	}

	public void setVerifyPaymentDao(VerifyPaymentDao verifyPaymentDao) {
		this.verifyPaymentDao = verifyPaymentDao;
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

	public ActionForward getPaymentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		System.out.println("inside getPaymentDetails--------");
		String json2 = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);

			Long enteredBy = misAuditBean.getEnteredBy();
			List<BeneficiaryDto> beneficiaryDtos = verifyPaymentDao.getBillDetails(enteredBy);
			System.out.println(beneficiaryDtos.toString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if (!MisUtility.ifEmpty(beneficiaryDtos)) {
				json2 = gson.toJson(beneficiaryDtos);
			}
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}

		return null;
	}

	public ActionForward viewPaymentDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		System.out.println("inside viewPaymentDetails--------");
		String json2 = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);

			Long enteredBy = misAuditBean.getEnteredBy();

			String paymetRequestId = request.getParameter("paymetRequestId");
			List<BeneficiaryDto> beneficiaryDtos = verifyPaymentDao.getPaymentDetails(paymetRequestId, enteredBy);
			System.out.println(beneficiaryDtos.toString());
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if (!MisUtility.ifEmpty(beneficiaryDtos)) {
				json2 = gson.toJson(beneficiaryDtos);
			}
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}

		return null;
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

		System.out.println("Unspecified........VerifyPaymentAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		System.out.println("--------inside delete--------");
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);

			Long enteredBy = misAuditBean.getEnteredBy();

			String requestDetailId = request.getParameter("requestDetailId");

			 approvePaymentBo.delete(requestDetailId, enteredBy);

			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print("Successfully Removed");
		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}

		return null;
	}

	public ActionForward forward(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("---inside forward---111-----");

		boolean status = false;
		ApprovePaymentForm approvePaymentForm = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			approvePaymentForm = (ApprovePaymentForm) form;
			String validationRequestId = request.getParameter("request");

			if (MisUtility.ifEmpty(validationRequestId)) {
				approvePaymentForm.setValidationRequestId(validationRequestId);
				String forward = "forward";
				approvePaymentForm.setEnterBy(misAuditBean.getEnteredBy());
				if (MisUtility.ifEmpty(approvePaymentForm)) {
					status = approvePaymentBo.updatePaymentDetails(approvePaymentForm, forward);
				}
				if (status) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.save", "Successfully Approve");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					request.setAttribute("level2", "true");
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save", "Internal error");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}
		} catch (Exception e) {

			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}

	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("---inside forward--------");

		boolean status = false;
		ApprovePaymentForm approvePaymentForm = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			approvePaymentForm = (ApprovePaymentForm) form;
			String validationRequestId = request.getParameter("request");

			if (MisUtility.ifEmpty(validationRequestId)) {
				
				approvePaymentForm.setValidationRequestId(validationRequestId);
				approvePaymentForm.setEnterBy(misAuditBean.getEnteredBy());

				String back = "return";
				if (MisUtility.ifEmpty(approvePaymentForm)) {
					status = approvePaymentBo.updatePaymentDetails(approvePaymentForm, back);
				}
				if (status) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.save", "Successfully Forwarded");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					request.setAttribute("level2", "true");
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save", "Internal error");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}
}
