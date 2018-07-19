package com.prwss.min.SDU.BO;

import com.prwss.min.SDU.form.TrainingDetailsEntryForm;
import com.prwss.mis.common.exception.MISException;

public interface TrainingDetailsEntryBo {

	public boolean save(TrainingDetailsEntryForm trainingDetailsEntryForm, Integer enteredBy) throws MISException;

}
