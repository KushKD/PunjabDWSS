/**
 * 
 */
package com.prwss.min.construction.quality.bo;

import java.util.List;

/**
 * @author BH390738
 *
 */
public interface MonthlyPlanViewBo<T> {

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);

}
