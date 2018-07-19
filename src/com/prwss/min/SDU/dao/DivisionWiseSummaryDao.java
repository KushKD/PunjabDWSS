package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.DivisionWiseSummaryBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public interface DivisionWiseSummaryDao {

	public boolean saveDivisionWiseSummaryDetails(List<DivisionWiseSummaryBean> divisionWiseSummaryBean) throws DataAccessException;
	public List<String> getAllLocationIds(String UserID) throws DataAccessException;
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> idLocations) throws DataAccessException;
	public Boolean getStatusCategory(String financialYear, String division, String category) throws DataAccessException;

}
