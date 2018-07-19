package com.prwss.min.environment.bo;

import org.hibernate.exception.DataException;

import com.prwss.min.environment.form.EnvironmentDataCollectionHealthForm;
import com.prwss.min.environment.form.EnvironmentDataCollectionSocialForm;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentMasterHealthBo {

	int saveMaster(EnvironmentDataCollectionHealthForm healthEnvironmentForm,
			int parseInt) throws DataException, MISException;

	EnvironmentDataCollectionHealthForm getEnvironmentDataSheetData(String appId) throws DataException, MISException;

	int updateMaster(EnvironmentDataCollectionHealthForm healthEnvironmentForm,
			int parseInt) throws DataException, MISException;

}
