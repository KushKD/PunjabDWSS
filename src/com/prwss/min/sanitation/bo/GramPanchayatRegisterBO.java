package com.prwss.min.sanitation.bo;

import com.prwss.min.sanitation.form.GramPanchayatRegisterForm;
import com.prwss.mis.common.exception.MISException;

public interface GramPanchayatRegisterBO {
	
	public boolean saveGramPanchayatRegisterData(
			GramPanchayatRegisterForm gramPanchayatform) throws MISException;

}
