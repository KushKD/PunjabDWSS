package com.prwss.min.RTI.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.form.SubmitRtiForm;

public interface SubmitRtiDao {
	public boolean saveRtiDetails(SubmitRtiBean submitRtiBean)throws DataAccessException;
	public List<UpdateRTIDto> getRtiByPagination(String searchString,int clickedColumn,String colOrder) throws DataAccessException;
	public List<UpdateRTIDto> getRtiDetails(String rtiID) throws DataAccessException;
	public List<UpdateRTIDto> getAttachmentData(String rtiID) throws DataAccessException;
	public List<SubmitRtiBean> findRtiCollection(SubmitRtiForm rtiForm) throws DataAccessException;
	public Boolean updateRtiResponse(SubmitRtiBean submitRtiBean)throws DataAccessException;

}
