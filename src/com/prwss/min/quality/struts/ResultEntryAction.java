package com.prwss.min.quality.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.LabToParameterMapping;
import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.bean.ParameterMasterDto;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.ResultEntryDetailBean;
import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.min.dao.ResultEntryDao;
import com.prwss.min.quality.ResultEntryBo;
import com.prwss.min.quality.ResultEntryDto;
import com.prwss.min.quality.ResultEntryForm;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class ResultEntryAction extends DispatchAction {

	private Logger log = Logger.getLogger(ResultEntryAction.class);

	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private ResultEntryBo<ResultEntryDto> resultEntryBo;
	private ResultEntryDao resultEntryDao;
	LocationDao locationDao;

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public ResultEntryDao getResultEntryDao() {
		return resultEntryDao;
	}

	public void setResultEntryDao(ResultEntryDao resultEntryDao) {
		this.resultEntryDao = resultEntryDao;
	}

	/**
	 * @return the resultEntryBo
	 */
	public ResultEntryBo<ResultEntryDto> getResultEntryBo() {
		return resultEntryBo;
	}

	/**
	 * @param resultEntryBo
	 *            the resultEntryBo to set
	 */
	public void setResultEntryBo(ResultEntryBo<ResultEntryDto> resultEntryBo) {
		this.resultEntryBo = resultEntryBo;
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

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSamplePartNo");

		List<SampleDistributionBean> sampleDistributionBeans = null;
		List<Long> employeeId = null;
		List<String> status;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			ResultEntryForm resultEntyForm = (ResultEntryForm) form;
			this.setAttrib(request);
			if (MisUtility.ifEmpty(resultEntyForm.getPartno())) {

				status = new ArrayList<String>();

				status.add(MISConstants.MASTER_STATUS_APPROVED);
				status.add(MISConstants.MASTER_STATUS_VERIFIED);

				sampleDistributionBeans = resultEntryDao.fetchEmployeeId(resultEntyForm.getPartno());

				System.out.println(sampleDistributionBeans.toString());

				if (!MisUtility.ifEmpty(sampleDistributionBeans)) {

					employeeId = new ArrayList<Long>();
					for (SampleDistributionBean sampleDistributionBean : sampleDistributionBeans) {

						List<ReceiveSampleBean> receiveSampleBeans = resultEntryDao
								.fetchSampleCollectionDetails(sampleDistributionBean);

						if (!MisUtility.ifEmpty(receiveSampleBeans)) {
							for (ReceiveSampleBean receiveSampleBean : receiveSampleBeans) {
								request.setAttribute("districtName",
										receiveSampleBean.getDistrictDetailBean().getLocationName());

								if (MisUtility.ifEmpty(receiveSampleBean.getBlockDetailBean())) {
									request.setAttribute("blockName",
											receiveSampleBean.getBlockDetailBean().getLocationName());
								}
								if (MisUtility.ifEmpty(receiveSampleBean.getLocationDetailBean())) {
									request.setAttribute("vilageName",
											receiveSampleBean.getLocationDetailBean().getLocationName());
								}
								if (MisUtility.ifEmpty(receiveSampleBean.getPmsSchemeDetailsBean())) {
									request.setAttribute("schemeName",
											receiveSampleBean.getPmsSchemeDetailsBean().getSchemeName());
								}
								request.setAttribute("waterSource", receiveSampleBean.getSchemeMapping().getWsName());

								request.setAttribute("sampleId", receiveSampleBean.getSampleId());
							}
						}
						request.setAttribute("testDoneBy", sampleDistributionBean.getTechnician());
						List<SamplePartCodeLabMapping> samplePartCodeLabMappings = resultEntryDao
								.fetchTestType(sampleDistributionBean);

						if (!MisUtility.ifEmpty(samplePartCodeLabMappings)) {
							for (SamplePartCodeLabMapping samplePartCodeLabMapping : samplePartCodeLabMappings) {

								employeeId.add(Long.parseLong(sampleDistributionBean.getTechnician()));

								List<LabToParameterMapping> labToParameterMappings = resultEntryDao
										.getLabToParameter(samplePartCodeLabMapping);
								List<Integer> pararmeterId = null;
								if (!MisUtility.ifEmpty(labToParameterMappings)) {
									pararmeterId = getParameterList(labToParameterMappings);

									// Business logic for update mode
									if (MisUtility.ifEmpty(request.getParameter("resultEntryId"))) {
										String resultEntryId = request.getParameter("resultEntryId");
										request.setAttribute("resultEntryId1", resultEntryId);
										List<ResultEntryBean> resultEntryBeans = resultEntryDao
												.findResultEntryList(resultEntryId);

										if (!MisUtility.ifEmpty(resultEntryBeans)) {
											List<ParameterMasterDto> parameterMasterDtos = getParamasterDtos(
													resultEntryBeans, request);

											System.out.println("-----1------------" + parameterMasterDtos.toString());
											resultEntyForm.setParameterMasterBeans(parameterMasterDtos);
											request.setAttribute("parameterMasterBeans", resultEntyForm);
										}

									}
									// Business logic for save mode
									else {
										List<ParameterMasterBean> parameterMasterBeans = resultEntryDao
												.findParameterList(pararmeterId);
										if (!MisUtility.ifEmpty(parameterMasterBeans)) {
											List<ParameterMasterDto> parameterMasterDtos = getParamasterDto(
													parameterMasterBeans);
											resultEntyForm.setParameterMasterBeans(parameterMasterDtos);
											request.setAttribute("parameterMasterBeans", resultEntyForm);
										}
									}
								}
							}

						}
					}

				}

			}
			String partNo = resultEntyForm.getPartno().trim();
			request.setAttribute("labId", resultEntyForm.getLabname());
			request.setAttribute("partno", partNo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");

	}

	private List<ParameterMasterDto> getParamasterDtos(List<ResultEntryBean> resultEntryBeans,
			HttpServletRequest request) {

		List<ParameterMasterDto> parameterMasterDtos = null;
		try {
			if (!MisUtility.ifEmpty(resultEntryBeans)) {
				parameterMasterDtos = new ArrayList<ParameterMasterDto>();
				Set<ResultEntryDetailBean> locDetailBeans = resultEntryBeans.get(0).getLocDetailBeans();
				request.setAttribute("completionDate",
						MisUtility.convertDateString(resultEntryBeans.get(0).getTestCompletedDate()));
				for (ResultEntryDetailBean resultEntryDetailBean : locDetailBeans) {
					ParameterMasterDto parameterMasterDto = new ParameterMasterDto();
					parameterMasterDto
							.setAcceptableLimit(resultEntryDetailBean.getParameterMasterBean().getAcceptableLimit());
					parameterMasterDto
							.setPermissibleLimit(resultEntryDetailBean.getParameterMasterBean().getPermissibleLimit());
					parameterMasterDto
							.setParameterName(resultEntryDetailBean.getParameterMasterBean().getParameterName());

					if (MisUtility.ifEmpty(resultEntryDetailBean.getParameterMasterBean().getUom())) {
						parameterMasterDto.setUom(String.valueOf(
								resultEntryDetailBean.getParameterMasterBean().getCombodetailUom().getCmb_name()));
					}
					parameterMasterDto.setParameterId(resultEntryDetailBean.getParameterMasterBean().getParameterId());
					parameterMasterDto.setParameterValue(resultEntryDetailBean.getActualValue());
					parameterMasterDto.setResultEntryDetailId(resultEntryDetailBean.getTestDetailId());
					parameterMasterDtos.add(parameterMasterDto);
				}

			}

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return parameterMasterDtos;
	}

	private List<ParameterMasterDto> getParamasterDto(List<ParameterMasterBean> parameterMasterBeans) {

		List<ParameterMasterDto> parameterMasterDtos = null;
		try {
			if (!MisUtility.ifEmpty(parameterMasterBeans)) {
				parameterMasterDtos = new ArrayList<ParameterMasterDto>();
				for (ParameterMasterBean parameterMasterBean : parameterMasterBeans) {
					ParameterMasterDto parameterMasterDto = new ParameterMasterDto();
					parameterMasterDto.setAcceptableLimit(parameterMasterBean.getAcceptableLimit());
					parameterMasterDto.setPermissibleLimit(parameterMasterBean.getPermissibleLimit());
					parameterMasterDto.setParameterName(parameterMasterBean.getParameterName());

					if (MisUtility.ifEmpty(parameterMasterBean.getUom())) {
						parameterMasterDto
								.setUom(String.valueOf(parameterMasterBean.getCombodetailUom().getCmb_name()));
					}
					parameterMasterDto.setParameterId(parameterMasterBean.getParameterId());
					parameterMasterDtos.add(parameterMasterDto);
				}
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return parameterMasterDtos;
	}

	private List<Integer> getParameterList(List<LabToParameterMapping> labToParameterMappings) {
		List<Integer> pararmeterId = new ArrayList<Integer>();
		try {
			for (LabToParameterMapping mapping : labToParameterMappings) {
				pararmeterId.add(mapping.getParameter_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pararmeterId;
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
			Set<LabelValueBean> labName = null;
			this.setAttrib(request);

			System.out.println("Unspecified........Result Entry");

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

		} catch (Exception e) {

			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error check check logs");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		System.out.println("Unspecified........Sample Disrtibuion");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationName");
		/* request.setAttribute("d__auto", "locationName"); */
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, IOException {
		this.setAttrib(request);
		boolean status = false;
		ResultEntryForm resultEntyForm = (ResultEntryForm) form;
		System.out.println("inside save Result Entry --------------------" + resultEntyForm.toString());

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		resultEntyForm.setEmpId(String.valueOf(misSessionBean.getEnteredBy()));
		try {
			// resultEntyForm.set(misSessionBean.getEnteredBy());
			if (MisUtility.ifEmpty(resultEntyForm)) {

				boolean flag = validateDate(resultEntyForm);
				if (flag) {
					throw new MISException(MISExceptionCodes.MIS002,
							"Completion Date should be greater than Distribution Date");
				}
				status = resultEntryBo.saveResultEntyFom(resultEntyForm);
			}

			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Successfully saved entry of Sample Part No " + "\t" + resultEntyForm.getPartno());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Entry not saved" + resultEntyForm.getSampleNum());
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
			log.debug(e.getMessage());
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.save", "Internal error please check log.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshResultEntryForm(resultEntyForm);

		return mapping.findForward("display");

	}

	private boolean validateDate(ResultEntryForm resultEntyForm) {

		List<SampleDistributionBean> sampleDistributionBeans = null;
		boolean flag = false;
		try {
			if (MisUtility.ifEmpty(resultEntyForm.getPartno())) {
				sampleDistributionBeans = resultEntryDao.fetchSamplePartDistributionDate(resultEntyForm.getPartno());
				if (!MisUtility.ifEmpty(sampleDistributionBeans)) {
					for (SampleDistributionBean sampleDistributionBean : sampleDistributionBeans) {
						Date distributionDate = sampleDistributionBean.getDistributionDate();
						Date completionDate = MisUtility.convertStringSqlDate(resultEntyForm.getComDate());
						if (completionDate.before(distributionDate)) {
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
		ResultEntryForm resultEntyForm = (ResultEntryForm) form;
		System.out.println("--------inside update Result Entry --------------------" + resultEntyForm.toString());
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		resultEntyForm.setEmpId(String.valueOf(misSessionBean.getEnteredBy()));
		try {
			if (MisUtility.ifEmpty(resultEntyForm)) {
				status = resultEntryBo.updateResultEntyForm(resultEntyForm);
			}
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Sucessfully Update Result Entry" + "\t" + resultEntyForm.getPartno());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				request.setAttribute("level2", "true");

				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save",
						"Record not update" + resultEntyForm.getSampleNum());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {

			System.out.println("e.getCode()------------->" + e.getCode());
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry",
					"Test Result Alreaady exists for " + resultEntyForm.getSampleNum() + "Sample Number");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {

			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshResultEntryForm(resultEntyForm);

		return mapping.findForward("display");

	}

	public ActionForward fetchSamplePartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchSamplePartNo");

		List<SampleDistributionBean> locationBeans = null;
		SampleDistributionBean typeBean = new SampleDistributionBean();
		typeBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));

		StringBuffer buffer = new StringBuffer();
		System.out.println("------request.getParameter-----fetchSamplePartNo--------" + request.getParameter("labId"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("labId"))) {
				
				locationBeans = resultEntryDao.getSamplePartNo(request.getParameter("labId"),request.getParameter("d_mode"));
				if (!MisUtility.ifEmpty(locationBeans)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select Sample Part");
					buffer.append("</option>");
					for (SampleDistributionBean schemeHeaderBean2 : locationBeans) {
						buffer.append("<option value=\"").append(schemeHeaderBean2.getSamplePartNum().trim())
								.append("\">");
						buffer.append(schemeHeaderBean2.getSamplePartNum());
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

	public ActionForward resultEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		List<ResultEntryDto> resultEntryList = new ArrayList<ResultEntryDto>();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			int clickedColumn = Integer.parseInt(request.getParameter("iSortCol_0"));
			String clickedColumnDir = request.getParameter("sSortDir_0");
			Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
			Integer pageNumber = 0;
			Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
			if (null != request.getParameter("iDisplayStart"))
				pageNumber = (iDisplayStart / pageDisplayLength) + 1;
			String searchParameter = request.getParameter("sSearch");
			resultEntryList = resultEntryBo.getLocationMasterByPagination(
					Integer.parseInt(String.valueOf(misSessionBean.getEnteredBy())), searchParameter, clickedColumn,
					clickedColumnDir);

			LocationJsonObject<ResultEntryDto> locationJson = new LocationJsonObject<ResultEntryDto>();

			if (MisUtility.ifEmpty(resultEntryList)) {
				locationJson.setAaData(new ArrayList<ResultEntryDto>());
			}
			if (!MisUtility.ifEmpty(resultEntryList)) {
				locationJson.setiTotalDisplayRecords(resultEntryList.size());
				locationJson.setiTotalRecords(resultEntryList.size());
			}

			List<ResultEntryDto> locMasterLst = null;
			if (!MisUtility.ifEmpty(resultEntryList)) {
				locMasterLst = resultEntryBo.getListBasedOnPageNumber(resultEntryList, pageDisplayLength, pageNumber,
						iDisplayStart);
				locationJson.setAaData(locMasterLst);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(locationJson);
			System.out.println("----------2--------" + json2);
			PrintWriter out = MisUtility.getPrintWriter(response);
			out.print(json2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void refreshResultEntryForm(ResultEntryForm resultEntryForm) {

		resultEntryForm.setSampleNum(null);
		resultEntryForm.setPartno(null);
		resultEntryForm.setLabname(null);
		resultEntryForm.setTechnician(null);
		resultEntryForm.setComDate(null);
		resultEntryForm.setAluminium(null);
		resultEntryForm.setLead(null);
		resultEntryForm.setSelenium(null);
		resultEntryForm.setChromium(null);
		resultEntryForm.setMercury(null);
		resultEntryForm.setArcenic(null);
		resultEntryForm.setCadmium(null);
		resultEntryForm.setNickel(null);
		resultEntryForm.setCopper(null);
		resultEntryForm.setIron(null);
		resultEntryForm.setUranium(null);
		resultEntryForm.setFloride(null);
		resultEntryForm.setChloride(null);
		resultEntryForm.setNitrate(null);
		resultEntryForm.setSulphate(null);
		resultEntryForm.setCalcium(null);
		resultEntryForm.setMagnesium(null);
		resultEntryForm.setSodium(null);
		resultEntryForm.setPottasium(null);

	}
}
