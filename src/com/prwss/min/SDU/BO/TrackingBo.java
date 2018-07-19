package com.prwss.min.SDU.BO;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.form.TrackingForm;

public interface TrackingBo {

	boolean save(TrackingForm trackingForm, int parseInt) throws DataAccessException;
	
	
	

}
