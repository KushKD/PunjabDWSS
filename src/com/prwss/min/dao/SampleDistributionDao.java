package com.prwss.min.dao;



import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.LabEmployee;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.min.quality.SampleDistributionForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;


public interface SampleDistributionDao {

	public boolean saveSampleDistributionData(SampleDistributionBean sampleDistributionBean) 	throws DataAccessException ;
	public List<SampleDistributionBean> sampleDistributionData(SampleDistributionForm sampleDistributionForm)throws DataAccessException;
	public List<SampleDistributionBean> getsampleMasterBysamplenub(SampleDistributionForm sampleDistributionForm)throws DataAccessException;
	public boolean UpdateSchemeDistributionMaster(SampleDistributionBean sampleDistributionBean) throws DataAccessException;
	public List<SampleDistributionBean> getsampleDistributionByPagination(String searchString,int clickedColumn,String colOrder) throws DataAccessException;
	public List<LabEmployee> fetchEmployeeId(int labId) throws DataAccessException;
	public List<SamplePartCodeLabMapping> fetchTestType(String labId) throws DataAccessException;
	public List<SamplePartCodeLabMapping> fetchTestType1(String testType1) throws DataAccessException;
	public List<SamplePartCodeLabMapping> fetchSubSampleNumber(String testType) throws DataAccessException;
	public List<ReceiveSampleBean> getSampleNo(String sampleNumber)throws DataAccessException;
	public List<EmployeeBean> fetchEmployeeName(List<Long> empId,List<String> status)throws DataAccessException;
	public boolean updateSampleDistributionData(SampleDistributionBean sampleDistributionBean) 	throws DataAccessException ;
	public boolean updateSamplePart(SamplePartCodeLabMapping samplePartCodeLabMapping);
	
	public List<ReceiveSampleBean> fetchSampleAcceptDate(String sampleNo)throws DataAccessException;
	public boolean updateSampleCollection(String sampleNo,String freeze,Date currentDate)throws DataAccessException;
	
}
