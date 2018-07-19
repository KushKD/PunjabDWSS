/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.CCBean;
import com.prwss.min.construction.quality.bean.MonthlyPlanDto;
import com.prwss.min.construction.quality.bean.ShareCommonObservationBean;
import com.prwss.min.construction.quality.bean.ShareObservationBean;
import com.prwss.min.construction.quality.form.ShareObservationForm;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;

/**
 * @author BH390738
 *
 */
public interface ShareObservationDao {

	public List<MonthlyPlanDto> getEmployee(List<String> statusList,String designationId);
	public List<EmployeeDesignationBean> getEmployeeDesig()throws DataAccessException ;
	public List<Integer> getScheme(String yearPlanId,String monthlyId)throws DataAccessException;
	public List<MonthlyPlanDto> getSchemeName(List<Integer> schemeIds)throws DataAccessException;
	public List<String> getComments(String scheme)throws DataAccessException;
	public Long saveCommonObservationData(ShareCommonObservationBean shareCommonObservationBeans)throws DataAccessException;
	public boolean saveObservation(List<ShareObservationBean> shareObservationBeans)throws DataAccessException;
	public boolean saveCc(List<CCBean> ccBeans)throws DataAccessException;
	public List<Long> getMonthlyPlanId(ShareObservationForm shareObservationForm)throws DataAccessException;
}
