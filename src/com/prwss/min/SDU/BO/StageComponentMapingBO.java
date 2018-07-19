package com.prwss.min.SDU.BO;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.form.StageComponentMpgForm;

public interface StageComponentMapingBO {
	
	public Boolean CheckBeforeSave(StageComponentMpgForm form , Integer userId ) throws DataAccessException;

}
