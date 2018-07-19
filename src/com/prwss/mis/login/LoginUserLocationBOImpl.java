package com.prwss.mis.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.LocationMasterDto;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.admin.dao.LocationTypeBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserLocationBean;
import com.prwss.mis.login.dao.LoginUserLocationDao;
import com.prwss.mis.masters.location.dao.LocationDao;

public abstract class LoginUserLocationBOImpl implements LoginBO {
	
	private Logger log = Logger.getLogger(LoginUserLocationBOImpl.class);
	protected LoginUserLocationDao loginUserLocationDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setLoginUserLocationDao(LoginUserLocationDao loginUserLocationDao) {
		this.loginUserLocationDao = loginUserLocationDao;
	}

	
	public List<LocationMasterDto> getUserLocations(String userId) throws MISException{
		
		List<LocationMasterDto> locationBeans = null;
		List<String> loginUserLocationBeans = null;
		List<Integer> locationIds = null;
		try {
			loginUserLocationBeans = loginUserLocationDao.getLoginUserLocations(userId);
			
			if(!MisUtility.ifEmpty(loginUserLocationBeans)){
				locationIds=new ArrayList<Integer>();
				
				for(String userLocationIds:loginUserLocationBeans){
					locationIds.add(Integer.parseInt(userLocationIds));
				}
				locationBeans = locationDao.getLocationBeanOnLocationIdList(locationIds);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return locationBeans;
	}
	
public List<LocationDetailsBean> getUserLocationsByEmpId(String empId) throws MISException{
		
		List<LocationDetailsBean> locationBeans = null;

		List<Long> employeId = null;
		List<Integer> locationTypes;
		long empIds = 0;
		try {
			locationTypes=new ArrayList<Integer>();
			employeId = loginUserLocationDao.getLoginUserId(empId);
			
			List<LocationTypeBean> locationType=loginUserLocationDao.getLocationTypeIds();
			
			for(Long ll:employeId){
				empIds=ll;
			}
			if(!MisUtility.ifEmpty(locationType)){
				
			for(LocationTypeBean bean:locationType){
				locationTypes.add(bean.getLocationTypeId());
			}
			}
			if(!MisUtility.ifEmpty(empIds)){
				locationBeans = locationDao.getLocationDetails(locationTypes, empIds);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return locationBeans;
	}
	
	@Override
	public boolean saveUserLocations(String userId, Set<String> locationIds) throws MISException {
		boolean status = false;
		if(!MisUtility.ifEmpty(locationIds)){
			List<LoginUserLocationBean> loginUserLocationBeans = new ArrayList<LoginUserLocationBean>();
			LoginUserLocationBean loginUserLocationBean = null;
			for (String locationId : locationIds) {
				loginUserLocationBean = new LoginUserLocationBean();
				loginUserLocationBean.setLocationId(locationId);
				loginUserLocationBean.setUserId(userId);
				//loginUserLocationBean.setRowActive(MISConstants.MIS_USER_LOCATION_ACTIVE);
				loginUserLocationBeans.add(loginUserLocationBean);
				
			}
			status = loginUserLocationDao.saveOrUpdateLoginUserLocations(loginUserLocationBeans);
		}
		return status;
	}

	@Override
	public List<LocationDetailsBean> getUserDistrictLocations(String empId) throws MISException {
		
		List<LocationDetailsBean> locationBeans = null;
		List<Long> employeId=null;
		List<LocationTypeBean> locationTypeBean=null;
		long empIds = 0;
		try{
			employeId = loginUserLocationDao.getLoginUserId(empId);
			for(Long ll:employeId){
				empIds=ll;
			}
			locationTypeBean=loginUserLocationDao.getLocationTypeDistrictIds();
			
			if(!MisUtility.ifEmpty(locationTypeBean)){
			for(LocationTypeBean bean:locationTypeBean){
				locationBeans = locationDao.getDistrictDetails(empIds,bean.getLocationTypeId());
			}
			}

			
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
	
	return locationBeans;
}
/*	public List<LabMasterBean> getLabDetails() throws MISException{
		List<LabMasterBean> labMasterBeans=null;
		try{
			labMasterBeans=locationDao.getLabDetails();
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return labMasterBeans;
	}*/

 
}
