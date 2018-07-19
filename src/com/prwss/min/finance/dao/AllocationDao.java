/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.AllocationBean;
import com.prwss.min.finance.bean.FinanceHeadStructureBean;
import com.prwss.min.finance.bean.FundRequestDto;

/**
 * @author BH390738
 *
 */
public interface AllocationDao {

	public List<FundRequestDto> getFundRequestMasterBean(String requestId)throws DataAccessException;
	
	public List<FinanceHeadStructureBean> getHeadBean()throws DataAccessException;
	
	public List<FundRequestDto> getFetchInstallmentAmountDetails(String requestNo)throws DataAccessException;

	public boolean save(AllocationBean allocationBean) throws DataAccessException;
}
