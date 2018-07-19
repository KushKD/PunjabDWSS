/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.bean.FundSourceMasterBean;
import com.prwss.min.finance.bean.PMSSchemeDetailUpgradeBean;
import com.prwss.mis.admin.ProgramMasterBean;

/**
 * @author BH390738
 *
 */
public interface FundSourceMasterDao {

	public List<ProgramMasterBean> getProgramBean()throws DataAccessException;
	
	public List<Integer> getSchemeByProgram(String programId)throws DataAccessException;
	
	public boolean saveFundSourceMaster(FundSourceMasterBean fundSourceMasterBeans)throws DataAccessException;
	
	public List<FinanceDto> populateFundSourceMaster(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
	
	public List<PMSSchemeDetailsBean> getSchemeName(List<Integer> schemeId)throws DataAccessException;
}
