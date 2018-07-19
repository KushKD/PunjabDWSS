/**
 * 
 */
package com.prwss.min.dao;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.CollectionCenterBean;

/**
 * @author BH390738
 *
 */
public interface CollectionCenterDao {

	public boolean save(CollectionCenterBean collectionCenterBean)throws DataAccessException;
}
