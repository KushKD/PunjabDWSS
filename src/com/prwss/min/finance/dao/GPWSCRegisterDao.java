/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.GPWSCRegisterBean;
import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;

/**
 * @author BH390738
 *
 */
public interface GPWSCRegisterDao {

	public List<GramPanchayatDetailBean> getGPWSC(String villageId,String disrict)throws DataAccessException;
	
	public boolean save(GPWSCRegisterBean gpwscRegisterBean)throws DataAccessException;
	
	public List<FinanceDto> getGPWSCRegiter(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
}
