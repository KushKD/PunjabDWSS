package com.prwss.mis.admin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.quality.struts.LocationTypeForm;

public interface LocationTypeDao {

	public boolean saveMasterLocation(LocationTypeBean locationTypeBean) throws DataAccessException;
	public List<LocationTypeBean> getLocationMasterByPagination()throws DataAccessException;
	public List<LocationTypeBean> getLocationType(LocationTypeForm locationTypeBean)throws DataAccessException;
	public boolean updateMasterLocationType(LocationTypeBean locationTypeBean) throws DataAccessException;
	public List<LocationTypeBean> getLocationTypeBean(LocationTypeForm locationTypeForm) throws DataAccessException;

	
}
