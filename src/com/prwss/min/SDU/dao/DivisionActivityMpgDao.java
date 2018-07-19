package com.prwss.min.SDU.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.DivisionActivityDetailMpgBean;
import com.prwss.min.SDU.bean.DivisionActivityMpgBean;
import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.min.SDU.bean.SchemeCycleAttributeDetailBean;
import com.prwss.min.SDU.bean.SchemeCycleAttributeMasterBean;
import com.prwss.min.SDU.bean.StageComponentBean;
import com.prwss.min.SDU.form.DivisionActivityMpgDto;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public interface DivisionActivityMpgDao {
	
	public List<String> getAllLocationIds(String UserID) throws DataAccessException;
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> idLocations) throws DataAccessException;
	public List<DivisionActivityMpgDto> getStageNameAndId(Integer financialYr, Integer division, Integer category) throws DataAccessException;
	public List<DivisionWiseSummaryBean> getCategoryNameAndId(Integer financialYear, Integer division) throws DataAccessException;
	public List<StageComponentBean> getComponentNameAndId(Integer financialYr, Integer division, Integer category, Integer stage) throws DataAccessException;
	public List<SchemeCycleAttributeDetailBean> getCampaignNameAndId(Collection<SchemeCycleAttributeMasterBean> schemeCycleAttributeMasterBean)throws DataAccessException;
	public List<SchemeCycleAttributeDetailBean> getSchAttributeId(Integer attributeTypeId) throws DataAccessException;
	public List<SchemeCycleAttributeDetailBean> getActivityNameandId(Integer financialYr, Integer campaign, Integer category, Integer stage, Integer parentId)throws DataAccessException;
	public Integer saveDivisionActivityDetails(DivisionActivityMpgBean divisionActivityMpgBeans) throws DataAccessException;
	public List<Integer> fetchdivActivityId(Integer financialYr, Integer division, Integer catagory, Integer component, Integer stage, Integer campaign) throws DataAccessException;
	public boolean saveDivisionActivityMpgDetails(List<DivisionActivityDetailMpgBean> divisionActivityDetailMpgBeans) throws DataAccessException;
}
