package com.prwss.min.SDU.BO;

import java.util.List;

import com.prwss.min.SDU.form.DivisionWisePlanViewForm;
import com.prwss.mis.common.exception.MISException;

public interface DivisionWisePlanViewBo<T> {

	public List<DivisionWisePlanViewForm> getPlanViewByPagination(String searchParameter, int clickedColumn,
			String clickedColumnDir, String financialYear, String division) throws MISException;
	
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);

}
