/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.struts.util.LabelValueBean;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bo.ReValidateBeneficiaryBo;
import com.prwss.min.sanitation.bo.ValidateBeneficiaryBo;
import com.prwss.min.sanitation.dao.ValidateBeneficiaryDao;
import com.prwss.min.sanitation.form.ReValidateBeneficiary;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

/**
 * @author BH390738
 *
 */
public class ReValidateBeneficiaryAction extends DispatchAction {

	private Logger log = Logger.getLogger(ReValidateBeneficiaryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ReValidateBeneficiaryBo reValidateBeneficiaryBo;
	private LocationDao locationDao;
	private ValidateBeneficiaryBo validateBeneficiaryBo;
	private ValidateBeneficiaryDao validateBeneficiaryDao;

	public ValidateBeneficiaryBo getValidateBeneficiaryBo() {
		return validateBeneficiaryBo;
	}

	public void setValidateBeneficiaryBo(ValidateBeneficiaryBo validateBeneficiaryBo) {
		this.validateBeneficiaryBo = validateBeneficiaryBo;
	}

	public ValidateBeneficiaryDao getValidateBeneficiaryDao() {
		return validateBeneficiaryDao;
	}

	public void setValidateBeneficiaryDao(ValidateBeneficiaryDao validateBeneficiaryDao) {
		this.validateBeneficiaryDao = validateBeneficiaryDao;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public ReValidateBeneficiaryBo getReValidateBeneficiaryBo() {
		return reValidateBeneficiaryBo;
	}

	public void setReValidateBeneficiaryBo(ReValidateBeneficiaryBo reValidateBeneficiaryBo) {
		this.reValidateBeneficiaryBo = reValidateBeneficiaryBo;
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

		System.out
				.println("---------------------inside find-----ReValidateBeneficiaryAction--------------------------");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			this.setAttrib(request);
			ReValidateBeneficiary validateBeneficiary = (ReValidateBeneficiary) form;

			if (MisUtility.ifEmpty(validateBeneficiary)) {
				beneficiaryDto = reValidateBeneficiaryBo.getValidateBeneficiary(validateBeneficiary.getSurveyId(),
						misAuditBean.getEnteredBy());
				if (MisUtility.ifEmpty(beneficiaryDto)) {
					beneficiaryDto=new ArrayList<BeneficiaryDto>();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.save", "No Record Found");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
				validateBeneficiary.setBeneficiaryDto(beneficiaryDto);
				
				request.setAttribute("beneficiaryDto", validateBeneficiary);
				if (MisUtility.ifEmpty(validateBeneficiary)) {
					beneficiaryDto = reValidateBeneficiaryBo.getValidatedBeneficiary(validateBeneficiary.getSurveyId(),
							misAuditBean.getEnteredBy());
					if(!MisUtility.ifEmpty(beneficiaryDto)){
						validateBeneficiary.setBeneficiaryDtos(beneficiaryDto);
					}else{
						beneficiaryDto=new ArrayList<BeneficiaryDto>();
						validateBeneficiary.setBeneficiaryDtos(beneficiaryDto);
					}

					request.setAttribute("beneficiaryDtos", validateBeneficiary);
				}
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {
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
		ReValidateBeneficiary validateBeneficiary = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			validateBeneficiary = (ReValidateBeneficiary) form;

			validateBeneficiary.setEntBy(Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			if (MisUtility.ifEmpty(validateBeneficiary)) {
				status = reValidateBeneficiaryBo.saveBeneficiary(validateBeneficiary);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Saved Beneficiary Details");
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
		refreshForm(validateBeneficiary);

		return mapping.findForward("display");
	}

	public ActionForward forward(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("---inside save--------");

		boolean status = false;
		ReValidateBeneficiary forwardBeneficiaryForm = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			forwardBeneficiaryForm = (ReValidateBeneficiary) form;

			forwardBeneficiaryForm.setEntBy(Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			if (MisUtility.ifEmpty(forwardBeneficiaryForm)) {
				status = validateBeneficiaryBo.updateRequest(forwardBeneficiaryForm);
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

	public ActionForward saveReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("---inside saveReturn--------");

		boolean status = false;
		ReValidateBeneficiary forwardBeneficiaryForm = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			forwardBeneficiaryForm = (ReValidateBeneficiary) form;

			forwardBeneficiaryForm.setEntBy(Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			if (MisUtility.ifEmpty(forwardBeneficiaryForm)) {
				status = validateBeneficiaryBo.updateValidationRequestToReturn(forwardBeneficiaryForm);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save", "Successfully Return");
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

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String loggedInUsr;
		Set<LabelValueBean> surveys = null;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}

		this.setAttrib(request);

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		}
		loggedInUsr = String.valueOf(misSessionBean.getEnteredBy());
		if (MisUtility.ifEmpty(loggedInUsr)) {

			List<BeneficiaryDto> surveyMasterBeans = locationDao.fetchForwardedSurvey(loggedInUsr);
			surveys = new TreeSet<LabelValueBean>();
			if (!MisUtility.ifEmpty(surveyMasterBeans)) {

				for (BeneficiaryDto surveyMaster : surveyMasterBeans) {
					surveys.add(new LabelValueBean(surveyMaster.getSurveyName(),
							String.valueOf(surveyMaster.getSurveyId())));
				}

			}
		}
		request.getSession().setAttribute("surveyId", surveys);
		System.out.println("Unspecified........ValidateBeneficiaryAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/* request.setAttribute("d__ky", "block@district@villageId"); */
	}

	private void refreshForm(ReValidateBeneficiary validateBeneficiary) {
		validateBeneficiary.setSurveyId(null);
	}

	/*public ActionForward finds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out
				.println("---------------------inside find-----ReValidateBeneficiaryAction--------------------------");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			this.setAttrib(request);
			ReValidateBeneficiary validateBeneficiary = (ReValidateBeneficiary) form;

			if (MisUtility.ifEmpty(validateBeneficiary)) {
				beneficiaryDto = reValidateBeneficiaryBo.getValidatedBeneficiary(validateBeneficiary.getSurveyId(),
						misAuditBean.getEnteredBy());
				validateBeneficiary.setBeneficiaryDto(beneficiaryDto);

				request.setAttribute("beneficiaryDtos", validateBeneficiary);
			}
			request.setAttribute("level2", "true");

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save" + e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		}
		return mapping.findForward("display");
	}
*/
}
