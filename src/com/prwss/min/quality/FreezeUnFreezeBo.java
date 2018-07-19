/**
 * 
 */
package com.prwss.min.quality;

import java.util.List;

/**
 * @author BH390738
 *
 */
public interface FreezeUnFreezeBo<T> {

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
