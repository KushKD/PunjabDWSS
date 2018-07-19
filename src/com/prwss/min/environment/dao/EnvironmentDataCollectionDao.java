package com.prwss.min.environment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.bean.EnvironmentEDSMaster;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentDataCollectionDao {

	 List<Integer> getGramPanchayatId(String villageID) throws DataAccessException;

	List<GramPanchayatMasterBean> getGramPanchayatDetails( List<Integer> gramPanchayatId) throws DataAccessException;

	Integer saveMasterData(EnvironmentEDSMaster environmentEDSMasterBean) throws MISException, DataAccessException;

	List<EnvironmentEDSMaster> getLabMasterByPagination(String searchParameter,int clickedColumn, String clickedColumnDir) throws DataAccessException;

	Integer updateMasterData(EnvironmentEDSMaster environmentEDSMasterBean) throws DataAccessException, MISException;

}
