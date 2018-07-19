/**
 * 
 */
package com.prwss.min.quality;

import com.prwss.mis.common.exception.MISException;

/**
 * @author bhsingh
 *
 */
public interface DisplayEntryBo {
	
	public boolean updateResultEntry(String testId,String level,String requestFlow,String empId,String comments) throws MISException;
	
}
