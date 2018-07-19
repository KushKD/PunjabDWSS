/**
 * 
 */
package com.prwss.min.finance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.finance.bean.ComponentDetailsBean;
import com.prwss.min.finance.bean.DivisionBudgetBean;
import com.prwss.min.finance.bean.FinanceDto;
import com.prwss.min.finance.form.DivisionBudgetForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.login.dao.LoginUserBean;

/**
 * @author BH390738
 *
 */
public interface DivisionBudgetDao {

	public List<ComponentDetailsBean> getChildComponent(String componentType,String componentId)throws DataAccessException;
	public List<ComponentDetailsBean> getComponentType(String componentType,String componentId)throws DataAccessException;
	public List<Long> getSubComponentLevel1(String componentType,String componentId)throws DataAccessException;
	
	public List<Long> getSubComponentLevel2(List<Integer> subComponentLevel1)throws DataAccessException;
	
	
	public List<ComponentDetailsBean> getActivity(List<Integer> componentDetailsId)throws DataAccessException;
	
	public List<FinanceDto> getDivisionBudgetBean()throws DataAccessException;
	
	public List<DivisionBudgetBean> getDivisionBudgetBean(String divAnnBudgId)throws DataAccessException;
	
	public boolean save(DivisionBudgetBean divisionBudgetBean)throws DataAccessException;
	
	public List<FinanceDto> populateBudgetData(String searchString, int clickedColumn, String colOrder)throws DataAccessException;
	
	public List<FinanceDto> populateDivisionBudgetData(String searchString, int clickedColumn, String colOrder)throws DataAccessException;
	
	public List<Integer> getDivision(MISSessionBean misSessionBean)throws DataAccessException;
	public List<Integer> getNodalDivisionId(MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<Integer> getNodalDivision(MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<FinanceDto> getFinancialYear(String divisionId,MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<DivisionBudgetBean> getDivisionBudgets(String financialYear,String divisionId,MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<DivisionBudgetBean> getDivisionBudget(String financialYear,String divisionId,MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<DivisionBudgetBean> getDivisionBudgetState(String financialYear,String divisionId,MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<FinanceDto> getComponentDetails(DivisionBudgetForm divisionBudgetForm,String divisionType,MISSessionBean misSessionBean)throws DataAccessException;
	
	public List<FinanceDto> populateDivisionBudgetData(String division,String financialYear,String requestType,String searchString, int clickedColumn, String colOrder,MISSessionBean misSessionBean)throws DataAccessException;

	public boolean updateDivisionBudget(List<DivisionBudgetBean> divisionBudgetBeans)throws DataAccessException;
	
	public List<String> getUserId(String division)throws DataAccessException;
	
	public List<LoginUserBean> getUserName(List<String> userId)throws DataAccessException;
	
	public List<LoginUserBean> getStateUserName(String userId)throws DataAccessException;
	
	public List<String> getDdoName(String divisionId)throws DataAccessException;
	
	

}
