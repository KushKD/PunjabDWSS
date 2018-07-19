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

import com.prwss.min.dao.ReceivingSampleDao;
import com.prwss.min.sanitation.bo.GramPanchayatRegisterBO;
import com.prwss.min.sanitation.form.GramPanchayatRegisterForm;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class GramPanchayatRegisterAction extends DispatchAction {

	private Logger log = Logger.getLogger(GramPanchayatRegisterAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	LocationMasterDao locationMasterDao;
	private ReceivingSampleDao receiveSampleDao;
	private GramPanchayatRegisterBO gramPanchayatRegisterBO;

	public GramPanchayatRegisterBO getGramPanchayatRegisterBO() {
		return gramPanchayatRegisterBO;
	}

	public void setGramPanchayatRegisterBO(GramPanchayatRegisterBO gramPanchayatRegisterBO) {
		this.gramPanchayatRegisterBO = gramPanchayatRegisterBO;
	}

	public ReceivingSampleDao getReceiveSampleDao() {
		return receiveSampleDao;
	}

	public void setReceiveSampleDao(ReceivingSampleDao receiveSampleDao) {
		this.receiveSampleDao = receiveSampleDao;
	}

	/**
	 * @return the locationMasterDao
	 */
	public LocationMasterDao getLocationMasterDao() {
		return locationMasterDao;
	}

	/**
	 * @param locationMasterDao
	 *            the locationMasterDao to set
	 */
	public void setLocationMasterDao(LocationMasterDao locationMasterDao) {
		this.locationMasterDao = locationMasterDao;
	}

	/**
	 * @return the misAuditBean
	 */
	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}

	/**
	 * @param misAuditBean
	 *            the misAuditBean to set
	 */
	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	/**
	 * @return the misSessionBean
	 */
	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	/**
	 * @param misSessionBean
	 *            the misSessionBean to set
	 */
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		this.setAttrib(request);
		boolean status = false;
		GramPanchayatRegisterForm grampanchayatform = (GramPanchayatRegisterForm) form;
		
		System.out.println("inside save GramPanchayatRegisterForm --------------------" + grampanchayatform.toString());
		
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			
			try{
				grampanchayatform.setCreatedByUser(Long.toString(misSessionBean.getEnteredBy()));
				
			if (MisUtility.ifEmpty(grampanchayatform)) {
				status = gramPanchayatRegisterBO.saveGramPanchayatRegisterData(grampanchayatform);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save","Record saved for Family Id : " + grampanchayatform.getAadhaarNumber());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				 // refreshsampleDistribution(sampleDistributionForm);
				 
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Internal error please check log..");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
			}catch (MISException e) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", "Internal error please check log..");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		}
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

		System.out.println("Unspecified........Ghram Panchayat");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	/*public ActionForward fetchHabitation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchHabitation");
		System.out.println("ComboMaster"+ request.getParameter("comboMaster"));
		List<VillageDetailsBean> performaMaserBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("comboMaster"))) {
				performaMaserBeans = receiveSampleDao.findHabitationFromVillage(request.getParameter("villageId"));
				System.out.println(performaMaserBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select Habitation");
				buffer.append("</option>");
				for (VillageDetailsBean schemeHeaderBean2 : performaMaserBeans) {
					buffer.append("<option value=\"").append(schemeHeaderBean2.getParent_habitation_name())
							.append("\">");
					buffer.append(schemeHeaderBean2.getParent_habitation_name());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} 
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 
		catch (Exception e) {
			log.error(e);
		}

		return null;
	}*/
}
