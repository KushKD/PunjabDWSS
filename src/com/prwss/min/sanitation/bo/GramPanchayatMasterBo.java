package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.GramPanchayatDto;
import com.prwss.min.sanitation.form.GramPanchayatMasterForm;
import com.prwss.mis.common.exception.MISException;

public interface GramPanchayatMasterBo<T> {
	
	public boolean saveGramPanchayat(GramPanchayatMasterForm gramPanchayatMasterForm)throws MISException;
	
	public List<GramPanchayatDto> getGramPanchayatMasterByPagination() throws MISException;
	
	public List<T> getListBasedOnPageNumber(List<T> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);

}
