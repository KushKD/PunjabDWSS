package com.prwss.min.SDU.Struts;

import java.io.IOException;
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

import com.prwss.min.SDU.BO.TrainingDetailsEntryBo;
import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.dao.TrainingDetailsEntryDao;
import com.prwss.min.SDU.form.MaterialDistributionGrid;
import com.prwss.min.SDU.form.TrainingDetailsEntryForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TrainingDetailsEntryAction extends DispatchAction{
	
	private Logger log = Logger.getLogger(TrainingDetailsEntryAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private TrainingDetailsEntryDao trainingDetailsEntryDao;
	private TrainingDetailsEntryBo trainingDetailsEntryBo;  
	
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
	public TrainingDetailsEntryDao getTrainingDetailsEntryDao() {
		return trainingDetailsEntryDao;
	}
	public void setTrainingDetailsEntryDao(TrainingDetailsEntryDao trainingDetailsEntryDao) {
		this.trainingDetailsEntryDao = trainingDetailsEntryDao;
	}
	public TrainingDetailsEntryBo getTrainingDetailsEntryBo() {
		return trainingDetailsEntryBo;
	}
	public void setTrainingDetailsEntryBo(TrainingDetailsEntryBo trainingDetailsEntryBo) {
		this.trainingDetailsEntryBo = trainingDetailsEntryBo;
	}
	@Override
	public String toString() {
		return "TrainingDetailsEntryAction [log=" + log + ", misAuditBean=" + misAuditBean + ", misSessionBean="
				+ misSessionBean + ", trainingDetailsEntryDao=" + trainingDetailsEntryDao + "]";
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	
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
		TrainingDetailsEntryForm trainingDetailsEntryForm = (TrainingDetailsEntryForm) form;
		refreshTrainingDetailsEntryForm(trainingDetailsEntryForm);
		this.setAttrib(request);
		System.out.println("Unspecified........TrainingDetailsEntryAction.........");

		return mapping.findForward("display");
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	
	
	private void refreshTrainingDetailsEntryForm(TrainingDetailsEntryForm trainingDetailsEntryForm) {

		trainingDetailsEntryForm.setActivity(null);
		trainingDetailsEntryForm.setDivision(null);
		trainingDetailsEntryForm.setExpenditure(null);
		trainingDetailsEntryForm.setFemale(null);
		trainingDetailsEntryForm.setFinancialYear(null);
		trainingDetailsEntryForm.setLvlOfTrng(null);
		trainingDetailsEntryForm.setMale(null);
		trainingDetailsEntryForm.setNameOfTrng(null);
		trainingDetailsEntryForm.setNoOfCopiesDist(null);
		trainingDetailsEntryForm.setPanchayatMembers(null);
		trainingDetailsEntryForm.setScFemale(null);
		trainingDetailsEntryForm.setScMale(null);
		trainingDetailsEntryForm.setScTotal(null);
		trainingDetailsEntryForm.setTainingDate(null);
		trainingDetailsEntryForm.setTotal(null);
		trainingDetailsEntryForm.setTrainer(null);
		trainingDetailsEntryForm.setTrainingRefNo(null);
		trainingDetailsEntryForm.setTypeOfMaterial(null);
		trainingDetailsEntryForm.setVillage(null);
		trainingDetailsEntryForm.setMaterialDistGrid(getMaterialDistGrid(null));
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public ActionForward getVillageNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getVillageNameAndId");
		String financialYr = null;
		String division = null;

		financialYr = request.getParameter("financialYear");
		division = request.getParameter("division");

		System.out.println("financial year id is----------" + financialYr);
		System.out.println("division id is----------" + division);

		List<ActivityVillageMappingBean> activityVillageMappingBeans = null;

		StringBuffer buffer = new StringBuffer();

		try {
			activityVillageMappingBeans = trainingDetailsEntryDao.getVillageNameAndId(Integer.parseInt(financialYr),
					Integer.parseInt(division));
			System.out.println(activityVillageMappingBeans.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(activityVillageMappingBeans)) {
				for (ActivityVillageMappingBean activityVillageMappingBean : activityVillageMappingBeans) {
					buffer.append("<option value=\"").append(activityVillageMappingBean.getVillageId()).append("\">");
					buffer.append(activityVillageMappingBean.getLocationDetailsBeanVillageId().getLocationName());
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
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getActivityNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getActivityNameAndId");

		String financialYr = null;
		String division = null;
		String village = null;

		List<ActivityVillageMappingDetalBean> activityNameandId = null;
		StringBuffer buffer = new StringBuffer();
		try {

			financialYr = request.getParameter("financialYear");
			division = request.getParameter("division");
			village = request.getParameter("village");

			System.out.println("financial year id is----------" + financialYr);
			System.out.println("division id is----------" + division);
			System.out.println("village id is----------" + village);

			if (MisUtility.ifEmpty(financialYr) && MisUtility.ifEmpty(division) && MisUtility.ifEmpty(village)) {
				activityNameandId = trainingDetailsEntryDao.getActivityNameandId(Integer.parseInt(financialYr),
						Integer.parseInt(division), Integer.parseInt(village));
			}
			buffer.append("<option value='' selected>");
			buffer.append("Select Activity");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(activityNameandId)) {
				for (ActivityVillageMappingDetalBean bean : activityNameandId) {
					buffer.append("<option value=\"").append(bean.getActivityId()).append("\">");
					buffer.append(bean.getActivityAttributeName().getAttributeName());
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
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public ActionForward getDivisionsBasedOnUserId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String UserID = null;
		List<String> locationId = null;
		List<LocationDivisionSubDivisonDetailsBean> LocationNameandId = null;
		List<Integer> IdLocations = null;
		UserID = request.getParameter("username_");

		if (MisUtility.ifEmpty(UserID)) {
			locationId = trainingDetailsEntryDao.getAllLocationIds(UserID);
		}
		IdLocations = new ArrayList<Integer>();

		for (int i = 0; i < locationId.size(); i++) {
			IdLocations.add(Integer.parseInt(locationId.get(i)));
		}
		if (!MisUtility.ifEmpty(IdLocations)) {
			LocationNameandId = trainingDetailsEntryDao.getLocationNameandId(IdLocations);
		}
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Location ");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(LocationNameandId)) {
				for (LocationDivisionSubDivisonDetailsBean bean : LocationNameandId) {
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
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------

	
	private Datagrid getMaterialDistGrid(List<MaterialDistributionGrid> materialDistributionGrid) {

		Datagrid villageDatagrid = null;
		villageDatagrid = Datagrid.getInstance();
		villageDatagrid.setDataClass(MaterialDistributionGrid.class);
		List<MaterialDistributionGrid> villageBeans = new ArrayList<MaterialDistributionGrid>();
		villageDatagrid.setData(villageBeans);
		return villageDatagrid;
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException{
		
		boolean status = false;
		TrainingDetailsEntryForm trainingDetailsEntryForm = null;
		
		try {
			System.out.println("inside save TrainingDetailsEntrySave --------------------");
			
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			trainingDetailsEntryForm = (TrainingDetailsEntryForm) form;
			/*List<ProgressofWorkBean> progressofWorkdetails=progressofWorkDao.findSurveyCollection(progressofWorkForm);
			if(!MisUtility.ifEmpty(surveyMasterdetails)){
				throw new MISException(MISExceptionCodes.MIS002,"Entry Already Exist for ("+progressofWorkForm.getSurveyName()+")");				
			}*/
			if (MisUtility.ifEmpty(trainingDetailsEntryForm)) {
							
							if (MisUtility.ifEmpty(trainingDetailsEntryForm.getUpldPhoto().getFileSize())){
								System.out.println(" --------------------" + trainingDetailsEntryForm.getUpldPhoto().getFileName());
							if(!(trainingDetailsEntryForm.getUpldPhoto().getFileName().endsWith(".jpeg")|| trainingDetailsEntryForm.getUpldPhoto().getFileName().endsWith(".png")||trainingDetailsEntryForm.getUpldPhoto().getFileName().endsWith(".jpg") || trainingDetailsEntryForm.getUpldPhoto().getFileName().endsWith(".JPEG")|| trainingDetailsEntryForm.getUpldPhoto().getFileName().endsWith(".PNG")||trainingDetailsEntryForm.getUpldPhoto().getFileName().endsWith(".JPG"))){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File Format should be of jpeg/jpg/png");
							}
							if(trainingDetailsEntryForm.getUpldPhoto().getFileSize()>512000){
								throw new MISException(MISExceptionCodes.MIS003,"Photograph File size should not be greater than 500 KB");
							}
							}
			}
			
				if (MisUtility.ifEmpty(trainingDetailsEntryForm)) {
					status = trainingDetailsEntryBo.save(trainingDetailsEntryForm,Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
				}
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Record Saved Successfully.");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshTrainingDetailsEntryForm(trainingDetailsEntryForm);
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
	} catch (MISException e) {
		System.out.println("e.getCode()------------->"+e.getCode());
		if (MISExceptionCodes.MIS003.equals(e.getCode())) {	
		log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	}
	return mapping.findForward("display");
	}


	

}
