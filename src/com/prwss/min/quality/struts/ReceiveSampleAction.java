/**
 * 
 */
package com.prwss.min.quality.struts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SchemeMapping;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.min.bean.VillageSchemeMappingBean;
import com.prwss.min.dao.ReceivingSampleDao;
import com.prwss.min.quality.ReceiveSampleBo;
import com.prwss.min.quality.ReceiveSampleForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

/**
 * @author bhsingh
 *
 */
public class ReceiveSampleAction extends DispatchAction {

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ReceivingSampleDao receiveSampleDao;
	private LocationDao locationDao;
	private ReceiveSampleBo<ReceiveSampleForm> receiveSampleBo;

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * @return the receiveSampleBo
	 */
	public ReceiveSampleBo<ReceiveSampleForm> getReceiveSampleBo() {
		return receiveSampleBo;
	}

	/**
	 * @param receiveSampleBo
	 *            the receiveSampleBo to set
	 */
	public void setReceiveSampleBo(ReceiveSampleBo<ReceiveSampleForm> receiveSampleBo) {
		this.receiveSampleBo = receiveSampleBo;
	}

	/**
	 * @return the receiveSampleDao
	 */
	public ReceivingSampleDao getReceiveSampleDao() {
		return receiveSampleDao;
	}

	/**
	 * @param receiveSampleDao
	 *            the receiveSampleDao to set
	 */
	public void setReceiveSampleDao(ReceivingSampleDao receiveSampleDao) {
		this.receiveSampleDao = receiveSampleDao;
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
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		ReceiveSampleForm receiveSampleForm = (ReceiveSampleForm) form;
		System.out.println("Receive Sample Form ############"+receiveSampleForm.toString());

		try {

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			receiveSampleForm.setCreatedBy(misSessionBean.getEnteredBy());
			if (MisUtility.ifEmpty(receiveSampleForm.getSampleNumber())) {

				List<ReceiveSampleBean> locationDetailsBeans = receiveSampleDao.getSampleCollection(receiveSampleForm);
				if (!MisUtility.ifEmpty(locationDetailsBeans)) {
					throw new MISException(MISExceptionCodes.MIS012,
							"Entry Already Exist for Sample(" + receiveSampleForm.getSampleNumber() + ")");
				}

				if (MisUtility.ifEmpty(receiveSampleForm)) {
					status = receiveSampleBo.saveSample(receiveSampleForm);
					System.out.println("status------->" + status);
				}
				if (status) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("success.save",
							"Successfully saved Sample" + "\t" + receiveSampleForm.getSampleNumber());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);

				} else {
					request.setAttribute("level2", "true");

					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save",
							"Internal Error" + receiveSampleForm.getSchemeName());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshMasterLocationForm(receiveSampleForm);
		return mapping.findForward("display");

	}

	private String downloadQrCode(HttpServletRequest request, HttpServletResponse response,
			ReceiveSampleForm receiveSampleForm) throws IOException {
		ServletOutputStream out = null;
		InputStream in = null;
		
		try {
			System.out.println(receiveSampleForm.getSampleNumber());
			String qrCodeData = receiveSampleForm.getSampleNumber();
		//	String filePath = "/images/barCode.png";
					
					//request.getServletContext().getRealPath("/images/barCode.png");
			String filePathPDF = "barCode.pdf";
					
					//request.getServletContext().getRealPath("/images/barCode.pdf");
			String charset = "UTF-8"; // or "ISO-8859-1"
			Map hintMap = new HashMap();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			//createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
			
			generatePdf(filePathPDF,receiveSampleForm);

			response.reset();
			//response.setContentType("image/png");
			//response.setHeader("Content-Disposition", "attachment; filename=" + "barCodess.png");  //pdf not working
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=" + "barCodess.pdf");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "no-store");
			response.addHeader("Cache-Control", "max-age=0");
			byte[] array = read(new File(filePathPDF));
			System.out.println("size----------------->" + array.length);
			response.setContentLength(array.length);

			in = new ByteArrayInputStream(array);
			out = response.getOutputStream();
			byte[] outputByte = new byte[array.length];
			while (in.read(outputByte, 0, array.length) != -1) {
				out.write(outputByte, 0, array.length);
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				out.close();
			}
		}

		return null;
	}

