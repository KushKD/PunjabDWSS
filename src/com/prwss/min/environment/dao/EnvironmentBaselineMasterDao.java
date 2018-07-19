package com.prwss.min.environment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvPondBean;
import com.prwss.min.environment.bean.EnvironmentBaselineEnvWaterDisposableBean;
import com.prwss.min.environment.bean.EnvironmentBaselineWaterLoggingBean;
import com.prwss.min.environment.form.EnvironmentDataCollectionBaseLineEnvironmentForm;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentBaselineMasterDao {

	int saveMasterDataBaseline(EnvironmentBaselineBean baselineBean)
			throws DataAccessException, MISException;

	void saveMasterDataBaselinePond(
			List<EnvironmentBaselineEnvPondBean> baselineEnvPondBean)
			throws DataAccessException, MISException;

	void saveMasterDataBaselineWD(
			List<EnvironmentBaselineEnvWaterDisposableBean> baselineEnvWDBean)
			throws DataAccessException, MISException;

	void saveMasterDataBaselineWaterLogged(
			EnvironmentBaselineWaterLoggingBean environmentWaterLoggedbean)
			throws DataAccessException, MISException;

	List<EnvironmentBaselineBean> getDataBaseline(
			String appId) throws DataAccessException, MISException;

	int updateMasterDataBaseline(EnvironmentBaselineBean baselineBean)throws DataAccessException, MISException;

}
