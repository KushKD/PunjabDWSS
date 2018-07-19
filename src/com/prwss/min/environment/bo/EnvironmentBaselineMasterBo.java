package com.prwss.min.environment.bo;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentBaselineMasterBo {

	int saveMaster(
			EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm ,int parseInt) throws MISException,DataAccessException;

	EnvironmentDataCollectionBaseLineEnvironmentForm getEnvironmentDataSheetData( String appId) throws MISException,DataAccessException;

	int updateMaster( EnvironmentDataCollectionBaseLineEnvironmentForm baseLineEnvironmentForm, int parseInt)throws MISException,DataAccessException;

}