	private void generatePdf(String filePathPDF, ReceiveSampleForm receiveSampleForm) throws FileNotFoundException, DocumentException {
		
		
		
		//Step - 1 :Create Document object that will hold the code
		        Document qr_code = new Document(new Rectangle(336, 132)); 
		        qr_code.setMargins(20,20,4,4); 
		        // Step-2: Create PdfWriter object for the document
		         PdfWriter writer = PdfWriter.getInstance(qr_code, new FileOutputStream(filePathPDF));
		        // Step -3: Open document for editing
		         qr_code.open(); 
		         
		        
		      
		         PdfPTable table = new PdfPTable(5);
		         table.getDefaultCell().setBorder(0);   
		         table.setWidthPercentage(100);
		         
		         
		         //Row 1
		         table.addCell(getHeader("Sample Number: "+receiveSampleForm.getSampleNumber(), PdfPCell.ALIGN_CENTER));
		         table.addCell(getQRCodeImage(receiveSampleForm.getSampleNumber(),PdfPCell.ALIGN_JUSTIFIED));
		      
		        
		         
		         //Row 2
		         table.addCell(getCell("Scheme: " , PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell(receiveSampleForm.getSchemeName() , PdfPCell.ALIGN_LEFT));
		        
		         
		         //Row 3
		         table.addCell(getCell("Habitation: ", PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell(receiveSampleForm.getHabitation() , PdfPCell.ALIGN_LEFT));
		       
		         
		         //Row 4
		         table.addCell(getCell("Block: ", PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell(receiveSampleForm.getBlock() , PdfPCell.ALIGN_LEFT));
		         
		         //Row 5
		         table.addCell(getCell("District: ", PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell(receiveSampleForm.getDistrict(), PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell("\t" , PdfPCell.ALIGN_LEFT));
		         
		         //Row 6
		         table.addCell(getCell("Type Water Source: ", PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell(receiveSampleForm.getWaterSource(), PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell("\t" , PdfPCell.ALIGN_LEFT));
		         
		         
		         //Row 7
		         table.addCell(getCell("Collected Date: ", PdfPCell.LEFT));
		         table.addCell(getCell(receiveSampleForm.getCollDate() , PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell("\t" , PdfPCell.ALIGN_LEFT));
		         
		       //Row 7
		         table.addCell(getCell("Collected By: ", PdfPCell.LEFT));
		         table.addCell(getCell(receiveSampleForm.getCollectedBy() , PdfPCell.ALIGN_LEFT));
		         table.addCell(getCell(" " , PdfPCell.ALIGN_LEFT));
		         
		        //Add Table To Document
		         qr_code.add(table); 
		            
		        //Step-8: Close the PDF document
		         qr_code.close();
		
	}
	
	
	//getQRCodeImage
	public PdfPCell getQRCodeImage(String QRCode, int alignment) throws BadElementException {
		
		  BarcodeQRCode my_code = new BarcodeQRCode(QRCode, 1, 1, null);  
	
	         //Step-6: Get Image corresponding to the input string
	         Image qrcodeImage = my_code.getImage(); 
	         qrcodeImage.setAbsolutePosition(0, 0); 
	         qrcodeImage.scaleToFit(59, 200);   
		    
		 PdfPCell cell = new PdfPCell(qrcodeImage);
		    cell.setHorizontalAlignment(alignment);
		    cell.setRowspan(4);
		    cell.setColspan(1); 
		    return cell;
	
	}

	public PdfPCell getHeader(String string, int alignment) {
		
		 //Define the Font
        Font fontHeader=new Font(); /* Define a new Font Object */ 
        fontHeader.setStyle("bold"); 
        fontHeader.setColor(0,0,0);
        fontHeader.setSize(10);
		
		    PdfPCell cell = new PdfPCell(new Paragraph(string,fontHeader));
		    cell.setHorizontalAlignment(alignment);
		    cell.setBorder(PdfPCell.ALIGN_JUSTIFIED);
		    cell.setColspan(4); 
		    cell.setBorder(Rectangle.NO_BORDER);
		    return cell;
		
		
	}
	
	public PdfPCell getCell(String string, int alignment) {
		
		 //Define the Font
       Font fontHeader=new Font(); /* Define a new Font Object */ 
       fontHeader.setColor(0,0,0);
       fontHeader.setSize(8);
		
		    PdfPCell cell = new PdfPCell(new Paragraph(string,fontHeader));
		    cell.setHorizontalAlignment(alignment); 
		    cell.setColspan(2);  
		    cell.setBorder(Rectangle.NO_BORDER);
		    return cell;
		
		
	}

	public byte[] read(File file) throws IOException {
		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			byte[] buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
			}
		} finally {
			try {
				if (ous != null)
					ous.close();
			} catch (IOException e) {
			}

			try {
				if (ios != null)
					ios.close();
			} catch (IOException e) {
			}
		}
		return ous.toByteArray();
	}

	public void createQRCode(String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeheight,
			int qrCodewidth) throws WriterException, IOException {
		try {
			
			System.out.println(filePath);
			BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
			MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
					new File(filePath));
		} catch (Exception e) {
			log.debug(e.getMessage());
		}

	}

	public ActionForward generateQrCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		ReceiveSampleForm receiveSampleForm = (ReceiveSampleForm) form;
		try {
		this.setAttrib(request);
		boolean status = false;
		
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			/*
			receiveSampleForm.setBlock(request.getSession().getAttribute("block").toString().trim()); 
			receiveSampleForm.setDistrict(request.getSession().getAttribute("district").toString().trim()); 
			receiveSampleForm.setVillageId(request.getSession().getAttribute("habitation").toString().trim()); 
			
			//schemeName , waterSource, collectedBy
			receiveSampleForm.setSchemeName(request.getSession().getAttribute("schemeName").toString().trim()); 
			receiveSampleForm.setWaterSourceName(request.getSession().getAttribute("waterSource").toString().trim()); 
		receiveSampleForm.setCollectedBy(request.getSession().getAttribute("collectedBy").toString().trim()); 
		
		*/
			
			System.out.println(receiveSampleForm.toString());
			downloadQrCode(request, response, receiveSampleForm);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshMasterLocationForm(receiveSampleForm);
		return null;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		ReceiveSampleForm receiveSampleForm = (ReceiveSampleForm) form;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			receiveSampleForm.setCreatedBy(misSessionBean.getEnteredBy());

			if (MisUtility.ifEmpty(receiveSampleForm)) {
				status = receiveSampleBo.updateSample(receiveSampleForm);
			}
			if (status) {

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"SuccessFully Update Sample" + "\t" + receiveSampleForm.getSampleNumber());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

				/*
				 * refreshMasterLocationForm(locationMasterForm);
				 */
			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshMasterLocationForm(receiveSampleForm);
		return mapping.findForward("display");

	}

	public StringBuffer fetchSampleNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		StringBuffer buffer = new StringBuffer();
		List<LabMasterBean> sampleCodeLabMappings = null;
		String generatedSequence = null;
		try {
			String labId = request.getParameter("labId");
			if (MisUtility.ifEmpty(labId)) {
				sampleCodeLabMappings = receiveSampleDao.findSampleCode(labId);
				if (!MisUtility.ifEmpty(sampleCodeLabMappings)) {
					String labName = sampleCodeLabMappings.get(0).getLabIdName();
					int fiscalYear = MisUtility.getFiscalYear();
					fiscalYear = fiscalYear % 100;
					buffer.append(labName);
					buffer.append("/");
					buffer.append(fiscalYear);
					buffer.append(fiscalYear + 1);
					buffer.append("/");
					if (MisUtility
							.ifEmpty(sampleCodeLabMappings.get(0).getSampleCodeLabMapping().getCurrentSequence())) {
						int currentSequence = sampleCodeLabMappings.get(0).getSampleCodeLabMapping()
								.getCurrentSequence();
						if (currentSequence == 0) {
							currentSequence = 0001;
							generatedSequence = "00001";
						} else {
							if (currentSequence > 0 && currentSequence < 10) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 9) {
									generatedSequence = "000" + currentSequence;
								} else {
									generatedSequence = "0000" + currentSequence;
								}

							} else if (currentSequence > 10 && currentSequence < 99) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 99) {
									generatedSequence = "00" + currentSequence;
								} else {
									generatedSequence = "000" + currentSequence;
								}
							} else if (currentSequence > 99 && currentSequence < 1000) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 999) {
									generatedSequence = "0" + currentSequence;
								} else {
									generatedSequence = "00" + currentSequence;
								}
							} else if (currentSequence > 999 && currentSequence < 10000) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 9999) {
									generatedSequence = String.valueOf(currentSequence);
								} else {
									generatedSequence = "0" + currentSequence;
								}
							} else {
								currentSequence = currentSequence + 1;
								generatedSequence = String.valueOf(currentSequence);
							}

						}
					} else {
						generatedSequence = "00001";
					}
					buffer.append(generatedSequence);
					// request.getSession().setAttribute("runningNumber",buffer.toString());

