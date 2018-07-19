package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.ProgressStageMappingBean;
import com.prwss.min.sanitation.bean.ProgressWorkBean;

public interface ProgressofWorkDao {
	
	public int saveProgressWorkMaster(ProgressWorkBean progressWorkBean) throws DataAccessException;
	
	//public List<ProgressWorkBean> getProgressWorkMaster(ProgressWorkBean progressWorkBean) throws DataAccessException;
	
	public boolean saveprogressStageMapping(List<ProgressStageMappingBean> progressStageMappingBeans) throws DataAccessException;

}
