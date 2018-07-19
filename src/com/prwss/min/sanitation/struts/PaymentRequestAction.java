/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
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
import com.prwss.min.sanitation.bean.BeneficiaryEntryDetailsDto;
import com.prwss.min.sanitation.bo.PaymentRequestBo;
import com.prwss.min.sanitation.dao.PaymentRequestDao;
import com.prwss.min.sanitation.form.PaymentRequestForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class PaymentRequestAction extends DispatchAction {

	private Logger log = Logger.getLogger(PaymentRequestAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private PaymentRequestBo paymentRequestBo;
	private PaymentRequestDao paymentRequestDao;

	public PaymentRequestDao getPaymentRequestDao() {
		return paymentRequestDao;
	}

	public void setPaymentRequestDao(PaymentRequestDao paymentRequestDao) {
		this.paymentRequestDao = paymentRequestDao;
	}

	public PaymentRequestBo getPaymentRequestBo() {
		return paymentRequestBo;
	}

	public void setPaymentRequestBo(PaymentRequestBo paymentRequestBo) {
		this.paymentRequestBo = paymentRequestBo;
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

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("---------------------inside find-----PaymentRequestAction--------------------------");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		List<BeneficiaryDto> beneficiaryDto = null;
		List<BeneficiaryDto> beneficiaryDtos = null;
		try {
			this.setAttrib(request);
			PaymentRequestForm paymentRequestForm = (PaymentRequestForm) form;
			paymentRequestForm.setLoginUser(misAuditBean.getEnteredBy());
			if (MisUtility.ifEmpty(paymentRequestForm)) {
				beneficiaryDto = paymentRequestBo.findBeneficiary(paymentRequestForm);
				if (MisUtility.ifEmpty(beneficiaryDto)) {
					beneficiaryDto = new ArrayList<BeneficiaryDto>();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.save", "No Record Found");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
				paymentRequestForm.setBeneficiaryDto(beneficiaryDto);

				request.setAttribute("beneficiaryDto", paymentRequestForm);
				request.setAttribute("block", paymentRequestForm.getBlock());
				request.setAttribute("village", paymentRequestForm.getVillage());

				// start generate bill business logic
				
				beneficiaryDto = paymentRequestDao.getBillDetails(paymentRequestForm);
				if (!MisUtility.ifEmpty(beneficiaryDto)) {
					request.setAttribute("billNo", beneficiaryDto.get(0).getBillno());
					paymentRequestForm.setBeneficiaryDtos(beneficiaryDto);
					request.setAttribute("beneficiaryDtos", paymentRequestForm);
					
					beneficiaryDtos = paymentRequestDao.getBillAmount(paymentRequestForm);
					if (!MisUtility.ifEmpty(beneficiaryDtos)) {
						request.setAttribute("amount", beneficiaryDtos.get(0).getAmount());
					}
				} 
				

				// end generate bill business logic
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		}
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("---inside save--------");

		boolean status = false;
		PaymentRequestForm paymentRequestForm = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			paymentRequestForm = (PaymentRequestForm) form;

			paymentRequestForm.setLoginUser(misAuditBean.getEnteredBy());
			if (MisUtility.ifEmpty(paymentRequestForm)) {
				status = paymentRequestBo.saveBeneficiary(paymentRequestForm);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved Payment Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {

			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		// refreshForm(validateBeneficiary);

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

		System.out.println("Unspecified........PaymentRequestAction.........");
		
		return mapping.findForward("display");
	}
	public ActionForward forward(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		
		System.out.println("---inside forward--------");

		boolean status = false;
		PaymentRequestForm paymentRequestForm = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			paymentRequestForm = (PaymentRequestForm) form;

			paymentRequestForm.setLoginUser(misAuditBean.getEnteredBy());
			if (MisUtility.ifEmpty(paymentRequestForm)) {
				status = paymentRequestBo.updatePaymentDetails(paymentRequestForm);
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
		} catch (Exception e) {

			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}

	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

	public ActionForward progressStageDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<BeneficiaryDto> beneficiaryDto = null;
		List<BeneficiaryDto> beneficiaryDto1 = null;
		try {

			if (MisUtility.ifEmpty(request.getParameter("progressId"))) {
				beneficiaryDto = paymentRequestDao.getProgressStageDetails(request.getParameter("progressId"));
			}
			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				beneficiaryDto1=getProgressStageDto(beneficiaryDto);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryDto1.get(0));
				System.out.println("---1----"+json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private List<BeneficiaryDto> getProgressStageDto(List<BeneficiaryDto> beneficiaryDto) {

		List<BeneficiaryDto> beneficiayLists = null;

		try {
			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				beneficiayLists = new ArrayList<BeneficiaryDto>();
				Iterator<BeneficiaryDto> iterator = beneficiaryDto.iterator();
				while (iterator.hasNext()) {
					BeneficiaryDto beneficiaryDto2 = (BeneficiaryDto) iterator.next();
					String imageName = getBeneficiaryImage(beneficiaryDto2);
					beneficiaryDto2.setImage(imageName);
					beneficiaryDto2.setCreationDate(MisUtility.convertDateString(beneficiaryDto2.getCrt_date()));
					beneficiayLists.add(beneficiaryDto2);
				}
			}
		} catch (Exception e) {

		}
		return beneficiayLists;
	}
	
	private String getBeneficiaryImage(BeneficiaryDto beneficiaryDto2) {
		BufferedImage image = null;
		String b64 = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(beneficiaryDto2.getPictureStage());
			image = ImageIO.read(bis);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			bis.close();
			b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		} catch (Exception e) {

		}
		return b64;
	}
}
