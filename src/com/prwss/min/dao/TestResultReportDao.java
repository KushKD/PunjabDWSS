/**
 * 
 */
package com.prwss.min.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author BH390738
 *
 */
public interface TestResultReportDao {

	public List<String> fetchSample(String labId)throws DataAccessException;
}
