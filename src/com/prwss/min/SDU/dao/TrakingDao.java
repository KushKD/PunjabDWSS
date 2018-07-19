package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.StageDetailBean;
import com.prwss.min.SDU.BO.TrackingDto;
import com.prwss.min.SDU.form.TrackingForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public interface TrakingDao {
	
	public List<TrackingDto> gerTrackingDTO(TrackingForm form)  throws DataAccessException;
	public List<ActivityVillageMappingDetalBean> getBeansActivityDeailMapping(List<Integer> villageActivityID) throws DataAccessException;
	public Boolean saveUpdateData(List<ActivityVillageMappingDetalBean> saveBeans) throws DataAccessException;
	public List<String> getAllLocationIds(String UserID) throws DataAccessException;
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> locationId)throws DataAccessException;
	public List<StageDetailBean> getStageNameAndId() throws DataAccessException;
	//public List<String> getOutcome(List<Integer> outcomeIds) throws DataAccessException;

}
