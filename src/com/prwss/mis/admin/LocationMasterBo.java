/**
 * 
 */
package com.prwss.mis.admin;

import java.util.List;

import com.prwss.min.quality.struts.LocationMasterForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author bhsingh
 *
 */
public interface LocationMasterBo<T> {

	public Boolean saveLocationMaster(LocationMasterForm locationMasterForm) throws MISException;

	public List<LocationMasterForm> getLocationMasterByPagination() throws MISException;

	List<T> getListBasedOnSearchParameter(String searchParameter, List<T> formList);

	public List<T> getListBasedOnColumnSorting(List<T> formList, Integer sortingColumn, String sortingOrder, Object t);

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
