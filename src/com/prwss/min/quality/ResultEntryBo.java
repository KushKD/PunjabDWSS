package com.prwss.min.quality;

import java.util.List;

import com.prwss.mis.common.exception.MISException;

public interface ResultEntryBo<T> {
	
	public boolean saveResultEntyFom(ResultEntryForm resulEnryForm)throws MISException;
	
	public boolean updateResultEntyForm(ResultEntryForm resulEnryForm)throws MISException;
	
	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);

	public List<T> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn, String sortingOrder, Object t);

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	
	public List<ResultEntryDto> getLocationMasterByPagination(int empId,String searchString,int clickedColumn,String colOrder) throws MISException;
	
}
