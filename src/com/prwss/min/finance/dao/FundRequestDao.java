/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.ContractManagementMasterBean;
import com.prwss.min.finance.bean.FundRequestDocBean;
import com.prwss.min.finance.bean.FundRequestDto;
import com.prwss.min.finance.bean.FundRequestMasterBean;

/**
 * @author BH390738
 *
 */
public interface FundRequestDao {

	public List<FundRequestDto> getSchemeDetailsUpgrade(String schemeId) throws DataAccessException;
	public List<ContractManagementMasterBean> getContractAwardDetails(String schemeId) throws DataAccessException;
	public FundRequestMasterBean save(FundRequestMasterBean fundRequestMasterBean)throws DataAccessException;
	
	public boolean saveFundRequestDao(FundRequestDocBean fundRequestDocBean)throws DataAccessException;

	
}
