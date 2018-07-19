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
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.min.dao.SampleDistributionDao;
import com.prwss.min.quality.SampleDistributionBO;
import com.prwss.min.quality.SampleDistributionForm;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.admin.dao.LocationTypeBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class SampleDistributionAction extends DispatchAction {

	private Logger log = Logger.getLogger(SampleDistributionAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private SampleDistributionBO<SampleDistributionForm> sampleDistributionBO;
	private SampleDistributionDao sampleDistributionDao;
	private LocationDao locationDao;

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * @return the sampleDistributionDao
	 */
	public SampleDistributionDao getSampleDistributionDao() {
		return sampleDistributionDao;
	}

	/**
	 * @param sampleDistributionDao
	 *            the sampleDistributionDao to set
	 */
	public void setSampleDistributionDao(SampleDistributionDao sampleDistributionDao) {
		this.sampleDistributionDao = sampleDistributionDao;
	}

	public SampleDistributionBO<SampleDistributionForm> getSampleDistributionBO() {
		return sampleDistributionBO;
	}

	public void setSampleDistributionBO(SampleDistributionBO<SampleDistributionForm> sampleDistributionBO) {
		this.sampleDistributionBO = sampleDistributionBO;
	}

	LocationMasterDao locationMasterDao;

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
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		SampleDistributionForm sampleDistributionForm = (SampleDistributionForm) form;

		System.out
				.println("inside save sampleDistributionForm --------------------" + sampleDistributionForm.toString());
		log.debug("-----------------inside save sampleDistributionForm --------------------"
				+ sampleDistributionForm.toString());

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			boolean flag = validateDate(sampleDistributionForm);
			if (flag) {
				throw new MISException(MISExceptionCodes.MIS002,
						"Distribution Date should be greater than Accept Date");
			}
			if (MisUtility.ifEmpty(sampleDistributionForm.getSamplePartNum())) {
				status = sampleDistributionBO.saveSampleDistribution(sampleDistributionForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully saved Sample Part No." + "\t" + sampleDistributionForm.getSamplePartNum());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				refreshsampleDistribution(sampleDistributionForm);

			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Sample Part Number Not Saved" + sampleDistributionForm.getSamplePartNum());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			if (MISExceptionCodes.MIS002.equalsIgnoreCase(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal Error");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}

	private boolean validateDate(SampleDistributionForm sampleDistributionForm) {

		List<ReceiveSampleBean> receiveSampleBeans = null;
		boolean flag = false;
		try {
			if (MisUtility.ifEmpty(sampleDistributionForm.getDistributionDate())) {
				receiveSampleBeans = sampleDistributionDao.fetchSampleAcceptDate(sampleDistributionForm.getSampleNum());
				if (!MisUtility.ifEmpty(receiveSampleBeans)) {
					for (ReceiveSampleBean receiveSampleBean : receiveSampleBeans) {
						Date collectionDate = receiveSampleBean.getCollection_date();
						Date distributionDate = MisUtility
								.convertStringSqlDate(sampleDistributionForm.getDistributionDate());
						if (distributionDate.before(collectionDate)) {
							flag = true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return flag;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		this.setAttrib(request);
		boolean status = false;
		SampleDistributionForm sampleDistributionForm = (SampleDistributionForm) form;

		System.out
				.println("inside save sampleDistributionForm --------------------" + sampleDistributionForm.toString());

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}

		try {
			// sampleDistributionForm.setEnteredBy(misSessionBean.getEnteredBy());
			if (MisUtility.ifEmpty(sampleDistributionForm)) {
				status = sampleDistributionBO.updateSampleDistribution(sampleDistributionForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully Update Sample Distribution " + "\t" + sampleDistributionForm.getSampleNum());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

				refreshsampleDistribution(sampleDistributionForm);

			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Record not successfully updated of Sample Part No." + "\t"
								+ sampleDistributionForm.getSamplePartNum());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {

			System.out.println("e.getCode()------------->" + e.getCode());
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Sample Number already exists");
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

			SampleDistributionForm sampleDistributionForm = (SampleDistributionForm) form;
			Set<LabelValueBean> labName = null;
			this.setAttrib(request);

			System.out.println("Unspecified........Sample Disrtibuion");

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
					System.out.println(String.valueOf(employee.getLabId()));
				}

				try {
					List<LabMasterBean> labMasterBean = locationDao.getLabName(labIdLst);
					for (LabMasterBean beans : labMasterBean) {
						labName.add(new LabelValueBean(beans.getLabName(), String.valueOf(beans.getLabId())));
					}
				} catch (DataAccessException ex) {
					System.out.println(ex.getRootCause());
				}

			}
			request.getSession().setAttribute("labNames", labName);
			refreshsampleDistribution(sampleDistributionForm);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error check check logs");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	public ActionForward fetchSublocationType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSublocationType");

		List<LocationTypeBean> locationBeans = null;
		LocationTypeBean typeBean = new LocationTypeBean();
		typeBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

		StringBuffer buffer = new StringBuffer();
		System.out.println("request.getParameter-------------" + request.getParameter("locationName"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("locationName"))) {
				locationBeans = locationMasterDao.getLocationType(typeBean);
				System.out.println(locationBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select Location Type");
				buffer.append("</option>");
				for (LocationTypeBean schemeHeaderBean2 : locationBeans) {
					buffer.append("<option value=\"").append(
							schemeHeaderBean2.getLocationName() + " -(" + schemeHeaderBean2.getLocationTypeId() + ")")
							.append("\">");
					buffer.append(
							schemeHeaderBean2.getLocationName() + " - (" + schemeHeaderBean2.getLocationTypeId() + ")");
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

	private void refreshsampleDistribution(SampleDistributionForm sampleDistributionForm) {

		sampleDistributionForm.setSampleNum(null);
		sampleDistributionForm.setLabName(null);
		sampleDistributionForm.setLocationName(null);
		sampleDistributionForm.setDistributionDate(null);
		sampleDistributionForm.setTechnician(null);
		sampleDistributionForm.setSamplePartNum(null);
		sampleDistributionForm.setTests(null);
		sampleDistributionForm.setRequiredBy(null);
		sampleDistributionForm.setSampleType(null);
		sampleDistributionForm.setFreeze(null);
	}

	public ActionForward populateSampleDistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		try {
			List<SampleDistributionForm> labMasterLst = null;
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			String searchParameter = request.getParameter("sSearch");

			labMasterLst = new ArrayList<SampleDistributionForm>();
			labMasterLst = sampleDistributionBO.getDistributedSampleByPagination(searchParameter, clickedColumn,
					clickedColumnDir);

			LocationJsonObject<SampleDistributionForm> locationJson = new LocationJsonObject<SampleDistributionForm>();

			if (MisUtility.ifEmpty(labMasterLst)) {
				locationJson.setAaData(new ArrayList<SampleDistributionForm>());
			}

			if (!MisUtility.ifEmpty(labMasterLst)) {
				locationJson.setiTotalDisplayRecords(labMasterLst.size());
				locationJson.setiTotalRecords(labMasterLst.size());
			}

			List<SampleDistributionForm> locMasterLst = null;
			if (!MisUtility.ifEmpty(labMasterLst)) {
				locMasterLst = sampleDistributionBO.getListBasedOnPageNumber(labMasterLst, pageDisplayLength,
						pageNumber, iDisplayStart);
				locationJson.setAaData(locMasterLst);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println(json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward fetchEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSamplePartNo");

		List<LabEmployee> labEmployeeLst = null;
		StringBuffer buffer = new StringBuffer();
		List<Long> employeeId = null;
		List<String> status;
		List<EmployeeBean> employeeBean = null;
		try {

			if (MisUtility.ifEmpty(request.getParameter("labname"))) {

				status = new ArrayList<String>();

				status.add(MISConstants.MASTER_STATUS_APPROVED);
				status.add(MISConstants.MASTER_STATUS_VERIFIED);

				labEmployeeLst = sampleDistributionDao
						.fetchEmployeeId(Integer.parseInt(request.getParameter("labname")));

				// System.out.println(labEmployeeLst.toString());

				if (!MisUtility.ifEmpty(labEmployeeLst)) {

					employeeId = new ArrayList<Long>();
					for (LabEmployee labEmp : labEmployeeLst) {
						employeeId.add(Long.parseLong(String.valueOf(labEmp.getEmpId())));
					}
					employeeBean = sampleDistributionDao.fetchEmployeeName(employeeId, status);
					if (!MisUtility.ifEmpty(labEmployeeLst)) {
						buffer.append("<option value='' selected>");
						buffer.append("Select Employee");
						buffer.append("</option>");
						for (EmployeeBean schemeHeaderBean2 : employeeBean) {
							buffer.append("<option value=\"").append(schemeHeaderBean2.getEmployeeId()).append("\">");
							buffer.append(schemeHeaderBean2.getEmployeeName());
							buffer.append("</option>");
						}
					}
				}
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
					out.print(buffer.toString().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public ActionForward fetchTests(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchTests");

		List<SamplePartCodeLabMapping> samplePartCodeLabMappings = null;
		StringBuffer buffer = new StringBuffer();
		try {

			if (MisUtility.ifEmpty(request.getParameter("labId"))) {
				samplePartCodeLabMappings = sampleDistributionDao.fetchTestType(request.getParameter("labId"));
				// System.out.println(labEmployeeLst.toString());
				if (!MisUtility.ifEmpty(samplePartCodeLabMappings)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select Category Type");
					buffer.append("</option>");
					for (SamplePartCodeLabMapping schemeHeaderBean2 : samplePartCodeLabMappings) {
						buffer.append("<option value=\"").append(schemeHeaderBean2.getTestLabSampleSubCodeId())
								.append("\">");
						buffer.append(schemeHeaderBean2.getCategory_type());
						buffer.append("</option>");
					}
				}
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
					out.print(buffer.toString().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public StringBuffer fetchSamplePartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		StringBuffer buffer = new StringBuffer();
		String generatedSequence = null;
		List<SamplePartCodeLabMapping> samplePartCodeLabMappings = null;
		try {
			String testType = request.getParameter("testType");
			System.out.println("inside------fetchSamplePartNo---->" + testType);
			if (MisUtility.ifEmpty(testType)) {
				samplePartCodeLabMappings = sampleDistributionDao.fetchTestType1(request.getParameter("testType"));
				if (!MisUtility.ifEmpty(samplePartCodeLabMappings)) {
					String labName = samplePartCodeLabMappings.get(0).getLabMaster().getLabIdName();
					String testType1 = samplePartCodeLabMappings.get(0).getCategory_type1();
					int fiscalYear = MisUtility.getFiscalYear();
					fiscalYear = fiscalYear % 100;
					buffer.append(labName);
					buffer.append("/");
					buffer.append(testType1);
					buffer.append("/");
					buffer.append(fiscalYear);
					buffer.append(fiscalYear + 1);
					buffer.append("/");
					if (MisUtility.ifEmpty(samplePartCodeLabMappings.get(0).getCurrentSequence())) {
						int currentSequence = samplePartCodeLabMappings.get(0).getCurrentSequence();
						if (currentSequence == 0) {
							currentSequence = 0001;
							generatedSequence = "0001";
						} else {
							if (currentSequence > 0 && currentSequence < 10) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 9) {
									generatedSequence = "00" + currentSequence;
								} else {
									generatedSequence = "000" + currentSequence;
								}

							} else if (currentSequence > 10 && currentSequence < 99) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 99) {
									generatedSequence = "0" + currentSequence;
								} else {
									generatedSequence = "00" + currentSequence;
								}
							} else if (currentSequence > 99 && currentSequence < 1000) {
								currentSequence = currentSequence + 1;
								if (currentSequence > 999) {
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
						generatedSequence = "0001";
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

	public ActionForward fetchSampleNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSampleNo");

		List<ReceiveSampleBean> locationBeans = null;
		ReceiveSampleBean typeBean = new ReceiveSampleBean();
		typeBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

		StringBuffer buffer = new StringBuffer();
		System.out.println("------request.getParameter-----fetchSampleNo--------" + request.getParameter("labname"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("labname"))) {
				locationBeans = sampleDistributionDao.getSampleNo(request.getParameter("labname"));
				if (!MisUtility.ifEmpty(locationBeans)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select Sample No.");
					buffer.append("</option>");
					for (ReceiveSampleBean schemeHeaderBean2 : locationBeans) {
						buffer.append("<option value=\"").append(schemeHeaderBean2.getSampleNumber().trim())
								.append("\">");
						buffer.append(schemeHeaderBean2.getSampleNumber());
						buffer.append("</option>");
					}
				}
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
					out.print(buffer.toString().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward generateQrCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {

		SampleDistributionForm sampleDistributionForm = (SampleDistributionForm) form;
		try {
			this.setAttrib(request);

			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			downloadQrCode(request, response, sampleDistributionForm);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return null;
	}

	private String downloadQrCode(HttpServletRequest request, HttpServletResponse response,
			SampleDistributionForm sampleDistributionForm) throws IOException {
		ServletOutputStream out = null;
		InputStream in = null;

		try {
			System.out.println(sampleDistributionForm.getSamplePartNum());
			// String filePath = "/images/barCode.png";

			// request.getServletContext().getRealPath("/images/barCode.png");
			String filePathPDF = "barCode.pdf";

			// request.getServletContext().getRealPath("/images/barCode.pdf");
			String charset = "UTF-8"; // or "ISO-8859-1"
			Map hintMap = new HashMap();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			// createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);

			generatePdf(filePathPDF, sampleDistributionForm);

			response.reset();
			// response.setContentType("image/png");
			// response.setHeader("Content-Disposition", "attachment; filename="
			// + "barCodess.png"); //pdf not working
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

	private void generatePdf(String filePathPDF,SampleDistributionForm sampleDistributionForm)
			throws FileNotFoundException, DocumentException {

		// Step - 1 :Create Document object that will hold the code
		Document qr_code = new Document(new Rectangle(336, 132));
		qr_code.setMargins(8, 8, 4, 4);
		// Step-2: Create PdfWriter object for the document
		PdfWriter writer = PdfWriter.getInstance(qr_code, new FileOutputStream(filePathPDF));
		// Step -3: Open document for editing
		qr_code.open();

		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100);

		// Row 1
		table.addCell(getQRCodeImage(sampleDistributionForm.getSamplePartNum(), PdfPCell.ALIGN_CENTER));

		// Add Table To Document
		qr_code.add(table);

		// Step-8: Close the PDF document
		qr_code.close();

	}

	public PdfPCell getQRCodeImage(String QRCode, int alignment) throws BadElementException {

		BarcodeQRCode my_code = new BarcodeQRCode(QRCode, 1, 1, null);

		// Step-6: Get Image corresponding to the input string
		Image qrcodeImage = my_code.getImage();
		qrcodeImage.setAbsolutePosition(0, 0);
		qrcodeImage.scaleToFit(120,120);

		PdfPCell cell = new PdfPCell(qrcodeImage);
		cell.setHorizontalAlignment(alignment);
		/*cell.setRowspan(4);
		cell.setColspan(1);*/
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

}
