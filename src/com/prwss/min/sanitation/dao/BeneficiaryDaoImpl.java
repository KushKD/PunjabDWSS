/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.bean.BeneficiaryEntryDetailsDto;
import com.prwss.min.sanitation.form.BeneficiaryForm;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BeneficiaryDaoImpl extends HibernateDaoSupport implements BeneficiaryDao {
	private Logger log = Logger.getLogger(BeneficiaryDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryEntryBean> getBeneficiaryDetails(BeneficiaryForm beneficiaryForm)
			throws DataAccessException {
		List<BeneficiaryEntryBean> beneficiaryBean = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class);

			if (MisUtility.ifEmpty(beneficiaryForm)) {
				criteria.add(Restrictions.eq("aadhaarNumber", Long.parseLong(beneficiaryForm.getAdharNumber().trim())));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			}
			beneficiaryBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return beneficiaryBean;
	}

	@Override
	public boolean saveBeneficiaryDetails(BeneficiaryEntryBean beneficiaryEntryBean) throws DataAccessException {
		try {
			getHibernateTemplate().save(beneficiaryEntryBean);

		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryDto> populateBeneficiaryDetails(ViewRegistrationsForm viewRegistrationForm,
			String searchString, int clickedColumn, String colOrder) throws DataAccessException {

		List<BeneficiaryDto> beneficiaryDto = null;
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			criteria.createAlias("beneficiaryEntryBean.combodetailReligion", "combodetailReligion");
			criteria.createAlias("beneficiaryEntryBean.locationDetailBean", "locationDetailBean");

			if (MisUtility.ifEmpty(viewRegistrationForm.getVillage()))
				criteria.add(Restrictions.eq("villageId", Integer.parseInt(viewRegistrationForm.getVillage())));

			if (MisUtility.ifEmpty(viewRegistrationForm.getBlock()))
				criteria.add(Restrictions.eq("blockId", Integer.parseInt(viewRegistrationForm.getBlock())));

			if (MisUtility.ifEmpty(viewRegistrationForm.getDistrict()))
				criteria.add(Restrictions.eq("districtId", Integer.parseInt(viewRegistrationForm.getDistrict())));

			if (MisUtility.ifEmpty(Integer.parseInt(String.valueOf(viewRegistrationForm.getLoginUser())))) {
				criteria.add(Restrictions.eq("createdById",
						Integer.parseInt(String.valueOf(viewRegistrationForm.getLoginUser()))));
			}
			if (MisUtility.ifEmpty(searchString)) {
				criteria.add(Restrictions.disjunction()
						.add(Restrictions.ilike("beneficiaryEntryBean.beneficieryName", searchString,
								MatchMode.ANYWHERE))
						.add(Restrictions.ilike("beneficiaryEntryBean.fatHusName", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.ilike("combodetailReligion.cmb_name", searchString, MatchMode.ANYWHERE))
						.add(Restrictions.sqlRestriction("aadhaar_number::text like '%" + searchString + "%'")));
						
			}
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));
			if (MisUtility.ifEmpty(String.valueOf(clickedColumn)) && MisUtility.ifEmpty(colOrder)) {
				if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("beneficiaryEntryBean.beneficieryName"));
				} else if (MISConstants.CLICKED_ONE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("beneficiaryEntryBean.beneficieryName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("beneficiaryEntryBean.fatHusName"));
				} else if (MISConstants.CLICKED_TWO.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("beneficiaryEntryBean.fatHusName"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("combodetailReligion.cmb_name"));
				} else if (MISConstants.CLICKED_THREE.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("combodetailReligion.cmb_name"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.ASC)) {
					criteria.addOrder(Order.asc("beneficiaryEntryBean.aadhaarNumber"));
				} else if (MISConstants.CLICKED_FOUR.equalsIgnoreCase(String.valueOf(clickedColumn))
						&& colOrder.equalsIgnoreCase(MISConstants.DSC)) {
					criteria.addOrder(Order.desc("beneficiaryEntryBean.aadhaarNumber"));
				}
			}
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
					.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "name")
					.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatherSpouseName")
					.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadharNo")
					.add(Projections.property("combodetailReligion.cmb_name"), "religion"));

			criteria.getExecutableCriteria(getSession()).setMaxResults(100);
			criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryDto.class));
			
			beneficiaryDto=getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return beneficiaryDto;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryEntryDetailsDto> getBeneficiaryDetails(String beneficiaryId) throws DataAccessException {

		List<BeneficiaryEntryDetailsDto> beneficiaryEntryDetailsDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			criteria.createAlias("beneficiaryEntryBean.combodetailCasteId", "combodetailCasteId");
			criteria.createAlias("beneficiaryEntryBean.combodetailGender", "combodetailGender");
			criteria.createAlias("beneficiaryEntryBean.combodetailCategory", "combodetailCategory");
			criteria.createAlias("beneficiaryEntryBean.combodetailReligion", "combodetailReligion");
			criteria.createAlias("beneficiaryEntryBean.combodetailPoiType", "combodetailPoiType");
			criteria.createAlias("beneficiaryEntryBean.combodetailBankName", "combodetailBankName");
			criteria.createAlias("beneficiaryEntryBean.locationDetailBean", "locationDetailBean");
			criteria.createAlias("beneficiaryEntryBean.districtDetailBean", "districtDetailBean");
			criteria.createAlias("beneficiaryEntryBean.blockDetailBean", "blockDetailBean");

			if (MisUtility.ifEmpty(beneficiaryId)) {
				criteria.add(Restrictions.eq("beneficieryId", Integer.parseInt(beneficiaryId)));
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("beneficiaryEntryBean.beneficieryId"), "beneficiaryId")
						.add(Projections.property("combodetailCasteId.cmb_name"), "casteName")
						.add(Projections.property("combodetailGender.cmb_name"), "gender")
						.add(Projections.property("combodetailCategory.cmb_name"), "category")
						.add(Projections.property("combodetailReligion.cmb_name"), "religion")
						.add(Projections.property("combodetailPoiType.cmb_name"), "poiType")
						.add(Projections.property("combodetailBankName.cmb_name"), "bankName")
						.add(Projections.property("beneficiaryEntryBean.beneficieryName"), "beneficieryName")
						.add(Projections.property("beneficiaryEntryBean.fatHusName"), "fatHusName")
						.add(Projections.property("beneficiaryEntryBean.phoneNo"), "phoneNo")
						.add(Projections.property("locationDetailBean.locationName"), "villageName")
						.add(Projections.property("districtDetailBean.locationName"), "districtName")
						.add(Projections.property("blockDetailBean.locationName"), "blockName")
						.add(Projections.property("beneficiaryEntryBean.photographName"), "photographName")
						.add(Projections.property("beneficiaryEntryBean.gramPanchayatId"), "gramPanchayatId")
						.add(Projections.property("beneficiaryEntryBean.poiId"), "poiId")
						.add(Projections.property("beneficiaryEntryBean.aadhaarNumber"), "aadhaarNumber")
						.add(Projections.property("beneficiaryEntryBean.electConnNumber"), "electConnNumber")
						.add(Projections.property("beneficiaryEntryBean.electBill"), "electBill")
						.add(Projections.property("beneficiaryEntryBean.branchName"), "branchName")
						.add(Projections.property("beneficiaryEntryBean.accountNumber"), "accountNumber")
						.add(Projections.property("beneficiaryEntryBean.ifscCode"), "ifscCode")
						.add(Projections.property("beneficiaryEntryBean.photograp"), "photograp"));
				criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryEntryDetailsDto.class));
				beneficiaryEntryDetailsDto=getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		
		return beneficiaryEntryDetailsDto;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<BeneficiaryEntryDetailsDto> getElectricityBillData(String beneficiaryId) throws DataAccessException {

		List<BeneficiaryEntryDetailsDto> beneficiaryDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BeneficiaryEntryBean.class, "beneficiaryEntryBean");
			if (MisUtility.ifEmpty(beneficiaryId)) {
				criteria.add(Restrictions.eq("beneficiaryEntryBean.beneficieryId", Integer.parseInt(beneficiaryId)));
				criteria.add(Restrictions.eq("beneficiaryEntryBean.activeFlag", Integer.parseInt(MISConstants.ONE)));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("beneficiaryEntryBean.electBill"), "electBill")
						.add(Projections.property("beneficiaryEntryBean.elecConData"), "elecConData"));
				criteria.setResultTransformer(Transformers.aliasToBean(BeneficiaryEntryDetailsDto.class));
				beneficiaryDto = getHibernateTemplate().findByCriteria(criteria);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return beneficiaryDto;
	}

}
