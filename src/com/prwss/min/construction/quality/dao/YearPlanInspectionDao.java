/**
 * 
 */
package com.prwss.min.construction.quality.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.construction.quality.bean.ExternalAgencyMasterBean;
import com.prwss.min.construction.quality.bean.YearlyPlanInspectionBean;
import com.prwss.min.construction.quality.bean.YearlyPlanningComponentMappingBean;

/**
 * @author BH390738
 *
 */
public interface YearPlanInspectionDao {

	public List<ExternalAgencyMasterBean> fetchExternalAgency()throws DataAccessException;
	public int saveYearPlanDetails(YearlyPlanInspectionBean yearlyPlanInspectionBean) throws DataAccessException;
	public boolean saveYearPlanComponentDetails(List<YearlyPlanningComponentMappingBean> yearlyPlanInspectionBean) throws DataAccessException;
}
