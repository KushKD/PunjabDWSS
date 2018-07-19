package com.prwss.min.quality;

import java.util.List;

import com.prwss.mis.common.exception.MISException;

public interface SampleDistributionBO<T> {

	public boolean saveSampleDistribution(
			SampleDistributionForm receiveSampleForm) throws MISException;
	
	public boolean updateSampleDistribution(
			SampleDistributionForm receiveSampleForm) throws MISException;

	public List<SampleDistributionForm> getDistributedSampleByPagination(String searchString,int clickedColumn,String colOrder);

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);

	public List<T> getListBasedOnSearchParameter(String searchParameter,
			List<T> formList);

}
