package com.prwss.min.sanitation.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.min.sanitation.bean.ComboDetailPoJo;
import com.prwss.min.sanitation.bean.GramPanchayatDetailsDto;
import com.prwss.min.sanitation.bean.GramPanchayatRegisterBean;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;


public class GramPanchayatRegisterDaoImpl extends HibernateDaoSupport implements GramPanchayatRegisterDao {
	
	private Logger log = Logger.getLogger(GramPanchayatRegisterDaoImpl.class);
	
	@Override
	public boolean saveGramPanchayatRegisterData(GramPanchayatRegisterBean gramPanchayatRegisterBean)
			throws DataAccessException {
		try {
			System.out.println("DAO" + gramPanchayatRegisterBean.toString());
			getHibernateTemplate().save(gramPanchayatRegisterBean);

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return true;
	}

	@Override
	public List<ComboDetailPoJo> getHabitationFromCombo(String comboId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GramPanchayatDetailsDto> getGramPanchayatDetails(ViewRegistrationsForm form)
			throws DataAccessException {
		// TODO Auto-generated method stub
		List<GramPanchayatDetailsDto> gramPanchayatDetailsDtos=null;
		try{
			
			Criteria criteria= getSession().createCriteria(GramPanchayatRegisterBean.class, "gramPanchayatRegisterBean");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailHabitation", "combodetailHabitation");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailGender", "combodetailGender");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailCardType", "combodetailCardType");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailCategoryID", "combodetailCategoryID");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailSubCategoryID", "combodetailSubCategoryID");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailCasteId", "combodetailCasteId");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailtoiletBeforeSurvey", "combodetailtoiletBeforeSurvey");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailtoiletConstructedFrom", "combodetailtoiletConstructedFrom");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailhad_functional_toilet", "combodetailhad_functional_toilet");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailfunctional_toilet_used", "combodetailfunctional_toilet_used");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailwater_facility_available", "combodetailwater_facility_available");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailis_covered", "combodetailis_covered");
			criteria.createAlias("gramPanchayatRegisterBean.combodetailtoilet_type", "combodetailtoilet_type");
			criteria.createAlias("gramPanchayatRegisterBean.locationDetailBean", "locationDetailBean");
			if(MisUtility.ifEmpty(form.getDistrict()))
			criteria.add(Restrictions.eq("district_id", Integer.parseInt(form.getDistrict())));
			System.out.println("District  " + form.getDistrict());
			if(MisUtility.ifEmpty(form.getBlock()))
			criteria.add(Restrictions.eq("block_id", Integer.parseInt(form.getBlock())));
			System.out.println("Block  "+form.getBlock());
			if(MisUtility.ifEmpty(form.getVillage()))
			criteria.add(Restrictions.eq("village_id",Integer.parseInt( form.getVillage())));
			System.out.println("Village  "+form.getVillage());
			criteria.add(Restrictions.eq("active_flag", Integer.parseInt(MISConstants.ACTIVE_STATUS)));
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("gramPanchayatRegisterBean.family_id"),"familyId")
					.add(Projections.property("gramPanchayatRegisterBean.family_head"),"familyheadName")
					.add(Projections.property("gramPanchayatRegisterBean.fat_hus_name"),"fatherHusbandName")
					.add(Projections.property("gramPanchayatRegisterBean.aadhaar_number"),"aadhaarNumber")
					.add(Projections.property("gramPanchayatRegisterBean.remarks"),"remarks")
					.add(Projections.property("combodetailHabitation.cmb_name"), "habitationName")
					.add(Projections.property("combodetailGender.cmb_name"), "gender")
					.add(Projections.property("combodetailCardType.cmb_name"), "cardType")
					.add(Projections.property("combodetailCategoryID.cmb_name"), "category")
					.add(Projections.property("combodetailSubCategoryID.cmb_name"), "subcategory")
					.add(Projections.property("combodetailCasteId.cmb_name"), "caste")
					.add(Projections.property("combodetailtoiletBeforeSurvey.cmb_name"), "hadToiletBefore")
					.add(Projections.property("combodetailtoiletConstructedFrom.cmb_name"), "collDate")
					.add(Projections.property("combodetailhad_functional_toilet.cmb_name"), "functionalToilet")
					.add(Projections.property("combodetailfunctional_toilet_used.cmb_name"), "havingFunctionalToilet")
					.add(Projections.property("combodetailwater_facility_available.cmb_name"), "waterfacility")
					.add(Projections.property("combodetailis_covered.cmb_name"), "isCovered")
					.add(Projections.property("combodetailtoilet_type.cmb_name"), "toiletType")
					.add(Projections.property("locationDetailBean.locationName"), "villageName"));
					
			gramPanchayatDetailsDtos= criteria.setResultTransformer(Transformers.aliasToBean(GramPanchayatDetailsDto.class)).list();
					System.out.println(gramPanchayatDetailsDtos.toString());
			//gramPanchayatDetails=	getHibernateTemplate().findByCriteria(criteria);
			
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw e;
		}
		return gramPanchayatDetailsDtos;
	}

}
