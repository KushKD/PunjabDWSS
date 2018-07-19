/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.sanitation.bean.LatrineUsageBean;
import com.prwss.min.sanitation.dao.LatrineUsageDao;
import com.prwss.min.sanitation.form.LatrineUsageForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class LatrineUsageBoImpl extends AbstractPaginationMaster<LatrineUsageForm> implements LatrineUsageBo {

	private Logger log = Logger.getLogger(LatrineUsageBoImpl.class);

	private LatrineUsageDao latrineUsageDao;
	
	
	public LatrineUsageDao getLatrineUsageDao() {
		return latrineUsageDao;
	}

	public void setLatrineUsageDao(LatrineUsageDao latrineUsageDao) {
		this.latrineUsageDao = latrineUsageDao;
	}

	@Override
	public boolean saveLatrine(LatrineUsageForm latrineUsageForm) throws MISException {
		LatrineUsageBean latrineUsageBean = null;
		boolean status=false;
		try {

			latrineUsageBean = populateLatrine(latrineUsageForm);
			if (MisUtility.ifEmpty(latrineUsageBean)) {
				status = latrineUsageDao.saveLatrine(latrineUsageBean);
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return status;
	}

	private LatrineUsageBean populateLatrine(LatrineUsageForm latrineUsageForm) {

		LatrineUsageBean latrineUsageBean = new LatrineUsageBean();
		try {
			if (MisUtility.ifEmpty(latrineUsageForm.getDistrict()))
				latrineUsageBean.setDistrictId(Integer.parseInt(latrineUsageForm.getDistrict()));
			
			if (MisUtility.ifEmpty(latrineUsageForm.getBlock()))
				latrineUsageBean.setBlockId(Integer.parseInt(latrineUsageForm.getBlock()));
			
			if (MisUtility.ifEmpty(latrineUsageForm.getVillage()))
				latrineUsageBean.setVillageId(Integer.parseInt(latrineUsageForm.getVillage()));
			
			if (MisUtility.ifEmpty(latrineUsageForm.getGramPanchayat()))
				latrineUsageBean.setGramPanchayatId(latrineUsageForm.getGramPanchayat());
			
			if (MisUtility.ifEmpty(latrineUsageForm.getBeneficiaryId()))
				latrineUsageBean.setBeneficiaryId(Integer.parseInt(latrineUsageForm.getBeneficiaryId()));
			
			if (MisUtility.ifEmpty(latrineUsageForm.getMembersId()))
				latrineUsageBean.setMemberId(Integer.parseInt(latrineUsageForm.getMembersId()));
			
			latrineUsageBean.setCrtByUsr(Integer.parseInt(String.valueOf(latrineUsageForm.getCreatedBy())));
			latrineUsageBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));
			latrineUsageBean.setComments(latrineUsageForm.getComments());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latrineUsageBean;
	}

	@Override
	public List<LatrineUsageForm> getLatrineDetails(String searchParameter,int clickedColumn,String clickedColumnDir) throws MISException {
		
		List<LatrineUsageBean> latrineUsageBeans=null;
		List<LatrineUsageForm> latrineUsageForms=null;
		try{
			latrineUsageBeans=latrineUsageDao.getLatrineDetails(searchParameter, clickedColumn, clickedColumnDir);
			if(!MisUtility.ifEmpty(latrineUsageBeans)){
				latrineUsageForms=getLatrineForm(latrineUsageBeans);
			}
		}catch(Exception e){
			throw new MISException(e);
		}
		return latrineUsageForms;
	}
	
	public List<LatrineUsageForm> getLatrineForm(List<LatrineUsageBean> latrineUsageBeans)throws MISException{
		List<LatrineUsageForm> latrineUsageForms=null;
		try{
			latrineUsageForms=new ArrayList<LatrineUsageForm>();
			for(LatrineUsageBean latrineUsageBean:latrineUsageBeans){
				LatrineUsageForm latrineUsageForm=new LatrineUsageForm();
				latrineUsageForm.setBeneficiaryName(latrineUsageBean.getBeneficiaryEntryBean().getBeneficieryName());
				latrineUsageForm.setDistrictName(latrineUsageBean.getDistrictDetailBean().getLocationName());
				latrineUsageForm.setBlockName(latrineUsageBean.getBlockDetailBean().getLocationName());
				latrineUsageForm.setVillageName(latrineUsageBean.getLocationDetailBean().getLocationName());
				latrineUsageForms.add(latrineUsageForm);
			}
		}catch(Exception e){
			log.debug(e);
			throw new MISException(e);
		}
		return latrineUsageForms;
	}


}
