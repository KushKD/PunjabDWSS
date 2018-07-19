/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.io.FileOutputStream;
import java.io.IOException;
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

import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.bo.BeneficiaryBo;
import com.prwss.min.sanitation.form.BeneficiaryForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BenifecieryEntryAction extends DispatchAction {

	private Logger log = Logger.getLogger(BenifecieryEntryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private BeneficiaryBo<BeneficiaryForm> beneficiaryBo;

	public BeneficiaryBo<BeneficiaryForm> getBeneficiaryBo() {
		return beneficiaryBo;
	}

	public void setBeneficiaryBo(BeneficiaryBo<BeneficiaryForm> beneficiaryBo) {
		this.beneficiaryBo = beneficiaryBo;
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
		BeneficiaryForm beneficiaryForm = (BeneficiaryForm) form;

		log.debug("----------------inside Beneficiary save  --------------------" + beneficiaryForm.toString());
		List<BeneficiaryEntryBean> beneficiaryEntryBeans = null;
		FileOutputStream outputStream = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if (MisUtility.ifEmpty(beneficiaryForm)) {
				
				if(!(beneficiaryForm.getPhotograph().getFileName().endsWith(".jpeg")|| beneficiaryForm.getPhotograph().getFileName().endsWith(".png")||beneficiaryForm.getPhotograph().getFileName().endsWith(".jpg"))){
					throw new MISException(MISExceptionCodes.MIS003,"Photograph File Format should be of jpeg/jpg/png");
				}
				if(beneficiaryForm.getPhotograph().getFileSize()>512000){
					throw new MISException(MISExceptionCodes.MIS003,"Photograph File size should not be greater than 500 KB");
				}
				
				if(!(beneficiaryForm.getElectricityBill().getContentType().equalsIgnoreCase("application/pdf"))){
					throw new MISException(MISExceptionCodes.MIS003,"Electricity Bill File Format should be of pdf");
				}
				if(beneficiaryForm.getElectricityBill().getFileSize()>512000){
					throw new MISException(MISExceptionCodes.MIS003,"Electricity Bill File size should not be greater than 500 KB");
				}
				
				
				beneficiaryEntryBeans = beneficiaryBo.validateBeneficiaryDetails(beneficiaryForm);
				if (!MisUtility.ifEmpty(beneficiaryEntryBeans)) {
					throw new MISException(MISExceptionCodes.MIS001, "Entry Already Exist for Aadhaar Number("
							+ beneficiaryEntryBeans.get(0).getAadhaarNumber() + ")");
				} else {
					beneficiaryForm.setLoginUser(misSessionBean.getEnteredBy());
					status = beneficiaryBo.saveBeneficiaryDetails(beneficiaryForm);
				}
			}
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Succefully saved for User " + "\t" + beneficiaryForm.getPersonName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "User" + beneficiaryForm.getPersonName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshBeneficiaryForm(beneficiaryForm);
			}
		} catch (MISException e) {
			request.setAttribute("level2", "true");

			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("invalide.file.extension", e.getMessage());
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

		}finally{
			if(outputStream!=null)
				outputStream.close();
		}

		System.out.println("----------------Exit Beneficiary save  --------------------");
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

		System.out.println("Unspecified........BenifecieryEntryAction.........");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

	private void refreshBeneficiaryForm(BeneficiaryForm beneficiaryForm) {
		beneficiaryForm.setAccountNo(null);
		beneficiaryForm.setAdharNumber(null);
		beneficiaryForm.setBankName(null);
		beneficiaryForm.setBenifCategory(null);
		beneficiaryForm.setBlock(null);
		beneficiaryForm.setBranch(null);
		beneficiaryForm.setCast(null);
		beneficiaryForm.setDistrict(null);
		beneficiaryForm.setElectricityBill(null);
		beneficiaryForm.setElectricityCon(null);
		beneficiaryForm.setFatherSpouseName(null);
		beneficiaryForm.setGender(null);
		beneficiaryForm.setGramPanchayat(null);
		beneficiaryForm.setIfsCode(null);
		beneficiaryForm.setPersonName(null);
		beneficiaryForm.setPhoneNumber(null);
		beneficiaryForm.setPhotograph(null);
		beneficiaryForm.setPoiNumber(null);
		beneficiaryForm.setPoiType(null);
		beneficiaryForm.setReligion(null);
	}

}
