/**
 * 
 */
package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.LatrineUsageBean;

/**
 * @author BH390738
 *
 */
public interface LatrineUsageDao {

	public boolean saveLatrine(LatrineUsageBean latrineUsageBean)throws DataAccessException;
	public List<LatrineUsageBean> getLatrineDetails(String searchParameter,int clickedColumn,String clickedColumnDir)throws DataAccessException;

}
