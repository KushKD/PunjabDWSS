/**
 * 
 */
package com.prwss.min.dao;

import java.util.List;

import com.prwss.mis.common.util.MisUtility;

/**
 * @author bhsingh
 *
 */
public abstract class AbstractPaginationMaster<T> {

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart) {
		if(pageNumber>1){
			pageDisplayLength=pageDisplayLength*pageNumber;
		}
		List<T> formLst=null;
		System.out.println("pageno---------"+pageNumber);
		if (!MisUtility.ifEmpty(formList)) {
			if (formList.size() < pageDisplayLength) {
				formLst=formList.subList(0, formList.size());
			}
			else if (formList.size() > pageDisplayLength && pageNumber>1) {
				
				System.out.println("iDisplayStart----------"+iDisplayStart+"----pageDisplayLength-------"+pageDisplayLength);
					formLst=formList.subList((iDisplayStart),pageDisplayLength);
				}
			else{
				formLst=formList.subList(0, pageDisplayLength);
			}
			
		}
		return formLst;
	}


/*
	public List<T> getListBasedOnColumnSorting(List<T> formList,Integer sortingColumn,String sortingOrder,Object t) {
		if(sortingColumn==1){
			Collections.sort(formList, new ObjectComparator<T>("locationName", sortingOrder, t.getClass()));
		}if(sortingColumn==2){
			Collections.sort(formList, new ObjectComparator<T>("locationType", sortingOrder,t.getClass()));
		}if(sortingColumn==3){
			Collections.sort(formList, new ObjectComparator<T>("parentLocation", sortingOrder, t.getClass()));
		}if(sortingColumn==4){
			Collections.sort(formList, new ObjectComparator<T>("status", sortingOrder, t.getClass()));
		}
		return formList;
	}*/
}
