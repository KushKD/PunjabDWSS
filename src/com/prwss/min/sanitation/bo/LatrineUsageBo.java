/**
 * 
 */
package com.prwss.min.sanitation.bo;

import java.util.List;

import com.prwss.min.sanitation.bean.BeneficiaryDto;
import com.prwss.min.sanitation.form.LatrineUsageForm;
import com.prwss.mis.common.exception.MISException;

/**
 * @author BH390738
 *
 */
public interface LatrineUsageBo {

	public boolean saveLatrine(LatrineUsageForm latrineUsageForm)throws MISException;
	
	public List<LatrineUsageForm> getLatrineDetails(String searchParameter,int clickedColumn,String clickedColumnDir)throws MISException;
	public List<LatrineUsageForm> getListBasedOnPageNumber(List<LatrineUsageForm> formList, Integer pageDisplayLength, Integer pageNumber,
			Integer iDisplayStart);
}
