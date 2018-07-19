/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.ComponentBean;
import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;

/**
 * @author BH390738
 *
 */
public interface ComponentDao {

	public Long save(ComponentBean componentBean)throws DataAccessException;
	public boolean saveDetails(ComponentDetailsBean componentDetailBean)throws DataAccessException;
	public List<ComponentDetailsBean> getParent(String componentType,String comType)throws DataAccessException;
	public List<FinanceDto> populateComponent(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
}
