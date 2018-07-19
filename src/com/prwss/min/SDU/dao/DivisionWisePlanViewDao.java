package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.form.DivisionWisePlanViewForm;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public interface DivisionWisePlanViewDao {

	public List<DivisionWisePlanViewForm> getPlanByPagination(String searchString, int clickedColumn, String colOrder, String financialYear, String division) throws DataAccessException;
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> locationId)throws DataAccessException;
	public List<String> getAllLocationIds(String UserID) throws DataAccessException;
	public List<DivisionWisePlanViewForm> getPlanDetails(String activityVillageId) throws DataAccessException;

}
