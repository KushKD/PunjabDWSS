package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.DivisionVillageMappingBean;
import com.prwss.min.SDU.bean.DivisionVillageMappingDetalBean;
import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.form.VillageDivisionMpgForm;
import com.prwss.mis.admin.dao.LocationDetailsBean;

public interface VillageDivisionMappingDao {

	public List<DivisionWiseSummaryBean> getCategoryNameAndId(int parseInt)  throws DataAccessException;
	public List<StageComponentBean> getComponentNameAndId(Integer financialYr, Integer division, Integer category, Integer stage);
	
	public int getVillagesBasedOnStageAndCategory(String StageId , String ComponentId ,String fy, String div, String com) throws DataAccessException ;
	public List<Integer> getVillageIds (String DivisionId) throws DataAccessException;
	public List<LocationDetailsBean> villageDetails (List<Integer> idLocations) throws DataAccessException ;
	public Boolean getDivVillageId(DivisionVillageMappingBean divisionVillageMapping) throws DataAccessException;
	public List<DivisionVillageMappingBean> getDivisionVillageId(VillageDivisionMpgForm form) throws DataAccessException ;
	
	public int getDivVillageIdValue(VillageDivisionMpgForm form) throws DataAccessException;
	public Boolean saveDetailDivisonVillage (List<DivisionVillageMappingDetalBean> bean) throws DataAccessException;
	
}
