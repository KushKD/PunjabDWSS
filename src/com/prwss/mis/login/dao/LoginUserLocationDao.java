package com.prwss.mis.login.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.dao.LocationTypeBean;

public interface LoginUserLocationDao {
	
	public List<String> getLoginUserLocations(String userId) throws DataAccessException;
	public List<Long> getLoginUserId(String userName) throws DataAccessException;

	
	public boolean saveOrUpdateLoginUserLocations(Collection<LoginUserLocationBean> loginUserLocationBeans) throws DataAccessException;
	public List<LoginUserLocationBean> findUserLocation(LoginUserLocationBean userLocationBean,List<String> statusList) throws DataAccessException;
	public List<LocationTypeBean> getLocationTypeIds()throws DataAccessException;
	public List<LocationTypeBean> getLocationTypeDistrictIds()throws DataAccessException;

}
