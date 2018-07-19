package com.prwss.min.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.LabToParameterMapping;
import com.prwss.min.bean.ParameterMasterBean;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.ResultEntryDetailBean;
import com.prwss.min.bean.SampleDistributionBean;
import com.prwss.min.bean.SamplePartCodeLabMapping;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public interface ResultEntryDao {
	
	public int saveResultEntryData(ResultEntryBean resultEntryBean) throws DataAccessException;
	public boolean updateResultEntryData(ResultEntryBean resultEntryBean) throws DataAccessException;
	public boolean updateResultEntryDetails(Collection<ResultEntryDetailBean> resultEntryBean) throws DataAccessException;

	public boolean saveResultEntryDetails(Collection<ResultEntryDetailBean> resultEntryBean) throws DataAccessException;
	
	public List<SampleDistributionBean> getSamplePartNo(String sampleNo,String mode)throws DataAccessException;
	
	public List<SampleDistributionBean> getSamplePartNos(String samplePartNo)throws DataAccessException;
	
	
	public List<SampleDistributionBean> fetchEmployeeId(String labId)throws DataAccessException;
	public List<EmployeeBean> fetchEmployeeName(List<Long> empId,List<String> status)throws DataAccessException;
	public List<SampleDistributionBean> fetchSampleNumber(String sampleNumber)throws DataAccessException;
	
	public ReceiveSampleBean getSampleName(int sampleId)throws DataAccessException;
	public List<ResultEntryBean> getResultEntry(int testResultId)throws DataAccessException;
	public List<LabToParameterMapping> getLabToParameter(SamplePartCodeLabMapping samplePartCodeLabMapping)throws DataAccessException;
	
	public List<ParameterMasterBean> findParameterList(List<Integer> pararmeterId)throws DataAccessException;
	
	public List<ResultEntryBean> findResultEntryList(String resultEntryId)throws DataAccessException;

	public List<SamplePartCodeLabMapping> fetchTestType(SampleDistributionBean sampleDistributionBean) throws DataAccessException;
	public Set<ResultEntryBean> getLocationMasterByPagination(int empId, String searchString, int clickedColumn,
			String colOrder) throws DataAccessException;
	public List<ReceiveSampleBean> fetchSampleCollectionDetails(SampleDistributionBean sampleDistributionBean) throws DataAccessException;
	
	public List<SampleDistributionBean> fetchSamplePartDistributionDate(String samplePartNo)throws DataAccessException;
}
