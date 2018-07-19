/**
 * 
 */
package com.prwss.min.SDU.Struts;

import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponetDto;
import com.prwss.min.SDU.bean.StageDetailBean;
import com.prwss.min.SDU.BO.StageComponentMapingBO;
import com.prwss.min.SDU.dao.StageComponentMappingDao;
import com.prwss.min.SDU.form.StageCompMpgPlanGrid;
import com.prwss.min.SDU.form.StageComponentMpgForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author BH390738
 *
 */
public class StageComponentMpgAction extends DispatchAction {

	private Logger log = Logger.getLogger(StageComponentMpgAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	
	private StageComponentMappingDao stageComponentMpgDao;
	private StageComponentMapingBO stageComponentMpgBo;
	
	
	
	
	public StageComponentMapingBO getStageComponentMpgBo() {
		return stageComponentMpgBo;
	}

	public void setStageComponentMpgBo(StageComponentMapingBO stageComponentMpgBo) {
		this.stageComponentMpgBo = stageComponentMpgBo;
	}

	public StageComponentMappingDao getStageComponentMpgDao() {
		return stageComponentMpgDao;
	}

	public void setStageComponentMpgDao(StageComponentMappingDao stageComponentMpgDao) {
		this.stageComponentMpgDao = stageComponentMpgDao;
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

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		boolean status = false;
		StageComponentMpgForm stageComponentMpgForm = null;
		//List<DivisionWiseSummaryBean> idOutlinesTable = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			stageComponentMpgForm = (StageComponentMpgForm) actionForm;
			
			/*try{
				idOutlinesTable = stageComponentMpgDao.getOutlineIds(stageComponentMpgForm.getFinancialYear(), stageComponentMpgForm.getDivision(), stageComponentMpgForm.getCategory());
				//Pass the List and the GridData to BO File
				
				
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}*/

			if (MisUtility.ifEmpty(stageComponentMpgForm)) {
				System.out.println("inside sa-ve----------------------------"+stageComponentMpgForm.toString());
				status = stageComponentMpgBo.CheckBeforeSave(stageComponentMpgForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Mapped " + "\t"); //+ stageComponentMpgForm.getFinancialYear());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshStageComponentMpgForm(stageComponentMpgForm);
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("Unspecified.................");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		StageComponentMpgForm stageComponentMpgForm = (StageComponentMpgForm) form;
		refreshStageComponentMpgForm(stageComponentMpgForm);
		this.setAttrib(request);
		System.out.println("Unspecified........StageComponentMpgAction.........");
		
		return mapping.findForward("display");
	}

	private void refreshStageComponentMpgForm(StageComponentMpgForm stageComponentMpgForm) {

		stageComponentMpgForm.setCategory(null);
		stageComponentMpgForm.setComponent(null);
		//stageComponentMpgForm.setDivision(null);
		//stageComponentMpgForm.setFinancialYear(null);
		stageComponentMpgForm.setNoOfVillage(null);
		stageComponentMpgForm.setStage(null);
		stageComponentMpgForm.setStageCompMpgPlanGrid(getStageCompDatagrid(null));
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private Datagrid getStageCompDatagrid(List<StageCompMpgPlanGrid> schemeVillageBeans) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(StageCompMpgPlanGrid.class);
		List<StageCompMpgPlanGrid> villageBeans = new ArrayList<StageCompMpgPlanGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}


	
	//getDivisionsBasedOnUserId
	public ActionForward getDivisionsBasedOnUserId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String UserID = null;
		List<String> locationId = null;
		List<LocationDivisionSubDivisonDetailsBean> LocationNameandId = null;
		List<Integer> IdLocations = null;
		UserID = request.getParameter("username_");
		
		if(MisUtility.ifEmpty(UserID)){
			 locationId = stageComponentMpgDao.getAllLocationIds(UserID);
		}
		IdLocations = new ArrayList<Integer>();

		for (int i = 0; i < locationId.size(); i++) {
			IdLocations.add(Integer.parseInt(locationId.get(i)));
		}
				if(!MisUtility.ifEmpty(IdLocations)){
						LocationNameandId = stageComponentMpgDao.getLocationNameandId(IdLocations);
		}
				StringBuffer buffer = new StringBuffer();
		try {
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Location ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(LocationNameandId)) {
				for (LocationDivisionSubDivisonDetailsBean bean:LocationNameandId) {
					buffer.append("<option value=\"").append(bean.getDivisonSubDivisonDetailsId()).append("\">");
					buffer.append(bean.getDivisonSubDivisonDetailsName());
			buffer.append("</option>");
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

	
	public ActionForward find(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		StageComponentMpgForm stageComponentMpgForm = null;
		List<StageComponetDto> villageAndCategory = new ArrayList<StageComponetDto>();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			stageComponentMpgForm = (StageComponentMpgForm) actionForm;

			if (MisUtility.ifEmpty(stageComponentMpgForm)) {
				System.out.println("inside sa-ve----------------------------"+stageComponentMpgForm.toString());
				
				//Get Data From sdu_div_finyearly_plan_outline
				villageAndCategory = stageComponentMpgDao.getVillagesAndCategory(stageComponentMpgForm);
				
				stageComponentMpgForm.setStageComponetDtos(villageAndCategory);
				
				System.out.println(stageComponentMpgForm.getFinancialYear());
				System.out.println(stageComponentMpgForm.getDivision());
				request.setAttribute("stageComponetDtos", stageComponentMpgForm);
				request.setAttribute("fin_",stageComponentMpgForm.getFinancialYear());
				request.setAttribute("div_",stageComponentMpgForm.getDivision());
				
				
				//status = yearPlanInspectionBo.save(yearlyPlanInspectionForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			/*if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Added Plan:" + "\t" + stageComponentMpgForm.getFinancialYear());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}*/
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}
	
	
	//getStages
	public ActionForward getStages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		///String UserID = null;
		//List<String> locationId = null;
		List<StageDetailBean> stageDetails = null;
		//List<Integer> IdLocations = null;
		//UserID = request.getParameter("username_");
		
		stageDetails = new ArrayList<StageDetailBean>();
		stageDetails = stageComponentMpgDao.getStageNameAndId();
		
		

		
				StringBuffer buffer = new StringBuffer();
		try {
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Stages ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(stageDetails)) {
				for (StageDetailBean bean:stageDetails) {
					buffer.append("<option value=\"").append(bean.getStageId()).append("\">");
					buffer.append(bean.getStageName());
			buffer.append("</option>");
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
	
	
	//getComponents
	public ActionForward getComponents(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String FinancialYear = null;
		String Division = null;
		//List<String> locationId = null;
		List<DivisionWiseSummaryBean> stageDetails = null;
		//List<Integer> IdLocations = null;
		FinancialYear = request.getParameter("year");
		Division = request.getParameter("div");
		
		stageDetails = new ArrayList<DivisionWiseSummaryBean>();
		stageDetails = stageComponentMpgDao.getComponets(FinancialYear,Division);
		
				StringBuffer buffer = new StringBuffer();
		try {
			
			buffer.append("<option value='' selected>");
			buffer.append("Select Category ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(stageDetails)) {
				for (DivisionWiseSummaryBean bean:stageDetails) {
					buffer.append("<option value=\"").append(bean.getCategory()).append("\">");
					buffer.append(bean.getCombodetailCatName().getCmb_name());
			buffer.append("</option>");
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
