package com.prwss.min.sanitation.bo;

import com.prwss.min.sanitation.form.ProgressofWorkForm;
import com.prwss.mis.common.exception.MISException;

public interface ProgressofWorkBo {
	
	public boolean saveProgressofWork(ProgressofWorkForm progressofWorkForm)throws MISException;

}
