package com.prwss.min.environment.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvPondBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvWaterDisposableBean;
import com.prwss.min.environment.bean.EnvironmentBaselineWaterLoggingBean;
import com.prwss.min.environment.bean.EnvironmentEDSMaster;
import com.prwss.min.environment.dao.EnvironmentBaselineMasterDao;
import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.min.environment.struts.BaselineEnvironment;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class EnvironmentBaselineMasterBoImpl implements
		EnvironmentBaselineMasterBo {

	private Logger log = Logger.getLogger(EnvironmentBaselineMasterBoImpl.class);
	
	private EnvironmentBaselineMasterDao environmentBaselinedao;
	
	

	public EnvironmentBaselineMasterDao getEnvironmentBaselinedao() {
		return environmentBaselinedao;
	}

	public void setEnvironmentBaselinedao(
			EnvironmentBaselineMasterDao environmentBaselinedao) {
		this.environmentBaselinedao = environmentBaselinedao;
	}

	@Override
	@Transactional
	public int saveMaster(
			EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm,
			int parseInt) throws MISException, DataAccessException {
		EnvironmentBaselineBean baselineBean = null;
		List<EnvironmentBaselineEnvPondBean>  baselineEnvPondBean = null; 
		List<EnvironmentBaselineEnvWaterDisposableBean> baselineEnvWDBean = null;
		EnvironmentBaselineWaterLoggingBean environmentWaterLoggedbean = null;
		int status = 0;
		
		try {

			if (MisUtility.ifEmpty(baseLineEnvironmentForm)) {
				baselineBean = populateMasterEDSEnvironment(
						baseLineEnvironmentForm, parseInt);

				if (MisUtility.ifEmpty(baselineBean)) {
					status = environmentBaselinedao.saveMasterDataBaseline(baselineBean);
					
					
					//Save Data Table 1
					if(MisUtility.ifEmpty(status)){
						//populate and Save the other table  EnvironmentBaselineEnvPondBean
						baselineEnvPondBean = new ArrayList<EnvironmentBaselineEnvPondBean>();
						baselineEnvPondBean = populatePondData(baseLineEnvironmentForm,status,parseInt);
						environmentBaselinedao .saveMasterDataBaselinePond(baselineEnvPondBean);
						
						//Save Data Table 2
						baselineEnvWDBean = new ArrayList<EnvironmentBaselineEnvWaterDisposableBean>();
						baselineEnvWDBean = populateWD_Data(baseLineEnvironmentForm,status,parseInt);
						environmentBaselinedao .saveMasterDataBaselineWD(baselineEnvWDBean); 
						
						//Save Data Table 3
						environmentWaterLoggedbean = new EnvironmentBaselineWaterLoggingBean();
						environmentWaterLoggedbean = populateWaterLoggedBean(baseLineEnvironmentForm,status,parseInt);
						environmentBaselinedao .saveMasterDataBaselineWaterLogged(environmentWaterLoggedbean); 
						
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


	
	
	private EnvironmentBaselineWaterLoggingBean populateWaterLoggedBean( EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm, int status, int parseInt) {


		EnvironmentBaselineWaterLoggingBean waterLoggedBean = new EnvironmentBaselineWaterLoggingBean();
		
		
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getAreaUnderWaterLogging())) {
			waterLoggedBean.setArea_wl(baseLineEnvironmentForm.getAreaUnderWaterLogging());
		}
		
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getNameOfAreas())) {
			waterLoggedBean.setWl_area_name(baseLineEnvironmentForm.getNameOfAreas());
		}
		
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getContamination())) {
			waterLoggedBean.setContamination_wl(baseLineEnvironmentForm.getContamination());
		}
		
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getPeriodOfWaterLogging())) {
			waterLoggedBean.setPeriod_wl(baseLineEnvironmentForm.getPeriodOfWaterLogging());
		}
		
		//Population
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getPopulationEffected())) {
			waterLoggedBean.setPopulation_effctd(Integer
					.parseInt(baseLineEnvironmentForm.getPopulationEffected()));
		}
		
		
		//Created By and eds env id
		waterLoggedBean.setEds_bsln_env_id(status);
		waterLoggedBean.setCrt_by_user(parseInt);
		
		return waterLoggedBean;
		
	}

	private List<EnvironmentBaselineEnvWaterDisposableBean> populateWD_Data( EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm, int status, int parseInt) {
		EnvironmentBaselineEnvWaterDisposableBean bean = null;
		List<EnvironmentBaselineEnvWaterDisposableBean> beans = null;
		try {

			if (MisUtility.ifEmpty(baseLineEnvironmentForm.getSolidWasteDisposable())) {
				beans = new ArrayList<EnvironmentBaselineEnvWaterDisposableBean>();
			
				for (String assignedTo : baseLineEnvironmentForm.getIfPondCurrentUse()) {
					bean = new EnvironmentBaselineEnvWaterDisposableBean();
					bean.setWaste_dis_id(Integer.parseInt(assignedTo));
					bean.setEds_bsln_env_id(status);
					bean.setCrt_by_user(parseInt);
					beans.add(bean);
				}
			}
			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return beans;
	}
	
	

	private List<EnvironmentBaselineEnvPondBean> populatePondData(EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm, int status, int parseInt) {
		
		EnvironmentBaselineEnvPondBean bean = null;
		List<EnvironmentBaselineEnvPondBean> beans = null;
		try {

			if (MisUtility.ifEmpty(baseLineEnvironmentForm.getIfPondCurrentUse())) {
				beans = new ArrayList<EnvironmentBaselineEnvPondBean>();
			
				for (String assignedTo : baseLineEnvironmentForm.getIfPondCurrentUse()) {
					bean = new EnvironmentBaselineEnvPondBean();
					bean.setUsage_id(Integer.parseInt(assignedTo));
					bean.setEds_bsln_env_id(status);
					bean.setCrt_by_user(parseInt);
					beans.add(bean);
				}
			}
			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return beans;
	}

	private EnvironmentBaselineBean populateMasterEDSEnvironment(
			EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm,
			int parseInt) {

		// TODO Auto-generated method stub
		EnvironmentBaselineBean baselineBean = new EnvironmentBaselineBean();

		// topography_id
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getTopography())) {
			baselineBean.setTopography_id(Integer
					.parseInt(baseLineEnvironmentForm.getTopography()));
		}

		// soil_type
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getSoil())) {
			baselineBean.setSoil_type(Integer.parseInt(baseLineEnvironmentForm
					.getSoil()));
		}

		// rain_fall
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getRainfall())) {
			baselineBean.setRain_fall(Integer.parseInt(baseLineEnvironmentForm
					.getRainfall()));
		}

		// min_temp
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getTemperatureMin())) {
			baselineBean.setMin_temp(Integer.parseInt(baseLineEnvironmentForm
					.getTemperatureMin()));
		}

		// max_temp
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getTemperatueMax())) {
			baselineBean.setMax_temp(Integer.parseInt(baseLineEnvironmentForm
					.getMaximumWidth()));
		}

		// land_slope
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getScopeOfLand())) {
			baselineBean
					.setLand_slope(baseLineEnvironmentForm.getScopeOfLand());
		}

		// wind_direction
		if (MisUtility.ifEmpty(baseLineEnvironmentForm
				.getPredominentWindDirection())) {
			baselineBean.setWind_direction(Integer.parseInt(baseLineEnvironmentForm
					.getPredominentWindDirection()));
		}

		// water_level
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getWaterTable())) {
			baselineBean.setWater_level(Integer
					.parseInt(baseLineEnvironmentForm.getWaterTable()));
		}

		// water_body
		if (MisUtility.ifEmpty(baseLineEnvironmentForm
				.getExistingWaterBodyWithinVillage())) {
			baselineBean.setWater_body(Integer.parseInt(baseLineEnvironmentForm
					.getExistingWaterBodyWithinVillage()));
		}

		// water_logging
		if (MisUtility.ifEmpty(baseLineEnvironmentForm
				.getWaterLoggingProblemsYesNo())) {
			baselineBean.setWater_logging(Integer
					.parseInt(baseLineEnvironmentForm
							.getWaterLoggingProblemsYesNo()));
		}

		// road_min_wdth
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getMinimumWidth())) {
			baselineBean.setRoad_min_wdth(Integer
					.parseInt(baseLineEnvironmentForm.getMinimumWidth()));
		}

		// road_max_wdth
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getMaximumWidth())) {
			baselineBean.setRoad_max_wdth(Integer
					.parseInt(baseLineEnvironmentForm.getMaximumWidth()));
		}

		// road_type
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getExistingRoads())) {
			baselineBean.setRoad_type(Integer.parseInt(baseLineEnvironmentForm
					.getExistingRoads()));
		}

		// lcl_vegetation
		if (MisUtility.ifEmpty(baseLineEnvironmentForm.getLocalVegitation())) {
			baselineBean.setLcl_vegetation(Integer
					.parseInt(baseLineEnvironmentForm.getLocalVegitation()));
		}

		// createdByUser
		baselineBean.setCrt_by_user(parseInt);

		// eds_id
		baselineBean.setEds_id(Integer.parseInt(baseLineEnvironmentForm
				.getEdsId().trim()));
		
		//eds_id_baseline
		if(MisUtility.ifEmpty(baseLineEnvironmentForm.getEdsBaselineId())){
			baselineBean.setEds_bsln_env_id(Integer.parseInt(baseLineEnvironmentForm.getEdsBaselineId()));
		}

		return baselineBean;
	}

	@SuppressWarnings("null")
	@Override
	public EnvironmentDataCollectionBaseLineEnvironmentForm getEnvironmentDataSheetData(String appId) throws MISException, DataAccessException {
		List<EnvironmentBaselineBean> baseLineEnvironmentbean = null;
		EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm = null;
		if (MisUtility.ifEmpty(appId)) {
			baseLineEnvironmentbean = environmentBaselinedao.getDataBaseline(appId);
			if(!MisUtility.ifEmpty(baseLineEnvironmentbean)){
				
				
				try{
					baseLineEnvironmentForm = new EnvironmentDataCollectionBaseLineEnvironmentForm();
				for (EnvironmentBaselineBean environmentBaselineBean : baseLineEnvironmentbean) {
					//System.out.println(environmentBaselineBean.toString());
					baseLineEnvironmentForm.setTopography(String.valueOf(environmentBaselineBean.getTopography_id()));
					baseLineEnvironmentForm.setSoil(String.valueOf(environmentBaselineBean.getSoil_type()));
					baseLineEnvironmentForm.setRainfall(String.valueOf(environmentBaselineBean.getRain_fall()));
					baseLineEnvironmentForm.setTemperatureMin(String.valueOf(environmentBaselineBean.getMin_temp()));
					baseLineEnvironmentForm.setTemperatueMax(String.valueOf(environmentBaselineBean.getMax_temp()));
					baseLineEnvironmentForm.setScopeOfLand(environmentBaselineBean.getLand_slope());
					baseLineEnvironmentForm.setPredominentWindDirection(String.valueOf(environmentBaselineBean.getWind_direction()));
					baseLineEnvironmentForm.setExistingWaterBodyWithinVillage(String.valueOf(environmentBaselineBean.getWater_body()));
					baseLineEnvironmentForm.setMaximumWidth(String.valueOf(environmentBaselineBean.getRoad_max_wdth()));
					baseLineEnvironmentForm.setMinimumWidth(String.valueOf(environmentBaselineBean.getRoad_min_wdth()));
					baseLineEnvironmentForm.setExistingRoads(String.valueOf(environmentBaselineBean.getRoad_type()));
					baseLineEnvironmentForm.setLocalVegitation(String.valueOf(environmentBaselineBean.getLcl_vegetation()));
					baseLineEnvironmentForm.setWaterLoggingProblemsYesNo(String.valueOf(environmentBaselineBean.getWater_logging()));
					baseLineEnvironmentForm.setWaterTable(String.valueOf(environmentBaselineBean.getWater_level()));
					baseLineEnvironmentForm.setEdsId(String.valueOf(environmentBaselineBean.getEds_id()));
					baseLineEnvironmentForm.setEdsBaselineId(String.valueOf(environmentBaselineBean.getEds_bsln_env_id()));
				}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}
			
		return baseLineEnvironmentForm;
	}

	@Override
	public int updateMaster(
			EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm,
			int parseInt) throws MISException, DataAccessException {
		EnvironmentBaselineBean baselineBean = null;
		List<EnvironmentBaselineEnvPondBean>  baselineEnvPondBean = null; 
		List<EnvironmentBaselineEnvWaterDisposableBean> baselineEnvWDBean = null;
		EnvironmentBaselineWaterLoggingBean environmentWaterLoggedbean = null;
		int status = 0;
		int status_inner = 0;
		try {

			if (MisUtility.ifEmpty(baseLineEnvironmentForm)) {
				baselineBean = populateMasterEDSEnvironment(
						baseLineEnvironmentForm, parseInt);

				if (MisUtility.ifEmpty(baselineBean)) {
					status = environmentBaselinedao .updateMasterDataBaseline(baselineBean);
					
					
					//Save Data Table 1
					//if(MisUtility.ifEmpty(status)){
						//populate and Save the other table  EnvironmentBaselineEnvPondBean
						//baselineEnvPondBean = new ArrayList<EnvironmentBaselineEnvPondBean>();
						//baselineEnvPondBean = populatePondData(baseLineEnvironmentForm,status,parseInt);
						//environmentBaselinedao .updateMasterDataBaselinePond(baselineEnvPondBean);
						
						//Save Data Table 2
						//baselineEnvWDBean = new ArrayList<EnvironmentBaselineEnvWaterDisposableBean>();
						//baselineEnvWDBean = populateWD_Data(baseLineEnvironmentForm,status,parseInt);
						//environmentBaselinedao .saveMasterDataBaselineWD(baselineEnvWDBean); 
						
						//Save Data Table 3
						//environmentWaterLoggedbean = new EnvironmentBaselineWaterLoggingBean();
						//environmentWaterLoggedbean = populateWaterLoggedBean(baseLineEnvironmentForm,status,parseInt);
						//environmentBaselinedao .saveMasterDataBaselineWaterLogged(environmentWaterLoggedbean); 
					//}
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
