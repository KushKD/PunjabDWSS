package com.prwss.min.RTI.bo;

import java.util.List;

import com.prwss.min.RTI.bean.AssignRtiDto;
import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.form.AssignRtiForm;
import com.prwss.mis.common.exception.MISException;

public interface AssignRTIBo {

	
	
	public List<SubmitRtiBean> populateRTIDetails(String searchString,int clickedColumn,String colOrder)throws MISException;
	public List<AssignRtiDto> populateRTIDetail(String searchString,int clickedColumn,String colOrder)throws MISException;
//	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber, Integer iDisplayStart) ;
	public boolean saveRTIUpdateDetails(AssignRtiForm assignRtiForm)throws MISException;

	
	
}
