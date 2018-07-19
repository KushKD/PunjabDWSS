/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FinanceHeadBeans;
import com.prwss.min.finance.bean.FinanceHeadStructureBean;
import com.prwss.min.finance.form.FinanceHeadsStructureForm;

/**
 * @author BH390738
 *
 */
public interface FinancialHeadStructureDao {

	public List<FinanceHeadBeans> getHeadType(String headType,String head)throws DataAccessException;
	public List<FinanceHeadStructureBean> getHeadStructureBean(FinanceHeadsStructureForm financeHeadsStructureForm)throws DataAccessException;
	
	public boolean save(FinanceHeadStructureBean financeHeadStructureBean)throws DataAccessException;
	public boolean update(FinanceHeadStructureBean financeHeadStructureBean)throws DataAccessException;
	public List<FinanceDto> populateFinanceStructureHead(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
}
