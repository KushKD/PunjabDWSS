package com.prwss.min.sanitation.bo;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.form.HallofFameForm;
import com.prwss.mis.common.exception.MISException;

public interface HallofFameBo<T> {

	public boolean saveHallofFameDetails(HallofFameForm hallofFameForm) throws MISException;
	public List<HallofFameForm> getHallofFameByPagination() throws DataAccessException;
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
