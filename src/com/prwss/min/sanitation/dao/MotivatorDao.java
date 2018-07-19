package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.MotivatorAcademicMapping;
import com.prwss.min.sanitation.bean.MotivatorBean;
import com.prwss.min.sanitation.form.MotivatorEntryForm;

/**
 * @author BH390738
 *
 */
public interface MotivatorDao {

	public List<MotivatorBean> validateMotivatorDetails(MotivatorEntryForm motivatorEntryForm)throws DataAccessException;
	
	public boolean saveMotivatorDetails(MotivatorBean motivatorEntryForm)throws DataAccessException;
	
	public boolean saveMotivatorAcademicDetails(List<MotivatorAcademicMapping> motivatorAcademicMappings)throws DataAccessException;
	
	
}
