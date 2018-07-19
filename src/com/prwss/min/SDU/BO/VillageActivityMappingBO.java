package com.prwss.min.SDU.BO;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.form.VillageActivityMpgForm;

public interface VillageActivityMappingBO {

	boolean save(VillageActivityMpgForm villageActivityMpgForm, int parseInt) throws DataAccessException;

}
