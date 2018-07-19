package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.ConsolidatedPlanActivityWiseDTO;
import com.prwss.min.SDU.bean.SchemeCycleAttributeDetailBean;
import com.prwss.min.SDU.form.VillageActivityMpgForm;
import com.prwss.mis.admin.dao.LocationDetailsBean;

public interface VillageActivityMappingDao {
	
	public int getDivVillageIdValue(String category, String stage, String component) throws DataAccessException;
	public List<Integer> getVillageIds(int DivisionVillageId) throws DataAccessException;
	public List<LocationDetailsBean> getVillagesData(List<Integer> villageIds) throws DataAccessException;
   
	
	public int getDivisionActivityId(String category, String stage, String component , String DivisionId) throws DataAccessException;
	public List<Integer> getIdActivities(int DivisonActivityId) throws DataAccessException;
	public List<SchemeCycleAttributeDetailBean> getGetActivityDetails(List<Integer> activityIds) throws DataAccessException;
	
	public List<ActivityVillageMappingDetalBean> getActivityVillageId(VillageActivityMpgForm villageActivityMpgForm) throws DataAccessException;
	public int saveVillageActivityBean(ActivityVillageMappingBean activityVillageMapping) throws DataAccessException;
	public int getActivityVillageIdValue(VillageActivityMpgForm villageActivityMpgForm) throws DataAccessException;
	public boolean saveDetailActivityVillage(List<ActivityVillageMappingDetalBean> divisionVillageMappingDetailBean) throws DataAccessException;
	
	public List<ConsolidatedPlanActivityWiseDTO> getConsolidatePlanActivity(String financialYear) throws DataAccessException;
	
}
