package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ConsolidatedPlanDivisionWiseDTO;

public interface ConsolidatedPlanDivisionWiseDao {

	public List<ConsolidatedPlanDivisionWiseDTO> getConsolidatePlanDivision(String financialYear) throws DataAccessException;
	public List<ConsolidatedPlanDivisionWiseDTO> getPendingDivisions(List<Integer> divisionId)throws DataAccessException;
	public List<Integer> getExistingDivisionId(Integer financialYear)throws DataAccessException;
	public List<ConsolidatedPlanDivisionWiseDTO> getDetails(Integer financialYear, Integer divisionId)throws DataAccessException;

}
