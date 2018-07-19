package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.HallofFameBean;
import com.prwss.min.sanitation.bean.HallofFameDetailsDto;

public interface HallofFameDao {

	public boolean saveHallofFameDetails(HallofFameBean hallofFameBean)throws DataAccessException;
	public List<HallofFameBean> getHallofFameByPagination() throws DataAccessException;
	public List<HallofFameDetailsDto> gethallofFameDetails(String activityId)throws DataAccessException;
	public List<HallofFameDetailsDto> getAttachmentData(String activityId)throws DataAccessException;

}
