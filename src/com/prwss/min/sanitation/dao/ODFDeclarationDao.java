/**
 * 
 */
package com.prwss.min.sanitation.dao;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.ODFDeclarationBean;

/**
 * @author BH390738
 *
 */
public interface ODFDeclarationDao {
	public boolean save(ODFDeclarationBean odfDeclarationForm) throws DataAccessException;

}
