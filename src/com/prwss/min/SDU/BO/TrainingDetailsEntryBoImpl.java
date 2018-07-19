package com.prwss.min.SDU.BO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.min.SDU.bean.SduVillageTrainingAttachmentBean;
import com.prwss.min.SDU.bean.SduVillageTrainingDetailsBean;
import com.prwss.min.SDU.bean.SduVillageTrainingMaterialBean;
import com.prwss.min.SDU.dao.TrainingDetailsEntryDao;
import com.prwss.min.SDU.form.MaterialDistributionGrid;
import com.prwss.min.SDU.form.TrainingDetailsEntryForm;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class TrainingDetailsEntryBoImpl implements TrainingDetailsEntryBo{
	
	private Logger log = Logger.getLogger(TrainingDetailsEntryBoImpl.class);
	
	private TrainingDetailsEntryDao trainingDetailsEntryDao;

	public TrainingDetailsEntryDao getTrainingDetailsEntryDao() {
		return trainingDetailsEntryDao;
	}

	public void setTrainingDetailsEntryDao(TrainingDetailsEntryDao trainingDetailsEntryDao) {
		this.trainingDetailsEntryDao = trainingDetailsEntryDao;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean save(TrainingDetailsEntryForm trainingDetailsEntryForm, Integer enteredBy) throws MISException {

	boolean status = false;
	Integer trainingId = 0;
	try {
		
		if (MisUtility.ifEmpty(trainingDetailsEntryForm)) {
			
			SduVillageTrainingDetailsBean sduVillageTrainingDetailsBean = populateSduVillageTrainingDetailsBean(trainingDetailsEntryForm, enteredBy);
			
			if (MisUtility.ifEmpty(sduVillageTrainingDetailsBean)) {
				trainingId = trainingDetailsEntryDao.saveVillageTrainingDetail(sduVillageTrainingDetailsBean);
				System.out.println(trainingId);
				if (MisUtility.ifEmpty(trainingId)) {
					List<SduVillageTrainingMaterialBean> sduVillageTrainingMaterialBeans = populateVillageTrainingMaterialBeans(trainingDetailsEntryForm, trainingId, enteredBy);
					if (!MisUtility.ifEmpty(sduVillageTrainingMaterialBeans)) {
					status = trainingDetailsEntryDao.saveVillageTrainingMaterial(sduVillageTrainingMaterialBeans);
					}
					SduVillageTrainingAttachmentBean sduVillageTrainingAttachmentBean = populateSduVillageTrainingAttachmentBean(trainingDetailsEntryForm, enteredBy, trainingId);
					if (MisUtility.ifEmpty(sduVillageTrainingAttachmentBean)) {
					status = trainingDetailsEntryDao.saveSduVillageTrainingAttachment(sduVillageTrainingAttachmentBean);
					}
				}
			}
			
		}
	}catch (DataAccessException e) {
		log.debug(e.getMessage());
		throw new MISException(e);
	}
	return status;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private SduVillageTrainingDetailsBean populateSduVillageTrainingDetailsBean(TrainingDetailsEntryForm trainingDetailsEntryForm, Integer enteredBy) {
		
		SduVillageTrainingDetailsBean sduVillageTrainingDetailsBean = null;
		
		try {
		if (MisUtility.ifEmpty(trainingDetailsEntryForm)) {
			sduVillageTrainingDetailsBean = new SduVillageTrainingDetailsBean();
			
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getFinancialYear())) {
			sduVillageTrainingDetailsBean.setFinancial_year(Integer.parseInt(trainingDetailsEntryForm.getFinancialYear()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getDivision())) {
			sduVillageTrainingDetailsBean.setDivision_id(Integer.parseInt(trainingDetailsEntryForm.getDivision()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getVillage())) {
			sduVillageTrainingDetailsBean.setVillage_id(Integer.parseInt(trainingDetailsEntryForm.getVillage()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getActivity())) {
			sduVillageTrainingDetailsBean.setActivity_id(Integer.parseInt(trainingDetailsEntryForm.getActivity()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getTrainingRefNo())) {
			sduVillageTrainingDetailsBean.setTraining_ref_no(trainingDetailsEntryForm.getTrainingRefNo());
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getTainingDate())) {
			sduVillageTrainingDetailsBean.setTraining_dt(MisUtility.convertStringSqlDate(trainingDetailsEntryForm.getTainingDate()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getLvlOfTrng())) {
			sduVillageTrainingDetailsBean.setTraining_level(Integer.parseInt(trainingDetailsEntryForm.getLvlOfTrng()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getTrainer())) {
			sduVillageTrainingDetailsBean.setTrainer(trainingDetailsEntryForm.getTrainer());
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getNameOfTrng())) {
			sduVillageTrainingDetailsBean.setTraining_name(trainingDetailsEntryForm.getNameOfTrng());
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getExpenditure())) {
			sduVillageTrainingDetailsBean.setExpenditure(Double.parseDouble(trainingDetailsEntryForm.getExpenditure()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getMale())) {
			sduVillageTrainingDetailsBean.setTotal_male(Long.parseLong(trainingDetailsEntryForm.getMale()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getScMale())) {
			sduVillageTrainingDetailsBean.setSc_male(Long.parseLong(trainingDetailsEntryForm.getScMale()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getFemale())) {
			sduVillageTrainingDetailsBean.setTotal_female(Long.parseLong(trainingDetailsEntryForm.getFemale()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getScFemale())) {
			sduVillageTrainingDetailsBean.setSc_female(Long.parseLong(trainingDetailsEntryForm.getScFemale()));
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getPanchayatMembers())) {
			sduVillageTrainingDetailsBean.setPanch_member(Long.parseLong(trainingDetailsEntryForm.getPanchayatMembers()));
			}
			sduVillageTrainingDetailsBean.setCrt_by_user(enteredBy);
		}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return sduVillageTrainingDetailsBean;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	private List<SduVillageTrainingMaterialBean> populateVillageTrainingMaterialBeans(TrainingDetailsEntryForm trainingDetailsEntryForm, Integer trainingId, Integer enteredBy) {

		List<SduVillageTrainingMaterialBean> sduVillageTrainingMaterialBeans = null;
		SduVillageTrainingMaterialBean sduVillageTrainingMaterialBean = null;
		try {
				  Collection<MaterialDistributionGrid> materialDistributionGrids = trainingDetailsEntryForm.getMaterialDistGrid().getAddedData();

				if (!MisUtility.ifEmpty(materialDistributionGrids)) {
					sduVillageTrainingMaterialBeans = new ArrayList<SduVillageTrainingMaterialBean>();
				
				for (MaterialDistributionGrid materialDistributionGrid : materialDistributionGrids) {
				
					sduVillageTrainingMaterialBean = new SduVillageTrainingMaterialBean();
					Integer materialId = 0;
				
				if (MisUtility.ifEmpty(materialDistributionGrid.getTypeOfMaterialName())) {
					materialId = getMaterialId(materialDistributionGrid.getTypeOfMaterialName());
				}
				if (MisUtility.ifEmpty(materialDistributionGrid.getTypeOfMaterialName())) {
					sduVillageTrainingMaterialBean.setMaterial_type(materialId);
				}
				if (MisUtility.ifEmpty(materialDistributionGrid.getNoOfCopiesDistName())) {
					sduVillageTrainingMaterialBean.setQuantity(Long.parseLong(materialDistributionGrid.getNoOfCopiesDistName()));
				}
				sduVillageTrainingMaterialBean.setCrt_by_user(enteredBy);
				sduVillageTrainingMaterialBean.setTraining_id(Long.parseLong(String.valueOf(trainingId)));

				sduVillageTrainingMaterialBeans.add(sduVillageTrainingMaterialBean);
			}
				}	
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return sduVillageTrainingMaterialBeans;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------

	private SduVillageTrainingAttachmentBean populateSduVillageTrainingAttachmentBean(TrainingDetailsEntryForm trainingDetailsEntryForm, Integer enteredBy, Integer trainingId) {
		
		SduVillageTrainingAttachmentBean sduVillageTrainingAttachmentBean = null;
		
		try {
		if (MisUtility.ifEmpty(trainingDetailsEntryForm)) {
			sduVillageTrainingAttachmentBean = new SduVillageTrainingAttachmentBean();
			String extension = null;
			
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getUpldPhoto().getFileName())){ 
				extension = getExtension(trainingDetailsEntryForm.getUpldPhoto().getFileName()); 
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getPhotoTitle())) {
			sduVillageTrainingAttachmentBean.setAttachment_title(trainingDetailsEntryForm.getPhotoTitle()+"."+extension);
			}
			if (MisUtility.ifEmpty(trainingDetailsEntryForm.getUpldPhoto())) {
			sduVillageTrainingAttachmentBean.setAttachment(trainingDetailsEntryForm.getUpldPhoto().getFileData());
			}
			sduVillageTrainingAttachmentBean.setCrt_by_user(enteredBy);
			sduVillageTrainingAttachmentBean.setTraining_id(Long.parseLong(String.valueOf(trainingId)));
		}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return sduVillageTrainingAttachmentBean;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	private String getExtension(String data) {
		String extension = null;

		try {
			extension = data.substring(data.indexOf('.') + 1, data.length());
				System.out.println("-----------------------------------"+extension);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return extension;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------
	
	private Integer getMaterialId(String data) {
		Integer materialId = 0;

		try {
			materialId = Integer.parseInt(data.substring(data.indexOf('(') + 1, data.length()-1));
				System.out.println("-----------------------------------"+materialId);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return materialId;
	}
}
