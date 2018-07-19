package com.prwss.min.environment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.environment.bean.EnvironmentBaselineBean;
import com.prwss.min.environment.bean.EnvironmentSocialBean;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentSocialMasterDao {

	List<EnvironmentSocialBean> getDataBaseline(String appId) throws MISException,DataAccessException;

	int saveMasterDataSocial(EnvironmentSocialBean socialBean) throws MISException,DataAccessException;

	int updateMasterDataBaseline(EnvironmentSocialBean socailBean) throws MISException,DataAccessException;

}
