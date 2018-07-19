/**
 * 
 */
package com.prwss.min.finance.struts;

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

import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bo.DivisionBudgetBo;
import com.prwss.min.finance.dao.DivisionBudgetDao;
import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class UpdateBudgetDivision extends DispatchAction {

	private Logger log = Logger.getLogger(UpdateBudgetDivision.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private DivisionBudgetDao divisionBudgetDao;
	private DivisionBudgetBo<FinanceDto> divisionBudgetBo;
	
	

	public DivisionBudgetBo<FinanceDto> getDivisionBudgetBo() {
		return divisionBudgetBo;
	}

	public void setDivisionBudgetBo(DivisionBudgetBo<FinanceDto> divisionBudgetBo) {
		this.divisionBudgetBo = divisionBudgetBo;
	}

	public DivisionBudgetDao getDivisionBudgetDao() {
		return divisionBudgetDao;
	}

	public void setDivisionBudgetDao(DivisionBudgetDao divisionBudgetDao) {
		this.divisionBudgetDao = divisionBudgetDao;
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
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		String budgetReferenceNo = null;
		DivisionBudgetForm divisionBudgetForm = (DivisionBudgetForm) form;

		System.out.println("Inside DivisionBudgetAction Update()->");

		log.debug("Inside DivisionBudgetAction Update()->" + divisionBudgetForm.toString());

		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			divisionBudgetForm.setBudgetDiv("updateNodal");
			budgetReferenceNo = divisionBudgetBo.update(divisionBudgetForm, misSessionBean);
			if (MisUtility.ifEmpty(budgetReferenceNo)) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Divisional Budget Reference Id" + "\t" + budgetReferenceNo + "\t" + "Successfully Update");
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
		return mapping.findForward("display");

	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

		return mapping.findForward("display");
	}



	private void setAttrib(HttpServletRequest request) {

		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

}
