package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.GramPanchayatDetailBean;
import com.prwss.min.sanitation.bean.GramPanchayatDto;
import com.prwss.min.sanitation.bean.GramPanchayatMasterBean;

public interface GramPanchayatMasterDao {
	
	public int savegramPanchayatMaster(GramPanchayatMasterBean gramPanchayatMasterBean) throws DataAccessException;
	
	public List<GramPanchayatMasterBean> getgramPanchayatMaster(GramPanchayatMasterBean gramPanchayatMasterBean) throws DataAccessException;
	
	public boolean savegramPanchayatDetail(List<GramPanchayatDetailBean> gramPanchayatMasterBean) throws DataAccessException;
	
	public List<GramPanchayatDto> getLocationMasterByPagination()throws DataAccessException;

	public Boolean getStatusCategory(String status, String village) throws DataAccessException;

}
