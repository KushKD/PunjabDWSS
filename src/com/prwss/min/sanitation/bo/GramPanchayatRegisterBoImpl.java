package com.prwss.min.sanitation.bo;

import org.apache.log4j.Logger;

import com.prwss.min.sanitation.bean.GramPanchayatRegisterBean;
import com.prwss.min.sanitation.dao.GramPanchayatRegisterDao;
import com.prwss.min.sanitation.form.GramPanchayatRegisterForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class GramPanchayatRegisterBoImpl   implements GramPanchayatRegisterBO {

	private Logger log = Logger.getLogger(GramPanchayatRegisterBoImpl.class);
	
	private GramPanchayatRegisterDao gramPanchayatRegisterDao;
	
	
	
	
	public GramPanchayatRegisterDao getGramPanchayatRegisterDao() {
		return gramPanchayatRegisterDao;
	}

	public void setGramPanchayatRegisterDao(GramPanchayatRegisterDao gramPanchayatRegisterDao) {
		this.gramPanchayatRegisterDao = gramPanchayatRegisterDao;
	}

	@Override
	public boolean saveGramPanchayatRegisterData(GramPanchayatRegisterForm gramPanchayatform) throws MISException {
		
		GramPanchayatRegisterBean gramPanchayatBean = null;
		boolean status=false;
		try {
			if (MisUtility.ifEmpty(gramPanchayatform)) {
				gramPanchayatBean = populateGramRegister(gramPanchayatform);
				if(MisUtility.ifEmpty(gramPanchayatBean)){
					status=gramPanchayatRegisterDao.saveGramPanchayatRegisterData(gramPanchayatBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	private GramPanchayatRegisterBean populateGramRegister(GramPanchayatRegisterForm gramPanchayatform) {
		
		GramPanchayatRegisterBean bean = new GramPanchayatRegisterBean();
		
		if(MisUtility.ifEmpty(gramPanchayatform.getDistrict())){
			bean.setDistrict_id(Integer.parseInt(gramPanchayatform.getDistrict()));
		}
		if(MisUtility.ifEmpty(gramPanchayatform.getBlock())){
			bean.setBlock_id(Integer.parseInt(gramPanchayatform.getBlock()));
		}
		if(MisUtility.ifEmpty(gramPanchayatform.getVillageId())){
			bean.setVillage_id(Integer.parseInt(gramPanchayatform.getVillageId()));
		}
		if(MisUtility.ifEmpty(gramPanchayatform.getHabitation())){
			bean.setHabitaion_id(Integer.parseInt((gramPanchayatform.getHabitation())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getFamilyId())){
			bean.setFamily_id((gramPanchayatform.getFamilyId()));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getFamilyheadName())){
			bean.setFamily_head(gramPanchayatform.getFamilyheadName());
		}
		

		if(MisUtility.ifEmpty(gramPanchayatform.getFatherHusbandName())){
			bean.setFat_hus_name(gramPanchayatform.getFatherHusbandName());
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getCardType())){
			bean.setCard_type_id(Integer.parseInt((gramPanchayatform.getCardType())));
		}
		if(MisUtility.ifEmpty(gramPanchayatform.getAadhaarNumber())){
			bean.setAadhaar_number(Long.parseLong((gramPanchayatform.getAadhaarNumber())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getGender())){
			bean.setGender_id(Integer.parseInt((gramPanchayatform.getGender())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getCategory())){
			bean.setCategory_id(Integer.parseInt((gramPanchayatform.getCategory())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getSubcategory())){
			bean.setSub_category_id(Integer.parseInt((gramPanchayatform.getSubcategory())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getCaste())){
			bean.setCaste_id(Integer.parseInt((gramPanchayatform.getCaste())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getHadToiletBefore())){
			bean.setToilet_before_survey(Integer.parseInt(gramPanchayatform.getHadToiletBefore())); 
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getCollDate())){
			bean.setToilet_constructed_from(Integer.parseInt((gramPanchayatform.getCollDate())));   //needs clarification
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getFunctionalToilet())){
			bean.setFunctional_toilet_used(Integer.parseInt((gramPanchayatform.getFunctionalToilet())));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getHavingFunctionalToilet())){
			bean.setHad_functional_toilet(Integer.parseInt(gramPanchayatform.getHavingFunctionalToilet()));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getWaterfacility())){
			bean.setWater_facility_available(Integer.parseInt(gramPanchayatform.getWaterfacility()));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getIsCovered())){
			bean.setIs_covered(Integer.parseInt(gramPanchayatform.getIsCovered()));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getToiletType())){
			bean.setToilet_type(Integer.parseInt(gramPanchayatform.getToiletType()));
		}
		
		if(MisUtility.ifEmpty(gramPanchayatform.getCreatedByUser())){
			bean.setCrt_by_usr(Integer.parseInt(gramPanchayatform.getCreatedByUser()));
		}
		
	 if(MisUtility.ifEmpty(gramPanchayatform.getRemarks())){
			bean.setRemarks(gramPanchayatform.getRemarks());
		}
	
		bean.setActive_flag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
		
		
		return bean;
		
	}

}
