package com.prwss.min.quality;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.ResultEntryBean;
import com.prwss.min.bean.TestResultCommentBean;
import com.prwss.min.bean.WorkFlowMasterBean;
import com.prwss.min.dao.DisplayTestResultDao;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class DisplayEntryBoImpl implements DisplayEntryBo {

	private Logger log = Logger.getLogger(DisplayEntryBoImpl.class);

	private DisplayTestResultDao  displayTestResultDao;
	
	
	/**
	 * @param displayTestResultDao the displayTestResultDao to set
	 */
	public void setDisplayTestResultDao(DisplayTestResultDao displayTestResultDao) {
		this.displayTestResultDao = displayTestResultDao;
	}


	@Override
	public boolean updateResultEntry(String testId, String level,String requestFlow,String empId,String comments) throws MISException {
	
		boolean flag=false;
		List<ResultEntryBean> resultBean=null;
		List<WorkFlowMasterBean> workFlowMasterBeans=null;
		try{
			
			workFlowMasterBeans=displayTestResultDao.getWorkflowMaster(testId, level, requestFlow, empId);
			resultBean=displayTestResultDao.getResultEntry(testId);
			if(!MisUtility.ifEmpty(resultBean)){
				for(ResultEntryBean bean:resultBean){
					ResultEntryBean bean1=populateResultEntryBean(bean,workFlowMasterBeans,testId, requestFlow);
					flag=displayTestResultDao.updateResultEntry(bean1);
					
					//save comments
					TestResultCommentBean testResultCommentBean=populateTestResultComment(comments,testId,empId);
					displayTestResultDao.saveComments(testResultCommentBean);
					}
			}
			
			
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw new MISException(e);
		}
		
		return flag;
	}
	
	private  ResultEntryBean populateResultEntryBean(ResultEntryBean resultEntryBean,List<WorkFlowMasterBean> workFlowMasterBeans,
			String testId,String requestFlow){
		
		//ResultEntryBean resultEntryBean=new ResultEntryBean();
		
		for(WorkFlowMasterBean workFlowMasterBean:workFlowMasterBeans){
			//resultEntryBean.setTestResultId(Integer.parseInt(testId));
			
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_ONE)&&requestFlow.equalsIgnoreCase("Forward")){
				resultEntryBean.setRequestLevel(2);
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_fwd());
			}
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_TWO)&&requestFlow.equalsIgnoreCase("Forward")){
				resultEntryBean.setRequestLevel(3);
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_fwd());
				//resultEntryBean.setReviewerComment(reviewer);
			}
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_TWO)&&requestFlow.equalsIgnoreCase("Return")){
				resultEntryBean.setRequestLevel(1);
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_ret());
				//resultEntryBean.setReviewerComment(reviewer);
			}
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_TWO)&&requestFlow.equalsIgnoreCase("ReturnToInitiator")){
				resultEntryBean.setRequestLevel(1);
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_rti());
				//resultEntryBean.setApproverComment(reviewer);
			}
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_THREE)&&requestFlow.equalsIgnoreCase("Return")){
				resultEntryBean.setRequestLevel(2);
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_ret());
				//resultEntryBean.setApproverComment(approver);
			}
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_THREE)&&requestFlow.equalsIgnoreCase("ReturnToInitiator")){
				resultEntryBean.setRequestLevel(1);
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_rti());
				//resultEntryBean.setApproverComment(approver);
			}
			if(workFlowMasterBean.getLevel()==Integer.parseInt(MISConstants.REQUEST_THREE)&&requestFlow.equalsIgnoreCase("Approve")){
				resultEntryBean.setRequestLevel(1);
				resultEntryBean.setTestStatus(Integer.parseInt(MISConstants.REQUEST_TEN));
				resultEntryBean.setLyingWithUser(workFlowMasterBean.getTo_emp_id_rti());
				//resultEntryBean.setApproverComment(approver);
			}
			
		}
		
		
		
		return resultEntryBean;
	}
	
	private TestResultCommentBean populateTestResultComment(String comment,String testId,String empId){
		
		TestResultCommentBean testResultCommentBean=null;
		
		try{
			testResultCommentBean=new TestResultCommentBean();
			testResultCommentBean.setCommentBy(Integer.parseInt(empId));
			testResultCommentBean.setCommentText(comment);
			testResultCommentBean.setCommentDate( new java.sql.Date(new Date().getTime()));
			testResultCommentBean.setCreatedDate(new java.sql.Date(new Date().getTime()));
			testResultCommentBean.setTestResultId(Integer.parseInt(testId));
			
		}catch(Exception e){
			log.debug(e);
		}
		return testResultCommentBean;
	}


}
