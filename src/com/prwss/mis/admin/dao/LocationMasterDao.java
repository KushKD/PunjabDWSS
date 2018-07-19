package com.prwss.mis.admin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.quality.struts.LocationMasterForm;

public interface LocationMasterDao {

	public boolean saveMasterLocation(LocationMasterBean locationMasterBean) throws DataAccessException;
	public boolean saveDetailsLocation(LocationDetailsBean locationMasterBean) throws DataAccessException;

	public List<LocationMasterForm> getLocationMasterByPagination()throws DataAccessException;
	public List<LocationTypeBean> getLocationType(LocationTypeBean locationTypeBean)throws DataAccessException;

	public List<LocationDetailsBean> getParentLocation(int  locationTypeId)throws DataAccessException;
	
	public  List<LocationDetailsBean> findMasterLocation(LocationMasterForm locationMasterForm)throws DataAccessException;
	public boolean updateMasterLocation(LocationDetailsBean locationMasterForm)throws DataAccessException;
	
	public List<LocationDetailsBean> getLocation(LocationMasterForm locationMasterForm)throws DataAccessException; 

	public String getParentLocationName(int parentLocId) throws DataAccessException ;
}
