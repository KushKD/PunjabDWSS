package com.prwss.min.sanitation.struts;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.quality.struts.LocationJsonObject;
import com.prwss.min.sanitation.bean.BeneficiaryEntryDetailsDto;
import com.prwss.min.sanitation.bo.BeneficiaryBo;
import com.prwss.min.sanitation.dao.BeneficiaryDao;
import com.prwss.min.sanitation.form.BeneficiaryForm;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MisUtility;
/**
 * @author BH390738
 *
 */
public class BeneficiaryEntryViewAction extends DispatchAction {

	private Logger log = Logger.getLogger(BeneficiaryEntryViewAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private BeneficiaryBo<BeneficiaryForm> beneficiaryBo;
	private BeneficiaryDao beneficiaryDao;

	public BeneficiaryDao getBeneficiaryDao() {
		return beneficiaryDao;
	}

	public void setBeneficiaryDao(BeneficiaryDao beneficiaryDao) {
		this.beneficiaryDao = beneficiaryDao;
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

	public BeneficiaryBo<BeneficiaryForm> getBeneficiaryBo() {
		return beneficiaryBo;
	}

	public void setBeneficiaryBo(BeneficiaryBo<BeneficiaryForm> beneficiaryBo) {
		this.beneficiaryBo = beneficiaryBo;
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

		System.out.println("Unspecified........BeneficiaryEntryViewAction");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "block@district@villageId");
	}

	public ActionForward getBeneficiaryDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<BeneficiaryEntryDetailsDto> beneficiaryEntryDetailsDto = null;
		try {
			String beneficiaryId = request.getParameter("beneficiaryId");

			if (MisUtility.ifEmpty(beneficiaryId)) {
				beneficiaryEntryDetailsDto = beneficiaryDao.getBeneficiaryDetails(beneficiaryId);
				if (!MisUtility.ifEmpty(beneficiaryEntryDetailsDto)) {
					beneficiaryEntryDetailsDto = getBeneficiaryEntryDetailsDto(beneficiaryEntryDetailsDto);
				}
				//request.getSession().setAttribute("beneficiaryEntryBeans", beneficiaryEntryDetailsDto);
				System.out.println("-----1-------"+beneficiaryEntryDetailsDto.get(0));
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(beneficiaryEntryDetailsDto.get(0));
				System.out.println(json2);
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(json2);
				
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}

	private List<BeneficiaryEntryDetailsDto> getBeneficiaryEntryDetailsDto(
			List<BeneficiaryEntryDetailsDto> beneficiayList) {

		List<BeneficiaryEntryDetailsDto> beneficiayLists = null;

		try {
			if (!MisUtility.ifEmpty(beneficiayList)) {
				beneficiayLists = new ArrayList<BeneficiaryEntryDetailsDto>();
				Iterator<BeneficiaryEntryDetailsDto> iterator = beneficiayList.iterator();
				while (iterator.hasNext()) {
					BeneficiaryEntryDetailsDto beneficiaryEntry = (BeneficiaryEntryDetailsDto) iterator.next();
					String imageName = getBeneficiaryImage(beneficiaryEntry);
					beneficiaryEntry.setImage(imageName);
					beneficiaryEntry.setPhotograp(null);
					beneficiayLists.add(beneficiaryEntry);
				}
			}
		} catch (Exception e) {

		}
		return beneficiayLists;
	}

	public ActionForward downloadElectricityBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		List<BeneficiaryEntryDetailsDto> beneficiaryDto = null;
		try {
			System.out.println("id------------------" + request.getParameter("beneficiaryId"));
			if (MisUtility.ifEmpty(request.getParameter("beneficiaryId"))) {
				beneficiaryDto = beneficiaryDao.getElectricityBillData(request.getParameter("beneficiaryId"));
				if (!MisUtility.ifEmpty(beneficiaryDto)) {

					for (BeneficiaryEntryDetailsDto dto : beneficiaryDto) {
						byte[] elecConData = dto.getElecConData();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + dto.getElectBill());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(elecConData.length);

						InputStream in = new ByteArrayInputStream(elecConData);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[elecConData.length];
						while (in.read(outputByte, 0, elecConData.length) != -1) {
							out.write(outputByte, 0, elecConData.length);
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

	
	private String getBeneficiaryImage(BeneficiaryEntryDetailsDto beneficiaryEntry) {
		BufferedImage image = null;
		String b64 = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(beneficiaryEntry.getPhotograp());
			image = ImageIO.read(bis);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			bis.close();
			b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		} catch (Exception e) {

		}
		return b64;
	}

	// populateLabMaster
	public ActionForward populateBeneficiaryData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		String district = null;
		String block = null;
		String village = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			if (MisUtility.ifEmpty(request.getParameter("district"))) {
				district = request.getParameter("district");
			}
			if (MisUtility.ifEmpty(request.getParameter("block"))) {
				block = request.getParameter("block");
			}
			if (MisUtility.ifEmpty(request.getParameter("village"))) {
				village = request.getParameter("village");
			}

			ViewRegistrationsForm viewRegistrationForm = (ViewRegistrationsForm) form;
			viewRegistrationForm.setBlock(block);
			viewRegistrationForm.setDistrict(district);
			viewRegistrationForm.setVillage(village);
			viewRegistrationForm.setLoginUser(misAuditBean.getEnteredBy());

			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			String searchParameter = request.getParameter("sSearch");

			List<BeneficiaryForm> beneficiaryDto = beneficiaryBo.populateBeneficiaryDetails(viewRegistrationForm,
					searchParameter, clickedColumn, clickedColumnDir);
			LocationJsonObject<BeneficiaryForm> locationJson = new LocationJsonObject<BeneficiaryForm>();

			if (MisUtility.ifEmpty(beneficiaryDto)) {
				locationJson.setAaData(new ArrayList<BeneficiaryForm>());
			}
			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				locationJson.setiTotalDisplayRecords(beneficiaryDto.size());
				locationJson.setiTotalRecords(beneficiaryDto.size());
				// locationJson.setAaData(beneficiaryDto);
			}

			if (!MisUtility.ifEmpty(beneficiaryDto)) {
				beneficiaryDto = beneficiaryBo.getListBasedOnPageNumber(beneficiaryDto, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(beneficiaryDto);
			}

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

	/*
	 * private BufferedImage decodeToImage(String imageString) {
	 * 
	 * BufferedImage image = null; byte[] imageByte; try{ ByteArrayInputStream
	 * bis = new ByteArrayInputStream(imageByte); image = ImageIO.read(bis);
	 * bis.close(); } catch (Exception e) { e.printStackTrace(); } return image;
	 * }
	 */
}
