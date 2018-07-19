/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.io.FileOutputStream;
import java.io.IOException;
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

import com.prwss.min.sanitation.bean.MotivatorBean;
import com.prwss.min.sanitation.bo.MotivatorBo;
import com.prwss.min.sanitation.form.MotivatorEntryForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MotivatorEntryAction extends DispatchAction{

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private MotivatorBo motivatorBo;
	
	
	
	public MotivatorBo getMotivatorBo() {
		return motivatorBo;
	}
	public void setMotivatorBo(MotivatorBo motivatorBo) {
		this.motivatorBo = motivatorBo;
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
		MotivatorEntryForm motivatorEntryForm = (MotivatorEntryForm) form;

		log.debug("----------------inside Motivator save  --------------------" + motivatorEntryForm.toString());
		List<MotivatorBean> motivatorEntryBean = null;
		FileOutputStream outputStream = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			if (MisUtility.ifEmpty(motivatorEntryForm)) {
				
				if(!(motivatorEntryForm.getPhotograph().getFileName().endsWith(".jpeg")|| motivatorEntryForm.getPhotograph().getFileName().endsWith(".png")||motivatorEntryForm.getPhotograph().getFileName().endsWith(".jpg"))){
					throw new MISException(MISExceptionCodes.MIS003,"Photograph File Format should be of jpeg/jpg/png");
				}
				if(motivatorEntryForm.getPhotograph().getFileSize()>512000){
					throw new MISException(MISExceptionCodes.MIS003,"Photograph File size should not be greater than 500 KB");
				}
				
				motivatorEntryBean = motivatorBo.validateMotivatorDetails(motivatorEntryForm);
				if (!MisUtility.ifEmpty(motivatorEntryBean)) {
					throw new MISException(MISExceptionCodes.MIS001, "Entry Already Exist for Aadhaar Number("
							+ motivatorEntryBean.get(0).getAadhaarNumber() + ")");
				} else {
					motivatorEntryForm.setLoginUser(misSessionBean.getEnteredBy());
					status = motivatorBo.saveMotivatorDetails(motivatorEntryForm);
				}
			}
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Succefully saved for User " + "\t" + motivatorEntryForm.getPersonName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Succefully saved for User " + "\t" + motivatorEntryForm.getPersonName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				//refreshBeneficiaryForm(motivatorEntryForm);
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

		System.out.println("----------------Exit Motivator Entry save  --------------------");
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

		System.out.println("Unspecified........MotivatorEntryAction");
		return mapping.findForward("display");
	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "block@district@villageId");
	}
	
	
}
