package com.prwss.min.environment.dao;

import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.bean.EnvironmentWaterSchemeBean;
import com.prwss.min.environment.bean.WaterSupplyGridBean;
import com.prwss.min.environment.bean.WaterSupplyNatureQualityBean;

public interface EnvironmentWaterSupplyDao {

	int saveMasterDataWaterSupply(EnvironmentWaterSchemeBean waterSupplyBean) throws DataAccessException;

	void saveWaterSupplyQualityNature( List<WaterSupplyNatureQualityBean> waterSupplyNatureQualityBean) throws DataAccessException;

	int saveGridData(List<WaterSupplyGridBean> waterSupplyGridBean) throws DataException;

	int updateMasterDataBaseline(EnvironmentWaterSchemeBean bean) throws DataAccessException;

	List<EnvironmentWaterSchemeBean> getDataBaseline(String appId) throws DataAccessException;

}
