package com.prwss.min.environment.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;

import com.prwss.min.environment.bean.EnvironmentBaselineEnvPondBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvWaterDisposableBean;
import com.prwss.min.environment.bean.EnvironmentBaselineWaterLoggingBean;
import com.prwss.min.environment.bean.EnvironmentHealthBean;
import com.prwss.min.environment.bean.EnvironmentSocialBean;
import com.prwss.min.environment.bean.EnvironmentVectorBourneDiseaseBean;
import com.prwss.min.environment.bean.EnvironmentWaterBourneDiseaseBean;
import com.prwss.min.environment.dao.EnvironmentMasterHealthDao;
import com.prwss.min.environment.dao.EnvironmentMasterHealthDaoImpl;
import com.prwss.min.environment.form.EnvironmentDataCollectionHealthForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionSocialForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentMasterHealthBoImpl implements EnvironmentMasterHealthBo {
	
	private Logger log = Logger.getLogger(EnvironmentMasterHealthBoImpl.class);

	private EnvironmentMasterHealthDao environmentHealthDao;

	public EnvironmentMasterHealthDao getEnvironmentHealthDao() {
		return environmentHealthDao;
	}

	public void setEnvironmentHealthDao(
			EnvironmentMasterHealthDao environmentHealthDao) {
		this.environmentHealthDao = environmentHealthDao;
	}

	@Override
	public int saveMaster(
			EnvironmentDataCollectionHealthForm healthEnvironmentForm,
			int parseInt) throws DataException, MISException {
		EnvironmentHealthBean healthBean = null;
		
		List<EnvironmentWaterBourneDiseaseBean>  waterBourneDiseaseBean = null; 
		List<EnvironmentVectorBourneDiseaseBean> vectorBourneDiseaseBean = null;
	
		
		int status = 0;
		try {

			if (MisUtility.ifEmpty(healthEnvironmentForm)) {
				healthBean = populateMasterEDSEnvironment(healthEnvironmentForm, parseInt);

				if (MisUtility.ifEmpty(healthBean)) {
					status = environmentHealthDao.saveMasterDataSocial(healthBean);
					
					
					//Save Data Table 1
					if(MisUtility.ifEmpty(status)){
						//populate and Save the other table  EnvironmentBaselineEnvPondBean
						waterBourneDiseaseBean = new ArrayList<EnvironmentWaterBourneDiseaseBean>();
						waterBourneDiseaseBean = populateWaterBourneData(healthEnvironmentForm,status,parseInt);
						environmentHealthDao.saveWaterBourneDisease(waterBourneDiseaseBean);
						
						//Save Data Table 2
						vectorBourneDiseaseBean = new ArrayList<EnvironmentVectorBourneDiseaseBean>();
						vectorBourneDiseaseBean = populateVectorBourneData(healthEnvironmentForm,status,parseInt);
						environmentHealthDao.saveVectorBourneDisease(vectorBourneDiseaseBean); 
						
						
						
					}
					
					
					
					
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}
	
	
private List<EnvironmentVectorBourneDiseaseBean> populateVectorBourneData( EnvironmentDataCollectionHealthForm healthEnvironmentForm, int status, int parseInt) {
	EnvironmentVectorBourneDiseaseBean bean = null;
	List<EnvironmentVectorBourneDiseaseBean> beans = null;
	try {

		if (MisUtility.ifEmpty(healthEnvironmentForm.getVectorBourneText())) {
			beans = new ArrayList<EnvironmentVectorBourneDiseaseBean>();
		
			for (String assignedTo : healthEnvironmentForm.getVectorBourneText()) {
				bean = new EnvironmentVectorBourneDiseaseBean();
				bean.setDisease_id(Integer.parseInt(assignedTo));
				bean.setEds_pblc_hlth_id(status);
				bean.setCrt_By_User(parseInt);
				beans.add(bean);
			}
		}
		
	} catch (Exception e) {
		log.debug(e.getMessage());
	}
	return beans;
	}

private List<EnvironmentWaterBourneDiseaseBean> populateWaterBourneData( EnvironmentDataCollectionHealthForm healthEnvironmentForm, int status, int parseInt) {
	EnvironmentWaterBourneDiseaseBean bean = null;
	List<EnvironmentWaterBourneDiseaseBean> beans = null;
	try {

		if (MisUtility.ifEmpty(healthEnvironmentForm.getWaterBourneName())) {
			beans = new ArrayList<EnvironmentWaterBourneDiseaseBean>();
		
			for (String assignedTo : healthEnvironmentForm.getWaterBourneName()) {
				bean = new EnvironmentWaterBourneDiseaseBean();
				bean.setDisease_id(Integer.parseInt(assignedTo));
				bean.setEds_pblc_hlth_id(status);
				bean.setCrt_By_User(parseInt);
				beans.add(bean);
			}
		}
		
	} catch (Exception e) {
		log.debug(e.getMessage());
	}
	return beans;
	}

private EnvironmentHealthBean populateMasterEDSEnvironment( EnvironmentDataCollectionHealthForm healthEnvironmentForm, int parseInt) {
		
		
		// TODO Auto-generated method stub
	EnvironmentHealthBean healthBean = new EnvironmentHealthBean();

				
				if (MisUtility.ifEmpty(healthEnvironmentForm.getWaterBourne())) {
					healthBean.setWater_brn_disease(Integer
							.parseInt(healthEnvironmentForm.getWaterBourne()));
				}

			
				/*if (MisUtility.ifEmpty(healthEnvironmentForm.getWaterBourneName())) {
					healthBean.setWb_disease_name(Integer.parseInt(healthEnvironmentForm
							.getWaterBourneName()));
				}*/

				
			if (MisUtility.ifEmpty(healthEnvironmentForm.getVectorBourne())) {
					healthBean.setVector_brn_disease(Integer.parseInt(healthEnvironmentForm
							.getVectorBourne()));
				}

			
				/*if (MisUtility.ifEmpty(healthEnvironmentForm.getVectorBourneText())) {
					healthBean.setVb_disease_name(Integer.parseInt(healthEnvironmentForm
							.getVectorBourneText()));
				}*/

			
				

				
				healthBean.setCrt_by_user(parseInt);

				
				if(MisUtility.ifEmpty(healthEnvironmentForm.getEdsId())){
					healthBean.setEds_id(Integer.parseInt(healthEnvironmentForm
						.getEdsId().trim()));
				}
				
				
				if(MisUtility.ifEmpty(healthEnvironmentForm.getEds_pblc_hlth_id())){
					healthBean.setEds_pblc_hlth_id(Integer.parseInt(healthEnvironmentForm.getEds_pblc_hlth_id()));
				}

				return healthBean;
	}

@Override
public EnvironmentDataCollectionHealthForm getEnvironmentDataSheetData(
		String appId) throws DataException, MISException {
	List<EnvironmentHealthBean> healthEnvironmentbean = null;
	EnvironmentDataCollectionHealthForm environmentDataCollectionhealthForm = null;
	if (MisUtility.ifEmpty(appId)) {
		healthEnvironmentbean = environmentHealthDao.getDataHealth(appId);
		if(!MisUtility.ifEmpty(healthEnvironmentbean)){
			
			
			try{
				environmentDataCollectionhealthForm = new EnvironmentDataCollectionHealthForm();
			for (EnvironmentHealthBean environmentBaselineBean : healthEnvironmentbean) {
				
				environmentDataCollectionhealthForm.setEdsId(String.valueOf(environmentBaselineBean.getEds_id()));
				environmentDataCollectionhealthForm.setEds_pblc_hlth_id(String.valueOf(environmentBaselineBean.getEds_pblc_hlth_id()));
				environmentDataCollectionhealthForm.setWaterBourne(String.valueOf(environmentBaselineBean.getWater_brn_disease()));
				//environmentDataCollectionhealthForm.setWaterBourneName(String.valueOf(environmentBaselineBean.getWb_disease_name()));
				environmentDataCollectionhealthForm.setVectorBourne(String.valueOf(environmentBaselineBean.getVector_brn_disease()));
				//environmentDataCollectionhealthForm.setVectorBourneText(String.valueOf(environmentBaselineBean.getVb_disease_name()));
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
		
	return environmentDataCollectionhealthForm;
}

@Override
public int updateMaster(
		EnvironmentDataCollectionHealthForm healthEnvironmentForm, int parseInt)
		throws DataException, MISException {
	EnvironmentHealthBean healthBean = null;
	
	int status = 0;
	try {

		if (MisUtility.ifEmpty(healthEnvironmentForm)) {
			healthBean = populateMasterEDSEnvironment( healthEnvironmentForm, parseInt);

			if (MisUtility.ifEmpty(healthBean)) {
				status = environmentHealthDao.updateMasterDataBaseline(healthBean);
				
				
				
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
