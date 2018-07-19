package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.bean.StageComponetDto;
import com.prwss.min.SDU.bean.StageDetailBean;
import com.prwss.min.SDU.form.StageComponentMpgForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public interface StageComponentMappingDao {
	
	public List<String> getAllLocationIds(String UserID) throws DataAccessException;
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> idLocations) throws DataAccessException;
	public List<StageComponetDto> getVillagesAndCategory(StageComponentMpgForm form) throws DataAccessException;
	
	//Get Outline Plan It (LIST)
	public Integer getOutlineIds (String year, String district, int category) throws DataAccessException;
	
	//Get Stage Detail
	public List<StageDetailBean> getStageNameAndId() throws DataAccessException;
	
	//Get Components
	public List<DivisionWiseSummaryBean> getComponets (String year_id, String name_id) throws DataAccessException;
	public boolean saveData(List<StageComponentBean> stageComponentMappingBean) throws DataAccessException;
	
	//Get Village Numbers
	public Integer getVillages (int categoryID) throws DataAccessException;

}
