package com.prwss.mis.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.ObjectComparator;
import com.prwss.min.quality.struts.LocationTypeForm;
import com.prwss.mis.admin.dao.LocationTypeBean;
import com.prwss.mis.admin.dao.LocationTypeDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LocationTypeBoImpl extends AbstractPaginationMaster<LocationTypeForm> implements LocationTypeBo<LocationTypeForm>{

	private Logger log = Logger.getLogger(LocationMasterBoImpl.class);

	LocationTypeDao locationTypeDao;

	

	/**
	 * @return the locationTypeDao
	 */
	public LocationTypeDao getLocationTypeDao() {
		return locationTypeDao;
	}

	/**
	 * @param locationTypeDao the locationTypeDao to set
	 */
	public void setLocationTypeDao(LocationTypeDao locationTypeDao) {
		this.locationTypeDao = locationTypeDao;
	}

	@Override
	public Boolean saveLocationMaster(LocationTypeForm locationMasterForm) throws MISException {
		LocationTypeBean locationTypeBean = null;
		boolean status = false;
		List<LocationTypeBean> locationBeanLst;
		try {
			
			locationTypeBean = populateLocationMaster(locationMasterForm);
			
			if(MisUtility.ifEmpty(locationMasterForm.getLocationTypeId())){
			locationBeanLst=locationTypeDao.getLocationType(locationMasterForm);
			if(!MisUtility.ifEmpty(locationBeanLst)){
				for(LocationTypeBean bean:locationBeanLst){
					System.out.println("inside bo=----"+bean.toString());
					bean.setActiveFlag(Integer.parseInt(MISConstants.INACTIVE_STATUS));
					status=locationTypeDao.updateMasterLocationType(bean);
				}
			}
			}
			if (MisUtility.ifEmpty(locationTypeBean)) {
				status = locationTypeDao.saveMasterLocation(locationTypeBean);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public List<LocationTypeForm> getLocationMasterByPagination() throws MISException {
		List<LocationTypeBean> locationMasterBeans;
		LocationTypeBean locationMasterBean = null;
		List<LocationTypeForm> locationMasterFormLst = null;
		try {
/*			locationMasterBean = populateLocationMaster(locationMasterForm);
*/			System.out.println("hiii");
				locationMasterBeans = locationTypeDao.getLocationMasterByPagination();

				if (!MisUtility.ifEmpty(locationMasterBeans)) {
					locationMasterFormLst = getLocationMasterForm(locationMasterBeans);
				}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return locationMasterFormLst;
	}

	private LocationTypeBean populateLocationMaster(LocationTypeForm locationMasterForm) {
		LocationTypeBean locationMasterBean = null;
		try {
			
			if (MisUtility.ifEmpty(locationMasterForm)) {
				locationMasterBean = new LocationTypeBean();
				locationMasterBean.setLocationName(locationMasterForm.getLocationType());
				locationMasterBean.setActiveFlag(Integer.parseInt(locationMasterForm.getStatus()));
				locationMasterBean.setCreatedByUSer(locationMasterForm.getEmpId());
				if(MisUtility.ifEmpty(locationMasterForm.getStartDate())){
					locationMasterBean.setStartDate(MisUtility.convertStringSqlDate(locationMasterForm.getStartDate()));
				}
				if(MisUtility.ifEmpty(locationMasterForm.getEndDate())){
					locationMasterBean.setEndDate(MisUtility.convertStringSqlDate(locationMasterForm.getEndDate()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locationMasterBean;
	}

	private List<LocationTypeForm> getLocationMasterForm(List<LocationTypeBean> locationMasterBeans) {

		List<LocationTypeForm> locationMasterForm = new ArrayList<LocationTypeForm>();

		for (LocationTypeBean locationMasterBean : locationMasterBeans) {

			LocationTypeForm locationForm = new LocationTypeForm();
			locationForm.setLocationType(locationMasterBean.getLocationName());
			locationForm.setStatus(String.valueOf(locationMasterBean.getActiveFlag()));
			locationForm.setStartDate(MisUtility.convertDateString(locationMasterBean.getStartDate()));
			locationForm.setEndDate(MisUtility.convertDateString(locationMasterBean.getEndDate()));
			locationForm.setLocationTypeId(String.valueOf(locationMasterBean.getLocationTypeId()));
			locationMasterForm.add(locationForm);
		}
		return locationMasterForm;
	}

	@Override
	public List<LocationTypeForm> getListBasedOnSearchParameter(String searchParameter,
			List<LocationTypeForm> formList) {
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				List<LocationTypeForm> locationMasterList = new ArrayList<LocationTypeForm>();
				searchParameter = searchParameter.toUpperCase();
				for (LocationTypeForm masterForm : formList) {
					if (masterForm.getLocationType().toUpperCase().indexOf(searchParameter) != -1
							|| masterForm.getStatus().toUpperCase().indexOf(searchParameter) != -1) {

						locationMasterList.add(masterForm);
					}
				}
				formList = locationMasterList;
				locationMasterList = null;
			}
		} catch (Exception e) {

		}
		return formList;
	}

	

	@Override
	public List<LocationTypeForm> getListBasedOnColumnSorting(List<LocationTypeForm> formList, Integer sortingColumn,
			String sortingOrder, Object t) {
		try{
		if(sortingColumn==1){
			Collections.sort(formList, new ObjectComparator<LocationTypeForm>("locationType", sortingOrder, t.getClass()));
		}if(sortingColumn==2){
			Collections.sort(formList, new ObjectComparator<LocationTypeForm>("status", sortingOrder,t.getClass()));
		}if(sortingColumn==3){
			Collections.sort(formList, new ObjectComparator<LocationTypeForm>("startDate", sortingOrder, t.getClass()));
		}if(sortingColumn==4){
			Collections.sort(formList, new ObjectComparator<LocationTypeForm>("endDate", sortingOrder, t.getClass()));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return formList;
	}



}
