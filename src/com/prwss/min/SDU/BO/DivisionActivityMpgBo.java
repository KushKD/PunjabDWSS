package com.prwss.min.SDU.BO;

import com.prwss.min.SDU.form.DivisionActivityMpgForm;
import com.prwss.mis.common.exception.MISException;

public interface DivisionActivityMpgBo {

	boolean save(DivisionActivityMpgForm divisionActivityMpgForm, Integer enteredBy) throws MISException;

}
