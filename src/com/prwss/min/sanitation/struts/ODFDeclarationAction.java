/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.io.IOException;

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

import com.prwss.min.sanitation.bo.ODFDeclarationBo;
import com.prwss.min.sanitation.form.ODFDeclarationForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class ODFDeclarationAction extends DispatchAction {
	private Logger log = Logger.getLogger(BenifecieryEntryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ODFDeclarationBo odfDeclarationBo;

	public ODFDeclarationBo getOdfDeclarationBo() {
		return odfDeclarationBo;
	}

	public void setOdfDeclarationBo(ODFDeclarationBo odfDeclarationBo) {
		this.odfDeclarationBo = odfDeclarationBo;
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
		ODFDeclarationForm odfDeclarationForm = (ODFDeclarationForm) form;

		log.debug(
				"----------------inside ODFDeclarationAction save  --------------------" + odfDeclarationForm.toString());
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if (MisUtility.ifEmpty(odfDeclarationForm)) {

				if (!(odfDeclarationForm.getDeclaration().getContentType().equalsIgnoreCase("application/pdf"))) {
					throw new MISException(MISExceptionCodes.MIS003, "Declaration File Format should be of pdf");
				}
				if (odfDeclarationForm.getDeclaration().getFileSize() > 512000) {
					throw new MISException(MISExceptionCodes.MIS003,
							"Declaration File size should not be greater than 500 KB");
				}

				odfDeclarationForm.setCreatedBy(misSessionBean.getEnteredBy());
				status = odfDeclarationBo.save(odfDeclarationForm);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"ODF Declaration successfully Succefully saved ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				// refreshBeneficiaryForm(beneficiaryForm);
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

		} 

		System.out.println("----------------Exit ODFDeclarationAction save  --------------------");
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

		System.out.println("Unspecified........ODFDeclarationAction.........");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

}
