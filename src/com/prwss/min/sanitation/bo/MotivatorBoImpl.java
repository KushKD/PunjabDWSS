/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.sanitation.bean.MotivatorAcademicMapping;
import com.prwss.min.sanitation.bean.MotivatorBean;
import com.prwss.min.sanitation.dao.MotivatorDao;
import com.prwss.min.sanitation.form.MotivatorEntryForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author BH390738
 *
 */
public class MotivatorBoImpl implements MotivatorBo {

	private Logger log = Logger.getLogger(MotivatorBoImpl.class);
	private MotivatorDao motivatorDao;

	public MotivatorDao getMotivatorDao() {
		return motivatorDao;
	}

	public void setMotivatorDao(MotivatorDao motivatorDao) {
		this.motivatorDao = motivatorDao;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean saveMotivatorDetails(MotivatorEntryForm motivatorEntryForm) throws MISException {

		MotivatorBean motivatorBean = null;
		List<MotivatorAcademicMapping> motivatorAcademicMappings = null;
		boolean status = false;
		try {
			motivatorBean = getMotivatorBean(motivatorEntryForm);
			status = motivatorDao.saveMotivatorDetails(motivatorBean);
			if (status) {
				motivatorAcademicMappings = getMotivatorAcademicDetiails(motivatorEntryForm);
				Iterator<MotivatorAcademicMapping> iterator = motivatorAcademicMappings.iterator();
				List<MotivatorAcademicMapping> motivatorAcademicMappings2 = new ArrayList<MotivatorAcademicMapping>();
				while (iterator.hasNext()) {
					MotivatorAcademicMapping motivatorAcademicMapping = (MotivatorAcademicMapping) iterator.next();
					motivatorAcademicMapping.setMotivatorBean(motivatorBean);
					motivatorAcademicMappings2.add(motivatorAcademicMapping);
				}

				motivatorDao.saveMotivatorAcademicDetails(motivatorAcademicMappings2);
			}
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return true;
	}

	@Override
	public List<MotivatorBean> validateMotivatorDetails(MotivatorEntryForm motivatorEntryForm) throws MISException {
		List<MotivatorBean> motivatorBeans;
		try {
			motivatorBeans = motivatorDao.validateMotivatorDetails(motivatorEntryForm);
		} catch (DataAccessException e) {
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		return motivatorBeans;
	}

	private List<MotivatorAcademicMapping> getMotivatorAcademicDetiails(MotivatorEntryForm motivatorEntryForm) {

		List<MotivatorAcademicMapping> motivatorAcademicMappings = null;
		try {
			motivatorAcademicMappings = new ArrayList<MotivatorAcademicMapping>();

			if (MisUtility.ifEmpty(motivatorEntryForm.getHighPassedYear())) {
				MotivatorAcademicMapping motivatorAcademicMapping1 = new MotivatorAcademicMapping();
				motivatorAcademicMapping1.setExamName(motivatorEntryForm.getExamHighschool());
				motivatorAcademicMapping1.setExamYear(motivatorEntryForm.getHighPassedYear());
				motivatorAcademicMapping1.setTotalMarks(motivatorEntryForm.getHighTotalMarks());
				motivatorAcademicMapping1.setMarksObtained(motivatorEntryForm.getHighObtainedMarks());
				motivatorAcademicMapping1.setBoardUniversity(motivatorEntryForm.getHighBoardUniversity());
				motivatorAcademicMapping1.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				motivatorAcademicMappings.add(motivatorAcademicMapping1);

			}
			if (MisUtility.ifEmpty(motivatorEntryForm.getInterPassedYear())) {
				MotivatorAcademicMapping motivatorAcademicMapping2 = new MotivatorAcademicMapping();
				motivatorAcademicMapping2.setExamName(motivatorEntryForm.getExamIntermediate());
				motivatorAcademicMapping2.setExamYear(motivatorEntryForm.getInterPassedYear());
				motivatorAcademicMapping2.setTotalMarks(motivatorEntryForm.getInterTotalMarks());

				motivatorAcademicMapping2.setMarksObtained(motivatorEntryForm.getInterObtainedMarks());
				motivatorAcademicMapping2.setBoardUniversity(motivatorEntryForm.getInterBoardUniversity());
				motivatorAcademicMapping2.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				motivatorAcademicMappings.add(motivatorAcademicMapping2);

			}
			if (MisUtility.ifEmpty(motivatorEntryForm.getGrdPassedYear())) {
				MotivatorAcademicMapping motivatorAcademicMapping3 = new MotivatorAcademicMapping();
				motivatorAcademicMapping3.setExamName(motivatorEntryForm.getExamGraduation());
				motivatorAcademicMapping3.setExamYear(motivatorEntryForm.getGrdPassedYear());
				motivatorAcademicMapping3.setTotalMarks(motivatorEntryForm.getGrdTotalMarks());
				motivatorAcademicMapping3.setMarksObtained(motivatorEntryForm.getGrdObtainedMarks());
				motivatorAcademicMapping3.setBoardUniversity(motivatorEntryForm.getGrdBoardUniversity());
				motivatorAcademicMapping3.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				motivatorAcademicMapping3.setCreatedById(Integer.parseInt(String.valueOf(motivatorEntryForm.getLoginUser())));
				motivatorAcademicMappings.add(motivatorAcademicMapping3);
			}
			if (MisUtility.ifEmpty(motivatorEntryForm.getExamPGraduation())) {
				MotivatorAcademicMapping motivatorAcademicMapping4 = new MotivatorAcademicMapping();
				motivatorAcademicMapping4.setExamName(motivatorEntryForm.getExamPGraduation());
				motivatorAcademicMapping4.setExamYear(motivatorEntryForm.getPgrdPassedYear());
				motivatorAcademicMapping4.setTotalMarks(motivatorEntryForm.getPgrdTotalMarks());
				motivatorAcademicMapping4.setMarksObtained(motivatorEntryForm.getPgrdObtainedMarks());
				motivatorAcademicMapping4.setBoardUniversity(motivatorEntryForm.getPgrdBoardUniversity());
				motivatorAcademicMapping4.setActiveFlag(Integer.parseInt(MISConstants.ONE));
				motivatorAcademicMappings.add(motivatorAcademicMapping4);
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return motivatorAcademicMappings;
	}

	private MotivatorBean getMotivatorBean(MotivatorEntryForm motivatorEntryForm) {

		MotivatorBean motivatorBean = null;
		try {
			motivatorBean = new MotivatorBean();

			motivatorBean.setMotivatorName(motivatorEntryForm.getPersonName());
			motivatorBean.setFatHusName(motivatorEntryForm.getFatherSpouseName());

			if (MisUtility.ifEmpty(motivatorEntryForm.getBenifCategory()))
				motivatorBean.setCategoryId(Integer.parseInt(motivatorEntryForm.getBenifCategory()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getReligion()))
				motivatorBean.setReligionId(Integer.parseInt(motivatorEntryForm.getReligion()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getGender()))
				motivatorBean.setGenderId(Integer.parseInt(motivatorEntryForm.getGender()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getEmailId()))
				motivatorBean.setEmailId(motivatorEntryForm.getEmailId());

			if (MisUtility.ifEmpty(motivatorEntryForm.getPhoneNumber()))
				motivatorBean.setPhoneNo(Long.parseLong(motivatorEntryForm.getPhoneNumber()));

			motivatorBean.setPhotographName(motivatorEntryForm.getPhotograph().getFileName());
			motivatorBean.setPhotograp(motivatorEntryForm.getPhotograph().getFileData());

			if (MisUtility.ifEmpty(motivatorEntryForm.getDistrictCorsp()))
				motivatorBean.setDistrictId(Integer.parseInt(motivatorEntryForm.getDistrictCorsp()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getBlockCorsp()))
				motivatorBean.setBlockId(Integer.parseInt(motivatorEntryForm.getBlockCorsp()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getVillageCorsp()))
				motivatorBean.setVillageId(Integer.parseInt(motivatorEntryForm.getVillageCorsp()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getDistrictPermanent()))
				motivatorBean.setDistrictPrId(Integer.parseInt(motivatorEntryForm.getDistrictPermanent()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getBlockPermanent()))
				motivatorBean.setBlockPrId(Integer.parseInt(motivatorEntryForm.getBlockPermanent()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getVillagePermanent()))
				motivatorBean.setVillagePrId(Integer.parseInt(motivatorEntryForm.getVillagePermanent()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getPoiType()))
				motivatorBean.setPoiType(Integer.parseInt(motivatorEntryForm.getPoiType()));

			motivatorBean.setPoiId(motivatorEntryForm.getPoiNumber());

			if (MisUtility.ifEmpty(motivatorEntryForm.getAdharNumber()))
				motivatorBean.setAadhaarNumber(Long.parseLong(motivatorEntryForm.getAdharNumber()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getBankName()))
				motivatorBean.setBankName(Integer.parseInt(motivatorEntryForm.getBankName()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getAccountNo()))
				motivatorBean.setAccountNumber(Long.parseLong(motivatorEntryForm.getAccountNo()));

			if (MisUtility.ifEmpty(motivatorEntryForm.getIfsCode()))
				motivatorBean.setIfscCode(motivatorEntryForm.getIfsCode());

			motivatorBean.setCreatedById(Integer.parseInt(String.valueOf(motivatorEntryForm.getLoginUser())));

			motivatorBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
			motivatorBean.setLocation(motivatorEntryForm.getLocationAreaCorsp());
			motivatorBean.setAddStreet(motivatorEntryForm.getStreetCorsp());
			motivatorBean.setLandmark(motivatorEntryForm.getLandMarkCorsp());
			motivatorBean.setHousenumber(motivatorEntryForm.getHouseNumberCorsp());

			if (MisUtility.ifEmpty(motivatorEntryForm.getPincodeCorsp()))
				motivatorBean.setPincode(Integer.parseInt(motivatorEntryForm.getPincodeCorsp()));

			motivatorBean.setLocationPrm(motivatorEntryForm.getLocationAreaPermanent());
			motivatorBean.setAddStreetPrm(motivatorEntryForm.getStreetPermanent());
			motivatorBean.setLandmarkPrm(motivatorEntryForm.getLandMarkPermanent());
			motivatorBean.setHousenumberPrm(motivatorEntryForm.getHouseNumberPermanent());

			if (MisUtility.ifEmpty(motivatorEntryForm.getPincodePermanent()))
				motivatorBean.setPincodePrm(Integer.parseInt(motivatorEntryForm.getPincodePermanent()));

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return motivatorBean;
	}

}
