package com.prwss.min.quality;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SampleCodeLabMapping;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.ReceivingSampleDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class ReceiveSampleBoImpl extends AbstractPaginationMaster<ReceiveSampleForm>
		implements ReceiveSampleBo<ReceiveSampleForm> {

	private ReceivingSampleDao receiveSampleDao;

	private Logger log = Logger.getLogger(ReceiveSampleBoImpl.class);

	/**
	 * @return the receiveSampleDao
	 */
	public ReceivingSampleDao getReceiveSampleDao() {
		return receiveSampleDao;
	}

	/**
	 * @param receiveSampleDao
	 *            the receiveSampleDao to set
	 */
	public void setReceiveSampleDao(ReceivingSampleDao receiveSampleDao) {
		this.receiveSampleDao = receiveSampleDao;
	}

	@Override
	public boolean saveSample(ReceiveSampleForm receiveSampleForm) throws MISException {

		ReceiveSampleBean receiveSampleLst = null;
		boolean status = false;
		try {
			System.out.println(receiveSampleForm.getLab());
			if (MisUtility.ifEmpty(receiveSampleForm)) {
				receiveSampleLst = populateReceiveSample(receiveSampleForm);
				if (MisUtility.ifEmpty(receiveSampleLst)) {
					status = receiveSampleDao.saveSampleData(receiveSampleLst);
				}
				if (status) {
					List<SampleCodeLabMapping> sampleCodeLabMappings = receiveSampleDao
							.findLabIdSampleMapping(receiveSampleForm.getLab());
					if (!MisUtility.ifEmpty(sampleCodeLabMappings)) {
						int sequence = getSampleSequence(receiveSampleForm);
						if (MisUtility.ifEmpty(sequence)) {
							for (SampleCodeLabMapping mapping : sampleCodeLabMappings) {
								mapping.setCurrentSequence(sequence);
								status = receiveSampleDao.update(mapping);
							}
						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	private int getSampleSequence(ReceiveSampleForm receiveSampleForm) {
		int sequenceNumber = 0;
		try {
			String sampleNumber = receiveSampleForm.getSampleNumber();
			String sample = sampleNumber.substring(sampleNumber.lastIndexOf('/') + 1);
			String sequenceNumberStr = sample;

			sequenceNumber = Integer.parseInt(sequenceNumberStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sequenceNumber;
	}

	@Override
	public boolean updateSample(ReceiveSampleForm receiveSampleForm) throws MISException {

		ReceiveSampleBean receiveSampleLst = null;
		boolean status = false;
		try {

			if (MisUtility.ifEmpty(receiveSampleForm)) {

				if (MisUtility.ifEmpty(receiveSampleForm.getSampleId())) {
					List<ReceiveSampleBean> masterBean = receiveSampleDao.findSampleCollection(receiveSampleForm);
					if (!MisUtility.ifEmpty(masterBean)) {
						for (ReceiveSampleBean bean : masterBean) {
							bean.setActiveFlag(Integer.parseInt(MISConstants.INACTIVE_STATUS));
							status = receiveSampleDao.updateSampleCollection(bean);
						}
					}
				}
				receiveSampleLst = populateReceiveSample(receiveSampleForm);
				if (MisUtility.ifEmpty(receiveSampleLst)) {
					status = receiveSampleDao.saveSampleData(receiveSampleLst);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	private ReceiveSampleBean populateReceiveSample(ReceiveSampleForm receiveSampleForm) {

		ReceiveSampleBean receiveSampleBean = null;
		try {
			receiveSampleBean = new ReceiveSampleBean();

			if (MisUtility.ifEmpty(receiveSampleForm.getCollectionType())) {

				receiveSampleBean.setCollectionType(Integer.parseInt(receiveSampleForm.getCollectionType()));
				if (receiveSampleForm.getCollectionType().equalsIgnoreCase(MISConstants.ONE)) {

					if (MisUtility.ifEmpty(receiveSampleForm.getDesignation())) {
						receiveSampleBean.setDesignation(receiveSampleForm.getDesignation());
					}
					if (MisUtility.ifEmpty(receiveSampleForm.getCollectedBy())) {
						receiveSampleBean.setCollected_by(receiveSampleForm.getCollectedBy());
					}
					if (MisUtility.ifEmpty(receiveSampleForm.getEmailDwss())) {
						receiveSampleBean.setOffice_email_id(receiveSampleForm.getEmailDwss());
					}
					if (MisUtility.ifEmpty(receiveSampleForm.getMobileNoDwss())) {
						receiveSampleBean.setMobileNo(Long.parseLong(receiveSampleForm.getMobileNoDwss().trim()));
					}
				}
				if (receiveSampleForm.getCollectionType().equalsIgnoreCase(MISConstants.TWO)) {

					if (MisUtility.ifEmpty(receiveSampleForm.getMobileNo().trim())) {
						receiveSampleBean.setMobileNo(Long.parseLong(receiveSampleForm.getMobileNo().trim()));
					}
					receiveSampleBean.setPoi_id(receiveSampleForm.getPoiId());
					receiveSampleBean.setOffice_email_id(receiveSampleForm.getEmail());
					if (MisUtility.ifEmpty(receiveSampleForm.getPoiType())) {
						receiveSampleBean.setPoiType(Integer.parseInt(receiveSampleForm.getPoiType()));
					}
				}
			}

			if (MisUtility.ifEmpty(receiveSampleForm.getUrban())) {

				if (MisUtility.ifEmpty(receiveSampleForm.getCity())) {
					receiveSampleBean.setCity(receiveSampleForm.getCity());
				}
				if (MisUtility.ifEmpty(receiveSampleForm.getVillageId())) {
					receiveSampleBean.setVillageId(Integer.parseInt(receiveSampleForm.getVillageId()));
				} else {
					receiveSampleBean.setVillageId(Integer.parseInt("0"));
				} 
				if (MisUtility.ifEmpty(receiveSampleForm.getWaterSourceUrban())) {
					receiveSampleBean.setWaterSource(Integer.parseInt(receiveSampleForm.getWaterSourceUrban()));
				}
				//receiveSampleBean.setSchemeId("");
				receiveSampleBean.setRural_urban(0);

			}
			if (MisUtility.ifEmpty(receiveSampleForm.getRural())) {

				if (MisUtility.ifEmpty(receiveSampleForm.getVillageId())) {
					receiveSampleBean.setVillageId(Integer.parseInt(receiveSampleForm.getVillageId()));
				}
				if (MisUtility.ifEmpty(receiveSampleForm.getCity())) {
					receiveSampleBean.setCity(receiveSampleForm.getCity());
				}else{
					receiveSampleBean.setCity("");
				}if (MisUtility.ifEmpty(receiveSampleForm.getWaterSource())) {
					receiveSampleBean.setWaterSource(Integer.parseInt(receiveSampleForm.getWaterSource()));
				}
				if (MisUtility.ifEmpty(receiveSampleForm.getSchemeName())) {
					receiveSampleBean.setSchemeId(Integer.parseInt(receiveSampleForm.getSchemeName()));
				}
				
				receiveSampleBean.setRural_urban(1);
			}
			
			if (MisUtility.ifEmpty(receiveSampleForm.getSampleotherdetails())) {
				receiveSampleBean.setSampleotherdetails(receiveSampleForm.getSampleotherdetails());
			}

			if (MisUtility.ifEmpty(receiveSampleForm.getTestType())) {
				receiveSampleBean.setTestType(Integer.parseInt(receiveSampleForm.getTestType()));
			}

			if (MisUtility.ifEmpty(receiveSampleForm.getStatus())) {
				receiveSampleBean.setActiveFlag(Integer.parseInt(receiveSampleForm.getStatus()));
			}

			if (MisUtility.ifEmpty(receiveSampleForm.getDistrict())) {
				receiveSampleBean.setDistrictId(Integer.parseInt(receiveSampleForm.getDistrict()));
			}
			if (MisUtility.ifEmpty(receiveSampleForm.getBlock())) {
				receiveSampleBean.setBlockId(Integer.parseInt(receiveSampleForm.getBlock()));
			}

			if (MisUtility.ifEmpty(receiveSampleForm.getGramPanchayat())) {
				receiveSampleBean.setGramPanchayatId(receiveSampleForm.getGramPanchayat());
			}
			if (MisUtility.ifEmpty(receiveSampleForm.getHabitation())) {
				receiveSampleBean.setHabitationType(receiveSampleForm.getHabitation());
			}
			if (MisUtility.ifEmpty(receiveSampleForm.getCollDate())) {
				receiveSampleBean.setCollection_date(MisUtility.convertStringSqlDate(receiveSampleForm.getCollDate()));
			}
			if (MisUtility.ifEmpty(receiveSampleForm.getLab())) {
				receiveSampleBean.setLabTested(Integer.parseInt(receiveSampleForm.getLab()));
			}
			if(MisUtility.ifEmpty(receiveSampleForm.getSchemeName())){
				receiveSampleBean.setSchemeId(Integer.parseInt(receiveSampleForm.getSchemeName()));
			}
			
			if (MisUtility.ifEmpty(receiveSampleForm.getWaterSource())) {
				receiveSampleBean.setWaterSource(Integer.parseInt(receiveSampleForm.getWaterSource()));
			}
			
			if (MisUtility.ifEmpty(receiveSampleForm.getDepth())) {
				receiveSampleBean.setDepth(Double.parseDouble(receiveSampleForm.getDepth()));
			}
			
			if (MisUtility.ifEmpty(receiveSampleForm.getQuantity())) {
				receiveSampleBean.setQuantity(Double.parseDouble(receiveSampleForm.getQuantity()));
			}
			
			if (MisUtility.ifEmpty(receiveSampleForm.getBottleType())) {
				receiveSampleBean.setBottleType(Integer.parseInt(receiveSampleForm.getBottleType()));
			}
			
			if (MisUtility.ifEmpty(receiveSampleForm.getLetterRefNum())) {
				receiveSampleBean.setLetterRefNum(receiveSampleForm.getLetterRefNum());  
			}

			// receiveSampleBean.setSchemeStatus(receiveSampleForm.getSchemeStatus());
			receiveSampleBean.setCreateByUsr(receiveSampleForm.getCreatedBy());

			receiveSampleBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
			String sampleNo = receiveSampleForm.getSampleNumber().trim();
			receiveSampleBean.setSampleNumber(sampleNo);
			receiveSampleBean.setLandmark(receiveSampleForm.getLandMark());
			receiveSampleBean.setIs_freeze(Integer.parseInt(MISConstants.ZERO));
			receiveSampleBean.setIs_distributed(Integer.parseInt(MISConstants.ZERO));
			// receiveSampleBean.setWater_works_sample(receiveSampleForm.getSampleWaterWorks());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receiveSampleBean;
	}

	@Override
	public List<ReceiveSampleForm> getListBasedOnSearchParameter(String searchParameter,
			List<ReceiveSampleForm> formList) {
		/*
		 * try { if (null != searchParameter && !searchParameter.equals("")) {
		 * List<ReceiveSampleForm> locationMasterList = new
		 * ArrayList<ReceiveSampleForm>(); searchParameter =
		 * searchParameter.toUpperCase(); for (ReceiveSampleForm masterForm :
		 * formList) { if
		 * (masterForm.getSampleNumber().toUpperCase().indexOf(searchParameter)
		 * != -1 || masterForm.getLab().toUpperCase().indexOf(searchParameter)
		 * != -1 ||
		 * masterForm.getWaterSource().toUpperCase().indexOf(searchParameter) !=
		 * -1 ||
		 * masterForm.getHabitation().toUpperCase().indexOf(searchParameter) !=
		 * -1) {
		 * 
		 * locationMasterList.add(masterForm); } } formList =
		 * locationMasterList; locationMasterList = null; } } catch (Exception
		 * e) {
		 * 
		 * }
		 */
		return formList;
	}

	@Override
	public List<ReceiveSampleForm> getListBasedOnColumnSorting(List<ReceiveSampleForm> formList, Integer sortingColumn,
			String sortingOrder, Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReceiveSampleForm> getSampleCollectionByPagination(String searchString, int clickedColumn,
			String colOrder) {
		List<ReceiveSampleForm> receiveSampleForm = null;
		List<ReceiveSampleBean> receiveSampleBean = null;

		try {
			receiveSampleBean = receiveSampleDao.getSampleCollectionByPagination(searchString, clickedColumn, colOrder);
			if (!MisUtility.ifEmpty(receiveSampleBean)) {
				receiveSampleForm = getSampleCollectionform(receiveSampleBean);
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return receiveSampleForm;

	}

	private List<ReceiveSampleForm> getSampleCollectionform(List<ReceiveSampleBean> receiveSampleBean) {

		List<ReceiveSampleForm> receiveSampleForm = new ArrayList<ReceiveSampleForm>();
		try {

			for (ReceiveSampleBean bean : receiveSampleBean) {
				ReceiveSampleForm form = new ReceiveSampleForm();
				form.setLab(String.valueOf(bean.getLabTested()));
				form.setCollectedBy(bean.getCollected_by());
				form.setCollectionType(String.valueOf(bean.getCollectionType()));
				form.setDesignation(bean.getDesignation());
				form.setMobileNo(String.valueOf(bean.getMobileNo()));
				form.setEmail(String.valueOf(bean.getOffice_email_id()));
				form.setPoiId(bean.getPoi_id());
				form.setPoiType(String.valueOf(bean.getPoiType()));
				form.setDistrict(String.valueOf(bean.getDistrictId()));
				form.setBlock(String.valueOf(bean.getBlockId()));
				form.setVillageId(String.valueOf(bean.getVillageId()));
				form.setGramPanchayat(bean.getGramPanchayatId());
				form.setHabitation(bean.getHabitationType());
				form.setSchemeName(String.valueOf(bean.getSchemeId()));
				
				if(MisUtility.ifEmpty(bean.getWaterSource())){
				form.setWaterSource(String.valueOf((bean.getWaterSource())));
				}else{
					form.setWaterSourceUrban(String.valueOf(bean.getWaterSource()));
				}
				
				form.setTestType(String.valueOf(bean.getTestType()));
				form.setSampleNumber(bean.getSampleNumber());
				form.setCollDate(MisUtility.convertDateString(bean.getCollection_date()));
				form.setSampleId(String.valueOf(bean.getSampleId()));
				form.setCity(String.valueOf(bean.getCity())); 
				form.setLandMark(bean.getLandmark());
				if(MisUtility.ifEmpty(bean.getDepth())){
					form.setDepth(Double.toString(bean.getDepth()));
				}else{
					form.setDepth("0.0"); 
				}
				if(MisUtility.ifEmpty(bean.getBottleType())){
					form.setBottleType(Integer.toString(bean.getBottleType()));
				}else{
					form.setBottleType("");
				}
				form.setLetterRefNum(bean.getLetterRefNum());
				if(MisUtility.ifEmpty(bean.getQuantity())){
					form.setQuantity(Double.toString(bean.getQuantity()));
				}else{
					form.setQuantity("0.0"); 
				}
				

		       if(MisUtility.ifEmpty(bean.getRural_urban())){
		    	   if(bean.getRural_urban() == 1){
			        	form.setRural(Integer.toString(1)); 
			        }else{
			        	form.setUrban(Integer.toString(0));
			        }
		       }
				// form.setSampleWaterWorks(bean.getWater_works_sample());
				/**
				 * START KD Below Code Earlier Working Code USed to get the
				 * Water Source name nad Lab Name
				 */
				form.setWaterSourceName(bean.getSchemeMapping().getWsName());
				form.setLabName(bean.getLabMasterBean().getLabName());
				form.setSampleotherdetails(bean.getSampleotherdetails());
				/**
				 * End Code USed to get the Water Source name nad Lab Name
				 */

				receiveSampleForm.add(form);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receiveSampleForm;
	}

}
