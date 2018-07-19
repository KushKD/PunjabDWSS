package com.prwss.min.RTI.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.prwss.min.RTI.bean.AssignRtiDto;
import com.prwss.min.RTI.bean.SubmitRtiBean;
import com.prwss.min.RTI.dao.AssignRTIDao;
import com.prwss.min.RTI.form.AssignRtiForm;
import com.prwss.min.dao.AbstractPaginationMaster;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class AssignRTIBoImpl extends AbstractPaginationMaster<SubmitRtiBean> implements AssignRTIBo {
	private Logger log = Logger.getLogger(AssignRTIBoImpl.class);
	
	public AssignRTIDao assignRtiDao;
	
	
	

	public AssignRTIDao getAssignRtiDao() {
		return assignRtiDao;
	}
	public void setAssignRtiDao(AssignRTIDao assignRtiDao) {
		this.assignRtiDao = assignRtiDao;
	}




	@Override
	public List<AssignRtiDto> populateRTIDetail(String searchString, int clickedColumn, String colOrder)
			throws MISException {
	
		List<AssignRtiDto> dataRTIPagination = null;
		
		try{
			dataRTIPagination =  assignRtiDao.getAllRTI(searchString, clickedColumn, colOrder);
		}catch(Exception e){
			log.debug(e.getMessage());
		}
		
		
		
		
		
		return dataRTIPagination;
	}
	@Override
	public List<SubmitRtiBean> populateRTIDetails(String searchString, int clickedColumn, String colOrder)
			throws MISException {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean saveRTIUpdateDetails(AssignRtiForm assignRtiForm) throws MISException {

		Boolean status = false;
		try {
			if (MisUtility.ifEmpty(assignRtiForm)) {
				if (MisUtility.ifEmpty(assignRtiForm.getRtiID())) {
					List<SubmitRtiBean> submitRtiBeans = assignRtiDao.findRtiCollection(assignRtiForm);
					if (!MisUtility.ifEmpty(submitRtiBeans)) {
						for (SubmitRtiBean submitRtiBean2 : submitRtiBeans) {
							submitRtiBean2.setDesignation(Integer.parseInt(assignRtiForm.getDesignation()));
							submitRtiBean2.setEmployee(Integer.parseInt(assignRtiForm.getEmployee()));
							submitRtiBean2.setDueDate(MisUtility.convertStringSqlDate(assignRtiForm.getDueDate()));
							submitRtiBean2.setAssignRemarks(assignRtiForm.getAssignRemarks());
							submitRtiBean2.setIsassigned(Integer.parseInt(MISConstants.ONE));
							status = assignRtiDao.assignRtiResponse(submitRtiBean2);
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw new MISException(e);
		}
		return status;
	}

}
