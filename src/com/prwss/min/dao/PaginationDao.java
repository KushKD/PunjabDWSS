/**
 * 
 */
package com.prwss.min.dao;

import java.util.List;

/**
 * @author bhsingh
 *
 */
public interface PaginationDao<T> {

	public List<T> getListBasedOnPageNumber(List<T> formList);
	
	public List<T> getListBasedOnSearchParameter(List<T> formList);
	
	public List<T> getListBasedOnPageDisplayLength(List<T> formList);

	public List<T> getListBasedOnColumnSorting(List<T> formList);

}
