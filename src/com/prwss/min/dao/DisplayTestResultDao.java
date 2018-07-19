/**
 * 
 */
package com.prwss.min.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.TestResultCommentBean;
import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.quality.ResultEntryDto;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

/**
 * @author bhsingh
 *
 */
public interface DisplayTestResultDao {

	public List<ResultEntryDto> getResultEntry(int sampleId) throws DataAccessException;
	public List<ReceiveSampleBean> fetchSampleId(String sampleNumber) throws DataAccessException;
	public boolean updateResultEntry(ResultEntryBean resultEntryBean) throws DataAccessException;
	public List<ResultEntryBean> getResultEntry(String  testId) throws DataAccessException;
	public List<WorkFlowMasterBean> getWorkflowMaster(String testId, String level,String requestFlow,String empId) throws DataAccessException;
	List<EmployeeBean> fetchEmployeeName(Long empId, List<String> status) throws DataAccessException;
	public List<ResultEntryBean> getEmployeeId(int testId) throws DataAccessException;
	public boolean saveComments(TestResultCommentBean testResultCommentBean) throws DataAccessException;
	public List<TestResultCommentBean> fetchComments(int testresultid) throws DataAccessException;

}
