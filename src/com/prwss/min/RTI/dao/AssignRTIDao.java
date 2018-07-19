package com.prwss.min.RTI.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.RTI.bean.AssignRTIDetailsDto;
import com.prwss.min.RTI.bean.AssignRtiDto;
import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.bean.UpdateRTIDto;
import com.prwss.min.RTI.form.AssignRtiForm;
import com.prwss.mis.admin.dao.DesignationBean;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public interface AssignRTIDao {
	
	
	public List<AssignRtiDto> getAllRTI(String searchString,int clickedColumn,String colOrder) throws DataAccessException ;
	public List<AssignRTIDetailsDto> getRTIDetails(String rtiId)throws DataAccessException;

	
	//Get All Designation
	public List<DesignationBean> getAllDesignation() throws DataAccessException ;
	public List<EmployeeBean> getAllEmployee( String id) throws DataAccessException ;
	public List<SubmitRtiBean> findRtiCollection(AssignRtiForm rtiForm) throws DataAccessException;
	public Boolean assignRtiResponse(SubmitRtiBean submitRtiBean2) throws DataAccessException;
	public List<UpdateRTIDto> getAttachmentData(String rtiID) throws DataAccessException;
}
