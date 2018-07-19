/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.MonthlyProgressBean;
import com.prwss.min.construction.quality.bean.MonthlyProgressMovementBean;
import com.prwss.min.construction.quality.bean.MonthlyReportDto;
import com.prwss.min.construction.quality.bean.ProgressCommentMappingBean;
import com.prwss.min.construction.quality.bean.SaveMonthlyReportObservationBean;
import com.prwss.min.construction.quality.form.SendMonthlyReportForm;

/**
 * @author BH390738
 *
 */
public interface SendMonthlyReportDao {
	
	public Long saveSendMonthlyProgress(MonthlyProgressMovementBean monthlyProgressMovementBean)throws DataAccessException; 
	public boolean saveProgressComment(ProgressCommentMappingBean progressCommentMappingBean);
	public List<SaveMonthlyReportObservationBean> pupolateObservation(SendMonthlyReportForm sendMonthlyReportForm)throws DataAccessException;
	public List<MonthlyProgressBean> fetchMonthlyPlanId(String monthlyPlanId)throws DataAccessException;
	public boolean updateMonthlyProgress(MonthlyProgressBean monthlyProgressBean)throws DataAccessException;
	public List<MonthlyReportDto>  getMonthlyProgressData(Long entBy,String searchString, int clickedColumn, String colOrder)throws DataAccessException;
	public List<MonthlyProgressMovementBean> findMonthlyProgressMovement(SendMonthlyReportForm sendMonthlyReportForm)throws DataAccessException;
	public Long updateSendMonthlyProgress(MonthlyProgressMovementBean monthlyProgressMovementBean)throws DataAccessException; 
}
