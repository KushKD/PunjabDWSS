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
import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.bean.BeneficiaryEntryBean;
import com.prwss.min.sanitation.dao.BeneficiaryDao;
import com.prwss.min.sanitation.form.BeneficiaryForm;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class BeneficiaryBoImpl extends AbstractPaginationMaster<BeneficiaryForm>
		implements BeneficiaryBo<BeneficiaryForm> {

	private Logger log = Logger.getLogger(BeneficiaryBoImpl.class);

	private BeneficiaryDao beneficiaryDao;

	public BeneficiaryDao getBeneficiaryDao() {
		return beneficiaryDao;
	}

	public void setBeneficiaryDao(BeneficiaryDao beneficiaryDao) {
		this.beneficiaryDao = beneficiaryDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveBeneficiaryDetails(BeneficiaryForm beneficiaryForm) throws MISException {

		BeneficiaryEntryBean entryBean = null;
		Boolean status = false;
		try {

			entryBean = populateBeneficiary(beneficiaryForm);
			if (MisUtility.ifEmpty(entryBean)) {
				status = beneficiaryDao.saveBeneficiaryDetails(entryBean);
			}

		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return status;
	}

	@Override
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

	private BeneficiaryEntryBean populateBeneficiary(BeneficiaryForm beneficiaryForm) {

		BeneficiaryEntryBean entryBean = new BeneficiaryEntryBean();

		try {
			if (MisUtility.ifEmpty(beneficiaryForm.getAdharNumber()))
				entryBean.setAadhaarNumber(Long.parseLong(beneficiaryForm.getAdharNumber()));

			if (MisUtility.ifEmpty(beneficiaryForm.getAccountNo()))
				entryBean.setAccountNumber(Long.parseLong(beneficiaryForm.getAccountNo()));

			if (MisUtility.ifEmpty(beneficiaryForm.getBankName()))
				entryBean.setBankName(Integer.parseInt(beneficiaryForm.getBankName()));

			entryBean.setBeneficieryName(beneficiaryForm.getPersonName());

			entryBean.setFatHusName(beneficiaryForm.getFatherSpouseName());

			if (MisUtility.ifEmpty(beneficiaryForm.getBenifCategory())) {
				entryBean.setCategoryId(Integer.parseInt(beneficiaryForm.getBenifCategory()));
			}

			if (MisUtility.ifEmpty(beneficiaryForm.getCast()))
				entryBean.setCasteId(Integer.parseInt(beneficiaryForm.getCast()));

			if (MisUtility.ifEmpty(beneficiaryForm.getReligion()))
				entryBean.setReligionId(Integer.parseInt(beneficiaryForm.getReligion()));

			if (MisUtility.ifEmpty(beneficiaryForm.getGender()))
				entryBean.setGenderId(Integer.parseInt(beneficiaryForm.getGender()));

			if (MisUtility.ifEmpty(beneficiaryForm.getPhotograph()))
				entryBean.setPhotograp(beneficiaryForm.getPhotograph().getFileData());

			if (MisUtility.ifEmpty(beneficiaryForm.getPhoneNumber()))
				entryBean.setPhoneNo(Long.parseLong(beneficiaryForm.getPhoneNumber()));

			if (MisUtility.ifEmpty(beneficiaryForm.getPhotograph()))
				entryBean.setPhotographName(beneficiaryForm.getPhotograph().getFileName());

			if (MisUtility.ifEmpty(beneficiaryForm.getDistrict()))
				entryBean.setDistrictId(Integer.parseInt(beneficiaryForm.getDistrict()));

			if (MisUtility.ifEmpty(beneficiaryForm.getBlock()))
				entryBean.setBlockId(Integer.parseInt(beneficiaryForm.getBlock()));

			if (MisUtility.ifEmpty(beneficiaryForm.getVillage()))
				entryBean.setVillageId(Integer.parseInt(beneficiaryForm.getVillage()));

			if (MisUtility.ifEmpty(beneficiaryForm.getGramPanchayat()))
				entryBean.setGramPanchayatId(beneficiaryForm.getGramPanchayat());

			if (MisUtility.ifEmpty(beneficiaryForm.getPoiType()))
				entryBean.setPoiType(Integer.parseInt(beneficiaryForm.getPoiType()));

			if (MisUtility.ifEmpty(beneficiaryForm.getPoiNumber()))
				entryBean.setPoiId(beneficiaryForm.getPoiNumber());

			if (MisUtility.ifEmpty(beneficiaryForm.getElectricityCon()))
				entryBean.setElectConnNumber(beneficiaryForm.getElectricityCon());

			if (MisUtility.ifEmpty(beneficiaryForm.getElectricityBill()))
				entryBean.setElectBill(beneficiaryForm.getElectricityBill().getFileName());

			if (MisUtility.ifEmpty(beneficiaryForm.getElectricityBill()))
				entryBean.setElecConData(beneficiaryForm.getElectricityBill().getFileData());

			if (MisUtility.ifEmpty(beneficiaryForm.getBranch()))
				entryBean.setBranchName(beneficiaryForm.getBranch());

			entryBean.setIfscCode(beneficiaryForm.getIfsCode());

			entryBean.setCreatedById(Integer.parseInt(String.valueOf(beneficiaryForm.getLoginUser())));

			entryBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

		} catch (Exception e) {

		}

		return entryBean;

	}

	@Override
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
	}

	private List<BeneficiaryForm> populateBeneficiaryForm(List<BeneficiaryDto> beneficiaryDtos) throws MISException {
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
	}

	@Override
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
	}

	@Override
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
	}
}
