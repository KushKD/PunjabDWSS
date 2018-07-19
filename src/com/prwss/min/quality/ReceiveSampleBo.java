package com.prwss.min.quality;

import java.util.List;

import com.prwss.mis.common.exception.MISException;

public interface ReceiveSampleBo<T> {

	public boolean saveSample(ReceiveSampleForm receiveSampleForm) throws MISException;
	
	public boolean updateSample(ReceiveSampleForm receiveSampleForm) throws MISException;

	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);

	public List<T> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn, String sortingOrder, Object t);
	
	public List<ReceiveSampleForm> getSampleCollectionByPagination(String searchString,int clickedColumn,String colOrder);
	
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
