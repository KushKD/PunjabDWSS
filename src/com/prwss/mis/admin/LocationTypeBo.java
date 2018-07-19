package com.prwss.mis.admin;

import java.util.List;

import com.prwss.min.quality.struts.LocationTypeForm;
import com.prwss.mis.common.exception.MISException;

public interface LocationTypeBo<T> {
	
	public Boolean saveLocationMaster(LocationTypeForm locationTypeForm) throws MISException;

	public List<LocationTypeForm> getLocationMasterByPagination() throws MISException;

	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);

	public List<T> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn, String sortingOrder, Object t);

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
