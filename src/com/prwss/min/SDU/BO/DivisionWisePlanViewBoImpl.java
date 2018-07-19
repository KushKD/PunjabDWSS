package com.prwss.min.SDU.BO;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.dao.DivisionWisePlanViewDao;
import com.prwss.min.SDU.form.DivisionWisePlanViewForm;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.mis.common.exception.MISException;

public class DivisionWisePlanViewBoImpl extends AbstractPaginationMaster<DivisionWisePlanViewForm> implements DivisionWisePlanViewBo<DivisionWisePlanViewForm>{
	
	private Logger log = Logger.getLogger(DivisionWisePlanViewBoImpl.class);
	
	private DivisionWisePlanViewDao divisionWisePlanViewDao;

	public DivisionWisePlanViewDao getDivisionWisePlanViewDao() {
		return divisionWisePlanViewDao;
	}

	public void setDivisionWisePlanViewDao(DivisionWisePlanViewDao divisionWisePlanViewDao) {
		this.divisionWisePlanViewDao = divisionWisePlanViewDao;
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	public List<DivisionWisePlanViewForm> getPlanViewByPagination(String searchString,int clickedColumn,String colOrder,String financialYear,String division) throws MISException {

		List<DivisionWisePlanViewForm> divisionWisePlanViewForms = null;
		try{

			divisionWisePlanViewForms = divisionWisePlanViewDao.getPlanByPagination(searchString, clickedColumn, colOrder, financialYear, division);
		
		}catch(DataAccessException e){
			log.debug(e.getMessage());
			throw new MISException(e);
		}

		return divisionWisePlanViewForms;
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------
	

}
