package com.prwss.mis.masters.location.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.min.bean.VillageSchemeMappingBean;
import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.bean.ComboBeanMaster;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.min.sanitation.bean.SurveyValidationRequest;
import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.admin.dao.LocationConstituencyDetails;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonMasterBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LocationDaoImpl extends HibernateDaoSupport implements LocationDao {

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getLocationIds(String locationType) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);

			if (MisUtility.ifEmpty(locationType)) {
				criteria.add(Restrictions.eq("locationType", locationType).ignoreCase());
			}

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getChildLocationIds(String parentLocationId, String locationType)
			throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("parentLocation", parentLocationId));

				if (MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.eq("locationType", locationType).ignoreCase());

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationId(String parentLocationId) throws DataAccessException {

		System.out.println("inside dao-------" + parentLocationId);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class, "locationDetailsBean")
						.createCriteria("locationDetailsBean.locationMasterBean", "locationMasterBean");
				criteria.add(Restrictions.eq("locationMasterBean.parentLocation", Integer.parseInt(parentLocationId)));
				criteria.add(Restrictions.eq("locationMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationDetailsBean.locationName").as("locationName"))
						.add(Projections.property("locationMasterBean.locationId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getChildLocationIdss(String subdivisionId) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(subdivisionId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("locationId", subdivisionId));

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getChildLocationIds(String parentLocationId, List<String> locationType)
			throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("parentLocation", parentLocationId));

				if (!MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.in("locationType", locationType));

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationBean> getChildLocationListIds(String parentLocationId, String locationType)
			throws DataAccessException {
		List<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("parentLocation", parentLocationId));

				if (MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.eq("locationType", locationType).ignoreCase());

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				criteria.addOrder(Order.asc("locationName"));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	public boolean saveLocation(LocationBean locationBean) throws DataAccessException {
		try {
			getHibernateTemplate().save(locationBean);
		} catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public LocationBean getLocation(LocationBean locationBean) throws DataAccessException {
		List<LocationBean> locationBeans = null;
		LocationBean locationBean2 = null;

		try {
			if (MisUtility.ifEmpty(locationBean)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("locationId", locationBean.getLocationId()).ignoreCase());
				// criteria.addOrder(Order.asc("locationName"));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		if (!MisUtility.ifEmpty(locationBeans))
			locationBean2 = locationBeans.get(0);

		return locationBean2;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<LocationBean> getLocationIdOnTypeList(List<String> locationTypeList) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
			System.out.println("-----Search location for " + locationTypeList.get(0));
			if (!MisUtility.ifEmpty(locationTypeList))
				criteria.add(Restrictions.in("locationType", locationTypeList));

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			criteria.addOrder(Order.asc("locationName"));
			locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LocationMasterDto> getLocationBeanOnLocationIdList(List<Integer> locationIds)
			throws DataAccessException {
		List<LocationMasterDto> locationMasterDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(LocationDivisionSubDivisonDetailsBean.class, "locationDetailsDivSubDivBean")
					.createCriteria("locationDetailsDivSubDivBean.locationDivSubDivMasterBean",
							"locationDivSubDivMasterBean");
			criteria.add(
					Restrictions.eq("locationDivSubDivMasterBean.locationTypeId", Integer.parseInt(MISConstants.FOUR)));
			criteria.add(Restrictions.in("locationDivSubDivMasterBean.DivSubDivId", locationIds));
			criteria.add(Restrictions.eq("locationDivSubDivMasterBean.activeField",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("locationDetailsDivSubDivBean.DivisonSubDivisonDetailsName")
							.as("locationName"))
					.add(Projections.property("locationDivSubDivMasterBean.DivSubDivId").as("locationId")));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
			locationMasterDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return locationMasterDto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LocationDetailsBean> getLocationDetails(List<Integer> locationType, Long employeId)
			throws DataAccessException {
		List<LocationDetailsBean> locationBeans = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class);
			DetachedCriteria addrCrit = criteria.createCriteria("locationMasterBean");
			addrCrit.add(Restrictions.in("locationTypeId", locationType));
			addrCrit.add(Restrictions.eq("createdByUSer", employeId));
			/*
			 * addrCrit.setResultTransformer(addrCrit.DISTINCT_ROOT_ENTITY);
			 */
			locationBeans = getHibernateTemplate().findByCriteria(addrCrit);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return locationBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LocationBean> getLocationBeanOnLocationIdList2(List<String> locationIds) throws DataAccessException {
		List<LocationBean> locationBeans = null;
		List<String> locationTypes = new ArrayList<String>();
		locationTypes.add("DO");
		locationTypes.add("DPMC");
		locationTypes.add("SPMC");
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
			if (!MisUtility.ifEmpty(locationIds))
				criteria.add(Restrictions.in("locationId", locationIds));
			criteria.add(Restrictions.in("locationType", locationTypes));
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			locationBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LocationDetailsBean> getDistrictDetails(Long employeId, int locTypeId) throws DataAccessException {
		List<LocationDetailsBean> locationBeans = null;

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class);
			DetachedCriteria addrCrit = criteria.createCriteria("locationMasterBean");
			addrCrit.add(Restrictions.eq("locationTypeId", locTypeId));
			// addrCrit.add(Restrictions.eq("createdByUSer", employeId));
			/*
			 * addrCrit.setResultTransformer(addrCrit.DISTINCT_ROOT_ENTITY);
			 */
			locationBeans = getHibernateTemplate().findByCriteria(addrCrit);

		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabEmployee> getLabDetails(int empId) throws DataAccessException {
		List<LabEmployee> labMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LabEmployee.class);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("empId", empId));
			labMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw e;
		}
		return labMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabMasterBean> getLabName(List<Long> labEmployee) throws DataAccessException {
		List<LabMasterBean> labMasterBeans = null;
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LabMasterBean.class);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.in("labId", labEmployee));
			labMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			// e.printStackTrace();
			throw e;
		}
		return labMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBeanMaster> getComboDetails(int id) throws DataAccessException {
		List<ComboBeanMaster> comboMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComboBeanMaster.class, "comboBeanMaster");
			criteria.createAlias("comboBeanMaster.comboBeanDetail", "comboBeanDetail");
			criteria.add(Restrictions.eq("comboBeanMaster.active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.add(Restrictions.eq("comboBeanMaster.cmb_parent_id", id));
			criteria.addOrder(Order.asc("comboBeanDetail.cmb_id"));
			//criteria.addOrder(Order.asc("comboBeanDetail.cmb_name"));
			comboMasterBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return comboMasterBeans;
	}
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<VillageDetailsBean> fetchGramPanchayat(String id)
	 * throws DataAccessException { List<VillageDetailsBean> villageBean = null;
	 * try { DetachedCriteria criteria =
	 * DetachedCriteria.forClass(VillageDetailsBean.class);
	 * criteria.add(Restrictions.eq("location_id", Integer.parseInt(id)));
	 * criteria.add(Restrictions.ne("active_flag",
	 * Integer.parseInt(MISConstants.INACTIVE_STATUS)));
	 * criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); villageBean
	 * = getHibernateTemplate().findByCriteria(criteria);
	 * 
	 * } catch (DataAccessException e) { throw e; } return villageBean; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatMasterBean> fetchGramPanchayat(String id) throws DataAccessException {
		List<GramPanchayatMasterBean> villageBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatMasterBean.class,
					"gramPanchayatMasterBean");

			criteria.createAlias("gramPanchayatMasterBean.locDetailBeans", "locDetailBeans");
			criteria.add(Restrictions.eq("locDetailBeans.village", Integer.parseInt(id)));
			criteria.add(Restrictions.ne("locDetailBeans.status", Integer.parseInt(MISConstants.INACTIVE_STATUS)));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			villageBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return villageBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyMasterBean> fetchSurvey() throws DataAccessException {
		List<SurveyMasterBean> surveyMasterBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SurveyMasterBean.class);
			criteria.add(Restrictions.ne("active_flag", Integer.parseInt(MISConstants.INACTIVE_STATUS)));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			surveyMasterBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return surveyMasterBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> fetchForwardedSurvey(String loggedInUsrId) throws DataAccessException {
		System.out.println("inside forward------");
		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SurveyValidationRequest.class,
					"surveyValidationRequest");
			criteria.createAlias("surveyValidationRequest.surveyReviewMapping", "surveyReviewMapping");
			criteria.createAlias("surveyReviewMapping.surveyMasterBean", "surveyMasterBean");
			criteria.add(Restrictions.eq("lyingWithUsr", Integer.parseInt(loggedInUsrId)));
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("surveyMasterBean.survey_id")), "surveyId")
					.add(Projections.property("surveyMasterBean.survey_name"), "surveyName"));
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));

			beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return beneficiaryDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationIds(String parentLocationId) throws DataAccessException {
		List<LocationMasterDto> locationMasterDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatMasterBean.class,
					"gramPanchayatMasterBean");
			criteria.createAlias("gramPanchayatMasterBean.locDetailBeans", "locDetailBeans");
			criteria.add(Restrictions.eq("locDetailBeans.village", Integer.parseInt(parentLocationId)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("locDetailBeans.gramPanchayatId").as("locationId")))
					.add(Projections.property("gramPanchayatMasterBean.nameofGramPanchayat").as("locationName")));
			criteria.setResultTransformer(Transformers.aliasToBean(LocationMasterDto.class));
			locationMasterDtos = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}
		return locationMasterDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getVillage(String parentLocationId) throws DataAccessException {
		List<LocationMasterDto> locationMasterDtos = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(GramPanchayatMasterBean.class,
					"gramPanchayatMasterBean");
			criteria.createAlias("gramPanchayatMasterBean.locDetailBeans", "locDetailBeans");
			criteria.add(Restrictions.eq("locDetailBeans.gramPanchayatId", Integer.parseInt(parentLocationId)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("locDetailBeans.village").as("locationId"))));
			criteria.setResultTransformer(Transformers.aliasToBean(LocationMasterDto.class));
			locationMasterDtos = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return locationMasterDtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getVillageName(List<Integer> parentLocationId) throws DataAccessException {

		System.out.println("inside dao-------" + parentLocationId);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (!MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class, "locationDetailsBean")
						.createCriteria("locationDetailsBean.locationMasterBean", "locationMasterBean");
				criteria.add(Restrictions.in("locationMasterBean.locationId", parentLocationId));
				criteria.add(Restrictions.eq("locationMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationDetailsBean.locationName").as("locationName"))
						.add(Projections.property("locationMasterBean.locationId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationMasterDto;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> fetchBeneficiary(String villageId) throws DataAccessException {
		List<LocationMasterDto> beneficiaryEntryBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			criteria.add(Restrictions.eq("beneficiaryEntryBean.villageId", Integer.parseInt(villageId)));
			criteria.add(
					Restrictions.ne("beneficiaryEntryBean.activeFlag", Integer.parseInt(MISConstants.INACTIVE_STATUS)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryName").as("locationName"))
					.add(Projections.property("beneficiaryEntryBean.beneficieryId").as("locationId")));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));

			beneficiaryEntryBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return beneficiaryEntryBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationDivisionId(String parentLocationId) throws DataAccessException {
		System.out.println("inside dao-------" + parentLocationId);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(LocationDivisionSubDivisonDetailsBean.class, "locationDetailsDivSubDivBean")
						.createCriteria("locationDetailsDivSubDivBean.locationDivSubDivMasterBean",
								"locationDivSubDivMasterBean");

				criteria.add(Restrictions.eq("locationDivSubDivMasterBean.parentLocation",
						Integer.parseInt(parentLocationId)));
				criteria.add(Restrictions.eq("locationDivSubDivMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationDetailsDivSubDivBean.DivisonSubDivisonDetailsName")
								.as("locationName"))
						.add(Projections.property("locationDivSubDivMasterBean.DivSubDivId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationConstituencyId(String parentLocationId) throws DataAccessException {
		System.out.println("inside dao-------" + parentLocationId);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(LocationConstituencyDetails.class, "locationConstituencyDetails").createCriteria(
								"locationConstituencyDetails.locationConstituencyBean", "locationConstituencyBean");
				criteria.add(
						Restrictions.eq("locationConstituencyBean.parentLocation", Integer.parseInt(parentLocationId)));
				criteria.add(Restrictions.eq("locationConstituencyBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationConstituencyDetails.ConstituencyDetailsName")
								.as("locationName"))
						.add(Projections.property("locationConstituencyBean.ConstituencyId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageSchemeMappingBean> getSchemes(List<Integer> parentLocationId) throws DataAccessException {
		List<VillageSchemeMappingBean> locationBeans = null;
		try {
			if (!MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageSchemeMappingBean.class);
				criteria.add(Restrictions.in("villageId", parentLocationId));

				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<PMSSchemeDetailsBean> getSchemeNames(List<Integer> schemes) throws DataAccessException {
		List<PMSSchemeDetailsBean> locationBeans = null;
		try {
			if (!MisUtility.ifEmpty(schemes)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(PMSSchemeDetailsBean.class);
				criteria.add(Restrictions.in("scheme_id", schemes));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PMSSchemeDetailsBean> getSchemeNames(List<Integer> schemes) throws DataAccessException {
		List<PMSSchemeDetailsBean> locationBeans = null;
		try {
			if (!MisUtility.ifEmpty(schemes)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(PMSSchemeDetailsBean.class);
				criteria.createAlias("pmsSchemeMaster", "pmsSchemeMaster");
				criteria.add(Restrictions.in("scheme_id", schemes));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageSchemeMappingBean> fetchVillageIdsFromSchemeId(String schemes) throws DataAccessException {
		// TODO Auto-generated method stub
		List<VillageSchemeMappingBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(schemes)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageSchemeMappingBean.class);
				criteria.add(Restrictions.eq("schemeId", Integer.parseInt(schemes)));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

				// criteria.addOrder(Order.desc("locationName"));

				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationDetailsBean> getVillagesNames(List<Integer> villages) throws DataAccessException {
		List<LocationDetailsBean> locationBeans = null;
		try {
			if (!MisUtility.ifEmpty(villages)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class);
				criteria.add(Restrictions.in("locationDetailsId", villages));
				// criteria.setResultTransformer(new
				// AliasToBeanResultTransformer(LocationMasterDto.class));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> fetchVillageIds(String blockId) throws DataAccessException {

		List<VillageDetailsBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(blockId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
				criteria.add(Restrictions.eq("block_id", blockId));

				criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

				// criteria.addOrder(Order.desc("locationName"));

				locationBeans = new ArrayList<VillageDetailsBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> fetchVillageIdsByDivision(String divisionId) throws DataAccessException {
		List<VillageDetailsBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(divisionId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
				criteria.add(Restrictions.eq("divisionalId", Integer.parseInt(divisionId)));
				criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}

		return locationBeans;
	}

	@Override
	public List<WorkFlowMasterBean> fetchFormwardId(Integer entBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlowMasterBean> fetchWorkflowData(Integer entBy, String subjectId) {

		List<WorkFlowMasterBean> workFlowMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(WorkFlowMasterBean.class);
			criteria.add(Restrictions.eq("from_emp_id", entBy));
			criteria.add(Restrictions.eq("subject_id", Integer.parseInt(subjectId)));
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			workFlowMasterBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}

		return workFlowMasterBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> fetchDivision(String id) throws DataAccessException {
		List<LocationMasterDto> locationMasterDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(LocationDivisionSubDivisonDetailsBean.class, "locationDetailsDivSubDivBean")
					.createCriteria("locationDetailsDivSubDivBean.locationDivSubDivMasterBean",
							"locationDivSubDivMasterBean");
			criteria.add(Restrictions.eq("locationDivSubDivMasterBean.locationTypeId", Integer.parseInt(id)));
			criteria.add(Restrictions.eq("locationDivSubDivMasterBean.activeField",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("locationDetailsDivSubDivBean.DivisonSubDivisonDetailsName")
							.as("locationName"))
					.add(Projections.property("locationDivSubDivMasterBean.DivSubDivId").as("locationId")));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
			locationMasterDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationDivisionId1(List<Integer> parentLocationId)
			throws DataAccessException {
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (!MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(LocationDivisionSubDivisonDetailsBean.class, "locationDetailsDivSubDivBean")
						.createCriteria("locationDetailsDivSubDivBean.locationDivSubDivMasterBean",
								"locationDivSubDivMasterBean");
				criteria.add(Restrictions.in("locationDivSubDivMasterBean.parentLocation", parentLocationId));
				criteria.add(Restrictions.eq("locationDivSubDivMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationDetailsDivSubDivBean.DivisonSubDivisonDetailsName")
								.as("locationName"))
						.add(Projections.property("locationDivSubDivMasterBean.DivSubDivId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationByType(String locationType) throws DataAccessException {
		System.out.println("inside dao-------" + locationType);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (MisUtility.ifEmpty(locationType)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class, "locationDetailsBean")
						.createCriteria("locationDetailsBean.locationMasterBean", "locationMasterBean");
				criteria.add(Restrictions.eq("locationMasterBean.locationTypeId", Integer.parseInt(locationType)));
				criteria.add(Restrictions.eq("locationMasterBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationDetailsBean.locationName").as("locationName"))
						.add(Projections.property("locationMasterBean.locationId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getChildLocationConstituencyByType(String consType) throws DataAccessException {
		System.out.println("inside dao-------" + consType);
		List<LocationMasterDto> locationMasterDto = null;
		try {
			if (MisUtility.ifEmpty(consType)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(LocationConstituencyDetails.class, "locationConstituencyDetails").createCriteria(
								"locationConstituencyDetails.locationConstituencyBean", "locationConstituencyBean");
				criteria.add(Restrictions.eq("locationConstituencyBean.locationTypeId", Integer.parseInt(consType)));
				criteria.add(Restrictions.eq("locationConstituencyBean.activeField",
						Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("locationConstituencyDetails.ConstituencyDetailsName")
								.as("locationName"))
						.add(Projections.property("locationConstituencyBean.ConstituencyId").as("locationId")));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));
				locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> fetchVillageIdsBySubDivision(String subDivision) throws DataAccessException {
		List<VillageDetailsBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(subDivision)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
				criteria.add(Restrictions.eq("subDiv", Integer.parseInt(subDivision)));
				criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageSchemeMappingBean> getSchemeIdByVillage(String parentLocationId) throws DataAccessException {
		List<VillageSchemeMappingBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageSchemeMappingBean.class);
				criteria.add(Restrictions.eq("villageId", Integer.parseInt(parentLocationId)));

				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));

				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> fetchVillageIdByCons(String constituencyId) throws DataAccessException {
		List<VillageDetailsBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(constituencyId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
				criteria.add(Restrictions.eq("constituancyId", Integer.parseInt(constituencyId)));
				criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}
		return locationBeans;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDetailsBean> fetchVillageIdsByConstituency(String constituency) throws DataAccessException {
		List<VillageDetailsBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(constituency)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageDetailsBean.class);
				criteria.add(Restrictions.eq("constituancyId", Integer.parseInt(constituency)));
				criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> getNodalDivision(List<Integer> locationIds) throws DataAccessException {

		List<LocationMasterDto> locationMasterDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(LocationDivisionSubDivisonDetailsBean.class, "locationDetailsDivSubDivBean")
					.createCriteria("locationDetailsDivSubDivBean.locationDivSubDivMasterBean",
							"locationDivSubDivMasterBean");
			criteria.add(
					Restrictions.eq("locationDivSubDivMasterBean.locationTypeId", Integer.parseInt(MISConstants.FOUR)));
			criteria.add(Restrictions.in("locationDivSubDivMasterBean.DivSubDivId", locationIds));
			criteria.add(Restrictions.eq("locationDivSubDivMasterBean.activeField",
					Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections
							.distinct(Projections.property("locationDivSubDivMasterBean.DivSubDivId").as("locationId")))
					.add(Projections.property("locationDetailsDivSubDivBean.DivisonSubDivisonDetailsName")
							.as("locationName")));

			criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));

			locationMasterDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getDistrictIds(String divisionId) throws DataAccessException {
		List<Integer> locationMasterDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDivisionSubDivisonMasterBean.class);
			criteria.add(Restrictions.eq("DivSubDivId", Integer.parseInt(divisionId)));
			criteria.add(Restrictions.eq("activeField", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.setProjection(Projections.distinct(Projections.property("parentLocation").as("locationId")));

			locationMasterDto = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return locationMasterDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationMasterDto> fetchDistrictByDistrictId(List<Integer> locationIds) throws DataAccessException {

		List<LocationMasterDto> locationMasterDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationDetailsBean.class);
			criteria.createAlias("locationMasterBean", "locationMasterBean");
			criteria.add(Restrictions.in("locationMasterBean.locationId", locationIds));
			criteria.add(
					Restrictions.eq("locationMasterBean.activeField", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("locationName").as("locationName"))
					.add(Projections.property("locationMasterBean.locationId").as("locationId")));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(LocationMasterDto.class));

			locationMasterDto = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}
		return locationMasterDto;
	}
}
