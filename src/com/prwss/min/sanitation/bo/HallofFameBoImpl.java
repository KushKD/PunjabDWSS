/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.ObjectComparator;
import com.prwss.min.sanitation.bean.HallofFameBean;
import com.prwss.min.sanitation.dao.HallofFameDao;
import com.prwss.min.sanitation.form.HallofFameForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class HallofFameBoImpl extends AbstractPaginationMaster<HallofFameForm> implements HallofFameBo<HallofFameForm> {

	private Logger log = Logger.getLogger(HallofFameBoImpl.class);

	private HallofFameDao hallofFameDao;
	
	public HallofFameDao getHallofFameDao() {
		return hallofFameDao;
	}

	public void setHallofFameDao(HallofFameDao hallofFameDao) {
		this.hallofFameDao = hallofFameDao;
	}

	

	
	@Override
	public List<HallofFameForm> getHallofFameByPagination() {
		List<HallofFameBean> hallofFameBean=null;
		List<HallofFameForm> hallofFameForm=null;

		try{
			
			hallofFameBean=hallofFameDao.getHallofFameByPagination();
			
			if(!MisUtility.ifEmpty(hallofFameBean)){
				hallofFameForm=new ArrayList<HallofFameForm>();
				for(HallofFameBean bean:hallofFameBean){
					
					HallofFameForm form=new HallofFameForm();
					
					form.setActivityId(String.valueOf(bean.getActivityId()));
					
					form.setNameofActivity(bean.getNameofActivity());
					
					form.setType(String.valueOf(bean.getType()));
					
					form.setStatus(String.valueOf(bean.getStatus()));
					
					form.setLeadBy(bean.getLeadBy());
					
					form.setDescription(bean.getDescription());
					
					hallofFameForm.add(form);	
					}
				
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return hallofFameForm;
	}
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveHallofFameDetails(HallofFameForm hallofFameForm) throws MISException {

		HallofFameBean entryBean = null;
		Boolean status = false;
		try {

			entryBean = populateHallofFame(hallofFameForm);
			if (MisUtility.ifEmpty(entryBean)) {
				status = hallofFameDao.saveHallofFameDetails(entryBean);
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return status;
	}

/*	@Override
	public List<BeneficiaryEntryBean> validateBeneficiaryDetails(BeneficiaryForm beneficiaryForm) throws MISException {
		List<BeneficiaryEntryBean> beneficiaryEntryBean = null;
		try {
			if (MisUtility.ifEmpty(beneficiaryForm)) {
				beneficiaryEntryBean = beneficiaryDao.getBeneficiaryDetails(beneficiaryForm);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}

		return beneficiaryEntryBean;
	}
*/
	private HallofFameBean populateHallofFame(HallofFameForm hallofFameForm) {

		HallofFameBean entryBean = new HallofFameBean();

		try {
			
			if (MisUtility.ifEmpty(hallofFameForm.getActivityId())) {
				entryBean.setActivityId(Integer.parseInt(hallofFameForm.getActivityId()));
			}

			entryBean.setNameofActivity(hallofFameForm.getNameofActivity());

			if (MisUtility.ifEmpty(hallofFameForm.getType())) {
				entryBean.setType(Integer.parseInt(hallofFameForm.getType()));
			}
			
			entryBean.setLeadBy(hallofFameForm.getLeadBy());
			
			entryBean.setDescription(hallofFameForm.getDescription());

			if (MisUtility.ifEmpty(hallofFameForm.getAttachment()))
				entryBean.setAttachment(hallofFameForm.getAttachment().getFileData());

			if (MisUtility.ifEmpty(hallofFameForm.getAttachment()))
				entryBean.setAttachmentName(hallofFameForm.getAttachment().getFileName());

			entryBean.setCreatedByUser(Integer.parseInt(String.valueOf(hallofFameForm.getCreatedByUser())));

			entryBean.setStatus(Integer.parseInt(MISConstants.ONE));

		} catch (Exception e) {
					e.printStackTrace();
		}

		return entryBean;

	}

	/*@Override
	public List<BeneficiaryForm> populateBeneficiaryDetails(ViewRegistrationsForm viewRegistrationForm,String searchString,int clickedColumnNo,String colOrder)
			throws MISException {

		List<BeneficiaryDto> beneficiaryDtos = null;
		List<BeneficiaryForm> beneficiaryForm = null;
		try {
			beneficiaryDtos = beneficiaryDao.populateBeneficiaryDetails(viewRegistrationForm,searchString,clickedColumnNo,colOrder);
			if (!MisUtility.ifEmpty(beneficiaryDtos)) {
				beneficiaryForm = populateBeneficiaryForm(beneficiaryDtos);
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			e.printStackTrace();
			throw new MISException(e);
		}
		return beneficiaryForm;
	}*/

	/*private List<BeneficiaryForm> populateBeneficiaryForm(List<BeneficiaryDto> beneficiaryDtos) throws MISException {
		List<BeneficiaryForm> beneficiaryForms = null;
		try {
			beneficiaryForms = new ArrayList<BeneficiaryForm>();
			for (BeneficiaryDto dto : beneficiaryDtos) {
				BeneficiaryForm form = new BeneficiaryForm();
				form.setPersonName(dto.getName());
				form.setFatherSpouseName(dto.getFatherSpouseName());
				form.setAdharNumber(String.valueOf(dto.getAadharNo()));
				form.setReligion(dto.getReligion());
				form.setBeneficiaryId(String.valueOf(dto.getBeneficieryId()));
				beneficiaryForms.add(form);
			}
		} catch (Exception e) {
			throw new MISException(e);
		}
		return beneficiaryForms;
	}*/

	
	
	
	
	
	
	/*@Override
	public List<BeneficiaryForm> getListBasedOnSearchParameter(String searchParameter, List<BeneficiaryForm> formList)throws MISException {
	
		List<BeneficiaryForm> beneficiaryFormsLst=null;
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				beneficiaryFormsLst = new ArrayList<BeneficiaryForm>();
				searchParameter = searchParameter.toUpperCase();
				for (BeneficiaryForm masterForm : formList) {
					if (masterForm.getPersonName().toUpperCase().indexOf(searchParameter) != -1){
						beneficiaryFormsLst.add(masterForm);
					}
				if(masterForm.getFatherSpouseName().toUpperCase().indexOf(searchParameter) != -1){
					beneficiaryFormsLst.add(masterForm);
					}
				if( masterForm.getAdharNumber().toUpperCase().indexOf(searchParameter) != -1){
					beneficiaryFormsLst.add(masterForm);
	
					}
				if( masterForm.getReligion().toUpperCase().indexOf(searchParameter) != -1){
					beneficiaryFormsLst.add(masterForm);
	
					}

				}
				formList = beneficiaryFormsLst;
				beneficiaryFormsLst = null;
			}
		} catch (Exception e) {
			throw new MISException(e);
		}

		return formList;
	}*/

	/*@Override
	public List<BeneficiaryForm> getListBasedOnColumnSorting(List<BeneficiaryForm> formList, Integer sortingColumn,
			String sortingOrder, Object t)throws MISException {
		
		try{
			if(sortingColumn==1){
				Collections.sort(formList, new ObjectComparator<BeneficiaryForm>("personName", sortingOrder, t.getClass()));
			}if(sortingColumn==2){
				Collections.sort(formList, new ObjectComparator<BeneficiaryForm>("fatherSpouseName", sortingOrder,t.getClass()));
			}if(sortingColumn==3){
				Collections.sort(formList, new ObjectComparator<BeneficiaryForm>("adharNumber", sortingOrder, t.getClass()));
			}if(sortingColumn==4){
				Collections.sort(formList, new ObjectComparator<BeneficiaryForm>("religion", sortingOrder, t.getClass()));
			}
			
			}catch(Exception e){
				throw new MISException(e);
			}
			return formList;
	}*/
}
