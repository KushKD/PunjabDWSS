package com.prwss.min.RTI.Struts;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
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
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.bo.SubmitRtiBo;
import com.prwss.min.RTI.dao.SubmitRtiDao;
import com.prwss.min.RTI.form.SubmitRtiForm;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.struts.BenifecieryEntryAction;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

public class SubmitRtiAction extends DispatchAction {
	
	private Logger log = Logger.getLogger(BenifecieryEntryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private SubmitRtiBo submitRtiBo;
	private SubmitRtiDao submitRtiDao;
	
	
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

	public SubmitRtiBo getSubmitRtiBo() {
		return submitRtiBo;
	}

	public void setSubmitRtiBo(SubmitRtiBo submitRtiBo) {
		this.submitRtiBo = submitRtiBo;
	}

	public SubmitRtiDao getSubmitRtiDao() {
		return submitRtiDao;
	}

	public void setSubmitRtiDao(SubmitRtiDao submitRtiDao) {
		this.submitRtiDao = submitRtiDao;
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

		System.out.println("Unspecified........RTI Submit");  
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		/*request.setAttribute("d__ky", "locationName");*/
		/* request.setAttribute("d__auto", "locationName"); */
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException,IOException,FileUploadException  {

		System.out.println("----------------inside RTI save  -----2222---------------" );
		boolean status = false;
		SubmitRtiForm RtiForm = (SubmitRtiForm) form;
		//this.setAttrib(request);

		System.out.println("----------------inside RTI save  --------------------" + RtiForm );
		
		FileOutputStream outputStream = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			setAttrib(request);
			if (MisUtility.ifEmpty(RtiForm)) {
				
				if (MisUtility.ifEmpty(RtiForm.getApplicationFile().getFileSize())){
				if(!(RtiForm.getApplicationFile().getContentType().equalsIgnoreCase("application/pdf"))){
					throw new MISException(MISExceptionCodes.MIS003,"RTI Application File Format should be of pdf");
				}
				if(RtiForm.getApplicationFile().getFileSize()>512000){
					throw new MISException(MISExceptionCodes.MIS003,"RTI Application File size should not be greater than 500 KB");
				}
				}
				if (MisUtility.ifEmpty(RtiForm.getDocumentsFile().getFileSize())){
				if(!(RtiForm.getDocumentsFile().getContentType().equalsIgnoreCase("application/pdf"))){
					throw new MISException(MISExceptionCodes.MIS003,"Supporting Documents File Format should be of pdf");
				}
				if(RtiForm.getDocumentsFile().getFileSize()>512000){
					throw new MISException(MISExceptionCodes.MIS003,"Supporting Documents File size should not be greater than 500 KB");
				}
				}
			
					RtiForm.setCreatedByUser(misSessionBean.getEnteredBy());
					RtiForm.setUserID(misSessionBean.getEnteredBy());
					status = submitRtiBo.saveRtiDetails(RtiForm);
				}
			
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Succefully saved RTI Application.");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				refreshRtiForm(RtiForm);
				
			} else {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Form Failed to Save.");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				//refreshBeneficiaryForm(beneficiaryForm);
			}
		} catch (MISException e) {
			request.setAttribute("level2", "true");
/*
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}*/
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

		System.out.println("----------------Exit RTI  save  --------------------");
		return mapping.findForward("display");

	}

	private void refreshRtiForm(SubmitRtiForm rtiForm) {
		
		rtiForm.setRtiType(null);
		rtiForm.setOffice(null);
		rtiForm.setZone(null);
		rtiForm.setCircle(null);
		rtiForm.setDistrict(null);
		rtiForm.setDivision(null);
		rtiForm.setSubdivision(null);
		rtiForm.setWings(null);
		rtiForm.setPio(null);
		rtiForm.setName(null);
		rtiForm.setGender(null);
		rtiForm.setLandline(null);
		rtiForm.setMobile(null);
		rtiForm.setDistricta(null);
		rtiForm.setBlock(null);
		rtiForm.setVillageId(null);
		rtiForm.setEmail(null);
		rtiForm.setAddress(null);
		rtiForm.setLiterate(null);
		rtiForm.setPoverty(null);
		rtiForm.setShortname(null);
		rtiForm.setDetails(null);
		rtiForm.setDatePayment(null);
		rtiForm.setAmount(null);
		rtiForm.setRemarks(null);
		rtiForm.setNumber(null);
		rtiForm.setPaymentVia(null);
		rtiForm.setApplicationFile(null);
		rtiForm.setDocumentsFile(null);
		rtiForm.setResponseFile(null);
		rtiForm.setResponseName(null);
		rtiForm.setResponseRemarks(null);
	}
	
	
	public ActionForward populateRtiData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		try{
			List<UpdateRTIDto> rtiFormLst = null;
			
		int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
		String clickedColumnDir = request.getParameter("sSortDir_0");
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		Integer pageNumber = 0;
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (iDisplayStart / pageDisplayLength) + 1;
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");
		// Fetch Page display length

		System.out.println("clickedColumn-------------->" + clickedColumn + "-----------clickedColumnDir-------------"
				+ clickedColumnDir + "-iDisplayStart----------" + iDisplayStart + "pageNumber------------"
				+ pageNumber+"searchParameter-----------"+searchParameter+"pageDisplayLength-------------"+pageDisplayLength);
		
		rtiFormLst = submitRtiBo.getRtiByPagination(searchParameter, clickedColumn, clickedColumnDir);

		
		System.out.println("Update RTI ========================================"+ rtiFormLst.size());
		
		LocationJsonObject<UpdateRTIDto> locationJson = new LocationJsonObject<UpdateRTIDto>();
		if (MisUtility.ifEmpty(rtiFormLst)) {
			locationJson.setAaData(new ArrayList<UpdateRTIDto>());
		}
		if (!MisUtility.ifEmpty(rtiFormLst)) {
			locationJson.setiTotalDisplayRecords(rtiFormLst.size());
			locationJson.setiTotalRecords(rtiFormLst.size());
			 locationJson.setAaData(rtiFormLst);
		}

		//Pagination not working
		/*if (!MisUtility.ifEmpty(rtiBean)) {
			rtiBean = assignRtiBo.getListBasedOnPageNumber(rtiBean, pageDisplayLength, pageNumber, iDisplayStart); 
			locationJson.setAaData(rtiBean);
		}*/

		System.out.println("locationjson------------" + locationJson.toString());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(locationJson);
		System.out.println(json2);
		PrintWriter out = MisUtility.getPrintWriter(response);
		out.print(json2);
	} catch (Exception e) {
		log.debug(e.getMessage());

	}
	return null;
}
	
	
	public ActionForward getRtiDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<UpdateRTIDto> updateRTIDto = null;
		try {
			String rtiID = request.getParameter("Id");

			if (MisUtility.ifEmpty(rtiID)) {
				updateRTIDto = submitRtiDao.getRtiDetails(rtiID);
				/*if (!MisUtility.ifEmpty(updateRTIDto)) {
					updateRTIDto = getRtiDetailsDto(updateRTIDto);
				}*/
				//request.getSession().setAttribute("beneficiaryEntryBeans", hallofFameDetailsDto);
				System.out.println("-----1-------"+updateRTIDto.get(0));
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(updateRTIDto.get(0));
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
				
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}
	
	
	
	public ActionForward downloadApplicationFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<UpdateRTIDto> updateRTIDto = null;
		try {
			System.out.println("id------------------" + request.getParameter("rtiID"));
			if (MisUtility.ifEmpty(request.getParameter("rtiID"))) {
				updateRTIDto = submitRtiDao.getAttachmentData(request.getParameter("rtiID"));
				if (!MisUtility.ifEmpty(updateRTIDto)) {

					for (UpdateRTIDto dto : updateRTIDto) {
						byte[] attachment = dto.getApplicationFile();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + dto.getApplicationFileName());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(attachment.length);

						InputStream in = new ByteArrayInputStream(attachment);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[attachment.length];
						while (in.read(outputByte, 0, attachment.length) != -1) {
							out.write(outputByte, 0, attachment.length);
						}
						in.close();
						out.flush();
						out.close();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward downloadDocumentsFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<UpdateRTIDto> updateRTIDto = null;
		try {
			System.out.println("id------------------" + request.getParameter("rtiID"));
			if (MisUtility.ifEmpty(request.getParameter("rtiID"))) {
				updateRTIDto = submitRtiDao.getAttachmentData(request.getParameter("rtiID"));
				if (!MisUtility.ifEmpty(updateRTIDto)) {

					for (UpdateRTIDto dto : updateRTIDto) {
						byte[] attachment = dto.getDocumentsFile();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + dto.getDocumentsFileName());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(attachment.length);

						InputStream in = new ByteArrayInputStream(attachment);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[attachment.length];
						while (in.read(outputByte, 0, attachment.length) != -1) {
							out.write(outputByte, 0, attachment.length);
						}
						in.close();
						out.flush();
						out.close();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	
	//saveRTIAssignDetails
		public ActionForward saveRTIUpdateDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception, IOException {
			System.out.println("---------------------inside update-----------------------------");
			boolean status = false;
			SubmitRtiForm RtiForm = (SubmitRtiForm) form;
			FileOutputStream outputStream = null;
			try {
				if (request.getSession().getAttribute("misSessionBean") != null) {
					{
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					}
				} else {
					return mapping.findForward("login");
				}
				setAttrib(request);
				
				String responseRemarks = request.getParameter("responseRemarks");
				String responseFile = request.getParameter("response");
				String rtiID = request.getParameter("rtiID");

				System.out.println("responseRemarks" + responseRemarks);
				System.out.println("response" + responseFile);
				System.out.println("rtiID" + rtiID);
				
				if (MisUtility.ifEmpty(RtiForm)) {
					
					if (MisUtility.ifEmpty(RtiForm.getResponseFile().getFileSize())){
					if(!(RtiForm.getResponseFile().getContentType().equalsIgnoreCase("application/pdf"))){
						throw new MISException(MISExceptionCodes.MIS003,"RTI Application File Format should be of pdf");
					}
					if(RtiForm.getResponseFile().getFileSize()>512000){
						throw new MISException(MISExceptionCodes.MIS003,"RTI Application File size should not be greater than 500 KB");
					}
					}
					
						status = submitRtiBo.saveRTIUpdateDetails(RtiForm);
					}
				
				if (status) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.save",
							"Respose Saved Successfully.");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					
					refreshRtiForm(RtiForm);
					
				} else {

					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save", "Form Failed to Save.");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					//refreshBeneficiaryForm(beneficiaryForm);
				}
			}catch (MISException e) {
					request.setAttribute("level2", "true");
					
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

							System.out.println("----------------Exit RTI  save  --------------------");
							return mapping.findForward("display");
			}
}

	

