package com.prwss.min.quality;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.SampleDistributionDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class SampleDistributionBoImpl extends AbstractPaginationMaster<SampleDistributionForm>
		implements SampleDistributionBO<SampleDistributionForm> {

	private Logger log = Logger.getLogger(SampleDistributionBoImpl.class);

	private SampleDistributionDao sampleDistributionDao;

	public SampleDistributionDao getSampleDistributionDao() {
		return sampleDistributionDao;
	}

	public void setSampleDistributionDao(SampleDistributionDao sampleDistributionDao) {
		this.sampleDistributionDao = sampleDistributionDao;
	}

	@Override
	public boolean saveSampleDistribution(SampleDistributionForm sampleDistributionForm) throws MISException {
		SampleDistributionBean sampleDisributionBean = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(sampleDistributionForm)) {
				sampleDisributionBean = populateSample(sampleDistributionForm);
				if (MisUtility.ifEmpty(sampleDisributionBean)) {
					status = sampleDistributionDao.saveSampleDistributionData(sampleDisributionBean);
				}
				if (status) {
					System.out.println("sampleDistributionForm.getTests()=------------"+sampleDistributionForm.getTests());
					sampleDistributionDao.updateSampleCollection(sampleDistributionForm.getSampleNum(),sampleDistributionForm.getFreeze(),MisUtility.convertStringSqlDate(MisUtility.now("MM/dd/yyyy")));
					
					List<SamplePartCodeLabMapping> sampleCodeLabMappings = sampleDistributionDao
							.fetchSubSampleNumber(sampleDistributionForm.getTests());
					if (!MisUtility.ifEmpty(sampleCodeLabMappings)) {
						int sequence = getSampleSequence(sampleDistributionForm);
						if (MisUtility.ifEmpty(sequence)) {
							for (SamplePartCodeLabMapping mapping : sampleCodeLabMappings) {
								mapping.setCurrentSequence(sequence);
								status = sampleDistributionDao.updateSamplePart(mapping);
							}
						}

					}
				}
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	private int getSampleSequence(SampleDistributionForm receiveSampleForm) {
		int sequenceNumber = 0;
		try {
			String sampleNumber = receiveSampleForm.getSamplePartNum();
			String sample = sampleNumber.substring(sampleNumber.lastIndexOf('/') + 1);
			String sequenceNumberStr = sample;
			System.out.println("--------1-----------" + sequenceNumberStr);

			sequenceNumber = Integer.parseInt(sequenceNumberStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sequenceNumber;
	}

	@Override
	public boolean updateSampleDistribution(SampleDistributionForm receiveSampleForm) throws MISException {
		SampleDistributionBean sampleDisributionBean = null;
		boolean status = false;
		try {
			if (MisUtility.ifEmpty(receiveSampleForm)) {

				sampleDisributionBean = populateSampleUpdate(receiveSampleForm);
				System.out.println(sampleDisributionBean.toString());
				if (MisUtility.ifEmpty(sampleDisributionBean)) {
					status = sampleDistributionDao.UpdateSchemeDistributionMaster(sampleDisributionBean);
				}
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

	private SampleDistributionBean populateSample(SampleDistributionForm sampleDistributionForm) {
		SampleDistributionBean sampleDisributionBean = new SampleDistributionBean();

		if (MisUtility.ifEmpty(sampleDistributionForm.getSampleNum())) {
			sampleDisributionBean.setSampleNum(sampleDistributionForm.getSampleNum().trim());
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getLocationName())) {
			sampleDisributionBean.setLocationName(sampleDistributionForm.getLocationName());
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getTechnician())) {
			sampleDisributionBean.setTechnician(sampleDistributionForm.getTechnician());
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getSamplePartNum())) {
			sampleDisributionBean.setSamplePartNum(sampleDistributionForm.getSamplePartNum());
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getTests())) {
			sampleDisributionBean.setTests(Integer.parseInt(sampleDistributionForm.getTests()));
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getRequiredBy())) {
			sampleDisributionBean.setRequiredBy(sampleDistributionForm.getRequiredBy());
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getLabName())) {
			sampleDisributionBean.setLabName(Integer.parseInt(sampleDistributionForm.getLabName()));
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getDistributionDate())) {
			sampleDisributionBean.setDistributionDate(MisUtility.convertStringSqlDate(sampleDistributionForm.getDistributionDate()));
		}

		sampleDisributionBean.setSampleType(sampleDistributionForm.getSampleType());
		sampleDisributionBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
		sampleDisributionBean.setIsEntered(Integer.parseInt(MISConstants.ZERO));

		return sampleDisributionBean;
	}

	private SampleDistributionBean populateSampleUpdate(SampleDistributionForm sampleDistributionForm) {
		SampleDistributionBean sampleDisributionBean = new SampleDistributionBean();

		if (MisUtility.ifEmpty(sampleDistributionForm.getSampleNum())) {
			sampleDisributionBean.setSampleNum(sampleDistributionForm.getSampleNum());
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getLocationName())) {
			sampleDisributionBean.setLocationName(sampleDistributionForm.getLocationName());
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getTechnician())) {
			sampleDisributionBean.setTechnician(sampleDistributionForm.getTechnician());
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getSamplePartNum())) {
			sampleDisributionBean.setSamplePartNum(sampleDistributionForm.getSamplePartNum());
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getTests())) {
			sampleDisributionBean.setTests(Integer.parseInt(sampleDistributionForm.getTests()));
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getRequiredBy())) {
			sampleDisributionBean.setRequiredBy(sampleDistributionForm.getRequiredBy());
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getLabName())) {
			sampleDisributionBean.setLabName(Integer.parseInt(sampleDistributionForm.getLabName()));
		}

		if (MisUtility.ifEmpty(sampleDistributionForm.getDistributionDate())) {
			sampleDisributionBean.setDistributionDate(MisUtility.convertStringSqlDate(sampleDistributionForm.getDistributionDate()));
		}
		if (MisUtility.ifEmpty(sampleDistributionForm.getDistributionId())) {
			sampleDisributionBean.setSampleId(Integer.parseInt(sampleDistributionForm.getDistributionId()));
		}
		sampleDisributionBean.setSampleType(sampleDistributionForm.getSampleType());
		sampleDisributionBean.setActiveFlag(Integer.parseInt(MISConstants.ACTIVE_STATUS));
		sampleDisributionBean.setIsEntered(Integer.parseInt(MISConstants.ZERO));
		return sampleDisributionBean;
	}

	@Override
	public List<SampleDistributionForm> getDistributedSampleByPagination(String searchString, int clickedColumn,
			String colOrder) {
		List<SampleDistributionBean> sampleDistributionBean = null;
		List<SampleDistributionForm> sampleDistributionForm = null;

		try {

			sampleDistributionBean = sampleDistributionDao.getsampleDistributionByPagination(searchString,
					clickedColumn, colOrder);

			if (!MisUtility.ifEmpty(sampleDistributionBean)) {
				sampleDistributionForm = new ArrayList<SampleDistributionForm>();
				for (SampleDistributionBean bean : sampleDistributionBean) {
					SampleDistributionForm form = new SampleDistributionForm();
					form.setSampleNum(bean.getSampleNum());
					form.setLabName(String.valueOf(bean.getLabMasterBean().getLabName()));
					form.setLabId(String.valueOf(bean.getLabMasterBean().getLabId()));
					form.setLocationName(bean.getLocationName());
					form.setSampleType(bean.getSampleType());
					
					form.setSampleId(String.valueOf(bean.getSampleId()));
					
					form.setDistributionDate(MisUtility.convertDateString(bean.getDistributionDate()));
					form.setTechnician(bean.getTechnician());
					form.setSamplePartNum(bean.getSamplePartNum());
					form.setTests(String.valueOf(bean.getTests()));
					form.setRequiredBy(bean.getRequiredBy());
					form.setDistributionId(String.valueOf(bean.getSampleId()));
					form.setSampleType(bean.getSampleType());
					// form.setStatus(String.valueOf(bean.getActiveFlag()));
					/*
					 * if(MisUtility.ifEmpty(bean.getStartDate())){
					 * form.setStartDate(MisUtility.convertDateString(bean.
					 * getStartDate())); }
					 */

					sampleDistributionForm.add(form);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sampleDistributionForm;
	}

	@Override
	public List<SampleDistributionForm> getListBasedOnSearchParameter(String searchParameter,
			List<SampleDistributionForm> formList) {
		List<SampleDistributionForm> locationMasterList = null;
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				locationMasterList = new ArrayList<SampleDistributionForm>();
				searchParameter = searchParameter.toUpperCase();
				for (SampleDistributionForm masterForm : formList) {
					if (masterForm.getLocationName().toUpperCase().indexOf(searchParameter) != -1
							|| masterForm.getSampleNum().toUpperCase().indexOf(searchParameter) != -1
							|| masterForm.getSamplePartNum().toUpperCase().indexOf(searchParameter) != -1
							|| masterForm.getLabName().toUpperCase().indexOf(searchParameter) != -1
							|| masterForm.getTests().toUpperCase().indexOf(searchParameter) != -1) {
						locationMasterList.add(masterForm);
					}
					/*
					 * if(MisUtility.ifEmpty(masterForm.getParentLocation())){
					 * if( masterForm.getParentLocation().toUpperCase().indexOf(
					 * searchParameter) != -1){
					 * locationMasterList.add(masterForm); } }
					 */
				}
			}
		} catch (Exception e) {

		}
		return locationMasterList;
	}

}
