package com.prwss.min.environment.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.form.VillageActivityMpgForm;
import com.prwss.min.SDU.form.VillageActivityMpgGrid;
import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvPondBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvWaterDisposableBean;
import com.prwss.min.environment.bean.EnvironmentBaselineWaterLoggingBean;
import com.prwss.min.environment.bean.EnvironmentWaterSchemeBean;
import com.prwss.min.environment.bean.WaterSupplyGridBean;
import com.prwss.min.environment.bean.WaterSupplyNatureQualityBean;
import com.prwss.min.environment.dao.EnvironmentWaterSupplyDao;
import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionWaterForm;
import com.prwss.min.environment.form.WaterSupplyGrid;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentWaterSupplyBoImpl implements EnvironmentWaterSupplyBo{
	
	private Logger log = Logger.getLogger(EnvironmentWaterSupplyBoImpl.class);
	
	private EnvironmentWaterSupplyDao environmentWaterSupplyDao;
	
	
	

	//EnvironmentWaterSchemeBean
	public EnvironmentWaterSupplyDao getEnvironmentWaterSupplyDao() {
		return environmentWaterSupplyDao;
	}

	public void setEnvironmentWaterSupplyDao(
			EnvironmentWaterSupplyDao environmentWaterSupplyDao) {
		this.environmentWaterSupplyDao = environmentWaterSupplyDao;
	}

	@Override
	public int saveWaterSchemeMaster( EnvironmentDataCollectionWaterForm environmentDataCollectionForm, int parseInt) throws DataAccessException, MISException {


		EnvironmentWaterSchemeBean waterSupplyBean = null;
		List<WaterSupplyNatureQualityBean>  waterSupplyNatureQualityBean = null; 
		List<WaterSupplyGridBean> waterSupplyGridBean;
		//EnvironmentBaselineWaterLoggingBean environmentWaterLoggedbean = null;
		int status = 0;
		
		
		try {

			if (MisUtility.ifEmpty(environmentDataCollectionForm)) {
				waterSupplyBean = populateMasterEDSEnvironmentWaterSupply(environmentDataCollectionForm, parseInt);
				
				if (MisUtility.ifEmpty(waterSupplyBean)) {
				//Save Data  return Staus
					status = environmentWaterSupplyDao.saveMasterDataWaterSupply(waterSupplyBean);
					
					
					//Save Data Table 1 
					if(MisUtility.ifEmpty(status)){
						
						waterSupplyNatureQualityBean = new ArrayList<WaterSupplyNatureQualityBean>();
						waterSupplyNatureQualityBean = populateWaterSupplySchemeData(environmentDataCollectionForm,status,parseInt);
						environmentWaterSupplyDao .saveWaterSupplyQualityNature(waterSupplyNatureQualityBean);
						
					}
					
					//Save Data To GRID
					waterSupplyGridBean = populateWaterSupplySchemeGridDetail(environmentDataCollectionForm, parseInt, status);
					status = environmentWaterSupplyDao.saveGridData(waterSupplyGridBean);
					
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}
	
	
	
	
	private List<WaterSupplyGridBean> populateWaterSupplySchemeGridDetail(EnvironmentDataCollectionWaterForm form, int userId,
			int status)  {
		List<WaterSupplyGridBean> waterSupplyGridBeans = null;
		WaterSupplyGridBean waterSupplyGridBean = null;

		@SuppressWarnings("unchecked")
		Collection<WaterSupplyGrid> grid = form.getWaterSupplyGrid().getAddedData();  //getVillageDivisionMpgGrid().getAddedData();

		if (!MisUtility.ifEmpty(grid)) {
			waterSupplyGridBeans = new ArrayList<WaterSupplyGridBean>();
			for (WaterSupplyGrid divisionWiseSummaryGrid : grid) {

			

				waterSupplyGridBean = new WaterSupplyGridBean();

			
				Integer parameterId = getCategoryId(divisionWiseSummaryGrid.getParameter());
				waterSupplyGridBean.setParameter_id(parameterId);
				waterSupplyGridBean.setWater_supp_sch_id(status);
				waterSupplyGridBean.setIssue(divisionWiseSummaryGrid.getIssue());
				waterSupplyGridBean.setMitigation(divisionWiseSummaryGrid.getMeasure());
				
				
				
					waterSupplyGridBean.setCrt_by_user(userId);
				
				waterSupplyGridBeans.add(waterSupplyGridBean);
			}
		}

		return waterSupplyGridBeans;
	}
	
	
	
	
	

	private List<WaterSupplyNatureQualityBean> populateWaterSupplySchemeData(EnvironmentDataCollectionWaterForm environmentDataCollectionForm, int status, int parseInt) {
		WaterSupplyNatureQualityBean bean = null;
		List<WaterSupplyNatureQualityBean> beans = null;
		try {

			if (MisUtility.ifEmpty(environmentDataCollectionForm.getNatureQualityProblem())) {
				beans = new ArrayList<WaterSupplyNatureQualityBean>();
			
				for (String assignedTo : environmentDataCollectionForm.getNatureQualityProblem()) {
					bean = new WaterSupplyNatureQualityBean();
					bean.setDisease_id(Integer.parseInt(assignedTo));
					bean.setWater_supp_sch_id(status);
					bean.setCrt_by_user(parseInt);
					beans.add(bean);
				}
			}
			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return beans;
	}

	private EnvironmentWaterSchemeBean populateMasterEDSEnvironmentWaterSupply(EnvironmentDataCollectionWaterForm environmentDataCollectionForm, int parseInt) {
		
		
		EnvironmentWaterSchemeBean waterSupplyBean = new EnvironmentWaterSchemeBean();

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getProposedWaterSupplyScheme())) {
					waterSupplyBean.setScheme_bifurcation(Integer.parseInt(environmentDataCollectionForm.getProposedWaterSupplyScheme()));
				}

				if (MisUtility.ifEmpty(environmentDataCollectionForm.getCurrentDrinkingwaterSituation())) {
					waterSupplyBean.setData_crnt_water(environmentDataCollectionForm.getCurrentDrinkingwaterSituation());
				}

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getSourceDrinkingWater())) {
					waterSupplyBean.setSrc_drink_water(Integer.parseInt(environmentDataCollectionForm.getSourceDrinkingWater()));
				}

				if (MisUtility.ifEmpty(environmentDataCollectionForm.getWaterAvailabilityLpcd())) {
					waterSupplyBean.setWater_availability(environmentDataCollectionForm.getWaterAvailabilityLpcd());
				}

				if (MisUtility.ifEmpty(environmentDataCollectionForm.getAvailabilityLandIntakeOrWTPSite())) {
					waterSupplyBean.setLand_availability(Integer.parseInt(environmentDataCollectionForm.getAvailabilityLandIntakeOrWTPSite()));
				}

				if (MisUtility.ifEmpty(environmentDataCollectionForm.getAssessedWaterQuality())) {
					waterSupplyBean.setWater_qual_access(Integer.parseInt(environmentDataCollectionForm.getAssessedWaterQuality()));
				}

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getRiskContamination())) {
					waterSupplyBean.setIndustrial_cont(Integer.parseInt(environmentDataCollectionForm.getRiskContamination()));
				}

			
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getCorrectiveActionsTaken())) {
					waterSupplyBean.setPrev_corr_measures(environmentDataCollectionForm.getCorrectiveActionsTaken());
				}

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getProvitionBoreWell())) {
					waterSupplyBean.setBorewell_ind_pak(Integer.parseInt(environmentDataCollectionForm.getProvitionBoreWell()));
				}

			
				/*if (MisUtility.ifEmpty(environmentDataCollectionForm.getProvitionBoreWellSanctioned())) {
					waterSupplyBean.setWater_logging(Integer
							.parseInt(environmentDataCollectionForm.getProvitionBoreWellSanctioned()));
				}*/

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getCanalSourceTreatment())) {
					waterSupplyBean.setCanal_source_treat(Integer.parseInt(environmentDataCollectionForm.getCanalSourceTreatment()));
				}

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getGroundwaterTechnology())) {
					waterSupplyBean.setGrwater_trear(Integer.parseInt(environmentDataCollectionForm.getGroundwaterTechnology())); 
				}

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getHasDisinfectionSystemProvided())) {
					waterSupplyBean.setDisinfection_prov(Integer.parseInt(environmentDataCollectionForm.getHasDisinfectionSystemProvided()));
				}

				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getHasDisinfectionSystemProvidedType())) {
					waterSupplyBean.setType_disinfection(Integer.parseInt(environmentDataCollectionForm.getHasDisinfectionSystemProvidedType()));
				}
				
				
				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getAirWaterNoisTesting())) {
					waterSupplyBean.setAir_nose_water_test(Integer.parseInt(environmentDataCollectionForm.getAirWaterNoisTesting()));
				}
				
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getLawnsTreesPlantation())) {
					waterSupplyBean.setLawn_tree_plant(Integer.parseInt(environmentDataCollectionForm.getLawnsTreesPlantation()));
				}
				
				

				// createdByUser
				waterSupplyBean.setCrt_by_user(parseInt);

				// eds_id
				waterSupplyBean.setEds_id(Integer.parseInt(environmentDataCollectionForm.getEds_id().trim()));
				
				
				//Id
				if (MisUtility.ifEmpty(environmentDataCollectionForm.getId())) {
				waterSupplyBean.setWater_supp_sch_id(Integer.parseInt(environmentDataCollectionForm.getId()));
				}

				return waterSupplyBean;
			
	}
	
	
	private Integer getCategoryId(String categoryName) {
		Integer catId = null;
		try {
			String categoryId = categoryName.substring(categoryName.indexOf('(') + 1, categoryName.length() - 1);

			if (MisUtility.ifEmpty(categoryId)) {
				catId = Integer.parseInt(categoryId);
			}
		} catch (Exception e) {

		}
		return catId;
	}

	@Override
	public int updateMaster(
			EnvironmentDataCollectionWaterForm environmentDataCollectionWaterForm,
			int parseInt) throws DataAccessException, MISException {
		EnvironmentWaterSchemeBean bean = null;
		int status = 0;
		int status_inner = 0;
		try {

			if (MisUtility.ifEmpty(environmentDataCollectionWaterForm)) {
				bean = populateMasterEDSEnvironmentWaterSupply(
						environmentDataCollectionWaterForm, parseInt);

				if (MisUtility.ifEmpty(bean)) {
					status = environmentWaterSupplyDao.updateMasterDataBaseline(bean);
					
				
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	
	}

	@Override
	public EnvironmentDataCollectionWaterForm getEnvironmentDataSheetData(String appId) throws DataAccessException, MISException {
		List<EnvironmentWaterSchemeBean> waterSchemebean = null;
		EnvironmentDataCollectionWaterForm waterSupplyEnvironmentForm = null;
		if (MisUtility.ifEmpty(appId)) {
			waterSchemebean = environmentWaterSupplyDao.getDataBaseline(appId);
			if(!MisUtility.ifEmpty(waterSchemebean)){
				
				
				try{
					waterSupplyEnvironmentForm = new EnvironmentDataCollectionWaterForm();
				for (EnvironmentWaterSchemeBean environmentBaselineBean : waterSchemebean) {
					System.out.println(waterSchemebean.toString());
					
					waterSupplyEnvironmentForm.setEds_id(String.valueOf(environmentBaselineBean.getEds_id()));
					
					
					waterSupplyEnvironmentForm.setProposedWaterSupplyScheme(String.valueOf(environmentBaselineBean.getScheme_bifurcation()));
					waterSupplyEnvironmentForm.setCurrentDrinkingwaterSituation(String.valueOf(environmentBaselineBean.getData_crnt_water()));
					waterSupplyEnvironmentForm.setSourceDrinkingWater(String.valueOf(environmentBaselineBean.getSrc_drink_water()));
					waterSupplyEnvironmentForm.setWaterAvailabilityLpcd(String.valueOf(environmentBaselineBean.getWater_availability()));
					waterSupplyEnvironmentForm.setAvailabilityLandIntakeOrWTPSite(String.valueOf(environmentBaselineBean.getLand_availability()));
					waterSupplyEnvironmentForm.setAssessedWaterQuality(String.valueOf(environmentBaselineBean.getWater_qual_access()));
					
					waterSupplyEnvironmentForm.setRiskContamination(String.valueOf(environmentBaselineBean.getIndustrial_cont()));
					waterSupplyEnvironmentForm.setCorrectiveActionsTaken(String.valueOf(environmentBaselineBean.getPrev_corr_measures()));
					waterSupplyEnvironmentForm.setProvitionBoreWell(String.valueOf(environmentBaselineBean.getBorewell_ind_pak()));
					waterSupplyEnvironmentForm.setCanalSourceTreatment(String.valueOf(environmentBaselineBean.getCanal_source_treat()));
					waterSupplyEnvironmentForm.setGroundwaterTechnology(String.valueOf(environmentBaselineBean.getGrwater_trear()));
					waterSupplyEnvironmentForm.setHasDisinfectionSystemProvided(String.valueOf(environmentBaselineBean.getDisinfection_prov()));
					waterSupplyEnvironmentForm.setHasDisinfectionSystemProvidedType(String.valueOf(environmentBaselineBean.getType_disinfection()));
					waterSupplyEnvironmentForm.setAirWaterNoisTesting(String.valueOf(environmentBaselineBean.getAir_nose_water_test()));
					waterSupplyEnvironmentForm.setLawnsTreesPlantation(String.valueOf(environmentBaselineBean.getLawn_tree_plant()));
					
					waterSupplyEnvironmentForm.setId(String.valueOf(environmentBaselineBean.getWater_supp_sch_id()));
				}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}
			
		return waterSupplyEnvironmentForm;
	}

}
