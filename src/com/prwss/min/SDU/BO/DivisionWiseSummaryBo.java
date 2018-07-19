package com.prwss.min.SDU.BO;

import com.prwss.min.SDU.form.DivisionWiseSummaryForm;
import com.prwss.mis.common.exception.MISException;

public interface DivisionWiseSummaryBo {

	public boolean save(DivisionWiseSummaryForm divisionWiseSummaryForm, Integer employeeId) throws MISException;

}
