package com.prwss.min.environment.bo;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.form.EnvironmentDataCollectionWaterForm;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentWaterSupplyBo {

	int saveWaterSchemeMaster(EnvironmentDataCollectionWaterForm environmentDataCollectionForm, int parseInt) throws DataAccessException, MISException;

	int updateMaster( EnvironmentDataCollectionWaterForm environmentDataCollectionWaterForm, int parseInt) throws DataAccessException, MISException;

	EnvironmentDataCollectionWaterForm getEnvironmentDataSheetData(String appId)throws DataAccessException, MISException;

}
