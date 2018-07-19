package com.prwss.min.environment.bo;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.min.environment.form.EnvironmentDataCollectionForm;
import com.prwss.min.quality.LabMasterBo;
import com.prwss.min.quality.LabMasterForm;
import com.prwss.mis.common.exception.MISException;

public interface EnvironmentEDSMasterBo<T>  {

	Integer saveMaster( EnvironmentDataCollectionForm environmentDataCollectionForm, int parseInt) throws MISException,DataAccessException;

	List<EnvironmentDataCollectionForm> getdataByPagination( String searchParameter, int clickedColumn, String clickedColumnDir);

	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber, Integer iDisplayStart);

	Integer updateMaster(EnvironmentDataCollectionForm environmentDataCollectionForm,int parseInt) throws MISException,DataAccessException;
}
