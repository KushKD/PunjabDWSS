package com.prwss.min.sanitation.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.sanitation.bean.ComboDetailPoJo;
import com.prwss.min.sanitation.bean.GramPanchayatDetailsDto;
import com.prwss.min.sanitation.bean.GramPanchayatRegisterBean;
import com.prwss.min.sanitation.form.ViewRegistrationsForm;


public interface GramPanchayatRegisterDao {

	public boolean saveGramPanchayatRegisterData(GramPanchayatRegisterBean gramPanchayatRegisterBean) 	throws DataAccessException ;
	public List<ComboDetailPoJo> getHabitationFromCombo(String  comboId) 	throws DataAccessException ;
	public List<GramPanchayatDetailsDto> getGramPanchayatDetails(ViewRegistrationsForm form) 	throws DataAccessException ;
	
	
}
