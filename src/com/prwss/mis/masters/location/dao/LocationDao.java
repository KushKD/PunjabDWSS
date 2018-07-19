package com.prwss.mis.masters.location.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.min.bean.VillageSchemeMappingBean;
import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.ComboBeanMaster;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.min.sanitation.bean.SurveyMasterBean;
import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.admin.dao.LocationDetailsBean;

public interface LocationDao {
	
	public Set<LocationBean> getLocationIds(String locationType) throws DataAccessException ;
	
	public Set<LocationBean> getChildLocationIds(String parentLocationId, String locationType) throws DataAccessException ;
	//getChildLocationId
	public List<LocationMasterDto> getChildLocationId(String parentLocationId) throws DataAccessException ;
	public List<LocationMasterDto> getChildLocationByType(String locationType) throws DataAccessException ;
	public List<LocationMasterDto> fetchDivision(String id) throws DataAccessException ;
	
	public List<LocationMasterDto> fetchDistrictByDistrictId(List<Integer> id) throws DataAccessException ;
	
	public List<LocationMasterDto> getVillage(String parentLocationId) throws DataAccessException ;
	public List<LocationMasterDto> getVillageName(List<Integer> parentLocationId) throws DataAccessException ;
	public List<LocationMasterDto> getChildLocationIds(String parentLocationId) throws DataAccessException ;
	
	public List<Integer> getDistrictIds(String divisionId) throws DataAccessException ;

	public Set<LocationBean> getChildLocationIdss(String subDivisionId) throws DataAccessException ;

	
	public List<LocationBean> getChildLocationListIds(String parentLocationId, String locationType) throws DataAccessException ;
	
	public boolean saveLocation(LocationBean locationBean) throws DataAccessException;
	public LocationBean getLocation(LocationBean locationBean) throws DataAccessException;
	
	public Set<LocationBean> getLocationIdOnTypeList(List<String> locationTypeList) throws DataAccessException;
	
	public List<LocationMasterDto> getLocationBeanOnLocationIdList(List<Integer> locationIds) throws DataAccessException;
	
	public List<LocationMasterDto> getNodalDivision(List<Integer> locationIds) throws DataAccessException;
	
	public List<LocationBean> getLocationBeanOnLocationIdList2(List<String> locationIds) throws DataAccessException;
	
	public Set<LocationBean> getChildLocationIds(String parentLocationId, List<String> locationType)
	throws DataAccessException;
	public List<LocationDetailsBean> getLocationDetails(List<Integer> locationType, Long employeId)
			throws DataAccessException;
	
	public List<LocationDetailsBean> getDistrictDetails(Long employeId,int locId)
			throws DataAccessException;
	public List<LabEmployee> getLabDetails(int empId)throws DataAccessException;
	public List<LabMasterBean> getLabName(List<Long> labEmployee)throws DataAccessException;

	public List<ComboBeanMaster> getComboDetails (int id) throws DataAccessException;
	public List<GramPanchayatMasterBean> fetchGramPanchayat (String id) throws DataAccessException;
	public List<SurveyMasterBean> fetchSurvey() throws DataAccessException;
	public List<BeneficiaryDto> fetchForwardedSurvey(String loggedInUsrId) throws DataAccessException;
	public List<LocationMasterDto> fetchBeneficiary(String villageId) throws DataAccessException;
	
	//getChildLocationId
		public List<LocationMasterDto> getChildLocationDivisionId(String parentLocationId) throws DataAccessException ;
		
		public List<LocationMasterDto> getChildLocationDivisionId1(List<Integer> parentLocationId) throws DataAccessException ;
		
		public List<LocationMasterDto> getChildLocationConstituencyId(String parentLocationId) throws DataAccessException ;
		
		public List<LocationMasterDto> getChildLocationConstituencyByType(String consType) throws DataAccessException ;
		
		public List<VillageSchemeMappingBean> getSchemes(List<Integer> parentLocationId) throws DataAccessException ;
		
		
		public List<VillageSchemeMappingBean> getSchemeIdByVillage(String parentLocationId) throws DataAccessException ;
		
		
		public List<PMSSchemeDetailsBean> getSchemeNames(List<Integer> schemes) throws DataAccessException ;
		
		public List<VillageSchemeMappingBean> fetchVillageIdsFromSchemeId(String schemes) throws DataAccessException ;
		public List<LocationDetailsBean> getVillagesNames(List<Integer> villages) throws DataAccessException ;
		
		
		public List<VillageDetailsBean> fetchVillageIds(String blockId) throws DataAccessException;
		
		public List<VillageDetailsBean> fetchVillageIdByCons(String constituencyId) throws DataAccessException;
		
		public List<VillageDetailsBean> fetchVillageIdsBySubDivision(String subDivision) throws DataAccessException;
		public List<VillageDetailsBean> fetchVillageIdsByDivision(String division) throws DataAccessException;
		public List<VillageDetailsBean> fetchVillageIdsByConstituency(String constituency) throws DataAccessException;
		
		public List<WorkFlowMasterBean> fetchFormwardId(Integer entBy);
		public List<WorkFlowMasterBean> fetchWorkflowData(Integer entBy,String subjectId);

}
