/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FinanceHeadBean;
import com.prwss.min.finance.form.FinanceHeadsForm;

/**
 * @author BH390738
 *
 */
public interface FinancialHeadsDao {

	public boolean save(FinanceHeadBean financeHeadBean)throws DataAccessException;
	public boolean update(FinanceHeadBean financeHeadBean)throws DataAccessException;
	public List<FinanceHeadBean> getFinanceHead(FinanceHeadsForm financeHeadsForm)throws DataAccessException;
	public List<FinanceHeadBean> getParent(String headType)throws DataAccessException;
	public List<FinanceDto> populateFinanceHead(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
	
}
