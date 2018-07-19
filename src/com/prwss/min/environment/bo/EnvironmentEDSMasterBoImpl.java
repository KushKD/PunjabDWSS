package com.prwss.min.environment.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.environment.bean.EnvironmentEDSMaster;
import com.prwss.min.environment.dao.EnvironmentDataCollectionDao;
import com.prwss.min.environment.form.EnvironmentDataCollectionForm;
import com.prwss.min.quality.LabMasterBoImpl;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentEDSMasterBoImpl extends AbstractPaginationMaster<EnvironmentDataCollectionForm> implements EnvironmentEDSMasterBo<EnvironmentDataCollectionForm>  {

	
	private EnvironmentDataCollectionDao environmentDataCollectionDao;
			

	private Logger log = Logger.getLogger(EnvironmentEDSMasterBoImpl.class);
	
	

	public EnvironmentDataCollectionDao getEnvironmentDataCollectionDao() {
		return environmentDataCollectionDao;
	}

	public void setEnvironmentDataCollectionDao(
			EnvironmentDataCollectionDao environmentDataCollectionDao) {
		this.environmentDataCollectionDao = environmentDataCollectionDao;
	}

	@Override
	public Integer saveMaster( EnvironmentDataCollectionForm environmentDataCollectionForm, int parseInt) throws MISException,DataAccessException {
		           
		EnvironmentEDSMaster environmentEDSMasterBean = null;
		int status = 0;
		try {

			if (MisUtility.ifEmpty(environmentDataCollectionForm)) {
				 environmentEDSMasterBean = populateMasterEDSEnvironment(environmentDataCollectionForm, parseInt);
				 
				 if (MisUtility.ifEmpty(environmentEDSMasterBean)) {
						status = environmentDataCollectionDao.saveMasterData(environmentEDSMasterBean);
					}
				
			}
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error(e.getLocalizedMessage(), e);
		throw new MISException(e);
	}
		return status;
}

	private EnvironmentEDSMaster populateMasterEDSEnvironment(EnvironmentDataCollectionForm environmentDataCollectionForm, int parseInt) {
		
		
		
		// TODO Auto-generated method stub
		EnvironmentEDSMaster environmentEDSMasterBean = new EnvironmentEDSMaster();
		
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getZone())) {
			environmentEDSMasterBean.setZone_id(Integer.parseInt(environmentDataCollectionForm.getZone()));
		}
		
		//District
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getDistrict())) {
			environmentEDSMasterBean.setDistrict_id(Integer.parseInt(environmentDataCollectionForm.getDistrict()));
		}
		
		//Block
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getBlock())) {
			environmentEDSMasterBean.setBlock_id(Integer.parseInt(environmentDataCollectionForm.getBlock()));
		}
		
		//Village
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getVillageId())) {
			environmentEDSMasterBean.setVillage_id(Integer.parseInt(environmentDataCollectionForm.getVillageId()));
		}
		
		//SchemeType
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getSchemeType())) {
			environmentEDSMasterBean.setScheme_type(Integer.parseInt(environmentDataCollectionForm.getSchemeType()));
		}
		
		//SchemeCategory
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getSchemeCategory())) {
			environmentEDSMasterBean.setScheme_category(Integer.parseInt(environmentDataCollectionForm.getSchemeCategory()));
		}
		
		//GramPanchayat
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getVi())) {
			environmentEDSMasterBean.setGram_panchayat(Integer.parseInt(environmentDataCollectionForm.getVi()));
		}
		
		//Schemename
		if (MisUtility.ifEmpty(environmentDataCollectionForm.getSchemeId())) {
			environmentEDSMasterBean.setScheme_id(Integer.parseInt(environmentDataCollectionForm.getSchemeId()));
		}
		
		//EDS ID
		//Schemename
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getEdsId())) {
					environmentEDSMasterBean.setEds_id(Integer.parseInt(environmentDataCollectionForm.getEdsId()));
				}
		
		//createdByUser
			environmentEDSMasterBean.setCrt_by_user(parseInt);
		
		
		//ActiveFlag
			environmentEDSMasterBean.setActive_flag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
		
		
		
		
		
		return environmentEDSMasterBean;
	}

	@Override
	public List<EnvironmentDataCollectionForm> getdataByPagination( String searchParameter, int clickedColumn, String clickedColumnDir) {
		
		List<EnvironmentEDSMaster> edsMasterBean = null;
		List<EnvironmentDataCollectionForm> edsMasterForm = null;
		
		
		try {

			edsMasterBean = environmentDataCollectionDao.getLabMasterByPagination(searchParameter, clickedColumn, clickedColumnDir);

			if (!MisUtility.ifEmpty(edsMasterBean)) {
				edsMasterForm = new ArrayList<EnvironmentDataCollectionForm>();
				for (EnvironmentEDSMaster bean : edsMasterBean) {
					EnvironmentDataCollectionForm form = new EnvironmentDataCollectionForm();
					form.setZone(String.valueOf(bean.getZone_id()));
					form.setDistrict(String.valueOf(bean.getDistrict_id()));
					form.setBlock(String.valueOf(bean.getBlock_id()));
					form.setVi(String.valueOf(bean.getGram_panchayat()));
					form.setVillageId(String.valueOf(bean.getVillage_id()));
					form.setSchemeCategory(String.valueOf(bean.getScheme_category()));
					form.setSchemeId(String.valueOf(bean.getScheme_id()));
					form.setSchemeType(String.valueOf(bean.getScheme_type()));
					form.setEdsId(String.valueOf(bean.getEds_id()));
					
					if (MisUtility.ifEmpty(bean.getBlockDetailBean())) {
						form.setBlockName(bean.getBlockDetailBean().getLocationName());
					}
					
					if (MisUtility.ifEmpty(bean.getDistrictDetailBean())) {
						form.setDistrictName(bean.getDistrictDetailBean().getLocationName());
					}
					
					if (MisUtility.ifEmpty(bean.getComboBeanDetailsSchemeype())) {
						form.setSchemeTypeName(bean.getComboBeanDetailsSchemeype().getCmb_name());
					}
					
					if (MisUtility.ifEmpty(bean.getVillageDetailBean())) {
						form.setVillageName(bean.getVillageDetailBean().getLocationName());
					}
					if (MisUtility.ifEmpty(bean.getSchemeDetailBean())) {
						form.setSchemeName(bean.getSchemeDetailBean().getSchemeName());
					}
					
					
					
					

					edsMasterForm.add(form);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edsMasterForm;
		
		
	}

	@Override
	public Integer updateMaster(EnvironmentDataCollectionForm environmentDataCollectionForm, int parseInt) throws MISException {
		EnvironmentEDSMaster environmentEDSMasterBean = null;
		int status = 0;
		try {

			if (MisUtility.ifEmpty(environmentDataCollectionForm)) {
				 environmentEDSMasterBean = populateMasterEDSEnvironment(environmentDataCollectionForm, parseInt);
				 
				 if (MisUtility.ifEmpty(environmentEDSMasterBean)) {
						status = environmentDataCollectionDao.updateMasterData(environmentEDSMasterBean);
					}
				
			}
		
	}catch (Exception e) {
		e.printStackTrace();
		log.error(e.getLocalizedMessage(), e);
		throw new MISException(e);
	}
		return status;
	}
	
	
}
