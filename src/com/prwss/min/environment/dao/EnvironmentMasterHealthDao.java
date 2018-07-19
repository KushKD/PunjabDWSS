package com.prwss.min.environment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.bean.EnvironmentHealthBean;
import com.prwss.min.environment.bean.EnvironmentVectorBourneDiseaseBean;
import com.prwss.min.environment.bean.EnvironmentWaterBourneDiseaseBean;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentMasterHealthDao {

	int saveMasterDataSocial(EnvironmentHealthBean healthBean) throws DataAccessException , MISException;

	List<EnvironmentHealthBean> getDataHealth(String appId) throws DataAccessException , MISException;

	int updateMasterDataBaseline(EnvironmentHealthBean healthBean) throws DataAccessException , MISException;

	void saveWaterBourneDisease( List<EnvironmentWaterBourneDiseaseBean> waterBourneDiseaseBean) throws DataAccessException , MISException;

	void saveVectorBourneDisease( List<EnvironmentVectorBourneDiseaseBean> vectorBourneDiseaseBean) throws DataAccessException , MISException;

}
