/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.DdoMasterBean;
import com.prwss.min.finance.bean.FinanceDto;

/**
 * @author BH390738
 *
 */
public interface DdoMasterDao {

	public boolean saveDdoMasterBean(DdoMasterBean ddoMasterBean)throws DataAccessException;
	public List<FinanceDto> getDdoMaster(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
	
}
