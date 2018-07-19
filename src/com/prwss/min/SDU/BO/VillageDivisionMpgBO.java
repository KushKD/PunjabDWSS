package com.prwss.min.SDU.BO;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.form.VillageDivisionMpgForm;

public interface VillageDivisionMpgBO {
	
	public Boolean saveData (VillageDivisionMpgForm from , int user_id) throws DataAccessException ;

}
