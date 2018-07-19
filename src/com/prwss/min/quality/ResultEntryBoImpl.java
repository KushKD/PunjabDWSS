package com.prwss.min.quality;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.ParameterMasterDto;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.ResultEntryDetailBean;
import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.dao.ResultEntryDao;
import com.prwss.min.dao.SampleDistributionDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class ResultEntryBoImpl extends AbstractPaginationMaster<ResultEntryDto>
		implements ResultEntryBo<ResultEntryDto> {

	private Logger log = Logger.getLogger(ResultEntryBoImpl.class);

	ResultEntryDao resultEntryDao;
	private SampleDistributionDao sampleDistributionDao;
	
	
	

	public SampleDistributionDao getSampleDistributionDao() {
		return sampleDistributionDao;
	}

	public void setSampleDistributionDao(SampleDistributionDao sampleDistributionDao) {
		this.sampleDistributionDao = sampleDistributionDao;
	}

	/**
	 * @return the resultEntryDao
	 */
	public ResultEntryDao getResultEntryDao() {
		return resultEntryDao;
	}

	/**
	 * @param resultEntryDao
	 *            the resultEntryDao to set
	 */
	public void setResultEntryDao(ResultEntryDao resultEntryDao) {
		this.resultEntryDao = resultEntryDao;
	}

	@Override
	public boolean saveResultEntyFom(ResultEntryForm resultEnryForm) throws MISException {

		ResultEntryBean resultEntryBean = null;
		int testResultId = 0;
		List<ResultEntryDetailBean> resultEntryDetailBean = null;
		List<SampleDistributionBean> sampleDistributionBeans=null;
		boolean flag = false;
		try {

			resultEntryBean = pupolateTestResultEntry(resultEnryForm);
			testResultId = resultEntryDao.saveResultEntryData(resultEntryBean);
			if (MisUtility.ifEmpty(testResultId)) {
					resultEntryDetailBean = populateTestResultEntryDetails(testResultId, resultEnryForm);
					if (!MisUtility.ifEmpty(resultEntryDetailBean)) {
						flag = resultEntryDao.saveResultEntryDetails(resultEntryDetailBean);
					}
					if(flag){
						sampleDistributionBeans=resultEntryDao.getSamplePartNos(resultEnryForm.getPartno());
						
						if(!MisUtility.ifEmpty(sampleDistributionBeans)){
							for(SampleDistributionBean sampleDistributionBean:sampleDistributionBeans){
								sampleDistributionBean.setIsEntered(Integer.parseInt(MISConstants.ONE));
								sampleDistributionDao.UpdateSchemeDistributionMaster(sampleDistributionBean);
							}
						}
					}

			}

		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}

		return flag;
	}

	@Override
	public boolean updateResultEntyForm(ResultEntryForm resultEnryForm) throws MISException {
		ResultEntryBean resultEntryBean = null;
		List<ResultEntryDetailBean> resultEntryDetailBean = null;
		boolean flag = false;
		try {
			List<ResultEntryBean> resultEntryBeans = resultEntryDao
					.getResultEntry(Integer.parseInt(resultEnryForm.getResultEntryId()));
			if (!MisUtility.ifEmpty(resultEntryBeans)) {
				resultEntryBean = getTestResultEntryBean(resultEnryForm, resultEntryBeans);
				boolean testResultIds = resultEntryDao.updateResultEntryData(resultEntryBean);
				if (testResultIds) {
						resultEntryDetailBean = populateTestResultEntryDetailsBean(
								Integer.parseInt(resultEnryForm.getResultEntryId()), resultEnryForm, resultEntryBeans);
						if (!MisUtility.ifEmpty(resultEntryDetailBean)) {
							flag = resultEntryDao.updateResultEntryDetails(resultEntryDetailBean);
						}
				}
			}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}

		return flag;
	}

	@Override
	public List<ResultEntryDto> getLocationMasterByPagination(int empId, String searchString, int clickedColumn,
			String colOrder) throws MISException {
		List<ResultEntryDto> locationMasterFormLst = null;
		Set<ResultEntryBean> resultEntryLsts = null;
		try {
			resultEntryLsts = resultEntryDao.getLocationMasterByPagination(empId, searchString, clickedColumn,
					colOrder);

			if (!MisUtility.ifEmpty(resultEntryLsts)) {
				locationMasterFormLst = getResultEntryData(resultEntryLsts,empId);
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return locationMasterFormLst;
	}

	private List<ResultEntryDto> getResultEntryData(Set<ResultEntryBean> locationMasterFormLst,int empId) {

		List<ResultEntryDto> locationMasterFormLsts = null;
		try {
			if (!MisUtility.ifEmpty(locationMasterFormLst)) {
				locationMasterFormLsts = new ArrayList<ResultEntryDto>();
				for (ResultEntryBean bean : locationMasterFormLst) {
					ResultEntryDto resultEntryDto = new ResultEntryDto();
					resultEntryDto.setSamplenumber(bean.getReceiveSampleBean().getSampleNumber());
					resultEntryDto.setSampleid(bean.getSampleId());
					resultEntryDto.setTestResultId(bean.getTestResultId());
					resultEntryDto.setSamplepartno(bean.getSamplePartNo());
					resultEntryDto.setLabId(bean.getLabId());
					resultEntryDto.setTestDoneBy(bean.getTestDoneBy());
					resultEntryDto.setLyingWithUser(empId);
					resultEntryDto.setRequestlevel(bean.getRequestLevel());
					resultEntryDto.setCompletionDate(MisUtility.convertDateString(bean.getTestCompletedDate()));
					locationMasterFormLsts.add(resultEntryDto);
				}

			}

		} catch (Exception e) {

		}
		return locationMasterFormLsts;
	}

	private ResultEntryBean pupolateTestResultEntry(ResultEntryForm resultEnryForm) {
		ResultEntryBean resultEntryBean = null;
		
		try{
		if (MisUtility.ifEmpty(resultEnryForm)) {
			String samplePartNumber = resultEnryForm.getPartno().trim();
			List<SampleDistributionBean> sampleDistributionBeans = resultEntryDao.fetchSampleNumber(samplePartNumber);
			if (!MisUtility.ifEmpty(sampleDistributionBeans)) {
				for (SampleDistributionBean bean : sampleDistributionBeans) {
					List<ReceiveSampleBean> receiveSampleBeans = resultEntryDao.fetchSampleCollectionDetails(bean);
					if (!MisUtility.ifEmpty(receiveSampleBeans)) {
						for (ReceiveSampleBean receiveSampleBean : receiveSampleBeans) {
							resultEntryBean = new ResultEntryBean();
							resultEntryBean.setSampleId(receiveSampleBean.getSampleId());
							resultEntryBean.setSamplePartNo(resultEnryForm.getPartno());
							resultEntryBean.setLabId(Integer.parseInt(resultEnryForm.getLabname()));
							resultEntryBean
									.setTestCompletedDate(MisUtility.convertStringSqlDate(resultEnryForm.getComDate()));
							resultEntryBean.setCrtByUser(Integer.parseInt(resultEnryForm.getEmpId()));
							resultEntryBean.setLyingWithUser(Integer.parseInt(resultEnryForm.getEmpId()));
							resultEntryBean.setTestDoneBy(Integer.parseInt(resultEnryForm.getTechnician()));
							resultEntryBean.setRequestLevel(1);
							resultEntryBean.setActiveFlag(1);
						}
					}
				}
			}

		}
		}catch(Exception e){
			e.printStackTrace();
			log.debug(e.getMessage());
		}

		return resultEntryBean;

	}

	private ResultEntryBean getTestResultEntryBean(ResultEntryForm resultEnryForm,
			List<ResultEntryBean> resultEntryBeans) {
		ResultEntryBean resultEntryBean = null;
		if (MisUtility.ifEmpty(resultEnryForm)) {
			List<SampleDistributionBean> sampleDistributionBeans = resultEntryDao
					.fetchSampleNumber(resultEnryForm.getPartno());
			if (!MisUtility.ifEmpty(sampleDistributionBeans)) {
					resultEntryBean = new ResultEntryBean();
					resultEntryBean.setTestResultId(Integer.parseInt(resultEnryForm.getResultEntryId()));
					resultEntryBean.setSampleId(Integer.parseInt(resultEnryForm.getSampleId()));
					resultEntryBean.setSamplePartNo(resultEnryForm.getPartno());
					resultEntryBean.setLabId(Integer.parseInt(resultEnryForm.getLabname()));
					resultEntryBean.setTestCompletedDate(MisUtility.convertStringSqlDate(resultEnryForm.getComDate()));
					resultEntryBean.setCrtByUser(Integer.parseInt(resultEnryForm.getEmpId()));
					resultEntryBean.setLyingWithUser(Integer.parseInt(resultEnryForm.getEmpId()));
					resultEntryBean.setTestDoneBy(Integer.parseInt(resultEnryForm.getTechnician()));
					resultEntryBean.setRequestLevel(1);
					resultEntryBean.setActiveFlag(1);
			}

		}

		return resultEntryBean;

	}

	private List<ResultEntryDetailBean> populateTestResultEntryDetails(int testResultId,
			ResultEntryForm resultEnryForm) {
		List<ResultEntryDetailBean> resultEntryDetailBeans = null;

		try {
			resultEntryDetailBeans = new ArrayList<ResultEntryDetailBean>();
			if (MisUtility.ifEmpty(resultEnryForm)) {
				List<ParameterMasterDto> parameterMasterDtos = resultEnryForm.getParameterMasterBeans();
				for (ParameterMasterDto parameterMasterDto : parameterMasterDtos) {
					ResultEntryDetailBean resultEntryDetails = new ResultEntryDetailBean();
					resultEntryDetails.setParamId(parameterMasterDto.getParameterId());
					resultEntryDetails.setActualValue(parameterMasterDto.getParameterValue());
					resultEntryDetails.setTestResultId(testResultId);
					resultEntryDetails.setActiveFlag(1);
					resultEntryDetails.setCrtByUser(Integer.parseInt(resultEnryForm.getEmpId()));
					resultEntryDetailBeans.add(resultEntryDetails);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultEntryDetailBeans;
	}

	private List<ResultEntryDetailBean> populateTestResultEntryDetailsBean(int testResultId,
			ResultEntryForm resultEnryForm, List<ResultEntryBean> resultEntryBeans) {
		List<ResultEntryDetailBean> resultEntryDetailBeans = null;
		try {
			resultEntryDetailBeans = new ArrayList<ResultEntryDetailBean>();
			if (MisUtility.ifEmpty(resultEnryForm)) {
				List<ParameterMasterDto> parameterMasterDtos = resultEnryForm.getParameterMasterBeans();
				for (ParameterMasterDto parameterMasterDto : parameterMasterDtos) {
					ResultEntryDetailBean resultEntryDetails = new ResultEntryDetailBean();
					resultEntryDetails.setParamId(parameterMasterDto.getParameterId());
					resultEntryDetails.setActualValue(parameterMasterDto.getParameterValue());
					resultEntryDetails.setTestResultId(testResultId);
					resultEntryDetails.setTestDetailId(parameterMasterDto.getResultEntryDetailId());
					resultEntryDetails.setActiveFlag(1);
					resultEntryDetails.setCrtByUser(Integer.parseInt(resultEnryForm.getEmpId()));
					resultEntryDetailBeans.add(resultEntryDetails);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultEntryDetailBeans;
	}

	@Override
	public List<ResultEntryDto> getListBasedOnSearchParameter(String searchParameter, List<ResultEntryDto> formList) {
		List<ResultEntryDto> locationMasterList = null;
		try {
			if (null != searchParameter && !searchParameter.equals("")) {
				locationMasterList = new ArrayList<ResultEntryDto>();
				searchParameter = searchParameter.toUpperCase();
				for (ResultEntryDto masterForm : formList) {

					if (masterForm.getSamplenumber().toUpperCase().indexOf(searchParameter) != -1) {
						locationMasterList.add(masterForm);
					}
				}
			}
		} catch (Exception e) {

		}
		return locationMasterList;
	}

	@Override
	public List<ResultEntryDto> getListBasedOnColumnSorting(List<ResultEntryDto> formList, Integer sortingColumn,
			String sortingOrder, Object t) {
		// TODO Auto-generated method stub
		return null;
	}


}
