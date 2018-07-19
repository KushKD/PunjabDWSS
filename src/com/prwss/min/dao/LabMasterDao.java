package com.prwss.min.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.mis.admin.LocationMasterDto;

public interface LabMasterDao {

	public boolean saveLabData(LabMasterBean labMasterBean) throws DataAccessException;
	public List<LabMasterBean> getLabMasterByPagination(String searchString,int clickedColumn,String colOrder) throws DataAccessException;
	public List<LocationMasterDto> getvillageLocation(String parentLocationId)throws DataAccessException; 
	public List<LabMasterBean> getLabMasterData(LabMasterForm labMasterForm)throws DataAccessException;
	public List<LabMasterBean> getLabMasterByLabId(LabMasterForm labMasterForm)throws DataAccessException;
	public  boolean UpdateLabMaster(LabMasterBean labMasterBean)throws DataAccessException;
	//getLabMasterByLabId
}
