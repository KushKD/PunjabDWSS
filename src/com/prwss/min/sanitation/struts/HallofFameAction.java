/**
 * 
 */
package com.prwss.min.sanitation.struts;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bean.HallofFameBean;
import com.prwss.min.sanitation.bean.HallofFameDetailsDto;
import com.prwss.min.sanitation.bo.HallofFameBo;
import com.prwss.min.sanitation.form.HallofFameForm;
import com.prwss.min.sanitation.dao.HallofFameDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class HallofFameAction extends DispatchAction {

	private Logger log = Logger.getLogger(HallofFameAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private HallofFameBo hallofFameBo;
	private HallofFameDao hallofFameDao;


	public HallofFameDao getHallofFameDao() {
		return hallofFameDao;
	}

	public void setHallofFameDao(HallofFameDao hallofFameDao) {
		this.hallofFameDao = hallofFameDao;
	}

	public HallofFameBo getHallofFameBo() {
		return hallofFameBo;
	}

	public void setHallofFameBo(HallofFameBo hallofFameBo) {
		this.hallofFameBo = hallofFameBo;
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

	
	@SuppressWarnings("unchecked")
	public ActionForward populateHallofFameData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		try{
			List<HallofFameForm> hallofFameLst = null;
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
		
		hallofFameLst = hallofFameBo.getHallofFameByPagination();

		
		System.out.println("HallofFame ========================================"+ hallofFameLst.size());
		
		//if(!MisUtility.ifEmpty(labMasterLst)){
		LocationJsonObject<HallofFameForm> locationJson = new LocationJsonObject<HallofFameForm>();
		//locationJson.setDraw(Integer.parseInt(draw));
		if(!MisUtility.ifEmpty(hallofFameLst)){
			locationJson.setiTotalDisplayRecords(hallofFameLst.size());
			locationJson.setiTotalRecords(hallofFameLst.size());
		}

		List<HallofFameForm> locMasterLst = null;
		if (!MisUtility.ifEmpty(hallofFameLst)) {
			locMasterLst = hallofFameBo.getListBasedOnPageNumber(hallofFameLst, pageDisplayLength, pageNumber,iDisplayStart);
			locationJson.setAaData(hallofFameLst);
		}
		/*if ((!MisUtility.ifEmpty(hallofFameLst)) && MisUtility.ifEmpty(searchParameter)) {
			locMasterLst = hallofFameBO.getListBasedOnSearchParameter(searchParameter, hallofFameLst);
			locationJson.setAaData(hallofFameLst);
		}*/
		/*if (!MisUtility.ifEmpty(locationMasterList) && clickedColumn != 0) {
			locMasterLst = locationMasterBo.getListBasedOnColumnSorting(locationMasterList, clickedColumn,
					clickedColumnDir, locationMasterForm);
			locationJson.setAaData(locMasterLst);
		}*/
		if(MisUtility.ifEmpty(hallofFameLst)){
			locationJson.setAaData(hallofFameLst);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(locationJson);
		System.out.println(json2);
		PrintWriter out = MisUtility.getPrintWriter(response);
		out.print(json2);
		//}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public ActionForward gethallofFameDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<HallofFameDetailsDto> hallofFameDetailsDto = null;
		try {
			String activityId = request.getParameter("activityId");

			if (MisUtility.ifEmpty(activityId)) {
				hallofFameDetailsDto = hallofFameDao.gethallofFameDetails(activityId);
				if (!MisUtility.ifEmpty(hallofFameDetailsDto)) {
					hallofFameDetailsDto = getHallofFameEntryDetailsDto(hallofFameDetailsDto);
				}
				//request.getSession().setAttribute("beneficiaryEntryBeans", hallofFameDetailsDto);
				System.out.println("-----1-------"+hallofFameDetailsDto.get(0));
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(hallofFameDetailsDto.get(0));
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
				
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}
	
	
	private List<HallofFameDetailsDto> getHallofFameEntryDetailsDto(
			List<HallofFameDetailsDto> hallofFameDetailsDto) {

		List<HallofFameDetailsDto> fameLists = null;

		try {
			if (!MisUtility.ifEmpty(hallofFameDetailsDto)) {
				fameLists = new ArrayList<HallofFameDetailsDto>();
				Iterator<HallofFameDetailsDto> iterator = hallofFameDetailsDto.iterator();
				while (iterator.hasNext()) {
					HallofFameDetailsDto hallofFameEntry = (HallofFameDetailsDto) iterator.next();
					/*String imageName = getBeneficiaryImage(beneficiaryEntry);
					beneficiaryEntry.setImage(imageName);
					beneficiaryEntry.setPhotograp(null);*/
					fameLists.add(hallofFameEntry);
				}
			}
		} catch (Exception e) {

		}
		return fameLists;
	}
	
	
	
	public ActionForward downloadAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<HallofFameDetailsDto> hallofFameDetailsDto = null;
		try {
			System.out.println("id------------------" + request.getParameter("activityId"));
			if (MisUtility.ifEmpty(request.getParameter("activityId"))) {
				hallofFameDetailsDto = hallofFameDao.getAttachmentData(request.getParameter("activityId"));
				if (!MisUtility.ifEmpty(hallofFameDetailsDto)) {

					for (HallofFameDetailsDto dto : hallofFameDetailsDto) {
						byte[] attachment = dto.getAttachment();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + dto.getAttachmentName());
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
	
	
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		HallofFameForm hallofFameForm = (HallofFameForm) form;

		log.debug("----------------inside HallofFame save  --------------------" + hallofFameForm.toString());
		List<HallofFameBean> hallofFameBeans = null;
		FileOutputStream outputStream = null;
		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			
			hallofFameForm.setCreatedByUser(misSessionBean.getEnteredBy());
			
			if (MisUtility.ifEmpty(hallofFameForm)) {
				
				if(!(hallofFameForm.getAttachment().getContentType().equalsIgnoreCase("application/pdf"))){
					throw new MISException(MISExceptionCodes.MIS003,"Attachment File Format should be of pdf");
				}
				if(hallofFameForm.getAttachment().getFileSize()>512000){
					throw new MISException(MISExceptionCodes.MIS003,"Attachment File size should not be greater than 500 KB");
				}
				status=hallofFameBo.saveHallofFameDetails(hallofFameForm);
			}
			
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Succefully saved for Activity " + "\t" + hallofFameForm.getNameofActivity());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Activity" + hallofFameForm.getNameofActivity());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshHallofFameForm(hallofFameForm);
			}
		} catch (MISException e) {
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

		System.out.println("----------------Exit HallofFame save  --------------------");
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

		System.out.println("Unspecified........HallofFameAction.........");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}

	private void refreshHallofFameForm(HallofFameForm hallofFameForm) {
		hallofFameForm.setNameofActivity(null);
		hallofFameForm.setType(null);
		hallofFameForm.setLeadBy(null);
		hallofFameForm.setDescription(null);
		hallofFameForm.setAttachment(null);
		
	}
	
	
	
	

}
