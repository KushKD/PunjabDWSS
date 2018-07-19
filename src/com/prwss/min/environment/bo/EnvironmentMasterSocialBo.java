package com.prwss.min.environment.bo;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionSocialForm;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentMasterSocialBo {

	EnvironmentDataCollectionSocialForm getEnvironmentDataSheetData( String appId) throws MISException , DataAccessException;

	int saveMaster(EnvironmentDataCollectionSocialForm socailEnvironmentForm, int parseInt) throws MISException , DataAccessException;

	int updateMaster(EnvironmentDataCollectionSocialForm socialEnvironmentForm, int parseInt) throws MISException , DataAccessException;

}
