package com.prwss.min.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.quality.ParameterMasterForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public interface ParameterMasterDao {

	public List<ParameterMasterBean> findParameterData(ParameterMasterForm parameterMasterForm) 	throws DataAccessException ;
	public boolean saveParameterData(ParameterMasterBean parameterMasterLst) 	throws DataAccessException ;
	public boolean updateParameterData(ParameterMasterBean parameterMasterLst) 	throws DataAccessException ;
	public List<ParameterMasterBean> getParameterMasterByPagination(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
	public List<EmployeeBean> getEmployee(List<String> statusList) 	throws DataAccessException ;
	public String getEmployeeDesig(List<String> statusList,long empId) 	throws DataAccessException ;
	
	public String  getParameterAcceptableLimit(int param_id ) throws DataAccessException;
	public String  getParameterPermissibleLimit(int param_id) throws DataAccessException;
	public List<ParameterMasterBean> getAllParameters() throws DataAccessException;
	public String getParameterIdName(String  param_id) throws DataAccessException;
}
