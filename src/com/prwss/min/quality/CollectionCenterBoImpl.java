/**
 * 
 */
package com.prwss.min.quality;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.CollectionCenterBean;
import com.prwss.min.dao.CollectionCenterDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class CollectionCenterBoImpl implements CollectionCenterBo{
	
	private Logger log = Logger.getLogger(CollectionCenterBoImpl.class);
	private CollectionCenterDao collectionCenterDao;
	
	

	public CollectionCenterDao getCollectionCenterDao() {
		return collectionCenterDao;
	}

	public void setCollectionCenterDao(CollectionCenterDao collectionCenterDao) {
		this.collectionCenterDao = collectionCenterDao;
	}

	@Override
	public boolean save(CollectionCenterForm collectionCenterForm,MISSessionBean misSessionBean) throws MISException {
		
		CollectionCenterBean collectionCenterBean=null;
		try{
			collectionCenterBean=populateCollectionCenterBean(collectionCenterForm,misSessionBean);
			if(MisUtility.ifEmpty(collectionCenterBean)){
				collectionCenterDao.save(collectionCenterBean);
			}
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return true;
	}
	
	private  CollectionCenterBean populateCollectionCenterBean(CollectionCenterForm collectionCenterForm,MISSessionBean misSessionBean){
		
		CollectionCenterBean collectionCenterBean=null;
		try{
			if(MisUtility.ifEmpty(collectionCenterForm)){
				collectionCenterBean=new CollectionCenterBean();
				collectionCenterBean.setPhoneNumber(Long.parseLong(collectionCenterForm.getPhoneNo()));
				collectionCenterBean.setAddress(collectionCenterForm.getAddress());
				collectionCenterBean.setName(collectionCenterForm.getName());
				collectionCenterBean.setLabId(Integer.parseInt(collectionCenterForm.getLab()));
				collectionCenterBean.setCrtByUsr(misSessionBean.getEnteredBy());
				collectionCenterBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			}
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		return collectionCenterBean;
	}

}
