package com.prwss.min.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.bean.LabMasterBean;
import com.prwss.min.bean.PMSSchemeDetailsBean;
import com.prwss.min.bean.ReceiveSampleBean;
import com.prwss.min.bean.SampleCodeLabMapping;
import com.prwss.min.bean.SchemeMapping;
import com.prwss.min.bean.VillageDetailsBean;
import com.prwss.min.quality.ReceiveSampleForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;

public interface ReceivingSampleDao {

	public boolean saveSampleData(ReceiveSampleBean receiveSampleLst) 	throws DataAccessException ;

	public List<EmployeeBean> getEmployee(List<String> statusList,String designationId) 	throws DataAccessException ;
	public List<EmployeeDesignationBean> getEmployeeDesig(List<String> statusList)throws DataAccessException ;
	public List<VillageDetailsBean> findSchemeFromVillage(String VillageId) 	throws DataAccessException ;
	public String fetchSchemeName(String  performaMaserBeans) 	throws DataAccessException ;
	public List<VillageDetailsBean> findHabitationFromVillage(String  villageId) 	throws DataAccessException ;
	public List<EmployeeBean>   getEmployeeDetails(String empId,List<String> status)throws DataAccessException;
	public List<ReceiveSampleBean>   getSampleCollectionByPagination(String searchString,int clickedColumn,String colOrder)throws DataAccessException;
	public List<ReceiveSampleBean>   getSampleCollection(ReceiveSampleForm receiveSampleForm)throws DataAccessException;
	public List<ReceiveSampleBean>   findSampleCollection(ReceiveSampleForm receiveSampleForm)throws DataAccessException;
	public boolean   updateSampleCollection(ReceiveSampleBean receiveSampleBean)throws DataAccessException;
	public List<SchemeMapping> fetchWaterSource(String  schemeId) 	throws DataAccessException ;
	public List<PMSSchemeDetailsBean> findSchemeName(List<Integer> schemeId) 	throws DataAccessException ;
	public List<LabMasterBean> findSampleCode(String labId);
	public List<SampleCodeLabMapping> findLabIdSampleMapping(String labId)throws DataAccessException;
	public boolean update(SampleCodeLabMapping sampleCodeLabMapping)throws DataAccessException;
	
	
	//getSampleCollectionByPagination

//findHabitationFromVillage
	
}
