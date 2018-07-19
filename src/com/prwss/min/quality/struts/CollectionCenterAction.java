/**
 * 
 */
package com.prwss.min.quality.struts;

import java.io.IOException;
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

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.quality.CollectionCenterBo;
import com.prwss.min.quality.CollectionCenterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

/**
 * @author BH390738
 *
 */
public class CollectionCenterAction extends DispatchAction {

	
	private Logger log = Logger.getLogger(CollectionCenterAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private LocationDao locationDao;
	private CollectionCenterBo collectionCenterBo;
	
	
	public CollectionCenterBo getCollectionCenterBo() {
		return collectionCenterBo;
	}
	public void setCollectionCenterBo(CollectionCenterBo collectionCenterBo) {
		this.collectionCenterBo = collectionCenterBo;
	}
	public LocationDao getLocationDao() {
		return locationDao;
	}
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
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
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		CollectionCenterForm collectionCenterForm = (CollectionCenterForm) form;
		
		System.out.println("Inside CollectionCenterAction Save()->");
		log.debug("Inside CollectionCenterAction Save()->"+collectionCenterForm.toString());
		try {
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = collectionCenterBo.save(collectionCenterForm,misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Saved" + "\t" +  collectionCenterForm.getName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Internal error" + "\t" + collectionCenterForm.getName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		}
		catch(Exception e){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshCollectionCenterForm(collectionCenterForm);
		
		return mapping.findForward("display");

	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Set<LabelValueBean> labName = null;

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);

		long empId = misAuditBean.getEnteredBy();
		labName = new TreeSet<LabelValueBean>();
		List<Long> labIdLst = new ArrayList<Long>();
		StringBuffer buffer = new StringBuffer();

		buffer.append("<option value='' selected>");
		buffer.append("Select Lab");
		buffer.append("</option>");
		List<LabEmployee> labEmp = locationDao.getLabDetails(Integer.parseInt(String.valueOf(empId)));

		if (!MisUtility.ifEmpty(labEmp)) {
			for (LabEmployee employee : labEmp) {
				labIdLst.add(Long.parseLong(String.valueOf(employee.getLabId())));
			}
			List<LabMasterBean> labMasterBean = locationDao.getLabName(labIdLst);
			for (LabMasterBean beans : labMasterBean) {
				labName.add(new LabelValueBean(beans.getLabName(), String.valueOf(beans.getLabId())));
			}

		}
		request.getSession().setAttribute("labNames", labName);
		System.out.println("Unspecified........locationMaster");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
	}

	private void refreshCollectionCenterForm(CollectionCenterForm collectionCenterForm) {

		collectionCenterForm.setAddress(null);
		collectionCenterForm.setPhoneNo(null);
		collectionCenterForm.setName(null);
		collectionCenterForm.setLab(null);
	}
}