					PrintWriter out = response.getWriter();
					if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
						out.print(buffer.toString());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		/* request.setAttribute("d__auto", "locationName"); */
	}

	private void refreshMasterLocationForm(ReceiveSampleForm receiveSampleForm) {

		receiveSampleForm.setBlock(null);
		receiveSampleForm.setCollDate(null);
		receiveSampleForm.setCollectedBy(null);
		receiveSampleForm.setCollectionType(null);
		receiveSampleForm.setDesignation(null);
		receiveSampleForm.setDistrict(null);
		receiveSampleForm.setEmail(null);
		receiveSampleForm.setEmailDwss(null);
		receiveSampleForm.setMobileNo(null);
		receiveSampleForm.setMobileNoDwss(null);
		receiveSampleForm.setGramPanchayat(null);
		receiveSampleForm.setHabitation(null);
		receiveSampleForm.setLab(null);
		receiveSampleForm.setSchemeName(null);
		receiveSampleForm.setSampleNumber(null);
		receiveSampleForm.setWaterSource(null);
		receiveSampleForm.setVillageId(null);
		receiveSampleForm.setTestType(null);
		receiveSampleForm.setCollDate(null);
		receiveSampleForm.setLandMark(null);
		//receiveSampleForm.setSampleWaterWorks(null);
		receiveSampleForm.setLabName(null);
		receiveSampleForm.setWaterSourceName(null);
		receiveSampleForm.setCity(null);
		receiveSampleForm.setDepth(null);
		receiveSampleForm.setBottleType(null);
		receiveSampleForm.setLetterRefNum(null);
		receiveSampleForm.setQuantity(null);
		receiveSampleForm.setSampleotherdetails(null);
		receiveSampleForm.setRural(null);
		receiveSampleForm.setUrban(null); 

	}

	public ActionForward fetchEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchParentLocation");

		StringBuffer buffer = new StringBuffer();
		System.out.println("request.getParameter---collectionType----------" + request.getParameter("designation"));
		try {

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

			if (MisUtility.ifEmpty(request.getParameter("designation"))) {
				List<EmployeeBean> empList = receiveSampleDao.getEmployee(statusList,
						request.getParameter("designation"));
				if (!MisUtility.ifEmpty(empList)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select Employee");
					buffer.append("</option>");
					for (EmployeeBean bean : empList) {
						buffer.append("<option value=\"").append(bean.getEmployeeId()).append("\">");
						buffer.append(bean.getEmployeeName());
						buffer.append("</option>");
					}
				}
			}

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
			/*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	public ActionForward fetchEmpDesignation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchParentLocation");

		StringBuffer buffer = new StringBuffer();
		String designationId;
		List<EmployeeDesignationBean> employeeBean = null;
		System.out.println("request.getParameter---collectionType----------" + request.getParameter("collectionType"));
		try {

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

			if (MisUtility.ifEmpty(request.getParameter("collectionType"))) {
				employeeBean = receiveSampleDao.getEmployeeDesig(statusList);
				if (!MisUtility.ifEmpty(employeeBean)) {

					buffer.append("<option value='' selected>");
					buffer.append("Select Designation");

					buffer.append("</option>");
					for (EmployeeDesignationBean bean : employeeBean) {
						System.out.println(bean.getDesignationId());
						buffer.append("<option value=\"").append(bean.getDesignationId()).append("\">");
						buffer.append(bean.getDesignationName());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
			/*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return null;
	}

	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchScheme");
		List<PMSSchemeDetailsBean> pMSSchemeDetailsBeans = null;
		List<VillageDetailsBean> villageDetailsBean = null;
		StringBuffer buffer = new StringBuffer();
		List<Integer> schemeId = new ArrayList<Integer>();
		try {
			if (MisUtility.ifEmpty(request.getParameter("villageId"))) {
				villageDetailsBean = receiveSampleDao.findSchemeFromVillage(request.getParameter("villageId"));

				if (!MisUtility.ifEmpty(villageDetailsBean)) {
					for (VillageDetailsBean bean : villageDetailsBean) {
						Set<VillageSchemeMappingBean> villageSchemeMappingBeans = bean.getVillageSchemeMappingBean();
						for (VillageSchemeMappingBean bean1 : villageSchemeMappingBeans) {
							schemeId.add(bean1.getSchemeId());
						}
					}
					pMSSchemeDetailsBeans = receiveSampleDao.findSchemeName(schemeId);
				}
				System.out.println(pMSSchemeDetailsBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select Scheme");
				buffer.append("</option>");
				for (PMSSchemeDetailsBean pMSSchemeDetailsBean : pMSSchemeDetailsBeans) {
					buffer.append("<option value=\"")
							.append(pMSSchemeDetailsBean.getScheme_id()).append("\">");
					buffer.append(pMSSchemeDetailsBean.getSchemeName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} /*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return null;
	}

	/*public ActionForward fetchHabitation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchHabitation");
		List<VillageDetailsBean> performaMaserBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("villageId"))) {
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

	public ActionForward fetchGramPanchayat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchGramPanchayat");
		List<VillageDetailsBean> performaMaserBeans = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("villageId"))) {
				performaMaserBeans = receiveSampleDao.findHabitationFromVillage(request.getParameter("villageId"));
				System.out.println(performaMaserBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select GramPanchayat");
				buffer.append("</option>");
				for (VillageDetailsBean schemeHeaderBean2 : performaMaserBeans) {
					buffer.append("<option value=\"").append(schemeHeaderBean2.getGram_panchayat()).append("\">");
					buffer.append(schemeHeaderBean2.getGram_panchayat());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} /*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	public ActionForward fetchWaterSource(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchWaterSource");
		List<SchemeMapping> schemeMappings = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("schemeName"))) {
				schemeMappings = receiveSampleDao.fetchWaterSource(request.getParameter("schemeName"));
				System.out.println(schemeMappings.toString());
				buffer.append("<option value='NA' selected>");
				buffer.append("Select Water Source");
				buffer.append("</option>");
				for (SchemeMapping schemeHeaderBean2 : schemeMappings) {
					buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeWSId()).append("\">");
					buffer.append(schemeHeaderBean2.getWsName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} 
		catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	public ActionForward fetchMobileNoAndEmailId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchMobileNoAndEmailId--------------");
		List<EmployeeBean> employeeName = null;
		StringBuffer buffer = new StringBuffer();

		try {
			if (MisUtility.ifEmpty(request.getParameter("collectedBy"))) {
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				employeeName = receiveSampleDao.getEmployeeDetails(request.getParameter("collectedBy"), statusList);
				System.out.println(employeeName.toString());

				for (EmployeeBean schemeHeaderBean2 : employeeName) {
					buffer.append(schemeHeaderBean2.getOfficialEmailId());
					buffer.append(",");
					buffer.append(schemeHeaderBean2.getMobilePhone());
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} /*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		catch (Exception e) {
			// e.printStackTrace();
			log.error(e);
		}

		return null;
	}

	public ActionForward populateSampleCollection(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		ServletOutputStream out = null;
		List<ReceiveSampleForm> locationTypeList = null;

		try {
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			System.out.println("Item Clicked is:-"+clickedColumn); 
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			// Fetch search parameter
			String searchParameter = request.getParameter("sSearch");
			// Fetch Page display length

			locationTypeList = receiveSampleBo.getSampleCollectionByPagination(searchParameter, clickedColumn,
					clickedColumnDir);

			LocationJsonObject<ReceiveSampleForm> locationJson = new LocationJsonObject<ReceiveSampleForm>();

			if (MisUtility.ifEmpty(locationTypeList)) {
				locationJson.setAaData(new ArrayList<ReceiveSampleForm>());
			}
			if (!MisUtility.ifEmpty(locationTypeList)) {
				locationJson.setiTotalDisplayRecords(locationTypeList.size());
				locationJson.setiTotalRecords(locationTypeList.size());
			}
			List<ReceiveSampleForm> locMasterLst = null;
			if (!MisUtility.ifEmpty(locationTypeList)) {
				locMasterLst = receiveSampleBo.getListBasedOnPageNumber(locationTypeList, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(locMasterLst);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			out = response.getOutputStream();
			out.print(json2);

		} catch (Exception e) {
			log.debug(e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}

		return null;
	}

}
