package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.min.bean.VillageSchemeMappingBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.ComboBeanMaster;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class GetFilterValues extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1738910969424259354L;
	private LocationDao locationDao;
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;

	private Logger log = Logger.getLogger(GetFilterValues.class);

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

	private StringBuffer fetchBlock(String locationId) {

		System.out.println("--------------inside fetch block--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Block");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchDividions(String id) {

		System.out.println("--------------inside fetch block--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Division");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.fetchDivision(id);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer getForwardSurvey(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		String loggedInUsr;
		try {
			// System.out.println("DistrictId :"+locationId);
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
			loggedInUsr = String.valueOf(misSessionBean.getEnteredBy());
			if (MisUtility.ifEmpty(loggedInUsr)) {
				buffer.append("<option value='' selected>");
				buffer.append("Select Survey");
				buffer.append("</option>");
				List<BeneficiaryDto> surveyMasterBeans = locationDao.fetchForwardedSurvey(loggedInUsr);
				for (BeneficiaryDto surveyMaster : surveyMasterBeans) {
					buffer.append("<option value=\"").append(surveyMaster.getSurveyId()).append("\">");
					buffer.append(surveyMaster.getSurveyName());
					buffer.append("</option>");
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer getSurvey() {
		StringBuffer buffer = new StringBuffer();
		try {
			// System.out.println("DistrictId :"+locationId);
			buffer.append("<option value='' selected>");
			buffer.append("Select Survey");
			buffer.append("</option>");
			List<SurveyMasterBean> surveyMasterBeans = locationDao.fetchSurvey();
			for (SurveyMasterBean surveyMaster : surveyMasterBeans) {
				buffer.append("<option value=\"").append(surveyMaster.getSurvey_id()).append("\">");
				buffer.append(surveyMaster.getSurvey_name());
				buffer.append("</option>");
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer getBlockFromDistrict(String locationId) {
		StringBuffer buffer = new StringBuffer();
		try {
			// System.out.println("DistrictId :"+locationId);
			buffer.append("<option value='' selected>");
			buffer.append("Select Block");
			buffer.append("</option>");
			Set<LocationBean> blockList = locationDao.getChildLocationIds(locationId, "BLOCK");
			for (LocationBean locationBean2 : blockList) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName()).append(" - (").append(locationBean2.getLocationId())
						.append(")");
				buffer.append("</option>");
			}
			// System.out.println("Buffer: "+buffer.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchGramPanchayats(String blockId) {

		System.out.println("inside fetchGramPanchayat");

		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select GramPanchayat");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationIds(blockId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchVillages(String locationId) {
		System.out.println("inside fetch fetchVillages");
		StringBuffer buffer = new StringBuffer();
		List<LocationMasterDto> locationMasterDtos = null;
		List<Integer> villageIds = null;
		try {
			locationMasterDtos = locationDao.getVillage(locationId);
			if (!MisUtility.ifEmpty(locationMasterDtos)) {
				villageIds = new ArrayList<Integer>();
				for (LocationMasterDto dto : locationMasterDtos) {
					villageIds.add(dto.getLocationId());
				}
				buffer.append("<option value='' selected>");
				buffer.append("Select Village");
				buffer.append("</option>");
				List<LocationMasterDto> locationMasterDto = locationDao.getVillageName(villageIds);

				for (LocationMasterDto locationBean2 : locationMasterDto) {
					buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
					buffer.append(locationBean2.getLocationName());
					buffer.append("</option>");
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchVillage(String locationId) {
		System.out.println("inside fetch block");
		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchLab(HttpServletRequest request) {
		System.out.println("inside fetch lab");
		StringBuffer buffer = new StringBuffer();
		List<Long> labIdLst = new ArrayList<Long>();
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
			buffer.append("<option value='' selected>");
			buffer.append("Select Lab");
			buffer.append("</option>");
			List<LabEmployee> labEmp = locationDao
					.getLabDetails(Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));

			if (!MisUtility.ifEmpty(labEmp)) {

				for (LabEmployee employee : labEmp) {
					labIdLst.add(Long.parseLong(String.valueOf(employee.getLabId())));
				}
				List<LabMasterBean> labMasterBean = locationDao.getLabName(labIdLst);
				for (LabMasterBean bean : labMasterBean) {
					buffer.append("<option value=\"").append(bean.getLabId()).append("\">");
					buffer.append(bean.getLabName());
					buffer.append("</option>");
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(req.getSession().getServletContext());
			this.locationDao = (LocationDao) webApplicationContext.getBean("locationDao");
			// this.villageDao =
			// (VillageDao)webApplicationContext.getBean("villageDao");
			// this.itemDao=(ItemDao)webApplicationContext.getBean("itemDao");
			StringBuffer buffer = new StringBuffer();
			System.out.println("In GetFilterValues");

			if (MisUtility.ifEmpty(req.getParameter("parameterId"))) {
				System.out.println("req.getParameter(--------------)----" + req.getParameter("labdistrict"));
				buffer = fetchDividions(req.getParameter("parameterId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("villageId4321"))) {
				System.out.println("req.getParameter(--------------)----" + req.getParameter("villageId4321"));
				buffer = getBeneficary((String) req.getParameter("villageId4321"));
			}
			if (MisUtility.ifEmpty(req.getParameter("lab"))) {
				System.out.println("req.getParameter(--------------)----" + req.getParameter("lab"));
				buffer = fetchLab(req);
			}
			if (MisUtility.ifEmpty(req.getParameter("block"))) {
				System.out.println("req.getParameter(--------------)----" + req.getParameter("block"));
				buffer = fetchVillage((String) req.getParameter("block"));
			}
			if (MisUtility.ifEmpty(req.getParameter("gramPanchayatId"))) {
				buffer = fetchVillages((String) req.getParameter("gramPanchayatId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("villages"))) {
				buffer = fetchGramPanchayats((String) req.getParameter("villages"));
			}
			if (MisUtility.ifEmpty(req.getParameter("divisionIds"))) {
				buffer = fetchSchemeByDivisionId(req.getParameter("divisionIds"));
			}

			if (MisUtility.ifEmpty(req.getParameter("division"))) {
				buffer = fetchVillageByDivisionId(req.getParameter("division"));
			}

			if (MisUtility.ifEmpty(req.getParameter("subDivisionIds"))) {
				buffer = fetchSchemeBySubDivisionId(req.getParameter("subDivisionIds"));
			}
			if (MisUtility.ifEmpty(req.getParameter("subDivision"))) {
				buffer = fetchVillageBySubDivisionId(req.getParameter("subDivision"));
			}

			if (MisUtility.ifEmpty(req.getParameter("destrict"))) {
				System.out.println("req.getParameter(------------------" + req.getParameter("destrict"));
				buffer = fetchBlock((String) req.getParameter("destrict"));
			}
			if (MisUtility.ifEmpty(req.getParameter("surveyId"))) {
				System.out.println("req.getParameter(------------------" + req.getParameter("surveyId"));
				buffer = getSurvey();
			}

			if (MisUtility.ifEmpty(req.getParameter("surveyId"))) {
				System.out.println("req.getParameter(------------------" + req.getParameter("surveyId"));
				buffer = getSurvey();
			}

			if (MisUtility.ifEmpty(req.getParameter("surveyIds"))) {
				System.out.println("req.getParameter(------------------" + req.getParameter("surveyIds"));
				buffer = getForwardSurvey(req);
			}
			if (MisUtility.ifEmpty(req.getParameter("zoneId"))) {
				buffer = getCircleId((String) req.getParameter("zoneId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("circleId"))) {
				buffer = getDistrictId((String) req.getParameter("circleId"));
			}

			if (MisUtility.ifEmpty(req.getParameter("district_Id"))) {
				buffer = getDivisonalId((String) req.getParameter("district_Id"));
			}
			if (MisUtility.ifEmpty(req.getParameter("divisionId"))) {
				buffer = getSubDivisonalId((String) req.getParameter("divisionId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("getSubDivisonalId"))) {
				buffer = getSubDivisonalId2((String) req.getParameter("getSubDivisonalId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("constituencyVlg"))) {
				buffer = fetchVillageByConstituency((String) req.getParameter("constituencyVlg"));
			}

			/**
			 * Kush
			 */
			if (MisUtility.ifEmpty(req.getParameter("parentZone"))) {
				buffer = fetchZone((String) req.getParameter("parentZone"));
			}
			if (MisUtility.ifEmpty(req.getParameter("parentCircle"))) {
				buffer = fetchCircle((String) req.getParameter("parentCircle"));
			}
			if (MisUtility.ifEmpty(req.getParameter("parentDistrict"))) {
				buffer = fetchDistrict((String) req.getParameter("parentDistrict"));
			}
			if (MisUtility.ifEmpty(req.getParameter("divisionIdss"))) {
				buffer = fetchDistrictByDivision((String) req.getParameter("divisionIdss"));
			}

			if (MisUtility.ifEmpty(req.getParameter("districtType"))) {
				buffer = fetchDistrictByType((String) req.getParameter("districtType"));
			}
			if (MisUtility.ifEmpty(req.getParameter("division"))) {
				buffer = fetchDivision((String) req.getParameter("division"));
			}
			if (MisUtility.ifEmpty(req.getParameter("circle"))) {
				buffer = fetchDivisionByCircle((String) req.getParameter("circle"));
			}

			if (MisUtility.ifEmpty(req.getParameter("subdivision"))) {
				buffer = fetchSubDivision((String) req.getParameter("subdivision"));
			}

			if (MisUtility.ifEmpty(req.getParameter("subdivision1"))) {
				buffer = fetchScheme((String) req.getParameter("subdivision1"));
			}
			if (MisUtility.ifEmpty(req.getParameter("village"))) {
				buffer = fetchSchemeByVillage((String) req.getParameter("village"));
			}

			if (MisUtility.ifEmpty(req.getParameter("schemeId1"))) {
				buffer = fetchvillageFromScheme((String) req.getParameter("schemeId1"));
			}

			if (MisUtility.ifEmpty(req.getParameter("constituency"))) {
				buffer = fetchConstituency((String) req.getParameter("constituency"));
			}
			if (MisUtility.ifEmpty(req.getParameter("constituencyType"))) {
				buffer = fetchConstituencyByType((String) req.getParameter("constituencyType"));
			}
			if (MisUtility.ifEmpty(req.getParameter("constituencyId"))) {
				buffer = fetchSchemeByConstituency((String) req.getParameter("constituencyId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("parentComboId"))) {
				buffer = getCombo(Integer.parseInt(req.getParameter("parentComboId")));
			}

			if (MisUtility.ifEmpty(req.getParameter("habitation"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.HABITATION_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("gender"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.GENDER_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("status"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.STATUS));
			}
			if (MisUtility.ifEmpty(req.getParameter("truefalse"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.YES_NO_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("category"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.CATEGORY_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("apl"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.SUB_CATEGORY_COMBO_APL));
			}
			if (MisUtility.ifEmpty(req.getParameter("bpl"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.SUB_CATEGORY_COMBO_BPL));
			}
			if (MisUtility.ifEmpty(req.getParameter("toilet_type"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.TOILET_TYPE_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("toilet_constructed"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.TOILET_CONSTRUCTED_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("apl_card_combo"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.APL_CARD_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("bpl_card_combo"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.BPL_CARD_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("caste_combo"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.CASTE_COMBO));
			}
			if (MisUtility.ifEmpty(req.getParameter("uom"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.UOM));
			}
			if (MisUtility.ifEmpty(req.getParameter("water_source"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.WATER_SOURCE));
			}
			if (MisUtility.ifEmpty(req.getParameter("bottle_type"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.BOTTLE_TYPE));
			}
			if (MisUtility.ifEmpty(req.getParameter("religion"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.RELIGION));
			}
			if (MisUtility.ifEmpty(req.getParameter("bankName"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.BANK_NAME));
			}
			if (MisUtility.ifEmpty(req.getParameter("poiType"))) {
				buffer = getCombo(Integer.parseInt(MISConstants.POI_TYPE));
			}

			/*if (MisUtility.ifEmpty(req.getParameter("villageId"))) {
				buffer = fetchGramPanchayat(req.getParameter("villageId"));
			}
*/
			if (MisUtility.ifEmpty(req.getParameter("districtId"))) {
				if (MisUtility.ifEmpty(req.getParameter("type"))) {
					String type = req.getParameter("type");
					if (type.equals("withDPMC")) {
						buffer = getDivisionalOfficeDPMCId((String) req.getParameter("districtId"));
					} else if (type.equals("website")) {
						// System.out.println("In website");
						buffer = getBlockFromDistrict((String) req.getParameter("districtId"));
					} else if (type.equals("publicreport")) {
						buffer = getDivisionalOfficeDPMCIdForReport((String) req.getParameter("districtId"));

					}
				} else {
					buffer = getDivisionalOfficeId((String) req.getParameter("districtId"));
				}
			}

			PrintWriter out = resp.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {

				out.print(buffer.toString());
			}
		} catch (BeansException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	public StringBuffer getCircleId(String zoneId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(zoneId)) {
				locationBeans = locationDao.getChildLocationIds(zoneId, "CIRCLE");
				buffer.append("<option value='' selected>");
				buffer.append("Select Circle");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return buffer;
	}

	public StringBuffer getDistrictId(String circleId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(circleId)) {
				locationBeans = locationDao.getChildLocationIds(circleId, "DISTRICT");
				buffer.append("<option value='' selected>");
				buffer.append("Select District");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	// KD WOrking

	public StringBuffer getDivisonalId(String distictID) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(distictID)) {
				locationBeans = locationDao.getChildLocationIds(distictID, "DO");
				buffer.append("<option value='' selected>");
				buffer.append("Select Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getSubDivisonalId(String divisionalId) throws MISException {
		System.out.println("inside getSubDivisonalId------->");
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(divisionalId)) {
				locationBeans = locationDao.getChildLocationIds(divisionalId, "Sub-Division");
				buffer.append("<option value=''>");
				buffer.append("Select Sub Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
			System.out.println("buffer---->" + buffer.toString());
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getSubDivisonalId2(String divisionalId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(divisionalId)) {
				locationBeans = locationDao.getChildLocationIds(divisionalId, "Sub-Division");
				buffer.append("<option value=''>");
				buffer.append("Select Sub Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
			System.out.println("buffer---->" + buffer.toString());
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisionalOfficeId(String districtId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(districtId)) {
				locationBeans = locationDao.getChildLocationIds(districtId, "DO");
				buffer.append("<option value='' selected>");
				buffer.append("Select Division/SPMC");
				buffer.append("</option>");
				buffer.append("<option value='SPMC'>");
				buffer.append("SPMC");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisionalOfficeDPMCId(String districtId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(districtId)) {
				List<String> locationTypeList = new ArrayList<String>();
				locationTypeList.add("DO");
				locationTypeList.add("DPMC");
				locationTypeList.add("SPMC");
				locationBeans = locationDao.getChildLocationIds(districtId, locationTypeList);
				buffer.append("<option value='' selected>");
				buffer.append("Select Division/SPMC/DPMC");
				buffer.append("</option>");
				// buffer.append("<option value='SPMC'>");
				// buffer.append("SPMC");
				// buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisionalOfficeDPMCIdForReport(String districtId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(districtId)) {
				List<String> locationTypeList = new ArrayList<String>();
				locationTypeList.add("DO");
				locationBeans = locationDao.getChildLocationIds(districtId, locationTypeList);
				buffer.append("<option value='' selected>");
				buffer.append("Select Division");
				buffer.append("</option>");
				// buffer.append("<option value='SPMC'>");
				// buffer.append("SPMC");
				// buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	private StringBuffer getCombo(int id) {
		StringBuffer buffer = new StringBuffer();
		try {

			List<ComboBeanMaster> comboList = locationDao.getComboDetails(id);
			// Collections.sort(villageList);
			buffer.append("<option value='' selected>");
			buffer.append("Select ");
			buffer.append("</option>");
			for (ComboBeanMaster comboBean : comboList) {
				buffer.append("<option value=\"").append(comboBean.getCmb_id()).append("\">");
				buffer.append(comboBean.getComboBeanDetail().getCmb_name());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	/*public StringBuffer fetchGramPanchayat(String villageId) throws MISException {

		System.out.println("-------------inside fetchGramPanchayat------------------");
		//List<GramPanchayatMasterBean> gramPanchayatMasterBeans = null;   //GramPanchayatMasterBean
		List<GramPanchayatDetailBean> gramPanchayatDetailBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(villageId)) {
				gramPanchayatDetailBeans = locationDao.fetchGramPanchayat(villageId);
				System.out.println(gramPanchayatDetailBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select GramPanchayat");
				buffer.append("</option>");
				for (GramPanchayatDetailBean gramPanchayatDetailBean : gramPanchayatDetailBeans) {
					//gramPanchayatDetailBeans = gramPanchayatDetailBean.getGramPanchayatMasterBean().;   //GramPanchayatMasterBean
					for (GramPanchayatDetailBean gramPanchayatDetailBean : gramPanchayatDetailBeans) {
						buffer.append("<option value=\"").append(gramPanchayatDetailBean.getGramPanchayatId())
								.append("\">");
						buffer.append(gramPanchayatDetailBean.getGramPanchayatMasterBean().getNameofGramPanchayat());
						buffer.append("</option>");
					}

				}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return buffer;
	}*/

	private StringBuffer getBeneficary(String villageId) {
		System.out.println("inside Benifeciary fetch block");
		StringBuffer buffer = new StringBuffer();
		try {
			// System.out.println("DistrictId :"+locationId);
			buffer.append("<option value='' selected>");
			buffer.append("Select Beneficary");
			buffer.append("</option>");
			List<LocationMasterDto> beneficiaryEntryBean = locationDao.fetchBeneficiary(villageId);
			for (LocationMasterDto beneficiaryEntry : beneficiaryEntryBean) {
				buffer.append("<option value=\"").append(beneficiaryEntry.getLocationId()).append("\">");
				buffer.append(beneficiaryEntry.getLocationName());
				buffer.append("</option>");
			}
			// System.out.println("Buffer: "+buffer.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchZone(String locationId) {

		System.out.println("--------------inside fetch Zone--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Zone");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchCircle(String locationId) {

		System.out.println("--------------inside fetch Circle--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Circle");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchDistrict(String locationId) {

		System.out.println("--------------inside fetch District--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select District");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchDistrictByDivision(String locationId) {

		System.out.println("--------------inside fetch District--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			List<Integer> locationIds = locationDao.getDistrictIds(locationId);
			if (!MisUtility.ifEmpty(locationIds)) {

				buffer.append("<option value='' selected>");
				buffer.append("Select District");
				buffer.append("</option>");
				List<LocationMasterDto> locationMasterDto = locationDao.fetchDistrictByDistrictId(locationIds);

				for (LocationMasterDto locationBean2 : locationMasterDto) {
					buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
					buffer.append(locationBean2.getLocationName());
					buffer.append("</option>");
				}
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchDistrictByType(String locationId) {

		System.out.println("--------------inside fetch District--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select District");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationByType(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchDivisionByCircle(String locationId) {

		System.out.println("--------------inside fetch Division--------------");
		StringBuffer buffer = new StringBuffer();
		List<Integer> districtIds = null;
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Division");
			buffer.append("</option>");

			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationId(locationId);

			if (!MisUtility.ifEmpty(locationMasterDto)) {
				districtIds = new ArrayList<Integer>();
				for (LocationMasterDto dto : locationMasterDto) {
					districtIds.add(dto.getLocationId());
				}
			}
			List<LocationMasterDto> locationMasterDto1 = locationDao.getChildLocationDivisionId1(districtIds);

			for (LocationMasterDto locationBean2 : locationMasterDto1) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	// fetchDivision
	private StringBuffer fetchDivision(String locationId) {

		System.out.println("--------------inside fetch Division--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Division");
			buffer.append("</option>");

			List<LocationMasterDto> locationMasterDto1 = locationDao.getChildLocationDivisionId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto1) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	// Fetch Sub Division
	// fetchSubDivision
	private StringBuffer fetchSubDivision(String locationId) {

		System.out.println("--------------inside fetch Division--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Sub Division");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationDivisionId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	// Fetch fetchConstituency

	private StringBuffer fetchSchemeByConstituency(String locationId) {

		System.out.println("------inside fetch scheme-----------" + locationId);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		List<Integer> schemeIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Scheme");
			buffer.append("</option>");
			List<VillageDetailsBean> locationMasterDto = locationDao.fetchVillageIdByCons(locationId);
			if (!MisUtility.ifEmpty(locationMasterDto)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : locationMasterDto) {
					villageIds.add(locationBean2.getLocation_id());
				}

				List<VillageSchemeMappingBean> locationMasterDtos = locationDao.getSchemes(villageIds);
				schemeIds = new ArrayList<Integer>();
				for (VillageSchemeMappingBean locationBean2 : locationMasterDtos) {
					schemeIds.add(locationBean2.getSchemeId());
				}
				List<PMSSchemeDetailsBean> shemeDetails = locationDao.getSchemeNames(schemeIds);

				for (PMSSchemeDetailsBean locationBean2 : shemeDetails) {
					buffer.append("<option value=\"").append(locationBean2.getScheme_id()).append("\">");
					buffer.append(locationBean2.getSchemeName() + "("
							+ locationBean2.getPmsSchemeMaster().getSchemeNo() + ")");
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchConstituency(String locationId) {

		System.out.println("--------------inside fetch Division--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Constituency");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationConstituencyId(locationId);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchConstituencyByType(String consType) {

		System.out.println("--------------inside fetch Division--------------");
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select Constituency");
			buffer.append("</option>");
			List<LocationMasterDto> locationMasterDto = locationDao.getChildLocationConstituencyByType(consType);

			for (LocationMasterDto locationBean2 : locationMasterDto) {
				buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	// fetchvillageFromScheme
	private StringBuffer fetchvillageFromScheme(String locationId) {
		System.out.println("Fetching village from scheme ");
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");

			List<VillageSchemeMappingBean> locationMasterDto = locationDao.fetchVillageIdsFromSchemeId(locationId);
			if (!MisUtility.ifEmpty(locationMasterDto)) {
				villageIds = new ArrayList<Integer>();
				for (VillageSchemeMappingBean locationBean2 : locationMasterDto) {
					villageIds.add(locationBean2.getVillageId());
				}

				List<LocationDetailsBean> villageDetails = locationDao.getVillagesNames(villageIds);

				for (LocationDetailsBean locationBean2 : villageDetails) {
					// villageIds.add(locationBean2.getVillageIds());

					buffer.append("<option value=\"").append(locationBean2.getLocationDetailsId()).append("\">");
					buffer.append(locationBean2.getLocationName());
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchSchemeByVillage(String locationId) {
		System.out.println("------inside  fetchSchemeByVillage-----------" + locationId);
		StringBuffer buffer = new StringBuffer();
		List<Integer> schemeIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Scheme");
			buffer.append("</option>");

			List<VillageSchemeMappingBean> locationMasterDtos = locationDao.getSchemeIdByVillage(locationId);
			schemeIds = new ArrayList<Integer>();
			for (VillageSchemeMappingBean locationBean2 : locationMasterDtos) {
				schemeIds.add(locationBean2.getSchemeId());
			}
			List<PMSSchemeDetailsBean> shemeDetails = locationDao.getSchemeNames(schemeIds);

			for (PMSSchemeDetailsBean locationBean2 : shemeDetails) {
				buffer.append("<option value=\"").append(locationBean2.getScheme_id()).append("\">");
				buffer.append(locationBean2.getSchemeName() + "("
						+ locationBean2.getPmsSchemeMaster().getSchemeNo() + ")");
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchScheme(String locationId) {
		System.out.println("------inside fetch scheme-----------" + locationId);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		List<Integer> schemeIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Scheme");
			buffer.append("</option>");
			List<VillageDetailsBean> locationMasterDto = locationDao.fetchVillageIds(locationId);
			if (!MisUtility.ifEmpty(locationMasterDto)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : locationMasterDto) {
					villageIds.add(locationBean2.getLocation_id());
				}

				List<VillageSchemeMappingBean> locationMasterDtos = locationDao.getSchemes(villageIds);
				schemeIds = new ArrayList<Integer>();
				for (VillageSchemeMappingBean locationBean2 : locationMasterDtos) {
					schemeIds.add(locationBean2.getSchemeId());
				}
				List<PMSSchemeDetailsBean> shemeDetails = locationDao.getSchemeNames(schemeIds);

				for (PMSSchemeDetailsBean locationBean2 : shemeDetails) {
					buffer.append("<option value=\"").append(locationBean2.getScheme_id()).append("\">");
					buffer.append(locationBean2.getSchemeName() + "("
							+ locationBean2.getPmsSchemeMaster().getSchemeNo() + ")");
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchVillageByConstituency(String constituency) {
		System.out.println("------inside fetch fetchVillageByDivisionId---divisionId--------" + constituency);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			List<VillageDetailsBean> villageDetailsBeans = locationDao.fetchVillageIdsByConstituency(constituency);
			if (!MisUtility.ifEmpty(villageDetailsBeans)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : villageDetailsBeans) {
					villageIds.add(locationBean2.getLocation_id());
				}
				List<LocationMasterDto> locationMasterDto = locationDao.getVillageName(villageIds);
				for (LocationMasterDto locationBean2 : locationMasterDto) {
					buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
					buffer.append(locationBean2.getLocationName());
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchVillageByDivisionId(String divisionId) {
		System.out.println("------inside fetch fetchVillageByDivisionId---divisionId--------" + divisionId);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			List<VillageDetailsBean> villageDetailsBeans = locationDao.fetchVillageIdsByDivision(divisionId);
			if (!MisUtility.ifEmpty(villageDetailsBeans)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : villageDetailsBeans) {
					villageIds.add(locationBean2.getLocation_id());
				}
				List<LocationMasterDto> locationMasterDto = locationDao.getVillageName(villageIds);
				for (LocationMasterDto locationBean2 : locationMasterDto) {
					buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
					buffer.append(locationBean2.getLocationName());
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchVillageBySubDivisionId(String subDivision) {
		System.out.println("------inside fetch fetchVillageByDivisionId---divisionId--------" + subDivision);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			List<VillageDetailsBean> villageDetailsBeans = locationDao.fetchVillageIdsBySubDivision(subDivision);
			if (!MisUtility.ifEmpty(villageDetailsBeans)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : villageDetailsBeans) {
					villageIds.add(locationBean2.getLocation_id());
				}
				List<LocationMasterDto> locationMasterDto = locationDao.getVillageName(villageIds);
				for (LocationMasterDto locationBean2 : locationMasterDto) {
					buffer.append("<option value=\"").append(locationBean2.getLocationId()).append("\">");
					buffer.append(locationBean2.getLocationName());
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer fetchSchemeByDivisionId(String divisionId) {
		System.out.println("------inside fetch scheme---divisionId--------" + divisionId);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		List<Integer> schemeIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Scheme");
			buffer.append("</option>");
			List<VillageDetailsBean> locationMasterDto = locationDao.fetchVillageIdsByDivision(divisionId);
			if (!MisUtility.ifEmpty(locationMasterDto)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : locationMasterDto) {
					villageIds.add(locationBean2.getLocation_id());
				}

				List<VillageSchemeMappingBean> locationMasterDtos = locationDao.getSchemes(villageIds);
				schemeIds = new ArrayList<Integer>();
				for (VillageSchemeMappingBean locationBean2 : locationMasterDtos) {
					schemeIds.add(locationBean2.getSchemeId());
				}
				List<PMSSchemeDetailsBean> shemeDetails = locationDao.getSchemeNames(schemeIds);

				for (PMSSchemeDetailsBean locationBean2 : shemeDetails) {
					buffer.append("<option value=\"").append(locationBean2.getScheme_id()).append("\">");
					buffer.append(locationBean2.getSchemeName() + "("
							+ locationBean2.getPmsSchemeMaster().getSchemeNo() + ")");
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}


	private StringBuffer fetchSchemeBySubDivisionId(String subDivisionId) {
		System.out.println("------inside fetch scheme---divisionId--------" + subDivisionId);
		StringBuffer buffer = new StringBuffer();
		List<Integer> villageIds = null;
		List<Integer> schemeIds = null;
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select Scheme");
			buffer.append("</option>");
			List<VillageDetailsBean> locationMasterDto = locationDao.fetchVillageIdsBySubDivision(subDivisionId);
			if (!MisUtility.ifEmpty(locationMasterDto)) {
				villageIds = new ArrayList<Integer>();
				for (VillageDetailsBean locationBean2 : locationMasterDto) {
					villageIds.add(locationBean2.getLocation_id());
				}

				List<VillageSchemeMappingBean> locationMasterDtos = locationDao.getSchemes(villageIds);
				schemeIds = new ArrayList<Integer>();
				for (VillageSchemeMappingBean locationBean2 : locationMasterDtos) {
					schemeIds.add(locationBean2.getSchemeId());
				}
				List<PMSSchemeDetailsBean> shemeDetails = locationDao.getSchemeNames(schemeIds);

				for (PMSSchemeDetailsBean locationBean2 : shemeDetails) {
					buffer.append("<option value=\"").append(locationBean2.getScheme_id()).append("\">");
					buffer.append(locationBean2.getSchemeName() + "("
							+ locationBean2.getPmsSchemeMaster().getSchemeNo() + ")");
					buffer.append("</option>");
				}

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

}