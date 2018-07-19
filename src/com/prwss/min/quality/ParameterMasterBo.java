package com.prwss.min.quality;

import java.util.List;

import com.prwss.min.quality.ParameterMasterForm;
import com.prwss.mis.common.exception.MISException;

public interface ParameterMasterBo <T>{

	public boolean saveParameter(ParameterMasterForm parameterMasterForm);
	public boolean updateParameter(ParameterMasterForm parameterMasterForm);
	public List<ParameterMasterForm> getParameterMasterByPagination(String searchString,int clickedColumn,String colOrder) throws MISException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	public List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);
	public List<ParameterMasterForm> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn,
			String sortingOrder, Object t);

	
}
