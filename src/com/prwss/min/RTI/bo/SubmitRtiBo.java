package com.prwss.min.RTI.bo;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.form.SubmitRtiForm;
import com.prwss.mis.common.exception.MISException;

public interface SubmitRtiBo {

	public boolean saveRtiDetails(SubmitRtiForm RtiForm)throws MISException;
	public List<UpdateRTIDto> getRtiByPagination(String searchString,int clickedColumn,String colOrder) throws DataAccessException, MISException;
	public boolean saveRTIUpdateDetails(SubmitRtiForm rtiForm) throws MISException;
	
}
