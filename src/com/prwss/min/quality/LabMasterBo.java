package com.prwss.min.quality;

import java.util.List;

import com.prwss.mis.common.exception.MISException;

public interface LabMasterBo<T> {

	public boolean saveLabMaster(LabMasterForm labMasterForm)throws MISException;
	public boolean updateLab(LabMasterForm labMasterForm)throws MISException;
	public List<LabMasterForm> getLabMasterByPagination(String searchString,int clickedColumn,String colOrder);
	

	public List<T> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn, String sortingOrder, Object t);

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
	
	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);
	
}
