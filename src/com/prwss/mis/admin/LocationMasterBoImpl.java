/**
 * 
 */
package com.prwss.mis.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.ObjectComparator;
import com.prwss.min.quality.struts.LocationMasterForm;
import com.prwss.mis.admin.dao.LocationDetailsBean;
import com.prwss.mis.admin.dao.LocationMasterBean;
import com.prwss.mis.admin.dao.LocationMasterDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author bhsingh
 *
 */
public class LocationMasterBoImpl extends AbstractPaginationMaster<LocationMasterForm>
		implements LocationMasterBo<LocationMasterForm> {

	private Logger log = Logger.getLogger(LocationMasterBoImpl.class);

	LocationMasterDao locationMasterDao;

	/**
	 * @param locationMasterDao
	 *            the locationMasterDao to set
	 */
	public void setLocationMasterDao(LocationMasterDao locationMasterDao) {
		this.locationMasterDao = locationMasterDao;
	}

	@Override
	public Boolean saveLocationMaster(LocationMasterForm locationMasterForm) throws MISException {
		LocationDetailsBean locationDetailsBean = null;
		LocationMasterBean locationMasterBean = null;

		boolean status = false;
		try {
			
			locationMasterForm=findLocationMasterForm(locationMasterForm);
			List<LocationDetailsBean> locationDetailsBeans=locationMasterDao.getLocation(locationMasterForm);
			if(!MisUtility.ifEmpty(locationDetailsBeans)){
				throw new MISException(MISExceptionCodes.MIS012,"Entry Already Exist for Location("+locationMasterForm.getLocationName()+")");				
			}
			
			List<LocationDetailsBean> masterBean=locationMasterDao.findMasterLocation(locationMasterForm);
			
			if(!MisUtility.ifEmpty(masterBean)){
				for(LocationDetailsBean bean:masterBean){
					bean.getLocationMasterBean().setActiveField(Integer.parseInt(MISConstants.INACTIVE_STATUS));
					status=locationMasterDao.updateMasterLocation(bean);
				}
			}	
				
			locationMasterBean = populateLocationMaster(locationMasterForm);
			
			if (MisUtility.ifEmpty(locationMasterBean)) {
				System.out.println("locationMasterBean----------"+locationMasterBean.toString());
				status=locationMasterDao.saveMasterLocation(locationMasterBean);
			}
			if(status){
				locationDetailsBean=new LocationDetailsBean();
				locationDetailsBean.setLocationMasterBean(locationMasterBean);
				locationDetailsBean.setLocationName(locationMasterForm.getLocationName());
				status=locationMasterDao.saveDetailsLocation(locationDetailsBean);
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public List<LocationMasterForm> getLocationMasterByPagination() throws MISException {
		LocationDetailsBean locationMasterBean = null;
		List<LocationMasterForm> locationMasterFormLst = null;
		try {
				locationMasterFormLst = locationMasterDao.getLocationMasterByPagination();
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return locationMasterFormLst;
	}
	

	private LocationMasterBean populateLocationMaster(LocationMasterForm locationMasterForm) {
		
		LocationMasterBean locationMasterBean=null;
		
		try {
			if (MisUtility.ifEmpty(locationMasterForm)) {
				locationMasterBean = new LocationMasterBean();
				//locationDetailBean.setLocationName(locationMasterForm.getLocationName());
				locationMasterBean.setCreatedByUSer(Long.parseLong(String.valueOf(locationMasterForm.getEmpId())));
				locationMasterBean.setLocationTypeId(Integer.parseInt(locationMasterForm.getLocationType()));
				
				if(!MisUtility.ifEmpty(locationMasterForm.getParentLocation())){
					locationMasterBean.setParentLocation(Integer.parseInt("-1"));
				}else{
					
					//locationMasterBean.setParentLocation(Integer.parseInt(locationMasterForm.getParentLocation()));
					
					locationMasterBean.setParentLocation(Integer.parseInt(locationMasterForm.getParentLocation()));

				}
				locationMasterBean.setActiveField(Integer.parseInt(locationMasterForm.getStatus()));
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

	/*private List<LocationMasterForm> getLocationMasterForm(List<LocationMasterForm> locationMasterBeans) {

		List<LocationMasterForm> locationMasterForm = new ArrayList<LocationMasterForm>();

		for (LocationMasterForm locationMasterBean : locationMasterBeans) {

			LocationMasterForm locationForm = new LocationMasterForm();
			locationForm.setLocationName(locationMasterBean.getLocationName());
			locationForm.setLocationType(locationMasterBean.getLocationType());
			locationForm.setParentLocation(locationMasterBean.getParentLocation());
			locationForm.setStatus(locationMasterBean.getStatus());
			locationMasterForm.add(locationForm);
		}
		return locationMasterForm;
	}*/

	@Override
	public List<LocationMasterForm> getListBasedOnSearchParameter(String searchParameter,
			List<LocationMasterForm> formList) {
		List<LocationMasterForm> locationMasterList=null;
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				locationMasterList = new ArrayList<LocationMasterForm>();
				searchParameter = searchParameter.toUpperCase();
				for (LocationMasterForm masterForm : formList) {
					
					if (masterForm.getLocationName().toUpperCase().indexOf(searchParameter) != -1){
						locationMasterList.add(masterForm);
					}
					if(masterForm.getLocationType().toUpperCase().indexOf(searchParameter) != -1){
						locationMasterList.add(masterForm);
					}
						if(masterForm.getStatus().toUpperCase().indexOf(searchParameter) != -1){
							 locationMasterList.add(masterForm);
					}
					 if(MisUtility.ifEmpty(masterForm.getParentLocation())){
						 if( masterForm.getParentLocation().toUpperCase().indexOf(searchParameter) != -1){
							 locationMasterList.add(masterForm);
					}
				}
			}
		} 
		}catch (Exception e) {

		}
		return locationMasterList;
	}
	
	public List<LocationMasterForm> getListBasedOnColumnSorting(List<LocationMasterForm> formList,Integer sortingColumn,String sortingOrder,Object t) {
		
		try{
		if(sortingColumn==1){
			Collections.sort(formList, new ObjectComparator<LocationMasterForm>("locationName", sortingOrder, t.getClass()));
		}if(sortingColumn==2){
			Collections.sort(formList, new ObjectComparator<LocationMasterForm>("locationType", sortingOrder,t.getClass()));
		}if(sortingColumn==3){
			Collections.sort(formList, new ObjectComparator<LocationMasterForm>("parentLocation", sortingOrder, t.getClass()));
		}if(sortingColumn==4){
			Collections.sort(formList, new ObjectComparator<LocationMasterForm>("status", sortingOrder, t.getClass()));
		}
		if(sortingColumn==5){
			Collections.sort(formList, new ObjectComparator<LocationMasterForm>("startDate", sortingOrder, t.getClass()));
		}
		if(sortingColumn==6){
			Collections.sort(formList, new ObjectComparator<LocationMasterForm>("endDate", sortingOrder, t.getClass()));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return formList;
	}

	private LocationMasterForm findLocationMasterForm(LocationMasterForm locationMasterForm){
		
		try{
			if(MisUtility.ifEmpty(locationMasterForm.getLocationType())){
			String[] locType=locationMasterForm.getLocationType().split("-");
			String locationTypeId=locType[0].substring(locType[0].indexOf("(")+1,locType[0].indexOf(")"));
			locationMasterForm.setLocationType(locationTypeId);
			}
			if(MisUtility.ifEmpty(locationMasterForm.getParentLocation())){
			String[] parentLocation=locationMasterForm.getParentLocation().split("-");
			String hhhh=parentLocation[0];
			String hhhhzxz=parentLocation[1];
			
			int dd=parentLocation[1].indexOf("(");
			int hh=parentLocation[1].indexOf(")");
			String parentId=parentLocation[1].substring(parentLocation[1].indexOf("(")+1,parentLocation[1].indexOf(")"));
			locationMasterForm.setParentLocation(parentId);

			}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return locationMasterForm;
	}

}
