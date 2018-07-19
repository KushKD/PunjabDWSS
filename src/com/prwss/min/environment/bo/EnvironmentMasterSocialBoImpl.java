package com.prwss.min.environment.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvPondBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvWaterDisposableBean;
import com.prwss.min.environment.bean.EnvironmentBaselineWaterLoggingBean;
import com.prwss.min.environment.bean.EnvironmentSocialBean;
import com.prwss.min.environment.dao.EnvironmentSocialMasterDao;
import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionSocialForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentMasterSocialBoImpl implements EnvironmentMasterSocialBo {
	
	private Logger log = Logger.getLogger(EnvironmentMasterSocialBoImpl.class);
	
	private EnvironmentSocialMasterDao environmentSocialDao;
	
	

	public EnvironmentSocialMasterDao getEnvironmentSocialDao() {
		return environmentSocialDao;
	}

	public void setEnvironmentSocialDao(
			EnvironmentSocialMasterDao environmentSocialDao) {
		this.environmentSocialDao = environmentSocialDao;
	}

	@Override
	public EnvironmentDataCollectionSocialForm getEnvironmentDataSheetData(
			String appId) throws MISException, DataAccessException {
		List<EnvironmentSocialBean> socialEnvironmentbean = null;
		EnvironmentDataCollectionSocialForm environmentDataCollectionSocialForm = null;
		if (MisUtility.ifEmpty(appId)) {
			socialEnvironmentbean = environmentSocialDao.getDataBaseline(appId);
			if(!MisUtility.ifEmpty(socialEnvironmentbean)){
				
				
				try{
					environmentDataCollectionSocialForm = new EnvironmentDataCollectionSocialForm();
				for (EnvironmentSocialBean environmentBaselineBean : socialEnvironmentbean) {
					
					environmentDataCollectionSocialForm.setEdsId(String.valueOf(environmentBaselineBean.getEds_id()));
					environmentDataCollectionSocialForm.setIncomeSourse(String.valueOf(environmentBaselineBean.getIncome_src()));
					environmentDataCollectionSocialForm.setNaturalCultural(String.valueOf(environmentBaselineBean.getEffct_nat_prop()));
					environmentDataCollectionSocialForm.setNaturalCulturalText(environmentBaselineBean.getApp_safegaurd());
					environmentDataCollectionSocialForm.setNumberHouseholds(String.valueOf(environmentBaselineBean.getNo_households()));
					environmentDataCollectionSocialForm.setPattrenLandUsed(String.valueOf(environmentBaselineBean.getLand_pattern()));
					environmentDataCollectionSocialForm.setPopulation(String.valueOf(environmentBaselineBean.getPopulation()));
					environmentDataCollectionSocialForm.setReligiousImportance(String.valueOf(environmentBaselineBean.getHist_rel_impt()));
					environmentDataCollectionSocialForm.setRightsWater(String.valueOf(environmentBaselineBean.getInfrindge_rights()));
					environmentDataCollectionSocialForm.setRightsWaterText(environmentBaselineBean.getApp_mitigation());
					environmentDataCollectionSocialForm.setEds_social_env_id(String.valueOf(environmentBaselineBean.getEds_social_env_id()));
				}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}
			
		return environmentDataCollectionSocialForm;
	}

	@Override
	public int saveMaster(
			EnvironmentDataCollectionSocialForm socailEnvironmentForm,
			int parseInt) throws MISException, DataAccessException {


		EnvironmentSocialBean socialBean = null;
		
		int status = 0;
		try {

			if (MisUtility.ifEmpty(socailEnvironmentForm)) {
				socialBean = populateMasterEDSEnvironment(socailEnvironmentForm, parseInt);

				if (MisUtility.ifEmpty(socialBean)) {
					status = environmentSocialDao.saveMasterDataSocial(socialBean);
					
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
		
	}

	private EnvironmentSocialBean populateMasterEDSEnvironment( EnvironmentDataCollectionSocialForm socailEnvironmentForm, int parseInt) {
		
		
		// TODO Auto-generated method stub
		EnvironmentSocialBean socialBean = new EnvironmentSocialBean();

				
				if (MisUtility.ifEmpty(socailEnvironmentForm.getPopulation())) {
					socialBean.setPopulation(Integer
							.parseInt(socailEnvironmentForm.getPopulation()));
				}

			
				if (MisUtility.ifEmpty(socailEnvironmentForm.getNumberHouseholds())) {
					socialBean.setNo_households(Integer.parseInt(socailEnvironmentForm
							.getNumberHouseholds()));
				}

				
				if (MisUtility.ifEmpty(socailEnvironmentForm.getPattrenLandUsed())) {
					socialBean.setLand_pattern(Integer.parseInt(socailEnvironmentForm
							.getPattrenLandUsed()));
				}

			
				if (MisUtility.ifEmpty(socailEnvironmentForm.getReligiousImportance())) {
					socialBean.setHist_rel_impt(Integer.parseInt(socailEnvironmentForm
							.getReligiousImportance()));
				}

			
				if (MisUtility.ifEmpty(socailEnvironmentForm.getIncomeSourse())) {
					socialBean.setIncome_src(Integer.parseInt(socailEnvironmentForm
							.getIncomeSourse()));
				}

			
				if (MisUtility.ifEmpty(socailEnvironmentForm.getNaturalCultural())) {
					socialBean
							.setEffct_nat_prop(Integer.parseInt(socailEnvironmentForm.getNaturalCultural()));
				}

			
				if (MisUtility.ifEmpty(socailEnvironmentForm
						.getNaturalCulturalText())) {
					socialBean.setApp_safegaurd(socailEnvironmentForm
							.getNaturalCulturalText());
				}

				
				if (MisUtility.ifEmpty(socailEnvironmentForm.getRightsWater())) {
					socialBean.setInfrindge_rights(Integer
							.parseInt(socailEnvironmentForm.getRightsWater()));
				}

				
				if (MisUtility.ifEmpty(socailEnvironmentForm
						.getRightsWaterText())) {
					socialBean.setApp_mitigation(socailEnvironmentForm
							.getRightsWaterText());
				}

				

				

				
				
				socialBean.setCrt_by_user(parseInt);

				
				if(MisUtility.ifEmpty(socailEnvironmentForm.getEdsId())){
				socialBean.setEds_id(Integer.parseInt(socailEnvironmentForm
						.getEdsId().trim()));
				}
				
				
				if(MisUtility.ifEmpty(socailEnvironmentForm.getEds_social_env_id())){
					socialBean.setEds_social_env_id(Integer.parseInt(socailEnvironmentForm.getEds_social_env_id()));
				}

				return socialBean;
	}

	@Override
	public int updateMaster( EnvironmentDataCollectionSocialForm socialEnvironmentForm, int parseInt) throws MISException, DataAccessException {
		
		EnvironmentSocialBean socailBean = null;
		
		int status = 0;
		try {

			if (MisUtility.ifEmpty(socialEnvironmentForm)) {
				socailBean = populateMasterEDSEnvironment( socialEnvironmentForm, parseInt);

				if (MisUtility.ifEmpty(socailBean)) {
					status = environmentSocialDao .updateMasterDataBaseline(socailBean);
					
					
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

}
